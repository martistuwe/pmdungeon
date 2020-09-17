package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public class HealthPotion extends Item {

    private static final float HEALTH_POINTS = 2;

    public HealthPotion() {
        super(new Texture("textures/items/flask_big_green.png"));
    }

    @Override
    protected void alterSprite(Sprite sprite, float x, float y, boolean facingLeft) {
        sprite.setPosition(x - 0.1f, y);
    }

    @Override
    public void use(Character character) {
        character.heal(HEALTH_POINTS);
        character.getInventory().removeItem(this);
    }
}
