package com.taugames.freefall.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taugames.freefall.Parachutist;

public abstract class Obstacle {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float velocity = 0;

    /**
     * Determines if the parachutist was killed by the obstacle.
     * @param parachutist The parachutist.
     * @return Whether or not the parachutist collided with the obstacle.
     */
    public abstract boolean kills(Parachutist parachutist);

    /**
     * Determines if the parachutist passed the obstacle.
     * If he/she did, then the score should be increased.
     * @param parachutist The parachutist.
     * @return Whether or not the parachutist passed the obstacle safely.
     */
    public abstract boolean wasPassedBy(Parachutist parachutist);

    /**
     * Draws the obstacle on the screen.
     * @param batch The SpriteBatch to draw the obstacle texture.
     */
    public abstract void draw(SpriteBatch batch);

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getVelocity() {
        return velocity;
    }
}
