package fr.skyforce77.arch3ds.emulator;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import fr.skyforce77.arch3ds.api.graphics.ArchScreen;
import fr.skyforce77.arch3ds.emulator.file.FileMenuItem;

public class ArchScreenEmu extends JFrame{

	private static final long serialVersionUID = -3067221987757157524L;
	
	public ArchScreenEmu(ArchScreen screen, Dimension size) {
		setTitle("3dsArch Emulator");
		setSize(size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.add(new FileMenuItem());
		bar.add(file);
		
		setJMenuBar(bar);
		
		add(new ArchScreenComponent(screen));
		setVisible(true);
	}

}
