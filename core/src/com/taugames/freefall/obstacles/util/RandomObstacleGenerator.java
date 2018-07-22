package com.taugames.freefall.obstacles.util;

import com.badlogic.gdx.Gdx;
import com.taugames.freefall.Game;
import com.taugames.freefall.obstacles.Obstacle;
import com.taugames.freefall.obstacles.lasers.DoubleLaser;
import com.taugames.freefall.obstacles.lasers.SingleLaser;
import com.taugames.freefall.obstacles.lasers.SplitLaser;

public class RandomObstacleGenerator {
    private final Game game;
    private float velocity;

    public RandomObstacleGenerator(Game game, float velocity) {
        this.game = game;
        this.velocity = velocity;
    }

    public Obstacle getObstacle(float y) {
        double random = Math.random();
        if (random < 1f / 3) return getSingleLaser(y);
        else if (random < 2f / 3) return getDoubleLaser(y);
        else return getSplitLaser(y);
    }

    public SingleLaser getSingleLaser(float y) {
        SingleLaser.Direction direction = Math.random() >= 0.5 ? SingleLaser.Direction.LEFT : SingleLaser.Direction.RIGHT;
        float gap = (float) Math.random() * Gdx.graphics.getWidth() / 16f + 3f / 16 * Gdx.graphics.getWidth();
        float x = direction == SingleLaser.Direction.LEFT ? Gdx.graphics.getWidth() - gap : gap;
        float width = direction == SingleLaser.Direction.LEFT ? x : Gdx.graphics.getWidth() - gap;
        float height = Gdx.graphics.getWidth() / 32f;
        return new SingleLaser(game, direction, x, y, width, height, velocity);
    }

    public DoubleLaser getDoubleLaser(float y) {
        float width = (float) Math.random() * Gdx.graphics.getWidth() / 8f + Gdx.graphics.getWidth() / 4f;
        float height = Gdx.graphics.getWidth() / 32f;
        float x = (float) Math.random() * (3f / 4 * Gdx.graphics.getWidth() - width) + Gdx.graphics.getWidth() / 8f + width / 2;
        return new DoubleLaser(game, x, y, width, height, velocity);
    }

    public SplitLaser getSplitLaser(float y) {
        float leftGap = (float) Math.random() * 3f / 80 * Gdx.graphics.getWidth() + (11f / 80) * Gdx.graphics.getWidth();
        float rightGap = (float) Math.random() * 3f / 80 * Gdx.graphics.getWidth() + (11f / 80) * Gdx.graphics.getWidth();
        float width = Gdx.graphics.getWidth() - leftGap - rightGap;
        float height = Gdx.graphics.getWidth() / 32f;
        return new SplitLaser(game, leftGap, y, width, height, velocity);
    }
}
