package com.taugames.freefall.input;

import com.badlogic.gdx.InputAdapter;

public class AnyTouchInputListener extends InputAdapter {
    private boolean touched;

    public AnyTouchInputListener() {
        touched = false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touched = true;
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        touched = true;
        return true;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        touched = false;
        return true;
    }

    public boolean isTouched() {
        return touched;
    }
}
