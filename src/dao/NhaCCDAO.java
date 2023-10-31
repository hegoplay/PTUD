package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.LoaiSP;
import entity.NhaCC;

public class NhaCCDAO {
	public static NhaCC GetNhaCC(String maNCC) {
		NhaCC ncc = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from NhaCC where maNCC = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNCC);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenNCC = rs.getNString(2);
				String diaChi = rs.getNString(3);
				String maQG = rs.getString(4);
				ncc = new NhaCC(maNCC, tenNCC, diaChi, maQG);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ncc;
	}
}
