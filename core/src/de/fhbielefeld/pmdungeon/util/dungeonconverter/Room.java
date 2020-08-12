package de.fhbielefeld.pmdungeon.util.dungeonconverter;

import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("Node")
    private int node;
    @SerializedName("Shape")
    private Coordinate[] shape;
    @SerializedName("Position")
    private Coordinate position;
    @SerializedName("IsCorridor")
    private boolean isCorridor;
    @SerializedName("Doors")
    private Door[] doors;

    public Room(int node, Coordinate[] shape, Coordinate position, boolean isCorridor, Door[] doors) {
        this.node = node;
        this.shape = shape;
        this.position = position;
        this.isCorridor = isCorridor;
        this.doors = doors;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public Coordinate[] getShape() {
        return shape;
    }

    public void setShape(Coordinate[] shape) {
        this.shape = shape;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public boolean isCorridor() {
        return isCorridor;
    }

    public void setCorridor(boolean corridor) {
        isCorridor = corridor;
    }

    public Door[] getDoors() {
        return doors;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }
}
