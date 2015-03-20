package fr.skyforce77.arch3ds.emulator;

import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.skyforce77.arch3ds.api.graphics.ArchScreen;
import fr.skyforce77.arch3ds.emulator.swing.ArchMenuFrame;
import fr.skyforce77.arch3ds.emulator.swing.ArchScreenEmu;

public class Emulator {
	
	private static ArchScreenEmu topscreen, bottomscreen;
	private static ArchMenuFrame menu;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		topscreen = new ArchScreenEmu(ArchScreen.TOP_SCREEN, new Dimension(400,240));
		bottomscreen = new ArchScreenEmu(ArchScreen.BOTTOM_SCREEN, new Dimension(320,240));
		menu = new ArchMenuFrame();
		
		new Thread("Tick") {
			public void run() {
				try {
					Thread.sleep(100l);
					if(PluginManager.getPlugin() != null)
						PluginManager.getPlugin().onTick();
				} catch (InterruptedException e) {}
			};
		}.start();
	}
	
	public static ArchScreenEmu getTopScreen() {
		return topscreen;
	}
	
	public static ArchScreenEmu getBottomScreen() {
		return bottomscreen;
	}
	
	public static ArchMenuFrame getMenu() {
		return menu;
	}
	
	public static void update() {
		topscreen.repaint();
		bottomscreen.repaint();
	}
	
}