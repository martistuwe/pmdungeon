package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public abstract class Item implements Disposable {

    private static final float ITEM_SIZE_SCALE = 0.04f;

    protected Texture texture;

    public abstract void use(Character character);

    public void renderAtCharacter(float x, float y, SpriteBatch batch) {
        Sprite sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth() * ITEM_SIZE_SCALE, texture.getHeight() * ITEM_SIZE_SCALE);
        sprite.setPosition(x, y);
        sprite.draw(batch);
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
