package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import dao.SanPhamDAO;
import entity.SanPham;
import entity.NhaCC;
import entity.LoaiSP;
import entity.NhaCC;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class PnLSanPham extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textMaSP;
	private JTable table;
	private JTextField textField_TimSP;
	private JTable table_1;
	private JTextField textField_TenSP;
	private JTextField textField_GiaNhap;
	private JTextField textField_SoLuong;
	private JTextField textField_MauSac;
	private JTextField textField_TrangThai;
	private JComboBox<LoaiSP> comboBoxLoaiSP;
	private JComboBox<LoaiSP> comboBoxKichThuoc;
	private JComboBox<NhaCC> comboBox_NhaCC;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;

	/**
	 * Create the panel.
	 */
	public PnLSanPham() {
		setBackground(MainFrame.clrTheme);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 254, 224));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(765, 89, 358, 540);
		add(panel);
		panel.setLayout(null);

		JLabel lblMaSP = new JLabel("Mã Sản Phẩm:");
		lblMaSP.setBounds(13, 68, 120, 32);
		panel.add(lblMaSP);
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblgioiTinh = new JLabel("Giới tính:");
		lblgioiTinh.setBounds(11, 333, 122, 32);
		panel.add(lblgioiTinh);
		lblgioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnThemSP = new JButton("Thêm ");
		btnThemSP.setBounds(34, 478, 91, 32);
		panel.add(btnThemSP);
		btnThemSP.setForeground(new Color(255, 255, 255));
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemSP.setBackground(new Color(0, 128, 192));

		JButton btnSuaSP = new JButton("Sửa ");
		btnSuaSP.setBounds(141, 479, 91, 32);
		panel.add(btnSuaSP);
		btnSuaSP.setForeground(new Color(255, 255, 255));
		btnSuaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSuaSP.setBackground(new Color(0, 128, 192));

		JButton btnLamMoiSP = new JButton("Làm mới");
		btnLamMoiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLamMoiSP.setBounds(246, 479, 91, 32);
		panel.add(btnLamMoiSP);
		btnLamMoiSP.setForeground(new Color(255, 255, 255));
		btnLamMoiSP.setBackground(new Color(0, 128, 192));

		textMaSP = new JTextField();
		textMaSP.setBounds(143, 66, 132, 28);
		panel.add(textMaSP);
		textMaSP.setColumns(10);
		textMaSP.setEditable(false);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(172, 338, 67, 31);
		panel.add(rdbtnNam);
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 17));

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(275, 338, 55, 31);
		panel.add(rdbtnNu);
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblsubtitleSP = new JLabel("Sản Phẩm");
		lblsubtitleSP.setBounds(79, 16, 171, 39);
		panel.add(lblsubtitleSP);
		lblsubtitleSP.setFont(new Font("Tahoma", Font.BOLD, 28));

		JLabel lblTenSP = new JLabel("Tên Sản Phẩm:");
		lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSP.setBounds(13, 98, 120, 32);
		panel.add(lblTenSP);

		textField_TenSP = new JTextField();
		textField_TenSP.setColumns(10);
		textField_TenSP.setBounds(143, 101, 205, 28);
		panel.add(textField_TenSP);

		JLabel lblLoiSnPhm = new JLabel("Loại Sản Phẩm:");
		lblLoiSnPhm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoiSnPhm.setBounds(13, 135, 120, 32);
		panel.add(lblLoiSnPhm);

		JLabel lblKichThuoc = new JLabel("Kích Thước:");
		lblKichThuoc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKichThuoc.setBounds(13, 174, 120, 32);
		panel.add(lblKichThuoc);

		JLabel lblGiaNhap = new JLabel("Giá Nhập:");
		lblGiaNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaNhap.setBounds(9, 218, 124, 32);
		panel.add(lblGiaNhap);

		textField_GiaNhap = new JTextField();
		textField_GiaNhap.setColumns(10);
		textField_GiaNhap.setBounds(143, 216, 205, 28);
		panel.add(textField_GiaNhap);

		JLabel lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoLuong.setBounds(13, 259, 120, 32);
		panel.add(lblSoLuong);

		textField_SoLuong = new JTextField();
		textField_SoLuong.setColumns(10);
		textField_SoLuong.setBounds(143, 258, 205, 28);
		panel.add(textField_SoLuong);

		JLabel lblMauSac = new JLabel("Màu Sắc");
		lblMauSac.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMauSac.setBounds(13, 297, 120, 32);
		panel.add(lblMauSac);

		textField_MauSac = new JTextField();
		textField_MauSac.setColumns(10);
		textField_MauSac.setBounds(143, 300, 205, 28);
		panel.add(textField_MauSac);

		JLabel lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrangThai.setBounds(13, 369, 120, 32);
		panel.add(lblTrangThai);

		textField_TrangThai = new JTextField();
		textField_TrangThai.setColumns(10);
		textField_TrangThai.setBounds(143, 372, 205, 28);
		panel.add(textField_TrangThai);

		JLabel lblNCC = new JLabel("Nhà Cung Cấp:");
		lblNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNCC.setBounds(13, 409, 120, 32);
		panel.add(lblNCC);

		JLabel lblHinhAnh = new JLabel("Hình Ảnh:");
		lblHinhAnh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHinhAnh.setBounds(13, 448, 120, 32);
		panel.add(lblHinhAnh);

		comboBoxLoaiSP = new JComboBox();
		comboBoxLoaiSP.setBounds(143, 139, 205, 26);
		panel.add(comboBoxLoaiSP);

		comboBoxKichThuoc = new JComboBox();
		comboBoxKichThuoc.setBounds(143, 180, 205, 26);
		panel.add(comboBoxKichThuoc);

		comboBox_NhaCC = new JComboBox();
		comboBox_NhaCC.setBounds(143, 415, 205, 26);
		panel.add(comboBox_NhaCC);

		JButton btnTaoSP = new JButton("Tạo");
		btnTaoSP.setForeground(new Color(0, 0, 0));
		btnTaoSP.setBackground(new Color(0, 128, 192));
		btnTaoSP.setBounds(279, 63, 68, 32);
		panel.add(btnTaoSP);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 89, 755, 542);
		add(panel_1);
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(new Color(64, 128, 128));

		JLabel lblDanhSch = new JLabel("Danh Sách Sản Phẩm");
		lblDanhSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDanhSch.setBounds(10, 11, 190, 19);
		panel_1.add(lblDanhSch);

		JLabel lblTimKH = new JLabel("Tìm theo mã:");
		lblTimKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTimKH.setBounds(20, 40, 116, 25);
		panel_1.add(lblTimKH);

		JButton btnTimSP = new JButton("Tìm");
		btnTimSP.setForeground(Color.WHITE);
		btnTimSP.setBackground(new Color(0, 128, 192));
		btnTimSP.setBounds(332, 33, 93, 32);
		panel_1.add(btnTimSP);

		textField_TimSP = new JTextField();
		textField_TimSP.setColumns(10);
		textField_TimSP.setBounds(149, 34, 173, 31);
		panel_1.add(textField_TimSP);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 755, 465);
		panel_1.add(scrollPane);

		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, "", null, null, null },
						{ null, null, null, null, null, null, null, "", null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m",
						"Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "K\u00EDch Th\u01B0\u1EDBc", "Gi\u00E1 Nh\u1EADp",
						"S\u1ED1 L\u01B0\u1EE3ng", "M\u00E0u S\u1EAFc", "Gi\u1EDBi T\u00EDnh", "Tr\u1EA1ng Th\u00E1i",
						"Nh\u00E0 Cung C\u1EA5p", "H\u00ECnh \u1EA2nh" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(87);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(79);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(81);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(61);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(51);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(49);
		table_1.getColumnModel().getColumn(7).setPreferredWidth(56);
		scrollPane.setViewportView(table_1);

		JLabel lblTmTheoLoai = new JLabel("Tìm theo loại:");
		lblTmTheoLoai.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTmTheoLoai.setBounds(475, 40, 116, 25);
		panel_1.add(lblTmTheoLoai);

		JComboBox comboBox_TimSP = new JComboBox();
		comboBox_TimSP.setBounds(601, 33, 116, 33);
		panel_1.add(comboBox_TimSP);

		JLabel lblTitle = new JLabel("Quản Lý Sản Phẩm");
		lblTitle.setBounds(795, 28, 320, 39);
		add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		loadDataToTable();

	}

	private void loadDataToTable() {
		try {
			// Lấy dữ liệu từ cơ sở dữ liệu hoặc từ nơi khác
			ArrayList<SanPham> danhSachSanPham = SanPhamDAO.getAllSanPham();

			// Tạo một DefaultTableModel để hiển thị dữ liệu trên JTable
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setRowCount(0); // Xóa tất cả dữ liệu cũ trên JTable

			// Duyệt qua danh sách và thêm từng dòng dữ liệu vào model
			for (SanPham sp : danhSachSanPham) {
				model.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP().getTenLoai(), sp.getKichThuoc(),
						sp.getGiaNhap(), sp.getSlTonKho(), sp.getMauSac(), (sp.isNam() ? "Nam" : "Nữ"),
						(sp.isConKinhDoanh() ? "Con" : "Khong"), sp.getNhaCC().getTenNCC(), sp.getHinhAnh()
						// Thêm các trường khác tương ứng
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Xử lý ngoại lệ nếu có
		}

	}

	private void clearForm() {
		textMaSP.setText("");
		textField_TenSP.setText("");
		comboBoxLoaiSP.setSelectedItem(-1);
		comboBoxKichThuoc.setSelectedItem(-1);
		textField_GiaNhap.setText("");
		textField_SoLuong.setText("");
		textField_MauSac.setText("");
		rdbtnNam.setSelected(true);
		comboBox_NhaCC.setSelectedItem(-1);
		// no idea in image.

//        clearTableData();
		loadDataToTable();

	}

}