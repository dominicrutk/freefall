package com.taugames.freefall.input;

public abstract class InputListener {
    protected InputState inputState;

    public abstract void updateInputState();

    public InputState getInputState() {
        return inputState;
    }
}
