package fr.skyforce77.arch3ds.emulator.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import fr.skyforce77.arch3ds.emulator.PluginManager;

public class FileMenuItem extends JMenuItem{

	private static final long serialVersionUID = -5180323590655757625L;
	
	public FileMenuItem() {
		super("Open");
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new JarFilter());
				int value = fc.showDialog(fc, "Attach");
				if(value == JFileChooser.APPROVE_OPTION) {
					File plugin = fc.getSelectedFile();
					try {
						PluginManager.loadPlugin(plugin.toURI().toURL());
					} catch (MalformedURLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}

}
