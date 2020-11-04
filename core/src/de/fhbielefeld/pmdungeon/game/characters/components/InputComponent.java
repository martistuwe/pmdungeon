package de.fhbielefeld.pmdungeon.game.characters.components;

import de.fhbielefeld.pmdungeon.game.characters.Character;

/**
 * Base interface for components who handle inputs and movements
 */
public interface InputComponent {
    void update(Character character);
}
