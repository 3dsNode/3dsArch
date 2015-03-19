package fr.skyforce77.arch3ds.emulator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

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
		
		try {
			JarFile jar = new JarFile(new File(url.toURI()));
			JarEntry entry = jar.getJarEntry("plugin.yml");
	        InputStream stream = jar.getInputStream(entry);
	        Yaml yaml = new Yaml(new SafeConstructor());
	        Map<?, ?> map = asMap(yaml.load(stream));
            Class<?> cls = loader.loadClass(map.get("main").toString());
            Plugin p = (Plugin) cls.newInstance();
            jar.close();
            p.onInit();
            loaded = p;
            p.onEnable();
            Emulator.update();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private static Map<?, ?> asMap(Object object) {
			if (object instanceof Map) {
				return (Map<?, ?>) object;
			}
			return null;
	}
	
	public static Plugin getPlugin() {
		return loaded;
	}
}
