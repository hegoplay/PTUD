package test;

import java.time.LocalDate;

import entity.NhaCC;
import entity.NhanVien;
import entity.TaiKhoan;

public class Test {
	public static void main(String[] args) {
		NhanVien nv = null;
		TaiKhoan tk = null;
		try {
			nv = new NhanVien("NV00000001", "Pham The Manh", "0944713015", "pmanhh19@outlook.com", "abc", 0, "Truong Phong", true, true);
			tk = new TaiKhoan("abadfc", ".", LocalDate.now(), nv); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tk);
	}

}
