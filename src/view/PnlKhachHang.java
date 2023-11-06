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

public class PnlKhachHang extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textMaKH;
    private JTextField textNamSinh;
    private JTextField textTenKH;
    private JTextField textDiaChi;
    private JTextField textSDT;
    private JTable table;
    private JTextField textField;

    /**
     * Create the panel.
     */
    public PnlKhachHang() {
        setBackground(MainFrame.clrTheme);
        setLayout(null);

        JLabel lblTitle = new JLabel("Quản Lý Khách Hàng");
        lblTitle.setBounds(760, 11, 344, 39);
        add(lblTitle);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));

        JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
        lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblMaKH.setBounds(732, 170, 147, 32);
        add(lblMaKH);

        JLabel lblDiaChi = new JLabel("Giới tính:");
        lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblDiaChi.setBounds(732, 387, 81, 32);
        add(lblDiaChi);

        JButton btnThemKH = new JButton("Thêm ");
        btnThemKH.setForeground(new Color(255, 255, 255));
        btnThemKH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnThemKH.setBackground(new Color(0, 128, 192));
        btnThemKH.setBounds(732, 579, 91, 32);
        add(btnThemKH);

        JButton btnSuaKH = new JButton("Sửa ");
        btnSuaKH.setForeground(new Color(255, 255, 255));
        btnSuaKH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSuaKH.setBackground(new Color(0, 128, 192));
        btnSuaKH.setBounds(866, 579, 91, 32);
        add(btnSuaKH);

        JButton btnLamMoiKH = new JButton("Làm mới");
        btnLamMoiKH.setForeground(new Color(255, 255, 255));
        btnLamMoiKH.setBackground(new Color(0, 128, 192));
        btnLamMoiKH.setBounds(1001, 579, 91, 32);
        add(btnLamMoiKH);

        textMaKH = new JTextField();
        textMaKH.setBounds(906, 172, 186, 36);
        add(textMaKH);
        textMaKH.setColumns(10);

        JLabel lblSDT = new JLabel("Số Điện Thoại:");
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSDT.setBounds(732, 342, 128, 32);
        add(lblSDT);

        textNamSinh = new JTextField();
        textNamSinh.setColumns(10);
        textNamSinh.setBounds(906, 304, 186, 32);
        add(textNamSinh);
		
		textTenKH = new JTextField();
		textTenKH.setColumns(10);
		textTenKH.setBounds(906, 216, 186, 32);
		add(textTenKH);
		
		JLabel lblTenKH = new JLabel("Tên Khách Hàng");
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTenKH.setBounds(732, 216, 147, 32);
		add(lblTenKH);
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnNam.setBounds(916, 388, 67, 31);
		add(rdbtnNam);
		
		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnNu.setBounds(1019, 388, 55, 31);
		add(rdbtnNu);
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblaCh.setBounds(732, 258, 147, 32);
		add(lblaCh);
		
		textDiaChi = new JTextField();
		textDiaChi.setColumns(10);
		textDiaChi.setBounds(906, 262, 186, 32);
		add(textDiaChi);
		
		textSDT = new JTextField();
		textSDT.setColumns(10);
		textSDT.setBounds(906, 346, 186, 32);
		add(textSDT);
		
		JLabel lblNamSinh = new JLabel("Năm Sinh:");
		lblNamSinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNamSinh.setBounds(732, 300, 138, 32);
		add(lblNamSinh);
		
		JLabel lblNewLabel = new JLabel("Khách Hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(823, 94, 171, 39);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(52, 78, 65));
		panel.setBounds(0, 0, 713, 641);
		add(panel);
		        panel.setLayout(null);
		        
		        JLabel lblTimKiem = new JLabel("Tìm Theo Mã:");
		        lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 17));
		        lblTimKiem.setBounds(27, 67, 147, 32);
		        panel.add(lblTimKiem);
		        
		        textField = new JTextField();
		        textField.setColumns(10);
		        textField.setBounds(184, 67, 186, 36);
		        panel.add(textField);
		        
		                JButton btnTimKH = new JButton("Tìm ");
		                btnTimKH.setBounds(397, 71, 91, 32);
		                panel.add(btnTimKH);
		                btnTimKH.setForeground(Color.WHITE);
		                btnTimKH.setBackground(new Color(0, 128, 192));

	}
}
