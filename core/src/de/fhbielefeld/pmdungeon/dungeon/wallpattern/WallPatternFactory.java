package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public class WallPatternFactory {

    public WallPattern getWallPattern(Dungeon dungeon, Coordinate center) {
        DungeonCutout dungeonCutout = new DungeonCutout();
        dungeonCutout.fromDungeonTiles(dungeon, center);

        WallPattern horizontal = new HorizontalWall();

        if (dungeonCutout.equals(horizontal.getPattern())) {
            return horizontal;
        }
        return null;
    }
}
