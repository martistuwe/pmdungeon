package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.items.Item;
import de.fhbielefeld.pmdungeon.items.Sword;

public abstract class PlayerCharacter extends Character {

    private static final int INVENTORY_SIZE = 3;
    private final Item[] inventory;

    protected PlayerCharacter(SpriteBatch batch, Dungeon dungeon, float movementSpeed, float maxHealthPoints) {
        super(batch, dungeon, movementSpeed, maxHealthPoints);
        inventory = new Item[INVENTORY_SIZE];
        inventory[0] = new Sword();
    }

    public void handleInput(Input input) {
        float targetX = positionX;
        float targetY = positionY;
        idle = true;
        if (input.isKeyPressed(Input.Keys.W)) {
            targetY += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.A)) {
            targetX -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = true;
        }
        if (input.isKeyPressed(Input.Keys.S)) {
            targetY -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (input.isKeyPressed(Input.Keys.D)) {
            targetX += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = false;
        }
        move(targetX, targetY);
    }

    public Item[] getInventory() {
        return inventory;
    }
}
