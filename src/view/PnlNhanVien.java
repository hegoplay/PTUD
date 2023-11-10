package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.util.PublicCloneable;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import connectDB.ConnectDB;
import dao.NhaCCDAO;
import dao.NhanVienDAO;
import entity.NhaCC;
import entity.NhanVien;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class PnlNhanVien extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textMaNV;
    private JTextField textSDT;
    private JTextField textTenNV;
    private JTextField textDiaChi;
    private JTextField textEmail;
    private JTextField textLuong;
    private JTextField textTimNV;
    private JTable table;
    private JTable table_1;
    private NhanVienDAO nv_dao;
    private JTextField textField;
    private JRadioButton rdbtnNam, rdbtnNu, rdbtnDangLam, rdbtnDaNghi;
    private JComboBox<String> comboBox;

    /**
     * Create the panel.
     */
    public PnlNhanVien() {
    	try {
			ConnectDB.getConection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		nv_dao = new NhanVienDAO();
		
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

        JLabel lblTenNhaCC = new JLabel("Tên Nhân viên:");
        lblTenNhaCC.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTenNhaCC.setBounds(322, 51, 176, 32);
        add(lblTenNhaCC);

        comboBox = new JComboBox();
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
        btnTimNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm làm mới ở đây
            	timNhanVien();
	            }
	        });
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
//        		{"NV001", "Nguyễn Văn A", "01/01/1990", "Nam", "Quận 1, TP.HCM", "0123456789", "nva@example.com", "Nhân viên", 5000, "Đang làm", "AM Store"},
//                {"NV002", "Trần Thị B", "05/10/1995", "Nữ", "Quận 2, TP.HCM", "0987654321", "ttb@example.com", "Quản lý", 8000, "Đã nghỉ", "HM Store"},
        	},
        	new String[] {
        		"Mã NV", "Tên NV", "SĐT", "Email", "Địa chỉ", "Lương", "Chức vụ", "Giới tính", "Trạng thái", "Cửa hàng QL"
        	}
        ));
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lấy hàng được chọn
                int row = table_1.getSelectedRow();

                // Check if the value is null before calling toString()
                String maNV = (table_1.getValueAt(row, 0) != null) ? table_1.getValueAt(row, 0).toString() : "";
                String tenNV = (table_1.getValueAt(row, 1) != null) ? table_1.getValueAt(row, 1).toString() : "";
                String sdt = (table_1.getValueAt(row, 2) != null) ? table_1.getValueAt(row, 2).toString() : "";
                String email = (table_1.getValueAt(row, 3) != null) ? table_1.getValueAt(row, 3).toString() : "";
                String diaChi = (table_1.getValueAt(row, 4) != null) ? table_1.getValueAt(row, 4).toString() : "";
                String luong = (table_1.getValueAt(row, 5) != null) ? table_1.getValueAt(row, 5).toString() : "";
                String chucVu = (table_1.getValueAt(row, 6) != null) ? table_1.getValueAt(row, 6).toString() : "";
                String gioiTinh = (table_1.getValueAt(row, 7) != null) ? table_1.getValueAt(row, 7).toString() : "";
                String trangThai = (table_1.getValueAt(row, 8) != null) ? table_1.getValueAt(row, 8).toString() : "";
                String cuaHangQL = (table_1.getValueAt(row, 9) != null) ? table_1.getValueAt(row, 9).toString() : "";

                // Hiển thị thông tin trong text fields và combobox
                textMaNV.setText(maNV);
                textTenNV.setText(tenNV);
                textSDT.setText(sdt);
                textEmail.setText(email);
                textDiaChi.setText(diaChi);
                textLuong.setText(luong);
                
                // Set giới tính
                if (gioiTinh.equals("Nam")) {
                    rdbtnNam.setSelected(true);
                } else {
                    rdbtnNu.setSelected(true);
                }

                // Set trạng thái
                if (trangThai.equals("Đang làm")) {
                    rdbtnDangLam.setSelected(true);
                } else {
                    rdbtnDaNghi.setSelected(true);
                }

                // Set chức vụ
                comboBox.setSelectedItem(chucVu);
                textField.setText(cuaHangQL);
            }
        });


        JButton btnThemNV = new JButton("Thêm mới");
        btnThemNV.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/view/icon/boxPlusWhite_icon.png")));
        btnThemNV.setForeground(new Color(255, 255, 255));
        btnThemNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					themMoiNhanVien();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
            	try {
					suaThongTinNhanVien();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnSuaNV.setBackground(new Color(0, 128, 192));
        btnSuaNV.setBounds(450, 217, 146, 32);
        add(btnSuaNV);

        JButton btnLamMoiNV = new JButton("Làm mới");
        btnLamMoiNV.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/view/icon/refresh_icon.png")));
        btnLamMoiNV.setForeground(new Color(255, 255, 255));
        btnLamMoiNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm làm mới ở đây
            	clearForm();
	            }
	        });
        btnLamMoiNV.setBackground(new Color(0, 128, 192));
        btnLamMoiNV.setBounds(714, 217, 139, 32);
        add(btnLamMoiNV);

        textMaNV = new JTextField();
        textMaNV.setBounds(139, 54, 171, 32);
        add(textMaNV);
        textMaNV.setColumns(10);
        textMaNV.setEditable(false);

        JLabel lblSDT = new JLabel("Điện thoại:");
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSDT.setBounds(10, 109, 128, 32);
        add(lblSDT);

        textSDT = new JTextField();
        textSDT.setColumns(10);
        textSDT.setBounds(141, 112, 169, 32);
        add(textSDT);
		
		textTenNV = new JTextField();
		textTenNV.setColumns(10);
		textTenNV.setBounds(456, 54, 212, 32);
		add(textTenNV);
		
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
        lblGioiTinh.setBounds(485, 165, 81, 32);
        add(lblGioiTinh);

        rdbtnNam = new JRadioButton("Nam");
        rdbtnNam.setBackground(new Color(254, 250, 224));
        rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
        rdbtnNam.setBounds(580, 166, 67, 31);
        add(rdbtnNam);

        rdbtnNu = new JRadioButton("Nữ");
        rdbtnNu.setBackground(new Color(254, 250, 224));
        rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 17));
        rdbtnNu.setBounds(649, 166, 55, 31);
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
		lblaCh.setBounds(322, 109, 75, 32);
		add(lblaCh);
		
		textDiaChi = new JTextField();
		textDiaChi.setColumns(10);
		textDiaChi.setBounds(393, 112, 333, 32);
		add(textDiaChi);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(736, 109, 55, 32);
		add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(801, 112, 228, 32);
		add(textEmail);
		
		JLabel lblLuong = new JLabel("Lương:");
		lblLuong.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLuong.setBounds(277, 165, 81, 32);
		add(lblLuong);
		
		textLuong = new JTextField();
		textLuong.setColumns(10);
		textLuong.setBounds(343, 168, 132, 32);
		add(textLuong);
//		textLuong.setEditable(false);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTrangThai.setBounds(710, 165, 97, 32);
		add(lblTrangThai);
		
		rdbtnDangLam = new JRadioButton("Đang làm");
		rdbtnDangLam.setBackground(new Color(254, 250, 224));
		rdbtnDangLam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnDangLam.setBounds(824, 166, 111, 31);
		add(rdbtnDangLam);
		
		rdbtnDaNghi = new JRadioButton("Đã nghỉ");
		rdbtnDaNghi.setBackground(new Color(254, 250, 224));
		rdbtnDaNghi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnDaNghi.setBounds(937, 166, 93, 31);
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
		lblCuaHangQL.setBounds(678, 51, 128, 32);
		add(lblCuaHangQL);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(801, 51, 228, 32);
		add(textField);
		loadDataToTable();

	}
    public void loadDataToTable() {
        try {
            // Lấy dữ liệu từ cơ sở dữ liệu hoặc từ nơi khác
            ArrayList<NhanVien> danhSachNhanVien = NhanVienDAO.getAllNhanVien();

            // Tạo một DefaultTableModel để hiển thị dữ liệu trên JTable
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setRowCount(0); // Xóa tất cả dữ liệu cũ trên JTable

            // Duyệt qua danh sách và thêm từng dòng dữ liệu vào model
            for (NhanVien nv : danhSachNhanVien) {
                model.addRow(new Object[]{
                        nv.getMaNV(),
                        nv.getTen(),
                        nv.getSdt(),
                        nv.getEmail(),
                        nv.getDiaChi(),
                        nv.getLuong(),
                        (nv.isNQL() ? "Quản lý" : "Nhân viên"),
                        (nv.isNam() ? "Nam" : "Nữ"),
                        (nv.isDangLamViec() ? "Đang làm việc" : "Đã nghỉ"),
                        nv.getCuaHangQL(), // Add this line for "Cửa hàng QL"
                        // Thêm các trường khác tương ứng
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu có
        }
    }
    
//    private void clearTableData(JTable table) {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0); // Xóa tất cả các hàng trong bảng
//    }
    
    
    private void clearForm() {
        textMaNV.setText("");
        textTenNV.setText("");
        textSDT.setText("");
        textEmail.setText("");
        textDiaChi.setText("");
        textLuong.setText("");
        rdbtnNam.setSelected(true);
        rdbtnDangLam.setSelected(true);
        textField.setText("");
        textTimNV.setText("");
        
//        clearTableData();
        loadDataToTable();
        
    }
    private void timNhanVien() {
        // Lấy mã nhân viên từ text field
        String maNV = textTimNV.getText();

        // Gọi hàm tìm kiếm từ DAO
        ArrayList<NhanVien> dsNhanVien = nv_dao.findNhanVienByMa(maNV);

        // Xóa dữ liệu cũ trong bảng
        DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
        tableModel.setRowCount(0);

        // Hiển thị thông tin nhân viên tìm được hoặc thông báo không tìm thấy
        if (!dsNhanVien.isEmpty()) {
            for (int i = 0; i < dsNhanVien.size(); i++) {
                NhanVien nv = dsNhanVien.get(i);
                tableModel.addRow(new Object[] {
                    i + 1,
                    nv.getMaNV(),
                    nv.getTen(),
                    nv.getSdt(),
                    nv.getEmail(),
                    nv.getDiaChi(),
                    nv.getLuong(),
                    (nv.isNQL() ? "Quản lý" : "Nhân viên"),
                    (nv.isNam() ? "Nam" : "Nữ"),
                    (nv.isDangLamViec() ? "Đang làm việc" : "Đã nghỉ"),
                    nv.getCuaHangQL()
                    // Thêm các trường khác tương ứng
                });
            }

            // Nếu danh sách không rỗng, chỉ hiển thị thông tin của nhân viên đầu tiên trong danh sách
            NhanVien nv = dsNhanVien.get(0);
            textMaNV.setText(nv.getMaNV());
            textTenNV.setText(nv.getTen());
            textSDT.setText(nv.getSdt());
            textEmail.setText(nv.getEmail());
            textDiaChi.setText(nv.getDiaChi());
            textLuong.setText(String.valueOf(nv.getLuong()));
            textField.setText(nv.getCuaHangQL());

            // Set giới tính
            if (nv.isNam()) {
                rdbtnNam.setSelected(true);
            } else {
                rdbtnNu.setSelected(true);
            }

            // Set trạng thái
            if (nv.isDangLamViec()) {
                rdbtnDangLam.setSelected(true);
            } else {
                rdbtnDaNghi.setSelected(true);
            }

            // Set chức vụ
            comboBox.setSelectedItem(nv.isNQL() ? "Quản lý" : "Nhân viên");
        } else {
            // Nếu không tìm thấy, thông báo hoặc xử lý khác tùy ý
            JOptionPane.showMessageDialog(this, "Không tìm thấy Nhân viên với mã: " + maNV, "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void themMoiNhanVien() throws Exception {
//        // Lấy dữ liệu từ các text fields và combobox
//        String maNV = nv_dao.tuPhatSinhMa();
//        String tenNV = textTenNCC.getText();
//        String sdt = textSDT.getText();
//        String email = textEmail.getText();
//        String diaChi = textDiaChi.getText();
//        double luong = Double.parseDouble(textLuong.getText());
//        String chucVu = (String) comboBox.getSelectedItem();
//        boolean nam = rdbtnNam.isSelected();
//        boolean dangLamViec = rdbtnDangLam.isSelected();
//        String cuaHangQL = textField.getText();
//
//        // Tạo đối tượng NhanVien từ dữ liệu
//        NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, dangLamViec, nam, dangLamViec, cuaHangQL);
//
//        // Gọi hàm thêm mới từ DAO
//        nv_dao.addNhanVien(nv);
//
//        // Làm mới bảng
//        loadDataToTable();
//        clearForm();
    	try {
            // Lấy dữ liệu từ các text fields và combobox
            String maNV = nv_dao.tuPhatSinhMa();
            String tenNV = textTenNV.getText();
            String sdt = textSDT.getText();
            String email = textEmail.getText();
            String diaChi = textDiaChi.getText();
            double luong = 0;

            // Kiểm tra và chuyển đổi giá trị từ textLuong
            String luongStr = textLuong.getText();
            if (!luongStr.isEmpty()) {
                double luongValue = Double.parseDouble(luongStr);

                // Kiểm tra nếu giá trị lương lớn hơn 0
                if (luongValue > 0) {
                    luong = luongValue;
                } else {
                    throw new Exception("Vui lòng nhập số lương lớn hơn 0");
                }
            } else {
                throw new Exception("Vui lòng nhập số lương");
            }


            String chucVu = (String) comboBox.getSelectedItem();
            boolean nam = rdbtnNam.isSelected();
            boolean dangLamViec = rdbtnDangLam.isSelected();
            String cuaHangQL = textField.getText();

            // Tạo đối tượng NhanVien từ dữ liệu
            NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, dangLamViec, nam, dangLamViec, cuaHangQL);

            // Gọi hàm thêm mới từ DAO
            nv_dao.addNhanVien(nv);

            // Làm mới bảng
            loadDataToTable();
            clearForm();
        } catch (NumberFormatException ex) {
            // Xử lý nếu người dùng nhập không phải là số
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lương hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suaThongTinNhanVien() throws Exception {
        // Lấy dữ liệu từ các text fields và combobox
        String maNV = textMaNV.getText();
        String tenNV = textTenNV.getText();
        String sdt = textSDT.getText();
        String email = textEmail.getText();
        String diaChi = textDiaChi.getText();
        double luong = Double.parseDouble(textLuong.getText());
        String chucVu = (String) comboBox.getSelectedItem();
        boolean nam = rdbtnNam.isSelected();
        boolean dangLamViec = rdbtnDangLam.isSelected();
        String cuaHangQL = textField.getText();

        // Tạo đối tượng NhanVien từ dữ liệu
//        NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, chucVu, nam, dangLamViec, cuaHangQL);
        NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, diaChi, luong, dangLamViec, nam, dangLamViec, cuaHangQL);

        // Gọi hàm cập nhật từ DAO
        nv_dao.updateNhanVien(nv);

        // Làm mới bảng
        loadDataToTable();
        clearForm();
    }

    
}
