package de.fhbielefeld.pmdungeon.util.dungeonconverter;

import com.google.gson.Gson;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

import java.io.BufferedReader;
import java.io.FileReader;

public class DungeonConverter {

    public Dungeon dungeonFromJson(String filename) {
        String jsonString = readFile(filename);
        Room[] rooms = mapJsonToArray(jsonString);
        return convertToDungeon(rooms);
    }

    public Room[] roomsFromJson(String filename) {
        String jsonString = readFile(filename);
        return mapJsonToArray(jsonString);
    }

    private String readFile(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
        }
        //TODO Exception handling
        return null;
    }

    private Room[] mapJsonToArray(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Room[].class);
    }

    private Dungeon convertToDungeon(Room[] rooms) {
        Coordinate globalOffset = getOffset(rooms);
        Coordinate dungeonSize = getDungeonSize(globalOffset, rooms);
        Dungeon dungeon = new Dungeon(dungeonSize.getX(), dungeonSize.getY());
        for (Room room : rooms) {
            drawRoomEdges(globalOffset, room, dungeon);
        }
        return dungeon;
    }

    /**
     * Draws the outline edges of a room with floor tiles
     * @param globalOffset Offset of the whole dungeon
     * @param room The room that should be drawn
     * @param dungeon The dungeon in which the room should be drawn
     */
    private void drawRoomEdges(Coordinate globalOffset, Room room, Dungeon dungeon) {
        Coordinate[] node = room.getShape();
        int offsetX = room.getPosition().getX() + globalOffset.getX();
        int offsetY = room.getPosition().getY() + globalOffset.getY();
        for (int i = 0; i < node.length; i++) {
            Coordinate edgeFrom = node[i];
            Coordinate edgeTo;
            if (node.length == i + 1 ) {
                edgeTo = node[0];
            } else {
                edgeTo = node[i + 1];
            }

            //increasing Y same X
            for (int j = edgeFrom.getY(); j < edgeTo.getY(); j++) {
                dungeon.tiles[edgeFrom.getX() + offsetX][j + offsetY] = Dungeon.Tile.FLOOR;
            }
            //increasing X same Y
            for (int j = edgeFrom.getX(); j < edgeTo.getX(); j++) {
                dungeon.tiles[j + offsetX][edgeFrom.getY() + offsetY] = Dungeon.Tile.FLOOR;
            }
            //decreasing Y same X
            for (int j = edgeFrom.getY(); j > edgeTo.getY(); j--) {
                dungeon.tiles[edgeFrom.getX() + offsetX][j + offsetY] = Dungeon.Tile.FLOOR;
            }
            //decreasing X same Y
            for (int j = edgeFrom.getX(); j > edgeTo.getX() ; j--) {
                dungeon.tiles[j + offsetX][edgeFrom.getY() + offsetY] = Dungeon.Tile.FLOOR;
            }
        }
    }

    /**
     * Get the position-offset of the generated dungeon to move it in the positive area of the grid.
     * @param rooms Dungeon as array of rooms
     * @return Coordinate offset
     */
    private Coordinate getOffset(Room[] rooms) {
        Coordinate offset = new Coordinate(0, 0);
        for (Room room : rooms) {
            if (room.getPosition().getX() < offset.getX()) offset.setX(room.getPosition().getX());
            if (room.getPosition().getY() < offset.getY()) offset.setY(room.getPosition().getY());
        }
        offset.setX(offset.getX() * -1);
        offset.setY(offset.getY() * -1);
        return offset;
    }

    /**
     * Calculates the extensions of the dungeon in x and y direction.
     * @param globalOffset offset to only use the positive area of the grid.
     * @param rooms Dungeon as array of rooms
     * @return Size of the dungeon
     */
    private Coordinate getDungeonSize(Coordinate globalOffset, Room[] rooms) {
        Coordinate size = new Coordinate(Integer.MIN_VALUE,Integer.MIN_VALUE);
        for (Room room : rooms) {
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (Coordinate shape : room.getShape()) {
                if (maxX < shape.getX()) maxX = shape.getX();
                if (maxY < shape.getY()) maxY = shape.getY();
            }
            int roomX = room.getPosition().getX() + globalOffset.getX() + maxX;
            int roomY = room.getPosition().getY() + globalOffset.getY() + maxY;
            if (size.getX() <= roomX) size.setX(roomX + 1);
            if (size.getY() <= roomY) size.setY(roomY + 1);
        }
        return size;
    }
}
