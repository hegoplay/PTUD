package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import connectDB.ConnectDB;

public class NhaCC {
	private String maNCC;
	private String tenNCC;
	private String diaChi;
	private String quocGia;
	@Override
	public int hashCode() {
		return Objects.hash(maNCC);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCC other = (NhaCC) obj;
		return Objects.equals(maNCC, other.maNCC);
	}
	public NhaCC(String maNCC, String tenNCC, String diaChi, String quocGia) throws Exception {
		this.maNCC = maNCC;
		try {
			this.setTenNCC(tenNCC);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDiaChi(diaChi);
		this.setQuocGia(quocGia);
	}
	public NhaCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) throws Exception {
		if (tenNCC.trim().equalsIgnoreCase(""))
			throw new Exception("Vui lòng nhập tên nhà cung cấp!");
		if (tenNCC.length() > 50)
			throw new Exception("Tên nhân viên không quá 50 ký tự");
		this.tenNCC = tenNCC;
	}
	public String getQuocGia() {
		return quocGia;
	}
//	public void setQuocGia(String quocGia) {
//		try {
//			Connection con = ConnectDB.getConection();
//			String query = "Select * from Countries where Iso = ?";
//			PreparedStatement statement = con.prepareStatement(query);
//			statement.setString(1, quocGia);
//			ResultSet rs =  statement.executeQuery();
//			if(rs.next()) {
//				this.quocGia = quocGia;
//			}
//			else throw new Exception("khong tim thay quoc gia");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) throws Exception {
	    if (diaChi.trim().isEmpty()) {
	        throw new Exception("Vui lòng nhập địa chỉ nhà cung cấp!");
	    }
	    this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "NhaCC [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", quocGia=" + quocGia + "]";
	}
}
