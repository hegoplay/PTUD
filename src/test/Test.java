package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JFrame;

import connectDB.ConnectDB;
import dao.HoaDonDAO;
import entity.HoaDon;
import entity.NhaCC;
import entity.NhanVien;
import entity.TaiKhoan;
import view.PnlTKDoanhThu;
import view.PnlThongKe;

public class Test {
	public static void main(String[] args) {
		HoaDon hd = HoaDonDAO.GetHoaDon("HD00000001");
		if(hd == null) {
			System.out.println("DM cuoc doi");
		}
	}

}
