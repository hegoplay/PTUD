package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;

public class CthdDAO {
	public static boolean themChiTietHoaDon(String maHD, ChiTietHoaDon cthd) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectDB.getConection();
            String sql = "INSERT INTO ChiTietHoaDon (maSP, maHD, soLuong) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maHD);
            stmt.setString(2, cthd.getSanPham().getMaSP());
            stmt.setInt(3,cthd.getSoLuong());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt, con);
        }

        return true; // true nếu thành công
    }

//	public static ArrayList<ChiTietHoaDon> getChiTietHoaDonByMaHD(String maHD) {
//        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
//
//        try (Connection con = ConnectDB.getConection();
//             PreparedStatement statement = con.prepareStatement("SELECT * FROM ChiTietHoaDon WHERE maHD = ?")) {
//
//            statement.setString(1, maHD);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                String maSP = rs.getString("maSP");
//                int soLuong = rs.getInt("soLuong");
//
//                // Tạo đối tượng ChiTietHoaDon và thêm vào danh sách
//                ChiTietHoaDon cthd = new ChiTietHoaDon(maSP, maHD, soLuong);
//                dsCTHD.add(cthd);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return dsCTHD;
//    }

	public static int getSoLuongByMaHDAndMaSP(String maHD, String maSP) {
        int soLuong = 0;

        try (Connection con = ConnectDB.getConection();
             PreparedStatement statement = con.prepareStatement(
                     "SELECT soLuong FROM ChiTietHoaDon WHERE maHD = ? AND maSP = ?")) {

            statement.setString(1, maHD);
            statement.setString(2, maSP);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt("soLuong");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soLuong;
    }
    private static void closeResources(PreparedStatement stmt, Connection con) {
        // Đóng PreparedStatement
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Đóng Connection
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
