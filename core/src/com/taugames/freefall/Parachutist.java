package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

public class Parachutist {
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;
    private static final float VELOCITY = 16;

    public Parachutist(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch spriteBatch) {
        if (!spriteBatch.isDrawing()) {
            spriteBatch.begin();
        }
        spriteBatch.draw(texture, x, y, width, height);
    }

    public void moveRight() {
        incrementX(VELOCITY);
    }

    public void moveLeft() {
        decrementX(VELOCITY);
    }

    public void incrementX(float x) {
        this.x += x;
        if (this.x + width / 2 > Gdx.graphics.getWidth()) {
            this.x = Gdx.graphics.getWidth() - width / 2;
        }
    }

    public void decrementX(float x) {
        this.x -= x;
        if (this.x + width / 2 < 0) {
            this.x = -1 * width / 2;
        }
    }

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

    public Polygon getModel() {
        return new Polygon(new float[] {
            // Ordered pairs go here
        });
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
}
