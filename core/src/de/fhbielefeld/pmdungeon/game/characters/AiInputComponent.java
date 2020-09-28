package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.ai.pfa.GraphPath;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;

public class AiInputComponent implements InputComponent {

    private final GameWorld gameWorld;

    public AiInputComponent(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void update(Character character) {
        GraphPath<Tile> path = gameWorld.getDungeon().findPath(character.currentTile(), gameWorld.getHero().currentTile());
        if (path.getCount() > 1) {
            Tile nextTile = path.get(1);
            for (Tile.Direction direction : character.currentTile().directionTo(nextTile)) {
                switch (direction) {
                    case N:
                        character.moveUp();
                        break;
                    case E:
                        character.moveRight();
                        break;
                    case S:
                        character.moveDown();
                        break;
                    case W:
                        character.moveLeft();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
