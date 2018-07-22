package com.taugames.freefall.obstacles.lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taugames.freefall.Game;
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
        spriteBatch.draw(laserEndRight, x, y, height, height);
        spriteBatch.draw(laserBody, x + height, y, width - 2 * height, height);
        spriteBatch.draw(laserEndLeft, x + width - height, y, height, height);
    }
}
