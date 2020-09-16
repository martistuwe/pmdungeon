package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.items.HealthPotion;
import de.fhbielefeld.pmdungeon.game.items.Sword;

public class MaleKnight extends Character {

    private static final float MOVEMENT_SPEED = 5;
    private static final float MAX_HEALTH = 5f;
    public static final int INVENTORY_SIZE = 3;

    public MaleKnight(GameWorld gameWorld) {
        super(gameWorld, MOVEMENT_SPEED, MAX_HEALTH, INVENTORY_SIZE);

        this.idleAnimation = new Animation(0.2f);
        this.runAnimation = new Animation(0.1f);
        for (int i = 0; i < 4; i++) {
            this.idleAnimation.addTexture(new Texture("textures/characters/playercharacters/knight_m_idle_anim_f" + i + ".png"));
            this.runAnimation.addTexture(new Texture("textures/characters/playercharacters/knight_m_run_anim_f" + i + ".png"));
        }
        inventory.setSlot(0, new Sword());
        inventory.setSlot(1, new HealthPotion());
    }

    @Override
    public void update() {

    }
}
