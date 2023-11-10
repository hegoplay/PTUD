package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NguoiQuanLy;
import entity.NhaCC;
import entity.NhanVien;

public class NhanVienDAO {
	public static NhanVien getNhanVien(String maNV) {
        NhanVien nv = null;
        try {
            Connection con = ConnectDB.getConection();
            String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maNV);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String tenNV = rs.getNString(2);
                boolean isNam = rs.getBoolean(3);
                String diaChi = rs.getNString(4);
                String sdt = rs.getString(5);
                String email = rs.getString(6);
                double luong = rs.getDouble(7);
                boolean chucVu = rs.getBoolean(8);
                boolean dangLamViec = rs.getBoolean(9);
                String cuaHangQL = rs.getString(12);

                if (chucVu) {
                    nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL);
                } else {
                    nv = new NguoiQuanLy(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL);

                    // Add cuaHangQL to the SQL query
                    sql = "SELECT * FROM NhanVien WHERE maNQL = ? AND chucVu = 0";
                    statement = con.prepareStatement(sql);
                    statement.setString(1, maNV);
                    rs = statement.executeQuery();

                    ArrayList<NhanVien> dsNv = new ArrayList<>();
                    while (rs.next()) {
                        tenNV = rs.getNString(2);
                        isNam = rs.getBoolean(3);
                        diaChi = rs.getNString(4);
                        sdt = rs.getString(5);
                        email = rs.getString(6);
                        luong = rs.getDouble(7);
                        chucVu = rs.getBoolean(8);
                        dangLamViec = rs.getBoolean(9);
                        cuaHangQL = rs.getString(12);
                        dsNv.add(new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL));
                    }
                }
                return nv;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

	public static NguoiQuanLy getNguoiQuanLy(String maNQL) {
		NguoiQuanLy nv = null;
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from NhanVien where maNV = ? and chucVu = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maNQL);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tenNV = rs.getNString(2);
				boolean isNam = rs.getBoolean(3);
				String diaChi = rs.getNString(4);
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				double luong = rs.getDouble(7);
				boolean chucVu = rs.getBoolean(8);
				boolean dangLamViec = rs.getBoolean(9);
				String cuaHangQL = rs.getString(12);
				nv = new NguoiQuanLy(maNQL, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL);
				sql = "Select * from NhanVien where maNQL = ? and chucVu = 1";
				statement = con.prepareStatement(sql);
				statement.setString(1, maNQL);
				rs = statement.executeQuery();
				ArrayList<NhanVien> dsNv = new ArrayList<>();
				while (rs.next()) {
					String maNV = rs.getString(1);
					tenNV = rs.getNString(2);
					isNam = rs.getBoolean(3);
					diaChi = rs.getNString(4);
					sdt = rs.getString(5);
					email = rs.getString(6);
					luong = rs.getDouble(7);
					chucVu = rs.getBoolean(8);
					dangLamViec = rs.getBoolean(9);
					dsNv.add(new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec));

				}

				return nv;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
	}
	
	public static ArrayList<NhanVien> getAllNhanVien(){
		ArrayList<NhanVien> lists= new ArrayList<>();
		try {
			Connection con = ConnectDB.getConection();
			String sql = "Select * from NhanVien";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getNString(2);
				boolean isNam = rs.getBoolean(3);
				String diaChi = rs.getNString(4);
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				double luong = rs.getDouble(7);
				boolean chucVu = rs.getBoolean(8);
				boolean dangLamViec = rs.getBoolean(9);
				String cuaHangQL = rs.getString(12);
	
				NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL);
				lists.add(nv);
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	public static boolean addNhanVien(NhanVien nv) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String sql = "INSERT INTO NhanVien (maNV, tenNV, isNam, diaChi, sdt, email, luong, chucVu, dangLamViec, cuaHangQL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, nv.getMaNV());
	        statement.setString(2, nv.getTen());
	        statement.setBoolean(3, nv.isNam());
	        statement.setString(4, nv.getDiaChi());
	        statement.setString(5, nv.getSdt());
	        statement.setString(6, nv.getEmail());
	        statement.setDouble(7, nv.getLuong());
	        statement.setBoolean(8, nv.isNQL());
	        statement.setBoolean(9, nv.isDangLamViec());
	        statement.setString(10, nv.getCuaHangQL());

	        int rowsInserted = statement.executeUpdate();
	        return rowsInserted > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public static boolean updateNhanVien(NhanVien nv) {
	    try {
	        Connection con = ConnectDB.getConection();
	        String sql = "UPDATE NhanVien SET tenNV = ?, isNam = ?, diaChi = ?, sdt = ?, email = ?, luong = ?, chucVu = ?, dangLamViec = ?, cuaHangQL = ? WHERE maNV = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, nv.getTen());
	        statement.setBoolean(2, nv.isNam());
	        statement.setString(3, nv.getDiaChi());
	        statement.setString(4, nv.getSdt());
	        statement.setString(5, nv.getEmail());
	        statement.setDouble(6, nv.getLuong());
	        statement.setBoolean(7, nv.isNQL());
	        statement.setBoolean(8, nv.isDangLamViec());
	        statement.setString(9, nv.getCuaHangQL());
	        statement.setString(10, nv.getMaNV());

	        int rowsUpdated = statement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public static ArrayList<NhanVien> findNhanVienByMa(String maNV) {
	    ArrayList<NhanVien> dsNv = new ArrayList<>();
	    try (Connection con = ConnectDB.getConection();
	         PreparedStatement statement = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?")) {
	        statement.setString(1, maNV);

	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
	                String tenNV = rs.getNString("tenNV");
	                boolean isNam = rs.getBoolean("isNam");
	                String diaChi = rs.getNString("diaChi");
	                String sdt = rs.getString("sdt");
	                String email = rs.getString("email");
	                double luong = rs.getDouble("luong");
	                boolean chucVu = rs.getBoolean("chucVu");
	                boolean dangLamViec = rs.getBoolean("dangLamViec");
	                String cuaHangQL = rs.getString("cuaHangQL");

	                dsNv.add(new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, isNam, dangLamViec, cuaHangQL));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsNv;
	}
	public String tuPhatSinhMa() {
	    // Get the current list of suppliers
	    ArrayList<NhanVien> dsNV = getAllNhanVien();
	    
	    // Calculate the new ID
	    int soLuong = dsNV.size() + 1;
	    
	    // Format the new ID with leading zeros
	    return String.format("NV%08d", soLuong);
	}



}
