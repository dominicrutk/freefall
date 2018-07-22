package com.taugames.freefall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        assetManager = new AssetManager();
        loadAssets();
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        this.getScreen().dispose();
    }

    private void loadAssets() {
        assetManager.load("img/parachutist.png", Texture.class);
        assetManager.load("img/lasers/laserBody.png", Texture.class);
        assetManager.load("img/lasers/laserEndLeft.png", Texture.class);
        assetManager.load("img/lasers/laserEndRight.png", Texture.class);
        assetManager.finishLoading();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
