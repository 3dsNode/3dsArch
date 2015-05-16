package fr.skyforce77.arch3ds.emulator.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import fr.skyforce77.arch3ds.api.ArchAPI;
import fr.skyforce77.arch3ds.api.graphics.ArchScreen;
import fr.skyforce77.arch3ds.api.graphics.ArchScreenType;
import fr.skyforce77.arch3ds.api.input.ArchInput;
import fr.skyforce77.arch3ds.api.listener.InputListener;
import fr.skyforce77.arch3ds.emulator.ArchGameManager;
import fr.skyforce77.arch3ds.emulator.Emulator;
import fr.skyforce77.arch3ds.emulator.file.OpenMenuItem;
import fr.skyforce77.arch3ds.emulator.file.ReloadMenuItem;

public class ArchMenuFrame extends JFrame implements KeyListener{

	private static final long serialVersionUID = 6820689518540215701L;
	
	private ArchScreenComponent topScreen, bottomScreen;

	public ArchMenuFrame() {
		setTitle("3dsArch - Emulator");
		setSize(new Dimension(410,540));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setIconImage(new ImageIcon(Emulator.class.getResource("/resources/icon.png")).getImage());
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.add(new OpenMenuItem(this));
		file.add(new ReloadMenuItem());
		bar.add(file);
		bar.add(new JLabel(" "+ArchAPI.getServerName()+" v"+ArchAPI.getServerVersion()));
		
		topScreen = new ArchScreenComponent(new ArchScreen(ArchScreenType.TOP_SCREEN, new Dimension(400,240)));
		bottomScreen = new ArchScreenComponent(new ArchScreen(ArchScreenType.BOTTOM_SCREEN, new Dimension(320,240)));
		
		add(topScreen);
		add(bottomScreen);
		
		setJMenuBar(bar);
		setVisible(true);
	}
	
	public ArchScreenComponent getTopScreen() {
		return topScreen;
	}
	
	public ArchScreenComponent getBottomScreen() {
		return bottomScreen;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		ArchInput key = toInput(e.getKeyCode());
		if(ArchGameManager.getArchGame() != null && key != null) {
			for(InputListener l : ArchGameManager.getArchGame().getInputListeners()) {
				l.onInput(key, (byte)0x01);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ArchInput key = toInput(e.getKeyCode());
		if(ArchGameManager.getArchGame() != null && key != null) {
			for(InputListener l : ArchGameManager.getArchGame().getInputListeners()) {
				l.onInput(key, (byte)0x00);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	private ArchInput toInput(int keycode) {
		switch (keycode) {
			case KeyEvent.VK_UP: return ArchInput.BUTTON_UP;
			case KeyEvent.VK_DOWN: return ArchInput.BUTTON_DOWN;
			case KeyEvent.VK_LEFT: return ArchInput.BUTTON_LEFT;
			case KeyEvent.VK_RIGHT: return ArchInput.BUTTON_RIGHT;
			case KeyEvent.VK_S: return ArchInput.BUTTON_B;
			case KeyEvent.VK_D: return ArchInput.BUTTON_A;
			case KeyEvent.VK_Q: return ArchInput.BUTTON_Y;
			case KeyEvent.VK_Z: return ArchInput.BUTTON_X;
			case KeyEvent.VK_A: return ArchInput.BUTTON_L;
			case KeyEvent.VK_E: return ArchInput.BUTTON_R;
			case KeyEvent.VK_W: return ArchInput.BUTTON_ZL;
			case KeyEvent.VK_X: return ArchInput.BUTTON_ZR;
			case KeyEvent.VK_N: return ArchInput.BUTTON_SELECT;
			case KeyEvent.VK_B: return ArchInput.BUTTON_START;
			default: return null;
		}
	}
}
