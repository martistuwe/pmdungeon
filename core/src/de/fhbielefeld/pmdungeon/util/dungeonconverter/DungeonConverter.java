package de.fhbielefeld.pmdungeon.util.dungeonconverter;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

import java.io.BufferedReader;
import java.io.FileReader;

public class DungeonConverter {

    /**
     * Converts the json under the given path and filename to a dungeon
     *
     * @param filename Path and filename
     * @return The converted dungeon or null if failed
     */
    public Dungeon dungeonFromJson(String filename) {
        String jsonString = readFile(filename);
        if (jsonString == null) return null;
        Room[] rooms = mapJsonToArray(jsonString);
        if (rooms == null) return null;
        return convertToDungeon(rooms);
    }

    public Room[] roomsFromJson(String filename) {
        String jsonString = readFile(filename);
        return mapJsonToArray(jsonString);
    }

    /**
     * Reads the given file from the filesystem
     *
     * @param filename Path an filename
     * @return Content of the file or null if  failed
     */
    private String readFile(String filename) {
        String returnValue = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            returnValue = stringBuilder.toString();
        } catch (Exception e) {
            Gdx.app.log("Error", "Could not read Dungeon-File", e);
        }
        return returnValue;
    }

    /**
     * Uses Gson to map json to an array of rooms
     *
     * @param jsonString Json representing a dungeon
     * @return Array of rooms representing the dungeon or null if failed
     */
    private Room[] mapJsonToArray(String jsonString) {
        Room[] returnValue = null;
        try {
            Gson gson = new Gson();
            returnValue = gson.fromJson(jsonString, Room[].class);
        } catch (JsonSyntaxException jse) {
            Gdx.app.log("Error", "Could not convert json to object", jse);
        }
        return returnValue;
    }

    /**
     * Transforms the given rooms with der given local coordinates into the global coordinates of the dungeon
     *
     * @param rooms Rooms the dungeon consists of
     * @return Generated dungeon
     */
    private Dungeon convertToDungeon(Room[] rooms) {
        Coordinate globalOffset = getOffset(rooms);
        Coordinate dungeonSize = getDungeonSize(globalOffset, rooms);
        Dungeon dungeon = new Dungeon(dungeonSize.getX(), dungeonSize.getY());
        dungeon.setRooms(rooms);
        for (Room room : rooms) {
            drawRoomEdges(room, dungeon, globalOffset);
            fillRoom(room, dungeon, globalOffset);
        }
        return dungeon;
    }

    /**
     * Draws the outline edges of a room with floor tiles
     *
     * @param room         The room that should be drawn
     * @param dungeon      The dungeon in which the room should be drawn
     * @param globalOffset Offset of the whole dungeon
     */
    private void drawRoomEdges(Room room, Dungeon dungeon, Coordinate globalOffset) {
        Coordinate[] node = room.getShape();
        int offsetX = room.getPosition().getX() + globalOffset.getX();
        int offsetY = room.getPosition().getY() + globalOffset.getY();
        for (int i = 0; i < node.length; i++) {
            Coordinate edgeFrom = node[i];
            Coordinate edgeTo;
            if (node.length == i + 1) {
                edgeTo = node[0];
            } else {
                edgeTo = node[i + 1];
            }

            //increasing Y same X
            for (int j = edgeFrom.getY(); j < edgeTo.getY(); j++) {
                dungeon.tiles[edgeFrom.getX() + offsetX][j + offsetY] = Dungeon.Tile.WALL;
            }
            //increasing X same Y
            for (int j = edgeFrom.getX(); j < edgeTo.getX(); j++) {
                dungeon.tiles[j + offsetX][edgeFrom.getY() + offsetY] = Dungeon.Tile.WALL;
            }
            //decreasing Y same X
            for (int j = edgeFrom.getY(); j > edgeTo.getY(); j--) {
                dungeon.tiles[edgeFrom.getX() + offsetX][j + offsetY] = Dungeon.Tile.WALL;
            }
            //decreasing X same Y
            for (int j = edgeFrom.getX(); j > edgeTo.getX(); j--) {
                dungeon.tiles[j + offsetX][edgeFrom.getY() + offsetY] = Dungeon.Tile.WALL;
            }
        }
    }

    /**
     * Fills a room which walls where defined prior with floor tiles
     *
     * @param room         Room which should be filled
     * @param dungeon      Dungeon in which the room is
     * @param globalOffset Offset of the whole dungeon
     */
    private void fillRoom(Room room, Dungeon dungeon, Coordinate globalOffset) {
        boolean foundEmptySpace = true;
        int startX = room.getPosition().getX() + room.getShape()[0].getX() + globalOffset.getX() + 1;
        int startY = room.getPosition().getY() + room.getShape()[0].getY() + globalOffset.getY() + 1;
        while (dungeon.tiles[startX][startY] == Dungeon.Tile.EMPTY && foundEmptySpace) {
            int nextY = room.getPosition().getY() + room.getShape()[0].getY() + globalOffset.getY() + 1;
            foundEmptySpace = false;
            while (dungeon.tiles[startX][startY - 1] != Dungeon.Tile.WALL) {
                startY--;
            }
            while (dungeon.tiles[startX][startY] == Dungeon.Tile.EMPTY) {
                if (dungeon.tiles[startX + 1][startY] == Dungeon.Tile.EMPTY) {
                    if (!foundEmptySpace) nextY = startY;
                    foundEmptySpace = true;
                }
                dungeon.tiles[startX][startY] = Dungeon.Tile.FLOOR;
                startY++;
            }
            startX++;
            startY = nextY;
        }
    }

    /**
     * Get the position-offset of the generated dungeon to move it in the positive area of the grid.
     *
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
     * Calculates the extension of the dungeon in x and y direction.
     *
     * @param globalOffset offset to only use the positive area of the grid.
     * @param rooms        Dungeon as array of rooms
     * @return Size of the dungeon
     */
    private Coordinate getDungeonSize(Coordinate globalOffset, Room[] rooms) {
        Coordinate size = new Coordinate(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Room room : rooms) { // Replace with Room.getSize
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
