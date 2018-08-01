package com.taugames.freefall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.taugames.freefall.screens.Loading;
import com.taugames.freefall.util.persistent.Settings;
import com.taugames.freefall.util.persistent.Stats;

public class Game extends com.badlogic.gdx.Game {
    private AssetManager assetManager;
    private Settings settings;
    private Stats stats;
    private SpriteBatch spriteBatch;
    private GlyphLayout glyphLayout;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        assetManager = new AssetManager();
        settings = new Settings();
        stats = new Stats();
        spriteBatch = new SpriteBatch();
        glyphLayout = new GlyphLayout();
        shapeRenderer = new ShapeRenderer();
        queueAssetsToLoad();
        this.setScreen(new Loading(this));
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

    private void queueAssetsToLoad() {
        // Fonts
        assetManager.load("font/small.fnt", BitmapFont.class);
        assetManager.load("font/large.fnt", BitmapFont.class);

        // Icons
        assetManager.load("img/buttons/playButtonGreen.png", Texture.class);
        assetManager.load("img/buttons/settingsButton.png", Texture.class);
        assetManager.load("img/buttons/shopButton.png", Texture.class);
        assetManager.load("img/buttons/playButtonGray.png", Texture.class);
        assetManager.load("img/buttons/pauseButton.png", Texture.class);
        assetManager.load("img/buttons/exitButton.png", Texture.class);

        // Parachutist
        assetManager.load("img/parachutist/parachutistAlive.png", Texture.class);
        assetManager.load("img/parachutist/parachutistDead.png", Texture.class);

        // Lasers
        assetManager.load("img/lasers/laserBody.png", Texture.class);
        assetManager.load("img/lasers/laserEndLeft.png", Texture.class);
        assetManager.load("img/lasers/laserEndRight.png", Texture.class);
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Settings getSettings() {
        return settings;
    }

    public Stats getStats() {
        return stats;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
}
