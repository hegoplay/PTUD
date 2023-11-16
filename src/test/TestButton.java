package test;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import view.MainFrame;

public class TestButton {

    public static void main(String[] args) {
    	String path = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	String []path1 = path.split("/");
    	String finPath = "";
    	for (int i = 0 ; i < path1.length - 1; i++) {
    		finPath += path1[i] + "/";
    	}
    	System.out.println(finPath);
	}

}