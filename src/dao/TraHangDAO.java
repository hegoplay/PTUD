package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietTraHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NguoiQuanLy;
import entity.PhieuTraHang;
import entity.SanPham;

public class TraHangDAO {
	public static boolean KiemTraTTPhieuTra(String maPhieu) {
		boolean res = false;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select count(*) from PhieuTraHang where maPhieu = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maPhieu);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0) {
					res = true;
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return res;
	}

	public static void ThemPhieuTraHang(PhieuTraHang phieuTH) throws Exception {

		if (phieuTH == null) {
			throw new Exception("Không tồn tại phiếu trả hàng");
		}
		if (phieuTH.getDsChiTiet().size() == 0) {
			throw new Exception("Phiếu trả hàng không được không có sản phẩm");
		}
		Connection con = ConnectDB.getConection();
		String sql = "insert into PhieuTraHang(maPhieu,maKH,maNQL,ngayTraHang,maHD) values (?,?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, phieuTH.getMaPhieu());
		statement.setString(2, phieuTH.getKhachHang().getMaKH());
		statement.setString(3, phieuTH.getNguoiQuanLy().getMaNV());
		statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
		statement.setString(5, phieuTH.getHoaDon().getMaHD());
		statement.executeUpdate();
		for (ChiTietTraHang ct : phieuTH.getDsChiTiet()) {
			sql = "insert into ChiTietTraHang values (?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, phieuTH.getMaPhieu());
			statement.setString(2, ct.getSanPham().getMaSP());
			statement.setInt(3, ct.getSoLuongSP());
			statement.executeUpdate();
		}
		con.close();

	}
	
	public static ArrayList<SanPham> GetSanPhamInDate(LocalDate startDate, LocalDate endDate){
		ArrayList<SanPham> res = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select distinct maSP from PhieuTraHang hd"
					+ "	inner join ChiTietTraHang ct on hd.maPhieu = ct.maPhieu"
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
	
	public static ArrayList<PhieuTraHang> GetPTHInDate(LocalDate startDate, LocalDate endDate){
		ArrayList<PhieuTraHang> res = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from PhieuTraHang pth"
					+ "	where (ngayTraHang between ? and ?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(startDate));
			statement.setDate(2, Date.valueOf(endDate));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maPhieu = rs.getString(1);
				KhachHang kh = KhachHangDAO.getKhachHang(rs.getString(2));
				NguoiQuanLy nql = NhanVienDAO.getNguoiQuanLy(rs.getString(3));
				HoaDon hd = HoaDonDAO.GetHoaDon(rs.getString(4));
				LocalDate ngayTraHang = rs.getDate(5).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				ArrayList<ChiTietTraHang> ctth = GetCTTH(maPhieu);
				PhieuTraHang pth = new PhieuTraHang(maPhieu, hd, ngayTraHang, nql, kh, ctth);
				res.add(pth);
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static ArrayList<ChiTietTraHang> GetCTTH(String maPhieu){
		ArrayList<ChiTietTraHang> lists = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from ChiTietTraHang"
					+ "where maPhieuTraHang = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maPhieu);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SanPham sp = SanPhamDAO.GetSanPham(rs.getString(2));
				int soLuong = rs.getInt(3);
				lists.add(new ChiTietTraHang(sp, soLuong));
			}
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
}
