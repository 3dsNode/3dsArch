package fr.skyforce77.arch3ds.emulator.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fr.skyforce77.arch3ds.emulator.ArchGameManager;

public class UnloadMenuItem extends JMenuItem{

	private static final long serialVersionUID = -5180323590655757625L;
	
	public UnloadMenuItem() {
		super("Unload");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ArchGameManager.unloadArchGame();
			}
		});
	}

}
