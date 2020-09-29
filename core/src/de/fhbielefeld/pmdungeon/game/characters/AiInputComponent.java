package de.fhbielefeld.pmdungeon.game.characters;

import com.badlogic.gdx.ai.pfa.GraphPath;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;
import de.fhbielefeld.pmdungeon.game.items.Weapon;

public class AiInputComponent implements InputComponent {

    private final GameWorld gameWorld;

    public AiInputComponent(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void update(Character character) {
        GraphPath<Tile> path = gameWorld.getDungeon().findPath(character.currentTile(), gameWorld.getHero().currentTile());
        if (path.getCount() > 1 && path.getCount() < character.getAiRadius()) {
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
        Weapon weapon = (Weapon) character.getInventory().getSelectedItem();
        if (path.getCount() <= weapon.getRange()) {
            character.useSelectedItem();
        }
    }
}
