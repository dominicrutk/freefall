package com.taugames.freefall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Game extends com.badlogic.gdx.Game {
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;
    private Settings settings;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        assetManager = new AssetManager();
        settings = new Settings();
        configureLoader();
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

    private void configureLoader() {
        FileHandleResolver fileHandleResolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(fileHandleResolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(fileHandleResolver));
    }

    private void loadAssets() {
        // Fonts
        FreetypeFontLoader.FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        font.fontFileName = "font/PressStart2P.ttf";
        font.fontParameters.size = 32;
        assetManager.load("font/PressStart2P.ttf", BitmapFont.class, font);

        // Icons
        assetManager.load("img/buttons/playButton.png", Texture.class);
        assetManager.load("img/buttons/settingsButton.png", Texture.class);
        assetManager.load("img/buttons/shoppingCartButton.png", Texture.class);

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
}
