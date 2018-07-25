package com.taugames.freefall.obstacles.lasers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.taugames.freefall.Game;
import com.taugames.freefall.Parachutist;
import com.taugames.freefall.obstacles.Obstacle;

public class DoubleLaser extends Obstacle {
    private Texture laserBody;
    private Texture laserEndLeft;
    private Texture laserEndRight;

    public DoubleLaser(Game game, float x, float y, float width, float height, float velocity) {
        super(game, x, y, width, height, velocity);
        laserBody = game.getAssetManager().get("img/lasers/laserBody.png", Texture.class);
        laserEndLeft = game.getAssetManager().get("img/lasers/laserEndLeft.png", Texture.class);
        laserEndRight = game.getAssetManager().get("img/lasers/laserEndRight.png", Texture.class);
    }

    @Override
    public void setModels() {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (!spriteBatch.isDrawing()) {
            spriteBatch.begin();
        }
        spriteBatch.draw(laserEndLeft, x - width / 2 - height, y, height, height);
        spriteBatch.draw(laserEndRight, x + width / 2, y, height, height);
        spriteBatch.draw(laserBody, 0, y, x - width / 2 - height, height);
        spriteBatch.draw(laserBody, x + width / 2 + height, y, Gdx.graphics.getWidth() - (x + width / 2 + height), height);
    }
}
