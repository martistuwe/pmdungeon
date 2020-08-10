package de.fhbielefeld.pmdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import de.fhbielefeld.pmdungeon.PMDungeon;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;

// oder extends ScreenAdapter um nicht alle Methoden überschreiben zu müssen
public class MainMenuScreen implements Screen {

    final PMDungeon pmDungeon;
    private OrthographicCamera camera;
    private Dungeon dungeon;
    private Texture floorTexture;


    public MainMenuScreen(final PMDungeon pmDungeon) {
        this.pmDungeon = pmDungeon;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
        camera.update();

        dungeon = new Dungeon();
        floorTexture = new Texture("floor_1.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        camera.update();
        pmDungeon.batch.setProjectionMatrix(camera.combined);

        float cameraSpeed = 100;
        float cameraZoomSpeed = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(cameraSpeed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(-cameraSpeed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, -cameraSpeed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, cameraSpeed * Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.PLUS)) {
            camera.zoom -= cameraZoomSpeed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.MINUS)) {
            camera.zoom += cameraZoomSpeed * Gdx.graphics.getDeltaTime();
        }


        pmDungeon.batch.begin();
        for (float i = 0; i < Dungeon.HEIGHT; i++) {
            for (float j = 0; j < Dungeon.WIDTH; j++) {
                pmDungeon.batch.draw(floorTexture, i * floorTexture.getHeight(), j * floorTexture.getWidth());
            }
        }
        //pmDungeon.font.draw(pmDungeon.batch, "Hello World!", Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        pmDungeon.batch.end();
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

