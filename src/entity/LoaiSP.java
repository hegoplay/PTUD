package entity;

import java.util.Objects;

public class LoaiSP {
	private String maLoai;
	private String tenLoai;
	private boolean isDoTT;
	
	
	public LoaiSP(String maLoai) {
		this.maLoai = maLoai;
	}
	public LoaiSP(String maLoai, String tenLoai, boolean isDoTT) {
		try {
			this.maLoai = maLoai;
			this.setTenLoai(tenLoai);
			this.setDoTT(isDoTT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getTenLoai() {
		return tenLoai;
	}
	private void setTenLoai(String tenLoai) throws Exception {
		if (tenLoai.equalsIgnoreCase("")) {
			throw new Exception("Tên loại sản phẩm không được để trống");
		}
		this.tenLoai = tenLoai;
	}
	public boolean isDoTT() {
		return isDoTT;
	}
	private void setDoTT(boolean isDoTT) {
		this.isDoTT = isDoTT;
	}
	
	
	
	public String getMaLoai() {
		return maLoai;
	}
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
	
	@Override
    public String toString() {
        return tenLoai; // Trả về tên nhà cung cấp khi gọi toString()
    }
	
	
}
