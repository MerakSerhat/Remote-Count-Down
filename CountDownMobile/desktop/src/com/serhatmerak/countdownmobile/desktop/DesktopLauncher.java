package com.serhatmerak.countdownmobile.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.serhatmerak.countdownmobile.AppMain;
import com.serhatmerak.countdownmobile.helpers.AppInfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = AppInfo.HEIGHT / 2;
		config.width = AppInfo.WIDTH / 2;
		new LwjglApplication(new AppMain(), config);
	}
}
