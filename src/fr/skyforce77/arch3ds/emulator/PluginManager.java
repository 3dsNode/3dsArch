package fr.skyforce77.arch3ds.emulator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
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
            setPluginAttributes(p, map);
            jar.close();
            System.out.println("Loading "+p.getName());
            p.onInit();
            loaded = p;
            p.onEnable();
            Emulator.update();
            System.out.println("Loaded "+p.getName());
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
	
	private static void setPluginAttributes(Plugin p, Map<?,?> map) {
		try {
			Field name = Plugin.class.getDeclaredField("name");
			name.setAccessible(true);
			name.set(p, map.get("name").toString());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			Field version = Plugin.class.getDeclaredField("version");
			version.setAccessible(true);
			version.set(p, map.get("version"));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Plugin version isn't a double");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Plugin getPlugin() {
		return loaded;
	}
	
	public static void reloadPlugin() {
		if(loader != null) {
			URL[] urls = loader.getURLs();
			if(urls.length > 0)
				loadPlugin(urls[0]);
		}
	}
}
