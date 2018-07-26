package com.taugames.freefall.obstacles.lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.taugames.freefall.Game;
import com.taugames.freefall.Model;
import com.taugames.freefall.Parachutist;
import com.taugames.freefall.obstacles.Obstacle;

public class SplitLaser extends Obstacle {
    private Texture laserBody;
    private Texture laserEndRight;
    private Texture laserEndLeft;

    public SplitLaser(Game game, float x, float y, float width, float height, float velocity) {
        super(game, x, y, width, height, velocity);
        laserBody = game.getAssetManager().get("img/lasers/laserBody.png", Texture.class);
        laserEndRight = game.getAssetManager().get("img/lasers/laserEndRight.png", Texture.class);
        laserEndLeft = game.getAssetManager().get("img/lasers/laserEndLeft.png", Texture.class);
        setModels();
    }

    @Override
    public void setModels() {
        models = new Array<Model>();
        Array<Polygon> polygons = new Array<Polygon>();
        float px = height / 8;
        // Right laser end
        polygons.add(new Polygon(new float[] {
                x + 8 * px, y + px,
                x + 6 * px, y,
                x + px, y,
                x, y + px,
                x, y + height - px,
                x + px, y + height,
                x + 6 * px, y + height,
                x + 8 * px, y + height - px
        }));
        // Laser body
        polygons.add(new Polygon(new float[] {
                x + height, y + 2 * px,
                x + height, y + height - 2 * px,
                x + width - height, y + height - 2 * px,
                x + width - height, y + 2 * px
        }));
        // Left laser end
        polygons.add(new Polygon(new float[] {
                x + width - height, y + px,
                x + width - height + 2 * px, y,
                x + width - px, y,
                x + width, y + px,
                x + width, y + height - px,
                x + width - px, y + height,
                x + width - height + 2 * px, y + height,
                x + width - height, y + height - px
        }));
        models.add(new Model(polygons));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (!spriteBatch.isDrawing()) {
            spriteBatch.begin();
        }
        spriteBatch.draw(laserEndRight, x, y, height, height);
        spriteBatch.draw(laserBody, x + height, y, width - 2 * height, height);
        spriteBatch.draw(laserEndLeft, x + width - height, y, height, height);
    }
}
