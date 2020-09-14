package de.fhbielefeld.pmdungeon.inputhandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {

    private static final Command buttonW = new MoveUpCommand();
    private static final Command buttonA = new MoveLeftCommand();
    private static final Command buttonS = new MoveDownCommand();
    private static final Command buttonD = new MoveRightCommand();

    public Command handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) return buttonW;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) return buttonA;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) return buttonS;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) return buttonD;

        return null;
    }
}
