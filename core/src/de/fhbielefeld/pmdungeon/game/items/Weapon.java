package de.fhbielefeld.pmdungeon.game.items;

import de.fhbielefeld.pmdungeon.game.characters.Character;

public abstract class Weapon extends Item {

    private final float damage;
    private final float range;

    protected Weapon(float damage, float range) {
        this.damage = damage;
        this.range = range;
    }

    @Override
    public void use(Character character) {
        //add Animation
        Character nearestCharacter = character.nearestCharacter(null);
        if (character.distanceBetween(nearestCharacter) <= this.range) {
            character.attack(character, damage);
        }
    }
}
