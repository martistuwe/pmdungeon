package de.fhbielefeld.pmdungeon.items;

import com.badlogic.gdx.graphics.Texture;

public class Sword extends Weapon {

    private static final int DAMAGE = 3;

    public Sword() {
        super(DAMAGE);
        this.texture = new Texture("textures/items/weapon_regular_sword.png");
    }

    @Override
    public void use() {
        //TODO implement
    }
}
