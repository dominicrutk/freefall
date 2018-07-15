package com.taugames.freefall.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class TouchInputListener extends InputAdapter {
    private TouchInputState inputState = TouchInputState.NONE;
    public enum TouchInputState {
        NONE, LEFT, RIGHT
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        inputState = x < Gdx.graphics.getWidth() / 2f ? TouchInputState.LEFT : TouchInputState.RIGHT;
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (pointer > 0) {
            return false;
        }
        inputState = x < Gdx.graphics.getWidth() / 2f ? TouchInputState.LEFT : TouchInputState.RIGHT;
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        inputState = TouchInputState.NONE;
        return true;
    }

    public TouchInputState getInputState() {
        return inputState;
    }
}
