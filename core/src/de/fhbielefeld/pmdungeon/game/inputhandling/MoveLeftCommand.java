package de.fhbielefeld.pmdungeon.game.inputhandling;

import de.fhbielefeld.pmdungeon.game.characters.Character;

public class MoveLeftCommand implements Command {

    @Override
    public void execute(Character character) {
        character.moveLeft();
    }
}
