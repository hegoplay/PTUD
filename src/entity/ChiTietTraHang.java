package entity;

public class ChiTietTraHang {
	private SanPham SanPham;
	private int soLuongSP;
	public ChiTietTraHang(SanPham sanPham, int soLuongSP) {
		SanPham = sanPham;
		this.soLuongSP = soLuongSP;
	}
	public ChiTietTraHang(SanPham sanPham) {
		SanPham = sanPham;
	}
	public SanPham getSanPham() {
		return SanPham;
	}
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}
	@Override
	public String toString() {
		return "ChiTietTraHang [SanPham=" + SanPham + ", soLuongSP=" + soLuongSP + "]";
	}
	
	public double TinhThanhTien() {
		return 0;
	}
	
	public double TinhGTThue() {
		return 0;
	}
	
}
