package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Sword extends Weapon {

    private static final float DAMAGE = 3;
    private static final float RANGE = 1.5f;
    private static final long COOL_DOWN = 1000;

    public Sword() {
        super(DAMAGE, RANGE, COOL_DOWN);
        this.texture = new Texture("textures/items/weapon_regular_sword.png");
    }
}
