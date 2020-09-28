package de.fhbielefeld.pmdungeon.game.dungeon.tiles;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

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

    public Direction[] directionTo(Tile that) {
        List<Direction> directions = new ArrayList<>();
        if (this.x < that.x) {
            directions.add(Direction.E);
        } else if (this.x > that.x) {
            directions.add(Direction.W);
        }

        if (this.y < that.y) {
            directions.add(Direction.N);
        } else if (this.y > that.y) {
            directions.add(Direction.S);
        }
        return directions.toArray(new Direction[0]);
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

    public enum Direction {
        N,
        E,
        S,
        W,
    }
}
