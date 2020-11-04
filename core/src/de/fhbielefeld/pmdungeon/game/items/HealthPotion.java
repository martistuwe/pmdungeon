package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.fhbielefeld.pmdungeon.game.characters.Character;

/**
 * Healthpotion to regenerate health points
 */
public class HealthPotion extends Item {

    private static final float HEALTH_POINTS = 2;
    private static final float POTION_RENDER_OFFSET = -0.1f;

    public HealthPotion() {
        super(new Texture("textures/items/flask_big_green.png"));
    }

    @Override
    protected void prepareSprite(Sprite sprite, Character character) {
        super.prepareSprite(sprite, character);
        sprite.setPosition(sprite.getX() + POTION_RENDER_OFFSET, sprite.getY());
    }

    @Override
    public void use(Character character) {
        character.heal(HEALTH_POINTS);
        character.getInventory().removeItem(this);
    }
}
