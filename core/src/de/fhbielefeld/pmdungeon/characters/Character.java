package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public abstract class Character {

    protected SpriteBatch batch;
    protected float movementSpeed;
    protected Animation idleAnimation;
    protected Animation runAnimation;
    protected boolean idle;
    protected boolean facingLeft = false;
    protected float positionX = 0;
    protected float positionY = 0;

    protected Character(SpriteBatch batch) {
        this.batch = batch;
    }

    protected float getMovementSpeed() {
        return movementSpeed;
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

    public void render() {
        Sprite sprite = new Sprite(this.getTexture());
        sprite.flip(facingLeft, false);
        sprite.setPosition(positionX, positionY);
        sprite.draw(batch);
    }

    protected void moveLeft() {
        idle = false;
        positionY += getMovementSpeed() * Gdx.graphics.getDeltaTime();
    }

    protected void moveUp() {
        positionX -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
        idle = false;
        facingLeft = true;
    }

    protected void moveRight() {
        positionY -= getMovementSpeed() * Gdx.graphics.getDeltaTime();
        idle = false;
    }

    protected void moveDown() {
        positionX += getMovementSpeed() * Gdx.graphics.getDeltaTime();
        idle = false;
        facingLeft = false;
    }
}
