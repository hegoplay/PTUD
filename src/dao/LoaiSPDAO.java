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

public class LoaiSPDAO {
	public static LoaiSP GetLoaiSP(String maLoai) throws Exception {
		LoaiSP lsp = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from NhaCC where maNCC = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLoai);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenloai = rs.getNString(2);
				Boolean isDoTT = rs.getBoolean(3);
				lsp = new LoaiSP(maLoai, tenloai, isDoTT);
				
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsp;
	}
	public static ArrayList<LoaiSP> getAllLSP() {
		ArrayList<LoaiSP> dsLSP = new ArrayList<LoaiSP>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select *from LoaiSP";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				String maLoai = rs.getString(1);
				String tenloai = rs.getString(2);
				Boolean isDoTT = rs.getBoolean(3);
				LoaiSP lsp = new LoaiSP(maLoai, tenloai, isDoTT);
				dsLSP.add(lsp);

			}
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLSP;
	}
//	public boolean addNhaCC(NhaCC ncc) {
//	    try {
//	        Connection con = ConnectDB.getConection();
//	        String maNCC = ncc.getMaNCC();
//	        
//	        // Print or log the maNCC value for debugging
//	        System.out.println("maNCC value: " + maNCC);
//	        
//	        if (maNCC == null || maNCC.trim().isEmpty()) {
//	            System.err.println("Error: maNCC is null or empty");
//	            return false;
//	        }
//
//	        String sql = "INSERT INTO NhaCC (maNCC, tenNCC, diachi, maQuocGia) VALUES (?, ?, ?, ?)";
//	        PreparedStatement statement = con.prepareStatement(sql);
//	        statement.setString(1, maNCC);
//	        statement.setString(2, ncc.getTenNCC());
//	        statement.setString(3, ncc.getDiaChi());
//	        statement.setString(4, ncc.getQuocGia());
//	        int rowsAffected = statement.executeUpdate();
//	        con.close();
//	        return rowsAffected > 0;
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}
//	public boolean updateNhaCC(NhaCC ncc) {
//	    try {
//	        Connection con = ConnectDB.getConection();
//	        String sql = "UPDATE NhaCC SET tenNCC=?, diaChi=?, maQuocGia=? WHERE maNCC=?";
//	        PreparedStatement statement = con.prepareStatement(sql);
//	        statement.setString(1, ncc.getTenNCC());
//	        statement.setString(2, ncc.getDiaChi());
//	        statement.setString(3, ncc.getQuocGia());
//	        statement.setString(4, ncc.getMaNCC());
//	        int rowsAffected = statement.executeUpdate();
//	        con.close();
//	        return rowsAffected > 0;
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}
//	public ArrayList<NhaCC> searchNhaCCByCode(String maNCC) throws Exception {
//	    ArrayList<NhaCC> dsNCC = new ArrayList<>();
//	    try {
//	        Connection con = ConnectDB.getConection();
//	        String sql = "SELECT * FROM NhaCC WHERE maNCC LIKE ?";
//	        PreparedStatement statement = con.prepareStatement(sql);
//	        statement.setString(1, "%" + maNCC + "%");
//	        ResultSet rs = statement.executeQuery();
//	        while (rs.next()) {
//	            String tenNhaCC = rs.getString("tenNCC");
//	            String diaChi = rs.getString("diaChi");
//	            String quocGia = rs.getString("maQuocGia");
//	            NhaCC ncc = new NhaCC(maNCC, tenNhaCC, diaChi, quocGia);
//	            dsNCC.add(ncc);
//	        }
//	        con.close();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return dsNCC;
//	}

//	public String tuPhatSinhMa() {
//		ArrayList<NhaCC> dsNCC = getAllNCC();
//		int soLuong = dsNCC.size() + 1;
//		return soLuong < 10 ? "NCC0000000" + soLuong
//				: soLuong < 100 ? "NCC000000" + soLuong
//				: "NCC00000" + soLuong;
//	}
	public String tuPhatSinhMa() {
	    // Get the current list of suppliers
	    ArrayList<LoaiSP> dsLSP = getAllLSP();
	    
	    // Calculate the new ID
	    int soLuong = dsLSP.size() + 1;
	    
	    // Format the new ID with leading zeros
	    return String.format("NCC%07d", soLuong);
	}



}
