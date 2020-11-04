package de.fhbielefeld.pmdungeon.game.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.game.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.game.dungeon.Textures;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all patterns and controls which ones are rendered
 */
public class WallPatternFactory {

    private final List<WallPattern> wallPatternList = new ArrayList<>();

    public WallPatternFactory(ObjectMap<Textures, Texture> textureMap) {
        wallPatternList.add(new TCornerNorthWall(textureMap));
        wallPatternList.add(new TCornerEastWall(textureMap));
        wallPatternList.add(new TCornerSouthWall(textureMap));
        wallPatternList.add(new TCornerWestWall(textureMap));
        wallPatternList.add(new LowerLeftCornerWall(textureMap));
        wallPatternList.add(new UpperLeftCornerWall(textureMap));
        wallPatternList.add(new UpperRightCornerWall(textureMap));
        wallPatternList.add(new LowerRightCornerWall(textureMap));
        wallPatternList.add(new HorizontalWall(textureMap));
        wallPatternList.add(new VerticalWall(textureMap));
    }

    /**
     * Determines how wall segments are rendered based on their patterns
     *
     * @param dungeon Reference to the dungeon
     * @param center  Coordinates of the currently checked tile
     * @return The wall class pattern of the pattern that matched
     */
    public WallPattern getWallPattern(Dungeon dungeon, Coordinate center) {
        DungeonCutout dungeonCutout = new DungeonCutout();
        dungeonCutout.fromDungeonTiles(dungeon, center);

        for (WallPattern wallpattern : wallPatternList) {
            if (dungeonCutout.matches(wallpattern.getPatternList())) {
                return wallpattern;
            }
        }
        return null;
    }
}
