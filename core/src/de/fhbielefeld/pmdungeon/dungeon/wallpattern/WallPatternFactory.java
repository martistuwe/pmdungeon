package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class WallPatternFactory {

    private final WallPattern horizontal = new HorizontalWall();

    public WallPattern getWallPattern(Dungeon dungeon, Coordinate center) {
        DungeonCutout dungeonCutout = new DungeonCutout();
        dungeonCutout.fromDungeonTiles(dungeon, center);

        if (dungeonCutout.equals(horizontal.getPattern())) {
            return horizontal;
        }
        return null;
    }
}
