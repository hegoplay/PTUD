package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class LishSuTon {
	private String maLSTon;
	private int soLuongNhap;
	private LocalDateTime ngayThayDoi;
	private SanPham sanPham;
	public LishSuTon(String maLSTon, int soLuongNhap, LocalDateTime ngayThayDoi, SanPham sanPham) throws Exception {
		this.maLSTon = maLSTon;
		this.setSoLuongNhap(soLuongNhap);
		this.setNgayThayDoi(ngayThayDoi);
		this.setSanPham(sanPham);
	}
	public LishSuTon(String maLSTon) {
		this.maLSTon = maLSTon;
	}
	public LocalDateTime getNgayThayDoi() {
		return ngayThayDoi;
	}
	private void setNgayThayDoi(LocalDateTime lichSuTon) throws Exception {
		if (lichSuTon.isAfter(LocalDateTime.now())) {
			throw new Exception("Ngày thay đổi phải trước hoặc bằng ngày hiện hành");
		}
		this.ngayThayDoi = lichSuTon;
	}
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	private void setSoLuongNhap(int soLuongNhap) throws Exception {
		if (soLuongNhap < 0)
			throw new Exception("Số lượng nhập không âm");
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
		return "LishSuTon [maLSTon=" + maLSTon + ", soLuongNhap=" + soLuongNhap + ", lichSuTon=" + ngayThayDoi
				+ ", sanPham=" + sanPham + "]";
	}
	public double TinhTongTienNhap() {
		return sanPham.getGiaNhap()*soLuongNhap;
	}
	
}
