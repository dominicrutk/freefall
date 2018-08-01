package com.taugames.freefall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taugames.freefall.screens.MainMenu;

import com.taugames.freefall.util.persistent.Settings;
import com.taugames.freefall.util.persistent.Stats;

public class Game extends com.badlogic.gdx.Game {
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;
    private Settings settings;
    private Stats stats;
    private GlyphLayout glyphLayout;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        assetManager = new AssetManager();
        settings = new Settings();
        stats = new Stats();
        glyphLayout = new GlyphLayout();
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
        // Fonts
        assetManager.load("font/small.fnt", BitmapFont.class);
        assetManager.load("font/large.fnt", BitmapFont.class);

        // Icons
        assetManager.load("img/buttons/playButton.png", Texture.class);
        assetManager.load("img/buttons/settingsButton.png", Texture.class);
        assetManager.load("img/buttons/shopButton.png", Texture.class);

        // Parachutist
        assetManager.load("img/parachutist.png", Texture.class);

        // Lasers
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

    public Settings getSettings() {
        return settings;
    }

    public Stats getStats() {
        return stats;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }
}
