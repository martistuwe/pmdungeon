package de.fhbielefeld.pmdungeon.game.characters.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.game.characters.Animation;
import de.fhbielefeld.pmdungeon.game.characters.Character;

public class GraphicsComponent {

    private static final float RENDERING_OFFSET_X = -0.85f;
    private static final float RENDERING_OFFSET_Y = -0.5f;

    private final Character character;
    private final Animation idleAnimation;
    private final Animation runAnimation;

    public GraphicsComponent(Character character, Animation idleAnimation, Animation runAnimation) {
        this.character = character;
        this.idleAnimation = idleAnimation;
        this.runAnimation = runAnimation;
    }

    public void update(SpriteBatch batch) {
        if (character.getInventory().getSelectedItem() != null) {
            renderSelectedItem(batch);
        }
        renderCharacter(batch);
    }

    private void renderSelectedItem(SpriteBatch batch) {
        character.getInventory().getSelectedItem().renderAtCharacter(character, batch);
    }

    private void renderCharacter(SpriteBatch batch) {
        Texture texture = getCurrentTexture(character);
        Sprite sprite = new Sprite(texture);
        sprite.flip(character.isFacingLeft(), false);
        sprite.setSize(character.getCharacterWidth(), ((float) texture.getHeight() / (float) texture.getWidth()) * character.getCharacterWidth());
        sprite.setPosition(character.getPositionX() + RENDERING_OFFSET_X, character.getPositionY() + RENDERING_OFFSET_Y);
        sprite.draw(batch);
    }

    public Texture getCurrentTexture(Character character) {
        if (character.isIdle()) {
            return this.idleAnimation.getCurrentTexture();
        } else {
            return this.runAnimation.getCurrentTexture();
        }
    }

    public void dispose() {
        this.idleAnimation.dispose();
        this.runAnimation.dispose();
    }
}
