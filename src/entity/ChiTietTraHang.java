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
	public void setSoLuongSP(int soLuongSP) throws Exception {
		if (soLuongSP < 0) throw new Exception("so luong san pham phải lớn hơn 0");
		this.soLuongSP = soLuongSP;
	}
	@Override
	public String toString() {
		return "ChiTietTraHang [SanPham=" + SanPham + ", soLuongSP=" + soLuongSP + "]";
	}
	
	public double TinhThanhTien() {
		return SanPham.TinhGiaBan()*soLuongSP;
	}
	
	public double TinhGTThue() {
		return SanPham.TinhThue()*soLuongSP;
	}
	
}
