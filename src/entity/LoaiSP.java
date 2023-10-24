package entity;

import java.util.Objects;

public class LoaiSP {
	private String maLoai;
	private String tenLoai;
	private boolean isDoTT;
	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiSP other = (LoaiSP) obj;
		return Objects.equals(maLoai, other.maLoai);
	}
	public LoaiSP(String maLoai, String tenLoai, boolean isDoTT) {
		this.maLoai = maLoai;
		this.setTenLoai(tenLoai);
		this.setDoTT(isDoTT);
	}
	public String getTenLoai() {
		return tenLoai;
	}
	private void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public boolean isDoTT() {
		return isDoTT;
	}
	private void setDoTT(boolean isDoTT) {
		this.isDoTT = isDoTT;
	}
	@Override
	public String toString() {
		return "LoaiSP [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", isDoTT=" + isDoTT + "]";
	}
	
	
}
