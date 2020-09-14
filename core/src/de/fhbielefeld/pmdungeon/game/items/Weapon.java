package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.utils.TimeUtils;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public abstract class Weapon extends Item {

    private final float damage;
    private final float range;
    private final long coolDown;
    private long lastUsage = 0;

    protected Weapon(float damage, float range, long coolDown) {
        this.damage = damage;
        this.range = range;
        this.coolDown = coolDown;
        this.lastUsage = TimeUtils.millis();
    }

    @Override
    public void use(Character character) {
        if (TimeUtils.timeSinceMillis(lastUsage) >= coolDown) {
            lastUsage = TimeUtils.millis();
            //TODO add Animation
            Character nearestCharacter = character.nearestCharacter();
            if (character.distanceBetween(nearestCharacter) <= this.range) {
                character.attack(nearestCharacter, damage);
            }
        }
    }
}
