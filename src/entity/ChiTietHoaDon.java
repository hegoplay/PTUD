package entity;

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
}
