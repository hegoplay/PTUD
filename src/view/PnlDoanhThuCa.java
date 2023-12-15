package view;

import java.awt.BorderLayout;
import com.itextpdf.kernel.color.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
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
import entity.NhanVien;
import entity.PhieuTraHang;
import view.MainFrame;

import component.TblCTTK;
import controller.ToPDFController;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;

import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;

public class PnlDoanhThuCa extends JPanel implements ActionListener{
	private DefaultTableModel model;
	private JTable table;
	private JButton btnXuat ;
	private JLabel lblSoSP, lblDTVND, lblSoHD;
	private HoaDonDAO hdDAO;
	private NhanVienDAO nv;
	
	
	private String currentUsername = System.getProperty("user.name");
	private String maNVLogin = nv.getMaNVbyUserName(currentUsername);
	private DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String maNV = MainFrame.nv.getMaNV();


	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public PnlDoanhThuCa() throws Exception {
		setBackground(MainFrame.clrTheme);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pnlHeader = new JPanel();
		add(pnlHeader);
		pnlHeader.setBackground(MainFrame.clrTheme);
		pnlHeader.setLayout(new GridLayout(2, 1, 10, 0));
		
		JPanel pnlMainContent = new JPanel();
		add(pnlMainContent);
		pnlMainContent.setBackground(MainFrame.clrTheme);
		pnlMainContent.setBorder(BorderFactory.createEmptyBorder(20,20, 15, 20));
		pnlMainContent.setLayout(new BorderLayout());
		
		JPanel pnlTopContent = new JPanel();
		pnlTopContent.setBackground(MainFrame.clrTheme);
		pnlTopContent.setBorder(BorderFactory.createEmptyBorder(0,0, -80, 0));
		pnlTopContent.setLayout(new BorderLayout());
		pnlHeader.add(pnlTopContent);
		
		JPanel pnlTopInfo = new JPanel();
		pnlTopInfo.setLayout(new GridLayout(1,2,5,0));
		pnlTopInfo.setBackground(MainFrame.clrTheme);
		pnlTopInfo.setBorder(BorderFactory.createEmptyBorder(-80,40, 0, 0));
		pnlTopContent.add(pnlTopInfo, BorderLayout.CENTER);
		
		JPanel pnlNV = new JPanel();
		pnlNV.setLayout(new GridLayout(1,2,5,0));
		pnlNV.setBackground(MainFrame.clrTheme);
		pnlTopInfo.add(pnlNV);
		
		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNV.setBackground(MainFrame.clrTheme);
		pnlNV.add(lblTenNV);
		
		JLabel lblNV = new JLabel("vvvv");
		lblNV.setEnabled(false);
		lblNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNV.setBackground(MainFrame.clrTheme);
		pnlNV.add(lblNV);
		
		JPanel pnlNgay = new JPanel();
		pnlNgay.setLayout(new GridLayout(1, 2, 5, 0));
		pnlNgay.setBackground(MainFrame.clrTheme);
		pnlTopInfo.add(pnlNgay, BorderLayout.CENTER);	
		
		
		JLabel lblNgay = new JLabel("Ngày thống kê:");
		lblNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgay.setBackground(MainFrame.clrTheme);
		pnlNgay.add(lblNgay);
		
		JLabel lbldate = new JLabel("");
		lbldate.setEnabled(false);
		lbldate.setBackground(MainFrame.clrTheme);
		lbldate.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlNgay.add(lbldate);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày tháng
		Date ngayHienHanh = new Date(System.currentTimeMillis()); // Lấy ngày hiện hành
		String ngayHienHanhFormatted = dateFormat.format(ngayHienHanh); // Định dạng ngày hiện hành

		lbldate.setText(ngayHienHanhFormatted); 
		
		
		JPanel pnlBnt = new JPanel();
		pnlBnt.setBackground(MainFrame.clrTheme);
		pnlBnt.setBorder(BorderFactory.createEmptyBorder(30, 0, -20, 50));
		pnlTopContent.add(pnlBnt, BorderLayout.EAST);	
		pnlBnt.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		btnXuat = new JButton("Xuất file");
		btnXuat.setForeground(MainFrame.clrLblColor);
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuat.setBackground(MainFrame.clrBtn);
		btnXuat.setIcon(new ImageIcon(PnlDoanhThuCa.class.getResource("/view/icon/print_icon.png")));

		pnlBnt.add(btnXuat);
	
		
//Số liệu thống kê		
		JPanel pnlStatistic = new JPanel();
		pnlStatistic.setLayout(new GridLayout(1, 3, 100, 0));
		pnlStatistic.setBorder(BorderFactory.createEmptyBorder(0,50, 0, 50));
		pnlStatistic.setBackground(MainFrame.clrTheme);
		pnlHeader.add(pnlStatistic);

		JPanel pnlDT = new JPanel();
		pnlDT.setBackground(MainFrame.clrPnlDTCa);
		pnlDT.setBorder(BorderFactory.createEmptyBorder(20,0, 20, 0));
		pnlDT.setLayout(new BorderLayout());
		pnlStatistic.add(pnlDT);
		
		JLabel lblDoanhThu = new JLabel("Doanh thu");
		lblDoanhThu.setBorder(BorderFactory.createEmptyBorder(0,65, 10, 0));
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlDT.add(lblDoanhThu, BorderLayout.NORTH);
		
		lblDTVND = new JLabel("0 VNĐ");
		lblDTVND.setHorizontalAlignment(SwingConstants.CENTER);
		lblDTVND.setBackground(UIManager.getColor("Button.background"));
		lblDTVND.setForeground(MainFrame.clrRed);
		lblDTVND.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlDT.add(lblDTVND, BorderLayout.CENTER);

//		
		JPanel pnlHD = new JPanel();
		pnlHD.setBackground(MainFrame.clrPnlDTCa);
		pnlHD.setBorder(BorderFactory.createEmptyBorder(20,0, 20, 0));
		pnlHD.setLayout(new BorderLayout());
		pnlStatistic.add(pnlHD);
		
		JLabel lblHD = new JLabel("Hóa đơn đã lập");
		lblHD.setBorder(BorderFactory.createEmptyBorder(0,50, 10, 0));
		lblHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlHD.add(lblHD, BorderLayout.NORTH);

		lblSoHD = new JLabel("0");
		lblSoHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoHD.setForeground(MainFrame.clrRed);
		lblSoHD.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSoHD.setBackground(UIManager.getColor("Button.background"));
		pnlHD.add(lblSoHD, BorderLayout.CENTER);
		
		JPanel pnlSP = new JPanel();
		pnlSP.setBackground(MainFrame.clrPnlDTCa);
		pnlSP.setBorder(BorderFactory.createEmptyBorder(20,0, 20, 0));
		pnlSP.setLayout(new BorderLayout());
		pnlStatistic.add(pnlSP);

		JLabel lblSP = new JLabel("Sản phẩm đã bán");
		lblSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSP.setBorder(BorderFactory.createEmptyBorder(0,10, 10, 0));
		pnlSP.add(lblSP, BorderLayout.NORTH);
		
		lblSoSP = new JLabel("0");
		lblSoSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoSP.setForeground(MainFrame.clrRed);
		lblSoSP.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSoSP.setBackground(UIManager.getColor("Button.background"));
		pnlSP.add(lblSoSP, BorderLayout.CENTER);
		
		
		JPanel pnlCTHD = new JPanel();
		pnlCTHD.setBackground(MainFrame.clrTableCT);
		pnlCTHD.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, MainFrame.clrLblColor, MainFrame.clrGrey), "Danh sách sản phẩm đã bán", TitledBorder.LEADING, TitledBorder.TOP, null, MainFrame.clrBlack));
		pnlCTHD.setLayout(new BorderLayout());
		pnlMainContent.add(pnlCTHD, BorderLayout.CENTER);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Tên sản phẩm");
		model.addColumn("Đơn giá (VNĐ)");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền (VNĐ)");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlCTHD.add(scrollPane, BorderLayout.CENTER);
		
		btnXuat.addActionListener(this);
		

		lblNV.setText(MainFrame.nv.getTen());

		
		loadTable();
//		double tongDT = hdDAO.getDoanhThuNgayTheoMaNV(maNVLogin);
		double tongDT = hdDAO.getDoanhThuNgayTheoMaNV(maNV);
		String tongDTstr = decimalFormat.format(tongDT);
		lblDTVND.setText(tongDTstr);
		
		
		int soLuongHD = laySoLuongHDDaLap();
		String soLuongHDStr = String.valueOf(soLuongHD);
		lblSoHD.setText(soLuongHDStr);
		
		int soLuongSP = getTongSoLuongDaBan();
		String soLuongSPstr =String.valueOf(soLuongSP);
		lblSoSP.setText(soLuongSPstr);
		
	}
	public void loadTable() throws Exception {

		ArrayList<HoaDon> dsHD = hdDAO.getHoaDonByMaNVinToDay(maNV);
		ArrayList<ChiTietHoaDon> cthd = hdDAO.getDSCTHDFromList(dsHD);
		ArrayList<ChiTietHoaDon > ctSPdaban = hdDAO.processDSCTHD(cthd);
		
	 // Sắp xếp danh sách chi tiết hóa đơn theo số lượng giảm dần
	    Collections.sort(ctSPdaban, Comparator.comparingInt(ChiTietHoaDon::getSoLuong).reversed());
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    int stt = 1;



	    for (ChiTietHoaDon ct : ctSPdaban) {
	            model.addRow(new Object[]{
	                stt++,
	                ct.getSanPham().getMaSP(),
	                ct.getSanPham().getTenSP(),
	                decimalFormat.format(ct.getSanPham().TinhGiaBan()),
	                ct.getSoLuong(),
	                decimalFormat.format(ct.getSoLuong()*ct.getSanPham().TinhGiaBan())
	            });
	        }
	    }
	public int laySoLuongHDDaLap() {
		ArrayList<HoaDon> dsHD = hdDAO.getHoaDonByMaNVinToDay(maNV);
		int soLuong = dsHD.size();
		return soLuong;
	}
	public int getTongSoLuongDaBan() throws Exception {
		ArrayList<HoaDon> dsHD = hdDAO.getHoaDonByMaNVinToDay(maNV);
		ArrayList<ChiTietHoaDon> cthd = hdDAO.getDSCTHDFromList(dsHD);
		ArrayList<ChiTietHoaDon > ctSPdaban = hdDAO.processDSCTHD(cthd);
	    int tongSoLuong = 0;

	    for (ChiTietHoaDon chiTietHoaDon : ctSPdaban) {
	        tongSoLuong += chiTietHoaDon.getSoLuong();
	    }

	    return tongSoLuong;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnXuat)) {
            try {
                JFileChooser fileChooser = new JFileChooser();

                // Đặt đường dẫn mặc định cho thư mục làm việc
                fileChooser.setCurrentDirectory(new File("C:\\Users\\Linh\\Desktop\\dsPDF\\phieuTKDTCa"));

                // Đặt tên file mặc định
                File defaultFile = new File(fileChooser.getCurrentDirectory(), "PhieuThongKe.pdf");
                fileChooser.setSelectedFile(defaultFile);

                // Hiển thị hộp thoại lựa chọn vị trí lưu trữ
                int userSelection = fileChooser.showSaveDialog(this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();

                    // Kiểm tra xem file đã tồn tại hay chưa
                    if (fileToSave.exists()) {
                        int result = JOptionPane.showConfirmDialog(this, "File đã tồn tại. Bạn có muốn ghi đè lên không?",
                                "Xác nhận ghi đè", JOptionPane.YES_NO_OPTION);
                        if (result != JOptionPane.YES_OPTION) {
                            return; // Người dùng không muốn ghi đè, thoát khỏi phương thức
                        }
                    }

                    // Tiếp tục với quá trình xuất PDF
                    String dt = lblDTVND.getText();
                    String soHD = lblSoHD.getText();
                    String soSP = lblSoSP.getText();
                    xuatDoanhThuCa(fileToSave.getAbsolutePath(), dt, soHD, soSP, table);
                    JOptionPane.showMessageDialog(this, "Phiếu thống kê đã được xuất thành công!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi tạo pdf!!");
            }
        }

	}
	public static void xuatDoanhThuCa(String path, String dthu,String sHD, String sSP, JTable tableSP) throws IOException {
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		NhanVien nvLogin= MainFrame.nv;

		Document document = new Document(pdfDocument);
		ImageData imageData = ImageDataFactory.create(MainFrame.class.getResource("/view/icon/shop_logo.png"));
		Image image = new Image(imageData);

		ToPDFController.setFont();

		float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
		float y = pdfDocument.getDefaultPageSize().getHeight() / 2;
		image.setFixedPosition(x - image.getImageWidth() / 2, y - image.getImageHeight() / 2);
		image.setOpacity(0.1f);
		document.add(image);

		// tao ra cac mang luu cac thông tin của 1 hàng

		Paragraph onesp = new Paragraph("\n");
		Border gb = new SolidBorder(Color.GRAY, 2f);

		Table header = new Table(ToPDFController.twoColumnWidth2p1);
		header.addCell(new Cell().add("PHIẾU THỐNG KÊ DOANH THU TRONG CA LÀM").setFont(ToPDFController.getFont()).setFontSize(20).setBold()
				.setBorder(Border.NO_BORDER));
		Table timeCreate = new Table(ToPDFController.twoColumnWidth);
		timeCreate
				.addCell(
						ToPDFController.getHeaderLeftTextCell("Ngày thống kê: ").setFontSize(10f).setTextAlignment(TextAlignment.LEFT))
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		timeCreate.addCell(ToPDFController.getHeaderLeftTextCell(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString())
				.setFontSize(10f)).setVerticalAlignment(VerticalAlignment.BOTTOM);
		header.addCell(
				new Cell().add(timeCreate).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.BOTTOM));
		document.add(header);
		Table divider = new Table(ToPDFController.fullWidth);
		Table tableDivider2 = new Table(ToPDFController.fullWidth);
		Border dgb = new DashedBorder(Color.GRAY, 0.5f);
		document.add(tableDivider2.setBorder(dgb).setMarginTop(12f));
		document.add(onesp);
		// Thêm thông tin chung nhất
		Table twoColTable = new Table(ToPDFController.twoColumnWidth);
		twoColTable.addCell(ToPDFController.getHeaderLeftTextCell("Tênnhân viên"));		
		twoColTable.addCell(ToPDFController.getHeaderRightTextCell("Mã nhân viên"));	
		twoColTable.addCell(ToPDFController.getHeaderLeftTextCellValue(MainFrame.nv.getTen()));
		twoColTable.addCell(ToPDFController.getHeaderRightTextCellValue(MainFrame.nv.getMaNV()));

		// hàng 2
		twoColTable.addCell(ToPDFController.getHeaderLeftTextCell("Doanh thu trong ca:"));
		twoColTable.addCell(ToPDFController.getHeaderRightTextCell(dthu));

		// hàng 3
		twoColTable.addCell(ToPDFController.getHeaderLeftTextCell("Số hóa đơn đã lập trong ca:"));
		twoColTable.addCell(ToPDFController.getHeaderRightTextCell(sHD));

		// hàng 4
		twoColTable.addCell(ToPDFController.getHeaderLeftTextCell("Số sản phẩm đã bán được trong ca:"));
		twoColTable.addCell(ToPDFController.getHeaderRightTextCell(sSP));


		document.add(twoColTable.setBorder(Border.NO_BORDER));

		document.add(divider.setBorder(gb).setMarginBottom(10f));
		
		Paragraph productPara = new Paragraph("Danh sách các sản phẩm đã bán được:");
		document.add(productPara.setBold().setFont(ToPDFController.font));

//		Tạo title cho bảng
		Table sixColTable1 = new Table(ToPDFController.sixColumnWidth);
		sixColTable1.setBackgroundColor(Color.BLACK, 0.7f);
		sixColTable1.addCell(ToPDFController.getHeaderLeftTextCell("Số thứ tự").setFontColor(Color.WHITE));
		sixColTable1.addCell(
				ToPDFController.getHeaderLeftTextCell("Mã sản phẩm").setFontColor(Color.WHITE));
		sixColTable1.addCell(
				ToPDFController.getHeaderLeftTextCell("Tên sản phẩm").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				ToPDFController.getHeaderLeftTextCell("Đơn giá").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(
				ToPDFController.getHeaderLeftTextCell("Số lượng").setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		sixColTable1.addCell(ToPDFController.getHeaderRightTextCell("Thành tiền").setFontColor(Color.WHITE));
		document.add(sixColTable1);
		document.add(divider.setBorder(gb).setMarginBottom(10f));
		
		

		

//		Thêm thông tin các cột
		Table sixColumnTable2 = new Table(ToPDFController.sixColumnWidth);
		double totalSum = 0f;
		for (int i = 0; i < tableSP.getModel().getRowCount(); i++) {
			sixColumnTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(tableSP.getValueAt(i, 0)+""));
			sixColumnTable2.addCell(
					ToPDFController.getHeaderLeftTextCellValue(tableSP.getValueAt(i, 1) + ""));
			sixColumnTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(tableSP.getValueAt(i, 2) + ""));
			sixColumnTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(String.valueOf(tableSP.getValueAt(i, 3)+""))
					.setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(String.valueOf(tableSP.getValueAt(i, 4)+""))
					.setTextAlignment(TextAlignment.CENTER));
			sixColumnTable2.addCell(ToPDFController.getHeaderLeftTextCellValue(String.valueOf(tableSP.getValueAt(i, 5)+""))
					.setTextAlignment(TextAlignment.CENTER));
		}

		document.add(sixColumnTable2.setMarginBottom(20f));

		document.add(divider.setBorder(gb).setMarginBottom(10f));
		
		Table twoColTable2 = new Table(ToPDFController.twoColumnWidth);		
		twoColTable2.addCell(ToPDFController.getHeaderRightTextCell("Ghi chú:"));	
		twoColTable2.addCell(ToPDFController.getHeaderRightTextCellValue("Nộp phiếu này cho quản lý sau mỗi ca làm."));
		document.add(twoColTable2.setBorder(Border.NO_BORDER));

		document.close();
		System.out.println("Generated");
	}

}
	

