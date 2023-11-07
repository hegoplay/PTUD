package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDon {
	private String maHD;
	private LocalDateTime ngayLapHD;
	private	NhanVien nhanVien;
	private KhachHang khachHang;
	private float khuyenMai;
	private double tienKhachDua;
	private ArrayList<ChiTietHoaDon> dsCTHD;
	
	
	
	public HoaDon(String maHD, LocalDateTime ngayLapHD, NhanVien nhanVien, KhachHang khachHang, float khuyenMai,
			double tienKhachDua, ArrayList<ChiTietHoaDon> dsCTHD) {
		this.maHD = maHD;
		try {
			this.setNgayLapHD(ngayLapHD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setNhanVien(nhanVien);
		this.setKhachHang(khachHang);
		this.setKhuyenMai(khuyenMai);
		this.setTienKhachDua(tienKhachDua);
		this.setDsCTHD(dsCTHD);
	}
	public HoaDon(String maHD, LocalDateTime ngayLapHD, NhanVien nhanVien, KhachHang khachHang,
			double tienKhachDua, ArrayList<ChiTietHoaDon> dsCTHD) {
		this.maHD = maHD;
		try {
			this.setNgayLapHD(ngayLapHD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setNhanVien(nhanVien);
		this.setKhachHang(khachHang);
		this.setKhuyenMai();
		this.setTienKhachDua(tienKhachDua);
		this.setDsCTHD(dsCTHD);
	}
	public HoaDon(String maHD, NhanVien nhanVien, KhachHang khachHang,
			double tienKhachDua, ArrayList<ChiTietHoaDon> dsCTHD) {
		this.maHD = maHD;
		try {
			this.setNgayLapHD(LocalDateTime.now());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setNhanVien(nhanVien);
		this.setKhachHang(khachHang);
		this.setKhuyenMai();
		this.setTienKhachDua(tienKhachDua);
		this.setDsCTHD(dsCTHD);
	}
	
	public HoaDon(String maHD) {
		this.maHD = maHD;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}
	public String getMaHD() {
		return maHD;
	}
	public LocalDateTime getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(LocalDateTime ngayLapHD) throws Exception {
		if(ngayLapHD.isAfter(LocalDateTime.now())) {
			throw new Exception("Ngày lập hoá đơn phải trước hoặc bằng ngày hiện hành");
		}
		this.ngayLapHD = ngayLapHD;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public float getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(float khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public void setKhuyenMai() {
		//Neu tong tien > 4tr thi khuyen mai 10%
		if(TinhThanhTien() >= 4e6) {
			khuyenMai = 0.1f;
		}
		//Neu tong tien >= 1tr5 thi khuyen mai 5%
		else if (TinhThanhTien() >= 1e6 + 5e5) {
			khuyenMai = 0.05f;
		}
		else {
			khuyenMai = 0;
		}
	}
	public double getTienKhachDua() {
		return tienKhachDua;
	}
	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}
	public ArrayList<ChiTietHoaDon> getDsCTHD() {
		return dsCTHD;
	}
	public void setDsCTHD(ArrayList<ChiTietHoaDon> dsCTHD) {
		this.dsCTHD = dsCTHD;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", nhanVien=" + nhanVien + ", khachHang="
				+ khachHang + ", khuyenMai=" + khuyenMai + ", tienKhachDua=" + tienKhachDua + ", dsCTHD=" + dsCTHD
				+ "]";
	}
	
	public void XuatHoaDon() {
		
	}
	
	public double TinhTongTien() {
		return TinhThanhTien() - TinhTongKhuyenMai();
	}
	public double TinhThanhTien() {
		double total = 0;
		for (ChiTietHoaDon x : dsCTHD) {
			total += x.TinhThanhTien();
		}
		return total;
	}
	public double TinhTienTraLai(double tienNhap) {
		//Tiền nhập có cần phải lưu hay không 
//		Liên quan thiết kế sql nên cần trả lời đúng :v
//		Nhớ nhắn lên zalo nếu đã đọc đến phần này
		return 0;
	}
	public int soLuongSP() {
		// ? phuong thuc tinh tong so luong san pham cua 1 hoa don
		int soLuong = 0;
		for (ChiTietHoaDon x : dsCTHD) {
			soLuong += x.getSoLuong();
		}
		return soLuong;
	}
	public double TinhTongKhuyenMai() {
		//phuong thuc tinh tong khuyen mai
		return TinhThanhTien() * khuyenMai;
	}
	public boolean ThemCTHD(ChiTietHoaDon cthd) {
		if(dsCTHD.contains(cthd)) { return false; }
		dsCTHD.add(cthd);
		return true;
	}
	public boolean XoaCTHD(ChiTietHoaDon cthd) {
		return dsCTHD.remove(cthd);
	}
	
	public double TinhTongTienGoc() {
		double res = 0;
		for (ChiTietHoaDon ct : dsCTHD) {
			res += ct.TinhTienGoc();
		}
		return res;
	}
	
}
