package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.itextpdf.layout.property.VerticalAlignment;

import controller.ToPDFController;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import view.MainFrame;
import view.PnlLHD;

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
	
	public void XuatHoaDon(String path, HoaDon hd) throws Exception {
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
		float fourcol = 142f;
		float twocol = 285f;
		float twocol150 = twocol + 150f; 
		float twocolumnWidth[] = {twocol150,twocol};
		float threeColumnWidth[] = {threecol,threecol,threecol};
		float fourColumnWidth[] = {fourcol,fourcol,fourcol, fourcol};
		float fullWidth[] = {threecol*3};
		Paragraph onesp = new Paragraph("\n");
		Table table = new Table(twocolumnWidth);
		table.addCell(new Cell().add("HÓA ĐƠN BÁN HÀNG").setFontSize(20f).
				setBorder(Border.NO_BORDER).setBold()).setFont(ToPDFController.getFont());
		Table nestedTable = new Table(new float[] {twocol/2,twocol/2});
		
		nestedTable.addCell(ToPDFController.getHeaderLeftTextCell("Mã hóa đơn: "));
		nestedTable.addCell(ToPDFController.getHeaderLeftTextCellValue(this.getMaHD()));
		
		nestedTable.addCell(ToPDFController.getHeaderLeftTextCell("Ngày Lập hóa đơn"));
		nestedTable.addCell(ToPDFController.getHeaderLeftTextCellValue(
		LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString()));
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
		twoColTable3.addCell(ToPDFController.getCell10fLeft(KhachHangDAO.getKhachHang(this.getKhachHang().getMaKH()).getTenKH(), false));
		twoColTable3.addCell(ToPDFController.getCell10fLeft(NhanVienDAO.getNhanVien(this.getNhanVien().getMaNV()).getTen(), false));
		document.add(twoColTable3);
		
		
		Table tableDivider2 = new Table(fullWidth);
		Border dgb= new DashedBorder(Color.GRAY,0.5f);
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
		
		Paragraph productPara = new Paragraph("Chi tiết hóa đơn");
		document.add(productPara.setBold().setFont(ToPDFController.getFont()));

		Table fourColTable1 = new Table(fourColumnWidth);
		fourColTable1.setBackgroundColor(Color.BLACK,0.7f);
			
		fourColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Tên Sản Phẩm").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
		fourColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Số Lượng").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		fourColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Đơn Giá").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		fourColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Thành Tiền").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
		
		document.add(fourColTable1);
		Table fourColTable2 = new Table(fourColumnWidth);
		double totalSum = 0f;
		for (ChiTietHoaDon ct : hd.dsCTHD) {
		    if (ct.getSanPham() != null) {
		        totalSum += ct.getSoLuong() * ct.getSanPham().TinhGiaBan();
		        
		        fourColTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(ct.getSanPham().getTenSP()).setMarginLeft(10f).setTextAlignment(TextAlignment.LEFT));
		        fourColTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(String.valueOf(ct.getSoLuong())).setTextAlignment(TextAlignment.CENTER));
		        fourColTable2.addCell(
		                ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
		                        ct.getSanPham().TinhGiaBan())+" VNĐ").setTextAlignment(TextAlignment.CENTER));
		        fourColTable2.addCell(
		                ToPDFController.getHeaderLeftTextCellValue(MainFrame.moneyFormatter.format(
		                        ct.TinhThanhTien())+" VNĐ").setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
		    } else {
		        System.out.println("Lỗi dữ liệu!");
		    }
		}

		
		document.add(fourColTable2.setMarginBottom(20f));
		
		float onetwo[] = {threecol + 125,threecol *2};
		Table threeColTable4 = new Table(onetwo);
		threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		threeColTable4.addCell(new Cell().add(tableDivider2).setBorder(Border.NO_BORDER));
		document.add(threeColTable4);
		
		Table table2 = new Table(twocolumnWidth);
		table2.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		
		Table nestedTable2 = new Table(new float[] {twocol/2,twocol/2});
		
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCell("Tổng cộng: "));
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCellValue(MainFrame.moneyFormatter.format(PnlLHD.getTongCong())+ " VNĐ"));
		
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCell("Khuyến mãi: "));

		
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCellValue(MainFrame.moneyFormatter.format(PnlLHD.getTongKM())+ " VNĐ"));// tinh tong khuyen mai
		
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCell("Tổng tiền: "));
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCellValue(MainFrame.moneyFormatter.format(PnlLHD.getTongTien())+" VNĐ"));
		
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCell("Tiền khách đưa: "));
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCellValue(MainFrame.moneyFormatter.format(this.getTienKhachDua())+" VNĐ"));
		
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCell("Tiền thừa: "));
		nestedTable2.addCell(ToPDFController.getHeaderRightTextCellValue(MainFrame.moneyFormatter.format(tienKhachDua-PnlLHD.getTongTien())+" VNĐ"));
		
		table2.addCell(new Cell().add(nestedTable2).setBorder(Border.NO_BORDER));
		document.add(table2);

		document.add(tableDivider2);
		document.add(new Paragraph("\n"));
		document.add(divider.setBorder(new SolidBorder(Color.GRAY,1))).setBottomMargin(15f);
		
		Table tb = new Table(fullWidth);
		tb.addCell(ToPDFController.getHeaderLeftTextCell("ĐIỀU KHOẢN VÀ ĐIỀU KIỆN\n").setTextAlignment(TextAlignment.LEFT));
		List<String> TncList = new ArrayList<>();
		TncList.add("1. Hóa đơn chỉ xuất trong ngày");
		TncList.add("2. Mỗi Hóa Đơn chỉ được hoàn 1 lần duy nhất");
		TncList.add("3. Chỉ xử lí các yêu cầu trả hàng trong vòng 7 ngày kể từ ngày xuất hóa đơn");
		TncList.add("4. Mang theo hóa đơn này khi muốn trả hàng");
		TncList.add("5. Giảm 5% cho hóa đơn từ 1.500.000 VNĐ và 10% cho hóa đơn trên 4.000.000 VNĐ");
		TncList.add("6. Tiền khuyến mãi đã bao gồm khấu trừ giảm giá chương trình SALE (nếu có).");
		for(String tnc: TncList) {
			tb.addCell(ToPDFController.getHeaderLeftTextCellValue(tnc));
		}
		
		Table tb2 = new Table(fullWidth);
		tb2.addCell(ToPDFController.getHeaderRightTextCell("CẢM ƠN QUÝ KHÁCH! \n  HẸN GẶP LẠI!").setTextAlignment(TextAlignment.CENTER));
		

		
		document.add(tb);
		document.add(tb2);
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
