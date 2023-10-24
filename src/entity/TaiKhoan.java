package entity;

import java.time.LocalDate;
import java.util.Objects;

public class TaiKhoan {
	private String username;
	private String password;
	private LocalDate ngayLapTK;
	public TaiKhoan(String username, String password, LocalDate ngayLapTK) {
		this.username = username;
		this.password = password;
		this.ngayLapTK = ngayLapTK;
	}
	public TaiKhoan(String username) {
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
	
	
	
}
