package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
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
    private int rotationSetting = 3;
    private float rotationX = 0;
    private float rotationSensitivity;
    private boolean accelAvailable;

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
        float parachutistVelocity = 3 * Gdx.graphics.getWidth() / 250f;
        float parachutistVelocityDelta = Gdx.graphics.getWidth() / 3000f;
        parachutist = new Parachutist(parachutistTexture, parachutistX, parachutistY, parachutistWidth, parachutistHeight, parachutistVelocity, parachutistVelocityDelta);

        float obstacleGap = 5 * Gdx.graphics.getWidth() / 11f;
        float obstacleVelocity = Gdx.graphics.getWidth() / 180f;
        obstacleGenerator = new RandomObstacleGenerator(game, obstacleVelocity);

        obstacleQueue = new ObstacleQueue(obstacleGenerator, obstacleGap);

        accelAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
        rotationSensitivity = Math.abs(rotationSetting - 6) / 2;

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
            if (accelAvailable) {
                rotationX = Gdx.input.getAccelerometerX();

                rotationX *= (Gdx.graphics.getDeltaTime() * MathUtils.radiansToDegrees);

                Gdx.app.log("Rotation", Float.toString(rotationX));
            }
            if (touchInputState == TouchInputListener.TouchInputState.LEFT || rotationX > rotationSensitivity) {
                parachutist.moveLeft();
            } else if (touchInputState == TouchInputListener.TouchInputState.RIGHT || rotationX < -rotationSensitivity) {
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
