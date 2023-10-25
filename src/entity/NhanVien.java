package entity;

import java.util.Objects;
import java.util.regex.Pattern;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String sdt;
	private String email;
	private String diaChi;
	private double luong;
	private String chucVu;
	private boolean isNam;
	private boolean dangLamViec;

	public NhanVien(String maNV) throws Exception {
		this.setMaNV(maNV);
	}

	public NhanVien(String maNV, String ten, String sdt, String email, String diaChi, double luong, String chucVu,
			boolean isNam, boolean dangLamViec) throws Exception {
		this.setMaNV(maNV);
		this.setTen(ten);
		this.setSdt(sdt);
		this.setEmail(email);
		this.setDiaChi(diaChi);
		this.setLuong(luong);
		this.setChucVu(chucVu);
		this.setNam(isNam);
		this.setDangLamViec(dangLamViec);
	}

	public String getTen() {
		return tenNV;
	}

	public void setTen(String ten) throws Exception {
		if (ten.trim().equalsIgnoreCase(""))
			throw new Exception("Tên nhân viên không không được rỗng");
		if (ten.length() > 50)
			throw new Exception("Tên nhân viên không quá 50 ký tự");
		this.tenNV = ten;
	}

	public String getMaNV() {
		return maNV;
	}

	private void setMaNV(String maNV) throws Exception {
		String pattern = "^NV[0-9]{8}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(maNV).find();
		if (!check) {
			throw new Exception("Mã nhân viên không đúng định dạng");
		}
		
		this.maNV = maNV;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) throws Exception {
		String pattern = "^[0-9]{10}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(sdt).find();
		if (!check) {
			throw new Exception("Số điện thoại không được rỗng");// cai nay co j nen
//			sua lai la sdt chi co 10 ky tu
		}
		;
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		email = email.trim();
		String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(email).find();
		if (!check) {
			throw new Exception("Email không hợp lệ");
		}
		if (email.length() > 255) {
			throw new Exception("Email dài hơn 255 ký tự");
			//mới thêm rằng buộc độ dài email
		}
		this.email = email;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) throws Exception {
		if (luong < 0) {
			throw new Exception("Lương không hợp lệ");
		}
		this.luong = luong;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public boolean isNam() {
		return isNam;
	}

	public void setNam(boolean isNam) {
		this.isNam = isNam;
	}

	public boolean isDangLamViec() {
		return dangLamViec;
	}

	public void setDangLamViec(boolean dangLamViec) {
		this.dangLamViec = dangLamViec;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ten=" + tenNV + ", sdt=" + sdt + ", diaChi=" + diaChi + ", luong=" + luong
				+ ", chucVu=" + chucVu + ", isNam=" + isNam + ", dangLamViec=" + dangLamViec + "]";
	}

}
