package de.fhbielefeld.pmdungeon.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.fhbielefeld.pmdungeon.PMDungeon;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("PM-Dungeon");
		new Lwjgl3Application(new PMDungeon(), config);
	}
}
