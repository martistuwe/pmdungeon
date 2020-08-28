package de.fhbielefeld.pmdungeon.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MaleKnight extends PlayableCharacter {

    private static final float MOVEMENT_SPEED = 90;

    public MaleKnight(SpriteBatch batch) {
        super(batch);
        this.idleAnimation = new Animation(0.2f);
        this.idleAnimation.addTexture(new Texture("textures/characters/knight_m_idle_anim_f0.png"));
        this.idleAnimation.addTexture(new Texture("textures/characters/knight_m_idle_anim_f1.png"));
        this.idleAnimation.addTexture(new Texture("textures/characters/knight_m_idle_anim_f2.png"));
        this.idleAnimation.addTexture(new Texture("textures/characters/knight_m_idle_anim_f3.png"));

        this.runAnimation = new Animation(0.1f);
        this.runAnimation.addTexture(new Texture("textures/characters/knight_m_run_anim_f0.png"));
        this.runAnimation.addTexture(new Texture("textures/characters/knight_m_run_anim_f1.png"));
        this.runAnimation.addTexture(new Texture("textures/characters/knight_m_run_anim_f2.png"));
        this.runAnimation.addTexture(new Texture("textures/characters/knight_m_run_anim_f3.png"));
    }

    @Override
    float getMovementSpeed() {
        return MOVEMENT_SPEED;
    }
}
