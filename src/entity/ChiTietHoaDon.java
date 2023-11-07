package entity;

import java.util.Objects;

/**
 * 
 */
public class ChiTietHoaDon {
	private SanPham sanPham;
	private int soLuong;
	public ChiTietHoaDon(SanPham sanPham, int soLuong) throws Exception {
		this.sanPham = sanPham;
		setSoLuong(soLuong);
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	
	
	
	private void setSoLuong(int soLuong) throws Exception {
		if (soLuong <=0) {
			throw new Exception("số lượng chi tiết hóa đơn phải lớn hơn 0");
		}
		this.soLuong = soLuong;
	}
	public double TinhThanhTien() {
		return sanPham.TinhGiaBan()*soLuong;
	}
	
	public double TinhTongThue() {
		return sanPham.TinhThue()*soLuong;
	}
	
	public void tangSoLuong(int soLuong) throws Exception {
		if(soLuong + this.soLuong <0) {
			throw new Exception("So luong khong phu hop");
		}
		this.soLuong+=soLuong;
	}
	public ChiTietHoaDon(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	@Override
	public int hashCode() {
		return Objects.hash(sanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Objects.equals(sanPham, other.sanPham);
	}
	
	public double TinhTienGoc() {
		return sanPham.getGiaNhap() * soLuong;
	}
	
}
