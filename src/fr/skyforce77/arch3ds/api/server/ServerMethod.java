package fr.skyforce77.arch3ds.api.server;

public enum ServerMethod {

	DISPLAY_POPUP(true),
	DISPLAY_CONFIRM_POPUP(true),
	DISPLAY_PROMPT_POPUP(true);
	
	private boolean supported = false;
	
	private ServerMethod(boolean supported) {
		this.supported = supported;
	}
	
	public boolean isSupported() {
		return supported;
	}
	
}