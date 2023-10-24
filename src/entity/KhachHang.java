package entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String sdt;
	private int namSinh;
	private boolean gioiTinh;
	public KhachHang(String maKH) {
		this.setMaKH(maKH);
	}
	public KhachHang(String maKH, String tenKH, String diaChi, String sdt, int namSinh, boolean gioiTinh) {
		this.setMaKH(maKH);
		this.setTenKH(tenKH);
		this.setDiaChi(diaChi);
		this.setSdt(sdt);
		this.setNamSinh(namSinh);
		this.setGioiTinh(gioiTinh);
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	
	
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public int getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	
}
