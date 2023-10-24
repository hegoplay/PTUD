package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDon {
	private String maHD;
	private LocalDate ngayLapHD;
	private	NhanVien nhanVien;
	private KhachHang khachHang;
	private float khuyenMai;
	private double tienKhachDua;
	private ArrayList<ChiTietHoaDon> dsCTHD;
	public HoaDon(String maHD, LocalDate ngayLapHD, NhanVien nhanVien, KhachHang khachHang, float khuyenMai,
			double tienKhachDua, ArrayList<ChiTietHoaDon> dsCTHD) {
		this.maHD = maHD;
		this.setNgayLapHD(ngayLapHD);
		this.setNhanVien(nhanVien);
		this.setKhachHang(khachHang);
		this.setKhuyenMai(khuyenMai);
		this.setTienKhachDua(tienKhachDua);
		this.setDsCTHD(dsCTHD);
	}
	public HoaDon(String maHD) {
		this.maHD = maHD;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}
	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public float getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(float khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public double getTienKhachDua() {
		return tienKhachDua;
	}
	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}
	public ArrayList<ChiTietHoaDon> getDsCTHD() {
		return dsCTHD;
	}
	public void setDsCTHD(ArrayList<ChiTietHoaDon> dsCTHD) {
		this.dsCTHD = dsCTHD;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", nhanVien=" + nhanVien + ", khachHang="
				+ khachHang + ", khuyenMai=" + khuyenMai + ", tienKhachDua=" + tienKhachDua + ", dsCTHD=" + dsCTHD
				+ "]";
	}
	
	public void XuatHoaDon() {
		
	}
	
	public double TinhTongTien() {
		return 0;
	}
	public double TinhThanhTien() {
		return 0;
	}
	public double TinhTienTraLai(double tienNhap) {
		return 0;
	}
	public int soLuongSP() {
		return 0;
	}
	public double TinhTongKhuyenMai() {
		return 0;
	}
	public void ThemCTHD(ChiTietHoaDon cthd) {
		
	}
	public void XoaCTHD(ChiTietHoaDon cthd) {
		
	}
}
