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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class PnlNhanVien extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textMaNV;
    private JTextField textSDT;
    private JTextField textTenNCC;
    private JTextField textDiaChi;
    private JTextField textEmail;
    private JTextField textLuong;
    private JTextField textTimNV;
    private JTable table;
    private JTable table_1;

    /**
     * Create the panel.
     */
    public PnlNhanVien() {
        setBackground(MainFrame.clrTheme);
        setLayout(null);

        JLabel lblTitle = new JLabel("NHÂN VIÊN");
        lblTitle.setBounds(420, 11, 248, 39);
        add(lblTitle);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));

        JLabel lblMaNV = new JLabel("Mã Nhân viên:");
        lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblMaNV.setBounds(10, 51, 128, 32);
        add(lblMaNV);

        JLabel lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblChucVu.setBounds(10, 165, 128, 32);
        add(lblChucVu);

        JLabel lblTenNhaCC = new JLabel("Tên Nhà cung cấp:");
        lblTenNhaCC.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTenNhaCC.setBounds(322, 51, 176, 32);
        add(lblTenNhaCC);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Quản lý"}));
        comboBox.setBounds(96, 170, 171, 22);
        add(comboBox);

        JPanel panel = new JPanel();
        panel.setForeground(new Color(255, 255, 255));
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(10, 260, 1019, 314);
        add(panel);
        panel.setLayout(null);

        JLabel lblDSNV = new JLabel("Danh sách nhân viên");
        lblDSNV.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblDSNV.setBounds(10, 11, 190, 19);
        panel.add(lblDSNV);

        JLabel lblTimNV = new JLabel("Tìm kiếm theo mã:");
        lblTimNV.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTimNV.setBounds(61, 41, 164, 25);
        panel.add(lblTimNV);

        JButton btnTimNV = new JButton("Tìm");
        btnTimNV.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/view/icon/magnifying_glass_icon.png")));
        btnTimNV.setForeground(new Color(255, 255, 255));
        btnTimNV.setBackground(new Color(0, 128, 192));
        btnTimNV.setBounds(774, 34, 103, 32);
        panel.add(btnTimNV);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(992, 77, 17, 226);
        panel.add(scrollBar);

        textTimNV = new JTextField();
        textTimNV.setColumns(10);
        textTimNV.setBounds(235, 34, 529, 32);
        panel.add(textTimNV);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 77, 982, 226);
        panel.add(scrollPane);
        
        table_1 = new JTable();
        scrollPane.setViewportView(table_1);
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"NV001", "Nguyễn Văn A", "01/01/1990", "Nam", "Quận 1, TP.HCM", "0123456789", "nva@example.com", "Nhân viên", 5000, "Đang làm", "AM Store"},
                {"NV002", "Trần Thị B", "05/10/1995", "Nữ", "Quận 2, TP.HCM", "0987654321", "ttb@example.com", "Quản lý", 8000, "Đã nghỉ", "HM Store"},
        	},
        	new String[] {
        		"Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Email", "Chức vụ", "Lương", "Trạng thái", "Cửa hàng QL"
        	}
        ));

        JButton btnThemNV = new JButton("Thêm mới");
        btnThemNV.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/view/icon/boxPlusWhite_icon.png")));
        btnThemNV.setForeground(new Color(255, 255, 255));
        btnThemNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnThemNV.setBackground(new Color(0, 128, 192));
        btnThemNV.setBounds(213, 217, 139, 32);
        add(btnThemNV);

        JButton btnSuaNV = new JButton("Sửa thông tin");
        btnSuaNV.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/view/icon/mechanical-gears-.png")));
        btnSuaNV.setForeground(new Color(255, 255, 255));
        btnSuaNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSuaNV.setBackground(new Color(0, 128, 192));
        btnSuaNV.setBounds(450, 217, 146, 32);
        add(btnSuaNV);

        JButton btnLamMoiNV = new JButton("Làm mới");
        btnLamMoiNV.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/view/icon/refresh_icon.png")));
        btnLamMoiNV.setForeground(new Color(255, 255, 255));
        btnLamMoiNV.setBackground(new Color(0, 128, 192));
        btnLamMoiNV.setBounds(714, 217, 139, 32);
        add(btnLamMoiNV);

        textMaNV = new JTextField();
        textMaNV.setBounds(139, 54, 171, 32);
        add(textMaNV);
        textMaNV.setColumns(10);

        JLabel lblSDT = new JLabel("Điện thoại:");
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSDT.setBounds(10, 109, 128, 32);
        add(lblSDT);

        textSDT = new JTextField();
        textSDT.setColumns(10);
        textSDT.setBounds(141, 112, 169, 32);
        add(textSDT);
		
		textTenNCC = new JTextField();
		textTenNCC.setColumns(10);
		textTenNCC.setBounds(503, 54, 212, 32);
		add(textTenNCC);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNgySinh.setBounds(740, 51, 93, 32);
		add(lblNgySinh);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(853, 51, 176, 32);
		add(dateChooser);
		
//		JLabel lblGioiTinh = new JLabel("Giới tính:");
//        lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
//        lblGioiTinh.setBounds(320, 109, 81, 32);
//        add(lblGioiTinh);
//        
//		JRadioButton rdbtnNam = new JRadioButton("Nam");
//		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNam.setBounds(407, 110, 67, 31);
//		add(rdbtnNam);
//		
//		JRadioButton rdbtnNu = new JRadioButton("Nữ");
//		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		rdbtnNu.setBounds(484, 110, 55, 31);
//		add(rdbtnNu);
		JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblGioiTinh.setBounds(322, 109, 81, 32);
        add(lblGioiTinh);

        JRadioButton rdbtnNam = new JRadioButton("Nam");
        rdbtnNam.setBackground(new Color(254, 250, 224));
        rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
        rdbtnNam.setBounds(408, 110, 67, 31);
        add(rdbtnNam);

        JRadioButton rdbtnNu = new JRadioButton("Nữ");
        rdbtnNu.setBackground(new Color(254, 250, 224));
        rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 17));
        rdbtnNu.setBounds(482, 110, 55, 31);
        add(rdbtnNu);

        // Nhóm các JRadioButton lại với nhau
        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(rdbtnNam);
        groupGioiTinh.add(rdbtnNu);

        // Đặt "Nam" là lựa chọn mặc định
        rdbtnNam.setSelected(true);

        setVisible(true);
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblaCh.setBounds(557, 109, 75, 32);
		add(lblaCh);
		
		textDiaChi = new JTextField();
		textDiaChi.setColumns(10);
		textDiaChi.setBounds(627, 112, 155, 32);
		add(textDiaChi);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(790, 109, 55, 32);
		add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(855, 112, 174, 32);
		add(textEmail);
		
		JLabel lblLuong = new JLabel("Lương:");
		lblLuong.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLuong.setBounds(277, 165, 81, 32);
		add(lblLuong);
		
		textLuong = new JTextField();
		textLuong.setColumns(10);
		textLuong.setBounds(343, 168, 132, 32);
		add(textLuong);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTrangThai.setBounds(484, 165, 97, 32);
		add(lblTrangThai);
		
		JRadioButton rdbtnDangLam = new JRadioButton("Đang làm");
		rdbtnDangLam.setBackground(new Color(254, 250, 224));
		rdbtnDangLam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnDangLam.setBounds(587, 166, 109, 31);
		add(rdbtnDangLam);
		
		JRadioButton rdbtnDaNghi = new JRadioButton("Đã nghỉ");
		rdbtnDaNghi.setBackground(new Color(254, 250, 224));
		rdbtnDaNghi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnDaNghi.setBounds(705, 166, 93, 31);
		add(rdbtnDaNghi);
		
		 // Nhóm các JRadioButton lại với nhau
        ButtonGroup groupTrangThai = new ButtonGroup();
        groupTrangThai.add(rdbtnDangLam);
        groupTrangThai.add(rdbtnDaNghi);

        // Đặt "Nam" là lựa chọn mặc định
        rdbtnDangLam.setSelected(true);

        setVisible(true);
		
		JLabel lblCuaHangQL = new JLabel("Cửa hàng QL:");
		lblCuaHangQL.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCuaHangQL.setBounds(804, 165, 128, 32);
		add(lblCuaHangQL);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"AM Store", "HM Store"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_1.setBounds(931, 170, 97, 22);
		add(comboBox_1);

	}
}
