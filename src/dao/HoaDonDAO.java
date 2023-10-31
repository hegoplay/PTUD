package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class HoaDonDAO {
	public static HoaDon GetHoaDon(String maHD) {
		HoaDon hd = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from HoaDon where maHD = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				LocalDateTime ngayLapHD = rs.getTimestamp(2).toLocalDateTime();
				String maNV = rs.getString(3);
				String maKH = rs.getString(4);
				float khuyenMai = rs.getFloat(5);
				double tienKhachDua = rs.getDouble(6);
				double tongHoaDon = rs.getDouble(7);
				NhanVien nv = NhanVienDAO.getNhanVien(maNV);
				KhachHang kh = KhachHangDAO.getKhachHang(maKH);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hd;
	}
	public static ArrayList<ChiTietHoaDon> GetDSCTHD(String maHD) {
		ArrayList<ChiTietHoaDon> list = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from ChiTietHoaDon where hoaDon = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
//				SanPham sp = SanPhamDAO
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
}