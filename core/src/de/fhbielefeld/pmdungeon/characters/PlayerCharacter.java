package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.items.Item;
import de.fhbielefeld.pmdungeon.items.Sword;

public abstract class PlayerCharacter extends Character {

    public static final int INVENTORY_SIZE = 3;
    private final Item[] inventory;
    private Item selectedItem;

    protected PlayerCharacter(SpriteBatch batch, Dungeon dungeon, float movementSpeed, float maxHealthPoints) {
        super(batch, dungeon, movementSpeed, maxHealthPoints);
        inventory = new Item[INVENTORY_SIZE];
        inventory[0] = new Sword();
        inventory[1] = new Sword();
        inventory[2] = new Sword();
    }

    public void update() {
        updatePosition();
        updateInventory();
    }

    private void updatePosition() {
        float targetX = positionX;
        float targetY = positionY;
        idle = true;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            targetY += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            targetX -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            targetY -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            targetX += getMovementSpeed() * Gdx.graphics.getDeltaTime();
            idle = false;
            facingLeft = false;
        }
        move(targetX, targetY);
    }

    private void updateInventory() {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            this.selectedItem = inventory[0];
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            this.selectedItem = inventory[1];
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            this.selectedItem = inventory[2];
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && this.selectedItem != null) {
            this.selectedItem.use();
        }
    }

    public Item[] getInventory() {
        return inventory;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }
}
