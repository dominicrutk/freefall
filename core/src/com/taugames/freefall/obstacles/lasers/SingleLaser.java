package com.taugames.freefall.obstacles.lasers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.taugames.freefall.Game;
import com.taugames.freefall.Model;
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
        setModels();
    }

    public enum Direction {
        LEFT, RIGHT
    }

    @Override
    public void setModels() {
        models = new Array<Model>();
        Array<Polygon> polygons = new Array<Polygon>();
        float px = height / 8;
        if (direction == Direction.LEFT) {
            polygons.add(new Polygon(new float[] {
                    0, y + 2 * px,
                    width - 8 * px, y + 2 * px,
                    width - 8 * px, y + height - 2 * px,
                    0, y + height - 2 * px
            }));
            polygons.add(new Polygon(new float[] {
                    width - 8 * px, y + 2 * px,
                    width - 8 * px, y + px,
                    width - 6 * px, y,
                    width - px, y,
                    width, y + px,
                    width, y + height - px,
                    width - px, y + height,
                    width - 6 * px, y + height,
                    width - 8 * px, y + height - px,
                    width - 8 * px, y + height - 2 * px
            }));
        } else {
            polygons.add(new Polygon(new float[] {
                    x + width, y + 2 * px,
                    x + 8 * px, y + 2 * px,
                    x + 8 * px, y + height - 2 * px,
                    x + width, y + height - 2 * px
            }));
            polygons.add(new Polygon(new float[] {
                    x + 8 * px, y + 2 * px,
                    x + 8 * px, y + px,
                    x + 6 * px, y,
                    x + px, y,
                    x, y + px,
                    x, y + height - px,
                    x + px, y + height,
                    x + 6 * px, y + height,
                    x + 8 * px, y + height - px,
                    x + 8 * px, y + height - 2 * px
            }));
        }
        models.add(new Model(polygons));
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
