package de.fhbielefeld.pmdungeon.dungeon;

import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class WallPattern {

    private static final Dungeon.Tile W = Dungeon.Tile.WALL;
    private static final Dungeon.Tile F = Dungeon.Tile.FLOOR;
    private static final Dungeon.Tile E = Dungeon.Tile.EMPTY;
    private static final Dungeon.Tile N = null;

    private static final Dungeon.Tile[][] horizontal = {
            {N, N, N},
            {W, W, W},
            {N, N, N}
    };
    private static final Dungeon.Tile[][] vertical = {
            {N, W, N},
            {N, W, N},
            {N, W, N}
    };
    private static final Dungeon.Tile[][] cornerLeftTop = {
            {N, N, N},
            {N, W, W},
            {N, W, N}
    };
    private static final Dungeon.Tile[][] cornerRightTop = {
            {N, N, N},
            {W, W, N},
            {N, W, N}
    };
    private static final Dungeon.Tile[][] cornerLeftBottom = {
            {N, W, N},
            {N, W, W},
            {N, N, N}
    };
    private static final Dungeon.Tile[][] cornerRightBottom = {
            {N, W, N},
            {W, W, N},
            {N, N, N}
    };

}
