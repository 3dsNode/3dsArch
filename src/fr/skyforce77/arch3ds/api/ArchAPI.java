package fr.skyforce77.arch3ds.api;


public class ArchAPI {
	
	/**
     * API actual version
     */
	public static double getAPIActualVersion() {
		return 1.1;
	}
	
	/**
     * API version
     */
	public static APIVersion getAPIVersion() {
		return APIVersion.ARABICA;
	}
	
	/**
     * Server version
     */
	public static double getServerVersion() {
		return 0.2;
	}
	
	/**
     * Server name
     */
	public static String getServerName(){
		return "3dsArch Emulator";
	}
}