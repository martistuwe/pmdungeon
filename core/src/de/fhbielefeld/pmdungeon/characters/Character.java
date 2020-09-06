package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;

public abstract class Character {

    private static final float RENDERING_OFFSET_X = -0.85f;
    private static final float RENDERING_OFFSET_Y = -0.5f;

    protected SpriteBatch batch;
    protected Dungeon dungeon;
    protected float movementSpeed;
    protected Animation idleAnimation;
    protected Animation runAnimation;
    protected boolean idle;
    protected boolean facingLeft = false;
    protected float positionX = 0;
    protected float positionY = 0;

    protected Character(SpriteBatch batch, Dungeon dungeon) {
        this.batch = batch;
        this.dungeon = dungeon;
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
        Texture texture = this.getTexture();
        Sprite sprite = new Sprite(texture);
        sprite.flip(facingLeft, false);
        sprite.setSize(1, (float) texture.getHeight() / (float) texture.getWidth());
        sprite.setPosition(positionX + RENDERING_OFFSET_X, positionY + RENDERING_OFFSET_Y);
        sprite.draw(batch);
    }

    protected void move(float targetX, float targetY) {
        if (dungeon.getTileAt((int) targetX, (int) targetY) == Dungeon.Tile.FLOOR || dungeon.getTileAt((int) targetX, (int) targetY) == Dungeon.Tile.DOOR) {
            this.positionX = targetX;
            this.positionY = targetY;
        }
    }
}
