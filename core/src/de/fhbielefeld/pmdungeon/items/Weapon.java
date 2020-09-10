package de.fhbielefeld.pmdungeon.items;

public abstract class Weapon extends Item {

    private final int damage;

    protected Weapon(int damage) {
        this.damage = damage;
    }
}
