package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import controller.ToPDFController;
import view.MainFrame;

public class HoaDon {
	private String maHD;
	private LocalDateTime ngayLapHD;
	private	NhanVien nhanVien;
	private KhachHang khachHang;
	private float khuyenMai;
	private double tienKhachDua;
	private ArrayList<ChiTietHoaDon> dsCTHD;
	
	
	
	public HoaDon(String maHD, LocalDateTime ngayLapHD, NhanVien nhanVien, KhachHang khachHang, float khuyenMai,
			double tienKhachDua, ArrayList<ChiTietHoaDon> dsCTHD) throws Exception {
		this.setMaHD(maHD);
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
			double tienKhachDua, ArrayList<ChiTietHoaDon> dsCTHD) throws Exception {
		this.setMaHD(maHD);;
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
			double tienKhachDua, ArrayList<ChiTietHoaDon> dsCTHD) throws Exception {
		this.setMaHD(maHD);;
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
	private void setMaHD(String maHD) throws Exception {
		String pattern = "HD[0-9]{8}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		boolean check = p.matcher(maHD).find();
		if (!check)
			throw new Exception("Mã hóa đơn không đúng định dạng");
		else
			this.maHD = maHD;
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
	
	public void XuatHoaDon(String path) throws Exception {
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		
		Document document = new Document(pdfDocument);
		ImageData imageData = ImageDataFactory.create(MainFrame.class.getResource("/view/icon/shop_logo.png"));
		Image image = new Image(imageData);
		
		float x = pdfDocument.getDefaultPageSize().getWidth()/2;
		float y = pdfDocument.getDefaultPageSize().getHeight()/2;
		image.setFixedPosition(x-image.getImageWidth()/2, y - image.getImageHeight()/2);
		image.setOpacity(0.1f);
		document.add(image);
		
		ToPDFController.setFont();
		
		float threecol = 190f;
		float twocol = 285f;
		float twocol150 = twocol + 150f; 
		float twocolumnWidth[] = {twocol150,twocol};
		float threeColumnWidth[] = {threecol,threecol,threecol};
		float fullWidth[] = {threecol*3};
		Paragraph onesp = new Paragraph("\n");
		Table table = new Table(twocolumnWidth);
		table.addCell(new Cell().add("HÓA ĐƠN BÁN HÀNG").setFontSize(20f).
				setBorder(Border.NO_BORDER).setBold()).setFont(ToPDFController.getFont());
		Table nestedTable = new Table(new float[] {twocol/2,twocol/2});
		
		nestedTable.addCell(ToPDFController.getHeaderLeftTextCell("Mã hóa đơn: "));
		nestedTable.addCell(ToPDFController.getHeaderLeftTextCellValue(this.getMaHD()));
		
		nestedTable.addCell(ToPDFController.getHeaderLeftTextCell("Ngày Lập hóa đơn"));
		nestedTable.addCell(ToPDFController.
				getHeaderLeftTextCellValue(String.valueOf(this.getNgayLapHD())));
		
		table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
		
		Border gb = new SolidBorder(Color.GRAY, 2f);
		Table divider = new Table(fullWidth);
		divider.setBorder(gb);
		
		document.add(table);
		document.add(onesp);
		document.add(divider);
		document.add(onesp);
		
		Table twoColTable = new Table(twocolumnWidth);
		twoColTable.addCell(ToPDFController.getBillingandShippingCell("Thông tin khách Hàng"));
		twoColTable.addCell(ToPDFController.getBillingandShippingCell("Nhân viên lập hóa đơn"));
		document.add(twoColTable.setMarginBottom(12f));
		
		Table twoColTable2 = new Table(twocolumnWidth);
		twoColTable2.addCell(ToPDFController.getCell10fLeft("Mã Khách Hàng", true));
		twoColTable2.addCell(ToPDFController.getCell10fLeft("Mã Nhân Viên", true));
		twoColTable2.addCell(ToPDFController.getCell10fLeft(this.getKhachHang().getMaKH(), false));
		twoColTable2.addCell(ToPDFController.getCell10fLeft(this.getNhanVien().getMaNV(), false));
		document.add(twoColTable2);
		
		Table twoColTable3 = new Table(twocolumnWidth);
		twoColTable3.addCell(ToPDFController.getCell10fLeft("Tên Khách Hàng", true));
		twoColTable3.addCell(ToPDFController.getCell10fLeft("Tên Nhân Viên", true));
//		twoColTable3.addCell(ToPDFController.getCell10fLeft(this.getKhachHang().getTenKH(), false));
//		twoColTable3.addCell(ToPDFController.getCell10fLeft(this.getNhanVien().getTen(), false));
		twoColTable3.addCell(ToPDFController.getCell10fLeft("Đinh Thiện Quang", false));
		twoColTable3.addCell(ToPDFController.getCell10fLeft("Hoàng Thị Mỹ Linh", false));
		document.add(twoColTable3);
		
		
		Table tableDivider2 = new Table(fullWidth);
		Border dgb= new DashedBorder(Color.GRAY,0.5f);
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
		
		Paragraph productPara = new Paragraph("Chi tiết hóa đơn");
		document.add(productPara.setBold().setFont(ToPDFController.getFont()));
		
		Table threeColTable1 = new Table(threeColumnWidth);
		threeColTable1.setBackgroundColor(Color.BLACK,0.7f);
			
		threeColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Tên Sản Phẩm").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
		threeColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Số Lượng").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		threeColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Thành tiền").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
		
		document.add(threeColTable1);
		Table threeColTable2 = new Table(threeColumnWidth);
		double totalSum = 0f;
//		for(ChiTietHoaDon ct : this.getDsCTHD()) {
//			totalSum += ct.getSoLuong() * ct.getSanPham().TinhGiaBan();
//			threeColTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(ct.getSanPham().getTenSP()).setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
//			threeColTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(String.valueOf(ct.getSoLuong())).setTextAlignment(TextAlignment.CENTER));
//			threeColTable2.addCell(
//					ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
//							ct.getSanPham().TinhGiaBan())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
//		}
		for (ChiTietHoaDon ct : this.getDsCTHD()) {
		    if (ct.getSanPham() != null) {
		        totalSum += ct.getSoLuong() * ct.getSanPham().TinhGiaBan();
		        
		        threeColTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(ct.getSanPham().getTenSP()).setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		        threeColTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(String.valueOf(ct.getSoLuong())).setTextAlignment(TextAlignment.CENTER));
		        threeColTable2.addCell(
		                ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
		                        ct.getSanPham().TinhGiaBan())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		    } else {
		        System.out.println("Lỗi dữ liệu!");
		    }
		}

		
		document.add(threeColTable2.setMarginBottom(20f));
		float onetwo[] = {threecol + 125,threecol *2};
		Table threeColTable4 = new Table(onetwo);
		threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
		document.add(threeColTable4);
		
		Table threeColTable3 = new Table(threeColumnWidth);
		
		threeColTable3.addCell(ToPDFController.getHeaderLeftTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable3.addCell(ToPDFController.getHeaderLeftTextCellValue("Tổng cộng:").setTextAlignment(TextAlignment.CENTER));
		threeColTable3.addCell(
				ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
						this.TinhThanhTien())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable3);
		
		Table threeColTable41 = new Table(threeColumnWidth);
		
		threeColTable41.addCell(ToPDFController.getHeaderLeftTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable41.addCell(ToPDFController.getHeaderLeftTextCellValue("Khuyễn mãi:").setTextAlignment(TextAlignment.CENTER));
		threeColTable41.addCell(
				ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
						this.TinhGTKhuyenMai())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable41);
		
		Table threeColTable5 = new Table(threeColumnWidth);
		
		threeColTable5.addCell(ToPDFController.getHeaderLeftTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable5.addCell(ToPDFController.getHeaderLeftTextCellValue("Tổng tiền:").setTextAlignment(TextAlignment.CENTER));
		threeColTable5.addCell(
				ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
						this.TinhTongTien())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable5);
		
		Table threeColTable6 = new Table(threeColumnWidth);
		
		threeColTable6.addCell(ToPDFController.getHeaderLeftTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable6.addCell(ToPDFController.getHeaderLeftTextCellValue("Tiền khách đưa:").setTextAlignment(TextAlignment.CENTER));
		threeColTable6.addCell(
				ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
						this.getTienKhachDua())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable6);
		
		Table threeColTable7 = new Table(threeColumnWidth);
		
		threeColTable7.addCell(ToPDFController.getHeaderLeftTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable7.addCell(ToPDFController.getHeaderLeftTextCellValue("Tiền thừa:").setTextAlignment(TextAlignment.CENTER));
		threeColTable7.addCell(
				ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
						this.TinhTienTraLai())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable7);
		
		document.add(tableDivider2);
		document.add(new Paragraph("\n"));
		document.add(divider.setBorder(new SolidBorder(Color.GRAY,1))).setBottomMargin(15f);
		
		Table tb = new Table(fullWidth);
		tb.addCell(ToPDFController.getHeaderLeftTextCell("ĐIỀU KHOẢN VÀ ĐIỀU KIỆN\n").setTextAlignment(TextAlignment.LEFT));
		List<String> TncList = new ArrayList<>();
		TncList.add("1. Hóa đơn chỉ xuất trong ngày");
		TncList.add("2. Mỗi Hóa Đơn chỉ được hoàn 1 lần duy nhất");
		TncList.add("3. Giảm 5% cho hóa đơn từ 1.500.000 VNĐ và 10% cho hóa đơn trên 4.000.000 VNĐ");
		for(String tnc: TncList) {
			tb.addCell(ToPDFController.getHeaderLeftTextCellValue(tnc));
		}
		
		Table tb2 = new Table(fullWidth);
		tb2.addCell(ToPDFController.getHeaderRightTextCell("CẢM ƠN QUÝ KHÁCH! HẸN GẶP LẠI!\n").setTextAlignment(TextAlignment.RIGHT));
		

		
		document.add(tb);
		document.close();
//		System.out.println("Generated");
	}
	
	public double TinhTienTraLai () {
		return tienKhachDua - TinhTongTien();
	}
	
	public double TinhTongTien() {
		return TinhThanhTien() - TinhGTKhuyenMai();
	}
	public double TinhThanhTien() {
		double total = 0;
		for (ChiTietHoaDon x : dsCTHD) {
			total += x.TinhThanhTien();
		}
		return total;
	}
	public int TinhSoLuongSP() {
		// ? phuong thuc tinh tong so luong san pham cua 1 hoa don
		int soLuong = 0;
		for (ChiTietHoaDon x : dsCTHD) {
			soLuong += x.getSoLuong();
		}
		return soLuong;
	}
	public double TinhGTKhuyenMai() {
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
