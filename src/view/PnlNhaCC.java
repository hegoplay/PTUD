package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class PnlNhaCC extends JPanel {
	private JTextField textMaNCC;
	private JTextField textTenNhaCC;
	private JTextField textDiaChi;
	private JTextField textTimNhaCC;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PnlNhaCC() {
		setBackground(MainFrame.clrTheme);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("NHÀ CUNG CẤP");
		lblTitle.setBounds(420, 11, 248, 39);
		add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JLabel lblMaNhaCC = new JLabel("Mã Nhà cung cấp:");
		lblMaNhaCC.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaNhaCC.setBounds(10, 51, 166, 32);
		add(lblMaNhaCC);
		
		textMaNCC = new JTextField();
		textMaNCC.setBounds(186, 56, 248, 29);
		add(textMaNCC);
		textMaNCC.setColumns(10);
		
		JLabel lblQuocGia = new JLabel("Quốc gia:");
		lblQuocGia.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblQuocGia.setBounds(10, 94, 128, 32);
		add(lblQuocGia);
		
		JLabel lblTenNhaCC = new JLabel("Tên Nhà cung cấp:");
		lblTenNhaCC.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTenNhaCC.setBounds(580, 51, 176, 32);
		add(lblTenNhaCC);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(186, 102, 171, 22);
		add(comboBox);
		
		textTenNhaCC = new JTextField();
		textTenNhaCC.setColumns(10);
		textTenNhaCC.setBounds(766, 56, 263, 29);
		add(textTenNhaCC);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiaChi.setBounds(590, 94, 128, 32);
		add(lblDiaChi);
		
		textDiaChi = new JTextField();
		textDiaChi.setColumns(10);
		textDiaChi.setBounds(766, 99, 263, 29);
		add(textDiaChi);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(64, 128, 128));
		panel.setBounds(10, 192, 1019, 382);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblDSNhaCC = new JLabel("Danh sách Nhà cung cấp");
		lblDSNhaCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDSNhaCC.setBounds(10, 11, 190, 19);
		panel.add(lblDSNhaCC);
		
		JLabel lblTimNCC = new JLabel("Tìm kiếm theo mã:");
		lblTimNCC.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTimNCC.setBounds(61, 41, 164, 25);
		panel.add(lblTimNCC);
		
		textTimNhaCC = new JTextField();
		textTimNhaCC.setColumns(10);
		textTimNhaCC.setBounds(235, 37, 524, 29);
		panel.add(textTimNhaCC);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(0, 128, 192));
		btnTim.setBounds(774, 34, 103, 32);
		panel.add(btnTim);
		// Thêm dữ liệu cho các hàng khác ở đây

		table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("STT");
        tableModel.addColumn("Mã NCC");
        tableModel.addColumn("Tên Nhà cung cấp");
        tableModel.addColumn("SĐT");
        tableModel.addColumn("Địa chỉ");

        // Thêm dữ liệu vào bảng
        tableModel.addRow(new Object[] {1, "NCC0001", "Levent", "0836482846", "220/12 Nguyễn Oanh - P.17 - Q.Gò Vấp - TP.HCM"});
        tableModel.addRow(new Object[] {2, "NCC0003", "Juno", "0485685436", "12/76 Nguyễn Kiệm - P.5 - Q.Gò Vấp - TP.HCM"});

        table.setModel(tableModel);

        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setPreferredWidth(87);
        table.getColumnModel().getColumn(2).setPreferredWidth(145);
        table.getColumnModel().getColumn(3).setPreferredWidth(112);
        table.getColumnModel().getColumn(4).setPreferredWidth(252);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 77, 999, 294);
        panel.add(scrollPane);
		
		JButton btnThem = new JButton("Thêm mới");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setBackground(new Color(0, 128, 192));
		btnThem.setBounds(218, 148, 119, 32);
		add(btnThem);
		
		JButton btnSua = new JButton("Sửa thông tin");
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setBackground(new Color(0, 128, 192));
		btnSua.setBounds(457, 149, 128, 32);
		add(btnSua);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setBackground(new Color(0, 128, 192));
		btnLamMoi.setBounds(716, 148, 119, 32);
		add(btnLamMoi);

	}
}
