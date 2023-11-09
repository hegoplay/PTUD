package controller;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.JFreeChart;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

import component.TblNhanVien;
import entity.ChiTietTraHang;
import entity.NguoiQuanLy;
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
//sample test 
	public static void xuatTKDTNV(String path, LocalDate startDate, LocalDate endDate, NguoiQuanLy nql, TblNhanVien tblNV, JFreeChart chart) throws IOException {
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		
		Document document = new Document(pdfDocument);
		ImageData imageData = ImageDataFactory.create(MainFrame.class.getResource("/view/icon/shop_logo.png"));
		Image image = new Image(imageData);
		
		setFont();
		
		float x = pdfDocument.getDefaultPageSize().getWidth()/2;
		float y = pdfDocument.getDefaultPageSize().getHeight()/2;
		image.setFixedPosition(x-image.getImageWidth()/2, y - image.getImageHeight()/2);
		image.setOpacity(0.1f);
		document.add(image);
		
		
		//tao ra cac mang luu cac thông tin của 1 hàng
		float oneCol = 570f;
		float twocol = oneCol/2;
		float threecol = oneCol/3;
		float twocol150 = twocol + 150f; 
		float twoColumnWidth[] = {twocol,twocol};
		float twocolumnWidth150[] = {twocol150,twocol};
		float twoColumnWidth2p1[] = {threecol*2,threecol};
		float threeColumnWidth[] = {threecol,threecol,threecol};
		float sixColumnWidth[] = {threecol/2+ 45f,threecol/2 + 70f,threecol/2,threecol/2,threecol/2,threecol/2+ 45f};
		float fullWidth[] = {threecol*3};
		
		
		
		Paragraph onesp = new Paragraph("\n");
		Border gb = new SolidBorder(Color.GRAY, 2f);
		
		
		Table header = new Table(twoColumnWidth2p1 );
		header.addCell(new Cell().add("Phiếu Thống Kê Doanh Thu Nhân Viên")
				.setFont(font).setFontSize(20).setBold().setBorder(Border.NO_BORDER));
		Table timeCreate = new Table(twoColumnWidth);
		timeCreate.addCell(getHeaderTextCell("Ngày lập phiếu: ").setFontSize(10f).setTextAlignment(TextAlignment.LEFT)).setVerticalAlignment(VerticalAlignment.BOTTOM);
		timeCreate.addCell(getHeaderTextCell(
						LocalDateTime.now().format(
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString()).setFontSize(10f)
		).setVerticalAlignment(VerticalAlignment.BOTTOM);
		header.addCell(new Cell().add(timeCreate).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.BOTTOM));
		document.add(header);
		Table divider = new Table(fullWidth);
		Table tableDivider2 = new Table(fullWidth);
		Border dgb= new DashedBorder(Color.GRAY,0.5f);		
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
		
		Table subTable = new Table(twoColumnWidth);
		subTable.addCell(getHeaderTextCell("Ngày bắt đầu: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderTextCell(startDate.toString()));
		subTable.addCell(getHeaderTextCell("Ngày kết thúc: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderTextCell(endDate.toString()));
		subTable.addCell(getHeaderTextCell("Người Quản Lý thống kê: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderTextCell(nql.getTen()));
		subTable.addCell(getHeaderTextCell("Chi nhánh: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderTextCell("Gò Vấp, TPHCM"));
		
		Table fullColTable1 = new Table(fullWidth);
		
//		Tao du lieu anh de ve
		PDFDocument doc = new PDFDocument();
        Rectangle bounds = new Rectangle((int) pdfDocument.getDefaultPageSize().getWidth()- 100, 200);
        Page page = doc.createPage(bounds);
        PDFGraphics2D g2 = page.getGraphics2D();
        chart.draw(g2, bounds);
        
        
        PdfReader reader = new PdfReader(new ByteArrayInputStream(doc.getPDFBytes()));
        PdfDocument chartDoc = new PdfDocument(reader);
        PdfFormXObject chart1 = chartDoc.getFirstPage().copyAsFormXObject(pdfDocument);
        Image chartImage = new Image(chart1);
		
        Paragraph chartParaph = new Paragraph("").add(chartImage);
        document.add(chartParaph);
        //them thong tin ve ngay bat dau, ngay ket thuc
		fullColTable1.addCell(new Cell().add(subTable).setBorder(Border.NO_BORDER));
		document.add(fullColTable1);
		
		Paragraph productPara = new Paragraph("Nhân Viên");
		document.add(productPara.setBold().setFont(font));
		
//		Tạo title cho bảng
		Table sixColTable1 = new Table(sixColumnWidth);
		sixColTable1.setBackgroundColor(Color.BLACK,0.7f);
		sixColTable1.addCell(
				getHeaderTextCell("Mã nhân viên").setFontColor(Color.WHITE).setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		sixColTable1.addCell(
				getHeaderTextCell("Tên nhân viên").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
		sixColTable1.addCell(
				getHeaderTextCell("Giới tính").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				getHeaderTextCell("HĐ Lặp").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				getHeaderTextCell("SP bán").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				getHeaderTextCell("Doanh Thu").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		
		document.add(sixColTable1);	
		
//		Thêm thông tin các cột
		Table sixColumnTable2 = new Table(sixColumnWidth);
		double totalSum = 0f;
		for(int i = 0 ; i < tblNV.getModel().getRowCount();i++) {
			if((Double)tblNV.getValue(i, 6) <=0) { continue; }
			sixColumnTable2.addCell(getHeaderTextCellValue((String)tblNV.getValue(i, 1)).setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
			sixColumnTable2.addCell(getHeaderTextCellValue(tblNV.getValue(i, 2) + "").setTextAlignment(TextAlignment.LEFT));
			String gt = (Boolean)tblNV.getValue(i, 3) ? "Nam" : "Nữ";
			sixColumnTable2.addCell(getHeaderTextCellValue(gt).setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderTextCellValue(String.valueOf(tblNV.getValue(i, 4))).setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderTextCellValue(String.valueOf(tblNV.getValue(i, 5))).setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(
					getHeaderTextCellValue(MainFrame.moneyFormatter.format(
							tblNV.getValue(i, 6))).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
			totalSum += (Double)tblNV.getValue(i, 6);
		}
		
		document.add(sixColumnTable2.setMarginBottom(20f));
		float onetwo[] = {threecol + 125,threecol *2};
		Table threeColTable4 = new Table(onetwo);
		threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
		document.add(threeColTable4);
		
		Table threeColTable3 = new Table(threeColumnWidth);
		//Thêm thông tin tổng doanh thu
		threeColTable3.addCell(getHeaderTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable3.addCell(getHeaderTextCellValue("Tổng doanh thu").setTextAlignment(TextAlignment.CENTER));
		threeColTable3.addCell(
				getHeaderTextCellValue(MainFrame.moneyFormatter.format(
						totalSum) + " VND").setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable3);
		
		document.close();
		System.out.println("Generated");
	}
}
