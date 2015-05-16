package fr.skyforce77.arch3ds.emulator;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.skyforce77.arch3ds.emulator.swing.ArchMenuFrame;

public class Emulator {

	private static ArchMenuFrame menu;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		menu = new ArchMenuFrame();

		new Thread("Tick") {
			public void run() {
				while(true) {
					try {
						Thread.sleep(100l);
						if(ArchGameManager.getArchGame() != null)
							ArchGameManager.getArchGame().onTick();
					} catch (InterruptedException e) {}
				}
			};
		}.start();
	}

	public static ArchMenuFrame getMenu() {
		return menu;
	}

	public static void updateTop() {
		Emulator.getMenu().getTopScreen().repaint();
	}
	
	public static void updateBottom() {
		Emulator.getMenu().getBottomScreen().repaint();
	}

}