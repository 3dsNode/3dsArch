package fr.skyforce77.arch3ds.emulator.swing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;

import fr.skyforce77.arch3ds.api.input.ArchInput;
import fr.skyforce77.arch3ds.emulator.PluginManager;

public class ArchButton extends JButton implements ItemListener{

	private static final long serialVersionUID = -8382662894059591237L;
	
	private ArchInput input;
	
	public ArchButton(ArchInput input) {
		this.input = input;
		addItemListener(this);
	}
	
	public ArchInput getInput() {
		return input;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(PluginManager.getPlugin() != null && input != null) {
			PluginManager.getPlugin().onInput(input, e.getStateChange() == 1 ? (byte)0x01 : (byte)0x00);
		}
	}

}