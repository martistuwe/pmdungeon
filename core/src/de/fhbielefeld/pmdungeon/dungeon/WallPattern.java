package de.fhbielefeld.pmdungeon.dungeon;

import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class WallPattern {

    private static final Dungeon.Tile W = Dungeon.Tile.WALL;
    private static final Dungeon.Tile F = Dungeon.Tile.FLOOR;
    private static final Dungeon.Tile E = Dungeon.Tile.EMPTY;
    private static final Dungeon.Tile N = null;

    public static final Dungeon.Tile[][] horizontal = {
            {N, N, N},
            {W, W, W},
            {N, N, N}
    };
    public static final Dungeon.Tile[][] vertical = {
            {N, W, N},
            {N, W, N},
            {N, W, N}
    };
    public static final Dungeon.Tile[][] verticalWithFloorRight = {
            {N, W, N},
            {N, W, F},
            {N, W, N}
    };
    public static final Dungeon.Tile[][] cornerLeftTop = {
            {N, N, N},
            {N, W, W},
            {N, W, N}
    };
    public static final Dungeon.Tile[][] cornerRightTop = {
            {N, N, N},
            {W, W, N},
            {N, W, N}
    };
    public static final Dungeon.Tile[][] cornerLeftBottom = {
            {N, W, N},
            {N, W, W},
            {N, N, N}
    };
    public static final Dungeon.Tile[][] cornerRightBottom = {
            {N, W, N},
            {W, W, N},
            {N, N, N}
    };

    private Dungeon.Tile[][] pattern;
    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;

    public WallPattern() {
        this.pattern = new Dungeon.Tile[WIDTH][HEIGHT];
    }

    public void fromDungeonTiles(Dungeon dungeon, Coordinate center) {
        // inverted arraycopy to account for internal mirrored representation
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (center.getX() - 1 + x < 0 || center.getX() - 1 + x >= dungeon.getWidth() ||
                        center.getY() - 1 + y < 0 || center.getY() - 1 + y >= dungeon.getHeight()) {
                    this.pattern[2 - y][x] = N;
                } else {
                    this.pattern[2 - y][x] = dungeon.tiles[center.getX() - 1 + x][center.getY() - 1 + y];
                }
            }
        }
    }

    public boolean equals(Dungeon.Tile[][] pattern) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (pattern[x][y] != N && this.pattern[x][y] != pattern[x][y]) return false;
            }
        }
        return true;
    }
}
