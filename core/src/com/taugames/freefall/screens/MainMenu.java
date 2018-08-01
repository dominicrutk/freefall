package com.taugames.freefall.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.taugames.freefall.util.persistent.Settings;

public class MainMenu implements Screen {
    private final Game game;
    private Settings settings;
    private float playButtonSize;
    private float menuButtonSize;
    private float menuButtonDistance;
    private Stage stage;
    private ArrayMap<Integer, BitmapFont> fonts;
    private Notification notification;
    private float notificationTime;
    private static class Notification {
        private String message;
        private Color color;

        public Notification(String message, Color color) {
            this.message = message;
            this.color = color;
        }

        public String getMessage() {
            return message;
        }

        public Color getColor() {
            return color;
        }
    }

    public MainMenu(final Game game) {
        this.game = game;

        settings = new Settings();

        playButtonSize = Gdx.graphics.getWidth() / 4f;
        menuButtonSize = Gdx.graphics.getWidth() / 8f;
        menuButtonDistance = Gdx.graphics.getWidth() / 48f;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture playButtonTexture = game.getAssetManager().get("img/buttons/playButtonGreen.png", Texture.class);
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
                Settings.InputType inputType = settings.getInputType();
                if (inputType == Settings.InputType.TOUCH && settings.rotationInputAvailable()) {
                    settings.setInputType(Settings.InputType.ROTATION);
                    notification = new Notification("Rotation input\nenabled.", Colors.LIGHT_GRAY);
                } else if (inputType == Settings.InputType.TOUCH) {
                    notification = new Notification("Rotation input is\nnot available.", Colors.RED);
                } else {
                    settings.setInputType(Settings.InputType.TOUCH);
                    notification = new Notification("Touch input enabled.", Colors.LIGHT_GRAY);
                }
                notificationTime = 0;
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
                notification = new Notification("Shop coming soon.", Colors.LIGHT_GRAY);
                notificationTime = 0;
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
        Gdx.gl.glClearColor(Colors.SKY_BLUE.r, Colors.SKY_BLUE.g, Colors.SKY_BLUE.b, Colors.SKY_BLUE.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if (notification != null) {
            if (notificationTime <= 2) {
                notificationTime += Gdx.graphics.getDeltaTime();
            } else {
                notification = null;
                notificationTime = 0;
            }
        } else {
            notificationTime = 0;
        }

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
        if (notification != null) {
            fonts.get(50).setColor(notification.getColor());
            float y = Gdx.graphics.getHeight() - menuButtonDistance - menuButtonSize - 50;
            for (String line : notification.getMessage().split("\n")) {
                game.getGlyphLayout().setText(fonts.get(50), line);
                fonts.get(50).draw(spriteBatch, line, Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, y);
                y -= 75;
            }
            fonts.get(50).setColor(Colors.LIGHT_GRAY);
        }
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
