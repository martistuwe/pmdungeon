package de.fhbielefeld.pmdungeon.game.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.game.dungeon.Textures;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Base representation of wall segments for pattern matching
 */
public abstract class WallPattern {

    protected static final Tile.Type W = Tile.Type.WALL;
    protected static final Tile.Type D = Tile.Type.DOOR;
    protected static final Tile.Type A = null; //Anything

    protected static final int WIDTH = 3;
    protected static final int HEIGHT = 3;

    protected List<Tile.Type[][]> patternList;

    ObjectMap<Textures, Texture> textureMap;

    protected WallPattern(ObjectMap<Textures, Texture> textureMap) {
        patternList = new ArrayList<>();
        this.textureMap = textureMap;
    }

    /**
     * Checks if any of the given pattern matches
     *
     * @param externalPatternList List of patterns for a wall segment
     * @return true if any pattern matches; false if it doesn't
     */
    public boolean matches(List<Tile.Type[][]> externalPatternList) {
        for (Tile.Type[][] internalPattern : patternList) {
            for (Tile.Type[][] externalPattern : externalPatternList) {
                if (patternMatches(internalPattern, externalPattern)) return true;
            }
        }
        return false;
    }

    /**
     * Checks if a single pattern matches another
     *
     * @param internalPattern A pattern
     * @param externalPattern Another pattern
     * @return true if the patterns match
     */
    private boolean patternMatches(Tile.Type[][] internalPattern, Tile.Type[][] externalPattern) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (externalPattern[x][y] != A && internalPattern[x][y] != externalPattern[x][y]) return false;
            }
        }
        return true;
    }

    public List<Tile.Type[][]> getPatternList() {
        return patternList;
    }

    /**
     * Defines how walls should be rendered
     *
     * @param batch    SpriteBatch used to render on the screen
     * @param position Position where the wall segment is rendered
     */
    public abstract void render(SpriteBatch batch, Coordinate position);
}
