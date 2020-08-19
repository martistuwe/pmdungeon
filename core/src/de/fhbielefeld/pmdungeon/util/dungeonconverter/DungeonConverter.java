package de.fhbielefeld.pmdungeon.util.dungeonconverter;

import com.badlogic.gdx.utils.Array;
import com.google.gson.Gson;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

import java.io.BufferedReader;
import java.io.FileReader;

// helper class to convert https://github.com/OndrejNepozitek/Edgar-DotNet generated dungeons to use in game
public class DungeonConverter {

    public Dungeon dungeonFromJson(String filename) {
        String jsonString = readFile(filename);
        Room[] rooms = mapToArray(jsonString);

        return null;
    }

    public Room[] roomsFromJson(String filename) {
        String jsonString = readFile(filename);
        return mapToArray(jsonString);
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

    private Room[] mapToArray(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Room[].class);
    }

    private Dungeon convertToDungeon(Room[] rooms) {
        for (Room room : rooms) {

        }
        return null;
    }

    private void getDungeonSize(Room[] rooms) {
        int dungeonWidth = 0, dungeonHeight = 0;
        for (Room room : rooms) {
            int maxWidth = Integer.MIN_VALUE, minWidth = Integer.MAX_VALUE;
            int maxHeight = Integer.MIN_VALUE, minHeight = Integer.MAX_VALUE;
            for (Coordinate coordinate : room.getShape()) {
                if (coordinate.getX() > maxWidth) {
                    maxWidth = coordinate.getX();
                }
                if (coordinate.getX() < minWidth) {
                    minWidth = coordinate.getX();
                }
                if (coordinate.getY() > maxHeight) {
                    maxHeight = coordinate.getY();
                }
                if (coordinate.getY() < minHeight) {
                    minHeight = coordinate.getY();
                }
            }
            dungeonWidth += (maxWidth - minHeight);
            dungeonHeight += (maxHeight - minHeight);
        }
    }
}
