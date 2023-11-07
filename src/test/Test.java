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
		LocalDate current = LocalDate.now().minusDays(31);
		System.out.println(HoaDonDAO.GetSanPhamInDate(current, LocalDate.now()));
	
	}

}
