package de.fhbielefeld.pmdungeon.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public abstract class Item implements Disposable {

    protected Texture texture;

    public abstract void use();

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
