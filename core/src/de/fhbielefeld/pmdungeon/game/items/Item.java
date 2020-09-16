package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public abstract class Item implements Disposable {

    private final Texture texture;

    private static final float ITEM_SIZE_SCALE = 0.04f;
    protected State animationState = State.IDLE;

    protected Item(Texture texture) {
        this.texture = texture;
    }

    public abstract void use(Character character);

    protected abstract void alterSprite(Sprite sprite, float x, float y, boolean facingLeft);

    public void renderAtCharacter(float x, float y, boolean facingLeft, SpriteBatch batch) {
        Sprite sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth() * ITEM_SIZE_SCALE, texture.getHeight() * ITEM_SIZE_SCALE);
        sprite.setOrigin((texture.getWidth() * ITEM_SIZE_SCALE) / 2, -0.1f);
        sprite.setPosition(x, y);

        alterSprite(sprite, x, y, facingLeft);
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
