package com.shenko.rfk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shenko.rfk.RFKGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Robot Doesn't Find Kitten";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new RFKGame(), config);
	}
}
