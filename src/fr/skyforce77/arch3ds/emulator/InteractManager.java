package fr.skyforce77.arch3ds.emulator;

public class InteractManager {

	private static String response = null;
	
	public static void onResponse(String s) {
		response = s;
	}
	
	public static String waitForResponse() {
		while(response == null) {
			try {
				Thread.sleep(100l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String s = response;
		response = null;
		return s;
	}
}
