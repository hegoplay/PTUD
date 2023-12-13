package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
				sp = new SanPham(maSP, tenSP, giaNhap, slTonKho, kichThuoc, mauSac, conKinhDoanh, isNam, ncc, hinhAnh,
						thue, lsp);
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

	public static LoaiSP GetLoaiSPByTen(String tenLoai) {
		LoaiSP lsp = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from LoaiSP where maLoai = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tenLoai);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String maLoai = rs.getNString(1);
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
	
	public static ArrayList<SanPham> getAllSP() {
		ArrayList<SanPham> lists = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select * from SanPham order by maSP";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString(1);
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
				SanPham sp = new SanPham(maSP, tenSP, giaNhap, slTonKho, kichThuoc, mauSac, conKinhDoanh, isNam, ncc, hinhAnh,
						thue, lsp);
				lists.add(sp);
			}
			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	
	public static ArrayList<SanPham> getAllSanPham(){
		ArrayList<SanPham> lists= new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from SanPham"
					+ " inner join NhaCC on SanPham.MaNCC = NhaCC.maNCC"
					+ " inner join LoaiSP on LoaiSP.maLoai = SanPham.maLoaiSP";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString(1);
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
				NhaCC ncc = new NhaCC(maNCC, rs.getString(14), rs.getNString(15), rs.getString(16));
				LoaiSP lsp = new LoaiSP(maLoaiSP, rs.getNString(18), rs.getBoolean(19));
				SanPham sp = new SanPham(maSP, tenSP, giaNhap, slTonKho, kichThuoc, mauSac, conKinhDoanh, isNam, ncc, hinhAnh, thue, lsp);
				lists.add(sp);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	public static boolean addSanPham(SanPham sp) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String maSP = sp.getMaSP();
	        String sql = "INSERT INTO SanPham (maSP, tenSP, giaNhap, slTonKho, kichThuoc, mauSac, isNam, ConKinhDoanh,maLoaiSP, hinhAnh, MaNCC) VALUES (?,? , ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = con.prepareStatement(sql);
	        int i = 1;
	        statement.setString(i++, maSP);
	        statement.setString(i++, sp.getTenSP());
	        statement.setDouble(i++, sp.getGiaNhap());
	        statement.setInt(i++, sp.getSlTonKho());
	        statement.setString(i++, sp.getKichThuoc());
	        statement.setString(i++, sp.getMauSac());
	        statement.setBoolean(i++, sp.isNam());
	        statement.setBoolean(i++, sp.isConKinhDoanh());
	        statement.setString(i++, sp.getLoaiSP().getMaLoai());
	        statement.setString(i++, sp.getHinhAnh());
	        statement.setString(i++, sp.getNhaCC().getMaNCC());
	        //statement.setFloat(10,sp.getThue());
	        //statement.setString(11, sp.getLoaiSP());
	        int rowsAffected = statement.executeUpdate();
	        con.close();
	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean updateSanPham(SanPham sp) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String sql = "UPDATE SanPham SET tenSP = ?, giaNhap = ?, slTonKho = ?, kichThuoc = ?, mauSac = ?,isNam =?, conKinhDoanh = ?, hinhAnh = ?, MaNCC = ? WHERE maSP = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, sp.getTenSP());
	        statement.setDouble(2, sp.getGiaNhap());
	        statement.setInt(3, sp.getSlTonKho());
	        statement.setString(4, sp.getKichThuoc());
	        statement.setString(5, sp.getMauSac());
	        statement.setBoolean(6, sp.isNam());
	        statement.setBoolean(7, sp.isConKinhDoanh());
	        statement.setString(8, sp.getHinhAnh());
	        statement.setString(9, sp.getNhaCC().getMaNCC());
	        //statement.setFloat(10,sp.getThue());
	        //statement.setString(11, sp.getLoaiSP());
			statement.setString(10, sp.getMaSP());

	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    
	}
	
	public static ArrayList<SanPham> findSanPhamByMa(String maSP) {
	    ArrayList<SanPham> dsSp = new ArrayList<>();
	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement statement = con.prepareStatement("SELECT * FROM SanPham WHERE maSP = ?")) {
	        statement.setString(1, maSP);

	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
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


					dsSp.add(new SanPham(maSP, tenSP, giaNhap, slTonKho, kichThuoc, mauSac, conKinhDoanh, isNam, ncc, hinhAnh, thue, lsp));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsSp;
	}

	public String tuPhatSinhMa() {
	    // Get the current list of suppliers
	    ArrayList<SanPham> dsSP = getAllSanPham();

	    // Calculate the new ID
	    int soLuong = dsSP.size() + 1;

	    // Format the new ID with leading zeros
	    return String.format("SP%08d", soLuong);
	}
	
}
