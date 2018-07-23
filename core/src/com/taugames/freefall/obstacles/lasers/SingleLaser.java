package com.taugames.freefall.obstacles.lasers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.taugames.freefall.Game;
import com.taugames.freefall.Parachutist;
import com.taugames.freefall.obstacles.Obstacle;

public class SingleLaser extends Obstacle {
    private Direction direction;
    private Texture laserBody;
    private Texture laserEnd;

    public SingleLaser(Game game, Direction direction, float x, float y, float width, float height, float velocity) {
        super(game, x, y, width, height, velocity);
        this.direction = direction;
        laserBody = game.getAssetManager().get("img/lasers/laserBody.png", Texture.class);
        laserEnd = game.getAssetManager().get("img/lasers/laserEnd" + (direction == Direction.LEFT ? "Left" : "Right") + ".png", Texture.class);
    }

    public enum Direction {
        LEFT, RIGHT
    }

    @Override
    public boolean kills(Parachutist parachutist) {
        Polygon model;
        float px = height / 8;
        if (direction == Direction.LEFT) {
            model = new Polygon(new float[] {
                    0, y + 6 * px,
                    width - 8 * px, y + 6 * px,
                    width - 8 * px, y + 7 * px,
                    width - 6 * px, y + 7 * px,
                    width - 6 * px, y + 8 * px,
                    width - 1 * px, y + 8 * px,
                    width - 1 * px, y + 7 * px,
                    width, y + 7 * px,
                    width, y + 1 * px,
                    width - 1 * px, y + 1 * px,
                    width - 1 * px, y,
                    width - 6 * px, y,
                    width - 6 * px, y + 1 * px,
                    width - 8 * px, y + 1 * px,
                    width - 8 * px, y + 2 * px,
                    0, y + 2 * px
            });
        } else {
            model = new Polygon(new float[] {
                    x + width, y + 6 * px,
                    x + 8 * px, y + 6 * px,
                    x + 8 * px, y + 7 * px,
                    x + 6 * px, y + 7 * px,
                    x + 6 * px, y + 8 * px,
                    x + 1 * px, y + 8 * px,
                    x + 1 * px, y + 7 * px,
                    x, y + 7 * px,
                    x, y + 1 * px,
                    x + 1 * px, y + 1 * px,
                    x + 1 * px, y,
                    x + 6 * px, y,
                    x + 6 * px, y + 1 * px,
                    x + 8 * px, y + 1 * px,
                    x + 8 * px, y + 2 * px,
                    x + width, y + 2 * px
            });
        }
        return Intersector.overlapConvexPolygons(parachutist.getModel(), model);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (!spriteBatch.isDrawing()) {
            spriteBatch.begin();
        }
        if (direction == Direction.LEFT) {
            spriteBatch.draw(laserEnd, x - height, y, height, height);
            spriteBatch.draw(laserBody, 0, y, x - height, height);
        } else {
            spriteBatch.draw(laserEnd, x, y, height, height);
            spriteBatch.draw(laserBody, x + height, y, Gdx.graphics.getWidth() - x - height, height);
        }
    }
}
