package fr.skyforce77.arch3ds.emulator.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fr.skyforce77.arch3ds.emulator.PluginManager;

public class ReloadMenuItem extends JMenuItem{

	private static final long serialVersionUID = -5180323590655757625L;
	
	public ReloadMenuItem() {
		super("Reload");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					PluginManager.reloadPlugin();
			}
		});
	}

}
