package fr.skyforce77.arch3ds.api.graphics;

import java.awt.Graphics2D;

public class ArchGraphics {
	
	private ArchScreen screen;
	private Graphics2D g2d;
	
	/**
     * Instancing with selected type
     */
	public ArchGraphics(ArchScreen screen, Graphics2D g2d) {
		this.screen = screen;
		this.g2d = g2d;
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
     * Called to refresh graphics
     */
	public void push() {
		//TODO
	}

}