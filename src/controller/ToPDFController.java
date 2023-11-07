package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import entity.ChiTietTraHang;
import entity.PhieuTraHang;
import view.MainFrame;

public class ToPDFController {
	static PdfFont font ;
//	
	public static void setFont() throws IOException {
		font = PdfFontFactory.createFont("c:/windows/fonts/times.ttf", PdfEncodings.IDENTITY_H, true);
	}
	
	public static PdfFont getFont() {
		return font;
	}
	
	public static Cell getHeaderTextCell(String textValue) {
		return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFont(font);
	}
	
	public static Cell getHeaderTextCellValue(String textValue) {
		return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setFont(font);
	}
	public static Cell getBillingandShippingCell (String textValue) {
		return new Cell().add(textValue).setFontSize(12f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setFont(font);
	}
	
	public static Cell getCell10fLeft (String textValue, Boolean isBold) {
		Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setFont(font);
		return isBold ? myCell.setBold() : myCell;
	}
}

//sample test 
//	public static void xuatPTTH(String path, PhieuTraHang pth) throws IOException {
//		PdfWriter pdfWriter = new PdfWriter(path);
//		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//		pdfDocument.setDefaultPageSize(PageSize.A4);
//		
//		Document document = new Document(pdfDocument);
//		ImageData imageData = ImageDataFactory.create(MainFrame.class.getResource("/view/icon/shop_logo.png"));
//		Image image = new Image(imageData);
//		
//		float x = pdfDocument.getDefaultPageSize().getWidth()/2;
//		float y = pdfDocument.getDefaultPageSize().getHeight()/2;
//		image.setFixedPosition(x-image.getImageWidth()/2, y - image.getImageHeight()/2);
//		image.setOpacity(0.1f);
//		document.add(image);
//		
//		
//		
//		float threecol = 190f;
//		float twocol = 285f;
//		float twocol150 = twocol + 150f; 
//		float twocolumnWidth[] = {twocol150,twocol};
//		float threeColumnWidth[] = {threecol,threecol,threecol};
//		float fullWidth[] = {threecol*3};
//		Paragraph onesp = new Paragraph("\n");
//		Table table = new Table(twocolumnWidth);
//		table.addCell(new Cell().add("Phiếu Trả Hàng").setFontSize(20f).setBorder(Border.NO_BORDER).setBold()).setFont(font);
//		Table nestedTable = new Table(new float[] {twocol/2,twocol/2});
//		
//		nestedTable.addCell(getHeaderTextCell("Mã phiếu: "));
//		nestedTable.addCell(getHeaderTextCellValue(pth.getMaPhieu()));
//		
//		nestedTable.addCell(getHeaderTextCell("Ngày Trả Hàng"));
//		nestedTable.addCell(getHeaderTextCellValue(String.valueOf(pth.getNgayTraHang())));
//		
//		table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
//		
//		Border gb = new SolidBorder(Color.GRAY, 2f);
//		Table divider = new Table(fullWidth);
//		divider.setBorder(gb);
//		
//		document.add(table);
//		document.add(onesp);
//		document.add(divider);
//		document.add(onesp);
//		
//		Table twoColTable = new Table(twocolumnWidth);
//		twoColTable.addCell(getBillingandShippingCell("Thông tin khách Hàng"));
//		twoColTable.addCell(getBillingandShippingCell("Thông tin người quản lý"));
//		document.add(twoColTable.setMarginBottom(12f));
//		
//		Table twoColTable2 = new Table(twocolumnWidth);
//		twoColTable2.addCell(getCell10fLeft("Mã Khách Hàng", true));
//		twoColTable2.addCell(getCell10fLeft("Mã Người Quản lý", true));
//		twoColTable2.addCell(getCell10fLeft(pth.getKhachHang().getMaKH(), false));
//		twoColTable2.addCell(getCell10fLeft(pth.getNguoiQuanLy().getMaNV(), false));
//		document.add(twoColTable2);
//		
//		Table twoColTable3 = new Table(twocolumnWidth);
//		twoColTable3.addCell(getCell10fLeft("Tên Khách Hàng", true));
//		twoColTable3.addCell(getCell10fLeft("Tên Người Quản lý", true));
//		twoColTable3.addCell(getCell10fLeft(pth.getKhachHang().getTenKH(), false));
//		twoColTable3.addCell(getCell10fLeft(pth.getNguoiQuanLy().getTen(), false));
//		document.add(twoColTable3);
//		
////		float oneColumnwidth[] = {twocol150};
//		
////		Table oneColTable1 = new Table(oneColumnwidth);
////		oneColTable1.addCell(getCell10fLeft("Address", true));
////		oneColTable1.addCell(getCell10fLeft("8570 Gulseth Terra, 3324 Eastwood\nSpringfi, Ma, 01114", false));
////		oneColTable1.addCell(getCell10fLeft("Email", true));
////		oneColTable1.addCell(getCell10fLeft("stern@example.com", false));
////		document.add(oneColTable1);
//		
//		Table tableDivider2 = new Table(fullWidth);
//		Border dgb= new DashedBorder(Color.GRAY,0.5f);
//		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
//		
//		Paragraph productPara = new Paragraph("Sản phẩm");
//		document.add(productPara.setBold().setFont(font));
//		
//		Table threeColTable1 = new Table(threeColumnWidth);
//		threeColTable1.setBackgroundColor(Color.BLACK,0.7f);
//			
//		threeColTable1.addCell(getHeaderTextCell("Tên Sản Phẩm").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
//		threeColTable1.addCell(getHeaderTextCell("Số Lượng").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
//		threeColTable1.addCell(getHeaderTextCell("Giá Thành").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
//		
//		document.add(threeColTable1);
////		
////		List<Product> productList = new ArrayList<>();
////		productList.add(new Product("apple", 2, 159));
////		productList.add(new Product("mango", 4, 205));
////		productList.add(new Product("banana", 2, 90));
////		productList.add(new Product("grapes", 3, 10));
////		productList.add(new Product("coconut", 2, 61));
////		productList.add(new Product("cherry", 1, 1000));
////		productList.add(new Product("kiwi", 3, 30));
////		
//		Table threeColTable2 = new Table(threeColumnWidth);
//		double totalSum = 0f;
//		for(ChiTietTraHang ct : pth.getDsChiTiet()) {
//			totalSum += ct.getSoLuongSP() * ct.getSanPham().TinhGiaBan();
//			threeColTable2.addCell(getHeaderTextCellValue(ct.getSanPham().getTenSP()).setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
//			threeColTable2.addCell(getHeaderTextCellValue(String.valueOf(ct.getSoLuongSP())).setTextAlignment(TextAlignment.CENTER));
//			threeColTable2.addCell(
//					getHeaderTextCellValue(MainFrame.moneyFormatter.format(
//							ct.getSanPham().TinhGiaBan())).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
//		}
//		
//		document.add(threeColTable2.setMarginBottom(20f));
//		float onetwo[] = {threecol + 125,threecol *2};
//		Table threeColTable4 = new Table(onetwo);
//		threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//		threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
//		document.add(threeColTable4);
//		
//		Table threeColTable3 = new Table(threeColumnWidth);
//		
//		threeColTable3.addCell(getHeaderTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
//		threeColTable3.addCell(getHeaderTextCellValue("Total").setTextAlignment(TextAlignment.CENTER));
//		threeColTable3.addCell(
//				getHeaderTextCellValue(MainFrame.moneyFormatter.format(
//						totalSum)).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
//		document.add(threeColTable3);
//		
//		document.add(tableDivider2);
//		document.add(new Paragraph("\n"));
//		document.add(divider.setBorder(new SolidBorder(Color.GRAY,1))).setBottomMargin(15f);
//		
//		Table tb = new Table(fullWidth);
//		tb.addCell(getHeaderTextCell("ĐIỀU KHOẢN VÀ ĐIỀU KIỆN\n").setTextAlignment(TextAlignment.LEFT));
//		List<String> TncList = new ArrayList<>();
//		TncList.add("1. Mỗi Hóa Đơn chỉ được hoàn 1 lần duy nhất");
////		TncList.add("2. The Seller warrants the product for one (1) year fromt he date of shipment");
//		
//		for(String tnc: TncList) {
//			tb.addCell(getHeaderTextCellValue(tnc));
//		}
//		
//		document.add(tb);
//		document.close();
//		System.out.println("Generated");
//	}