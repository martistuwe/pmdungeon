package de.fhbielefeld.pmdungeon.dungeon.wallpattern;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public abstract class WallPattern {

    protected static final Dungeon.Tile W = Dungeon.Tile.WALL;
    protected static final Dungeon.Tile A = null; //Anything

    protected static final int WIDTH = 3;
    protected static final int HEIGHT = 3;

    protected Dungeon.Tile[][] pattern;

    abstract void render(SpriteBatch batch, Coordinate position);

    public boolean equals(Dungeon.Tile[][] pattern) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (pattern[x][y] != A && this.pattern[x][y] != pattern[x][y]) return false;
            }
        }
        return true;
    }

    public Dungeon.Tile[][] getPattern() {
        return pattern;
    }
}
