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

    private static final Command buttonMouseLeft = new UseSelectedItemCommand();

    private static final Command buttonNum1 = new InventorySelectSlot1Command();
    private static final Command buttonNum2 = new InventorySelectSlot2Command();
    private static final Command buttonNum3 = new InventorySelectSlot3Command();

    public Command[] handleInput() {
        List<Command> commandList = new ArrayList<>();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) commandList.add(buttonW);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) commandList.add(buttonA);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) commandList.add(buttonS);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) commandList.add(buttonD);

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) commandList.add(buttonMouseLeft);

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) commandList.add(buttonNum1);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) commandList.add(buttonNum2);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) commandList.add(buttonNum3);

        return commandList.toArray(new Command[0]);
    }
}
