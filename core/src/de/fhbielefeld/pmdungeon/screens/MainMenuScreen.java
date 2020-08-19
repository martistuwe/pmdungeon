package de.fhbielefeld.pmdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import de.fhbielefeld.pmdungeon.PMDungeon;
import de.fhbielefeld.pmdungeon.characters.MaleKnight;
import de.fhbielefeld.pmdungeon.characters.PlayableCharacter;
import de.fhbielefeld.pmdungeon.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.DungeonConverter;
import de.fhbielefeld.pmdungeon.util.dungeonconverter.Room;

// oder extends ScreenAdapter um nicht alle Methoden überschreiben zu müssen
public class MainMenuScreen implements Screen {

    final PMDungeon pmDungeon;
    private OrthographicCamera camera;
    private PlayableCharacter hero;
    private Dungeon dungeon;
    private Room[] rooms;

    public MainMenuScreen(final PMDungeon pmDungeon) {
        this.pmDungeon = pmDungeon;

        camera = new OrthographicCamera(250, 150);
        camera.position.set(0, 0, 0);
        camera.update();

        hero = new MaleKnight();
        dungeon = new Dungeon(pmDungeon.batch);

        DungeonConverter dungeonConverter = new DungeonConverter();
        dungeon.setRooms(dungeonConverter.roomsFromJson("simple_dungeon.json"));
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
        hero.handleInput(Gdx.input);

        pmDungeon.batch.begin();
        dungeon.render();
        pmDungeon.batch.draw(hero.getTexture(), hero.getOffsetFromStartX(), hero.getOffsetFromStartY());
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
        dungeon.dispose();
    }
}
