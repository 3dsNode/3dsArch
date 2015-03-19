package fr.skyforce77.arch3ds.emulator;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;

import fr.skyforce77.arch3ds.api.Plugin;

public class PluginManager {

	private static URLClassLoader loader;
	private static Plugin loaded;
	
	public static void loadPlugin(URL url) {
		if(loaded != null) {
			loaded.onDisable();
		}
		if(loader != null) {
			try {
				loader.close();
			} catch (IOException e) {
				new JOptionPane("An error occurred", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
		loader = new URLClassLoader(new URL[]{url});
	}
	
	public static Plugin getPlugin() {
		return loaded;
	}
}
