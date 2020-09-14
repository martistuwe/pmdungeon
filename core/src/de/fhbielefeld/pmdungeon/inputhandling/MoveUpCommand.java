package de.fhbielefeld.pmdungeon.inputhandling;

import de.fhbielefeld.pmdungeon.characters.Character;

public class MoveUpCommand implements Command {

    @Override
    public void execute(Character character) {
        character.moveUp();
    }
}
