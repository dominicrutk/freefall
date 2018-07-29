package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taugames.freefall.input.InputListener;
import com.taugames.freefall.input.InputState;
import com.taugames.freefall.input.RotationInputListener;
import com.taugames.freefall.input.TouchInputListener;
import com.taugames.freefall.obstacles.util.ObstacleQueue;
import com.taugames.freefall.obstacles.util.RandomObstacleGenerator;

public class Infinite implements Screen {
    private final Game game;
    private Settings settings;
    private InputListener inputListener;
    private GameState gameState;
    private Parachutist parachutist;
    private RandomObstacleGenerator obstacleGenerator;
    private ObstacleQueue obstacleQueue;
    private long score;

    public Infinite(Game game) {
        gameState = GameState.LOADING;
        this.game = game;

        settings = game.getSettings();

        Settings.InputType inputType = settings.getInputType();
        if (inputType == Settings.InputType.ROTATION && settings.rotationInputAvailable()) {
            inputListener = new RotationInputListener();
        } else {
            inputListener = new TouchInputListener();
            Gdx.input.setInputProcessor((TouchInputListener) inputListener);
        }

        parachutist = new Parachutist(game);

        float obstacleGap = 5 * Gdx.graphics.getWidth() / 11f;
        float obstacleVelocity = Gdx.graphics.getWidth() / 180f;
        obstacleGenerator = new RandomObstacleGenerator(game, obstacleVelocity);

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
            inputListener.updateInputState();
            InputState inputState = inputListener.getInputState();
            if (inputState == InputState.LEFT) {
                parachutist.moveLeft();
            } else if (inputState == InputState.RIGHT) {
                parachutist.moveRight();
            } else {
                parachutist.resetVelocity();
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
