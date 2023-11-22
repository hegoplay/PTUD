package entity;

import java.util.ArrayList;

public class NguoiQuanLy extends NhanVien{

	private String cuaHangQL;
	private ArrayList<NhanVien> dsNhanVien;
	
	public NguoiQuanLy(String maNV) throws Exception {
		super(maNV);
		cuaHangQL = "";
		dsNhanVien = new ArrayList<NhanVien>();
		// TODO Auto-generated constructor stub
	}

	public NguoiQuanLy(String maNV, String ten, String sdt, String email, String diaChi, double luong, boolean chucVu, boolean isNam,
			boolean dangLamViec,String cuaHangQL) throws Exception {
		super(maNV, ten, sdt,email, diaChi, luong, chucVu, isNam, dangLamViec);
		// TODO Auto-generated constructor stub
		this.setCuaHangQL(cuaHangQL);
		dsNhanVien = new ArrayList<NhanVien>();
	}

	public NguoiQuanLy(String maNV, String ten, String sdt, String email, String diaChi, double luong, boolean chucVu, boolean isNam,
			boolean dangLamViec,String cuaHangQL,ArrayList<NhanVien> dsNV) throws Exception {
		super(maNV, ten, sdt,email, diaChi, luong, chucVu, isNam, dangLamViec);
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
		if (dsNhanVien.contains(nv))
			return false;
		dsNhanVien.add(nv);
		return true;
	}
	
	public boolean xoaNhanVien(NhanVien nv) {
		return dsNhanVien.remove(nv);
	}

	public String getMaQuanLy() {
		// TODO Auto-generated method stub
		return "QuanLy001";
	}

}
