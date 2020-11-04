package de.fhbielefeld.pmdungeon.game.items;

/**
 * Textureless default weapon of NPCs.
 */
public class NpcAttack extends Weapon {

    private final float range;
    private final float damage;
    private final long coolDown;

    public NpcAttack(float range, float damage, long coolDown) {
        super(null);

        this.range = range;
        this.damage = damage;
        this.coolDown = coolDown;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    protected float getDamage() {
        return damage;
    }

    @Override
    protected long getCoolDown() {
        return coolDown;
    }
}
