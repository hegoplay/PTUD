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

import component.TblNhanVien;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class PnlTKDoanhThu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTimTheoTen;
	
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlTKTheo.add(comboBox);
		
		JPanel pnlXuatFile = new JPanel();
		pnlXuatFile.setBackground(MainFrame.clrCyan4);
		pnlXuatFile.setBorder(new EmptyBorder(5, 9, 5, 9));
		pnlTKTheo.add(pnlXuatFile);
		
		JLabel lblXuatFile = new JLabel("Xuất file");
		lblXuatFile.setForeground(Color.WHITE);
		lblXuatFile.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXuatFile.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/file_icon.png")));
		pnlXuatFile.add(lblXuatFile);
		
		fillComboBox(comboBox);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(MainFrame.clrCyan3);
		pnlTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Chi ti\u1EBFt th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		TblNhanVien tblNhanVien = new TblNhanVien();
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
		
		JLabel lblMaNV = new JLabel("NV00000037");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblMaNV);
		
		JLabel lblTitleTenNV = new JLabel("Tên nhân viên:");
		lblTitleTenNV.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleTenNV);
		
		JLabel lblTenNV = new JLabel("Nguyễn Xuân Khôi");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblTenNV);
		
		JLabel lblTitleDoanhThu = new JLabel("Doanh Thu:");
		lblTitleDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleDoanhThu);
		
		JLabel lblDoanhThu = new JLabel("219.000.000 VNĐ");
		lblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblDoanhThu);
		
		JLabel lblTitleHDLap = new JLabel("Số hóa đơn đã lập:");
		lblTitleHDLap.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleHDLap);
		
		JLabel lblHoaDonLap = new JLabel("65");
		lblHoaDonLap.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblHoaDonLap);
		
		JLabel lblTitleDoanhSo = new JLabel("Doanh số:");
		lblTitleDoanhSo.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDetails.add(lblTitleDoanhSo);
		
		JLabel lblDoanhSo = new JLabel("334");
		lblDoanhSo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDetails.add(lblDoanhSo);
		
		
		
//		JPanel panel = new JPanel();
//		pnlTitle.add(panel, BorderLayout.CENTER);
	}

	private void fillComboBox(JComboBox<String> comboBox) {
		// TODO Auto-generated method stub
		comboBox.addItem("Tháng");
		comboBox.addItem("Tuần");
		comboBox.addItem("Ngày");
		comboBox.addItem("Năm");
	}

}
