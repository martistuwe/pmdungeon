package de.fhbielefeld.pmdungeon.inputhandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    private static final Command buttonW = new MoveUpCommand();
    private static final Command buttonA = new MoveLeftCommand();
    private static final Command buttonS = new MoveDownCommand();
    private static final Command buttonD = new MoveRightCommand();

    public Command[] handleInput() {
        List<Command> commandList = new ArrayList<>();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) commandList.add(buttonW);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) commandList.add(buttonA);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) commandList.add(buttonS);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) commandList.add(buttonD);

        return commandList.toArray(new Command[0]);
    }
}
