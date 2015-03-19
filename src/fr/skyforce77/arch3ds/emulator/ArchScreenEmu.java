package fr.skyforce77.arch3ds.emulator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import fr.skyforce77.arch3ds.api.graphics.ArchGraphics;
import fr.skyforce77.arch3ds.api.graphics.ArchScreen;

public class ArchScreenEmu extends JFrame{

	private static final long serialVersionUID = -3067221987757157524L;
	private ArchScreen screen;
	
	public ArchScreenEmu(ArchScreen screen, Dimension size) {
		this.screen = screen;
			
		setTitle("3dsArch Emulator");
		setSize(size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void update(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if(PluginManager.getPlugin() != null) {
			PluginManager.getPlugin().drawScreen(new ArchGraphics(screen, g2d, getWidth(), getHeight()));
		}
	}

}
