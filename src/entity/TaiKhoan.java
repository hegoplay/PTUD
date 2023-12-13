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
		String pattern = "^[A-Za-z]{1,32}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(username).find();
		if (!check) {
			throw new Exception("username không hợp lệ, chỉ chứa các chữ");
		}
		this.username = username;
	}
	private void setPassword(String password) throws Exception {
		Pattern[] inputRegexes = new Pattern[4];
		{
		    inputRegexes[0] = Pattern.compile(".*[A-Z].*");
		    inputRegexes[1] = Pattern.compile(".*[a-z].*");
		    inputRegexes[2] = Pattern.compile(".*\\d.*");
		    inputRegexes[3] = Pattern.compile("^[a-zA-Z0-9]*$");
		}
		int i = 0;
		for (Pattern pattern : inputRegexes) {
			i++;
			System.out.println(i);
			if (!pattern.matcher(password).matches()) {
				throw new Exception("Mật khẩu phải có ký tự hoa, ký tự thường và ký tự số và không ký tự đặc biệt");
			}
		}
		if(password.length()<8) {
			throw new Exception("Mật khẩu phải ít nhất 8 ký tự");
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
