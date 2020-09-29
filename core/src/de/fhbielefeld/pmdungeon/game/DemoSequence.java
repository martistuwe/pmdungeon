package de.fhbielefeld.pmdungeon.game;

import de.fhbielefeld.pmdungeon.game.dungeon.dungeonconverter.DungeonConverter;

public class DemoSequence {

    private final GameWorld gameWorld;
    private final DungeonConverter dungeonConverter = new DungeonConverter();
    private Stage stage = Stage.A;

    public DemoSequence(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        nextStage();
    }

    public void update() {
        if (gameWorld.isNextLevelTriggered()) nextStage();
    }

    private void nextStage() {
        switch (stage) {
            case A:
                gameWorld.setupDungeon(dungeonConverter.dungeonFromJson("small_dungeon.json"));
                gameWorld.populate();
                stage = Stage.B;
                break;
            case B:
                gameWorld.setupDungeon(dungeonConverter.dungeonFromJson("simple_dungeon.json"));
                gameWorld.populate();
                stage = Stage.C;
                break;
            case C:
                gameWorld.setupDungeon(dungeonConverter.dungeonFromJson("simple_dungeon_2.json"));
                gameWorld.populate();
                gameWorld.setupBoss();
                stage = Stage.D;
                break;
            case D:
                break;
        }

    }

    enum Stage {
        A,
        B,
        C,
        D,
    }
}
