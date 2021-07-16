package com.serhatmerak.countdowndesktop.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.serhatmerak.countdowndesktop.AppMain;
import com.serhatmerak.countdowndesktop.helpers.AppInfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = AppInfo.WIDTH;
		config.title = "Count Down Desktop";
		config.x = 1100;
		config.y = 30;
		config.height = AppInfo.HEIGHT;
		new LwjglApplication(new AppMain(), config);
	}
}
