package fr.skyforce77.arch3ds.emulator.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import fr.skyforce77.arch3ds.api.input.ArchInput;
import fr.skyforce77.arch3ds.emulator.PluginManager;

public class ArchButton extends JButton implements MouseListener{

	private static final long serialVersionUID = -8382662894059591237L;
	
	private ArchInput input;
	
	public ArchButton(ArchInput input) {
		super(input.toString());
		this.input = input;
		addMouseListener(this);
	}
	
	public ArchInput getInput() {
		return input;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(PluginManager.getPlugin() != null && input != null) {
			PluginManager.getPlugin().onInput(input, (byte)0x01);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(PluginManager.getPlugin() != null && input != null) {
			PluginManager.getPlugin().onInput(input, (byte)0x00);
		}
	}

}