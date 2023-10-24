package entity;

import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String ten;
	private String sdt;
	private String diaChi;
	private double luong;
	private String chucVu;
	private boolean isNam;
	private boolean dangLamViec;
	public NhanVien(String maNV) {
		this.setMaNV(maNV);
	}
	public NhanVien(String maNV, String ten, String sdt, String diaChi, double luong, String chucVu, boolean isNam,
			boolean dangLamViec) {
		this.setMaNV(maNV);
		this.setTen(ten);
		this.setSdt(sdt);
		this.setDiaChi(diaChi);
		this.setLuong(luong);
		this.setChucVu(chucVu);
		this.setNam(isNam);
		this.setDangLamViec(dangLamViec);
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getMaNV() {
		return maNV;
	}
	private void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public boolean isNam() {
		return isNam;
	}
	public void setNam(boolean isNam) {
		this.isNam = isNam;
	}
	public boolean isDangLamViec() {
		return dangLamViec;
	}
	public void setDangLamViec(boolean dangLamViec) {
		this.dangLamViec = dangLamViec;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ten=" + ten + ", sdt=" + sdt + ", diaChi=" + diaChi + ", luong=" + luong
				+ ", chucVu=" + chucVu + ", isNam=" + isNam + ", dangLamViec=" + dangLamViec + "]";
	}
	
	
}
