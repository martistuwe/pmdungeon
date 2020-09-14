package de.fhbielefeld.pmdungeon.inputhandling;

import de.fhbielefeld.pmdungeon.characters.Character;

public interface Command {
    void execute(Character character);
}
