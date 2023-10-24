package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class PhieuTraHang {
	private String maPhieu;
	private LocalDate ngayTraHang;
	private NguoiQuanLy nguoiQuanLy;
	private KhachHang khachHang;
	private ArrayList<ChiTietTraHang> dsChiTiet;
	public PhieuTraHang(String maPhieu, LocalDate ngayTraHang, NguoiQuanLy nguoiQuanLy, KhachHang khachHang,
			ArrayList<ChiTietTraHang> dsChiTiet) {
		this.maPhieu = maPhieu;
		this.setNgayTraHang(ngayTraHang);
		this.setNguoiQuanLy(nguoiQuanLy);
		this.setKhachHang(khachHang);
		this.setDsChiTiet(dsChiTiet);
	}
	public PhieuTraHang(String maPhieu) {
		this.maPhieu = maPhieu;
	}
	public LocalDate getNgayTraHang() {
		return ngayTraHang;
	}
	public void setNgayTraHang(LocalDate ngayTraHang) {
		this.ngayTraHang = ngayTraHang;
	}
	public NguoiQuanLy getNguoiQuanLy() {
		return nguoiQuanLy;
	}
	public void setNguoiQuanLy(NguoiQuanLy nguoiQuanLy) {
		this.nguoiQuanLy = nguoiQuanLy;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public ArrayList<ChiTietTraHang> getDsChiTiet() {
		return dsChiTiet;
	}
	public void setDsChiTiet(ArrayList<ChiTietTraHang> dsChiTiet) {
		this.dsChiTiet = dsChiTiet;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuTraHang other = (PhieuTraHang) obj;
		return Objects.equals(maPhieu, other.maPhieu);
	}

	@Override
	public String toString() {
		return "PhieuTraHang [maPhieu=" + maPhieu + ", ngayTraHang=" + ngayTraHang + ", nguoiQuanLy=" + nguoiQuanLy
				+ ", khachHang=" + khachHang + ", dsChiTiet=" + dsChiTiet + "]";
	}
	public double TinhThanhTien() {
		return 0;
	}
	public void XuatPhieuTraHang(String url) {
		
	}
	public void ThemCTTH(ChiTietTraHang ctth) {
		
	}
	public void XoaCTTH(ChiTietTraHang ctth) {
		
	}
	public void SuaCTTH(ChiTietTraHang ctth) {
		
	}
}
