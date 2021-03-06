package fr.skyforce77.arch3ds.api;

import javax.swing.JOptionPane;

import fr.skyforce77.arch3ds.emulator.Emulator;

@Deprecated
public class GameManager {

	/**
     * Call server specific method
     *
     * @return boolean method exists and called successfully
     */
	public static boolean callGameMethod(String method, Object... args){
		if(method.equals("displayPopup")) {
			if(args.length >= 1) {
				JOptionPane.showMessageDialog(Emulator.getMenu(), args[0],
					args.length >= 2 ? args[1].toString() : "Information",
					JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		}
		return false;
	}
	
}