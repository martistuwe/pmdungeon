package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

public abstract class PlayableCharacter extends Character {

    protected PlayableCharacter(SpriteBatch batch, Dungeon dungeon) {
        super(batch, dungeon);
    }

    public void handleInput(Input input) {
        idle = true;
        if (input.isKeyPressed(Input.Keys.W)) {
            if (tileIsReachable(positionX, positionY + getMovementSpeed() * Gdx.graphics.getDeltaTime())) {
                positionY += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            }
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.A)) {
            if (tileIsReachable(positionX - getMovementSpeed() * Gdx.graphics.getDeltaTime(), positionY)) {
                positionX -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            }
            idle = false;
            facingLeft = true;
        }
        if (input.isKeyPressed(Input.Keys.S)) {
            if (tileIsReachable(positionX, positionY - getMovementSpeed() * Gdx.graphics.getDeltaTime())) {
                positionY -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            }
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.D)) {
            if (tileIsReachable(positionX + getMovementSpeed() * Gdx.graphics.getDeltaTime(), positionY)) {
                positionX += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            }
            idle = false;
            facingLeft = false;
        }
    }

    private boolean tileIsReachable(float targetX, float targetY) {
        return dungeon.getTileAt((int) targetX, (int) targetY) == Dungeon.Tile.FLOOR || dungeon.getTileAt((int) targetX, (int) targetY) == Dungeon.Tile.DOOR;
    }
}
