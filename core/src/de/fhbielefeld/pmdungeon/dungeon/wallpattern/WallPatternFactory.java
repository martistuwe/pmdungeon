package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class WallPatternFactory {

    private List<WallPattern> wallPatternList = new ArrayList<>();

    public WallPatternFactory() {
        wallPatternList.add(new HorizontalWall());
    }

    public WallPattern getWallPattern(Dungeon dungeon, Coordinate center) {
        DungeonCutout dungeonCutout = new DungeonCutout();
        dungeonCutout.fromDungeonTiles(dungeon, center);

        for (WallPattern wallpattern : wallPatternList) {
            if (dungeonCutout.equals(wallpattern.getPattern())) {
                return wallpattern;
            }
        }
        return null;
    }
}
