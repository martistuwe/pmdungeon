package de.fhbielefeld.pmdungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import de.fhbielefeld.pmdungeon.game.characters.Character;
import de.fhbielefeld.pmdungeon.game.characters.Imp;
import de.fhbielefeld.pmdungeon.game.characters.MaleKnight;
import de.fhbielefeld.pmdungeon.game.chest.Chest;
import de.fhbielefeld.pmdungeon.game.dungeon.Dungeon;
import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.DungeonConverter;
import de.fhbielefeld.pmdungeon.game.inputhandling.Command;
import de.fhbielefeld.pmdungeon.game.inputhandling.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class GameWorld implements Disposable {

    private final SpriteBatch batch;
    private final List<Character> characterList = new ArrayList<>();
    private final List<Chest> chestList = new ArrayList<>();
    private final InputHandler inputHandler = new InputHandler();
    private Dungeon dungeon;
    private Character hero;

    public GameWorld(SpriteBatch batch) {
        this.batch = batch;

        setupDungeon();
        setupCharacters();
        setupLoot();
    }

    private void setupDungeon() {
        DungeonConverter dungeonConverter = new DungeonConverter();
        dungeon = dungeonConverter.dungeonFromJson("simple_dungeon.json");
    }

    private void setupCharacters() {
        hero = new MaleKnight(this);
        hero.setPosition(dungeon.getStartingPoint());
        characterList.add(hero);

        Character imp = new Imp(this);
        imp.setPosition(dungeon.getRandomPointInDungeon());
        characterList.add(imp);
    }

    private void setupLoot() {
        chestList.add(new Chest(dungeon.getRandomPointInDungeon()));
    }

    public void update() {
        Command[] commands = inputHandler.handleInput();
        if (commands.length == 0) hero.setIdle(true);
        for (Command command : commands) {
            if (command != null) {
                hero.setIdle(false);
                command.execute(hero);
            }
        }
        for (Chest chest : chestList) {
            chest.update();
        }
        for (Character character : characterList) {
            character.update();
        }
    }

    public void render() {
        dungeon.renderFloor(batch);
        for (Chest chest : chestList) {
            chest.render(batch);
        }
        for (Character character : characterList) {
            character.render();
        }
        dungeon.renderWalls(batch);
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

    @Override
    public void dispose() {
        dungeon.dispose();
        for (Character character : characterList) {
            character.dispose();
        }
    }
}
