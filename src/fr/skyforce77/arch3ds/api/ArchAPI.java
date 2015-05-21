package fr.skyforce77.arch3ds.api;


public class ArchAPI {
	
	/**
     * API actual version
     */
	public static double getAPIActualVersion() {
		return 3.0;
	}
	
	/**
     * API version
     */
	public static APIVersion getAPIVersion() {
		return APIVersion.CUPCAKE;
	}
	
	/**
     * Server version
     */
	public static double getServerVersion() {
		return 0.5;
	}
	
	/**
     * Server name
     */
	public static String getServerName(){
		return "3dsArch";
	}
}