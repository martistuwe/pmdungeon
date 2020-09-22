package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.game.interactable.Interactable;
import de.fhbielefeld.pmdungeon.game.inventory.Inventory;

public abstract class Character implements Disposable {

    private static final float CHARACTER_WIDTH = 1;
    private static final float RENDERING_OFFSET_X = -0.85f;
    private static final float RENDERING_OFFSET_Y = -0.5f;
    private static final float INTERACTABLE_REACH = 1;

    protected GameWorld gameWorld;

    protected Animation idleAnimation;
    protected Animation runAnimation;
    protected boolean idle = true;
    protected boolean facingLeft = false;
    protected float positionX = 0;
    protected float positionY = 0;
    private float oldX = 0;
    private float oldY = 0;

    private final float movementSpeed;
    private float healthPoints;
    private final float maxHealthPoints;

    protected final Inventory inventory;

    protected Character(GameWorld gameWorld, float movementSpeed, float maxHealthPoints, int inventorySize) {
        this.gameWorld = gameWorld;
        this.movementSpeed = movementSpeed;
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.inventory = new Inventory(inventorySize);
    }

    public void update() {
        idle = positionX == oldX && positionY == oldY;
        oldX = positionX;
        oldY = positionY;
    }

    public void render() {
        if (this.inventory.getSelectedItem() != null) {
            renderSelectedInventoryItem();
        }
        renderCharacter();
    }

    private void renderSelectedInventoryItem() {
        this.inventory.getSelectedItem().renderAtCharacter(this, gameWorld.getBatch());
    }

    private void renderCharacter() {
        Texture texture = this.getCurrentTexture();
        Sprite sprite = new Sprite(texture);
        sprite.flip(facingLeft, false);
        sprite.setSize(getCharacterWidth(), ((float) texture.getHeight() / (float) texture.getWidth()) * getCharacterWidth());
        sprite.setPosition(positionX + RENDERING_OFFSET_X, positionY + RENDERING_OFFSET_Y);
        sprite.draw(gameWorld.getBatch());
    }

    public void moveUp() {
        float nextY = positionY + movementSpeed * Gdx.graphics.getDeltaTime();
        if (gameWorld.getDungeon().isTileAccessible((int) positionX, (int) nextY)) positionY = nextY;
    }

    public void moveDown() {
        float nextY = positionY - movementSpeed * Gdx.graphics.getDeltaTime();
        if (gameWorld.getDungeon().isTileAccessible((int) positionX, (int) nextY)) positionY = nextY;
    }

    public void moveLeft() {
        facingLeft = true;
        float nextX = positionX - movementSpeed * Gdx.graphics.getDeltaTime();
        if (gameWorld.getDungeon().isTileAccessible((int) nextX, (int) positionY)) positionX = nextX;
    }

    public void moveRight() {
        facingLeft = false;
        float nextX = positionX + movementSpeed * Gdx.graphics.getDeltaTime();
        if (gameWorld.getDungeon().isTileAccessible((int) nextX, (int) positionY)) positionX = nextX;
    }

    public void useSelectedItem() {
        if (inventory.getSelectedItem() != null) {
            inventory.getSelectedItem().use(this);
        }
    }

    public void interact() {
        Interactable interactable = nearestInteractable();
        if (interactable != null && distanceBetween(interactable.getCoordinate().getX(), interactable.getCoordinate().getY()) < INTERACTABLE_REACH) {
            interactable.interact(this);
        }
    }

    public void selectItem(int index) {
        inventory.setSelectedItem(index);
    }

    public Character nearestCharacter() {
        float minDistance = Float.MAX_VALUE;
        Character returnCharacter = null;
        for (Character character : gameWorld.getCharacterList()) {
            if (this != character) {
                float distance = distanceBetween(character);
                if (minDistance > distance) returnCharacter = character;
            }
        }
        return returnCharacter;
    }

    private Interactable nearestInteractable() {
        float minDistance = Float.MAX_VALUE;
        Interactable returnInteractable = null;
        for (Interactable interactable : gameWorld.getInteractables()) {
            float distance = distanceBetween(interactable.getCoordinate().getX(), interactable.getCoordinate().getY());
            if (minDistance > distance) returnInteractable = interactable;
        }
        return returnInteractable;
    }

    public float distanceBetween(Character character) {
        return distanceBetween(character.getPositionX(), character.getPositionY());
    }

    private float distanceBetween(float x, float y) {
        return (float) Math.sqrt(Math.pow(this.positionX - x, 2) + Math.pow(this.positionY - y, 2));
    }

    public void attack(Character character, float damage) {
        if (this != character) {
            character.decreaseHealth(damage);
        }
    }

    public void heal(float heal) {
        increaseHealth(heal);
    }

    private void decreaseHealth(float damage) {
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            System.out.println("DEAD: " + this.toString());
        }
        //TODO if healthPoints < 0 = die
    }

    private void increaseHealth(float heal) {
        if (heal > 0) {
            if (this.healthPoints + heal >= this.maxHealthPoints) {
                this.healthPoints = this.maxHealthPoints;
            } else {
                this.healthPoints += heal;
            }
        }
    }

    public void setPosition(Coordinate position) {
        this.positionX = position.getX();
        this.positionY = position.getY();
    }

    public Texture getCurrentTexture() {
        if (idle) {
            return this.idleAnimation.getCurrentTexture();
        } else {
            return this.runAnimation.getCurrentTexture();
        }
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public float getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public float getCharacterWidth() {
        return CHARACTER_WIDTH;
    }

    @Override
    public void dispose() {
        idleAnimation.dispose();
        runAnimation.dispose();
    }
}
