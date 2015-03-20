package fr.skyforce77.arch3ds.emulator.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import fr.skyforce77.arch3ds.api.input.ArchInput;
import fr.skyforce77.arch3ds.emulator.Emulator;
import fr.skyforce77.arch3ds.emulator.file.FileMenuItem;

public class ArchMenuFrame extends JFrame{

	private static final long serialVersionUID = 6820689518540215701L;

	public ArchMenuFrame() {
		setTitle("3dsArch - Menu");
		setSize(new Dimension(300,500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setIconImage(new ImageIcon(Emulator.class.getResource("/resources/icon.png")).getImage());
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.add(new FileMenuItem());
		bar.add(file);
		
		add(new ArchButton(ArchInput.BUTTON_SELECT));
		add(new ArchButton(ArchInput.BUTTON_START));
		
		setJMenuBar(bar);
		setVisible(true);
	}
}
