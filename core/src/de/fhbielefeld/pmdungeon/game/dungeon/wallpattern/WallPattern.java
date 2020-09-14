package de.fhbielefeld.pmdungeon.game.dungeon.wallpattern;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import de.fhbielefeld.pmdungeon.game.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.game.dungeon.Textures;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class WallPattern {

    protected static final Dungeon.Tile W = Dungeon.Tile.WALL;
    protected static final Dungeon.Tile D = Dungeon.Tile.DOOR;
    protected static final Dungeon.Tile A = null; //Anything

    protected static final int WIDTH = 3;
    protected static final int HEIGHT = 3;

    protected List<Dungeon.Tile[][]> patternList;

    ObjectMap<Textures, Texture> textureMap;

    protected WallPattern(ObjectMap<Textures, Texture> textureMap) {
        patternList = new ArrayList<>();
        this.textureMap = textureMap;
    }

    public boolean matches(List<Dungeon.Tile[][]> externalPatternList) {
        for (Dungeon.Tile[][] internalPattern : patternList) {
            for (Dungeon.Tile[][] externalPattern : externalPatternList) {
                if (patternMatches(internalPattern, externalPattern)) return true;
            }
        }
        return false;
    }

    private boolean patternMatches(Dungeon.Tile[][] internalPattern, Dungeon.Tile[][] externalPattern) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (externalPattern[x][y] != A && internalPattern[x][y] != externalPattern[x][y]) return false;
            }
        }
        return true;
    }

    public List<Dungeon.Tile[][]> getPatternList() {
        return patternList;
    }

    public abstract void render(SpriteBatch batch, Coordinate position);
}
