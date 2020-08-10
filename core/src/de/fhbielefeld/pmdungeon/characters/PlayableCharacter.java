package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public abstract class PlayableCharacter {

    protected Animation idleAnimation;
    protected Animation runAnimation;

    protected boolean idle;

    protected float offsetFromStartX = 0;
    protected float offsetFromStartY = 0;

    public void handleInput(Input input) {
        idle = true;
        if (input.isKeyPressed(Input.Keys.W)) {
            offsetFromStartY += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.A)) {
            offsetFromStartX -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.S)) {
            offsetFromStartY -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.D)) {
            offsetFromStartX += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
    }

    abstract float getMovementSpeed();

    public Texture getTexture() {
        if (idle) {
            return this.idleAnimation.getCurrentTexture();
        }
        return this.runAnimation.getCurrentTexture();
    }

    public float getOffsetFromStartX() {
        return offsetFromStartX;
    }

    public float getOffsetFromStartY() {
        return offsetFromStartY;
    }
}
