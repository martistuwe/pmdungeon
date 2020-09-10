package de.fhbielefeld.pmdungeon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.characters.PlayerCharacter;
import de.fhbielefeld.pmdungeon.items.Item;

import static de.fhbielefeld.pmdungeon.screens.GameScreen.VIRTUAL_HEIGHT;

public class HeadUpDisplay implements Disposable {

    private static final float HEART_SIZE = 0.5f;
    private static final float INVENTORY_BACKGROUND_SIZE = 0.6f;
    private static final float INVENTORY_ITEM_SIZE = 0.6f;

    private final PlayerCharacter hero;
    private final SpriteBatch hudBatch;
    private final OrthographicCamera hudCamera;
    private final Texture heartFull = new Texture("textures/ui/ui_heart_full.png");
    private final Texture heartHalf = new Texture("textures/ui/ui_heart_half.png");
    private final Texture heartEmpty = new Texture("textures/ui/ui_heart_empty.png");

    public HeadUpDisplay(PlayerCharacter hero) {
        this.hero = hero;
        hudBatch = new SpriteBatch();
        hudCamera = new OrthographicCamera();
        hudCamera.position.set(0, 0, 0);
        hudCamera.update();
    }

    public void render() {
        hudCamera.update();
        hudBatch.setProjectionMatrix(hudCamera.combined);

        hudBatch.begin();
        if (hero != null) {
            drawHealthPoints();
            drawInventory();
        }
        hudBatch.end();
    }

    private void drawHealthPoints() {
        int i = 0;
        for (; i < Math.floor(hero.getHealthPoints()); i++) {
            hudBatch.draw(heartFull, i * HEART_SIZE, VIRTUAL_HEIGHT - HEART_SIZE, HEART_SIZE, HEART_SIZE);
        }
        if (hero.getHealthPoints() % 1 != 0) {
            hudBatch.draw(heartHalf, i++ * HEART_SIZE, VIRTUAL_HEIGHT - HEART_SIZE, HEART_SIZE, HEART_SIZE);
        }
        for (; i < hero.getMaxHealthPoints(); i++) {
            hudBatch.draw(heartEmpty, i * HEART_SIZE, VIRTUAL_HEIGHT - HEART_SIZE, HEART_SIZE, HEART_SIZE);
        }
    }

    private void drawInventory() {
        Texture background = createInventoryBackground();
        float backgroundXAxis = ((VIRTUAL_HEIGHT * Gdx.graphics.getWidth() / Gdx.graphics.getHeight()) / 2) - (PlayerCharacter.INVENTORY_SIZE * INVENTORY_BACKGROUND_SIZE) / 2;

        Item[] items = hero.getInventory();
        for (int i = 0; i < PlayerCharacter.INVENTORY_SIZE; i++) {
            hudBatch.draw(background, backgroundXAxis + i * INVENTORY_BACKGROUND_SIZE, 0, INVENTORY_BACKGROUND_SIZE, INVENTORY_BACKGROUND_SIZE);
            if (items[i] != null) {
                float itemOffset = INVENTORY_BACKGROUND_SIZE / 2 - calculateItemWidth(items[i]) / 2;
                hudBatch.draw(items[i].getTexture(), (backgroundXAxis + i * INVENTORY_ITEM_SIZE) + itemOffset, 0, calculateItemWidth(items[i]), INVENTORY_ITEM_SIZE);
            }
        }
    }

    private Texture createInventoryBackground() {
        Pixmap backgroundPixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
        backgroundPixmap.setColor(0, 0, 0, 0.3f);
        backgroundPixmap.fill();
        Texture background = new Texture(backgroundPixmap);
        backgroundPixmap.dispose();
        return background;
    }

    private float calculateItemWidth(Item item) {
        return (float) item.getTexture().getWidth() / (float) item.getTexture().getHeight() * INVENTORY_ITEM_SIZE;
    }

    public void resize(int width, int height) {
        hudCamera.setToOrtho(false, VIRTUAL_HEIGHT * width / (float) height, VIRTUAL_HEIGHT);
        hudBatch.setProjectionMatrix(hudCamera.combined);
    }

    @Override
    public void dispose() {
        hudBatch.dispose();
        heartEmpty.dispose();
        heartHalf.dispose();
        heartFull.dispose();
    }
}
