package de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a door in the dungeon
 */
public class Door {

    @SerializedName("Node")
    private int node;
    @SerializedName("From")
    private Coordinate from;
    @SerializedName("To")
    private Coordinate to;

    public Door(int node, Coordinate from, Coordinate to) {
        this.node = node;
        this.from = from;
        this.to = to;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public Coordinate getFrom() {
        return from;
    }

    public void setFrom(Coordinate from) {
        this.from = from;
    }

    public Coordinate getTo() {
        return to;
    }

    public void setTo(Coordinate to) {
        this.to = to;
    }
}
