package de.fhbielefeld.pmdungeon.items;

public abstract class Weapon extends Item {

    private final float damage;
    private final float range;

    protected Weapon(float damage, float range) {
        this.damage = damage;
        this.range = range;
    }
}
