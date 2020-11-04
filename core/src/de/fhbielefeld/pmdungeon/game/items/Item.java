package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.Character;

/**
 * Base interface of items.
 */
public abstract class Item implements Disposable {

    protected static final float ITEM_SIZE_SCALE = 0.04f;
    protected static final float CHARACTER_CENTER_AXIS_OFFSET = -0.06f;
    protected static final float ITEM_SIDE_OFFSET = 0.5f;

    private final Texture texture;

    protected State animationState = State.IDLE;

    protected Item(Texture texture) {
        this.texture = texture;
    }

    /**
     * Methode is called when the item gets used.
     *
     * @param character Character on which the item is used.
     */
    public abstract void use(Character character);

    /**
     * Default methode for preparing sprites of item textures. Sets their position, size and point of origin.
     *
     * @param sprite    Sprite the item texture should be added to
     * @param character Owner of the item
     */
    protected void prepareSprite(Sprite sprite, Character character) {
        float positionX = calculateXPosition(character);
        float positionY = character.getPositionY();

        sprite.setSize(texture.getWidth() * ITEM_SIZE_SCALE, texture.getHeight() * ITEM_SIZE_SCALE);
        sprite.setOrigin((texture.getWidth() * ITEM_SIZE_SCALE) / 2, -0.1f);
        sprite.setPosition(positionX, positionY);
    }

    /**
     * Calculates the position of the texture of an item relative to its owner and orientation.
     *
     * @param character Owner of the item
     * @return Position on X axis
     */
    private float calculateXPosition(Character character) {
        float x = character.getPositionX() - (character.getCharacterWidth() / 2) + CHARACTER_CENTER_AXIS_OFFSET;
        if (character.isFacingLeft()) {
            x -= ITEM_SIDE_OFFSET;
        } else {
            x += ITEM_SIDE_OFFSET;
        }
        return x;
    }

    /**
     * Rendering an item at the character who's holding it
     *
     * @param character Character who is holding the item
     * @param batch     SpriteBatch used to render on the screen
     */
    public void renderAtCharacter(Character character, SpriteBatch batch) {
        if (texture != null) {
            Sprite sprite = new Sprite(texture);
            prepareSprite(sprite, character);
            sprite.draw(batch);
        }
    }

    protected enum State {
        IDLE,
        IN_USE
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
