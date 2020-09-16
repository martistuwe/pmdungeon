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
    private static final float ROTATION_SPEED = 400;
    private long lastUsage;
    private float rotation = 0;

    protected Weapon(Texture texture, float damage, float range, long coolDown) {
        super(texture);
        this.damage = damage;
        this.range = range;
        this.coolDown = coolDown;
        this.lastUsage = TimeUtils.millis();
    }

    @Override
    public Sprite prepareSprite(boolean flipRotation) {
        Sprite sprite = super.prepareSprite(flipRotation);
        if (this.animationState == State.IN_USE) {
            calculateRotation();
            if (flipRotation) {
                sprite.rotate(-rotation);
            } else {
                sprite.rotate(rotation);
            }
        }
        return sprite;
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
            if (character.distanceBetween(nearestCharacter) <= this.range) {
                character.attack(nearestCharacter, damage);
            }
        }
    }
}
