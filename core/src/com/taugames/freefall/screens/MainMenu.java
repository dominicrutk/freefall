package com.taugames.freefall.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.taugames.freefall.Game;
import com.taugames.freefall.util.Colors;

public class MainMenu implements Screen {
    private final Game game;
    private float playButtonSize;
    private Stage stage;
    private ArrayMap<Integer, BitmapFont> fonts;

    public MainMenu(final Game game) {
        this.game = game;

        playButtonSize = Gdx.graphics.getWidth() / 4f;
        float menuButtonSize = Gdx.graphics.getWidth() / 10f;
        float menuButtonDistance = Gdx.graphics.getWidth() / 72f;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture playButtonTexture = game.getAssetManager().get("img/buttons/playButton.png", Texture.class);
        Button playButton = new Button(new TextureRegionDrawable(new TextureRegion(playButtonTexture)));
        playButton.setSize(playButtonSize, playButtonSize);
        playButton.setPosition((Gdx.graphics.getWidth() - playButtonSize) / 2f, (Gdx.graphics.getHeight() - playButtonSize) / 2f);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Infinite(game));
            }
        });
        stage.addActor(playButton);

        Texture settingsButtonTexture = game.getAssetManager().get("img/buttons/settingsButton.png", Texture.class);
        Button settingsButton = new Button(new TextureRegionDrawable(new TextureRegion(settingsButtonTexture)));
        settingsButton.setSize(menuButtonSize, menuButtonSize);
        settingsButton.setPosition(menuButtonDistance, Gdx.graphics.getHeight() - menuButtonDistance - menuButtonSize);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("TODO", "Settings - Coming soon!");
            }
        });
        stage.addActor(settingsButton);

        Texture shopButtonTexture = game.getAssetManager().get("img/buttons/shopButton.png", Texture.class);
        Button shopButton = new Button(new TextureRegionDrawable(new TextureRegion(shopButtonTexture)));
        shopButton.setSize(menuButtonSize, menuButtonSize);
        shopButton.setPosition(Gdx.graphics.getWidth() - menuButtonDistance - menuButtonSize, Gdx.graphics.getHeight() - menuButtonDistance - menuButtonSize);
        shopButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("TODO", "Shop - Coming soon!");
            }
        });
        stage.addActor(shopButton);

        fonts = new ArrayMap<Integer, BitmapFont>();
        fonts.put(50, game.getAssetManager().get("font/small.fnt", BitmapFont.class));
        fonts.get(50).setColor(Colors.LIGHT_GRAY);
        fonts.put(100, game.getAssetManager().get("font/large.fnt", BitmapFont.class));
        fonts.get(100).setColor(Colors.LIGHT_GRAY);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135f / 255, 206f / 255, 250f / 255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        String freefall = "Freefall";
        String totalScore = "Total Score: " + Long.toString(game.getStats().getTotalScore());
        String highScore = "High Score: " + Long.toString(game.getStats().getHighScore());

        SpriteBatch spriteBatch = game.getSpriteBatch();
        spriteBatch.begin();
        game.getGlyphLayout().setText(fonts.get(100), freefall);
        fonts.get(100).draw(spriteBatch, freefall, Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, stage.getActors().get(0).getY() + playButtonSize + 150);
        game.getGlyphLayout().setText(fonts.get(50), totalScore);
        fonts.get(50).draw(spriteBatch, totalScore, Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, stage.getActors().get(0).getY() - 75);
        game.getGlyphLayout().setText(fonts.get(50), highScore);
        fonts.get(50).draw(spriteBatch, highScore, Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, stage.getActors().get(0).getY() - 150);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
