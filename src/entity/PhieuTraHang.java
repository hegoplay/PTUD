package entity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
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
	
	public double TinhTienTra() {
		return TinhThanhTien()*(1-hoaDon.getKhuyenMai());
	}

	public void XuatPhieuTraHang(String path) throws IOException {
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
		table.addCell(new Cell().add("Phiếu Trả Hàng").setFontSize(20f).
				setBorder(Border.NO_BORDER).setBold()).setFont(ToPDFController.getFont());
		Table nestedTable = new Table(new float[] {twocol/2,twocol/2});
		
		nestedTable.addCell(ToPDFController.getHeaderTextCell("Mã phiếu: "));
		nestedTable.addCell(ToPDFController.getHeaderTextCellValue(this.getMaPhieu()));
		
		nestedTable.addCell(ToPDFController.getHeaderTextCell("Ngày Trả Hàng"));
		nestedTable.addCell(ToPDFController.
				getHeaderTextCellValue(String.valueOf(this.getNgayTraHang())));
		
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
		twoColTable.addCell(ToPDFController.getBillingandShippingCell("Thông tin người quản lý"));
		document.add(twoColTable.setMarginBottom(12f));
		
		Table twoColTable2 = new Table(twocolumnWidth);
		twoColTable2.addCell(ToPDFController.getCell10fLeft("Mã Khách Hàng", true));
		twoColTable2.addCell(ToPDFController.getCell10fLeft("Mã Người Quản lý", true));
		twoColTable2.addCell(ToPDFController.getCell10fLeft(this.getKhachHang().getMaKH(), false));
		twoColTable2.addCell(ToPDFController.getCell10fLeft(this.getNguoiQuanLy().getMaNV(), false));
		document.add(twoColTable2);
		
		Table twoColTable3 = new Table(twocolumnWidth);
		twoColTable3.addCell(ToPDFController.getCell10fLeft("Tên Khách Hàng", true));
		twoColTable3.addCell(ToPDFController.getCell10fLeft("Tên Người Quản lý", true));
		twoColTable3.addCell(ToPDFController.getCell10fLeft(this.getKhachHang().getTenKH(), false));
		twoColTable3.addCell(ToPDFController.getCell10fLeft(this.getNguoiQuanLy().getTen(), false));
		document.add(twoColTable3);
		
		
		Table tableDivider2 = new Table(fullWidth);
		Border dgb= new DashedBorder(Color.GRAY,0.5f);
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
		
		Paragraph productPara = new Paragraph("Sản phẩm");
		document.add(productPara.setBold().setFont(ToPDFController.getFont()));
		
		Table threeColTable1 = new Table(threeColumnWidth);
		threeColTable1.setBackgroundColor(Color.BLACK,0.7f);
			
		threeColTable1.addCell(ToPDFController.getHeaderTextCell("Tên Sản Phẩm").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
		threeColTable1.addCell(ToPDFController.getHeaderTextCell("Số Lượng").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		threeColTable1.addCell(ToPDFController.getHeaderTextCell("Giá Thành").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
		
		document.add(threeColTable1);
		Table threeColTable2 = new Table(threeColumnWidth);
		double totalSum = 0f;
		for(ChiTietTraHang ct : this.getDsChiTiet()) {
			totalSum += ct.getSoLuongSP() * ct.getSanPham().TinhGiaBan();
			threeColTable2.addCell(ToPDFController.getHeaderTextCellValue(ct.getSanPham().getTenSP()).setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
			threeColTable2.addCell(ToPDFController.getHeaderTextCellValue(String.valueOf(ct.getSoLuongSP())).setTextAlignment(TextAlignment.CENTER));
			threeColTable2.addCell(
					ToPDFController.getHeaderTextCellValue(MainFrame.moneyFormatter.format(
							ct.getSanPham().TinhGiaBan())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		}
		
		document.add(threeColTable2.setMarginBottom(20f));
		float onetwo[] = {threecol + 125,threecol *2};
		Table threeColTable4 = new Table(onetwo);
		threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
		document.add(threeColTable4);
		
		Table threeColTable3 = new Table(threeColumnWidth);
		
		threeColTable3.addCell(ToPDFController.getHeaderTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable3.addCell(ToPDFController.getHeaderTextCellValue("Total").setTextAlignment(TextAlignment.CENTER));
		threeColTable3.addCell(
				ToPDFController.getHeaderTextCellValue(MainFrame.moneyFormatter.format(
						totalSum)).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable3);
		
		document.add(tableDivider2);
		document.add(new Paragraph("\n"));
		document.add(divider.setBorder(new SolidBorder(Color.GRAY,1))).setBottomMargin(15f);
		
		Table tb = new Table(fullWidth);
		tb.addCell(ToPDFController.getHeaderTextCell("ĐIỀU KHOẢN VÀ ĐIỀU KIỆN\n").setTextAlignment(TextAlignment.LEFT));
		List<String> TncList = new ArrayList<>();
		TncList.add("1. Mỗi Hóa Đơn chỉ được hoàn 1 lần duy nhất");
//		TncList.add("2. The Seller warrants the product for one (1) year fromt he date of shipment");
		
		for(String tnc: TncList) {
			tb.addCell(ToPDFController.getHeaderTextCellValue(tnc));
		}
		
		document.add(tb);
		document.close();
		System.out.println("Generated");
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
	public String getMaPhieu() {
		return this.maPhieu;
	}
	
	public double TinhTongTienGoc() {
		double res = 0;
		for (ChiTietTraHang ct : dsChiTiet) {
			res+=ct.getSanPham().getGiaNhap() * ct.getSoLuongSP();
		}
		return res;
	}
	
	
	
}
