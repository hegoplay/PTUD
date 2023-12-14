package test;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import dao.NhanVienDAO;
import entity.TaiKhoan;
import view.MainFrame;

public class TestButton {

    public static void main(String[] args) {
    	try {
			TaiKhoan tk = new TaiKhoan("khoi", "dA9", LocalDate.now(),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}