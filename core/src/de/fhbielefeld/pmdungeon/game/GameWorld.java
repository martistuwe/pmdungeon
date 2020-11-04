package de.fhbielefeld.pmdungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.BigDemon;
import de.fhbielefeld.pmdungeon.game.characters.Character;
import de.fhbielefeld.pmdungeon.game.characters.Imp;
import de.fhbielefeld.pmdungeon.game.characters.MaleKnight;
import de.fhbielefeld.pmdungeon.game.characters.components.AiInputComponent;
import de.fhbielefeld.pmdungeon.game.characters.components.PlayerInputComponent;
import de.fhbielefeld.pmdungeon.game.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.game.interactable.Chest;
import de.fhbielefeld.pmdungeon.game.interactable.Interactable;
import de.fhbielefeld.pmdungeon.game.items.HealthPotion;
import de.fhbielefeld.pmdungeon.game.items.NpcAttack;
import de.fhbielefeld.pmdungeon.game.items.Sword;

import java.util.ArrayList;
import java.util.List;

/**
 * This is where all the strings run together. Handles every character, interactable und the dungeon itself.
 */
public class GameWorld implements Disposable {

    private final SpriteBatch batch;
    private final List<Character> characterList = new ArrayList<>();
    private final List<Interactable> interactables = new ArrayList<>();
    private Dungeon dungeon;
    private Character hero;
    private boolean nextLevelTriggered = false;

    public GameWorld(SpriteBatch batch) {
        this.batch = batch;
        setupHero();
    }

    /**
     * Setting up a new dungeon. Gets also called when the level changes and the dungeon gets replaced.
     *
     * @param dungeon new Dungeon
     */
    public void setupDungeon(Dungeon dungeon) {
        if (this.dungeon != null) this.dungeon.dispose();
        this.dungeon = dungeon;
        this.nextLevelTriggered = false;
        dungeon.makeConnections();
    }

    /**
     * Adds characters and loot to the dungeon.
     */
    public void populate() {
        characterList.clear();
        hero.setPosition(dungeon.getStartingLocation());
        characterList.add(hero);
        for (int i = 1; i < dungeon.getRooms().length; i += 2) {
            Character imp = new Imp(this, new AiInputComponent(this, 6));
            imp.setPosition(dungeon.getRandomLocationInDungeon());
            imp.getInventory().add(new NpcAttack(1, 0.5f, 300));
            characterList.add(imp);
        }
        setupLoot();
    }

    /**
     * Sets up the boss, if there should be one in the current level.
     */
    public void setupBoss() {
        Character bigDemon = new BigDemon(this, new AiInputComponent(this, 10));
        bigDemon.setPosition(dungeon.getBossStartingLocation());
        bigDemon.getInventory().add(new NpcAttack(1, 2, 750));
        characterList.add(bigDemon);
    }

    /**
     * Setting up the playable character.
     */
    private void setupHero() {
        hero = new MaleKnight(this, new PlayerInputComponent());
        hero.getInventory().add(new Sword());
        hero.getInventory().add(new HealthPotion());
    }

    /**
     * Hiding a chest with loot in the dungeon.
     */
    private void setupLoot() {
        interactables.clear();
        interactables.add(new Chest(dungeon.getRandomLocationInDungeon()));
    }

    /**
     * Part of the gameloop. Updates everything in the dungeon, that needs to be updated.
     */
    public void update() {
        if (hero.currentTile() == dungeon.getNextLevelTrigger()) nextLevelTriggered = true;
        for (Interactable interactable : interactables) {
            interactable.update();
        }
        List<Character> deadCharacters = new ArrayList<>();
        for (Character character : characterList) {
            character.update();
            if (character.isDead()) deadCharacters.add(character);
        }
        characterList.removeAll(deadCharacters);
    }

    /**
     * Part of the gameloop. Renders everything in the dungeon and the dungeon itself.
     */
    public void render() {
        dungeon.renderFloor(batch);
        for (Interactable interactable : interactables) {
            interactable.render(batch);
        }
        dungeon.renderWalls(dungeon.getHeight() - 1, (int) getHero().getPositionY(), batch);
        for (Character character : characterList) {
            character.render();
        }
        dungeon.renderWalls((int) getHero().getPositionY(), 0, batch);
    }

    /**
     * Methode for finding the nearest interactable in the dungeon, from a given character.
     *
     * @param character Character who looks for an interactable
     * @return Nearest interactable to the character
     */
    public Interactable nearestInteractable(Character character) {
        float minDistance = Float.MAX_VALUE;
        Interactable returnInteractable = null;
        for (Interactable interactable : interactables) {
            float distance = character.distanceBetween(interactable.getPositionX(), interactable.getPositionY());
            if (minDistance > distance) {
                minDistance = distance;
                returnInteractable = interactable;
            }
        }
        return returnInteractable;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Character getHero() {
        return hero;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public List<Interactable> getInteractables() {
        return interactables;
    }

    public boolean isNextLevelTriggered() {
        return nextLevelTriggered;
    }

    @Override
    public void dispose() {
        dungeon.dispose();
        for (Character character : characterList) {
            character.dispose();
        }
        for (Interactable interactable : interactables) {
            interactable.dispose();
        }
    }
}
