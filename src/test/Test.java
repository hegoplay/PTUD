package test;

import java.time.LocalDate;

import javax.swing.JFrame;

import entity.NhaCC;
import entity.NhanVien;
import entity.TaiKhoan;
import view.PnlTKDoanhThu;
import view.PnlThongKe;

public class Test {
	public static void main(String[] args) {
		NhanVien nv = null;
		TaiKhoan tk = null;
		try {
			nv = new NhanVien("NV00000001", "Pham The Manh", "0944713015", "pmanhh19@outlook.com", "abc", 0, "Truong Phong", true, true);
			tk = new TaiKhoan("abadfc", ".afafdsf", LocalDate.now(), nv); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tk);
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 1280, 720);
		PnlTKDoanhThu pnlThongKe = new PnlTKDoanhThu();
		frame.add(pnlThongKe);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
