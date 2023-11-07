package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.SanPham;

public class LSTTonDAO {
	public static ArrayList<SanPham> GetSanPhamInDate(LocalDate startDate, LocalDate endDate){
		ArrayList<SanPham> res = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select distinct maSP from LichSuTon"
					+ "	where (ngayThayDoi between ? and ?) order by maSP";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(startDate));
			statement.setDate(2, Date.valueOf(endDate));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				res.add(SanPhamDAO.GetSanPham(rs.getString(1)));
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static int GetSLTang(SanPham sp, LocalDate startDate, LocalDate endDate){
		int res = 0;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select sum(soLuongNhap) from LichSuTon\r\n"
					+ "where (ngayThayDoi between ? and ?) and maSP = ? \r\n"
					+ "group by maSP";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(startDate));
			statement.setDate(2, Date.valueOf(endDate));
			statement.setString(3, sp.getMaSP());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	} 
}
