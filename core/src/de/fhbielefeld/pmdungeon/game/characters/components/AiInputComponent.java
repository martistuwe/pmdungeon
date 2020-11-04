package de.fhbielefeld.pmdungeon.game.characters.components;

import com.badlogic.gdx.ai.pfa.GraphPath;
import de.fhbielefeld.pmdungeon.game.GameWorld;
import de.fhbielefeld.pmdungeon.game.characters.Character;
import de.fhbielefeld.pmdungeon.game.dungeon.tiles.Tile;
import de.fhbielefeld.pmdungeon.game.items.Weapon;

/**
 * Handles AI input generation. Can be a component of the character class.
 */
public class AiInputComponent implements InputComponent {

    private final GameWorld gameWorld;
    private final int aiRadius;

    public AiInputComponent(GameWorld gameWorld, int aiRadius) {
        this.gameWorld = gameWorld;
        this.aiRadius = aiRadius;
    }

    /**
     * Generates a path for the given character, checks if the player is close enough and evaluates the direction
     *
     * @param character The NPC that should be moved
     */
    @Override
    public void update(Character character) {
        GraphPath<Tile> path = gameWorld.getDungeon().findPath(character.currentTile(), gameWorld.getHero().currentTile());
        if (path.getCount() > 1 && path.getCount() < this.aiRadius) {
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
