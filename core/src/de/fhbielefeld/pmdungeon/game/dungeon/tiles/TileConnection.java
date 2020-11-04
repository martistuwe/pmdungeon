package de.fhbielefeld.pmdungeon.game.dungeon.tiles;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents a connection between two tile
 */
public class TileConnection implements Connection<Tile> {

    private final Tile from;
    private final Tile to;
    private final float cost;

    public TileConnection(Tile from, Tile to) {
        this.from = from;
        this.to = to;
        this.cost = Vector2.dst(from.getX(), from.getY(), to.getX(), to.getY());
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public Tile getFromNode() {
        return from;
    }

    @Override
    public Tile getToNode() {
        return to;
    }
}
