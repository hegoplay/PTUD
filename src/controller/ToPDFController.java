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

import component.TblCTTK;
import component.TblNhanVien;
import entity.ChiTietTraHang;
import entity.NguoiQuanLy;
import entity.PhieuTraHang;
import view.MainFrame;

public class ToPDFController {
	public static float oneCol = 570f;
	public static float twocol = oneCol / 2;
	public static float threecol = oneCol / 3;
	public static float twocol150 = twocol + 150f;
	public static float twoColumnWidth[] = { twocol, twocol };
	public static float twocolumnWidth150[] = { twocol150, twocol };
	public static float twoColumnWidth2p1[] = { threecol * 2, threecol };
	public static float threeColumnWidth[] = { threecol, threecol, threecol };
	public static float sixColumnWidth[] = { threecol / 2 + 45f, threecol / 2 + 70f, threecol / 2, threecol / 2, threecol / 2,
			threecol / 2 + 45f };
	public static float fullWidth[] = { threecol * 3 };
	public static PdfFont font;

//	
	public static void setFont() throws IOException {
		font = PdfFontFactory.createFont("c:/windows/fonts/times.ttf", PdfEncodings.IDENTITY_H, true);
	}

	public static PdfFont getFont() {
		return font;
	}

	public static Cell getHeaderLeftTextCell(String textValue) {
		return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT)
				.setFont(font);
	}

	public static Cell getHeaderLeftTextCellValue(String textValue) {
		return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setFont(font);
	}

	public static Cell getHeaderRightTextCell(String textValue) {
		return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
				.setFont(font);
	}

	public static Cell getHeaderRightTextCellValue(String textValue) {
		return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT)
				.setFont(font);
	}

	public static Cell getBillingandShippingCell(String textValue) {
		return new Cell().add(textValue).setFontSize(12f).setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT).setFont(font);
	}

	public static Cell getCell10fLeft(String textValue, Boolean isBold) {
		Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.LEFT).setFont(font);
		return isBold ? myCell.setBold() : myCell;
	}

//sample test 
	public static void xuatTKDTNV(String path, LocalDate startDate, LocalDate endDate, NguoiQuanLy nql,
			TblNhanVien tblNV, JFreeChart chart) throws IOException {
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);

		Document document = new Document(pdfDocument);
		ImageData imageData = ImageDataFactory.create(MainFrame.class.getResource("/view/icon/shop_logo.png"));
		Image image = new Image(imageData);

		setFont();

		float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
		float y = pdfDocument.getDefaultPageSize().getHeight() / 2;
		image.setFixedPosition(x - image.getImageWidth() / 2, y - image.getImageHeight() / 2);
		image.setOpacity(0.1f);
		document.add(image);

		// tao ra cac mang luu cac thông tin của 1 hàng

		Paragraph onesp = new Paragraph("\n");
		Border gb = new SolidBorder(Color.GRAY, 2f);

		Table header = new Table(twoColumnWidth2p1);
		header.addCell(new Cell().add("Phiếu Thống Kê Doanh Thu Nhân Viên").setFont(font).setFontSize(20).setBold()
				.setBorder(Border.NO_BORDER));
		Table timeCreate = new Table(twoColumnWidth);
		timeCreate
				.addCell(
						getHeaderLeftTextCell("Ngày lập phiếu: ").setFontSize(10f).setTextAlignment(TextAlignment.LEFT))
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		timeCreate.addCell(getHeaderLeftTextCell(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString())
				.setFontSize(10f)).setVerticalAlignment(VerticalAlignment.BOTTOM);
		header.addCell(
				new Cell().add(timeCreate).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.BOTTOM));
		document.add(header);
		Table divider = new Table(fullWidth);
		Table tableDivider2 = new Table(fullWidth);
		Border dgb = new DashedBorder(Color.GRAY, 0.5f);
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));

		Table subTable = new Table(twoColumnWidth);
		subTable.addCell(getHeaderLeftTextCell("Ngày bắt đầu: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderLeftTextCell(startDate.toString()));
		subTable.addCell(getHeaderLeftTextCell("Ngày kết thúc: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderLeftTextCell(endDate.toString()));
		subTable.addCell(getHeaderLeftTextCell("Người Quản Lý thống kê: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderLeftTextCell(nql.getTen()));
		subTable.addCell(getHeaderLeftTextCell("Chi nhánh: ").setTextAlignment(TextAlignment.LEFT));
		subTable.addCell(getHeaderLeftTextCell("Gò Vấp, TPHCM"));

		Table fullColTable1 = new Table(fullWidth);

//		Tao du lieu anh de ve
		PDFDocument doc = new PDFDocument();
		Rectangle bounds = new Rectangle((int) pdfDocument.getDefaultPageSize().getWidth() - 100, 200);
		Page page = doc.createPage(bounds);
		PDFGraphics2D g2 = page.getGraphics2D();
		chart.draw(g2, bounds);

		PdfReader reader = new PdfReader(new ByteArrayInputStream(doc.getPDFBytes()));
		PdfDocument chartDoc = new PdfDocument(reader);
		PdfFormXObject chart1 = chartDoc.getFirstPage().copyAsFormXObject(pdfDocument);
		Image chartImage = new Image(chart1);

		Paragraph chartParaph = new Paragraph("").add(chartImage);
		document.add(chartParaph);
		// them thong tin ve ngay bat dau, ngay ket thuc
		fullColTable1.addCell(new Cell().add(subTable).setBorder(Border.NO_BORDER));
		document.add(fullColTable1);

		Paragraph productPara = new Paragraph("Nhân Viên");
		document.add(productPara.setBold().setFont(font));

//		Tạo title cho bảng
		Table sixColTable1 = new Table(sixColumnWidth);
		sixColTable1.setBackgroundColor(Color.BLACK, 0.7f);
		sixColTable1.addCell(getHeaderLeftTextCell("Mã nhân viên").setFontColor(Color.WHITE).setMarginLeft(10f)
				.setTextAlignment(TextAlignment.LEFT));
		sixColTable1.addCell(
				getHeaderLeftTextCell("Tên nhân viên").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
		sixColTable1.addCell(
				getHeaderLeftTextCell("Giới tính").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				getHeaderLeftTextCell("HĐ Lặp").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				getHeaderLeftTextCell("SP bán").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(getHeaderLeftTextCell("Doanh Thu").setFontColor(Color.WHITE)
				.setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));

		document.add(sixColTable1);

//		Thêm thông tin các cột
		Table sixColumnTable2 = new Table(sixColumnWidth);
		double totalSum = 0f;
		for (int i = 0; i < tblNV.getModel().getRowCount(); i++) {
			if ((Double) tblNV.getValue(i, 6) <= 0) {
				continue;
			}
			sixColumnTable2.addCell(getHeaderLeftTextCellValue((String) tblNV.getValue(i, 1)).setMarginLeft(10f)
					.setTextAlignment(TextAlignment.LEFT));
			sixColumnTable2.addCell(
					getHeaderLeftTextCellValue(tblNV.getValue(i, 2) + "").setTextAlignment(TextAlignment.LEFT));
			String gt = (Boolean) tblNV.getValue(i, 3) ? "Nam" : "Nữ";
			sixColumnTable2.addCell(getHeaderLeftTextCellValue(gt).setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderLeftTextCellValue(String.valueOf(tblNV.getValue(i, 4)))
					.setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderLeftTextCellValue(String.valueOf(tblNV.getValue(i, 5)))
					.setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(tblNV.getValue(i, 6)))
					.setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
			totalSum += (Double) tblNV.getValue(i, 6);
		}

		document.add(sixColumnTable2.setMarginBottom(20f));
		float onetwo[] = { threecol + 125, threecol * 2 };
		Table threeColTable4 = new Table(onetwo);
		threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
		document.add(threeColTable4);

		Table threeColTable3 = new Table(threeColumnWidth);
		// Thêm thông tin tổng doanh thu
		threeColTable3.addCell(getHeaderLeftTextCellValue("").setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		threeColTable3.addCell(getHeaderLeftTextCellValue("Tổng doanh thu").setTextAlignment(TextAlignment.CENTER));
		threeColTable3.addCell(getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(totalSum) + " VND")
				.setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		document.add(threeColTable3);

		document.close();
		System.out.println("Generated");
	}

	public static void xuatTKTK(String path, LocalDate startDate, LocalDate endDate, NguoiQuanLy nql, TblCTTK tblCTTK,
			int[] values) throws IOException {
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);

		Document document = new Document(pdfDocument);
		ImageData imageData = ImageDataFactory.create(MainFrame.class.getResource("/view/icon/shop_logo.png"));
		Image image = new Image(imageData);

		setFont();

		float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
		float y = pdfDocument.getDefaultPageSize().getHeight() / 2;
		image.setFixedPosition(x - image.getImageWidth() / 2, y - image.getImageHeight() / 2);
		image.setOpacity(0.1f);
		document.add(image);

		// tao ra cac mang luu cac thông tin của 1 hàng

		Paragraph onesp = new Paragraph("\n");
		Border gb = new SolidBorder(Color.GRAY, 2f);

		Table header = new Table(twoColumnWidth2p1);
		header.addCell(new Cell().add("Phiếu Thống Kê Tồn Kho").setFont(font).setFontSize(20).setBold()
				.setBorder(Border.NO_BORDER));
		Table timeCreate = new Table(twoColumnWidth);
		timeCreate
				.addCell(
						getHeaderLeftTextCell("Ngày lập phiếu: ").setFontSize(10f).setTextAlignment(TextAlignment.LEFT))
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		timeCreate.addCell(getHeaderLeftTextCell(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString())
				.setFontSize(10f)).setVerticalAlignment(VerticalAlignment.BOTTOM);
		header.addCell(
				new Cell().add(timeCreate).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.BOTTOM));
		document.add(header);
		Table divider = new Table(fullWidth);
		Table tableDivider2 = new Table(fullWidth);
		Border dgb = new DashedBorder(Color.GRAY, 0.5f);
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
		document.add(onesp);
		// Thêm thông tin chung nhất
		Table twoColTable = new Table(twoColumnWidth);
		twoColTable.addCell(getHeaderLeftTextCell("Tổng sản phẩm"));
		twoColTable.addCell(getHeaderRightTextCell("Người thống kê"));
		twoColTable.addCell(getHeaderLeftTextCellValue(values[0] + ""));
		twoColTable.addCell(getHeaderRightTextCellValue(nql.getTen()));
		// hàng 2
		twoColTable.addCell(getHeaderLeftTextCell("Đã bán"));
		twoColTable.addCell(getHeaderRightTextCell("Mã Người quản lý"));
		twoColTable.addCell(getHeaderLeftTextCellValue(values[1] + ""));
		twoColTable.addCell(getHeaderRightTextCellValue(nql.getMaNV()));

		// hàng 3
		twoColTable.addCell(getHeaderLeftTextCell("Nhập mới"));
		twoColTable.addCell(getHeaderRightTextCell("Ngày đầu kỳ"));
		twoColTable.addCell(getHeaderLeftTextCellValue(values[2] + ""));
		twoColTable.addCell(getHeaderRightTextCellValue(MainFrame.timeFormatter.format(startDate)));

		// hàng 4
		twoColTable.addCell(getHeaderLeftTextCell("Còn lại"));
		twoColTable.addCell(getHeaderRightTextCell("Ngày cuối kì"));
		twoColTable.addCell(getHeaderLeftTextCellValue(values[3] + ""));
		twoColTable.addCell(getHeaderRightTextCellValue(MainFrame.timeFormatter.format(endDate)));

		document.add(twoColTable.setBorder(Border.NO_BORDER));

		document.add(divider.setBorder(gb).setMarginBottom(10f));
		
		Paragraph productPara = new Paragraph("Sản phẩm");
		document.add(productPara.setBold().setFont(font));

//		Tạo title cho bảng
		Table sixColTable1 = new Table(sixColumnWidth);
		sixColTable1.setBackgroundColor(Color.BLACK, 0.7f);
		sixColTable1.addCell(getHeaderLeftTextCell("Mã SP").setFontColor(Color.WHITE));
		sixColTable1.addCell(
				getHeaderLeftTextCell("Tên sản phẩm").setFontColor(Color.WHITE));
		sixColTable1.addCell(
				getHeaderLeftTextCell("Số lượng").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				getHeaderLeftTextCell("Đã bán").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				getHeaderLeftTextCell("Nhập mới").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(getHeaderRightTextCell("Còn lại").setFontColor(Color.WHITE));

		document.add(sixColTable1);

//		Thêm thông tin các cột
		Table sixColumnTable2 = new Table(sixColumnWidth);
		double totalSum = 0f;
		for (int i = 0; i < tblCTTK.getModel().getRowCount(); i++) {
			if ((int) tblCTTK.getValueAt(i, 9) <= 0) {
				continue;
			}
			sixColumnTable2.addCell(getHeaderLeftTextCellValue((String) tblCTTK.getValueAt(i, 1)));
			sixColumnTable2.addCell(
					getHeaderLeftTextCellValue(tblCTTK.getValueAt(i, 2) + ""));
			sixColumnTable2.addCell(getHeaderLeftTextCellValue(tblCTTK.getValueAt(i, 6) + "")
					.setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderLeftTextCellValue(String.valueOf(tblCTTK.getValueAt(i, 7)))
					.setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderLeftTextCellValue(String.valueOf(tblCTTK.getValueAt(i, 8)))
					.setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(getHeaderRightTextCellValue(MainFrame.moneyFormatter.format(tblCTTK.getValueAt(i, 9))));
		}

		document.add(sixColumnTable2.setMarginBottom(20f));


		document.close();
		System.out.println("Generated");
	}

	public static void xuatTKCH(String path, LocalDate startDate, LocalDate endDate, NguoiQuanLy nql,
			double[] values, JFreeChart lineChart, JFreeChart barChart) throws IOException {
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);

		Document document = new Document(pdfDocument);
		ImageData imageData = ImageDataFactory.create(MainFrame.class.getResource("/view/icon/shop_logo.png"));
		Image image = new Image(imageData);

		setFont();

		float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
		float y = pdfDocument.getDefaultPageSize().getHeight() / 2;
		image.setFixedPosition(x - image.getImageWidth() / 2, y - image.getImageHeight() / 2);
		image.setOpacity(0.1f);
		document.add(image);

		// tao ra cac mang luu cac thông tin của 1 hàng

		Paragraph onesp = new Paragraph("\n");
		Border gb = new SolidBorder(Color.GRAY, 2f);

		//Title
		
		Table header = new Table(twoColumnWidth2p1);
		header.addCell(new Cell().add("Phiếu Thống Kê Doanh Thu Cửa Hàng").setFont(font).setFontSize(20).setBold()
				.setBorder(Border.NO_BORDER));
		Table timeCreate = new Table(twoColumnWidth);
		timeCreate
				.addCell(
						getHeaderLeftTextCell("Ngày lập phiếu: ").setFontSize(10f).setTextAlignment(TextAlignment.LEFT))
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		timeCreate.addCell(getHeaderLeftTextCell(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString())
				.setFontSize(10f)).setVerticalAlignment(VerticalAlignment.BOTTOM);
		header.addCell(
				new Cell().add(timeCreate).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.BOTTOM));
		document.add(header);
		Table divider = new Table(fullWidth);
		Table tableDivider2 = new Table(fullWidth);
		Border dgb = new DashedBorder(Color.GRAY, 0.5f);
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
		document.add(onesp);
		// Thêm thông tin chung nhất
		Table twoColTable = new Table(twoColumnWidth);
		twoColTable.addCell(getHeaderLeftTextCell("Doanh Thu"));
		twoColTable.addCell(getHeaderRightTextCell("Người thống kê"));
		twoColTable.addCell(getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format( values[0])));
		twoColTable.addCell(getHeaderRightTextCellValue(nql.getTen()));
		// hàng 2
		twoColTable.addCell(getHeaderLeftTextCell("Doanh số"));
		twoColTable.addCell(getHeaderRightTextCell("Ngày đầu kỳ"));
		twoColTable.addCell(getHeaderLeftTextCellValue((int)values[1] + ""));
		twoColTable.addCell(getHeaderRightTextCellValue(MainFrame.timeFormatter.format(startDate)));

		// hàng 3
		twoColTable.addCell(getHeaderLeftTextCell("Lợi nhuậns"));
		twoColTable.addCell(getHeaderRightTextCell("Ngày cuối kỳ"));
		twoColTable.addCell(getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(values[2])));
		twoColTable.addCell(getHeaderRightTextCellValue(MainFrame.timeFormatter.format(endDate)));


		document.add(twoColTable.setBorder(Border.NO_BORDER));

		document.add(divider.setBorder(gb).setMarginBottom(10f));
		
		Paragraph productPara = new Paragraph("Biểu đồ đường thẳng doanh thu cửa hàng");
		document.add(productPara.setBold().setFont(font));

		PDFDocument doc = new PDFDocument();
		Rectangle bounds = new Rectangle((int) pdfDocument.getDefaultPageSize().getWidth() - 100, 200);
		Page page = doc.createPage(bounds);
		PDFGraphics2D g2 = page.getGraphics2D();
		lineChart.draw(g2, bounds);

		PdfReader reader = new PdfReader(new ByteArrayInputStream(doc.getPDFBytes()));
		PdfDocument chartDoc = new PdfDocument(reader);
		PdfFormXObject chart1 = chartDoc.getFirstPage().copyAsFormXObject(pdfDocument);
		Image chartImage = new Image(chart1);

		
		Paragraph chartParaph = new Paragraph("").add(chartImage);
		document.add(chartParaph);
		
//		barChart
		
		document.add(new Paragraph("Biểu đồ cột sản phẩm").setBold().setFont(font));

		PDFDocument doc1 = new PDFDocument();
		Page page1 = doc1.createPage(new Rectangle((int) pdfDocument.getDefaultPageSize().getWidth() - 100, 200));
		PDFGraphics2D g21 = page1.getGraphics2D();
		barChart.draw(g21, bounds);

		PdfReader reader1 = new PdfReader(new ByteArrayInputStream(doc1.getPDFBytes()));
		PdfDocument chartDoc1 = new PdfDocument(reader1);
		PdfFormXObject chart2 = chartDoc1.getFirstPage().copyAsFormXObject(pdfDocument);
		Image chartImage1 = new Image(chart2);

		
		Paragraph chartParaph1 = new Paragraph("").add(chartImage1);
		document.add(chartParaph1);
		
		document.close();
		System.out.println("Generated");
	}
}
