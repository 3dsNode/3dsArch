package fr.skyforce77.arch3ds.emulator.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import fr.skyforce77.arch3ds.api.graphics.ArchGraphics;
import fr.skyforce77.arch3ds.api.graphics.ArchScreen;
import fr.skyforce77.arch3ds.emulator.PluginManager;

public class ArchScreenComponent extends JComponent{
	
	private static final long serialVersionUID = -8493615357999345644L;
	
	private ArchScreen screen;
	
	public ArchScreenComponent(ArchScreen screen) {
		this.screen = screen;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if(PluginManager.getPlugin() != null) {
			PluginManager.getPlugin().drawScreen(new ArchGraphics(screen, g2d, getWidth(), getHeight()));
		}
	}

}
