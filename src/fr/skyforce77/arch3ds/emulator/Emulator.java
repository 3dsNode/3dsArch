package fr.skyforce77.arch3ds.emulator;

import java.awt.Dimension;

import fr.skyforce77.arch3ds.api.graphics.ArchScreen;
import fr.skyforce77.arch3ds.emulator.swing.ArchMenuFrame;
import fr.skyforce77.arch3ds.emulator.swing.ArchScreenEmu;

public class Emulator {
	
	private static ArchScreenEmu topscreen, bottomscreen;
	private static ArchMenuFrame menu;

	public static void main(String[] args) {
		topscreen = new ArchScreenEmu(ArchScreen.TOP_SCREEN, new Dimension(400,240));
		bottomscreen = new ArchScreenEmu(ArchScreen.BOTTOM_SCREEN, new Dimension(320,240));
		menu = new ArchMenuFrame();
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