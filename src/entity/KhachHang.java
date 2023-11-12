package entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String sdt;
	private int namSinh;
	private boolean gioiTinh;
	public KhachHang(String maKH) throws Exception {
		this.setMaKH(maKH);
	}
	public KhachHang(String maKH, String tenKH, String diaChi, String sdt, int namSinh, boolean gioiTinh) throws Exception {
		this.setMaKH(maKH);
		this.setTenKH(tenKH);
		this.setDiaChi(diaChi);
		this.setSdt(sdt);
		this.setNamSinh(namSinh);
		this.setGioiTinh(gioiTinh);
	}
	public KhachHang(String tenKH, String diaChi, String sdt, int namSinh, boolean gioiTinh) throws Exception {
		this.setMaKH();
		this.setTenKH(tenKH);
		this.setDiaChi(diaChi);
		this.setSdt(sdt);
		this.setNamSinh(namSinh);
		this.setGioiTinh(gioiTinh);
	}
	private void setMaKH(String maKH) throws Exception {
		String pattern = "^KH[0-9]{10}$";
		Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(maKH).find();
		if (!check)
			throw new Exception("Mã khách hàng không đúng định dạng");
		else
			this.maKH = maKH;
	}
	
	public String getMaKH() {
		return maKH;
	}
	private void setMaKH() {
		maKH = "KH" + sdt;
	}
	
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) throws Exception {
		tenKH = tenKH.trim();
		if (tenKH.equalsIgnoreCase("")) 
			throw new Exception("Tên khách hàng không không được rỗng");
		if(tenKH.length()>50) throw new Exception("Tên khách hàng bé hơn 50 ký tự");
		this.tenKH = tenKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) throws Exception {
		if(sdt.length()!=10) throw new Exception("sdt phải đúng 10 ký tự");
		this.sdt = sdt;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public int getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(int namSinh) throws Exception {
		if (namSinh> 1890 & namSinh <= LocalDate.now().getYear())
			this.namSinh = namSinh;
		else
			throw new Exception("Năm sinh phải lớn hơn 1890 và phải nhỏ hơn năm hiện hành");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	
}
