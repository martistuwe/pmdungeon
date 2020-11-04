package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import de.fhbielefeld.pmdungeon.game.characters.Character;

/**
 * Item sub-class. Just for weapon.
 */
public abstract class Weapon extends Item {

    private static final float ROTATION_SPEED = 1000;
    private long lastUsage;
    private float rotation = 0;

    protected Weapon(Texture texture) {
        super(texture);
        this.lastUsage = TimeUtils.millis();
    }

    public abstract float getRange();

    protected abstract float getDamage();

    protected abstract long getCoolDown();

    /**
     * Overrides the default because weapons get rotated for animation.
     *
     * @param sprite    Sprite the item texture should be added to
     * @param character Owner of the item
     */
    @Override
    protected void prepareSprite(Sprite sprite, Character character) {
        super.prepareSprite(sprite, character);
        if (this.animationState == State.IN_USE) {
            calculateRotation();
            if (character.isFacingLeft()) {
                sprite.rotate(rotation);
            } else {
                sprite.rotate(-rotation);
            }
        }
    }

    /**
     * Calculates the current degree of rotation based on time.
     */
    private void calculateRotation() {
        if (TimeUtils.timeSinceMillis(lastUsage) <= getCoolDown() / 2) {
            rotation += ROTATION_SPEED * Gdx.graphics.getDeltaTime();
        } else if (TimeUtils.timeSinceMillis(lastUsage) > getCoolDown() / 2) {
            rotation -= ROTATION_SPEED * Gdx.graphics.getDeltaTime();
        }
        if (rotation <= 0) {
            this.animationState = State.IDLE;
            rotation = 0;
        }
    }

    /**
     * Concrete implementation of use methode. Checks if the weapon has cooldown, triggers its animation and attacks the nearest player, if there is one.
     *
     * @param character Character on which the item is used.
     */
    @Override
    public void use(Character character) {
        if (TimeUtils.timeSinceMillis(lastUsage) >= getCoolDown()) {
            this.animationState = State.IN_USE;
            lastUsage = TimeUtils.millis();
            Character nearestCharacter = character.nearestCharacter();
            if (nearestCharacter != null && character.distanceBetween(nearestCharacter) <= getRange()) {
                character.attack(nearestCharacter, getDamage());
                nearestCharacter.punchBack(character);
            }
        }
    }
}
