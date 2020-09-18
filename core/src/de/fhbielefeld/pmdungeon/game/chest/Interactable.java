package de.fhbielefeld.pmdungeon.game.chest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public interface Interactable {
    void update();

    void render(SpriteBatch batch);

    void interact(Character character);
}
