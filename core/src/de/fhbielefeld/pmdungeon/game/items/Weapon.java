package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public abstract class Weapon extends Item {

    private final float damage;
    private final float range;
    private final long coolDown;
    private final long punchBackDuration;
    private static final float ROTATION_SPEED = 1000;
    private long lastUsage;
    private float rotation = 0;

    protected Weapon(Texture texture, float damage, float range, long coolDown, long punchBackDuration) {
        super(texture);
        this.damage = damage;
        this.range = range;
        this.coolDown = coolDown;
        this.punchBackDuration = punchBackDuration;
        this.lastUsage = TimeUtils.millis();
    }

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

    private void calculateRotation() {
        if (TimeUtils.timeSinceMillis(lastUsage) <= coolDown / 2) {
            rotation += ROTATION_SPEED * Gdx.graphics.getDeltaTime();
        } else if (TimeUtils.timeSinceMillis(lastUsage) > coolDown / 2) {
            rotation -= ROTATION_SPEED * Gdx.graphics.getDeltaTime();
        }
        if (rotation <= 0) {
            this.animationState = State.IDLE;
            rotation = 0;
        }
    }

    @Override
    public void use(Character character) {
        if (TimeUtils.timeSinceMillis(lastUsage) >= coolDown) {
            this.animationState = State.IN_USE;
            lastUsage = TimeUtils.millis();
            Character nearestCharacter = character.nearestCharacter();
            if (nearestCharacter != null && character.distanceBetween(nearestCharacter) <= this.range) {
                character.attack(nearestCharacter, damage);
                nearestCharacter.punchBack(character, punchBackDuration);
            }
        }
    }
}
