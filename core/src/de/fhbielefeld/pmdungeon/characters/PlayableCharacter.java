package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class PlayableCharacter {

    protected SpriteBatch batch;

    protected Animation idleAnimation;
    protected Animation runAnimation;

    protected boolean idle;
    protected boolean facingLeft = false;

    protected float positionX = 0;
    protected float positionY = 0;

    protected PlayableCharacter(SpriteBatch batch) {
        this.batch = batch;
    }

    public void handleInput(Input input) {
        idle = true;
        if (input.isKeyPressed(Input.Keys.W)) {
            positionY += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.A)) {
            positionX -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = true;
        }
        if (input.isKeyPressed(Input.Keys.S)) {
            positionY -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.D)) {
            positionX += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = false;
        }
    }

    abstract float getMovementSpeed();

    public Texture getTexture() {
        if (idle) {
            return this.idleAnimation.getCurrentTexture();
        } else {
            return this.runAnimation.getCurrentTexture();
        }
    }

    public void render() {
        Sprite sprite = new Sprite(this.getTexture());
        sprite.flip(facingLeft, false);
        sprite.setPosition(positionX, positionY);
        sprite.draw(batch);
    }
}
