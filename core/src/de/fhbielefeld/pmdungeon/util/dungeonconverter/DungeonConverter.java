package de.fhbielefeld.pmdungeon.util.dungeonconverter;

import com.google.gson.Gson;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

import java.io.BufferedReader;
import java.io.FileReader;

// helper class to convert https://github.com/OndrejNepozitek/Edgar-DotNet generated dungeons to use in game
public class DungeonConverter {

    public Dungeon dungeonFromJson(String filename) {
        String jsonString = readFile(filename);
        Room[] rooms = mapJsonToArray(jsonString);

        return null;
    }

    public Room[] roomsFromJson(String filename) {
        String jsonString = readFile(filename);
        return mapJsonToArray(jsonString);
    }

    private String readFile(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
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
            for (Coordinate shape : room.getShape()) {

            }
            //convert to 2D tile array
        }
        return null;
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
     * Calculates the extensions of the dungeon in every direction.
     * @param globalOffset offset to only use the positive area of the grid.
     * @param rooms Dungeon as array of rooms
     * @return Size of the dungeon
     */
    private Coordinate getDungeonSize(Coordinate globalOffset, Room[] rooms) {
        Coordinate size = new Coordinate(0,0);
        for (Room room : rooms) {
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (Coordinate shape : room.getShape()) {
                if (maxX < shape.getX()) maxX = shape.getX();
                if (maxY < shape.getY()) maxY = shape.getY();
            }
            int roomX = room.getPosition().getX() + globalOffset.getX() + maxX;
            int roomY = room.getPosition().getY() + globalOffset.getY() + maxY;
            if (size.getX() < roomX) size.setX(roomX);
            if (size.getY() < roomY) size.setY(roomY);
        }
        return size;
    }
}
