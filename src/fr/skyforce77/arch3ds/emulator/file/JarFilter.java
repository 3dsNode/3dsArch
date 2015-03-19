package fr.skyforce77.arch3ds.emulator.file;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class JarFilter extends FileFilter{

	public boolean accept(File f) {
	    if (f.isDirectory()) {
	        return true;
	    }

	    return f.getName().endsWith(".jar");
	}

	@Override
	public String getDescription() {
		return "Jar only";
	}

}
