package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public abstract class Character implements Disposable {

    private static final float RENDERING_OFFSET_X = -0.85f;
    private static final float RENDERING_OFFSET_Y = -0.5f;

    protected SpriteBatch batch;
    protected Dungeon dungeon;
    protected Animation idleAnimation;
    protected Animation runAnimation;
    protected boolean idle = true;
    protected boolean facingLeft = false;
    protected float positionX = 0;
    protected float positionY = 0;

    private final float movementSpeed;
    private float healthPoints;
    private final float maxHealthPoints;

    protected Character(SpriteBatch batch, Dungeon dungeon, float movementSpeed, float maxHealthPoints) {
        this.batch = batch;
        this.dungeon = dungeon;
        this.movementSpeed = movementSpeed;
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
    }

    public abstract void update();

    public void render() {
        Texture texture = this.getTexture();
        Sprite sprite = new Sprite(texture);
        sprite.flip(facingLeft, false);
        sprite.setSize(1, (float) texture.getHeight() / (float) texture.getWidth());
        sprite.setPosition(positionX + RENDERING_OFFSET_X, positionY + RENDERING_OFFSET_Y);
        sprite.draw(batch);
    }

    public void moveUp() {
        float nextY = positionY + movementSpeed * Gdx.graphics.getDeltaTime();
        if (dungeon.isTileAccessible((int) positionX, (int) nextY)) positionY = nextY;
    }

    public void moveDown() {
        float nextY = positionY - movementSpeed * Gdx.graphics.getDeltaTime();
        if (dungeon.isTileAccessible((int) positionX, (int) nextY)) positionY = nextY;
    }

    public void moveLeft() {
        facingLeft = true;
        float nextX = positionX - movementSpeed * Gdx.graphics.getDeltaTime();
        if (dungeon.isTileAccessible((int) nextX, (int) positionY)) positionX = nextX;
    }

    public void moveRight() {
        facingLeft = false;
        float nextX = positionX + movementSpeed * Gdx.graphics.getDeltaTime();
        if (dungeon.isTileAccessible((int) nextX, (int) positionY)) positionX = nextX;
    }

    public Character nearestCharacter(Character[] characters) {
        float minDistance = Float.MAX_VALUE;
        Character returnCharacter = null;
        for (Character character : characters) {
            float distance = distanceBetween(character);
            if (minDistance > distance && character != this) returnCharacter = character;
        }
        return returnCharacter;
    }

    public float distanceBetween(Character that) {
        return (float) Math.sqrt(Math.pow(this.positionX - that.positionX, 2) + Math.pow(this.positionY - that.positionY, 2));
    }

    public void attack(Character character, float damage) {
        character.decreaseHealth(damage);
    }

    private void decreaseHealth(float damage) {
        this.healthPoints -= damage;
        //TODO if healthPoints < 0 = die
    }

    public void setPosition(Coordinate position) {
        this.positionX = position.getX();
        this.positionY = position.getY();
    }

    public Texture getTexture() {
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

    public float getHealthPoints() {
        return healthPoints;
    }

    public float getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    @Override
    public void dispose() {
        idleAnimation.dispose();
        runAnimation.dispose();
    }
}
