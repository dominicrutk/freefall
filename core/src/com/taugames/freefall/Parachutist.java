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
        float px = width / 32;
        return new Polygon(new float[] {
            x + 15 * px, y + 2 * px,
            x + 15 * px, y + 0 * px,
            x + 10 * px, y + 0 * px,
            x + 10 * px, y + 3 * px,
            x + 11 * px, y + 3 * px,
            x + 11 * px, y + 4 * px,
            x + 12 * px, y + 4 * px,
            x + 12 * px, y + 10 * px,
            x + 11 * px, y + 10 * px,
            x + 11 * px, y + 11 * px,
            x + 7 * px, y + 11 * px,
            x + 7 * px, y + 12 * px,
            x + 5 * px, y + 12 * px,
            x + 5 * px, y + 16 * px,
            x + 6 * px, y + 16 * px,
            x + 6 * px, y + 18 * px,
            x + 5 * px, y + 18 * px,
            x + 5 * px, y + 19 * px,
            x + 4 * px, y + 19 * px,
            x + 4 * px, y + 20 * px,
            x + 3 * px, y + 20 * px,
            x + 3 * px, y + 21 * px,
            x + 2 * px, y + 21 * px,
            x + 2 * px, y + 25 * px,
            x + 3 * px, y + 25 * px,
            x + 3 * px, y + 27 * px,
            x + 4 * px, y + 27 * px,
            x + 4 * px, y + 28 * px,
            x + 5 * px, y + 28 * px,
            x + 5 * px, y + 29 * px,
            x + 7 * px, y + 29 * px,
            x + 7 * px, y + 30 * px,
            x + 8 * px, y + 30 * px,
            x + 8 * px, y + 31 * px,
            x + 12 * px, y + 31 * px,
            x + 12 * px, y + 32 * px,
            x + 20 * px, y + 32 * px,
            x + 20 * px, y + 31 * px,
            x + 24 * px, y + 31 * px,
            x + 24 * px, y + 30 * px,
            x + 25 * px, y + 30 * px,
            x + 25 * px, y + 29 * px,
            x + 27 * px, y + 29 * px,
            x + 27 * px, y + 28 * px,
            x + 28 * px, y + 28 * px,
            x + 28 * px, y + 27 * px,
            x + 29 * px, y + 27 * px,
            x + 29 * px, y + 25 * px,
            x + 30 * px, y + 25 * px,
            x + 30 * px, y + 21 * px,
            x + 29 * px, y + 21 * px,
            x + 29 * px, y + 20 * px,
            x + 28 * px, y + 20 * px,
            x + 28 * px, y + 19 * px,
            x + 27 * px, y + 19 * px,
            x + 27 * px, y + 18 * px,
            x + 26 * px, y + 18 * px,
            x + 26 * px, y + 16 * px,
            x + 27 * px, y + 16 * px,
            x + 27 * px, y + 12 * px,
            x + 25 * px, y + 12 * px,
            x + 25 * px, y + 11 * px,
            x + 21 * px, y + 11 * px,
            x + 21 * px, y + 10 * px,
            x + 20 * px, y + 10 * px,
            x + 20 * px, y + 4 * px,
            x + 21 * px, y + 4 * px,
            x + 21 * px, y + 3 * px,
            x + 22 * px, y + 3 * px,
            x + 22 * px, y + 0 * px,
            x + 17 * px, y + 0 * px,
            x + 17 * px, y + 2 * px
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
