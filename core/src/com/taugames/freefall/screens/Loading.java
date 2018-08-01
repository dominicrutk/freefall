package com.taugames.freefall.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.taugames.freefall.Game;
import com.taugames.freefall.util.Colors;

public class Loading implements Screen {
    private final Game game;
    private AssetManager assetManager;
    private ShapeRenderer shapeRenderer;
    private float x;
    private float y;
    private float width;
    private float height;

    public Loading(final Game game) {
        this.game = game;
        assetManager = game.getAssetManager();
        shapeRenderer = game.getShapeRenderer();
        shapeRenderer.setColor(Colors.WHITE);
        x = Gdx.graphics.getWidth() / 4f;
        y = Gdx.graphics.getHeight() / 2f - 75;
        width = Gdx.graphics.getWidth() / 2f;
        height = 150;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Colors.BLACK.r, Colors.BLACK.g, Colors.BLACK.b, Colors.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (assetManager.update()) {
            game.setScreen(new MainMenu(game));
        }

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.rect(x, y, assetManager.getProgress() * width, height);
        shapeRenderer.end();
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
