package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiSP;
import entity.NhaCC;
import entity.SanPham;

public class NhaCCDAO {
	public static NhaCC GetNhaCC(String maNCC) throws Exception {
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
	public static ArrayList<NhaCC> getAllNCC() {
		ArrayList<NhaCC> dsNCC = new ArrayList<NhaCC>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select *from NhaCC";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String diachi = rs.getString(3);
				String maQuocGia = rs.getString(4);
				NhaCC ncc = new NhaCC(maNCC, tenNCC, diachi, maQuocGia);
				dsNCC.add(ncc);

			}
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNCC;
	}
	public boolean addNhaCC(NhaCC ncc) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String maNCC = ncc.getMaNCC();
	        
	        // Print or log the maNCC value for debugging
	        System.out.println("maNCC value: " + maNCC);
	        
	        if (maNCC == null || maNCC.trim().isEmpty()) {
	            System.err.println("Error: maNCC is null or empty");
	            return false;
	        }

	        String sql = "INSERT INTO NhaCC (maNCC, tenNCC, diachi, maQuocGia) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, maNCC);
	        statement.setString(2, ncc.getTenNCC());
	        statement.setString(3, ncc.getDiaChi());
	        statement.setString(4, ncc.getQuocGia());
	        int rowsAffected = statement.executeUpdate();
	        con.close();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean updateNhaCC(NhaCC ncc) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String sql = "UPDATE NhaCC SET tenNCC=?, diaChi=?, maQuocGia=? WHERE maNCC=?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, ncc.getTenNCC());
	        statement.setString(2, ncc.getDiaChi());
	        statement.setString(3, ncc.getQuocGia());
	        statement.setString(4, ncc.getMaNCC());
	        int rowsAffected = statement.executeUpdate();
	        con.close();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public ArrayList<NhaCC> searchNhaCCByCode(String maNCC) throws Exception {
	    ArrayList<NhaCC> dsNCC = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getConection();
	        String sql = "SELECT * FROM NhaCC WHERE maNCC LIKE ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, "%" + maNCC + "%");
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            String tenNhaCC = rs.getString("tenNCC");
	            String diaChi = rs.getString("diaChi");
	            String quocGia = rs.getString("maQuocGia");
	            NhaCC ncc = new NhaCC(maNCC, tenNhaCC, diaChi, quocGia);
	            dsNCC.add(ncc);
	        }
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsNCC;
	}

//	public String tuPhatSinhMa() {
//		ArrayList<NhaCC> dsNCC = getAllNCC();
//		int soLuong = dsNCC.size() + 1;
//		return soLuong < 10 ? "NCC0000000" + soLuong
//				: soLuong < 100 ? "NCC000000" + soLuong
//				: "NCC00000" + soLuong;
//	}
	public String tuPhatSinhMa() {
	    // Get the current list of suppliers
	    ArrayList<NhaCC> dsNCC = getAllNCC();
	    
	    // Calculate the new ID
	    int soLuong = dsNCC.size() + 1;
	    
	    // Format the new ID with leading zeros
	    return String.format("NCC%07d", soLuong);
	}



}
