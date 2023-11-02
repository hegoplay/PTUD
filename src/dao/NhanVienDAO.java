package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NguoiQuanLy;
import entity.NhanVien;

public class NhanVienDAO {
	public static NhanVien getNhanVien(String maNV) {
		NhanVien nv = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from NhanVien where maNV = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNV);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenNV = rs.getNString(2);
				boolean isNam = rs.getBoolean(3);
				String diaChi = rs.getNString(4);
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				double luong = rs.getDouble(7);
				boolean chucVu = rs.getBoolean(8);
				boolean dangLamViec = rs.getBoolean(9);
				String cuaHangQL = rs.getString(12);
				if (chucVu) {
					nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec);

				} else {
					nv = new NguoiQuanLy(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL);
					sql = "Select * from NhanVien where maNQL = ? and chucVu = 0";
					statement = con.prepareStatement(sql);
					statement.setString(1, maNV);
					rs = statement.executeQuery();
					ArrayList<NhanVien> dsNv = new ArrayList<>();
					while (rs.next()) {
						tenNV = rs.getNString(2);
						isNam = rs.getBoolean(3);
						diaChi = rs.getNString(4);
						sdt = rs.getString(5);
						email = rs.getString(6);
						luong = rs.getDouble(7);
						chucVu = rs.getBoolean(8);
						dangLamViec = rs.getBoolean(9);
						dsNv.add(new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec));

					}
				}
				return nv;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
	}

	public static NguoiQuanLy getNguoiQuanLy(String maNQL) {
		NguoiQuanLy nv = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from NhanVien where maNV = ? and chucVu = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNQL);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenNV = rs.getNString(2);
				boolean isNam = rs.getBoolean(3);
				String diaChi = rs.getNString(4);
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				double luong = rs.getDouble(7);
				boolean chucVu = rs.getBoolean(8);
				boolean dangLamViec = rs.getBoolean(9);
				String cuaHangQL = rs.getString(12);
				nv = new NguoiQuanLy(maNQL, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL);
				sql = "Select * from NhanVien where maNQL = ? and chucVu = 1";
				statement = con.prepareStatement(sql);
				statement.setString(1, maNQL);
				rs = statement.executeQuery();
				ArrayList<NhanVien> dsNv = new ArrayList<>();
				while (rs.next()) {
					String maNV = rs.getString(1);
					tenNV = rs.getNString(2);
					isNam = rs.getBoolean(3);
					diaChi = rs.getNString(4);
					sdt = rs.getString(5);
					email = rs.getString(6);
					luong = rs.getDouble(7);
					chucVu = rs.getBoolean(8);
					dangLamViec = rs.getBoolean(9);
					dsNv.add(new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec));

				}

				return nv;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
	}
	
	public static ArrayList<NhanVien> getAllNhanVien(){
		ArrayList<NhanVien> lists= new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from NhanVien";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getNString(2);
				boolean isNam = rs.getBoolean(3);
				String diaChi = rs.getNString(4);
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				double luong = rs.getDouble(7);
				boolean chucVu = rs.getBoolean(8);
				boolean dangLamViec = rs.getBoolean(9);
	
				NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec);
				lists.add(nv);
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
}
