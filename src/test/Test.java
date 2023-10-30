package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JFrame;

import connectDB.ConnectDB;
import entity.NhaCC;
import entity.NhanVien;
import entity.TaiKhoan;
import view.PnlTKDoanhThu;
import view.PnlThongKe;

public class Test {
	public static void main(String[] args) {
		
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from HoaDon where maHD = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, "HD00000001");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				LocalDateTime datetime = rs.getTimestamp(2).toLocalDateTime();
				System.out.println(datetime);
			}
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
