package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;

public class Settings {
    // Preferences abstraction
    private static Preferences preferences;

    static {
        preferences = Gdx.app.getPreferences("preferences");
    }

    private static void save() {
        preferences.flush();
    }

    // Input type (inputType) settings
    public enum InputType {
        TOUCH, ROTATION
    }
    
    public boolean rotationInputAvailable() {
        return Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    public void setInputType(InputType inputType) {
        if (inputType == InputType.ROTATION && rotationInputAvailable()) {
            preferences.putString("inputType", "ROTATION");
        } else if (inputType == InputType.ROTATION) {
            throw new IllegalArgumentException("The accelerometer is not available so rotation input cannot be used.");
        } else {
            preferences.putString("inputType", "TOUCH");
        }
        save();
    }

    public InputType getInputType() {
        return InputType.valueOf(preferences.getString("inputType", "TOUCH"));
    }
}
