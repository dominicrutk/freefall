package com.taugames.freefall.obstacles;

import com.badlogic.gdx.Gdx;
import com.taugames.freefall.Game;
import com.taugames.freefall.obstacles.lasers.SingleLaser;

public class RandomObstacleGenerator {
    private final Game game;
    private float velocity;

    public RandomObstacleGenerator(Game game, float velocity) {
        this.game = game;
        this.velocity = velocity;
    }

    public Obstacle getObstacle(float y) {
        return getSingleLaser(y);
    }

    public SingleLaser getSingleLaser(float y) {
        SingleLaser.Direction direction = Math.random() > 0.5 ? SingleLaser.Direction.LEFT : SingleLaser.Direction.RIGHT;
        float gap = (float) Math.random() * (Gdx.graphics.getWidth() / 16f) + (3f / 16) * Gdx.graphics.getWidth();
        float x = direction == SingleLaser.Direction.LEFT ? Gdx.graphics.getWidth() - gap : gap;
        float width = direction == SingleLaser.Direction.LEFT ? x : Gdx.graphics.getWidth() - gap;
        float height = Gdx.graphics.getWidth() / 32f;
        return new SingleLaser(game, direction, x, y, width, height, velocity);
    }
}
