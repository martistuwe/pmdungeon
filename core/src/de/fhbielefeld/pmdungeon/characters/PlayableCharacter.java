package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class PlayableCharacter extends Character {

    protected PlayableCharacter(SpriteBatch batch) {
        super(batch);
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
}
