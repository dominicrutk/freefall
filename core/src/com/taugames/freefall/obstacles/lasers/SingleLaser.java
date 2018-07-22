package com.taugames.freefall.obstacles.lasers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        return false;
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
