package de.fhbielefeld.pmdungeon.game.interactable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import de.fhbielefeld.pmdungeon.game.characters.Character;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.game.items.HealthPotion;
import de.fhbielefeld.pmdungeon.game.items.Item;

public class Chest implements Disposable, Interactable {

    private static final int CHEST_SIZE = 3;
    private static final int TEXTURE_COUNT = 3;
    private static final long TRANSITION_TIME = 200;
    private final Coordinate coordinate;
    private final Item[] content;
    private final Texture[] textures;
    private State state = State.CLOSED;
    private long transitionStartTime;

    public Chest(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.content = new Item[CHEST_SIZE];
        this.content[0] = new HealthPotion();
        this.textures = new Texture[TEXTURE_COUNT];
        for (int i = 0; i < textures.length; i++) {
            this.textures[i] = new Texture("textures/chest/chest_full_open_anim_f" + i + ".png");
        }
    }

    public void update() {
        long currentTransitionTime = TimeUtils.timeSinceMillis(transitionStartTime);
        if (this.state == State.OPENING && currentTransitionTime > TRANSITION_TIME) {
            this.state = State.OPEN;
        } else if (this.state == State.CLOSING && currentTransitionTime > TRANSITION_TIME) {
            this.state = State.CLOSED;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), coordinate.getX(), coordinate.getY(), 1, 1);
    }

    @Override
    public void interact(Character character) {
        switch (this.state) {
            case CLOSED:
                open();
                break;
            case OPEN:
                close();
                break;
        }
    }

    private void open() {
        if (TimeUtils.timeSinceMillis(transitionStartTime) > TRANSITION_TIME && this.state == State.CLOSED) {
            this.state = State.OPENING;
            this.transitionStartTime = TimeUtils.millis();
        }
    }

    private void close() {
        if (TimeUtils.timeSinceMillis(transitionStartTime) > TRANSITION_TIME && this.state == State.OPEN) {
            this.state = State.CLOSING;
            this.transitionStartTime = TimeUtils.millis();
        }
    }

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

    public Item[] getContent() {
        return content;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    enum State {
        OPEN,
        CLOSED,
        OPENING,
        CLOSING
    }
}
