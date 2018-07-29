package com.taugames.freefall.input;

import com.badlogic.gdx.Gdx;

public class RotationInputListener extends InputListener {
    private float tolerance;

    public RotationInputListener() {
        this(1.2f);
    }

    public RotationInputListener(float tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public void updateInputState() {
        // Rotation values seem to range from -10 to 10 where 10 is the screen facing
        // left, 0 is the screen facing up or down, and -10 is the screen facing right.
        float rotation = Gdx.input.getAccelerometerX();
        if (Math.abs(rotation) <= tolerance) {
            inputState = InputState.NONE;
        } else if (rotation > 0) {
            inputState = InputState.LEFT;
        } else {
            inputState = InputState.RIGHT;
        }
    }
}
