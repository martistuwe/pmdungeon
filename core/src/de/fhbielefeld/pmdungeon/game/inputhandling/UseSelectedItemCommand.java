package de.fhbielefeld.pmdungeon.game.inputhandling;

import de.fhbielefeld.pmdungeon.game.characters.Character;

public class UseSelectedItemCommand implements Command {

    @Override
    public void execute(Character character) {
        character.useSelectedItem();
    }
}
