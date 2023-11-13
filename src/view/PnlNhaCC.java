package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhaCCDAO;
import entity.NhaCC;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;

public class PnlNhaCC extends JPanel {
	private JTextField textMaNCC;
	private JTextField textTenNhaCC;
	private JTextField textDiaChi;
	private JTextField textTimNhaCC;
	private JTable table;
	private NhaCCDAO ncc_dao;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public PnlNhaCC() {
		try {
			ConnectDB.getConection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ncc_dao = new NhaCCDAO();
		
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
		textMaNCC.setEditable(false);
		
		JLabel lblQuocGia = new JLabel("Quốc gia:");
		lblQuocGia.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblQuocGia.setBounds(10, 94, 128, 32);
		add(lblQuocGia);
		
		JLabel lblTenNhaCC = new JLabel("Tên Nhà cung cấp:");
		lblTenNhaCC.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTenNhaCC.setBounds(580, 51, 176, 32);
		add(lblTenNhaCC);
		
//		JComboBox comboBox = new JComboBox();
		comboBox = new JComboBox<>(ConnectDB.getQuocGiaList().toArray(new String[0]));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 9));
		comboBox.setBounds(186, 102, 171, 22);
		add(comboBox);
		
		
		textTenNhaCC = new JTextField();
		textTenNhaCC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					textDiaChi.requestFocus();
                }
			}
		});
		textTenNhaCC.setColumns(10);
		textTenNhaCC.setBounds(766, 56, 263, 29);
		add(textTenNhaCC);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiaChi.setBounds(590, 94, 128, 32);
		add(lblDiaChi);
		
		textDiaChi = new JTextField();
		textDiaChi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					textTenNhaCC.requestFocus();
                }
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					textTimNhaCC.requestFocus();
                }
			}
		});
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
		textTimNhaCC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					textDiaChi.requestFocus();
                }
			}
		});
		textTimNhaCC.setColumns(10);
		textTimNhaCC.setBounds(235, 37, 524, 29);
		panel.add(textTimNhaCC);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(PnlNhaCC.class.getResource("/view/icon/magnifying_glass_icon.png")));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm tìm kiếm ở đây
                try {
					timNhaCC();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
		btnTim.setBackground(new Color(0, 128, 192));
		btnTim.setBounds(774, 34, 120, 32);
		panel.add(btnTim);
		// Thêm dữ liệu cho các hàng khác ở đây

		table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("STT");
        tableModel.addColumn("Mã NCC");
        tableModel.addColumn("Tên Nhà cung cấp");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Mã quốc gia");

        // Thêm dữ liệu vào bảng
//        tableModel.addRow(new Object[] {1, "NCC0001", "Levent", "0836482846", "220/12 Nguyễn Oanh - P.17 - Q.Gò Vấp - TP.HCM"});
//        tableModel.addRow(new Object[] {2, "NCC0003", "Juno", "0485685436", "12/76 Nguyễn Kiệm - P.5 - Q.Gò Vấp - TP.HCM"});

        table.setModel(tableModel);

        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setPreferredWidth(87);
        table.getColumnModel().getColumn(2).setPreferredWidth(145);
        table.getColumnModel().getColumn(3).setPreferredWidth(112);
        table.getColumnModel().getColumn(4).setPreferredWidth(252);
        
     // Thêm sự kiện cho bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lấy hàng được chọn
            	int row = table.getSelectedRow();

            	// Check if the value is null before calling toString()
            	String maNCC = (table.getValueAt(row, 1) != null) ? table.getValueAt(row, 1).toString() : "";
            	String tenNCC = (table.getValueAt(row, 2) != null) ? table.getValueAt(row, 2).toString() : "";
            	String diaChi = (table.getValueAt(row, 3) != null) ? table.getValueAt(row, 3).toString() : "";
            	String quocGia = (table.getValueAt(row, 4) != null) ? table.getValueAt(row, 4).toString() : "";

                // Hiển thị thông tin trong text fields và combobox
                textMaNCC.setText(maNCC);
                textTenNhaCC.setText(tenNCC);
                textDiaChi.setText(diaChi);
                comboBox.setSelectedItem(quocGia);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 77, 999, 294);
        panel.add(scrollPane);
		
		JButton btnThem = new JButton("Thêm mới");
		btnThem.setIcon(new ImageIcon(PnlNhaCC.class.getResource("/view/icon/boxPlusWhite_icon.png")));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm thêm mới ở đây
                try {
					themMoiNhaCC();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	}
        	});
		btnThem.setBackground(new Color(0, 128, 192));
		btnThem.setBounds(218, 148, 139, 32);
		add(btnThem);
		
		JButton btnSua = new JButton("Sửa thông tin");
		btnSua.setIcon(new ImageIcon(PnlNhaCC.class.getResource("/view/icon/mechanical-gears-.png")));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm sửa thông tin ở đây
                try {
					suaThongTinNhaCC();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	}
			});
		btnSua.setBackground(new Color(0, 128, 192));
		btnSua.setBounds(457, 149, 145, 32);
		add(btnSua);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(PnlNhaCC.class.getResource("/view/icon/refresh_icon.png")));
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm làm mới ở đây
                lamMoi();
	            }
	        });
		btnLamMoi.setBackground(new Color(0, 128, 192));
		btnLamMoi.setBounds(716, 148, 139, 32);
		add(btnLamMoi);
		
		// Gọi hàm load dữ liệu sau khi setModel cho bảng
		loadDataToTable();

	}
	 private void loadDataToTable() {
	        NhaCCDAO nccDAO = new NhaCCDAO();

	        // Lấy danh sách nhà cung cấp từ cơ sở dữ liệu
	        ArrayList<NhaCC> dsNCC = nccDAO.getAllNCC();

	        // Xóa dữ liệu cũ trong bảng
	        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	        tableModel.setRowCount(0);

	        // Thêm dữ liệu mới từ danh sách nhà cung cấp
	        for (int i = 0; i < dsNCC.size(); i++) {
	            NhaCC ncc = dsNCC.get(i);
	            tableModel.addRow(new Object[] { i + 1, ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getQuocGia() });
	        }
	    }
	 
	 private void themMoiNhaCC() {
		    try {
		        // Lấy dữ liệu từ các text fields và combobox
		        String maNCC = ncc_dao.tuPhatSinhMa();
		        String tenNCC = textTenNhaCC.getText();
		        String diaChi = textDiaChi.getText();
		        String quocGia = (String) comboBox.getSelectedItem();

		        // Kiểm tra điều kiện trước khi thêm mới
		        if (tenNCC.trim().isEmpty()) {
		            throw new Exception("Vui lòng nhập tên nhà cung cấp!");
		        }

		        // Tạo đối tượng NhaCC từ dữ liệu
		        NhaCC ncc = new NhaCC(maNCC, tenNCC, diaChi, quocGia);

		        // Gọi hàm thêm mới từ DAO
		        ncc_dao.addNhaCC(ncc);

		        // Làm mới bảng
		        loadDataToTable();

		        // Hiển thị thông báo thành công
		        JOptionPane.showMessageDialog(this, "Thêm mới Nhà cung cấp thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		    } catch (Exception ex) {
		        // Hiển thị thông báo lỗi
		        JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.INFORMATION_MESSAGE);
		    }
		}


	    private void suaThongTinNhaCC() throws Exception {
	        // Lấy dữ liệu từ các text fields và combobox
	        String maNCC = textMaNCC.getText();
	        String tenNCC = textTenNhaCC.getText();
	        String diaChi = textDiaChi.getText();
	        String quocGia = (String) comboBox.getSelectedItem();

	        // Tạo đối tượng NhaCC từ dữ liệu
	        NhaCC ncc = new NhaCC(maNCC, tenNCC, diaChi, quocGia);

	        // Gọi hàm cập nhật từ DAO
	        ncc_dao.updateNhaCC(ncc);

	        // Làm mới bảng
	        loadDataToTable();
	    }
	private void timNhaCC() throws Exception {
		    // Lấy mã NCC từ text field
		    String maNCC = textTimNhaCC.getText();

		    // Gọi hàm tìm kiếm từ DAO
		    ArrayList<NhaCC> dsNCC = ncc_dao.searchNhaCCByCode(maNCC);

		    // Xóa dữ liệu cũ trong bảng
		    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		    tableModel.setRowCount(0);

		    // Hiển thị thông tin NCC tìm được hoặc thông báo không tìm thấy
		    if (!dsNCC.isEmpty()) {
		        for (int i = 0; i < dsNCC.size(); i++) {
		            NhaCC ncc = dsNCC.get(i);
		            tableModel.addRow(new Object[] { i + 1, ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getQuocGia() });
		        }

		        // Nếu danh sách không rỗng, chỉ hiển thị thông tin của NCC đầu tiên trong danh sách
		        NhaCC ncc = dsNCC.get(0);
		        textMaNCC.setText(ncc.getMaNCC());
		        textTenNhaCC.setText(ncc.getTenNCC());
		        textDiaChi.setText(ncc.getDiaChi());
		        comboBox.setSelectedItem(ncc.getQuocGia());
		    } else {
		        // Nếu không tìm thấy, thông báo hoặc xử lý khác tùy ý
		        JOptionPane.showMessageDialog(this, "Không tìm thấy Nhà cung cấp với mã: " + maNCC, "Thông báo", JOptionPane.WARNING_MESSAGE);
		    }
		    
		}

	    private void lamMoi() {
	        // Xóa dữ liệu trong các text fields và combobox
	        textMaNCC.setText("");
	        textTenNhaCC.setText("");
	        textDiaChi.setText("");
	        comboBox.setSelectedIndex(0);
	        textTimNhaCC.setText("");
	        
	        loadDataToTable();
	    }
	    

	 
}
