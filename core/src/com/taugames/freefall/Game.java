package com.taugames.freefall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        assetManager = new AssetManager();
    }

    @Override
    public void render() {
        super.render();
        if (assetManager.update()) {
            this.setScreen(new MainMenu(this));
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        this.getScreen().dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
