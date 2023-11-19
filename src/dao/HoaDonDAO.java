package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class HoaDonDAO {
	
	public static ArrayList<HoaDon> getHoaDonByMaNVinToDay(String maNV) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();

        try (Connection con = ConnectDB.getConection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM HoaDon WHERE maNV = ? AND CAST(ngayLapHD AS DATE) = ?");
        ) {
            LocalDate ngayHienHanh = LocalDate.now();
            stmt.setString(1, maNV);
            stmt.setDate(2, java.sql.Date.valueOf(ngayHienHanh));

            try (ResultSet rs = stmt.executeQuery()) {                  
        	        while (rs.next()) {
        	            String maHD = rs.getString("maHD");
        	            LocalDateTime ngayLapHD = rs.getTimestamp("ngayLapHD").toLocalDateTime();
        	            String mNV = rs.getString("maNV");
        	            String maKH = rs.getString("maKH");
        	            float khuyenMai = rs.getFloat("coKhuyenMai");
        	            double tienKhachDua = rs.getDouble("tienKhachDua");
        	            String ghiChu = rs.getString("ghiChu");

        	            NhanVien nv = NhanVienDAO.getNhanVien(mNV);
        	            KhachHang kh = KhachHangDAO.getKhachHang(maKH);
        	            ArrayList<ChiTietHoaDon> list = HoaDonDAO.GetDSCTHD(maHD);

        	            listHoaDon.add(new HoaDon(maHD, ngayLapHD, nv, kh, khuyenMai, tienKhachDua, list));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listHoaDon;
	}	
	
	public static boolean ThemHD(HoaDon hdon) throws Exception {
	    Connection con = ConnectDB.getConection();
	    con.setAutoCommit(false);

	    PreparedStatement statement1 = null;
	    PreparedStatement statement2 = null;

	    try {
	        String sql1 = "INSERT INTO HoaDon (maHD, ngayLapHD, maNV, maKH, coKhuyenMai, tienKhachDua, tongHoaDon) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        statement1 = con.prepareStatement(sql1);
	        statement1.setString(1, hdon.getMaHD());
	        statement1.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
	        statement1.setString(3, hdon.getNhanVien().getMaNV());
	        statement1.setString(4, hdon.getKhachHang().getMaKH());
	        statement1.setFloat(5, hdon.getKhuyenMai());
	        statement1.setDouble(6, hdon.getTienKhachDua());
	        statement1.setDouble(7, hdon.TinhTongTien());
	        System.out.println(hdon.TinhTongTien());
	        statement1.executeUpdate();
	        // Lấy maHD vừa được tạo
	        String maHD = hdon.getMaHD();

	        // Thêm chi tiết hóa đơn
	        for (ChiTietHoaDon ct : hdon.getDsCTHD()) {
	            String sql2 = "INSERT INTO ChiTietHoaDon (maSP, hoaDon, soLuong) VALUES (?, ?, ?)";
	            statement2 = con.prepareStatement(sql2);
	            statement2.setString(1, ct.getSanPham().getMaSP());
	            statement2.setString(2, maHD);
	            statement2.setInt(3, ct.getSoLuong());
	            statement2.executeUpdate();
	        }

	        // Commit transaction nếu mọi thứ thành công
	        con.commit();
	        return true;
	    } catch (SQLException e) {
	        // In hoặc xử lý lỗi
	        e.printStackTrace();

	        // Rollback transaction nếu có lỗi
	        try {
	            con.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            // Đặt lại chế độ tự động commit
	            con.setAutoCommit(true);

	            // Đóng các statement
	            if (statement1 != null) {
	                statement1.close();
	            }
	            if (statement2 != null) {
	                statement2.close();
	            }
	            // Đóng kết nối
	            con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public static ArrayList<ChiTietHoaDon> getDSCTHDFromList(ArrayList<HoaDon> listHoaDon) {
	    ArrayList<ChiTietHoaDon> listDSCTHD = new ArrayList<>();

	    for (HoaDon hoaDon : listHoaDon) {
	        // Lấy danh sách chi tiết hóa đơn từ mỗi hóa đơn
	        ArrayList<ChiTietHoaDon> dsCTHD = hoaDon.getDsCTHD();
	        listDSCTHD.addAll(dsCTHD);
	    }

	    return listDSCTHD;
	}
	
	public static double getDoanhThuNgayTheoMaNV(String maNV) {
	    double tongDoanhThu = 0;

	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement stmt = con.prepareStatement(
	                 "SELECT SUM(tongHoaDon) AS tongDoanhThu FROM HoaDon WHERE maNV = ? AND CAST(ngayLapHD AS DATE) = ?"
	         );
	    ) {
	        LocalDate ngayHienHanh = LocalDate.now();
	        stmt.setString(1, maNV);
	        stmt.setDate(2, java.sql.Date.valueOf(ngayHienHanh));

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                tongDoanhThu = rs.getDouble("tongDoanhThu");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return tongDoanhThu;
	}
	
	public static double getTongTienByMaHD(String maHD) {
	    double tongTien = 0;

	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement statement = con.prepareStatement("SELECT tongHoaDon FROM HoaDon WHERE maHD = ?")) {

	        statement.setString(1, maHD);
	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {
	            tongTien = rs.getDouble("tongHoaDon");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tongTien;
	}
	// Phương thức lấy khuyến mãi của hoa đơn theo maHD
    public static double getKhuyenMaiByMaHD(String maHD) throws SQLException {
        double khuyenMai = 0;
        float km = 0;

        try (Connection con = ConnectDB.getConection();
             PreparedStatement statement = con.prepareStatement("SELECT coKhuyenMai FROM HoaDon WHERE maHD = ?");
        ) {
            statement.setString(1, maHD);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    km = rs.getFloat("coKhuyenMai");
                    khuyenMai = (getTongTienByMaHD(maHD)/(1-km))*km;
                }
            }
        }

        return khuyenMai;
    }
	
	public static ArrayList<ChiTietHoaDon> processDSCTHD(ArrayList<ChiTietHoaDon> listDSCTHD) throws Exception {
	    Map<String, ChiTietHoaDon> mapChiTietHoaDon = new HashMap<>();
	    for (ChiTietHoaDon chiTietHoaDon : listDSCTHD) {
	        String maSP = chiTietHoaDon.getSanPham().getMaSP();

	        // Kiểm tra xem mã sản phẩm đã tồn tại trong Map chưa
	        if (mapChiTietHoaDon.containsKey(maSP)) {
	            // tồn tại, tăng số lượng lên 1
	            ChiTietHoaDon existingChiTiet = mapChiTietHoaDon.get(maSP);
	            existingChiTiet.tangSoLuong (existingChiTiet.getSoLuong() + 1);
	        } else {
	            // Nếu chưa tồn tại
	            mapChiTietHoaDon.put(maSP, chiTietHoaDon);
	        }
	    }
	    return new ArrayList<>(mapChiTietHoaDon.values());
	}
	
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
	public static String taoMaHD() {
	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement stGetNumCount = con.prepareStatement("SELECT count(*) FROM HoaDon");
	    ) {
	        String maHD = "HD";
	        ResultSet rs = stGetNumCount.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) < 1e8) {
	        	return maHD + String.format("%08d", rs.getInt(1) + 1);
	        }
	        else {
	        	return null;
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; 
	}
	
	public static boolean themHD(HoaDon k) {
	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement stmt = con.prepareStatement(
	                 "INSERT INTO HoaDon (maHD, ngayLapHD, maNV, maKH, coKhuyenMai, tienKhachDua) " +
	                         "VALUES (?, ?, ?, ?, ?, ?)")) {
	        stmt.setString(1, k.getMaHD());
	        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(k.getNgayLapHD());
	        stmt.setTimestamp(2, timestamp);
	        stmt.setString(3, k.getNhanVien().getMaNV());
	        stmt.setString(4, k.getKhachHang().getMaKH());
	        stmt.setDouble(5, k.getKhuyenMai());
	        stmt.setDouble(6, k.getTienKhachDua());
	        stmt.executeUpdate();
//	        for (ChiTietHoaDon ct : k.getDsCTHD()) {
////	        	        					            String sql2 = "INSERT INTO ChiTietHoaDon (maSP, hoaDon, soLuong) VALUES (?, ?, ?)";
////	        						            statement2 = con.prepareStatement(sql2);
////	        					            statement2.setString(1, ct.getSanPham().getMaSP());
////	        						            statement2.setString(2, maHD);
////	        					            statement2.setInt(3, ct.getSoLuong());
////	        					            statement2.executeUpdate();
////	        					        };
//	        	
//	        }
//	        con.commit();
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("23505")) {
	            JOptionPane.showMessageDialog(null, "Mã HD bị trùng");
	        } else {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    return true;
	}
}
