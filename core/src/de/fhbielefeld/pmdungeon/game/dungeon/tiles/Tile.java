package de.fhbielefeld.pmdungeon.game.dungeon.tiles;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public class Tile {

    private final Type type;
    private final int x;
    private final int y;
    private int index;
    private final Array<Connection<Tile>> connections = new Array<>();

    public Tile(Type type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public boolean isAccessible() {
        switch (type) {
            case FLOOR:
            case DOOR:
                return true;
            case WALL:
            case EMPTY:
                return false;
        }
        return false;
    }

    public void addConnection(Tile to) {
        connections.add(new TileConnection(this, to));
    }

    public Array<Connection<Tile>> getConnections() {
        return connections;
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public enum Type {
        FLOOR,
        WALL,
        DOOR,
        EMPTY,
    }
}
