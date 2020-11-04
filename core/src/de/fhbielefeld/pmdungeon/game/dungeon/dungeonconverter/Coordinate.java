package de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter;

import com.google.gson.annotations.SerializedName;

/**
 * Holds a coordinate
 */
public class Coordinate {

    @SerializedName("X")
    private int x;
    @SerializedName("Y")
    private int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Coordinate coordinate) {
        if (coordinate != null) {
            this.x += coordinate.getX();
            this.y += coordinate.getY();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
