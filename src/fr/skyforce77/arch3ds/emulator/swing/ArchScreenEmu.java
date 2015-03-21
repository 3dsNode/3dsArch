package fr.skyforce77.arch3ds.emulator.swing;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.skyforce77.arch3ds.api.graphics.ArchScreen;
import fr.skyforce77.arch3ds.api.input.ArchInput;
import fr.skyforce77.arch3ds.emulator.ArchGameManager;
import fr.skyforce77.arch3ds.emulator.Emulator;

public class ArchScreenEmu extends JFrame implements KeyListener{

	private static final long serialVersionUID = -3067221987757157524L;
	
	public ArchScreenEmu(ArchScreen screen, Dimension size) {
		setTitle("3dsArch - "+screen);
		setSize(size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(Emulator.class.getResource("/resources/icon.png")).getImage());
		
		add(new ArchScreenComponent(screen));
		
		addKeyListener(this);
		setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		ArchInput key = toInput(e.getKeyCode());
		if(ArchGameManager.getArchGame() != null && key != null) {
			ArchGameManager.getArchGame().onInput(key, (byte)0x01);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ArchInput key = toInput(e.getKeyCode());
		if(ArchGameManager.getArchGame() != null && key != null) {
			ArchGameManager.getArchGame().onInput(key, (byte)0x00);
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
			default: return null;
		}
	}

}
