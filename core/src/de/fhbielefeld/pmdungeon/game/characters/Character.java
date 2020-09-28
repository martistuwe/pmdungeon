package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;
import de.fhbielefeld.pmdungeon.game.interactable.Chest;
import de.fhbielefeld.pmdungeon.game.interactable.Interactable;
import de.fhbielefeld.pmdungeon.game.inventory.Inventory;

public abstract class Character implements Disposable {

    private static final float CHARACTER_WIDTH = 1;
    private static final float INTERACTABLE_REACH = 1.5f;
    private static final float PUNCH_BACK_INTENSITY = 10f;
    private static final float PUNCH_BACK_DURATION = 100;

    protected GameWorld gameWorld;
    protected InputComponent inputComponent;
    protected GraphicsComponent graphicsComponent;

    protected boolean idle = true;
    private boolean dead = false;
    protected boolean facingLeft = false;
    private boolean movementEnabled = true;
    private boolean punched = false;
    private long punchStart;
    private Vector2 punchBackDirection = new Vector2();
    protected float positionX = 0;
    protected float positionY = 0;
    private float oldX = 0;
    private float oldY = 0;

    private float healthPoints;

    protected final Inventory inventory;

    protected Character(InputComponent inputComponent, GameWorld gameWorld) {
        this.inputComponent = inputComponent;
        this.gameWorld = gameWorld;

        this.inventory = new Inventory(getInventorySize());
        this.healthPoints = getMaxHealthPoints();
        this.graphicsComponent = new GraphicsComponent(this, setupIdleAnimation(), setupRunAnimation());
    }

    protected abstract Animation setupIdleAnimation();

    protected abstract Animation setupRunAnimation();

    protected abstract float getMovementSpeed();

    protected abstract int getInventorySize();

    public abstract float getMaxHealthPoints();

    public abstract int getAiRadius();

    public void update() {
        if (punched) {
            disableMovement();
            if (TimeUtils.timeSinceMillis(punchStart) < PUNCH_BACK_DURATION) {
                float nextX = positionX + punchBackDirection.x * Gdx.graphics.getDeltaTime() * PUNCH_BACK_INTENSITY;
                float nextY = positionY + punchBackDirection.y * Gdx.graphics.getDeltaTime() * PUNCH_BACK_INTENSITY;
                if (gameWorld.getDungeon().isTileAccessible((int) nextX, (int) nextY)) {
                    positionX = nextX;
                    positionY = nextY;
                }
            } else {
                enableMovement();
            }
        }

        if (dead) dispose();
        if (inputComponent != null) inputComponent.update(this);

        checkForIdle();
    }

    private void checkForIdle() {
        idle = positionX == oldX && positionY == oldY;
        oldX = positionX;
        oldY = positionY;
    }

    public void render() {
        graphicsComponent.update(gameWorld.getBatch());
    }

    public void moveUp() {
        if (movementEnabled) {
            float nextY = positionY + getMovementSpeed() * Gdx.graphics.getDeltaTime();
            if (gameWorld.getDungeon().isTileAccessible((int) positionX, (int) nextY)) positionY = nextY;
        }
    }

    public void moveDown() {
        if (movementEnabled) {
            float nextY = positionY - getMovementSpeed() * Gdx.graphics.getDeltaTime();
            if (gameWorld.getDungeon().isTileAccessible((int) positionX, (int) nextY)) positionY = nextY;
        }
    }

    public void moveLeft() {
        if (movementEnabled) {
            facingLeft = true;
            float nextX = positionX - getMovementSpeed() * Gdx.graphics.getDeltaTime();
            if (gameWorld.getDungeon().isTileAccessible((int) nextX, (int) positionY)) positionX = nextX;
        }
    }

    public void moveRight() {
        if (movementEnabled) {
            facingLeft = false;
            float nextX = positionX + getMovementSpeed() * Gdx.graphics.getDeltaTime();
            if (gameWorld.getDungeon().isTileAccessible((int) nextX, (int) positionY)) positionX = nextX;
        }
    }

    public void useSelectedItem() {
        if (movementEnabled && inventory.getSelectedItem() != null) {
            inventory.getSelectedItem().use(this);
        }
    }

    public void interact() {
        Interactable interactable = gameWorld.nearestInteractable(this);
        if (interactable != null && distanceBetween(interactable.getPositionX(), interactable.getPositionY()) < INTERACTABLE_REACH) {
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
            if (character != this) {
                float distance = distanceBetween(character);
                if (minDistance > distance) {
                    minDistance = distance;
                    returnCharacter = character;
                }
            }
        }
        return returnCharacter;
    }

    public float distanceBetween(Character character) {
        return distanceBetween(character.getPositionX(), character.getPositionY());
    }

    public float distanceBetween(float x, float y) {
        return (float) Math.sqrt(Math.pow(this.positionX - x, 2) + Math.pow(this.positionY - y, 2));
    }

    public void attack(Character character, float damage) {
        if (this != character) {
            character.decreaseHealth(damage);
        }
    }

    public void punchBack(Character from) {
        punched = true;
        Vector2 vector = new Vector2(positionX - from.getPositionX(), positionY - from.getPositionY());
        vector.nor();
        punchBackDirection = vector;
        punchStart = TimeUtils.millis();
    }

    private void decreaseHealth(float damage) {
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            dead = true;
        }
    }

    public void heal(float heal) {
        if (heal > 0) {
            if (this.healthPoints + heal >= getMaxHealthPoints()) {
                this.healthPoints = getMaxHealthPoints();
            } else {
                this.healthPoints += heal;
            }
        }
    }

    public void takeFromChest(Chest chest, int index) {
        if (chest.getState() == Chest.State.OPEN && chest.getItemAt(index) != null && this.inventory.add(chest.getItemAt(index))) {
            chest.removeItemAt(index);
        }
    }

    public void setPosition(Coordinate position) {
        this.positionX = position.getX();
        this.positionY = position.getY();
    }

    public Tile currentTile() {
        return gameWorld.getDungeon().getTileAt((int) positionX, (int) positionY);
    }

    public void enableMovement() {
        this.movementEnabled = true;
    }

    public void disableMovement() {
        this.movementEnabled = false;
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

    public boolean isDead() {
        return dead;
    }

    public boolean isIdle() {
        return idle;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public float getCharacterWidth() {
        return CHARACTER_WIDTH;
    }

    @Override
    public void dispose() {
        graphicsComponent.dispose();
    }
}
