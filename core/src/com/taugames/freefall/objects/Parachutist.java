package com.taugames.freefall.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

import com.taugames.freefall.Game;

public class Parachutist {
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;
    private Model model;
    private float baseVelocity;
    private float velocityDelta;
    private float velocity;
    private Direction direction = Direction.NONE;
    private enum Direction {
        LEFT, NONE, RIGHT
    }

    public Parachutist(Game game) {
        this(
                game.getAssetManager().get("img/parachutist.png", Texture.class),
                7 * Gdx.graphics.getWidth() / 16f,
                119 * Gdx.graphics.getWidth() / 144f,
                Gdx.graphics.getWidth() / 8f,
                Gdx.graphics.getWidth() / 8f,
                3 * Gdx.graphics.getWidth() / 250f,
                Gdx.graphics.getWidth() / 3000f
        );
    }

    public Parachutist(Texture texture, float x, float y, float width, float height, float velocity, float velocityDelta) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        baseVelocity = velocity;
        this.velocityDelta = velocityDelta;
        this.velocity = velocity;
        setModel();
    }

    public void draw(SpriteBatch spriteBatch) {
        if (!spriteBatch.isDrawing()) {
            spriteBatch.begin();
        }
        spriteBatch.draw(texture, x, y, width, height);
    }

    public void moveRight() {
        incrementX(velocity);
    }

    public void moveLeft() {
        decrementX(velocity);
    }

    public void resetVelocity() {
        velocity = baseVelocity;
        direction = Direction.NONE;
    }

    public void incrementX(float x) {
        if (direction == Direction.RIGHT) {
            velocity += velocityDelta;
        } else {
            velocity = baseVelocity;
        }
        direction = Direction.RIGHT;
        if (this.x + x + width / 2 > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - width / 2 - this.x;
            direction = Direction.NONE;
        }
        this.x += x;
        model.changeX(x);
    }

    public void decrementX(float x) {
        if (direction == Direction.LEFT) {
            velocity += velocityDelta;
        } else {
            velocity = baseVelocity;
        }
        direction = Direction.LEFT;
        if (this.x - x + width / 2 < 0) {
            x = this.x + width / 2;
            direction = Direction.NONE;
        }
        this.x -= x;
        model.changeX(-1 * x);
    }

    public void setX(float x) {
        float difference = x - this.x;
        this.x = x;
        model.changeX(difference);
        if (difference != 0) {
            direction = difference < 0 ? Direction.LEFT : Direction.RIGHT;
        } else {
            direction = Direction.NONE;
        }
    }

    public void setY(float y) {
        float difference = y - this.y;
        this.y = y;
        model.changeY(difference);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setModel() {
        float px = width / 32;
        Array<Polygon> polygons = new Array<Polygon>();
        // Feet
        polygons.add(new Polygon(new float[] {
                x + 10 * px, y + 0 * px,
                x + 10 * px, y + 3 * px,
                x + 12 * px, y + 5 * px,
                x + 20 * px, y + 5 * px,
                x + 22 * px, y + 3 * px,
                x + 22 * px, y + 0 * px
        }));
        // Torso
        polygons.add(new Polygon(new float[] {
                x + 12 * px, y + 5 * px,
                x + 12 * px, y + 10 * px,
                x + 20 * px, y + 10 * px,
                x + 20 * px, y + 5 * px
        }));
        // Chest and arms
        polygons.add(new Polygon(new float[] {
                x + 11 * px, y + 10 * px,
                x + 7 * px, y + 11 * px,
                x + 5 * px, y + 12 * px,
                x + 5 * px, y + 16 * px,
                x + 6 * px, y + 17 * px,
                x + 26 * px, y + 17 * px,
                x + 27 * px, y + 16 * px,
                x + 27 * px, y + 12 * px,
                x + 25 * px, y + 11 * px,
                x + 21 * px, y + 10 * px
        }));
        // Parachute strings and head
        polygons.add(new Polygon(new float[] {
                x + 6 * px, y + 17 * px,
                x + 2 * px, y + 21 * px,
                x + 2 * px, y + 22 * px,
                x + 30 * px, y + 22 * px,
                x + 30 * px, y + 21 * px,
                x + 26 * px, y + 17 * px
        }));
        // Parachute
        polygons.add(new Polygon(new float[] {
                x + 2 * px, y + 22 * px,
                x + 2 * px, y + 25 * px,
                x + 3 * px, y + 27 * px,
                x + 5 * px, y + 29 * px,
                x + 8 * px, y + 31 * px,
                x + 12 * px, y + 32 * px,
                x + 20 * px, y + 32 * px,
                x + 24 * px, y + 31 * px,
                x + 27 * px, y + 29 * px,
                x + 29 * px, y + 27 * px,
                x + 30 * px, y + 25 * px,
                x + 30 * px, y + 22 * px
        }));
        model = new Model(polygons);
    }

    public Model getModel() {
        return model;
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
