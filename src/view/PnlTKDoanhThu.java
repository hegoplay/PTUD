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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
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

import component.TblNhanVien;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import entity.NhanVien;

import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

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

		JButton btnXuatFile = new JButton("Xuất file");
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

		JButton btnTim = new JButton("Tìm");
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
//		JPanel panel = new JPanel();
//		pnlTitle.add(panel, BorderLayout.CENTER);
	}

	private void LoadTable() {
		tblNhanVien.removeAllRow();
		pnlContent.removeAll();
		pnlContent.repaint();
		ArrayList<NhanVien> lists = NhanVienDAO.getAllNhanVien();
		startDay = LocalDate.now();
		endDay = LocalDate.now().plusDays(1);

		switch ((String) cmbTKTheo.getSelectedItem()) {
		case "Tháng": {
			startDay = startDay.minusMonths(1);
		}
		case "Kỳ": {
			startDay = startDay.minusMonths(3);
		}
		case "Năm": {
			startDay = startDay.minusYears(1);
		}
		}
		for (int i = 0; i < lists.size(); i++) {
			NhanVien nv = lists.get(i);
			tblNhanVien.addRow(String.format("%03d", i), nv, HoaDonDAO.GetSLHDCuaNV(nv, startDay, endDay),
					HoaDonDAO.GetTongSPNV(nv, startDay, endDay), HoaDonDAO.GetTongDTNV(nv, startDay, endDay));
		}
		lblMaNV.setText((String) tblNhanVien.getValueAt(0, 1));
		lblTenNV.setText((String) tblNhanVien.getValueAt(0, 2));
		lblDoanhThu.setText((Integer) tblNhanVien.getValueAt(0, 4) + "");
		lblHoaDonLap.setText((Integer) tblNhanVien.getValueAt(0, 5) + "");
		lblDoanhSo.setText(new DecimalFormat("#0.00").format((Double) tblNhanVien.getValueAt(0, 6)));


		JFreeChart barChart = ChartFactory.createBarChart("Top Nhân Viên có doanh thu cao trong tháng", "",
				"Doanh thu", createDataset(), PlotOrientation.VERTICAL, true, true, false);
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
			dataset.addValue((Double) tblNhanVien.getValueAt(i, 6), trongThang, (String) tblNhanVien.getValueAt(i, 2));
//			dataset.addValue(HoaDonDAO.GetTongDTNV(new NhanVien((String) tblNhanVien.getValueAt(i, 1)), startDay.minusMonths(1), endDay.minusMonths(1)), thangTruoc, (String) tblNhanVien.getValueAt(i, 2));
		}

		
		return dataset;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == cbTKTheo) {
//			
//		}
		LoadTable();
	}

//	private void fillComboBox(JComboBox<String> comboBox) {
//		// TODO Auto-generated method stub
//		comboBox.addItem("Tháng");
//		comboBox.addItem("Tuần");
//		comboBox.addItem("Ngày");
//		comboBox.addItem("Năm");
//	}

}
