package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.characters.components.InputComponent;

public class Imp extends Character {

    private static final float MOVEMENT_SPEED = 2f;
    private static final float MAX_HEALTH_POINTS = 2f;
    private static final int INVENTORY_SIZE = 1;
    private static final int TEXTURE_COUNT = 4;

    public Imp(GameWorld gameWorld, InputComponent inputComponent) {
        super(gameWorld, inputComponent, MAX_HEALTH_POINTS, MOVEMENT_SPEED, INVENTORY_SIZE);
    }

    @Override
    protected Animation setupIdleAnimation() {
        Animation idle = new Animation(0.2f);
        for (int i = 0; i < TEXTURE_COUNT; i++) {
            idle.addTexture(new Texture("textures/characters/demons/imp/imp_idle_anim_f" + i + ".png"));
        }
        return idle;
    }

    @Override
    protected Animation setupRunAnimation() {
        Animation run = new Animation(0.1f);
        for (int i = 0; i < TEXTURE_COUNT; i++) {
            run.addTexture(new Texture("textures/characters/demons/imp/imp_run_anim_f" + i + ".png"));
        }
        return run;
    }
}
