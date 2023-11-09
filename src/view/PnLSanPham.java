package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class PnLSanPham extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textMaKH;
    private JTable table;
    private JTextField textField_1;
    private JTable table_1;
    private JTextField textField;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;

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
		                        
		                                JLabel lblDiaChi = new JLabel("Giới tính:");
		                                lblDiaChi.setBounds(11, 333, 122, 32);
		                                panel.add(lblDiaChi);
		                                lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		                                
		                                        JButton btnThemKH = new JButton("Thêm ");
		                                        btnThemKH.setBounds(34, 478, 91, 32);
		                                        panel.add(btnThemKH);
		                                        btnThemKH.setForeground(new Color(255, 255, 255));
		                                        btnThemKH.addActionListener(new ActionListener() {
		                                            public void actionPerformed(ActionEvent e) {
		                                            }
		                                        });
		                                        btnThemKH.setBackground(new Color(0, 128, 192));
		                                        
		                                                JButton btnSuaKH = new JButton("Sửa ");
		                                                btnSuaKH.setBounds(141, 479, 91, 32);
		                                                panel.add(btnSuaKH);
		                                                btnSuaKH.setForeground(new Color(255, 255, 255));
		                                                btnSuaKH.addActionListener(new ActionListener() {
		                                                    public void actionPerformed(ActionEvent e) {
		                                                    }
		                                                });
		                                                btnSuaKH.setBackground(new Color(0, 128, 192));
		                                                
		                                                        JButton btnLamMoiKH = new JButton("Làm mới");
		                                                        btnLamMoiKH.addActionListener(new ActionListener() {
		                                                        	public void actionPerformed(ActionEvent e) {
		                                                        	}
		                                                        });
		                                                        btnLamMoiKH.setBounds(246, 479, 91, 32);
		                                                        panel.add(btnLamMoiKH);
		                                                        btnLamMoiKH.setForeground(new Color(255, 255, 255));
		                                                        btnLamMoiKH.setBackground(new Color(0, 128, 192));
		                                                        
		                                                                textMaKH = new JTextField();
		                                                                textMaKH.setBounds(143, 66, 132, 28);
		                                                                panel.add(textMaKH);
		                                                                textMaKH.setColumns(10);
		                                                                                
		                                                                                JRadioButton rdbtnNam = new JRadioButton("Nam");
		                                                                                rdbtnNam.setBounds(172, 338, 67, 31);
		                                                                                panel.add(rdbtnNam);
		                                                                                rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		                                                                                
		                                                                                JRadioButton rdbtnNu = new JRadioButton("Nữ");
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
		                                                                                
		                                                                                textField = new JTextField();
		                                                                                textField.setColumns(10);
		                                                                                textField.setBounds(143, 101, 205, 28);
		                                                                                panel.add(textField);
		                                                                                
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
		                                                                                
		                                                                                textField_4 = new JTextField();
		                                                                                textField_4.setColumns(10);
		                                                                                textField_4.setBounds(143, 216, 205, 28);
		                                                                                panel.add(textField_4);
		                                                                                
		                                                                                JLabel lblSoLuong = new JLabel("Số Lượng:");
		                                                                                lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		                                                                                lblSoLuong.setBounds(13, 259, 120, 32);
		                                                                                panel.add(lblSoLuong);
		                                                                                
		                                                                                textField_5 = new JTextField();
		                                                                                textField_5.setColumns(10);
		                                                                                textField_5.setBounds(143, 258, 205, 28);
		                                                                                panel.add(textField_5);
		                                                                                
		                                                                                JLabel lblMauSac = new JLabel("Màu Sắc");
		                                                                                lblMauSac.setFont(new Font("Tahoma", Font.BOLD, 15));
		                                                                                lblMauSac.setBounds(13, 297, 120, 32);
		                                                                                panel.add(lblMauSac);
		                                                                                
		                                                                                textField_6 = new JTextField();
		                                                                                textField_6.setColumns(10);
		                                                                                textField_6.setBounds(143, 300, 205, 28);
		                                                                                panel.add(textField_6);
		                                                                                
		                                                                                JLabel lblTrangThai = new JLabel("Trạng Thái:");
		                                                                                lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		                                                                                lblTrangThai.setBounds(13, 369, 120, 32);
		                                                                                panel.add(lblTrangThai);
		                                                                                
		                                                                                textField_7 = new JTextField();
		                                                                                textField_7.setColumns(10);
		                                                                                textField_7.setBounds(143, 372, 205, 28);
		                                                                                panel.add(textField_7);
		                                                                                
		                                                                                JLabel lblNCC = new JLabel("Nhà Cung Cấp:");
		                                                                                lblNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		                                                                                lblNCC.setBounds(13, 409, 120, 32);
		                                                                                panel.add(lblNCC);
		                                                                                
		                                                                                JLabel lblHinhAnh = new JLabel("Hình Ảnh:");
		                                                                                lblHinhAnh.setFont(new Font("Tahoma", Font.BOLD, 15));
		                                                                                lblHinhAnh.setBounds(13, 448, 120, 32);
		                                                                                panel.add(lblHinhAnh);
		                                                                                
		                                                                                JComboBox comboBoxLoaiSP = new JComboBox();
		                                                                                comboBoxLoaiSP.setBounds(143, 139, 205, 26);
		                                                                                panel.add(comboBoxLoaiSP);
		                                                                                
		                                                                                JComboBox comboBoxKichThuoc = new JComboBox();
		                                                                                comboBoxKichThuoc.setBounds(143, 180, 205, 26);
		                                                                                panel.add(comboBoxKichThuoc);
		                                                                                
		                                                                                JComboBox comboBoxKichThuoc_1 = new JComboBox();
		                                                                                comboBoxKichThuoc_1.setBounds(143, 415, 205, 26);
		                                                                                panel.add(comboBoxKichThuoc_1);
		                                                                                
		                                                                                JButton btnThemKH_1 = new JButton("Tạo");
		                                                                                btnThemKH_1.setForeground(new Color(0, 0, 0));
		                                                                                btnThemKH_1.setBackground(new Color(0, 128, 192));
		                                                                                btnThemKH_1.setBounds(279, 63, 68, 32);
		                                                                                panel.add(btnThemKH_1);
		                                
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
		                                
		                                JButton btnTimKH = new JButton("Tìm");
		                                btnTimKH.setForeground(Color.WHITE);
		                                btnTimKH.setBackground(new Color(0, 128, 192));
		                                btnTimKH.setBounds(332, 33, 93, 32);
		                                panel_1.add(btnTimKH);
		                                
		                                textField_1 = new JTextField();
		                                textField_1.setColumns(10);
		                                textField_1.setBounds(149, 34, 173, 31);
		                                panel_1.add(textField_1);
		                                
		                                JScrollPane scrollPane = new JScrollPane();
		                                scrollPane.setBounds(0, 77, 755, 465);
		                                panel_1.add(scrollPane);
		                                
		                                table_1 = new JTable();
		                                table_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		                                table_1.setModel(new DefaultTableModel(
		                                	new Object[][] {
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, "", null, null, null},
		                                		{null, null, null, null, null, null, null, "", null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                		{null, null, null, null, null, null, null, null, null, null, null},
		                                	},
		                                	new String[] {
		                                		"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "K\u00EDch Th\u01B0\u1EDBc", "Gi\u00E1 Nh\u1EADp", "S\u1ED1 L\u01B0\u1EE3ng", "M\u00E0u S\u1EAFc", "Gi\u1EDBi T\u00EDnh", "Tr\u1EA1ng Th\u00E1i", "Nh\u00E0 Cung C\u1EA5p", "H\u00ECnh \u1EA2nh"
		                                	}
		                                ));
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
		                                
		                                JComboBox comboBox = new JComboBox();
		                                comboBox.setBounds(601, 33, 116, 33);
		                                panel_1.add(comboBox);
		                                
		                                        JLabel lblTitle = new JLabel("Quản Lý Sản Phẩm");
		                                        lblTitle.setBounds(795, 28, 320, 39);
		                                        add(lblTitle);
		                                        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));

	}
}
