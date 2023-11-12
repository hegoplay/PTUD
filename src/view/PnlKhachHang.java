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
import javax.swing.JOptionPane;
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

import connectDB.ConnectDB;
import dao.NhaCCDAO;
import dao.KhachHangDAO;
import entity.NhaCC;
import entity.KhachHang;

public class PnlKhachHang extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textMaKH;
    private JTextField textNamSinh;
    private JTextField textTenKH;
    private JTextField textDiaChi;
    private JTextField textSDT;
    private JTable table;
    private JTextField textFTimKH;
    private JTable table_1;
    private JRadioButton rdbtnNam;
    private JRadioButton rdbtnNu;
    private KhachHangDAO kh_dao;

    /**
     * Create the panel.
     */
    public PnlKhachHang() {
    	try {
			ConnectDB.getConection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		kh_dao = new KhachHangDAO();
        setBackground(MainFrame.clrTheme);
        setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 254, 224));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(737, 89, 383, 542);
		add(panel);
		        panel.setLayout(null);
		                
		                        JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
		                        lblMaKH.setBounds(10, 86, 147, 32);
		                        panel.add(lblMaKH);
		                        lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		                        
		                                JLabel lblDiaChi = new JLabel("Giới tính:");
		                                lblDiaChi.setBounds(10, 303, 81, 32);
		                                panel.add(lblDiaChi);
		                                lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
		                                
		                                        JButton btnThemKH = new JButton("Thêm ");
		                                        btnThemKH.setBounds(10, 446, 91, 32);
		                                        panel.add(btnThemKH);
		                                        btnThemKH.setForeground(new Color(255, 255, 255));
		                                        btnThemKH.addActionListener(new ActionListener() {
		                                            public void actionPerformed(ActionEvent e) {
		                                            	try {
		                                					themMoiKhachHang();
		                                				} catch (Exception e1) {
		                                					// TODO Auto-generated catch block
		                                					e1.printStackTrace();
		                                				}
		                                            }
		                                        });
		                                        btnThemKH.setBackground(new Color(0, 128, 192));
		                                        
		                                                JButton btnSuaKH = new JButton("Sửa ");
		                                                btnSuaKH.setBounds(144, 446, 91, 32);
		                                                panel.add(btnSuaKH);
		                                                btnSuaKH.setForeground(new Color(255, 255, 255));
		                                                btnSuaKH.addActionListener(new ActionListener() {
		                                                    public void actionPerformed(ActionEvent e) {
		                                                    	try {
		                                        					suaThongTinKhachHang();
		                                        				} catch (Exception e1) {
		                                        					// TODO Auto-generated catch block
		                                        					e1.printStackTrace();
		                                        				}
		                                                    }
		                                                });
		                                                btnSuaKH.setBackground(new Color(0, 128, 192));
		                                                
		                                                        JButton btnLamMoiKH = new JButton("Làm mới");
		                                                        btnLamMoiKH.setBounds(279, 446, 91, 32);
		                                                        panel.add(btnLamMoiKH);
		                                                        btnLamMoiKH.setForeground(new Color(255, 255, 255));
		                                                        btnLamMoiKH.setBackground(new Color(0, 128, 192));
		                                                        
		                                                                textMaKH = new JTextField();
		                                                                textMaKH.setBounds(184, 88, 186, 36);
		                                                                panel.add(textMaKH);
		                                                                textMaKH.setColumns(10);
		                                                                
		                                                                        JLabel lblSDT = new JLabel("Số Điện Thoại:");
		                                                                        lblSDT.setBounds(10, 258, 128, 32);
		                                                                        panel.add(lblSDT);
		                                                                        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		                                                                        
		                                                                                textNamSinh = new JTextField();
		                                                                                textNamSinh.setBounds(184, 220, 186, 32);
		                                                                                panel.add(textNamSinh);
		                                                                                textNamSinh.setColumns(10);
		                                                                                
		                                                                                textTenKH = new JTextField();
		                                                                                textTenKH.setBounds(184, 132, 186, 32);
		                                                                                panel.add(textTenKH);
		                                                                                textTenKH.setColumns(10);
		                                                                                
		                                                                                JLabel lblTenKH = new JLabel("Tên Khách Hàng");
		                                                                                lblTenKH.setBounds(10, 132, 147, 32);
		                                                                                panel.add(lblTenKH);
		                                                                                lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		                                                                                
		                                                                                rdbtnNam = new JRadioButton("Nam");
		                                                                                rdbtnNam.setBounds(194, 304, 67, 31);
		                                                                                panel.add(rdbtnNam);
		                                                                                rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		                                                                                
		                                                                                rdbtnNu = new JRadioButton("Nữ");
		                                                                                rdbtnNu.setBounds(297, 304, 55, 31);
		                                                                                panel.add(rdbtnNu);
		                                                                                rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		                                                                                
		                                                                                JLabel lblaCh = new JLabel("Địa chỉ:");
		                                                                                lblaCh.setBounds(10, 174, 147, 32);
		                                                                                panel.add(lblaCh);
		                                                                                lblaCh.setFont(new Font("Tahoma", Font.BOLD, 17));
		                                                                                
		                                                                                textDiaChi = new JTextField();
		                                                                                textDiaChi.setBounds(184, 178, 186, 32);
		                                                                                panel.add(textDiaChi);
		                                                                                textDiaChi.setColumns(10);
		                                                                                
		                                                                                textSDT = new JTextField();
		                                                                                textSDT.setBounds(184, 262, 186, 32);
		                                                                                panel.add(textSDT);
		                                                                                textSDT.setColumns(10);
		                                                                                
		                                                                                JLabel lblNamSinh = new JLabel("Năm Sinh:");
		                                                                                lblNamSinh.setBounds(10, 216, 138, 32);
		                                                                                panel.add(lblNamSinh);
		                                                                                lblNamSinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		                                                                                
		                                                                                JLabel lblNewLabel = new JLabel("Khách Hàng");
		                                                                                lblNewLabel.setBounds(101, 10, 171, 39);
		                                                                                panel.add(lblNewLabel);
		                                                                                lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		                                
		                                JPanel panel_1 = new JPanel();
		                                panel_1.setBounds(10, 89, 727, 542);
		                                add(panel_1);
		                                panel_1.setLayout(null);
		                                panel_1.setForeground(Color.WHITE);
		                                panel_1.setBackground(new Color(64, 128, 128));
		                                
		                                JLabel lblDanhSch = new JLabel(" Danh Sách Khách Hàng");
		                                lblDanhSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		                                lblDanhSch.setBounds(10, 11, 190, 19);
		                                panel_1.add(lblDanhSch);
		                                
		                                JLabel lblTimKH = new JLabel("Tìm kiếm theo mã:");
		                                lblTimKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		                                lblTimKH.setBounds(20, 40, 164, 25);
		                                panel_1.add(lblTimKH);
		                                
		                                JButton btnTimKH = new JButton("Tìm");
		                                btnTimKH.setForeground(Color.WHITE);
		                                btnTimKH.setBackground(new Color(0, 128, 192));
		                                btnTimKH.setBounds(614, 33, 103, 32);
		                                panel_1.add(btnTimKH);
		                                btnTimKH.addActionListener(new ActionListener() {
		                                    public void actionPerformed(ActionEvent e) {
		                                        // Gọi hàm làm mới ở đây
		                                    	timKhachHang();
		                        	            }
		                        	        });
		                                
		                                textFTimKH = new JTextField();
		                                textFTimKH.setColumns(10);
		                                textFTimKH.setBounds(194, 34, 410, 32);
		                                panel_1.add(textFTimKH);
		                                
		                                JScrollPane scrollPane = new JScrollPane();
		                                scrollPane.setBounds(10, 77, 707, 455);
		                                panel_1.add(scrollPane);
		                                
		                                table_1 = new JTable();
		                                table_1.setModel(new DefaultTableModel(
		                                	new Object[][] {
		                                		{null, null, null, null, null, null},
		                                		{"KH001", "Nguy\u1EC5n Huy ", " 81/2  L\u00EA \u0110\u1EE9c Th\u1ECD", "2001", "0864745634", "Nam"},
		                                		{"KH02", "L\u00EA Ng\u1ECDc", "1 B\u1EA1ch \u0110\u1EB1ng", "2002", "0986573569", "N\u1EEF "},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                		{null, null, null, null, null, null},
		                                	},
		                                	new String[] {
		                                		"M\u00E3 Kh\u00E1ch H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng", "\u0110\u1ECBa ch\u1EC9", "N\u0103m Sinh", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "Gi\u1EDBi T\u00EDnh"
		                                	}
		                                ));
		                                table_1.getColumnModel().getColumn(2).setPreferredWidth(94);
		                                scrollPane.setViewportView(table_1);
		                                
		                                        JLabel lblTitle = new JLabel("Quản Lý Khách Hàng");
		                                        lblTitle.setBounds(765, 27, 344, 39);
		                                        add(lblTitle);
		                                        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		                                        
		                                        loadDataToTable();

	}

	private void loadDataToTable() {
		try {
            // Lấy dữ liệu từ cơ sở dữ liệu hoặc từ nơi khác
            ArrayList<KhachHang> danhSachKhachHang = KhachHangDAO.getAllKhachHang();

            // Tạo một DefaultTableModel để hiển thị dữ liệu trên JTable
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setRowCount(0); // Xóa tất cả dữ liệu cũ trên JTable

            // Duyệt qua danh sách và thêm từng dòng dữ liệu vào model
            for (KhachHang kh : danhSachKhachHang) {
                model.addRow(new Object[]{
                        kh.getMaKH(),
                        kh.getTenKH(),
                        kh.getDiaChi(),
                        kh.getSdt(),
                        kh.getNamSinh(),
                        (kh.isGioiTinh() ? "Nam" : "Nữ"),
                        // Thêm các trường khác tương ứng
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu có
        }
		
	}
	
	private void clearForm() {
        textMaKH.setText("");
        textTenKH.setText("");
        textDiaChi.setText("");
        textSDT.setText("");
        textNamSinh.setText("");
        rdbtnNam.setSelected(true);
        
//        clearTableData();
        loadDataToTable();
        
    }
	
	private void timKhachHang() {
        // Lấy mã nhân viên từ text field
        String maKH = textFTimKH.getText();

        // Gọi hàm tìm kiếm từ DAO
        ArrayList<KhachHang> dsKhachHang = kh_dao.findKhachHangByMa(maKH);

        // Xóa dữ liệu cũ trong bảng
        DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
        tableModel.setRowCount(0);

        // Hiển thị thông tin nhân viên tìm được hoặc thông báo không tìm thấy
        if (!dsKhachHang.isEmpty()) {
            for (int i = 0; i < dsKhachHang.size(); i++) {
                KhachHang kh = dsKhachHang.get(i);
                tableModel.addRow(new Object[] {
                    i + 1,
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getDiaChi(),
                    kh.getSdt(),
                    kh.getNamSinh(),
                    (kh.isGioiTinh() ? "Nam" : "Nữ"),
                    // Thêm các trường khác tương ứng
                });
            }

            // Nếu danh sách không rỗng, chỉ hiển thị thông tin của nhân viên đầu tiên trong danh sách
            KhachHang kh = dsKhachHang.get(0);
            textMaKH.setText(kh.getMaKH());
            textTenKH.setText(kh.getTenKH());
            textDiaChi.setText(kh.getDiaChi());
            textSDT.setText(kh.getSdt());
            textNamSinh.setText(String.valueOf(kh.getNamSinh()));

            // Set giới tính
            if (kh.isGioiTinh()) {
                rdbtnNam.setSelected(true);
            } else {
                rdbtnNu.setSelected(true);
            }

            

        } else {
            // Nếu không tìm thấy, thông báo hoặc xử lý khác tùy ý
            JOptionPane.showMessageDialog(this, "Không tìm thấy Khach Hang với mã: " + maKH, "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
	
	private void themMoiKhachHang() throws Exception {
		
		
//      //Lấy dữ liệu từ các text fields và combobox
//      String maKH = kh_dao.tuPhatSinhMa();
//      String tenNV = textTenKH.getText();
//      String diaChi = textDiaChi.getText();
//      String sdt = textSDT.getText();
//      int namSinh = 2000;
//      boolean nam = rdbtnNam.isSelected();
//
//      // Tạo đối tượng KhachHang từ dữ liệu
//      KhachHang kh = new KhachHang(maKH, tenNV, diaChi, sdt, namSinh, nam);
//
//      // Gọi hàm thêm mới từ DAO
//      kh_dao.addKhachHang(kh);
//
//      // Làm mới bảng
//      loadDataToTable();
//      clearForm();
  	try {
          // Lấy dữ liệu từ các text fields và combobox
  		String maKH = kh_dao.tuPhatSinhMa();
      String tenNV = textTenKH.getText();
      String diaChi = textDiaChi.getText();
      String sdt = textSDT.getText();
      int namSinh = 0;
      boolean nam = rdbtnNam.isSelected();

//          // Kiểm tra và chuyển đổi giá trị từ textNamSInh
//          String namSinhStr = textNamSinh.getText();
//          if (!namSinhStr.isEmpty()) {
//              int namSinhValue = i.parseDouble(namSinhStr);
//
//              // Kiểm tra nếu giá trị nam sinh lớn hơn 0
//              if (namSinhValue > 0) {
//                  namSinh = namSinhValue;
//              } else {
//                  throw new Exception("Vui lòng nhập số nam sinhlớn hơn 0");
//              }
//          } else {
//              throw new Exception("Vui lòng nhập số lnam sinh");
//          }



          // Tạo đối tượng NhanVien từ dữ liệu
          KhachHang kh = new KhachHang(maKH, tenNV, diaChi, sdt, namSinh, nam) ;

          // Gọi hàm thêm mới từ DAO
          kh_dao.addKhachHang(kh);

          // Làm mới bảng
          loadDataToTable();
          clearForm();
      } catch (NumberFormatException ex) {
          // Xử lý nếu người dùng nhập không phải là số
          JOptionPane.showMessageDialog(this, "Vui lòng nhập số lương hợp lệ", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
      } catch (Exception e) {
          JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.INFORMATION_MESSAGE);
      }
  }
    private void suaThongTinKhachHang() throws Exception {
        // Lấy dữ liệu từ các text fields và combobox
    String maKH = kh_dao.tuPhatSinhMa();
    String tenNV = textTenKH.getText();
    String diaChi = textDiaChi.getText();
    String sdt = textSDT.getText();
    int namSinh = 2000;
    boolean nam = rdbtnNam.isSelected();

        // Tạo đối tượng Khach Hang từ dữ liệu
        KhachHang kh = new KhachHang(maKH, tenNV, diaChi, sdt, namSinh, nam);

        // Gọi hàm cập nhật từ DAO
        kh_dao.updateKhachHang(kh);

        // Làm mới bảng
        loadDataToTable();
        clearForm();
    }
}
