package de.fhbielefeld.pmdungeon.game.characters;

import de.fhbielefeld.pmdungeon.game.inputhandling.Command;
import de.fhbielefeld.pmdungeon.game.inputhandling.InputHandler;

public class PlayerInputComponent implements InputComponent {

    private final InputHandler inputHandler = new InputHandler();

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
