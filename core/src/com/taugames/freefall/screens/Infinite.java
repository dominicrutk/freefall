package com.taugames.freefall.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.taugames.freefall.Game;
import com.taugames.freefall.util.Colors;
import com.taugames.freefall.util.GameState;
import com.taugames.freefall.objects.Parachutist;
import com.taugames.freefall.util.persistent.Settings;
import com.taugames.freefall.input.InputListener;
import com.taugames.freefall.input.InputState;
import com.taugames.freefall.input.RotationInputListener;
import com.taugames.freefall.input.TouchInputListener;
import com.taugames.freefall.objects.obstacles.general.ObstacleQueue;
import com.taugames.freefall.objects.obstacles.general.RandomObstacleGenerator;
import com.taugames.freefall.util.persistent.Stats;

public class Infinite implements Screen {
    private final Game game;
    private GameState gameState;
    private Settings settings;
    private Stats stats;
    private InputListener inputListener;
    private Parachutist parachutist;
    private RandomObstacleGenerator obstacleGenerator;
    private ObstacleQueue obstacleQueue;
    private ArrayMap<Integer, BitmapFont> fonts;
    private long score;

    public Infinite(Game game) {
        gameState = GameState.LOADING;

        this.game = game;

        settings = game.getSettings();

        stats = game.getStats();

        Settings.InputType inputType = settings.getInputType();
        if (inputType == Settings.InputType.ROTATION && settings.rotationInputAvailable()) {
            inputListener = new RotationInputListener();
        } else {
            inputListener = new TouchInputListener();
            Gdx.input.setInputProcessor((TouchInputListener) inputListener);
        }

        parachutist = new Parachutist(game);

        float obstacleVelocity = Gdx.graphics.getWidth() / 180f;
        obstacleGenerator = new RandomObstacleGenerator(game, obstacleVelocity);

        float obstacleGap = 5 * Gdx.graphics.getWidth() / 11f;
        obstacleQueue = new ObstacleQueue(obstacleGenerator, obstacleGap);

        fonts = new ArrayMap<Integer, BitmapFont>();
        fonts.put(50, game.getAssetManager().get("font/small.fnt", BitmapFont.class));
        fonts.get(50).setColor(Colors.LIGHT_GRAY);
        fonts.put(100, game.getAssetManager().get("font/large.fnt", BitmapFont.class));
        fonts.get(100).setColor(Colors.LIGHT_GRAY);

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

        if (gameState != GameState.DEAD) {
            inputListener.updateInputState();
            InputState inputState = inputListener.getInputState();
            if (inputState == InputState.LEFT) {
                parachutist.moveLeft();
            } else if (inputState == InputState.RIGHT) {
                parachutist.moveRight();
            } else {
                parachutist.resetVelocity();
            }
        }

        obstacleQueue.updateObstacles();

        if (gameState != GameState.DEAD) {
            if (obstacleQueue.kills(parachutist)) {
                gameState = GameState.DEAD;
                parachutist.setTexture(game.getAssetManager().get("img/parachutist/parachutistDead.png", Texture.class));
            } else {
                score += obstacleQueue.pointsToAdd(parachutist);
            }
        }

        SpriteBatch spriteBatch = game.getSpriteBatch();
        spriteBatch.begin();
        parachutist.draw(spriteBatch);
        obstacleQueue.draw(spriteBatch);
        fonts.get(100).draw(spriteBatch, Long.toString(score), 25, Gdx.graphics.getHeight() - 25);

        if (gameState == GameState.DEAD) {
            game.getGlyphLayout().setText(fonts.get(100), "Game Over");
            fonts.get(100).draw(spriteBatch, "Game Over", Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, Gdx.graphics.getHeight() / 2f + game.getGlyphLayout().height + 15);

            float tapToContinueY = Gdx.graphics.getHeight() / 2f - 10;
            if (score > game.getStats().getHighScore()) {
                game.getGlyphLayout().setText(fonts.get(50), "New high score!");
                fonts.get(50).setColor(Colors.RED);
                fonts.get(50).draw(spriteBatch, "New high score!", Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, tapToContinueY);
                fonts.get(50).setColor(Colors.LIGHT_GRAY);
                tapToContinueY -= 75;
            }

            game.getGlyphLayout().setText(fonts.get(50), "Tap to return to");
            fonts.get(50).draw(spriteBatch, "Tap to return to", Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, tapToContinueY);
            tapToContinueY -= 75;
            
            game.getGlyphLayout().setText(fonts.get(50), "the main menu...");
            fonts.get(50).draw(spriteBatch, "the main menu...", Gdx.graphics.getWidth() / 2f - game.getGlyphLayout().width / 2, tapToContinueY);
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
