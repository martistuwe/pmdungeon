package de.fhbielefeld.pmdungeon.screens;

import com.badlogic.gdx.Screen;
import de.fhbielefeld.pmdungeon.PMDungeon;

public class GameScreen implements Screen {

    final PMDungeon pmDungeon;

    public GameScreen(final PMDungeon pmDungeon) {
        this.pmDungeon = pmDungeon;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //handle Input
        //update World
        //render
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
