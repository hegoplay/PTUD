package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import connectDB.ConnectDB;
import entity.HoaDon;

public class HoaDonDAO {
	public HoaDon GetHoaDon(String maHD) {
		HoaDon hd = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from HoaDon where ?";
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
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hd;
		
		
	}
}
