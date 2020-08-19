package de.fhbielefeld.pmdungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.fhbielefeld.pmdungeon.screens.MainMenuScreen;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.DungeonConverter;

public class PMDungeon extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        //DungeonConverter dungeonConverter = new DungeonConverter();
        //dungeonConverter.dungeonFromJson("simple_dungeon.json");
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
