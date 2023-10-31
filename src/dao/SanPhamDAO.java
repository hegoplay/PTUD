package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiSP;
import entity.NhaCC;
import entity.NhanVien;
import entity.SanPham;

public class SanPhamDAO {
	public static SanPham GetSanPham(String maSP) {
		SanPham sp = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select * from SanPham where maSP = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenSP = rs.getNString(2);
				double giaNhap = rs.getDouble(3);
				int slTonKho = rs.getInt(4);
				String kichThuoc = rs.getString(5);
				String mauSac = rs.getString(6);
				boolean isNam = rs.getBoolean(7);
				boolean conKinhDoanh = rs.getBoolean(8);
				String maLoaiSP = rs.getString(9);
				String hinhAnh = rs.getNString(10);
				String maNCC = rs.getString(11);
				float thue = rs.getFloat(12);
				NhaCC ncc = NhaCCDAO.GetNhaCC(maNCC);
				LoaiSP lsp = SanPhamDAO.GetLoaiSP(maLoaiSP);
				sp = new SanPham(maSP, tenSP, giaNhap, slTonKho, kichThuoc, mauSac, conKinhDoanh, isNam, ncc, hinhAnh, thue, lsp);
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp;
	}
	
	public static LoaiSP GetLoaiSP(String maLoai) {
		LoaiSP lsp = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from LoaiSP where maLoai = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLoai);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenLoai = rs.getNString(2);
				boolean isDoTT = rs.getBoolean(3);
				lsp = new LoaiSP(maLoai, tenLoai, isDoTT);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsp;
	}
	
	
}
