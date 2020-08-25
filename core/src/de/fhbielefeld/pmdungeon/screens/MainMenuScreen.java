package de.fhbielefeld.pmdungeon.screens;

import com.badlogic.gdx.Screen;
import de.fhbielefeld.pmdungeon.PMDungeon;

// oder extends ScreenAdapter um nicht alle Methoden überschreiben zu müssen
public class MainMenuScreen implements Screen {

    final PMDungeon pmDungeon;

    public MainMenuScreen(final PMDungeon pmDungeon) {
        this.pmDungeon = pmDungeon;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
