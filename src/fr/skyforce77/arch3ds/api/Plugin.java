package fr.skyforce77.arch3ds.api;

import fr.skyforce77.arch3ds.api.graphics.ArchGraphics;
import fr.skyforce77.arch3ds.api.input.ArchAxis;
import fr.skyforce77.arch3ds.api.input.ArchInput;

public class Plugin {

	private String name;
	private Double version;
	
	/**
     * Returns the name of the plugin
     *
     * @return name of the plugin
     */
	public String getName(){
		return name;
	}
	
	/**
     * Returns the version of the plugin
     *
     * @return version of the plugin
     */
	public Double getVersion(){
		return version;
	}
	
	/**
     * Called when this plugin need to be enabled
     */
	public void onInit(){};
	
	/**
     * Called when this plugin is enabled
     */
	public void onEnable(){};
	
	/**
     * Called when this plugin is disabled
     */
	public void onDisable(){};
	
	/**
     * Called when server ticked
     */
	public void onTick(){};
	
	/**
     * Called when input status changed
     */
	public void onInput(ArchInput input, byte status){};
	
	/**
     * Called when axis status changed
     */
	public void onAxis(ArchAxis axis, double location){};
	
	/**
     * Called when server need to refresh screen
     */
	public void drawScreen(ArchGraphics graphics){};
	
}