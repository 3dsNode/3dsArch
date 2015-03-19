package fr.skyforce77.arch3ds.emulator;

import java.awt.Dimension;

import fr.skyforce77.arch3ds.api.graphics.ArchScreen;

public class Emulator {
	
	private static ArchScreenEmu topscreen, bottomscreen;

	public static void main(String[] args) {
		topscreen = new ArchScreenEmu(ArchScreen.TOP_SCREEN, new Dimension(400,240));
		bottomscreen = new ArchScreenEmu(ArchScreen.BOTTOM_SCREEN, new Dimension(320,240));
	}
	
	public static ArchScreenEmu getTopScreen() {
		return topscreen;
	}
	
	public static ArchScreenEmu getBottomScreen() {
		return bottomscreen;
	}
	
}