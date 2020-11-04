package de.fhbielefeld.pmdungeon.game.interactable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import de.fhbielefeld.pmdungeon.game.characters.Character;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.game.items.HealthPotion;
import de.fhbielefeld.pmdungeon.game.items.Item;

/**
 * Chest class is based
 */
public class Chest implements Interactable {

    private static final int CHEST_SIZE = 9;
    private static final int TEXTURE_COUNT = 3;
    private static final long TRANSITION_TIME = 200;
    private final float positionX;
    private final float positionY;
    private final Item[] content;
    private final Texture[] textures;
    private State state = State.CLOSED;
    private long transitionStartTime;

    public Chest(Coordinate coordinate) {
        this.positionX = coordinate.getX() + 0.5f;
        this.positionY = coordinate.getY() + 0.5f;
        this.content = new Item[CHEST_SIZE];
        this.content[0] = new HealthPotion();
        this.textures = new Texture[TEXTURE_COUNT];
        for (int i = 0; i < textures.length; i++) {
            this.textures[i] = new Texture("textures/chest/chest_full_open_anim_f" + i + ".png");
        }
    }

    /**
     * Updates the current state of the chest if its closing or opening.
     */
    public void update() {
        long currentTransitionTime = TimeUtils.timeSinceMillis(transitionStartTime);
        if (this.state == State.OPENING && currentTransitionTime > TRANSITION_TIME) {
            this.state = State.OPEN;
        } else if (this.state == State.CLOSING && currentTransitionTime > TRANSITION_TIME) {
            this.state = State.CLOSED;
        }
    }

    /**
     * Renders the chest at its coordinates.
     *
     * @param batch SpriteBatch used to render on the screen
     */
    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), positionX - 0.5f, positionY - 0.5f, 1, 1);
    }

    /**
     * Defines the behavior of the chest when a character interacts with it. It's opening when it's closed and vice versa.
     *
     * @param character Character who interacted with the interactable
     */
    @Override
    public void interact(Character character) {
        switch (this.state) {
            case CLOSED:
                open();
                character.disableMovement();
                break;
            case OPEN:
                close();
                character.enableMovement();
                break;
            default:
                break;
        }
    }

    /**
     * Opens the chest if it's not currently in a transition and it's current state is closed
     */
    private void open() {
        if (TimeUtils.timeSinceMillis(transitionStartTime) > TRANSITION_TIME && this.state == State.CLOSED) {
            this.state = State.OPENING;
            this.transitionStartTime = TimeUtils.millis();
        }
    }

    /**
     * Closes the chest if it's not currently in a transition and it's current state is open
     */
    private void close() {
        if (TimeUtils.timeSinceMillis(transitionStartTime) > TRANSITION_TIME && this.state == State.OPEN) {
            this.state = State.CLOSING;
            this.transitionStartTime = TimeUtils.millis();
        }
    }

    /**
     * Returns the current texture of the chest based on it's state
     *
     * @return Current texture of the chest
     */
    private Texture getTexture() {
        switch (this.state) {
            case CLOSED:
                return this.textures[0];
            case OPEN:
                return this.textures[2];
            case OPENING:
            case CLOSING:
                return this.textures[1];
        }
        return null;
    }

    /**
     * Removes an item from the chest
     *
     * @param index Index of the item that should be removed
     */
    public void removeItemAt(int index) {
        content[index] = null;
    }

    public Item[] getContent() {
        return content;
    }

    public Item getItemAt(int index) {
        return content[index];
    }

    public State getState() {
        return state;
    }

    @Override
    public float getPositionX() {
        return positionX;
    }

    @Override
    public float getPositionY() {
        return positionY;
    }

    @Override
    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    public enum State {
        OPEN,
        CLOSED,
        OPENING,
        CLOSING
    }
}
