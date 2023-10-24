package entity;

import java.util.Objects;

public class SanPham {
	private String maSP;
	private String tenSP;
	private double giaNhap;
	private int slTonKho;
	private String kichThuoc;
	private boolean conKinhDoanh;
	private boolean isNam;
	private NhaCC nhaCC;
	private String hinhAnh;
	private float thue;
	private LoaiSP loaiSP;

	public SanPham(String maSP, String tenSP, double giaNhap, int slTonKho, String kichThuoc, boolean conKinhDoanh,
			boolean isNam, NhaCC nhaCC, String hinhAnh, float thue, LoaiSP loaiSP) {
		this.setMaSP(maSP);
		this.setTenSP(tenSP);
		this.setGiaNhap(giaNhap);
		this.setSlTonKho(slTonKho);
		this.setKichThuoc(kichThuoc);
		this.setConKinhDoanh(conKinhDoanh);
		this.setNam(isNam);
		this.setNhaCC(nhaCC);
		this.setHinhAnh(hinhAnh);
		this.setThue(thue);
		this.setLoaiSP(loaiSP);
	}

	public SanPham(String maSP) {
		this.setMaSP(maSP);
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSP, other.maSP);
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public String getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public int getSlTonKho() {
		return slTonKho;
	}

	public void setSlTonKho(int slTonKho) {
		this.slTonKho = slTonKho;
	}

	public boolean isConKinhDoanh() {
		return conKinhDoanh;
	}

	public void setConKinhDoanh(boolean conKinhDoanh) {
		this.conKinhDoanh = conKinhDoanh;
	}

	public boolean isNam() {
		return isNam;
	}

	public void setNam(boolean isNam) {
		this.isNam = isNam;
	}

	public NhaCC getNhaCC() {
		return nhaCC;
	}

	public void setNhaCC(NhaCC nhaCC) {
		this.nhaCC = nhaCC;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public float getThue() {
		return thue;
	}

	public void setThue(float thue) {
		this.thue = thue;
	}

	public LoaiSP getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(LoaiSP loaiSP) {
		this.loaiSP = loaiSP;
	}

	
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", giaNhap=" + giaNhap + ", slTonKho=" + slTonKho
				+ ", kichThuoc=" + kichThuoc + ", conKinhDoanh=" + conKinhDoanh + ", isNam=" + isNam + ", nhaCC="
				+ nhaCC + ", hinhAnh=" + hinhAnh + ", thue=" + thue + ", loaiSP=" + loaiSP + "]";
	}

	public void tangSLSP(int sl) {

	}
	
	public double TinhGiaBan() {
		return 0;
	}

	public double TinhThue() {
		return 0;
	}
	
	public void XuatThongTinSP(String url) {
		
	}
}
