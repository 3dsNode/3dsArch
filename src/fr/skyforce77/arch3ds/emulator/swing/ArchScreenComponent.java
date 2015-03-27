package fr.skyforce77.arch3ds.emulator.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import fr.skyforce77.arch3ds.api.graphics.ArchGraphics;
import fr.skyforce77.arch3ds.api.graphics.ArchScreen;
import fr.skyforce77.arch3ds.emulator.ArchGameManager;

public class ArchScreenComponent extends JComponent implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = -8493615357999345644L;
	
	private ArchScreen screen;
	
	public ArchScreenComponent(ArchScreen screen) {
		this.screen = screen;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if(ArchGameManager.getArchGame() != null) {
			ArchGameManager.getArchGame().drawScreen(new ArchGraphics(screen, g2d, getWidth(), getHeight()));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(ArchGameManager.getArchGame() != null)
			ArchGameManager.getArchGame().onStylusClicked(screen, e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(ArchGameManager.getArchGame() != null)
			ArchGameManager.getArchGame().onStylusPressed(screen, e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(ArchGameManager.getArchGame() != null)
			ArchGameManager.getArchGame().onStylusReleased(screen, e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(ArchGameManager.getArchGame() != null)
			ArchGameManager.getArchGame().onStylusMoved(screen, e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(ArchGameManager.getArchGame() != null)
			ArchGameManager.getArchGame().onStylusMoved(screen, e.getX(), e.getY());
	}

}
