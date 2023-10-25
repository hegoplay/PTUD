package entity;

import java.util.Objects;
import java.util.regex.Pattern;

public class SanPham {
	private String maSP;
	private String tenSP;
	private double giaNhap;
	private int slTonKho;
	private String kichThuoc;
	private String mauSac;
	private boolean conKinhDoanh;
	private boolean isNam;
	private NhaCC nhaCC;
	private String hinhAnh;
	private float thue;
	private LoaiSP loaiSP;

	
	
	public SanPham(String maSP, String tenSP, double giaNhap, int slTonKho, String kichThuoc,String mauSac, boolean conKinhDoanh,
			boolean isNam, NhaCC nhaCC, String hinhAnh, LoaiSP loaiSP) throws Exception {
		this.setMaSP(maSP);
		this.setTenSP(tenSP);
		this.setGiaNhap(giaNhap);
		this.setSlTonKho(slTonKho);
		this.setKichThuoc(kichThuoc);
		this.setMauSac(mauSac);
		this.setConKinhDoanh(conKinhDoanh);
		this.setNam(isNam);
		this.setNhaCC(nhaCC);
		this.setHinhAnh(hinhAnh);
		this.setThue();
		this.setLoaiSP(loaiSP);
	}
	
	public SanPham(String maSP, String tenSP, double giaNhap, int slTonKho, String kichThuoc, String mauSac, boolean conKinhDoanh,
			boolean isNam, NhaCC nhaCC, String hinhAnh,float thue, LoaiSP loaiSP) throws Exception {
		this.setMaSP(maSP);
		this.setTenSP(tenSP);
		this.setGiaNhap(giaNhap);
		this.setSlTonKho(slTonKho);
		this.setKichThuoc(kichThuoc);
		this.setMauSac(mauSac);
		this.setConKinhDoanh(conKinhDoanh);
		this.setNam(isNam);
		this.setNhaCC(nhaCC);
		this.setHinhAnh(hinhAnh);
		this.setThue(thue);
		this.setLoaiSP(loaiSP);
	}

	public SanPham(String maSP) throws Exception {
		this.setMaSP(maSP);
	}

	public void setMaSP(String maSP) throws Exception {
		String pattern = "^SP[0-9]{8}$";
		Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(maSP).find();
		if (!check) {
			throw new Exception("Mã sản phẩm không đúng định dạng");
		};
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

	public void setTenSP(String tenSP) throws Exception {
		if(tenSP == null || tenSP.isEmpty())
			throw new Exception("Tên sản phẩm không không được rỗng");
		this.tenSP = tenSP;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) throws Exception {
		if (giaNhap >= 0) {
			throw new Exception("Giá nhập không âm");
		}
		this.giaNhap = giaNhap;
	}

	public String getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) throws Exception {
		String pattern = "^X{0,4}(S|M|L)$";
		Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(maSP).find();
		if (!check) {
			throw new Exception("Kích thước không hợp lệ");
		};
		this.kichThuoc = kichThuoc;
	}
	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) throws Exception {
		if(mauSac.trim().equalsIgnoreCase("")) {
			throw new Exception("Màu sắc không rỗng");
		}
		else if (mauSac.length()>50){
			throw new Exception("Màu sắc không quá 50 ký tự");
		}
		else {
			this.mauSac = mauSac;
		}
	}
	public int getSlTonKho() {
		return slTonKho;
	}

	public void setSlTonKho(int slTonKho) throws Exception {
		if(slTonKho < 0) throw new Exception("Số lượng tồn kho không âm") ;
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

	public void setThue() {
		thue = 0;
		//Tinh thue VAT
		if (loaiSP.isDoTT()) thue+=0.1;
		else thue +=0.05;
		//Tinh thue EVFTA
		if(nhaCC.laChauAu()) {
			if(loaiSP.getMaLoai() == "BLZ") {
				thue+=0.1;
			}
			else {
				thue += 0.066;
			}
		}
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

	public void tangSLSP(int sl) throws Exception {
		if (slTonKho + sl > 100) {
			throw new Exception("Số lượng tồn kho quá 100");
		}
		slTonKho+=sl;
	}
	
	public double TinhGiaBan() {
		return giaNhap*(1.25+ thue);
	}

	public double TinhThue() {
		return giaNhap*thue;
	}
	
	public void XuatThongTinSP(String url) {
		
	}

	
}
