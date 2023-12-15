package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.LoaiSP;
import entity.NhaCC;
import entity.SanPham;

public class KhuyenMaiDAO {
	
	public static ArrayList<SanPham> getDsSPbyMaKM(String maKM){
	    ArrayList<SanPham> dsSanPham = new ArrayList<>();

	        try {
				Connection con = ConnectDB.getConection();

	            String sql = "SELECT maSP FROM KhuyenMai WHERE maKM = ?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, maKM);
				ResultSet rs = statement.executeQuery();

	            while (rs.next()) {
	                String maSP = rs.getString("maSP");

	                // Tạo đối tượng SanPham từ mã sản phẩm
	                SanPham sanPham = SanPhamDAO.GetSanPham(maSP);

	                // Thêm vào danh sách
	                dsSanPham.add(sanPham);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 

	        return dsSanPham;
	    }

	public static KhuyenMai GetKM(String maCT) {
		KhuyenMai km = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "select * from KhuyenMai where maKM = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maCT);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenCT = rs.getNString(2);
				LocalDateTime ngayBD = rs.getTimestamp(3).toLocalDateTime();
				LocalDateTime ngayKT = rs.getTimestamp(4).toLocalDateTime();
				String loaiKM = rs.getString(5);
				double mucKM = rs.getDouble(6);
				
				ArrayList<SanPham> dsSP = getDsSPbyMaKM(maCT);
				String maSP= rs.getString(7);
				
				
				km = new KhuyenMai(maCT, tenCT, ngayBD, ngayKT, loaiKM, mucKM,dsSP);
			}
			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return km;
	}

	public static ArrayList<KhuyenMai> getAllKhuyenMai() {
	    ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();

	    try (Connection con = ConnectDB.getConection();
	         Statement statement = con.createStatement();
	         ResultSet rs = statement.executeQuery("SELECT * FROM KhuyenMai")) {
	    	KhuyenMai km = null;
	        while (rs.next()) {
	        	String maCT = rs.getString("maKM");
	        	if(km != null && km.getMaChuongTrinh()!= maCT) {
	        		dsKhuyenMai.add(km);
	        		km = null;
	        	}
	        	if(km == null) {
	        		int i =2;
	        		km = new KhuyenMai(
		            		maCT,
		            		rs.getNString(i++) , 
		            		rs.getTimestamp(i++).toLocalDateTime(),
		            		rs.getTimestamp(i++).toLocalDateTime(),
		            		rs.getString(i++),
		            		rs.getDouble(i++),
		            		new ArrayList<SanPham>());
	        	}
	        	km.getDsSP().add(new SanPham(rs.getString(7)));
	            
	        }
	        if(km != null) {
        		dsKhuyenMai.add(km);
        		km = null;
        	}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dsKhuyenMai;
	}


	public static ArrayList<String> getAllTenLoaiSP() {
		ArrayList<String> dsTenLoaiSP = new ArrayList<>();

	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement statement = con.prepareStatement("SELECT tenLoai FROM LoaiSP");
	         ResultSet rs = statement.executeQuery()) {

	        while (rs.next()) {
	            String tenLoai = rs.getString("tenLoai");
	            dsTenLoaiSP.add(tenLoai);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dsTenLoaiSP;
	}

	public static String taoMaKM() {
	    try (Connection con = ConnectDB.getConection();
	         Statement st = con.createStatement();
	    ) {
	        String maKM = "KM";

	        // Lặp để kiểm tra mã có tồn tại trong danh sách hay không
	        for (int i = 1; i < 1e8; i++) {
	            String newCode = String.format("%08d", i);
	            String checkQuery = "SELECT COUNT(*) FROM KhuyenMai WHERE MaKM = '" + maKM + newCode + "'";
	            ResultSet rs = st.executeQuery(checkQuery);

	            if (rs.next() && rs.getInt(1) == 0) {
	                // Nếu không tìm thấy mã trong danh sách, trả về mã mới
	                return maKM + newCode;
	            }
	        }

	        // Nếu đã kiểm tra hết tất cả các mã có thể tạo mà không tìm thấy, trả về mã mới có thứ tự cuối cùng
	        int currentCount = (int) st.executeQuery("SELECT COUNT(*) FROM KhuyenMai").getInt(1);
	        return maKM + String.format("%08d", currentCount + 1);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	
	
	public static boolean luuKM(KhuyenMai khuyenMai, List<SanPham> dsSP) {
	    String sql = "INSERT INTO KhuyenMai (maKM, tenChuongTrinh, ngayBatDau, ngayKetThuc, loaiKM, mucKM, maSP) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = ConnectDB.getConection();
	         PreparedStatement statement = conn.prepareStatement(sql)) {

	        statement.setString(1, khuyenMai.getMaChuongTrinh());
	        statement.setString(2, khuyenMai.getTenChuongTrinh());
	        statement.setDate(3, java.sql.Date.valueOf(khuyenMai.getNgayBatDau().toLocalDate()));
	        statement.setDate(4, java.sql.Date.valueOf(khuyenMai.getNgayKetThuc().toLocalDate()));
	        statement.setString(5, khuyenMai.getLoaiKhuyenMai());
	        statement.setDouble(6, khuyenMai.getMucGiamGia());

	        for (SanPham sanPham : dsSP) {
	            statement.setString(7, sanPham.getMaSP());
	            statement.addBatch();
	        }

	        int[] result = statement.executeBatch();

	        // Kiểm tra kết quả của từng lệnh insert
	        for (int i : result) {
	            if (i <= 0) {
	                return false;  // Có ít nhất một lệnh không thành công
	            }
	        }

	        return true;  // Tất cả các lệnh insert đều thành công
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Hiển thị thông báo lỗi
	        JOptionPane.showMessageDialog(null, "Lỗi khi lưu vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;  // Lỗi khi thực hiện lệnh SQL
	    }
	}

	public static boolean coChuongTrinhSale(String maSP) {
	    String maKM = SanPhamDAO.layMaKhuyenMaiChoSanPham(maSP);
	    if (maKM != null) {
	        // Kiểm tra xem chương trình khuyến mãi có hiệu lực hay không
	        LocalDateTime ngayHienTai = LocalDateTime.now();
	        String sql = "SELECT COUNT(*) FROM KhuyenMai WHERE maKM = ? AND ? >= ngayBatDau AND ? <= ngayKetThuc";
	        try (Connection connection = ConnectDB.getConection();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, maKM);
	            preparedStatement.setObject(2, ngayHienTai);
	            preparedStatement.setObject(3, ngayHienTai);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    int count = resultSet.getInt(1);
	                    return count > 0;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	public static double layMucGiamChoKhuyenMai(String maKM) {
	    double mucGiam = 0.0;

	    String sql = "SELECT mucKM FROM KhuyenMai WHERE maKM = ?";

	    try (Connection connection = ConnectDB.getConection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, maKM);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                mucGiam = resultSet.getDouble("mucKM");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return mucGiam;
	}

	public static void capNhatThongTin(String maKM, String tenChuongTrinh, LocalDate ngayBatDau, LocalDate ngayKetThuc, double mucGiam) {
	    String sql = "UPDATE KhuyenMai SET tenChuongTrinh = ?, ngayBatDau = ?, ngayKetThuc = ?, mucKM = ? WHERE maKM = ?";

	    try (Connection connection = ConnectDB.getConection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        // Thiết lập các tham số trong câu lệnh SQL
	        preparedStatement.setString(1, tenChuongTrinh);
	        preparedStatement.setDate(2, Date.valueOf(ngayBatDau));
	        preparedStatement.setDate(3, Date.valueOf(ngayKetThuc));
	        preparedStatement.setDouble(4, mucGiam);
	        preparedStatement.setString(5, maKM);

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	
}