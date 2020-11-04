package de.fhbielefeld.pmdungeon.game.characters.components;

import de.fhbielefeld.pmdungeon.game.characters.Character;
import de.fhbielefeld.pmdungeon.game.inputhandling.Command;
import de.fhbielefeld.pmdungeon.game.inputhandling.InputHandler;

/**
 * Handles inputs from users. Can be a component of the character class.
 */
public class PlayerInputComponent implements InputComponent {

    private final InputHandler inputHandler = new InputHandler();

    /**
     * Handles the user generated commands and executes them on the given character
     *
     * @param character User controlled character
     */
    @Override
    public void update(Character character) {
        Command[] commands = inputHandler.handleInput();
        for (Command command : commands) {
            if (command != null) {
                command.execute(character);
            }
        }
    }
}
