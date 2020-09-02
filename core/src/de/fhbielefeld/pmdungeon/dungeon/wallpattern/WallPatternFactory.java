package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.Textures;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class WallPatternFactory {

    private final List<WallPattern> wallPatternList = new ArrayList<>();

    public WallPatternFactory(ObjectMap<Textures, Texture> textureMap) {
        wallPatternList.add(new HorizontalWall(textureMap));
        wallPatternList.add(new VerticalWall(textureMap));
        wallPatternList.add(new LowerLeftCornerWall(textureMap));
        wallPatternList.add(new UpperLeftCornerWall(textureMap));
        wallPatternList.add(new UpperRightCornerWall(textureMap));
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
