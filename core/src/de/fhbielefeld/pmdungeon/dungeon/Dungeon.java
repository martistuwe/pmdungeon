package de.fhbielefeld.pmdungeon.dungeon;

public class Dungeon {

    public static int HEIGHT = 16;
    public static int WIDTH = 16;

    public Tile[][] tiles;

    enum Tile {
        GROUND,
        WALL,
        DOOR,
        EMPTY,
    }

    public Dungeon() {
        tiles = new Tile[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                tiles[i][j] = Tile.GROUND;
            }
        }
    }
}
