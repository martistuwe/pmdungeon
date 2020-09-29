package de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import de.fhbielefeld.pmdungeon.game.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;

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
        Coordinate dungeonSize = getDungeonDimensions(globalOffset, rooms);
        Dungeon dungeon = new Dungeon(dungeonSize.getX() + 1, dungeonSize.getY() + 1);
        dungeon.setRooms(rooms);
        for (Room room : rooms) {
            room.move(globalOffset);
            drawRoomEdges(room, dungeon);
            fillRoom(room, dungeon);
            drawDoors(room, dungeon);
        }
        dungeon.setupNextLevelTriggerLocation();
        return dungeon;
    }

    /**
     * Draws the outline edges of a room with floor tiles
     *
     * @param room    The room that should be drawn
     * @param dungeon The dungeon in which the room should be drawn
     */
    private void drawRoomEdges(Room room, Dungeon dungeon) {
        Coordinate[] node = room.getShape();
        for (int i = 0; i < node.length; i++) {
            Coordinate edgeFrom = node[i];
            Coordinate edgeTo;
            if (node.length == i + 1) {
                edgeTo = node[0];
            } else {
                edgeTo = node[i + 1];
            }
            drawTiles(edgeFrom, edgeTo, room.getPosition(), dungeon, Tile.Type.WALL);
        }
    }

    /**
     * Draws the doors into the dungeon
     *
     * @param room    Room of which the doors should be drawn
     * @param dungeon Dungeon to draw the doors into
     */
    private void drawDoors(Room room, Dungeon dungeon) {
        if (room.getDoors() != null) {
            for (Door door : room.getDoors()) {
                drawTiles(door.getFrom(), door.getTo(), new Coordinate(0, 0), dungeon, Tile.Type.DOOR);
            }
        }
    }

    /**
     * Draws a line of tiles between the two given coordinates
     *
     * @param from    Line start
     * @param to      Line end
     * @param offset  Offset of the line in the dungeon
     * @param dungeon Dungeon in which the line should be drawn
     * @param tile    Kind of tile to draw
     */
    private void drawTiles(Coordinate from, Coordinate to, Coordinate offset, Dungeon dungeon, Tile.Type tile) {
        //increasing Y same X
        for (int j = from.getY(); j <= to.getY(); j++) {
            dungeon.setTileAt(from.getX() + offset.getX(), j + offset.getY(), tile);
        }
        //increasing X same Y
        for (int j = from.getX(); j <= to.getX(); j++) {
            dungeon.setTileAt(j + offset.getX(), from.getY() + offset.getY(), tile);
        }
        //decreasing Y same X
        for (int j = from.getY(); j >= to.getY(); j--) {
            dungeon.setTileAt(from.getX() + offset.getX(), j + offset.getY(), tile);
        }
        //decreasing X same Y
        for (int j = from.getX(); j >= to.getX(); j--) {
            dungeon.setTileAt(j + offset.getX(), from.getY() + offset.getY(), tile);
        }
    }

    /**
     * Fills a room with floor tiles which walls where defined prior
     *
     * @param room    Room which should be filled
     * @param dungeon Dungeon in which the room is
     */
    private void fillRoom(Room room, Dungeon dungeon) {
        boolean foundEmptySpace = true;
        int startX = room.getPosition().getX() + room.getShape()[0].getX() + 1;
        int startY = room.getPosition().getY() + room.getShape()[0].getY() + 1;
        while (dungeon.getTileTypeAt(startX, startY) == Tile.Type.EMPTY && foundEmptySpace) {
            int nextY = room.getPosition().getY() + room.getShape()[0].getY() + 1;
            foundEmptySpace = false;
            while (dungeon.getTileTypeAt(startX, startY - 1) != Tile.Type.WALL) {
                startY--;
            }
            while (dungeon.getTileTypeAt(startX, startY) == Tile.Type.EMPTY) {
                if (dungeon.getTileTypeAt(startX + 1, startY) == Tile.Type.EMPTY) {
                    if (!foundEmptySpace) nextY = startY;
                    foundEmptySpace = true;
                }
                dungeon.setTileAt(startX, startY, Tile.Type.FLOOR);
                startY++;
            }
            startX++;
            startY = nextY;
        }
    }

    /**
     * Get the position-offset of the generated dungeon to move it into the positive area of the grid.
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
     * Calculates the dimensions of the dungeon in x and y direction.
     *
     * @param globalOffset offset to only use the positive area of the grid.
     * @param rooms        Dungeon as array of rooms
     * @return Size of the dungeon
     */
    private Coordinate getDungeonDimensions(Coordinate globalOffset, Room[] rooms) {
        Coordinate size = new Coordinate(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Room room : rooms) {
            Coordinate roomExtensions = room.getExtension();
            int roomX = room.getPosition().getX() + globalOffset.getX() + roomExtensions.getX();
            int roomY = room.getPosition().getY() + globalOffset.getY() + roomExtensions.getY();
            if (size.getX() <= roomX) size.setX(roomX + 1);
            if (size.getY() <= roomY) size.setY(roomY + 1);
        }
        return size;
    }
}
