package de.fhbielefeld.pmdungeon.characters.nonplayercharacters.demons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.characters.Animation;
import de.fhbielefeld.pmdungeon.characters.NonPlayerCharacter;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

public class Imp extends NonPlayerCharacter {

    private static final float MOVEMENT_SPEED = 2f;
    private static final float MAX_HEALTH_POINTS = 2f;

    public Imp(SpriteBatch batch, Dungeon dungeon) {
        super(batch, dungeon, MOVEMENT_SPEED, MAX_HEALTH_POINTS);

        this.idleAnimation = new Animation(0.2f);
        this.runAnimation = new Animation(0.2f);
        for (int i = 0; i < 4; i++) {
            this.idleAnimation.addTexture(new Texture("textures/characters/nonplayercharacters/demons/imp/imp_idle_anim_f" + i + ".png"));
            this.runAnimation.addTexture(new Texture("textures/characters/nonplayercharacters/demons/imp/imp_run_anim_f" + i + ".png"));
        }
    }
}
