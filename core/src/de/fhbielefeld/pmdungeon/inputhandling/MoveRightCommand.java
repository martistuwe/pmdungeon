package de.fhbielefeld.pmdungeon.inputhandling;

import de.fhbielefeld.pmdungeon.characters.Character;

public class MoveRightCommand implements Command {

    @Override
    public void execute(Character character) {
        character.moveRight();
    }
}
