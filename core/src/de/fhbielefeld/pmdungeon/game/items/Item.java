package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public abstract class Item implements Disposable {

    protected static final float ITEM_SIZE_SCALE = 0.04f;
    protected static final float CHARACTER_CENTER_AXIS_OFFSET = -0.06f;
    protected static final float ITEM_SIDE_OFFSET = 0.5f;

    private final Texture texture;

    protected State animationState = State.IDLE;

    protected Item(Texture texture) {
        this.texture = texture;
    }

    public abstract void use(Character character);

    protected void prepareSprite(Sprite sprite, Character character) {
        float positionX = calculateXPosition(character);
        float positionY = character.getPositionY();

        sprite.setSize(texture.getWidth() * ITEM_SIZE_SCALE, texture.getHeight() * ITEM_SIZE_SCALE);
        sprite.setOrigin((texture.getWidth() * ITEM_SIZE_SCALE) / 2, -0.1f);
        sprite.setPosition(positionX, positionY);
    }

    private float calculateXPosition(Character character) {
        float x = character.getPositionX() - (Character.CHARACTER_WIDTH / 2) + CHARACTER_CENTER_AXIS_OFFSET;
        if (character.isFacingLeft()) {
            x -= ITEM_SIDE_OFFSET;
        } else {
            x += ITEM_SIDE_OFFSET;
        }
        return x;
    }

    public void renderAtCharacter(Character character, SpriteBatch batch) {
        Sprite sprite = new Sprite(texture);
        prepareSprite(sprite, character);
        sprite.draw(batch);
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
