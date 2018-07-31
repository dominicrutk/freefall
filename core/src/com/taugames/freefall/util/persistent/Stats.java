package com.taugames.freefall.util.persistent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Stats {
    // Preferences abstraction
    private static Preferences stats;

    static {
        stats = Gdx.app.getPreferences("stats");
    }

    private static void save() {
        stats.flush();
    }

    // Total score (totalScore) stats
    public void setTotalScore(long totalScore) {
        stats.putLong("totalScore", totalScore);
        save();
    }

    public long getTotalScore() {
        return stats.getLong("totalScore", 0);
    }

    // High score (highScore) stats
    public void setHighScore(long highScore){
        stats.putLong("highScore", highScore);
        save();
    }

    public long getHighScore() {
        return stats.getLong("highScore", 0);
    }
}
