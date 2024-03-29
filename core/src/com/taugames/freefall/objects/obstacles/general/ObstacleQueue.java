package com.taugames.freefall.objects.obstacles.general;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.taugames.freefall.objects.Parachutist;

public class ObstacleQueue {
    private RandomObstacleGenerator obstacleGenerator;
    private Array<Obstacle> obstacles;
    private float obstacleGap;

    public ObstacleQueue(RandomObstacleGenerator obstacleGenerator, float obstacleGap) {
        this.obstacleGenerator = obstacleGenerator;
        obstacles = new Array<Obstacle>();
        this.obstacleGap = obstacleGap;
        obstacles.add(obstacleGenerator.getObstacle(-1 * obstacleGap));
    }

    public void updateObstacles() {
        moveObstacles();
        removeObstacles();
        addObstacles();
    }

    public void moveObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.move();
        }
    }

    public void removeObstacles() {
        while (obstacles.get(0).getY() > Gdx.graphics.getHeight()) {
            obstacles.removeIndex(0);
        }
    }

    public void addObstacles() {
        while (obstacles.peek().getY() >= 0) {
            Obstacle next = obstacleGenerator.getObstacle(obstacles.peek().getY() - obstacleGap);
            next.setY(next.getY() - next.getHeight());
            obstacles.add(next);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(spriteBatch);
        }
    }

    public void drawModels() {
        for (Obstacle obstacle : obstacles) {
            obstacle.drawModels();
        }
    }

    public boolean kills(Parachutist parachutist) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getY() > parachutist.getY() + parachutist.getHeight()) {
                // Obstacle above parachutist
            } else if (obstacle.getY() + obstacle.getHeight() < parachutist.getY()) {
                // Obstacle under parachutist
            } else {
                // Obstacle near parachutist
                if (obstacle.kills(parachutist)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int pointsToAdd(Parachutist parachutist) {
        int pointsToAdd = 0;
        for (Obstacle obstacle : obstacles) {
            if (obstacle.wasPassedBy(parachutist) && !obstacle.isScored()) {
                pointsToAdd++;
                obstacle.score();
            }
        }
        return pointsToAdd;
    }
}
