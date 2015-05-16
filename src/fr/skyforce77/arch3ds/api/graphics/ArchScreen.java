package fr.skyforce77.arch3ds.api.graphics;

import java.awt.Dimension;

public class ArchScreen {

	private ArchScreenType type;
	private Dimension size;
	
	public ArchScreen(ArchScreenType type, Dimension size) {
		this.type = type;
		this.size = size;
	}
	
	/**
     * Size getter
     * 
     * @since 3.0
     * @return dimension size
     */
	public Dimension getSize() {
		return size;
	}
	
	/**
     * Screen type getter
     * 
     * @since 3.0
     * @return ArchScreenType type
     */
	public ArchScreenType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type+"("+size.width+"x"+size.height+")";
	}
	
}