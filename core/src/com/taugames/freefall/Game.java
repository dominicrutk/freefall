package com.taugames.freefall;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
	private SpriteBatch spriteBatch;
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render() {
	    super.render();
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		this.getScreen().dispose();
	}

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
