package entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class TaiKhoan {
	private String username;
	private String password;
	private LocalDate ngayLapTK;
	private NhanVien nhanVien;
	public TaiKhoan(String username, String password, LocalDate ngayLapTK,NhanVien nhanVien) throws Exception {
		this.setUsername(username);
		this.setPassword(password);
		this.setNgayLapTK(ngayLapTK);
		this.setNhanVien(nhanVien);
	}
	public TaiKhoan(String username) throws Exception {
		
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public LocalDate getNgayLapTK() {
		return ngayLapTK;
	}
	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(username, other.username);
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	private void setUsername(String username) throws Exception {
		String pattern = "^[A-Za-z]{6,32}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(username).find();
		if (!check) {
			throw new Exception("username không hợp lệ, chỉ chứa các chữ");
		}
		this.username = username;
	}
	private void setPassword(String password) throws Exception {
		String pattern = "^[a-zA-z0-9]*[{}\\[\\]/\\\\+*.0$^|?]+[a-zA-z0-9]*$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(password).find();
		if (!check) {
			throw new Exception("Mật khẩu thiếu kí tự đặc biệt");
		}
		else if(password.length()!=8) {
			throw new Exception("Mật khẩu phải 8 ký tự");
		}
		this.password = password;
	}
	private void setNgayLapTK(LocalDate ngayLapTK) throws Exception {
		if(ngayLapTK.isAfter(LocalDate.now())) 
			throw new Exception("Ngày lập không không hợp lệ");
		this.ngayLapTK = ngayLapTK;
	}
	private void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public String toString() {
		return "TaiKhoan [username=" + username + ", password=" + password + ", ngayLapTK=" + ngayLapTK + ", nhanVien="
				+ nhanVien + "]";
	}
	
	
	
}
