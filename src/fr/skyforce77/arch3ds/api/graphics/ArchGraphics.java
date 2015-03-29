package fr.skyforce77.arch3ds.api.graphics;

import java.awt.Graphics2D;

import fr.skyforce77.arch3ds.emulator.Emulator;

public class ArchGraphics {
	
	private ArchScreen screen;
	private Graphics2D g2d;
	private int width, height;
	
	/**
     * Instancing with selected type
     */
	public ArchGraphics(ArchScreen screen, Graphics2D g2d, int width, int height) {
		this.screen = screen;
		this.g2d = g2d;
		this.width = width;
		this.height = height;
	}
	
	/**
     * Screen type getter
     * 
     * @return actual graphics screen
     */
	public ArchScreen getScreen() {
		return screen;
	}
	
	/**
     * Screen graphics getter
     * 
     * @return actual graphics
     */
	public Graphics2D getGraphics() {
		return g2d;
	}
	
	/**
     * Screen width getter
     * 
     * @return graphics width
     */
	public int getWidth() {
		return width;
	}
	
	/**
     * Screen height getter
     * 
     * @return graphics height
     */
	public int getHeight() {
		return height;
	}
	
	/**
     * Called to refresh graphics
     */
	public static void push(ArchScreen screen) {
		if(screen.equals(ArchScreen.TOP_SCREEN)) {
			Emulator.updateTop();
		} else if(screen.equals(ArchScreen.BOTTOM_SCREEN)) {
			Emulator.updateBottom();
		}
	}

}