package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Sword extends Weapon {

    private static final float DAMAGE = 3;
    private static final float RANGE = 1.5f;

    public Sword() {
        super(DAMAGE, RANGE);
        this.texture = new Texture("textures/items/weapon_regular_sword.png");
    }
}
