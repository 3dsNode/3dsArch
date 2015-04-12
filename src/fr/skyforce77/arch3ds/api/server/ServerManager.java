package fr.skyforce77.arch3ds.api.server;

import javax.swing.JOptionPane;

import fr.skyforce77.arch3ds.emulator.Emulator;

public class ServerManager {

	/**
     * Call server specific method
     *
     * @return Object returned by called method
     */
	public static Object callMethod(ServerMethod method, Object... args){
		return callMethod(method.toString(), args);
	}
	
	/**
     * Call server specific method
     *
     * @return Object returned by called method
     */
	public static Object callMethod(String method, Object... args){
		if(method.equals(ServerMethod.DISPLAY_POPUP.toString())) {
			if(args.length >= 1) {
				JOptionPane.showMessageDialog(Emulator.getBottomScreen(), args[0],
					args.length >= 2 ? args[1].toString() : "Information",
					JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			return false;
		}
		return null;
	}
	
}