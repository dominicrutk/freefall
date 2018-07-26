package com.taugames.freefall.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.taugames.freefall.Game;
import com.taugames.freefall.Model;
import com.taugames.freefall.Parachutist;

public abstract class Obstacle {
    protected final Game game;
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected Array<Model> models;
    private float velocity;
    private boolean scored = false;

    public Obstacle(Game game, float x, float y, float width, float height, float velocity) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }

    /**
     * Draws the obstacle on the screen.
     * @param spriteBatch The SpriteBatch to draw the obstacle texture.
     */
    public abstract void draw(SpriteBatch spriteBatch);

    /**
     * Initializes the models array for collision detection.
     */
    public abstract void setModels();

    /**
     * Determines if the parachutist was killed by the obstacle.
     * @param parachutist The parachutist.
     * @return Whether or not the parachutist collided with the obstacle.
     */
    public boolean kills(Parachutist parachutist) {
        Array<Polygon> obstaclePolygons = new Array<Polygon>();
        for (Model model : models) {
            obstaclePolygons.addAll(model.getPolygons());
        }
        Array<Polygon> parachutistPolygons = parachutist.getModel().getPolygons();
        for (Polygon parachutistPolygon : parachutistPolygons) {
            for (Polygon obstaclePolygon : obstaclePolygons) {
                if (Intersector.overlapConvexPolygons(parachutistPolygon, obstaclePolygon)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if the parachutist passed the obstacle.
     * If he/she did, then the score should be increased.
     * @param parachutist The parachutist.
     * @return Whether or not the parachutist passed the obstacle safely.
     */
    public boolean wasPassedBy(Parachutist parachutist) {
        return parachutist.getY() + parachutist.getHeight() / 2f <= y + height / 2;
    }

    public void drawModels() {
        for (Model model : models) {
            model.draw();
        }
    }

    public void move() {
        y += velocity;
        for (Model model : models) {
            model.changeY(velocity);
        }
    }

    public void setX(float x) {
        float difference = this.x - x;
        this.x = x;
        for (Model model : models) {
            model.changeX(difference);
        }
    }

    public void setY(float y) {
        float difference = this.y - y;
        this.y = y;
        for (Model model : models) {
            model.changeY(difference);
        }
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

    public void score() {
        scored = true;
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

    public boolean isScored() {
        return scored;
    }
}
