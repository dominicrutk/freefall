package com.taugames.freefall.obstacles;

import com.badlogic.gdx.Gdx;
import com.taugames.freefall.Game;
import com.taugames.freefall.obstacles.lasers.DoubleLaser;
import com.taugames.freefall.obstacles.lasers.SingleLaser;

public class RandomObstacleGenerator {
    private final Game game;
    private float velocity;

    public RandomObstacleGenerator(Game game, float velocity) {
        this.game = game;
        this.velocity = velocity;
    }

    public Obstacle getObstacle(float y) {
        return Math.random() >= 0.5 ? getSingleLaser(y) : getDoubleLaser(y);
    }

    public SingleLaser getSingleLaser(float y) {
        SingleLaser.Direction direction = Math.random() >= 0.5 ? SingleLaser.Direction.LEFT : SingleLaser.Direction.RIGHT;
        float gap = (float) Math.random() * (Gdx.graphics.getWidth() / 16f) + (3f / 16) * Gdx.graphics.getWidth();
        float x = direction == SingleLaser.Direction.LEFT ? Gdx.graphics.getWidth() - gap : gap;
        float width = direction == SingleLaser.Direction.LEFT ? x : Gdx.graphics.getWidth() - gap;
        float height = Gdx.graphics.getWidth() / 32f;
        return new SingleLaser(game, direction, x, y, width, height, velocity);
    }

    public DoubleLaser getDoubleLaser(float y) {
        float width = (float) Math.random() * Gdx.graphics.getWidth() / 8f + Gdx.graphics.getWidth() / 4f;
        float height = Gdx.graphics.getWidth() / 32f;
        float x = (float) Math.random() * ((3f/ 4) * Gdx.graphics.getWidth() - width) + Gdx.graphics.getWidth() / 8f + width / 2;
        return new DoubleLaser(game, x, y, width, height, velocity);
    }
}
