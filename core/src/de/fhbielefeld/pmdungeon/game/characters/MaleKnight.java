package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.items.HealthPotion;
import de.fhbielefeld.pmdungeon.game.items.Sword;

public class MaleKnight extends Character {

    private static final float MOVEMENT_SPEED = 5;
    private static final float MAX_HEALTH = 5f;
    public static final int INVENTORY_SIZE = 3;
    public static final int TEXTURE_COUNT = 4;
    public static final int AI_RADIUS = 0;

    public MaleKnight(InputComponent inputComponent, GameWorld gameWorld) {
        super(inputComponent, gameWorld);
        inventory.setSlot(0, new Sword());
        inventory.setSlot(1, new HealthPotion());
    }

    @Override
    protected Animation setupIdleAnimation() {
        Animation idle = new Animation(0.2f);
        for (int i = 0; i < TEXTURE_COUNT; i++) {
            idle.addTexture(new Texture("textures/characters/playercharacters/knight_m_idle_anim_f" + i + ".png"));
        }
        return idle;
    }

    @Override
    protected Animation setupRunAnimation() {
        Animation run = new Animation(0.1f);
        for (int i = 0; i < TEXTURE_COUNT; i++) {
            run.addTexture(new Texture("textures/characters/playercharacters/knight_m_run_anim_f" + i + ".png"));
        }
        return run;
    }

    @Override
    protected float getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    @Override
    protected int getInventorySize() {
        return INVENTORY_SIZE;
    }

    @Override
    public float getMaxHealthPoints() {
        return MAX_HEALTH;
    }

    @Override
    public int getAiRadius() {
        return AI_RADIUS;
    }
}
