package com.taugames.freefall.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
    private InputMultiplexer inputMultiplexer;
    private InputListener inputListener;
    private Parachutist parachutist;
    private RandomObstacleGenerator obstacleGenerator;
    private ObstacleQueue obstacleQueue;
    private ArrayMap<Integer, BitmapFont> fonts;
    private long score;
    private float timeDead;
    private boolean highScore;

    private Stage runningStage;
    private Stage pausedStage;

    public Infinite(final Game game) {
        gameState = GameState.LOADING;

        this.game = game;

        settings = game.getSettings();

        stats = game.getStats();

        inputMultiplexer = new InputMultiplexer();

        Settings.InputType inputType = settings.getInputType();
        if (inputType == Settings.InputType.ROTATION && settings.rotationInputAvailable()) {
            inputListener = new RotationInputListener();
        } else {
            inputListener = new TouchInputListener();
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

        highScore = false;

        // Pause mechanics
        float menuButtonSize = Gdx.graphics.getWidth() / 8f;
        float menuButtonDistance = Gdx.graphics.getWidth() / 48f;

        runningStage = new Stage(new ScreenViewport());
        pausedStage = new Stage(runningStage.getViewport());

        Texture pauseButtonTexture = game.getAssetManager().get("img/buttons/pauseButton.png", Texture.class);
        Button pauseButton = new Button(new TextureRegionDrawable(new TextureRegion(pauseButtonTexture)));
        pauseButton.setSize(menuButtonSize, menuButtonSize);
        pauseButton.setPosition(Gdx.graphics.getWidth() - menuButtonDistance - menuButtonSize, Gdx.graphics.getHeight() - menuButtonDistance - menuButtonSize);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameState = GameState.PAUSED;
                inputMultiplexer.removeProcessor(runningStage);
                inputMultiplexer.addProcessor(0, pausedStage);
            }
        });
        runningStage.addActor(pauseButton);

        Texture exitButtonTexture = game.getAssetManager().get("img/buttons/exitButton.png", Texture.class);
        Button exitButton = new Button(new TextureRegionDrawable(new TextureRegion(exitButtonTexture)));
        exitButton.setSize(menuButtonSize, menuButtonSize);
        exitButton.setPosition(menuButtonDistance, Gdx.graphics.getHeight() - menuButtonDistance - menuButtonSize);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        pausedStage.addActor(exitButton);

        Texture playButtonTexture = game.getAssetManager().get("img/buttons/playButtonGray.png", Texture.class);
        Button playButton = new Button(new TextureRegionDrawable(new TextureRegion(playButtonTexture)));
        playButton.setSize(menuButtonSize, menuButtonSize);
        playButton.setPosition(Gdx.graphics.getWidth() - menuButtonDistance - menuButtonSize, Gdx.graphics.getHeight() - menuButtonDistance - menuButtonSize);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameState = GameState.RUNNING;
                inputMultiplexer.removeProcessor(pausedStage);
                inputMultiplexer.addProcessor(0, runningStage);
            }
        });
        pausedStage.addActor(playButton);

        inputMultiplexer.addProcessor(runningStage);
        if (inputListener instanceof TouchInputListener) {
            inputMultiplexer.addProcessor((TouchInputListener) inputListener);
        }
        Gdx.input.setInputProcessor(inputMultiplexer);

        gameState = GameState.RUNNING;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Colors.SKY_BLUE.r, Colors.SKY_BLUE.g, Colors.SKY_BLUE.b, Colors.SKY_BLUE.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (gameState != GameState.PAUSED && gameState != GameState.DEAD) {
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

        if (gameState != GameState.PAUSED) {
            obstacleQueue.updateObstacles();
        }

        if (gameState != GameState.PAUSED && gameState != GameState.DEAD) {
            if (obstacleQueue.kills(parachutist)) {
                gameState = GameState.DEAD;
                parachutist.setTexture(game.getAssetManager().get("img/parachutist/parachutistDead.png", Texture.class));
                timeDead = 0;
                stats.setTotalScore(stats.getTotalScore() + score);
                if (score > stats.getHighScore()) {
                    stats.setHighScore(score);
                    highScore = true;
                }
            } else {
                score += obstacleQueue.pointsToAdd(parachutist);
            }
        } else if (gameState == GameState.DEAD) {
            timeDead += Gdx.graphics.getDeltaTime();
            if (timeDead >= 0.5 && Gdx.input.justTouched()) {
                game.setScreen(new MainMenu(game));
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
            if (highScore) {
                game.getGlyphLayout().setText(fonts.get(50), "New high score!");
                fonts.get(50).setColor(((int) timeDead & 1) == 0 ? Colors.RED : Colors.WHITE);
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

        if (gameState != GameState.DEAD) {
            if (gameState == GameState.PAUSED) {
                pausedStage.act();
                pausedStage.draw();
            } else {
                runningStage.act();
                runningStage.draw();
            }
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
