package de.fhbielefeld.pmdungeon.inputhandling;

import de.fhbielefeld.pmdungeon.characters.Character;

public class InventorySelectSlot3Command implements Command {

    @Override
    public void execute(Character character) {
        character.selectItem(2);
    }
}
