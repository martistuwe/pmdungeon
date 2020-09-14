package de.fhbielefeld.pmdungeon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public abstract class Item implements Disposable {

    protected Texture texture;

    public abstract void use(Character character);

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
