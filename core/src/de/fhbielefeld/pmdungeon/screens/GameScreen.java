package de.fhbielefeld.pmdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import de.fhbielefeld.pmdungeon.PMDungeon;
import de.fhbielefeld.pmdungeon.characters.MaleKnight;
import de.fhbielefeld.pmdungeon.characters.PlayableCharacter;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Coordinate;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.DungeonConverter;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameScreen implements Screen {

    final PMDungeon pmDungeon;

    private OrthographicCamera camera;
    private Dungeon dungeon;
    private final PlayableCharacter hero;

    public GameScreen(final PMDungeon pmDungeon) {
        this.pmDungeon = pmDungeon;
        hero = new MaleKnight(pmDungeon.batch);
        setupCamera();
        setupDungeon();
    }

    private void setupCamera() {
        camera = new OrthographicCamera(250, 150);
        camera.position.set(0, 0, 0);
        camera.update();
    }

    private void setupDungeon() {
        DungeonConverter dungeonConverter = new DungeonConverter();
        dungeon = dungeonConverter.dungeonFromJson("boss_dungeon.json");
        Coordinate startPosition = dungeon.getStart();
        hero.setPosition(startPosition);
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for a Game.
    }

    @Override
    public void render(float delta) {
        //handle Input
        //update World
        //render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        camera.update();
        pmDungeon.batch.setProjectionMatrix(camera.combined);

        float cameraSpeed = 100;
        float cameraZoomSpeed = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-cameraSpeed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(cameraSpeed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, cameraSpeed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -cameraSpeed * Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.PLUS)) {
            camera.zoom -= cameraZoomSpeed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.MINUS)) {
            camera.zoom += cameraZoomSpeed * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            dungeon.printToConsole();
        }

        hero.handleInput(Gdx.input);

        pmDungeon.batch.begin();
        dungeon.render(pmDungeon.batch);
        hero.render();
        //dungeon.renderWalls(pmDungeon.batch);
        pmDungeon.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // This method is called every time the game screen is re-sized and the game is not in the paused state.
        // It is also called once just after the create() method.
        //The parameters are the new width and height the screen has been resized to in pixels.
    }

    @Override
    public void pause() {
        // On Android this method is called when the Home button is pressed or an incoming call is received.
        // On desktop this is called just before dispose() when exiting the application.
        // A good place to save the game state.
    }

    @Override
    public void resume() {
        // This method is only called on Android, when the application resumes from a paused state.
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen for a Game.
    }

    @Override
    public void dispose() {
        // Called when the application is destroyed. It is preceded by a call to pause().
        dungeon.dispose();
    }
}
