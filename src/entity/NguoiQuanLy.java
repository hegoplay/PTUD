package entity;

import java.util.ArrayList;

public class NguoiQuanLy extends NhanVien{

	private String cuaHangQL;
	private ArrayList<NhanVien> dsNhanVien;
	
	public NguoiQuanLy(String maNV) {
		super(maNV);
		cuaHangQL = "";
		dsNhanVien = new ArrayList<NhanVien>();
		// TODO Auto-generated constructor stub
	}

	public NguoiQuanLy(String maNV, String ten, String sdt, String diaChi, double luong, String chucVu, boolean isNam,
			boolean dangLamViec,String cuaHangQL) {
		super(maNV, ten, sdt, diaChi, luong, chucVu, isNam, dangLamViec);
		// TODO Auto-generated constructor stub
		this.setCuaHangQL(cuaHangQL);
		dsNhanVien = new ArrayList<NhanVien>();
	}

	public NguoiQuanLy(String maNV, String ten, String sdt, String diaChi, double luong, String chucVu, boolean isNam,
			boolean dangLamViec,String cuaHangQL,ArrayList<NhanVien> dsNV) {
		super(maNV, ten, sdt, diaChi, luong, chucVu, isNam, dangLamViec);
		// TODO Auto-generated constructor stub
		this.setCuaHangQL(cuaHangQL);
		dsNhanVien = dsNV;
	}
	
	public String getCuaHangQL() {
		return cuaHangQL;
	}

	public void setCuaHangQL(String cuaHangQL) {
		this.cuaHangQL = cuaHangQL;
	}

	public ArrayList<NhanVien> getDsNhanVien() {
		return dsNhanVien;
	}
	
	public boolean themNhanVien(NhanVien nv) {
		return false;
	}
	
	public boolean xoaNhanVien(NhanVien nv) {
		return false;
	}
}
