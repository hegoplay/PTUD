package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class KhuyenMai {
	   private String maChuongTrinh;
	   private String tenChuongTrinh;
	   private LocalDateTime ngayBatDau;
	   private LocalDateTime ngayKetThuc;
	   private String loaiKhuyenMai;
	   private double mucGiamGia;
	   private ArrayList<SanPham> dsSP;
	public KhuyenMai() {
		super();
	}
	public KhuyenMai(String maChuongTrinh, String tenChuongTrinh, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc,
			String loaiKhuyenMai, double mucGiamGia, ArrayList<SanPham> dsSP) {
		super();
		this.maChuongTrinh = maChuongTrinh;
		this.setTenChuongTrinh(tenChuongTrinh);;
		this.setNgayBatDau(ngayBatDau);
		this.setNgayKetThuc(ngayKetThuc);
		this.setLoaiKhuyenMai(loaiKhuyenMai);
		this.setMucGiamGia(mucGiamGia);
		this.setDsSP(dsSP);
	}
	public String getMaChuongTrinh() {
		return maChuongTrinh;
	}
//	public void setMaChuongTrinh(String maChuongTrinh) {
//		this.maChuongTrinh = maChuongTrinh;
//	}
	public String getTenChuongTrinh() {
		return tenChuongTrinh;
	}
	public void setTenChuongTrinh(String tenChuongTrinh) {
		this.tenChuongTrinh = tenChuongTrinh;
	}
	public LocalDateTime getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDateTime ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public String getLoaiKhuyenMai() {
		return loaiKhuyenMai;
	}
	public void setLoaiKhuyenMai(String loaiKhuyenMai) {
		this.loaiKhuyenMai = loaiKhuyenMai;
	}
	public double getMucGiamGia() {
		return mucGiamGia;
	}
	public void setMucGiamGia(double mucGiamGia) {
		this.mucGiamGia = mucGiamGia;
	}
	public ArrayList<SanPham> getDsSP() {
		return dsSP;
	}
	public void setDsSP(ArrayList<SanPham> dsSP) {
		this.dsSP = dsSP;
	}
	   
}