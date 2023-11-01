package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import connectDB.ConnectDB;
import entity.ChiTietTraHang;
import entity.PhieuTraHang;

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
}
