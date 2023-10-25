package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class PhieuTraHang {
	private String maPhieu;
	private HoaDon hoaDon;
	private LocalDate ngayTraHang;
	private NguoiQuanLy nguoiQuanLy;
	private KhachHang khachHang;
	private ArrayList<ChiTietTraHang> dsChiTiet;

	public PhieuTraHang(String maPhieu, HoaDon hoaDon, LocalDate ngayTraHang, NguoiQuanLy nguoiQuanLy,
			KhachHang khachHang, ArrayList<ChiTietTraHang> dsChiTiet) throws Exception {
		this.setMaPhieu(maPhieu);
		this.setHoaDon(hoaDon);
		this.setNgayTraHang(ngayTraHang);
		this.setNguoiQuanLy(nguoiQuanLy);
		this.setKhachHang(khachHang);
		this.setDsChiTiet(dsChiTiet);
	}

	public PhieuTraHang(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	private void setMaPhieu(String maPhieu) throws Exception {
		// TODO Auto-generated method stub
		String pattern = "^TH[0-9]{8}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(maPhieu).find();
		if (!check) {
			throw new Exception("Mã phiếu không được rỗng"); //Nên sửa lại mã phiếu không phù hợp
		}
		this.maPhieu = maPhieu;
		
	}

	public LocalDate getNgayTraHang() {
		return ngayTraHang;
	}

	public void setNgayTraHang(LocalDate ngayTraHang) {
		this.ngayTraHang = ngayTraHang;
	}

	public NguoiQuanLy getNguoiQuanLy() {
		return nguoiQuanLy;
	}

	public void setNguoiQuanLy(NguoiQuanLy nguoiQuanLy) {
		this.nguoiQuanLy = nguoiQuanLy;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public ArrayList<ChiTietTraHang> getDsChiTiet() {
		return dsChiTiet;
	}

	public void setDsChiTiet(ArrayList<ChiTietTraHang> dsChiTiet) {
		this.dsChiTiet = dsChiTiet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhieu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuTraHang other = (PhieuTraHang) obj;
		return Objects.equals(maPhieu, other.maPhieu);
	}

	@Override
	public String toString() {
		return "PhieuTraHang [maPhieu=" + maPhieu + ", ngayTraHang=" + ngayTraHang + ", nguoiQuanLy=" + nguoiQuanLy
				+ ", khachHang=" + khachHang + ", dsChiTiet=" + dsChiTiet + "]";
	}

	public double TinhThanhTien() {
		double money = 0;
		for (ChiTietTraHang ct : dsChiTiet) {
			money += ct.TinhThanhTien();
		}
		return money;
	}

	public void XuatPhieuTraHang(String url) {

	}

	public void ThemCTTH(ChiTietTraHang ctth) throws Exception {
		if (dsChiTiet.contains(ctth)) {
			throw new Exception("Hoa don ton tai");
		}
		dsChiTiet.add(ctth);
	}

	public void XoaCTTH(ChiTietTraHang ctth) throws Exception {
		if (!dsChiTiet.contains(ctth)) {
			throw new Exception("Hoa don khong ton tai");
		}
		dsChiTiet.remove(ctth);
	}

	public void SuaCTTH(ChiTietTraHang ctth) throws Exception {
		if (!dsChiTiet.contains(ctth)) {
			throw new Exception("Hoa don khong ton tai");
		}
		dsChiTiet.set(dsChiTiet.indexOf(ctth), ctth);
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	private void setHoaDon(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		this.hoaDon = hoaDon;
	}
}
