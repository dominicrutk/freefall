package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen {
    private final Game game;
    private Texture playButton;
    private Texture settingsButton;
    private Texture shoppingCartButton;
    private SpriteBatch batch;


    public MainMenu(Game game) {

        this.game = game;
        playButton = game.getAssetManager().get("img/buttons/playButton.png", Texture.class);
        settingsButton = game.getAssetManager().get("img/buttons/settingsButton.png", Texture.class);
        shoppingCartButton = game.getAssetManager().get("img/buttons/shoppingCartButton.png", Texture.class);

        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135f / 255, 206f / 255, 250f / 255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float playLength = Gdx.graphics.getWidth() / 4f;
        float iconLength = Gdx.graphics.getWidth() / 10f;

        float iconDist = 20 * Gdx.graphics.getWidth() / 1440f;

        batch.begin();

        batch.draw(playButton, (Gdx.graphics.getWidth() - playLength) / 2f, (Gdx.graphics.getHeight() - playLength) / 2f, playLength, playLength);
        batch.draw(settingsButton, Gdx.graphics.getWidth() - iconDist - iconLength, Gdx.graphics.getHeight() - iconDist - iconLength, iconLength, iconLength);
        batch.draw(shoppingCartButton, iconDist, Gdx.graphics.getHeight() - iconDist - iconLength, iconLength, iconLength);

        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new Infinite(game));
        }
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
