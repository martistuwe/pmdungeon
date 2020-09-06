package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

public abstract class NonPlayerCharacter extends Character {

    protected NonPlayerCharacter(SpriteBatch batch, Dungeon dungeon) {
        super(batch, dungeon);
    }
}
