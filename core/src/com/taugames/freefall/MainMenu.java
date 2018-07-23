package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu implements Screen {
    private final Game game;
    private Texture playButton;
    private Texture settingsButton;
    private Texture shoppingCartButton;
    private SpriteBatch batch;
    private ImageButton playButtonButton;
    private Stage stage;
    private float playLength = Gdx.graphics.getWidth() / 4f;


    public MainMenu(final Game game) {

        this.game = game;
        playButton = game.getAssetManager().get("img/buttons/playButton.png", Texture.class);
        settingsButton = game.getAssetManager().get("img/buttons/settingsButton.png", Texture.class);
        shoppingCartButton = game.getAssetManager().get("img/buttons/shoppingCartButton.png", Texture.class);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        playButtonButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButton)));
        playButtonButton.setSize(playLength, playLength);
        playButtonButton.setPosition((Gdx.graphics.getWidth() - playLength) / 2f, (Gdx.graphics.getHeight() - playLength) / 2f);
        playButtonButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new Infinite(game));
            }
        });

        stage.addActor(playButtonButton);

        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(135f / 255, 206f / 255, 250f / 255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float iconLength = Gdx.graphics.getWidth() / 10f;

        float iconDist = 20 * Gdx.graphics.getWidth() / 1440f;

        stage.act();
        stage.draw();

        batch.begin();

        batch.draw(settingsButton, Gdx.graphics.getWidth() - iconDist - iconLength, Gdx.graphics.getHeight() - iconDist - iconLength, iconLength, iconLength);
        batch.draw(shoppingCartButton, iconDist, Gdx.graphics.getHeight() - iconDist - iconLength, iconLength, iconLength);

        batch.end();
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
