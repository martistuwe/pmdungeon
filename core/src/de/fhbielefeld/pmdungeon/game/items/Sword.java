package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;

/**
 * Sword is an item of type weapon
 */
public class Sword extends Weapon {

    private static final float DAMAGE = 1.5f;
    private static final float RANGE = 1.5f;
    private static final long COOL_DOWN = 250;

    public Sword() {
        super(new Texture("textures/items/weapon_regular_sword.png"));
    }

    @Override
    public float getRange() {
        return RANGE;
    }

    @Override
    protected float getDamage() {
        return DAMAGE;
    }

    @Override
    protected long getCoolDown() {
        return COOL_DOWN;
    }
}
