package com.taugames.freefall.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ShortArray;
import com.taugames.freefall.Game;
import com.taugames.freefall.Parachutist;

public abstract class Obstacle {
    protected final Game game;
    protected float x;
    protected float y;
    protected float width;
    protected float height;
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
     * Determines if the parachutist was killed by the obstacle.
     * @param parachutist The parachutist.
     * @return Whether or not the parachutist collided with the obstacle.
     */
    public abstract boolean kills(Parachutist parachutist);

    /**
     * Draws the obstacle on the screen.
     * @param spriteBatch The SpriteBatch to draw the obstacle texture.
     */
    public abstract void draw(SpriteBatch spriteBatch);

    /**
     * Determines if the parachutist passed the obstacle.
     * If he/she did, then the score should be increased.
     * @param parachutist The parachutist.
     * @return Whether or not the parachutist passed the obstacle safely.
     */
    public boolean wasPassedBy(Parachutist parachutist) {
        return parachutist.getY() + parachutist.getHeight() / 2f <= y + height / 2;
    }

    public void move() {
        y += velocity;
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

    // Triangulation for concave polygon collision detection
    private static EarClippingTriangulator triangulator;

    static {
        triangulator = new EarClippingTriangulator();
    }

    // SOURCE: https://stackoverflow.com/a/42319276/7300063
    protected static Array<Polygon> triangulate(Polygon polygon) {
        float[] vertices = polygon.getTransformedVertices();
        ShortArray triangleVertexIndices = triangulator.computeTriangles(vertices);
        Array<Polygon> triangles = new Array<Polygon>();
        for (int i = 0; i < triangleVertexIndices.size; i += 3) {
            triangles.add(new Polygon(new float[] {
                vertices[triangleVertexIndices.get(i) * 2], vertices[triangleVertexIndices.get(i) * 2 + 1],
                vertices[triangleVertexIndices.get(i + 1) * 2], vertices[triangleVertexIndices.get(i + 1) * 2 + 1],
                vertices[triangleVertexIndices.get(i + 2) * 2], vertices[triangleVertexIndices.get(i + 2) * 2 + 1]
            }));
        }
        return triangles;
    }
}
