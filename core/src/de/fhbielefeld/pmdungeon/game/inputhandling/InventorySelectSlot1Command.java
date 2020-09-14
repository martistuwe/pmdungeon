package de.fhbielefeld.pmdungeon.game.inputhandling;

import de.fhbielefeld.pmdungeon.game.characters.Character;

public class InventorySelectSlot1Command implements Command {

    @Override
    public void execute(Character character) {
        character.selectItem(0);
    }
}
