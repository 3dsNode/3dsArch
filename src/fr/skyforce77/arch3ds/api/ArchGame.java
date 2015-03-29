package fr.skyforce77.arch3ds.api;

import java.util.ArrayList;

import fr.skyforce77.arch3ds.api.listener.AxisListener;
import fr.skyforce77.arch3ds.api.listener.GraphicsListener;
import fr.skyforce77.arch3ds.api.listener.InputListener;
import fr.skyforce77.arch3ds.api.listener.StylusListener;

public class ArchGame {

	private String name;
	private Double version;
	private ArrayList<AxisListener> axisListeners = new ArrayList<>();
	private ArrayList<InputListener> inputListeners = new ArrayList<>();
	private ArrayList<GraphicsListener> graphicsListeners = new ArrayList<>();
	private ArrayList<StylusListener> stylusListeners = new ArrayList<>();
	
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
	public void onInit(){}
	
	/**
     * Called when this plugin is enabled
     */
	public void onEnable(){}
	
	/**
     * Called when this plugin is disabled
     */
	public void onDisable(){}
	
	/**
     * Called when server ticked
     */
	public void onTick(){}
	
	/**
     * Register AxisListener
     */
	public void addAxisListener(AxisListener listener){
		axisListeners.add(listener);
	}
	
	/**
     * Register InputListener
     */
	public void addInputListener(InputListener listener){
		inputListeners.add(listener);
	}
	
	/**
     * Register GraphicsListener
     */
	public void addGraphicsListener(GraphicsListener listener){
		graphicsListeners.add(listener);
	}
	
	/**
     * Register StylusListener
     */
	public void addStylusListener(StylusListener listener){
		stylusListeners.add(listener);
	}
	
	/**
     * AxisListener list getter
     * 
     * @return AxisListener Array
     */
	public AxisListener[] getAxisListeners(){
		return axisListeners.toArray(new AxisListener[axisListeners.size()]);
	}
	
	/**
     * InputListener list getter
     * 
     * @return InputListener Array
     */
	public InputListener[] getInputListeners(){
		return inputListeners.toArray(new InputListener[inputListeners.size()]);
	}
	
	/**
     * GraphicsListener list getter
     * 
     * @return GraphicsListener Array
     */
	public GraphicsListener[] getGraphicsListeners(){
		return graphicsListeners.toArray(new GraphicsListener[graphicsListeners.size()]);
	}
	
	/**
     * StylusListener list getter
     * 
     * @return StylusListener Array
     */
	public StylusListener[] getStylusListeners(){
		return stylusListeners.toArray(new StylusListener[stylusListeners.size()]);
	}
	
}