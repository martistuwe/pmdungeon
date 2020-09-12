package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.items.Item;
import de.fhbielefeld.pmdungeon.items.Sword;

public abstract class PlayerCharacter extends Character {

    public static final int INVENTORY_SIZE = 3;
    private static final float ITEM_SIZE_SCALE = 0.04f;
    private final Item[] inventory;
    private Item selectedItem;

    protected PlayerCharacter(SpriteBatch batch, Dungeon dungeon, float movementSpeed, float maxHealthPoints) {
        super(batch, dungeon, movementSpeed, maxHealthPoints);
        inventory = new Item[INVENTORY_SIZE];
        inventory[0] = new Sword();
        inventory[1] = new Sword();
        inventory[2] = new Sword();
    }

    @Override
    public void render() {
        if (selectedItem != null) {
            Texture itemTexture = selectedItem.getTexture();
            Sprite sprite = new Sprite(itemTexture);
            sprite.setSize(itemTexture.getHeight() * ITEM_SIZE_SCALE, itemTexture.getWidth() * ITEM_SIZE_SCALE);
            sprite.rotate90(!facingLeft);
            if (facingLeft) {
                sprite.setPosition(positionX - 1.5f, positionY);
            } else {
                sprite.setPosition(positionX, positionY);
            }
            sprite.draw(batch);
        }
        super.render();
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
