package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;

public class TraHangDAO {
	public static boolean KiemTraTTPhieuTra(String maPhieu) {
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select count(*) from PhieuTraHang where maPhieu = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maPhieu);
			ResultSet rs = statement.executeQuery();
			
			con.close();
			boolean res = false;
			if (rs.next()) {
				if (rs.getInt(1) == 0) {
					res = true;
					
				}
			}
			con.close();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
