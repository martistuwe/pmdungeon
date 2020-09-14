package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.game.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.game.items.Sword;

public class MaleKnight extends Character {

    private static final float MOVEMENT_SPEED = 5;
    private static final float MAX_HEALTH = 5f;
    public static final int INVENTORY_SIZE = 3;
    private static final float ITEM_SIZE_SCALE = 0.04f;

    public MaleKnight(SpriteBatch batch, Dungeon dungeon) {
        super(batch, dungeon, MOVEMENT_SPEED, MAX_HEALTH, INVENTORY_SIZE);

        this.idleAnimation = new Animation(0.2f);
        this.runAnimation = new Animation(0.1f);
        for (int i = 0; i < 4; i++) {
            this.idleAnimation.addTexture(new Texture("textures/characters/playercharacters/knight_m_idle_anim_f" + i + ".png"));
            this.runAnimation.addTexture(new Texture("textures/characters/playercharacters/knight_m_run_anim_f" + i + ".png"));
        }
        inventory.setSlot(0, new Sword());
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        if (this.inventory.getSelectedItem() != null) {
            Texture itemTexture = inventory.getSelectedItem().getTexture();
            Sprite sprite = new Sprite(itemTexture);
            sprite.setSize(itemTexture.getWidth() * ITEM_SIZE_SCALE, itemTexture.getHeight() * ITEM_SIZE_SCALE);
            if (facingLeft) {
                sprite.setPosition(positionX - 1f, positionY);
            } else {
                sprite.setPosition(positionX - 0.1f, positionY);
            }
            sprite.draw(batch);
        }
        super.render();
    }
}
