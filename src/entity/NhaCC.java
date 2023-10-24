package entity;

import java.util.Objects;

public class NhaCC {
	private String maNCC;
	private String tenNCC;
	private String diaChi;
	private String quocGia;
	@Override
	public int hashCode() {
		return Objects.hash(maNCC);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCC other = (NhaCC) obj;
		return Objects.equals(maNCC, other.maNCC);
	}
	public NhaCC(String maNCC, String tenNCC, String diaChi, String quocGia) {
		this.maNCC = maNCC;
		this.setTenNCC(tenNCC);
		this.setDiaChi(diaChi);
		this.setQuocGia(quocGia);
	}
	public NhaCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getQuocGia() {
		return quocGia;
	}
	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "NhaCC [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", quocGia=" + quocGia + "]";
	}
	
	public boolean laChauAu() {
		return true;
	}
}
