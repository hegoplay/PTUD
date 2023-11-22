package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import dao.CthdDAO;
import dao.HoaDonDAO;
//import dao.HoaDonDAO1;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PnlTimHD extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtKH;
	private JTextField txtMaHD ;
	private JTextField txtNV;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private DefaultTableModel model, model_1;
	private JTable table, table_1;
	private JButton btnLamMoi;
	private HoaDonDAO hoaDon;
	private CthdDAO cthd;
	private NhanVienDAO nhanVien;
	private KhachHangDAO khachHang;
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public PnlTimHD() throws Exception {
		setBackground(MainFrame.clrTheme);
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setLayout(new GridLayout(2,3,20,10));
		pnlInfo.setBackground(MainFrame.clrTheme);
		pnlInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20));
		add(pnlInfo, BorderLayout.NORTH);
		
		
		JPanel pnlMaHD = new JPanel();
		pnlMaHD.setLayout(new BorderLayout(15,0));
		pnlMaHD.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlMaHD);
		
		JLabel lblMaHD = new JLabel("Tìm theo mã HĐ:");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlMaHD.add(lblMaHD,BorderLayout.WEST);
		
		txtMaHD = new JTextField();
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(159, 14, 126, 25);
		pnlMaHD.add(txtMaHD,BorderLayout.CENTER);
		
		JPanel pnlNV = new JPanel();
		pnlNV.setLayout(new BorderLayout(15,0));
		pnlNV.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlNV);
		
		JLabel lblNV = new JLabel("Tìm theo nhân viên:");
		lblNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNV.setBounds(307, 20, 150, 19);
		pnlNV.add(lblNV,BorderLayout.WEST);
		
		txtNV = new JTextField();
		txtNV.setColumns(10);
		txtNV.setBounds(465, 14, 184, 25);
		pnlNV.add(txtNV, BorderLayout.CENTER);

//Ngày lập hóa đơn	
		
		JPanel pnlNgayL = new JPanel();
		pnlNgayL.setLayout(new BorderLayout(15,0));
		pnlNgayL.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlNgayL);
		
		JLabel lblDate = new JLabel("Tìm theo ngày lập hóa đơn:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlNgayL.add(lblDate, BorderLayout.WEST);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(238, 57, 172, 25);
		pnlNgayL.add(dateChooser,BorderLayout.CENTER);
		
		
		JPanel pnlKH = new JPanel();
		pnlKH.setLayout(new BorderLayout(15,0));
		pnlKH.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlKH);
		
		JLabel lblTmTheoKhch = new JLabel("Tìm theo khách hàng:");
		lblTmTheoKhch.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlKH.add(lblTmTheoKhch, BorderLayout.WEST);
		
		txtKH = new JTextField();
		txtKH.setColumns(10);
		pnlKH.add(txtKH, BorderLayout.CENTER);
		
		JPanel pnlTT = new JPanel();
		pnlTT.setLayout(new BorderLayout(15, 0));
		pnlTT.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlTT);
		
		JLabel lblTmTheoTng = new JLabel("Tìm theo tổng tiền:");
		lblTmTheoTng.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlTT.add(lblTmTheoTng, BorderLayout.WEST);
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0 - 500.000", "500.000 - 3.000.000", "3.000.000 - 5.000.000", "5.000.000 - 10.000.000", "10.000.000 - 50.000.000",  "50.000.000 - 100.000.000"}));
		pnlTT.add(comboBox, BorderLayout.CENTER);
		
		JPanel pnlLM= new JPanel();
		pnlLM.setLayout(new BorderLayout());
		pnlLM.setBackground(MainFrame.clrTheme);
		pnlLM.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 90));
		pnlInfo.add(pnlLM);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(new Color(69, 129, 142));
		pnlLM.add(btnLamMoi, BorderLayout.CENTER);
		
		
		

		JPanel pnlMainContent = new JPanel();
		pnlMainContent.setLayout(new BoxLayout(pnlMainContent, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout với trục Y
		pnlMainContent.setBackground(MainFrame.clrTheme);
		add(pnlMainContent, BorderLayout.CENTER);
		
//Chi tiết hóa đơn		
		JPanel pnlCTHD = new JPanel();
		pnlCTHD.setBackground(new Color(201, 228, 228));
		pnlCTHD.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCTHD.setLayout(new BorderLayout(0,5));
		
		pnlMainContent.add(pnlCTHD);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Tên sản phẩm");
		model.addColumn("Đơn giá (VNĐ)");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền (VNĐ)");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlCTHD.add(scrollPane, BorderLayout.CENTER);

		
		JPanel pnlDSHD = new JPanel();
		pnlDSHD.setBackground(new Color(201, 228, 228));
		pnlDSHD.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDSHD.setLayout(new BorderLayout());
		pnlMainContent.add(pnlDSHD);
		
		model_1 = new DefaultTableModel();
		table_1 = new JTable(model_1);
		table_1.setBorder(new LineBorder(new Color(240, 255, 240), 0, true));
		model_1.addColumn("STT");
		model_1.addColumn("Mã HĐ");
		model_1.addColumn("Tên KH");
		model_1.addColumn("Tên NV");
		model_1.addColumn("Ngày lập HĐ");
		model_1.addColumn("Tổng cộng");
		model_1.addColumn("Khuyến mãi");
		model_1.addColumn("Tổng tiền");
		model_1.addColumn("Ghi chú");
		JScrollPane scrollPane_1 = new JScrollPane(table_1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlDSHD.add(scrollPane_1, BorderLayout.CENTER);

        ArrayList<HoaDon> dsHoaDon = HoaDonDAO.getAllHoaDon();
        updateDSHDTable(dsHoaDon);

        
        
    	table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    	    @Override
    	    public void valueChanged(ListSelectionEvent e) {
    	        if (!e.getValueIsAdjusting()) {
    	            int selectedRow = table_1.getSelectedRow();
    	            if (selectedRow != -1) {
    	                String maHD = (String) table_1.getValueAt(selectedRow, 1); 
    	                try {
    	                    showChiTietHoaDon(maHD);
    	                } catch (Exception ex) {
    	                    ex.printStackTrace();
    	                }
    	            }
    	        }
    	    }


    	});
        dateChooser.setDate(null);
        
        btnLamMoi.addActionListener(this);
 //Xử lý txtMaHD 
		txtMaHD.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				findData();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				findData();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				findData();

			}

			private void findData() {
				String maNV = txtMaHD.getText().trim();
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(maNV, 1);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				table_1.setRowSorter(sorter);
			}
		});
        
//Xử lý txtMaKH	
        txtKH.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				findData();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				findData();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				findData();
			}

			private void findData() {
				String maKH = txtKH.getText().trim();
//				khachHang = new KhachHangDAO();
				String tenKH = khachHang.getKhachHang(maKH).getTenKH();
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(tenKH, 2);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				table_1.setRowSorter(sorter);
				
			}
		});
// Xử lí txtNV
		txtNV.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				findData();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				findData();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			private void findData() {
				String maNV = txtNV.getText().trim();
//				nhanVien = new NhanVienDAO();
//				String tenNV = nhanVien.getTenNVByMaNV(maNV);
				NhanVien nvien = nhanVien.getNhanVien(maNV);
				String tenNV= nvien.getTen();
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				// Tạo một bộ lọc để lấy các dòng có giá trị maNV trùng với text
				RowFilter<Object, Object> filter = RowFilter.regexFilter(maNV, 3);

				// Tạo một sorter để sắp xếp lại các dòng
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				sorter.setRowFilter(filter);

				// Đặt sorter cho bảng
				table_1.setRowSorter(sorter);
			}
		});   
		
//// Xử lý lọc bằng ngày tháng:
	
	dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    @Override
	    public void propertyChange(PropertyChangeEvent evt) {
	        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu ngày được chọn thay đổi
	        	java.util.Date date = dateChooser.getDate();
	        	Date selectedDate = new Date((long) date.getTime()); 
	        	if(date!=null) {
		            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate); // Định dạng lại
		            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

		            RowFilter<Object, Object> filter = RowFilter.regexFilter(formattedDate, 4);
		            sorter.setRowFilter(filter);

		           
		            table_1.setRowSorter(sorter);
	        	}
	        }
	    }
	});


//Xử lí combobox tổng tiền        
//        comboBox.setSelectedIndex(0);
    	comboBox.setSelectedItem("");
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRange = (String) comboBox.getSelectedItem();

                if (!selectedRange.isEmpty()) {
                    // Tách giá trị 
                    String[] range = selectedRange.split("-");
                    
                    // Kiểm tra lỗi định dạng
                    if (range.length == 2) {
                        try {
                            double min = Double.parseDouble(range[0].trim().replace(".", "").replace(" VNĐ", ""));
                            double max = Double.parseDouble(range[1].trim().replace(".", "").replace(" VNĐ", ""));

                            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
                            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

                            RowFilter<Object, Object> customFilter = new RowFilter<Object, Object>() {
                                @Override
                                public boolean include(Entry<? extends Object, ? extends Object> entry) {
                                    String rowValue = (String) entry.getValue(7); 
                                    double rowDoubleValue = Double.parseDouble(rowValue.replace(",", "").replace(" VNĐ", ""));
                                    return rowDoubleValue >= min && rowDoubleValue <= max;
                                }
                            };

                            sorter.setRowFilter(customFilter);
                            table_1.setRowSorter(sorter);
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
	}
	
// load danh sách hóa đơn lên bảng hóa đơn	
	private void updateDSHDTable(ArrayList<HoaDon> dsHoaDon) throws Exception {
	    DefaultTableModel model = (DefaultTableModel) table_1.getModel();
	    model.setRowCount(0);

	    int stt = 1;

	    DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    for (HoaDon hd : dsHoaDon) {
	        LocalDateTime ngayLapHD = hd.getNgayLapHD();
	        
	        if (ngayLapHD != null) {
	            String formattedNgayLapHD = ngayLapHD.format(formatter);

	            model.addRow(new Object[]{
	                stt++,
	                hd.getMaHD(),
	                hd.getKhachHang().getTenKH(),
	                hd.getNhanVien().getTen(),
	                formattedNgayLapHD,
	                decimalFormat.format(hoaDon.getTongTienByMaHD(hd.getMaHD()) + hoaDon.getKhuyenMaiByMaHD(hd.getMaHD())),
	                decimalFormat.format(hoaDon.getKhuyenMaiByMaHD(hd.getMaHD())),
	                decimalFormat.format(hoaDon.getTongTienByMaHD(hd.getMaHD()))
	            });
	        } else {
	            // hoadon null
	            System.out.println("Ngày lập hóa đơn là null cho hóa đơn có mã: " + hd.getMaHD());
	        }
	    }
	}



// load danh sách CTHĐ lên bảng cthđ
	private void showChiTietHoaDon(String maHD) throws Exception {
	    // Gọi phương thức để lấy chi tiết hóa đơn từ mã hóa đơn
	    ArrayList<ChiTietHoaDon> dsCTHD = hoaDon.GetDSCTHD(maHD);

	    // Xóa dữ liệu cũ trong bảng chi tiết hóa đơn
	    DefaultTableModel chiTietModel = (DefaultTableModel) table.getModel();
	    chiTietModel.setRowCount(0);
	    DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");

	    // Hiển thị chi tiết hóa đơn trong bảng chi tiết hóa đơn
	    int stt = 1;
	    for (ChiTietHoaDon cthd : dsCTHD) {
	        model.addRow(new Object[]{
	            stt++,
	            cthd.getSanPham().getMaSP(),
	            cthd.getSanPham().getTenSP(),
	            cthd.getSanPham().TinhGiaBan(),
	            cthd.getSoLuong(),
	            decimalFormat.format(cthd.TinhThanhTien() )
	        });
	    }
	}


//Xử lý btn Làm mới
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if(o.equals(btnLamMoi)) {
            txtMaHD.setText("");
            txtNV.setText("");

            txtKH.setText("");
            comboBox.setSelectedItem("");
            dateChooser.setDate(null);
        }
    }

}
