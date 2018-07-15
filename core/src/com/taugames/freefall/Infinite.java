package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.taugames.freefall.input.TouchInputListener;

public class Infinite implements Screen {
    private final Game game;
    private TouchInputListener touchInputListener;
    private Parachutist parachutist;

    public Infinite(Game game) {
        this.game = game;
        touchInputListener = new TouchInputListener();
        Gdx.input.setInputProcessor(touchInputListener);
        TextureRegion parachutistTexture = new TextureRegion(new Texture(Gdx.files.internal("parachutist.png")));
        float parachutistX = Gdx.graphics.getWidth() / 2f - 64;//parachutistTexture.getRegionWidth() / 2f;
        float parachutistY = Gdx.graphics.getHeight() / 2f - 64;//parachutistTexture.getRegionHeight() / 2f;
        parachutist = new Parachutist(parachutistTexture, parachutistX, parachutistY);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135f / 255, 206f / 255, 250f / 255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TouchInputListener.TouchInputState touchInputState = touchInputListener.getInputState();
        if (touchInputState == TouchInputListener.TouchInputState.LEFT) {
            parachutist.setX(parachutist.getX() - 10);
        } else if (touchInputState == TouchInputListener.TouchInputState.RIGHT) {
            parachutist.setX(parachutist.getX() + 10);
        }
        SpriteBatch spriteBatch = game.getSpriteBatch();
        spriteBatch.begin();
        parachutist.draw(spriteBatch);
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
