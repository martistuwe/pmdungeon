package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Houses an animation for a character
 */
public class Animation implements Disposable {

    private final Array<Texture> textures;
    private int currentFrameIndex = 0;
    private final float maxFrameTime;
    private float currentFrameTime = 0;

    public Animation(float maxFrameTime) {
        textures = new Array<>();
        this.maxFrameTime = maxFrameTime;
    }

    /**
     * Adds the given texture to the animation
     *
     * @param texture Texture that should be added to the animation
     */
    public void addTexture(Texture texture) {
        textures.add(texture);
    }

    /**
     * Returns the current texture of the animation based on time
     *
     * @return Current texture of the animation
     */
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

    /**
     * Frees system resources
     */
    @Override
    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
    }
}
