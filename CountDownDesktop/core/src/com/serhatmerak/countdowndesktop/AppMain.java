package com.serhatmerak.countdowndesktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.serhatmerak.countdowndesktop.helpers.Fonts;

public class AppMain extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Fonts.createFonts();
		setScreen(new MainScreen(this));
	}

//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	@Override
	public void dispose () {
		batch.dispose();
	}
}

