package de.fhbielefeld.pmdungeon.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.interactable.Chest;
import de.fhbielefeld.pmdungeon.game.interactable.Interactable;
import de.fhbielefeld.pmdungeon.game.inventory.Inventory;
import de.fhbielefeld.pmdungeon.game.items.Item;

import static de.fhbielefeld.pmdungeon.screens.GameScreen.VIRTUAL_HEIGHT;

public class HeadUpDisplay implements Disposable {

    private static final float HEART_SIZE = 0.4f;
    private static final float ITEM_BACKGROUND_SIZE = 0.5f;
    private static final float INVENTORY_ITEM_SIZE = 0.5f;
    private static final int CHEST_GRID = 3;

    private final GameWorld gameWorld;
    private final SpriteBatch hudBatch;
    private final OrthographicCamera hudCamera;
    private final Texture heartFull = new Texture("textures/ui/ui_heart_full.png");
    private final Texture heartHalf = new Texture("textures/ui/ui_heart_half.png");
    private final Texture heartEmpty = new Texture("textures/ui/ui_heart_empty.png");
    private final Texture background = createBackground();

    public HeadUpDisplay(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        hudBatch = new SpriteBatch();
        hudCamera = new OrthographicCamera();
        hudCamera.position.set(0, 0, 0);
        hudCamera.update();
    }

    public void render() {
        hudCamera.update();
        hudBatch.setProjectionMatrix(hudCamera.combined);

        hudBatch.begin();
        if (gameWorld.getHero() != null) {
            drawHealthPoints();
            drawInventory();
        }
        drawOpenChests();
        hudBatch.end();
    }

    private void drawHealthPoints() {
        int i = 0;
        for (; i < Math.floor(gameWorld.getHero().getHealthPoints()); i++) {
            hudBatch.draw(heartFull, i * HEART_SIZE, VIRTUAL_HEIGHT - HEART_SIZE, HEART_SIZE, HEART_SIZE);
        }
        if (gameWorld.getHero().getHealthPoints() % 1 != 0) {
            hudBatch.draw(heartHalf, i++ * HEART_SIZE, VIRTUAL_HEIGHT - HEART_SIZE, HEART_SIZE, HEART_SIZE);
        }
        for (; i < gameWorld.getHero().getMaxHealthPoints(); i++) {
            hudBatch.draw(heartEmpty, i * HEART_SIZE, VIRTUAL_HEIGHT - HEART_SIZE, HEART_SIZE, HEART_SIZE);
        }
    }

    private void drawInventory() {
        Texture highlight = createInventoryHighlight();
        Inventory inventory = gameWorld.getHero().getInventory();

        float originX = ((VIRTUAL_HEIGHT * Gdx.graphics.getWidth() / Gdx.graphics.getHeight()) / 2) - (inventory.getSize() * ITEM_BACKGROUND_SIZE) / 2;

        Item[] items = inventory.getItems();
        for (int i = 0; i < inventory.getSize(); i++) {
            hudBatch.draw(background, originX + i * ITEM_BACKGROUND_SIZE, 0, ITEM_BACKGROUND_SIZE, ITEM_BACKGROUND_SIZE);
            if (items[i] != null) {
                if (inventory.getSelectedItem() != null && items[i] == inventory.getSelectedItem()) {
                    hudBatch.draw(highlight, originX + i * ITEM_BACKGROUND_SIZE, 0, ITEM_BACKGROUND_SIZE, ITEM_BACKGROUND_SIZE / 12);
                }
                float itemOffset = ITEM_BACKGROUND_SIZE / 2 - calculateItemWidth(items[i]) / 2;
                hudBatch.draw(items[i].getTexture(), (originX + i * INVENTORY_ITEM_SIZE) + itemOffset, 0, calculateItemWidth(items[i]), INVENTORY_ITEM_SIZE);
            }
        }
    }

    private Texture createBackground() {
        Pixmap backgroundPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        backgroundPixmap.setColor(0, 0, 0, 0.3f);
        backgroundPixmap.fill();
        Texture texture = new Texture(backgroundPixmap);
        backgroundPixmap.dispose();
        return texture;
    }

    private Texture createInventoryHighlight() {
        Pixmap highlightPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        highlightPixmap.setColor(0, 0, 1, 0.5f);
        highlightPixmap.fill();
        Texture highlight = new Texture(highlightPixmap);
        highlightPixmap.dispose();
        return highlight;
    }

    private float calculateItemWidth(Item item) {
        return (float) item.getTexture().getWidth() / (float) item.getTexture().getHeight() * INVENTORY_ITEM_SIZE;
    }

    private void drawOpenChests() {
        Chest chest = findOpenChest();
        if (chest != null) {
            float originX = ((VIRTUAL_HEIGHT * Gdx.graphics.getWidth() / Gdx.graphics.getHeight()) / 2) - (CHEST_GRID * ITEM_BACKGROUND_SIZE) / 2;
            float originY = (VIRTUAL_HEIGHT / 2) - (CHEST_GRID * ITEM_BACKGROUND_SIZE) / 2;

            Item[] chestContent = chest.getContent();
            int counter = 0;
            for (int i = CHEST_GRID - 1; i >= 0; i--) {
                for (int j = 0; j < CHEST_GRID; j++) {
                    hudBatch.draw(background, originX + j * ITEM_BACKGROUND_SIZE, originY + i * ITEM_BACKGROUND_SIZE, ITEM_BACKGROUND_SIZE, ITEM_BACKGROUND_SIZE);
                    if (chestContent[counter] != null) {
                        Item item = chestContent[counter];
                        float itemOffset = ITEM_BACKGROUND_SIZE / 2 - calculateItemWidth(item) / 2;
                        hudBatch.draw(item.getTexture(), (originX + j * INVENTORY_ITEM_SIZE) + itemOffset, originY + i * INVENTORY_ITEM_SIZE, INVENTORY_ITEM_SIZE, INVENTORY_ITEM_SIZE);
                    }
                    counter++;
                }
            }
        }
    }

    private Chest findOpenChest() {
        for (Interactable interactable : gameWorld.getInteractables()) {
            if (interactable.getClass() == Chest.class && ((Chest) interactable).getState() == Chest.State.OPEN) {
                return (Chest) interactable;
            }
        }
        return null;
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
        background.dispose();
    }
}
