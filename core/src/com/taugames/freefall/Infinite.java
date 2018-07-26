package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taugames.freefall.input.TouchInputListener;
import com.taugames.freefall.obstacles.util.ObstacleQueue;
import com.taugames.freefall.obstacles.util.RandomObstacleGenerator;

public class Infinite implements Screen {
    private final Game game;
    private TouchInputListener touchInputListener;
    private GameState gameState;
    private Parachutist parachutist;
    private RandomObstacleGenerator obstacleGenerator;
    private ObstacleQueue obstacleQueue;
    private long score;

    public Infinite(Game game) {
        gameState = GameState.LOADING;
        this.game = game;

        touchInputListener = new TouchInputListener();
        Gdx.input.setInputProcessor(touchInputListener);

        Texture parachutistTexture = game.getAssetManager().get("img/parachutist.png", Texture.class);
        float parachutistWidth = Gdx.graphics.getWidth() / 8f;
        float parachutistHeight = parachutistWidth;
        float parachutistX = Gdx.graphics.getWidth() / 2f - parachutistWidth / 2f;
        float parachutistY = Gdx.graphics.getHeight() / 2f - parachutistHeight / 2f;
        parachutist = new Parachutist(parachutistTexture, parachutistX, parachutistY, parachutistWidth, parachutistHeight);

        float obstacleGap = Gdx.graphics.getWidth() / 2f;
        float velocity = obstacleGap / 90;
        obstacleGenerator = new RandomObstacleGenerator(game, velocity);

        obstacleQueue = new ObstacleQueue(obstacleGenerator, obstacleGap);

        score = 0;

        gameState = GameState.RUNNING;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135f / 255, 206f / 255, 250f / 255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (gameState != GameState.DEAD && gameState != GameState.COMPLETED) {
            TouchInputListener.TouchInputState touchInputState = touchInputListener.getInputState();
            if (touchInputState == TouchInputListener.TouchInputState.LEFT) {
                parachutist.moveLeft();
            } else if (touchInputState == TouchInputListener.TouchInputState.RIGHT) {
                parachutist.moveRight();
            }

            obstacleQueue.updateObstacles();

            if (obstacleQueue.kills(parachutist)) {
                gameState = GameState.DEAD;
            }

            score += obstacleQueue.pointsToAdd(parachutist);
        }

        SpriteBatch spriteBatch = game.getSpriteBatch();
        spriteBatch.begin();
        obstacleQueue.draw(spriteBatch);
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
