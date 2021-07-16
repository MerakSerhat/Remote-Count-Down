package com.serhatmerak.countdownmobile;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.serhatmerak.countdownmobile.helpers.Fonts;
import com.serhatmerak.countdownmobile.systems.Client;

public class AppMain extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Fonts.createFonts();

		loadIPFile();

		setScreen(new MainScreen(this));
	}

	private void loadIPFile() {
		try {
			FileHandle IPFile = Gdx.files.internal("IP.txt");
			Client.IPv4 = IPFile.readString();
		}catch (Exception e){
			Client.IPv4 = "192.168.1.103";
		}

	}

//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	@Override
	public void dispose () {
		batch.dispose();
	}
}

