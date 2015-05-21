package fr.skyforce77.arch3ds.emulator;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.skyforce77.arch3ds.api.ArchAPI;
import fr.skyforce77.arch3ds.api.graphics.ArchGraphics;
import fr.skyforce77.arch3ds.api.input.ArchAxis;
import fr.skyforce77.arch3ds.api.input.ArchInput;
import fr.skyforce77.arch3ds.api.listener.AxisListener;
import fr.skyforce77.arch3ds.api.listener.GraphicsListener;
import fr.skyforce77.arch3ds.api.listener.InputListener;
import fr.skyforce77.arch3ds.api.listener.StylusListener;
import fr.skyforce77.arch3ds.emulator.swing.ArchMenuFrame;

public class Emulator {

	private static ArchMenuFrame menu;
	private static String[] buttons = new String[16];
	private static boolean server = false;

	public static void main(String[] args) {
		if(args.length >= 1 && args[0].equals("--server"))
			server = true;
		
		print(Arrays.toString(args));
		
		print("+======================+");
		print(ArchAPI.getServerName()+" "+ArchAPI.getServerVersion());
		print("API: "+ArchAPI.getAPIActualVersion()+" "+ArchAPI.getAPIVersion());
		print("Server: "+server);
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
		
		new Thread("Read") {
			public void run() {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			    while(true) {
					try {
						Thread.sleep(10l);
						try {
							String s = in.readLine();
							if(s != null) {
								action(s);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {}
				}
			};
		}.start();
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = "0";
		}
		print("+======================+");
	}
	
	public static void print(String s) {
		System.out.println(s);
	}
	
	public static void message(String s) {
		if(server)
			System.err.print(s);
	}

	public static ArchMenuFrame getMenu() {
		return menu;
	}

	public static void updateTop() {
		if(ArchGameManager.getArchGame() != null) {
			BufferedImage bi = new BufferedImage(400, 215, BufferedImage.TYPE_INT_ARGB);
			ArchGraphics ag = new ArchGraphics(Emulator.getMenu().getTopScreen().getScreen(), bi.createGraphics());
			for(GraphicsListener l : ArchGameManager.getArchGame().getGraphicsListeners()) {
				l.onScreenUpdated(ag);
			}
		    try {
		    	File f = new File("apps/arch/top.png");
		    	if(server)
		    		ImageIO.write(bi, "png", f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    message("TOP_UPDATE");
		}
		Emulator.getMenu().getTopScreen().repaint();
	}
	
	public static void updateBottom() {
		if(ArchGameManager.getArchGame() != null) {
			BufferedImage bi = new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);
			ArchGraphics ag = new ArchGraphics(Emulator.getMenu().getBottomScreen().getScreen(), bi.createGraphics());
			for(GraphicsListener l : ArchGameManager.getArchGame().getGraphicsListeners()) {
				l.onScreenUpdated(ag);
			}
		    try {
		    	File f = new File("apps/arch/bottom.png");
		    	if(server)
		    		ImageIO.write(bi, "png", f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    message("BOTTOM_UPDATE");
		}
		Emulator.getMenu().getBottomScreen().repaint();
	}
	
	public static void action(String action) {
		if(action.startsWith("AXIS:")) {
			action = action.replace("AXIS:", "");
			String[] split = action.split(",");
			try {
				if(ArchGameManager.getArchGame() != null) {
					for(AxisListener l : ArchGameManager.getArchGame().getAxisListeners()) {
						l.onAxis(ArchAxis.STICK_LEFT, Double.parseDouble(split[0])
							, Double.parseDouble(split[1]));
					}
				}
			} catch(NumberFormatException e) {
				System.err.println("Wrong axis data");
			}
		} else if(action.startsWith("AXISC:")) {
			action = action.replace("AXISC:", "");
			String[] split = action.split(",");
			try {
				if(ArchGameManager.getArchGame() != null) {
					for(AxisListener l : ArchGameManager.getArchGame().getAxisListeners()) {
						l.onAxis(ArchAxis.STICK_RIGHT, Double.parseDouble(split[0])
							, Double.parseDouble(split[1]));
					}
				}
			} catch(NumberFormatException e) {
				print("Wrong axis-c data");
			}
		} else if(action.startsWith("MOUSEMOVE:")) {
			action = action.replace("MOUSEMOVE:", "");
			String[] split = action.split(",");
			try {
				if(ArchGameManager.getArchGame() != null) {
					for(StylusListener l : ArchGameManager.getArchGame().getStylusListeners()) {
						l.onStylusMoved(Emulator.getMenu().getBottomScreen().getScreen(), Integer.parseInt(split[0])
							, Integer.parseInt(split[1]));
					}
				}
			} catch(NumberFormatException e) {
				print("Wrong stylus location data");
			}
		} else if(action.startsWith("MOUSECLICK:")) {
			action = action.replace("MOUSECLICK:", "");
			String[] split = action.split(",");
			try {
				if(ArchGameManager.getArchGame() != null) {
					for(StylusListener l : ArchGameManager.getArchGame().getStylusListeners()) {
						l.onStylusClicked(Emulator.getMenu().getBottomScreen().getScreen(), Integer.parseInt(split[0])
							, Integer.parseInt(split[1]));
					}
				}
			} catch(NumberFormatException e) {
				print("Wrong stylus location data");
			}
		} else if(action.startsWith("MOUSEDOWN:")) {
			action = action.replace("MOUSEDOWN:", "");
			String[] split = action.split(",");
			try {
				if(ArchGameManager.getArchGame() != null) {
					for(StylusListener l : ArchGameManager.getArchGame().getStylusListeners()) {
						l.onStylusPressed(Emulator.getMenu().getBottomScreen().getScreen(), Integer.parseInt(split[0])
							, Integer.parseInt(split[1]));
					}
				}
			} catch(NumberFormatException e) {
				print("Wrong stylus location data");
			}
		} else if(action.startsWith("MOUSEUP:")) {
			action = action.replace("MOUSEUP:", "");
			String[] split = action.split(",");
			try {
				if(ArchGameManager.getArchGame() != null) {
					for(StylusListener l : ArchGameManager.getArchGame().getStylusListeners()) {
						l.onStylusReleased(Emulator.getMenu().getBottomScreen().getScreen(), Integer.parseInt(split[0])
							, Integer.parseInt(split[1]));
					}
				}
			} catch(NumberFormatException e) {
				print("Wrong stylus location data");
			}
		} else if(action.startsWith("BUTTONS:")) {
			action = action.replace("BUTTONS:", "");
			String[] split = action.split(",");
			for(int i = 0; i < split.length; i++) {
				if(!split[i].equals(buttons[i])) {
					buttons[i] = split[i];
					if(ArchGameManager.getArchGame() != null) {
						for(InputListener l : ArchGameManager.getArchGame().getInputListeners()) {
							l.onInput(ArchInput.byCode((byte)i), Byte.parseByte(buttons[i]));
						}
					}
				}
			}
		} else if(action.startsWith("METHODRESPONSE:")) {
			InteractManager.onResponse(action.replace("METHODRESPONSE:", ""));
		}
	}

}