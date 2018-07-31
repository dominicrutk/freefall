package com.taugames.freefall.util.persistent;

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

    // Sensitivity (sensitivity) settings
    public void setSensitivity(float sensitivity) {
        if (sensitivity <= 0 || sensitivity > 1) {
            throw new IllegalArgumentException("The sensitivity is not allowed to be less than or equal to 0 or greater than 1.");
        }
        preferences.putFloat("sensitivity", sensitivity);
        save();
    }

    public float getSensitivity() {
        return preferences.getFloat("sensitivity", 0.5f);
    }

    // Music volume (musicVolume) settings
    public void setMusicVolume(float volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException("The music volume is not allowed to be less than 0 or greater than 1.");
        }
        preferences.putFloat("musicVolume", volume);
        save();
    }

    public float getMusicVolume() {
        return preferences.getFloat("musicVolume", 1);
    }

    // Sound effects volume (soundEffectsVolume) settings
    public void setSoundEffectsVolume(float volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException("The sound effects volume is not allowed to be less than 0 or greater than 1.");
        }
        preferences.putFloat("soundEffectsVolume", volume);
        save();
    }

    public float getSoundEffectsVolume() {
        return preferences.getFloat("soundEffectsVolume", 1);
    }
}
