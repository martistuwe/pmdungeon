package de.fhbielefeld.pmdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import de.fhbielefeld.pmdungeon.PMDungeon;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.ui.HeadUpDisplay;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameScreen extends ScreenAdapter {

    public static final float VIRTUAL_HEIGHT = 5f;

    private final PMDungeon pmDungeon;
    private OrthographicCamera camera;
    private final HeadUpDisplay hud;

    private final GameWorld gameWorld;

    public GameScreen(final PMDungeon pmDungeon) {
        this.pmDungeon = pmDungeon;
        this.gameWorld = new GameWorld(pmDungeon.getBatch());
        this.hud = new HeadUpDisplay(gameWorld);
        setupCamera();
    }

    private void setupCamera() {
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
    }

    private void debugCameraZoom() {
        float cameraZoomSpeed = 5;
        if (Gdx.input.isKeyPressed(Input.Keys.PLUS)) {
            camera.zoom -= cameraZoomSpeed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.MINUS)) {
            camera.zoom += cameraZoomSpeed * Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        debugCameraZoom();

        gameWorld.update();

        camera.position.set(gameWorld.getHero().getPositionX(), gameWorld.getHero().getPositionY(), 0);
        camera.update();
        pmDungeon.getBatch().setProjectionMatrix(camera.combined);

        pmDungeon.getBatch().begin();
        gameWorld.render();
        hud.render();
        pmDungeon.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, VIRTUAL_HEIGHT * width / (float) height, VIRTUAL_HEIGHT);
        pmDungeon.getBatch().setProjectionMatrix(camera.combined);
        hud.resize(width, height);
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
    }
}
