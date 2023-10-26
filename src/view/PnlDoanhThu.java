package view;

import javax.swing.JPanel;
import java.awt.Color;
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

public class PnlDoanhThu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTimTheoTen;
	
	/**
	 * Create the panel.
	 */
	public PnlDoanhThu() {
		setBorder(new EmptyBorder(4, 25, 4, 25));
		setBackground(PnlThongKe.clrTheme);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTKTheo = new JPanel();
		add(pnlTKTheo, BorderLayout.NORTH);
		pnlTKTheo.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		pnlTKTheo.setBackground(PnlThongKe.clrTheme);
		
		JLabel lblTKTheo = new JLabel("Thống kê theo");
		lblTKTheo.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlTKTheo.add(lblTKTheo);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlTKTheo.add(comboBox);
		
		JPanel pnlXuatFile = new JPanel();
		pnlXuatFile.setBackground(MainFrame.clrBtnIdle);
		pnlXuatFile.setBorder(new EmptyBorder(5, 9, 5, 9));
		pnlTKTheo.add(pnlXuatFile);
		
		JLabel lblXuatFile = new JLabel("Xuất file");
		lblXuatFile.setForeground(Color.WHITE);
		lblXuatFile.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXuatFile.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/file_icon.png")));
		pnlXuatFile.add(lblXuatFile);
		
		fillComboBox(comboBox);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(pnlTable, BorderLayout.SOUTH);
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTimTheoTen = new JPanel();
		pnlTimTheoTen.setBackground(MainFrame.clrOpsBg);
		pnlTimTheoTen.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		btnTim.setBackground(MainFrame.clrBtnIdle);
		pnlTimTheoTen.add(btnTim);
		
		JScrollPane srcNhanVien = new JScrollPane();
		pnlTable.add(srcNhanVien, BorderLayout.CENTER);
		
		TblNhanVien tblNhanVien = new TblNhanVien();
		tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tblNhanVien.setRowHeight(30);
		srcNhanVien.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		srcNhanVien.setViewportView(tblNhanVien);
	}

	private void fillComboBox(JComboBox<String> comboBox) {
		// TODO Auto-generated method stub
		comboBox.addItem("Tháng");
		comboBox.addItem("Tuần");
		comboBox.addItem("Ngày");
		comboBox.addItem("Năm");
	}

}
