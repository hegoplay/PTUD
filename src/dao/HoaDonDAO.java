package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class HoaDonDAO {
	public static HoaDon GetHoaDon(String maHD) {
		HoaDon hd = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from HoaDon where maHD = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				LocalDateTime ngayLapHD = rs.getTimestamp(2).toLocalDateTime();
				String maNV = rs.getString(3);
				String maKH = rs.getString(4);
				
				float khuyenMai = rs.getFloat(5);
				double tienKhachDua = rs.getDouble(6);
				double tongHoaDon = rs.getDouble(7);
				NhanVien nv = NhanVienDAO.getNhanVien(maNV);
				KhachHang kh = KhachHangDAO.getKhachHang(maKH);
				ArrayList<ChiTietHoaDon> list = HoaDonDAO.GetDSCTHD(maHD);
				hd = new HoaDon(maHD, ngayLapHD, nv, kh, khuyenMai,tienKhachDua, list);
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hd;
	}
	public static ArrayList<ChiTietHoaDon> GetDSCTHD(String maHD) {
		ArrayList<ChiTietHoaDon> list = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from ChiTietHoaDon where hoaDon = ? order by maSP";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new ChiTietHoaDon(SanPhamDAO.GetSanPham(rs.getString(1)), rs.getInt(3)));
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static int GetSLSanPham(SanPham sp, LocalDate startDate, LocalDate endDate){
		int res = 0;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select sum(soLuong) from HoaDon hd "
					+ "inner join ChiTietHoaDon ct on hd.maHD = ct.hoaDon "
					+ "where (ngayLapHD between ? and ?) and maSP = ? "
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
	
	public static ArrayList<SanPham> GetSanPhamInDate(LocalDate startDate, LocalDate endDate){
		ArrayList<SanPham> res = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select distinct maSP from HoaDon hd"
					+ "	inner join ChiTietHoaDon ct on hd.maHD = ct.hoaDon"
					+ "	where (ngayLapHD between ? and ?) order by maSP";
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
	public static int GetSLHDCuaNV(NhanVien nv, LocalDate startDate, LocalDate endDate) {
		int res = 0;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select count(*) from HoaDon hd \r\n"
					+ "where maNV = ? and (ngayLapHD between ? and ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(2, Date.valueOf(startDate));
			statement.setDate(3, Date.valueOf(endDate));
			statement.setString(1, nv.getMaNV());
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
	
	
	public static int GetTongSPNV(NhanVien nv, LocalDate startDate, LocalDate endDate) {
		int res = 0;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select sum(soLuong) from HoaDon hd \r\n"
					+ "inner join ChiTietHoaDon cthd on hd.maHD = cthd.hoaDon "
					+ "where maNV = ? and (ngayLapHD between ? and ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(2, Date.valueOf(startDate));
			statement.setDate(3, Date.valueOf(endDate));
			statement.setString(1, nv.getMaNV());
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
	
	public static double GetTongDT(LocalDate startDate, LocalDate endDate) {
		double res = 0;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select sum(tongHoaDon) from HoaDon hd \r\n"
					+ "where (ngayLapHD between ? and ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(startDate));
			statement.setDate(2, Date.valueOf(endDate));
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				res = rs.getDouble(1);
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static ArrayList<HoaDon> GetHoaDonInDate(LocalDate startDate, LocalDate endDate){
		ArrayList<HoaDon> res = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from HoaDon"
					+ "	where (ngayLapHD between ? and ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(startDate));
			statement.setDate(2, Date.valueOf(endDate));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd;
				String maHD = rs.getString(1);
				LocalDateTime ngayLapHD = rs.getTimestamp(2).toLocalDateTime();
				String maNV = rs.getString(3);
				String maKH = rs.getString(4);
				float khuyenMai = rs.getFloat(5);
				double tienKhachDua = rs.getDouble(6);
				NhanVien nv = NhanVienDAO.getNhanVien(maNV);
				KhachHang kh = KhachHangDAO.getKhachHang(maKH);
				ArrayList<ChiTietHoaDon> list = HoaDonDAO.GetDSCTHD(maHD);
				hd = new HoaDon(maHD, ngayLapHD, nv, kh, khuyenMai,tienKhachDua, list);
				res.add(hd);
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static double GetTongDTNV(NhanVien nv, LocalDate startDate, LocalDate endDate) {
		double res = 0;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select dbo.getTongDTNV (?,?,?)";
//			PreparedStatement statement = con.prepareStatement(sql);
//			statement.setDate(2, Date.valueOf(startDate));
//			statement.setDate(3, Date.valueOf(endDate));
//			statement.setString(1, nv.getMaNV());
			int i = 0;
			CallableStatement statement = con.prepareCall(sql);
			statement.setString(++i, nv.getMaNV());
			statement.setDate(++i, Date.valueOf(startDate));
			statement.setDate(++i, Date.valueOf(endDate));
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				res = rs.getDouble(1);
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static ArrayList<HoaDon> getAllHoaDon() throws Exception {
	    ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement statement = con.prepareStatement("SELECT * FROM HoaDon");
	         ResultSet rs = statement.executeQuery()) {

	        while (rs.next()) {
	            String maHD = rs.getString("maHD");
	            LocalDateTime ngayLapHD = rs.getTimestamp("ngayLapHD").toLocalDateTime();
	            String maNV = rs.getString("maNV");
	            String maKH = rs.getString("maKH");
	            float khuyenMai = rs.getFloat("coKhuyenMai");
	            double tienKhachDua = rs.getDouble("tienKhachDua");
	            double tongHoaDon = rs.getDouble("tongHoaDon");
	            String ghiChu = rs.getString("ghiChu");

	            NhanVien nv = NhanVienDAO.getNhanVien(maNV);
	            KhachHang kh = KhachHangDAO.getKhachHang(maKH);
	            ArrayList<ChiTietHoaDon> list = HoaDonDAO.GetDSCTHD(maHD);

	            dsHoaDon.add(new HoaDon(maHD, ngayLapHD, nv, kh, khuyenMai, tienKhachDua, list));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsHoaDon;
	}
	
}
