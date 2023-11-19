package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

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

import component.TblNhanVien;
import controller.ToPDFController;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import entity.ChiTietTraHang;
import entity.NguoiQuanLy;
import entity.NhanVien;
import entity.PhieuTraHang;

import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PnlTKDoanhThu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtTimTheoTen;
	private TblNhanVien tblNhanVien;
	private JComboBox<String> cmbTKTheo;
	private JLabel lblHoaDonLap;
	private JLabel lblDoanhSo;
	private JLabel lblDoanhThu;
	private JLabel lblTenNV;
	private JLabel lblMaNV;
	private JPanel pnlContent;
	private LocalDate startDay;
	private LocalDate endDay;
	private JButton btnXuatFile;
	private JFreeChart barChart;
	private JButton btnTim;

	/**
	 * Create the panel.
	 */
	public PnlTKDoanhThu() {
		setBorder(new EmptyBorder(4, 25, 4, 25));
		setBackground(MainFrame.clrTheme);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTKTheo = new JPanel();
		add(pnlTKTheo, BorderLayout.NORTH);
		pnlTKTheo.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pnlTKTheo.setBackground(MainFrame.clrTheme);

		JLabel lblTKTheo = new JLabel("Thống kê theo");
		lblTKTheo.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlTKTheo.add(lblTKTheo);

		cmbTKTheo = new JComboBox<String>(new String[] { "Ngày", "Tháng", "Kỳ", "Năm" });
		cmbTKTheo.setFont(new Font("Tahoma", Font.PLAIN, 17));

		pnlTKTheo.add(cmbTKTheo);

		btnXuatFile = new JButton("Xuất file");
		btnXuatFile.setBackground(MainFrame.clrCyan4);
		btnXuatFile.setForeground(new Color(255, 255, 255));
		btnXuatFile.setIcon(new ImageIcon(PnlTKDoanhThu.class.getResource("/view/icon/file_icon.png")));
		btnXuatFile.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlTKTheo.add(btnXuatFile);

		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(MainFrame.clrCyan3);
		pnlTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Chi ti\u1EBFt th\u1ED1ng k\u00EA",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlTable, BorderLayout.SOUTH);
		pnlTable.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimTheoTen = new JPanel();
		pnlTimTheoTen.setBackground(MainFrame.clrCyan3);
		pnlTable.add(pnlTimTheoTen, BorderLayout.NORTH);
		pnlTimTheoTen.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));

		JLabel lblTimTheoTen = new JLabel("Tìm kiếm theo tên");
		lblTimTheoTen.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlTimTheoTen.add(lblTimTheoTen);

		txtTimTheoTen = new JTextField(50);
		txtTimTheoTen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTimTheoTen.setColumns(18);
		pnlTimTheoTen.add(txtTimTheoTen);

		btnTim = new JButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnTim.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/magnifying_glass_icon.png")));
		btnTim.setBackground(MainFrame.clrCyan4);
		pnlTimTheoTen.add(btnTim);

		JScrollPane srcNhanVien = new JScrollPane();
		srcNhanVien.setMaximumSize(new Dimension(1000, 400));
		srcNhanVien.setPreferredSize(new Dimension(100, 174));
		pnlTable.add(srcNhanVien, BorderLayout.CENTER);

		tblNhanVien = new TblNhanVien();
		tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tblNhanVien.setRowHeight(30);
		srcNhanVien.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		srcNhanVien.setViewportView(tblNhanVien);

		JPanel pnlChart = new JPanel();
		pnlChart.setBorder(new EmptyBorder(20, 0, 10, 0));
		pnlChart.setBackground(MainFrame.clrTheme);
		add(pnlChart, BorderLayout.CENTER);
		pnlChart.setLayout(new BorderLayout(0, 0));

		JPanel pnlInfo = new JPanel();
		pnlInfo.setBorder(new EmptyBorder(15, 15, 15, 15));
		pnlInfo.setBackground(MainFrame.clrCyan3);
		pnlChart.add(pnlInfo, BorderLayout.EAST);
		pnlInfo.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnlTitle.setBackground(MainFrame.clrCyan3);
		pnlInfo.add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Nhân viên xuất sắc");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlTitle.add(lblTitle, BorderLayout.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel pnlDetails = new JPanel();
		pnlDetails.setBackground(MainFrame.clrCyan3);
		pnlInfo.add(pnlDetails, BorderLayout.CENTER);
		pnlDetails.setLayout(new GridLayout(5, 2, 30, 5));

		JLabel lblTitleMaNV = new JLabel("Mã nhân viên");
		lblTitleMaNV.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleMaNV);

		lblMaNV = new JLabel("NV00000037");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblMaNV);

		JLabel lblTitleTenNV = new JLabel("Tên nhân viên:");
		lblTitleTenNV.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleTenNV);

		lblTenNV = new JLabel("Nguyễn Xuân Khôi");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblTenNV);

		JLabel lblTitleDoanhThu = new JLabel("Doanh Thu:");
		lblTitleDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleDoanhThu);

		lblDoanhThu = new JLabel("219.000.000 VNĐ");
		lblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblDoanhThu);

		JLabel lblTitleHDLap = new JLabel("Số hóa đơn đã lập:");
		lblTitleHDLap.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleHDLap);

		lblHoaDonLap = new JLabel("65");
		lblHoaDonLap.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblHoaDonLap);

		JLabel lblTitleDoanhSo = new JLabel("Doanh số:");
		lblTitleDoanhSo.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleDoanhSo);

		lblDoanhSo = new JLabel("334");
		lblDoanhSo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblDoanhSo);

		pnlContent = new JPanel();
		pnlContent.setBackground(MainFrame.clrTheme);
		pnlChart.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));

		
		LoadTable();
		
		cmbTKTheo.addActionListener(this);
		btnXuatFile.addActionListener(this);
		btnTim.addActionListener(this);
//		JPanel panel = new JPanel();
//		pnlTitle.add(panel, BorderLayout.CENTER);
	}

	private void LoadTable() {
		tblNhanVien.removeAllRow();
		pnlContent.removeAll();
		pnlContent.repaint();
		ArrayList<NhanVien> lists = NhanVienDAO.getAllNhanVien();
		
		//Tính khoảng ngày cần xử lý
		startDay = LocalDateTime.now().with(LocalTime.MIDNIGHT).toLocalDate();
		endDay = LocalDateTime.now().plusDays(1).with(LocalTime.MIDNIGHT).toLocalDate();
		switch ((String) cmbTKTheo.getSelectedItem()) {
			case "Tháng": {
				startDay = startDay.withDayOfMonth(1);
				endDay = startDay.plusMonths(1);
				break;
			}
			case "Kỳ": {
				startDay = startDay.withDayOfYear(1);
				System.out.println(startDay);
				endDay = startDay;
				while (endDay.isBefore(LocalDate.now())) {
					endDay = endDay.plusMonths(3);
				}
				startDay = endDay.minusMonths(3);
				break;
			}
			case "Năm": {
				startDay = startDay.withDayOfYear(1);
				endDay = startDay.plusYears(1);
				break;
			}
		}
		for (int i = 0; i < lists.size(); i++) {
			NhanVien nv = lists.get(i);
			tblNhanVien.addRow(String.format("%03d", i), nv, HoaDonDAO.GetSLHDCuaNV(nv, startDay, endDay),
					HoaDonDAO.GetTongSPNV(nv, startDay, endDay), HoaDonDAO.GetTongDTNV(nv, startDay, endDay));
		}
		lblMaNV.setText((String) tblNhanVien.getValueAt(0, 1));
		lblTenNV.setText((String) tblNhanVien.getValueAt(0, 2));
		lblHoaDonLap.setText((Integer) tblNhanVien.getValueAt(0, 4) + "");
		lblDoanhSo.setText((Integer) tblNhanVien.getValueAt(0, 5) + "");
		lblDoanhThu.setText(MainFrame.moneyFormatter.format((Double) tblNhanVien.getValueAt(0, 6)) + "VND");


		barChart = ChartFactory.createBarChart("Top nhân viên có doanh thu cao trong tháng", "",
				"Lợi nhuận", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		barChart.getTitle().setFont(new Font("Times New Roman", Font.BOLD, 24));
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setMouseZoomable(false);
		chartPanel.setDisplayToolTips(true);
		pnlContent.add(chartPanel,BorderLayout.CENTER);
		chartPanel.setLayout(new BorderLayout(0, 0));
		pnlContent.revalidate();
		
	}

	private CategoryDataset createDataset() {
		final String trongThang = "Tháng này";
		final String thangTruoc = "Tháng trước";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();             

		for (int i = 0; i < 5 && i < tblNhanVien.getRowCount(); i++) {
			String arr[] = ((String) tblNhanVien.getValueAt(i, 2)).trim().split(" ");
			dataset.addValue((Double) tblNhanVien.getValueAt(i, 6), trongThang,
					arr[arr.length-1] + " " + arr[0]);
			
		}

		
		return dataset;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnXuatFile) {
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Chọn vị trí muốn lưu");   
				fileChooser.setCurrentDirectory(new File(MainFrame.PdfPath));
				int userSelection = fileChooser.showSaveDialog(this);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					ToPDFController.xuatTKDTNV(
							fileToSave.getAbsolutePath(), 
							startDay, 
							endDay, 
							NhanVienDAO.getNguoiQuanLy("NV00000000"), 
							tblNhanVien,
							barChart
							);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btnTim) {
			 timMa();
		}
		LoadTable();
	}

	private void timMa() {
		boolean check = false;
		for (int i = 0 ; i < tblNhanVien.getRowCount();i++) {
			if(((String)tblNhanVien.getValueAt(i, 1)).equalsIgnoreCase(txtTimTheoTen.getText()) ) {
				tblNhanVien.setRowSelectionInterval(i, i);
				tblNhanVien.requestFocus();
				check =true;
				
				break;
			}
		}
		if (!check) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy mã");
		}
	}
	
}
