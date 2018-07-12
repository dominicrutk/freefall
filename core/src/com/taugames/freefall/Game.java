package com.taugames.freefall;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
	private SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render() {
	    super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		this.getScreen().dispose();
	}

    SpriteBatch getBatch() {
        return batch;
    }
}
