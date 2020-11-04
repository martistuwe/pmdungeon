package de.fhbielefeld.pmdungeon.game.interactable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.Character;

/**
 * Base interface of objects in the dungeon to interact with
 */
public interface Interactable extends Disposable {

    /**
     * Update interactable while gameloop
     */
    void update();

    /**
     * Render interactable
     *
     * @param batch SpriteBatch used to render on the screen
     */
    void render(SpriteBatch batch);

    /**
     * Triggered when a character interacts with the interactable
     *
     * @param character Character who interacted with the interactable
     */
    void interact(Character character);

    /**
     * Position of the interactable on the X axis
     *
     * @return X coordinate of the interactable
     */
    float getPositionX();

    /**
     * Position of the interactable on the Y axis
     *
     * @return Y coordinate of the interactable
     */
    float getPositionY();
}
