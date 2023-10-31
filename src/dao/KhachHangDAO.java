package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHangDAO {
	public static KhachHang getKhachHang(String maKH) {
		KhachHang kh = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from HoaDon where maKH = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maKH);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenKH = rs.getNString(2);
				String diaChi = rs.getNString(3);
				String sdt = rs.getString(4);
				int namSinh = rs.getInt(5);
				boolean gioiTinh = rs.getBoolean(6);
				kh = new KhachHang(tenKH, diaChi, sdt, namSinh, gioiTinh);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kh;
	}
}
