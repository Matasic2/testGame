package com.mygdx.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 2560 / 2;
		config.height = 1440 / 2;
		MyGdxGame.Companion.setScale(0.5f);
		new LwjglApplication(new MyGdxGame(), config);
		MyGdxGame.Companion.setAppType(Application.ApplicationType.Desktop);
		//System.out.println(new LwjglApplication(new MyGdxGame(), config).getType() == Application.ApplicationType.Desktop);
	}
}
