package entity;

public class ChiTietHoaDon {
	private SanPham sanPham;
	private int soLuong;
	public ChiTietHoaDon(SanPham sanPham, int soLuong) {
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	
	public double TinhThanhTien() {
		return 0;
	}
	
	public double TinhTongThue() {
		return 0;
	}
}
