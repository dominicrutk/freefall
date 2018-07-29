package com.taugames.freefall.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class TouchInputListener extends InputListener implements InputProcessor {
    @Override
    public void updateInputState() {}

    @Override
    public boolean keyDown(int keycode) { return false; }

    @Override
    public boolean keyUp(int keycode) { return false; }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(int amount) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        inputState = screenX < Gdx.graphics.getWidth() / 2f ? InputState.LEFT : InputState.RIGHT;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer > 0) {
            return false;
        }
        inputState = screenX < Gdx.graphics.getWidth() / 2f ? InputState.LEFT : InputState.RIGHT;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        inputState = InputState.NONE;
        return true;
    }
}
