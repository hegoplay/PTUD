package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHangDAO {
	public static KhachHang getKhachHang(String maKH) {
		KhachHang kh = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from KhachHang where maKH = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maKH);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenKH = rs.getNString(2);
				String diaChi = rs.getNString(3);
				String sdt = rs.getString(4);
				int namSinh = rs.getInt(5);
				boolean gioiTinh = rs.getBoolean(6);
				kh = new KhachHang(maKH,tenKH,diaChi,sdt,namSinh,gioiTinh);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kh;
	}
	public static KhachHang getKHBySDT(String sdt) {
		KhachHang kh = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from KhachHang where sdt = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, sdt);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getNString(2);
				String diaChi = rs.getNString(3);
				int namSinh = rs.getInt(5);
				boolean gioiTinh = rs.getBoolean(6);
				kh = new KhachHang(maKH,tenKH,diaChi,sdt,namSinh,gioiTinh);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kh;
	}
	public static ArrayList<KhachHang> getAllKhachHang(){
		ArrayList<KhachHang> lists= new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from KhachHang";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getNString(2);
				String diaChi = rs.getNString(3);
				String sdt = rs.getString(4);
				int namSinh = rs.getInt(5);
				boolean gioiTinh = rs.getBoolean(6);
			
	
				KhachHang kh = new KhachHang(maKH, tenKH, diaChi, sdt, namSinh, gioiTinh);
				lists.add(kh);
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	
	public static boolean addKhachHang(KhachHang kh) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String maKH = kh.getMaKH();
	        String sql = "INSERT INTO KhachHang (maKH, tenKH, diaChi, sdt, namSinh, gioiTinh) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, maKH);
	        statement.setString(2, kh.getTenKH());
	        statement.setString(3, kh.getDiaChi());
	        statement.setString(4, kh.getSdt());
	        statement.setInt(5, kh.getNamSinh());
	        statement.setBoolean(6, kh.isGioiTinh());
	        int rowsAffected = statement.executeUpdate();
	        con.close();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public static boolean updateKhachHang(KhachHang kh) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String sql = "UPDATE KhachHang SET tenKH = ?, diaChi = ?, sdt = ?, namSinh = ?, gioiTinh = ? WHERE maKH = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, kh.getTenKH());
	        statement.setString(2, kh.getDiaChi());
	        statement.setString(3, kh.getSdt());
	        statement.setInt(4, kh.getNamSinh());
	        statement.setBoolean(5, kh.isGioiTinh());
			statement.setString(6, kh.getMaKH());
	        int rowsAffected = statement.executeUpdate();
	        con.close();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public static ArrayList<KhachHang> findKhachHangByMa(String maKH) {
	    ArrayList<KhachHang> dsKh = new ArrayList<>();
	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement statement = con.prepareStatement("SELECT * FROM KhachHang WHERE maKH = ?")) {
	        statement.setString(1, maKH);

	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
	            	String tenKH = rs.getNString(2);
					String diaChi = rs.getNString(3);
					String sdt = rs.getString(4);
					int namSinh = rs.getInt(5);
					boolean gioiTinh = rs.getBoolean(6);

	                dsKh.add(new KhachHang(maKH, tenKH, diaChi, sdt, namSinh, gioiTinh));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsKh;
	}
	
	
}
