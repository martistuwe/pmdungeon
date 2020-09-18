package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Animation implements Disposable {

    private final Array<Texture> textures;
    private int currentFrameIndex = 0;
    private float maxFrameTime = 0;
    private float currentFrameTime = 0;

    public Animation(float maxFrameTime) {
        textures = new Array<>();
        this.maxFrameTime = maxFrameTime;
    }

    public void addTexture(Texture texture) {
        textures.add(texture);
    }

    public Texture getCurrentTexture() {
        currentFrameTime += Gdx.graphics.getDeltaTime();
        if (currentFrameTime >= maxFrameTime) {
            currentFrameIndex++;
            currentFrameTime = 0;
            if (currentFrameIndex >= textures.size) {
                currentFrameIndex = 0;
            }
        }
        return textures.get(currentFrameIndex);
    }

    @Override
    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
    }
}
