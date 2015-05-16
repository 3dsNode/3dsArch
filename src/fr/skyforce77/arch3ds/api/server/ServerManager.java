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
		return callMethod(method.name(), args);
	}
	
	/**
     * Call server specific method
     *
     * @return Object returned by called method
     */
	public static Object callMethod(String method, Object... args){
		if(method.equals(ServerMethod.DISPLAY_POPUP.name())) {
			if(args.length >= 1) {
				JOptionPane.showMessageDialog(Emulator.getMenu().getBottomScreen(), args[0],
					args.length >= 2 ? args[1].toString() : "Information",
					JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			return false;
		} else if(method.equals(ServerMethod.DISPLAY_CONFIRM_POPUP.name())) {
			if(args.length >= 1) {
				int result = JOptionPane.showConfirmDialog(Emulator.getMenu().getBottomScreen(), args[0],
					args.length >= 2 ? args[1].toString() : "Question",
					JOptionPane.YES_NO_OPTION);
				return result == JOptionPane.OK_OPTION;
			}
			return false;
		} else if(method.equals(ServerMethod.DISPLAY_PROMPT_POPUP.name())) {
			if(args.length >= 1) {
				return JOptionPane.showInputDialog(Emulator.getMenu().getBottomScreen(), args[0],
					args.length >= 2 ? args[1].toString() : "Question",
					JOptionPane.QUESTION_MESSAGE);
			}
			return false;
		}
		return null;
	}
	
}