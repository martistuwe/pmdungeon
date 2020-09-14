package de.fhbielefeld.pmdungeon.game.inputhandling;

import de.fhbielefeld.pmdungeon.game.characters.Character;

public class MoveRightCommand implements Command {

    @Override
    public void execute(Character character) {
        character.moveRight();
    }
}
