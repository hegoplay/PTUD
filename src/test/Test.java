package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import connectDB.ConnectDB;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import dao.TraHangDAO;
import entity.ChiTietTraHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NguoiQuanLy;
import entity.NhaCC;
import entity.NhanVien;
import entity.PhieuTraHang;
import entity.SanPham;
import entity.TaiKhoan;
import view.PnlTKDoanhThu;
import view.PnlThongKe;

public class Test {
	public static void main(String[] args) throws Exception {
		
//		SanPham sp = SanPhamDAO.GetSanPham("SP00000003");
//		System.out.println(sp);
		
		ArrayList<ChiTietTraHang> ctth =  new ArrayList<ChiTietTraHang>();
		ctth.add(new ChiTietTraHang(new SanPham("SP00000019"), 1));
		ctth.add(new ChiTietTraHang(new SanPham("SP00000020"), 1));
		
		PhieuTraHang pth = new PhieuTraHang("TH00000000", new HoaDon("HD00000001"), LocalDate.now(),
				new NguoiQuanLy("NV00000000", "PHTM", "0909092343", "adfa@gmail.com"
						, "abc", 10000000, false, false, false, "abc"), new KhachHang("KH0947842274"), 
				ctth);
		TraHangDAO.ThemPhieuTraHang(pth);
	
	}

}
