package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class LishSuTon {
	private String maLSTon;
	private int soLuongNhap;
	private LocalDateTime lichSuTon;
	private SanPham sanPham;
	public LishSuTon(String maLSTon, int soLuongNhap, LocalDateTime lichSuTon, SanPham sanPham) {
		this.maLSTon = maLSTon;
		this.setSoLuongNhap(soLuongNhap);
		this.setLichSuTon(lichSuTon);
		this.setSanPham(sanPham);
	}
	public LishSuTon(String maLSTon) {
		this.maLSTon = maLSTon;
	}
	public LocalDateTime getLichSuTon() {
		return lichSuTon;
	}
	private void setLichSuTon(LocalDateTime lichSuTon) {
		this.lichSuTon = lichSuTon;
	}
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	private void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	private void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLSTon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LishSuTon other = (LishSuTon) obj;
		return Objects.equals(maLSTon, other.maLSTon);
	}
	
	
	@Override
	public String toString() {
		return "LishSuTon [maLSTon=" + maLSTon + ", soLuongNhap=" + soLuongNhap + ", lichSuTon=" + lichSuTon
				+ ", sanPham=" + sanPham + "]";
	}
	public double TinhTongTienNhap() {
		return 0;
	}
	
}
