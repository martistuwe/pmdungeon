package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.game.GameWorld;

public class Imp extends Character {

    private static final float MOVEMENT_SPEED = 2f;
    private static final float MAX_HEALTH_POINTS = 2f;
    private static final int INVENTORY_SIZE = 1;

    public Imp(GameWorld gameWorld) {
        super(gameWorld, MOVEMENT_SPEED, MAX_HEALTH_POINTS, INVENTORY_SIZE);

        this.idleAnimation = new Animation(0.2f);
        this.runAnimation = new Animation(0.2f);
        for (int i = 0; i < 4; i++) {
            this.idleAnimation.addTexture(new Texture("textures/characters/demons/imp/imp_idle_anim_f" + i + ".png"));
            this.runAnimation.addTexture(new Texture("textures/characters/demons/imp/imp_run_anim_f" + i + ".png"));
        }
    }

    @Override
    public void update() {
        //soon
    }
}
