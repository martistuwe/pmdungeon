package de.fhbielefeld.pmdungeon.characters.playercharacters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.characters.Animation;
import de.fhbielefeld.pmdungeon.characters.PlayerCharacter;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

public class MaleKnight extends PlayerCharacter {

    private static final float MOVEMENT_SPEED = 5;
    private static final float MAX_HEALTH = 5f;

    public MaleKnight(SpriteBatch batch, Dungeon dungeon) {
        super(batch, dungeon);

        this.movementSpeed = MOVEMENT_SPEED;
        this.healthPoints = MAX_HEALTH;
        this.maxHealthPoints = MAX_HEALTH;

        this.idleAnimation = new Animation(0.2f);
        this.runAnimation = new Animation(0.1f);
        for (int i = 0; i < 4; i++) {
            this.idleAnimation.addTexture(new Texture("textures/characters/playercharacters/knight_m_idle_anim_f" + i + ".png"));
            this.runAnimation.addTexture(new Texture("textures/characters/playercharacters/knight_m_run_anim_f" + i + ".png"));
        }
    }
}
