package de.fhbielefeld.pmdungeon.game.inputhandling;

import de.fhbielefeld.pmdungeon.game.characters.Character;

/**
 * Base command for player interaction
 */
public interface Command {
    void execute(Character character);
}
