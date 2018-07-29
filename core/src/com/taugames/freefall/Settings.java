package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
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

    public void setInputType(InputType inputType) {
        preferences.putString("inputType", inputType.name());
        save();
    }

    public InputType getInputType() {
        return InputType.valueOf(preferences.getString("inputType", "TOUCH"));
    }
}
