package com.taugames.freefall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu implements Screen {
    private Stage stage;


    public MainMenu(final Game game) {

        float playLength = Gdx.graphics.getWidth() / 4f;
        float iconLength = Gdx.graphics.getWidth() / 10f;
        float iconDist = 20 * Gdx.graphics.getWidth() / 1440f;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture playButton = game.getAssetManager().get("img/buttons/playButton.png", Texture.class);
        ImageButton playButtonButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButton)));
        playButtonButton.setSize(playLength, playLength);
        playButtonButton.setPosition((Gdx.graphics.getWidth() - playLength) / 2f, (Gdx.graphics.getHeight() - playLength) / 2f);
        playButtonButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new Infinite(game));
            }
        });
        stage.addActor(playButtonButton);

        Texture settingsButton = game.getAssetManager().get("img/buttons/settingsButton.png", Texture.class);
        ImageButton settingsButtonButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(settingsButton)));
        settingsButtonButton.setSize(iconLength, iconLength);
        settingsButtonButton.setPosition(Gdx.graphics.getWidth() - iconDist - iconLength, Gdx.graphics.getHeight() - iconDist - iconLength);
        settingsButtonButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("TODO", "Settings - Coming soon!");
            }
        });
        stage.addActor(settingsButtonButton);

        Texture shoppingCartButton = game.getAssetManager().get("img/buttons/shoppingCartButton.png", Texture.class);
        ImageButton shoppingCartButtonButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(shoppingCartButton)));
        shoppingCartButtonButton.setSize(iconLength, iconLength);
        shoppingCartButtonButton.setPosition(iconDist, Gdx.graphics.getHeight() - iconDist - iconLength);
        shoppingCartButtonButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("TODO", "Shop - Coming soon!");
            }
        });
        stage.addActor(shoppingCartButtonButton);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(135f / 255, 206f / 255, 250f / 255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

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
