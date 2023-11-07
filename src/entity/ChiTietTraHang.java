package entity;

import java.util.Objects;

/**
 * 
 */
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
	@Override
	public int hashCode() {
		return Objects.hash(SanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietTraHang other = (ChiTietTraHang) obj;
		return Objects.equals(SanPham, other.SanPham);
	}
	
	public void tangSoLuong(int soLuong) throws Exception {
		if (soLuongSP + soLuong <0) {
			throw new Exception("Số lượng giảm lớn hơn số lượng sản phẩm hiện có");
		}
		soLuongSP+=soLuong;
	}
	
}
