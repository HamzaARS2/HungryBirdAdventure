package com.ars.hungrybirdadventure;

import static com.ars.hungrybirdadventure.Constants.HEIGHT;
import static com.ars.hungrybirdadventure.Constants.TITLE;
import static com.ars.hungrybirdadventure.Constants.WIDTH;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle(TITLE);
		config.setWindowedMode(WIDTH, HEIGHT);
		new Lwjgl3Application(new HungryBirdGame(), config);
	}
}
