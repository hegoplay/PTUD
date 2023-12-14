package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.HoaDonDAO;
import dao.KhuyenMaiDAO;
import dao.SanPhamDAO;
import entity.KhuyenMai;
import entity.SanPham;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PnlKhuyenMai extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField textField, txtMaSanPham, txtPhanTram, txtTien;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtMa, txtTenChuongTrinh ;
	private  JRadioButton radTheoLoai, radTheoSoSP, radPhanTram, radGiaTien;
	private JDateChooser dateChooserBatDau, dateChooserKetThuc;
	private JComboBox<String> cbLoaiSanPham ;
	private JButton btnTao, btnTaoKM, btnCapNhat, btnLamMoi, btnThem;
	private JLabel lblDsSP;
	private String loaiKhuyenMaiHienTai;
	/**
	 * Create the panel.
	 */
	public PnlKhuyenMai() {
		setBackground(MainFrame.clrTheme);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(MainFrame.clrTheme);
		pnlTitle.setLayout(new BorderLayout());
		pnlTitle.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(pnlTitle, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("TẠO CHƯƠNG TRÌNH KHUYẾN MÃI");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(153, 0, 0));
		pnlTitle.add(lblTitle, BorderLayout.NORTH);
		
// Hàng 0
        JPanel pnl0 = new JPanel();
        pnl0.setLayout(new BorderLayout(10, 10));
//        pnl0.setBorder(new EmptyBorder(15,0, 10,0));
        pnl0.setBackground(MainFrame.clrTheme);
        pnl0.setBorder(new EmptyBorder(20, 60, 0, 500));
        pnlTitle.add(pnl0,BorderLayout.CENTER);
        
        JPanel pnlright = new JPanel();
        pnlright.setLayout(new BorderLayout());
        pnlright.setBackground(MainFrame.clrTheme);

        pnl0.add(pnlright, BorderLayout.CENTER);
        
        JLabel lblMa = new JLabel("Mã Chương Trình Khuyến Mãi:   ");
        lblMa.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlright.add(lblMa, BorderLayout.WEST);
        
        txtMa = new JTextField();
        txtMa.setPreferredSize(new Dimension(txtMa.getPreferredSize().width, 30));
		txtMa.setEditable(false);
        pnlright.add(txtMa, BorderLayout.CENTER);
        
		btnTao = new JButton("Tạo");
		btnTao.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTao.setForeground(new Color(255, 255, 255));
		btnTao.setBackground(new Color(69, 129, 142));
		pnlright.add(btnTao, BorderLayout.EAST);

        JPanel pnlContent = new JPanel();
        pnlContent.setBackground(MainFrame.clrTheme);
        pnlContent.setLayout(new GridLayout(2, 1, 10, 10));
        pnlContent.setBorder(new EmptyBorder(0, 30, 10, 30));
        add(pnlContent, BorderLayout.CENTER);
		
		
        JPanel pnlMainContent = new JPanel();
        pnlMainContent.setBackground(MainFrame.clrTheme);
        pnlMainContent.setLayout(new GridLayout(4, 1, 10, 10));
        pnlMainContent.setBorder(new EmptyBorder(0, 30, -10, 30));
        pnlContent.add(pnlMainContent);
        

        
//Hang1

        JPanel pnl1 = new JPanel();
        pnl1.setLayout(new GridLayout(1, 2, 0, 0));
        pnl1.setBorder(new EmptyBorder(15,0, 10,0));
        pnl1.setBackground(MainFrame.clrTheme);
        pnlMainContent.add(pnl1);

        
        JPanel pnlTenCT = new JPanel();
        pnlTenCT.setLayout(new BorderLayout());
        pnlTenCT.setBackground(MainFrame.clrTheme);

        pnl1.add(pnlTenCT);
        
        JLabel lblTenChuongTrinh = new JLabel("Tên Chương Trình:   ");
        lblTenChuongTrinh.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlTenCT.add(lblTenChuongTrinh, BorderLayout.WEST);
        
        txtTenChuongTrinh = new JTextField();
        pnlTenCT.add(txtTenChuongTrinh, BorderLayout.CENTER);
        
        
        
        JPanel pnlDate = new JPanel();
        pnlDate.setLayout(new GridLayout(1, 2, 0, 10));
        pnlDate.setBackground(MainFrame.clrTheme);

        pnl1.add(pnlDate);
        
        JPanel pnlNgayBatDau = new JPanel();
        pnlNgayBatDau.setLayout(new GridLayout(1, 2, 10, 10));
        pnlNgayBatDau.setBackground(MainFrame.clrTheme);
        pnlDate.add(pnlNgayBatDau);

        JLabel lblNgayBatDau = new JLabel("Ngày Bắt Đầu:");
        lblNgayBatDau.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNgayBatDau.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlNgayBatDau.add(lblNgayBatDau);

        dateChooserBatDau = new JDateChooser();
        dateChooserBatDau.setDateFormatString("dd-MM-yyyy");
        pnlNgayBatDau.add(dateChooserBatDau);

        JPanel pnlNgayKetThuc = new JPanel();
        pnlNgayKetThuc.setLayout(new GridLayout(1, 2, 10, 10));
        pnlNgayKetThuc.setBackground(MainFrame.clrTheme);
        pnlDate.add(pnlNgayKetThuc);

        JLabel lblNgayKetThuc = new JLabel("Ngày Kết Thúc:");
        lblNgayKetThuc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNgayKetThuc.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlNgayKetThuc.add(lblNgayKetThuc);

        dateChooserKetThuc = new JDateChooser();
        dateChooserKetThuc.setDateFormatString("dd-MM-yyyy");
        pnlNgayKetThuc.add(dateChooserKetThuc);
        
 //Hang2       
        JPanel pnl2 = new JPanel();
        pnl2.setLayout(new GridLayout(1, 2, 70,30));
        pnl2.setBackground(MainFrame.clrTheme);
        pnlMainContent.add(pnl2);
        
        
        JPanel pnlLoaiKM = new JPanel();
        pnlLoaiKM.setLayout(new GridLayout(1, 2, 0, 10));
        pnlLoaiKM.setBackground(MainFrame.clrTheme);
        pnl2.add(pnlLoaiKM);
        
        JLabel lblLoaiKhuyenMai = new JLabel("Loại Khuyến Mãi:");
        lblLoaiKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlLoaiKM.add(lblLoaiKhuyenMai);
        
        JPanel pnlRadLoaiKM = new JPanel();
        pnlRadLoaiKM.setLayout(new GridLayout(3,1,10,0));
        pnlRadLoaiKM.setBackground(MainFrame.clrTheme);
        pnlLoaiKM.add(pnlRadLoaiKM);

        radTheoLoai = new JRadioButton("Giảm Theo Loại");
        radTheoLoai.setBackground(MainFrame.clrTheme);
        radTheoSoSP = new JRadioButton("Chỉ Một Số Sản Phẩm");
        radTheoSoSP.setBackground(MainFrame.clrTheme);
        ButtonGroup radGroup = new ButtonGroup();
        radGroup.add(radTheoLoai);
        radGroup.add(radTheoSoSP);

        pnlRadLoaiKM.add(radTheoLoai);
        pnlRadLoaiKM.add(radTheoSoSP);
        
        JPanel pnlLoaiKM1 = new JPanel();
        pnlLoaiKM1.setLayout(new GridLayout(1, 2, 0, 10));
        pnlLoaiKM1.setBackground(MainFrame.clrTheme);
        pnlRadLoaiKM.add(pnlLoaiKM1);
        
        
 //Sản phẩm áp dụng KM
        JPanel pnlSPAD = new JPanel();
        pnlSPAD.setLayout(new GridLayout(3, 1, 20, 0));
        pnlSPAD.setBackground(MainFrame.clrTheme);
        pnl2.add(pnlSPAD);
        
        JPanel pnlLoaiSP = new JPanel();
        pnlLoaiSP.setLayout(new GridLayout(1, 2, 20, 10));
        pnlLoaiSP.setBackground(MainFrame.clrTheme);
        pnlSPAD.add(pnlLoaiSP);
        
        JLabel lblLoaiSanPham = new JLabel("Loại Sản Phẩm:");
        lblLoaiSanPham.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLoaiSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlLoaiSP.add(lblLoaiSanPham);

        ArrayList<String> dsTenLoaiSP = KhuyenMaiDAO.getAllTenLoaiSP();
        String[] arrTenLoaiSP = dsTenLoaiSP.toArray(new String[0]);
        cbLoaiSanPham = new JComboBox<>(arrTenLoaiSP);
        cbLoaiSanPham.setEditable(false);
        pnlLoaiSP.add(cbLoaiSanPham);
        
        JPanel pnlSP = new JPanel();
        pnlSP.setLayout(new GridLayout(1, 2, 20, 10));
        pnlSP.setBackground(MainFrame.clrTheme);
        pnlSPAD.add(pnlSP);
        
        JLabel lblMaSanPham = new JLabel("Mã Sản Phẩm:");
        lblMaSanPham.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlSP.add(lblMaSanPham);

        
        JPanel pnlSP1 = new JPanel();
        pnlSP1.setLayout(new BorderLayout());
        pnlSP1.setBackground(MainFrame.clrTheme);
        pnlSP.add(pnlSP1);
        
        txtMaSanPham = new JTextField();
        pnlSP1.add(txtMaSanPham, BorderLayout.CENTER);
        
		btnThem = new JButton("+");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(69, 129, 142));
		pnlSP1.add(btnThem, BorderLayout.EAST);
 
        ///
      
        JPanel pnlDsSP = new JPanel();
	    pnlDsSP.setLayout(new BorderLayout());
	    pnlDsSP.setBackground(MainFrame.clrTheme);
	    pnlSPAD.add( pnlDsSP);
	      
	      
	    lblDsSP = new JLabel("");
	    lblDsSP.setFont(new Font("Tahoma", Font.ITALIC, 9));
	    pnlDsSP.add(lblDsSP, BorderLayout.EAST);
      
    
//Hang3     
        JPanel pnl3 = new JPanel();
        pnl3.setLayout(new GridLayout(1, 2, 70,30));
        pnl3.setBackground(MainFrame.clrTheme);
        pnlMainContent.add(pnl3);
        
        JPanel pnlHTKM = new JPanel();
        pnlHTKM.setLayout(new GridLayout(1, 2, 0, 10));
        pnlHTKM.setBackground(MainFrame.clrTheme);
        pnl3.add(pnlHTKM);
        
        JLabel lblHTKhuyenMai = new JLabel("Hình Thức Khuyến Mãi:");
        lblHTKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlHTKM.add(lblHTKhuyenMai);
        
        JPanel pnlRadHTKM = new JPanel();
        pnlRadHTKM.setLayout(new GridLayout(2,1,10,0));
        pnlRadHTKM.setBackground(MainFrame.clrTheme);
        pnlHTKM.add(pnlRadHTKM);

        radPhanTram = new JRadioButton("Theo chiết khẩu %");
        radPhanTram.setBackground(MainFrame.clrTheme);
        radGiaTien = new JRadioButton("Theo mức tiền");
        radGiaTien.setBackground(MainFrame.clrTheme);

        ButtonGroup radGroup1 = new ButtonGroup();
        radGroup1.add(radPhanTram);
        radGroup1.add(radGiaTien);

        pnlRadHTKM.add(radPhanTram);
        pnlRadHTKM.add(radGiaTien);
        
              

        
 //Mức KM
        JPanel pnlMucKM = new JPanel();
        pnlMucKM.setLayout(new GridLayout(2, 1, 20, 0));
        pnlMucKM.setBackground(MainFrame.clrTheme);
        pnl3.add(pnlMucKM);
        
        JPanel pnlTheoPT = new JPanel();
        pnlTheoPT.setLayout(new GridLayout(1, 2, 20, 10));
        pnlTheoPT.setBackground(MainFrame.clrTheme);
        pnlMucKM.add(pnlTheoPT);
        
        JLabel lblTheoPT = new JLabel("Phần trăm giảm:");
        lblTheoPT.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTheoPT.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlTheoPT.add(lblTheoPT);

        txtPhanTram = new JTextField();
        pnlTheoPT.add(txtPhanTram);
        
        JPanel pnlTien = new JPanel();
        pnlTien.setLayout(new GridLayout(1, 2, 20, 10));
        pnlTien.setBackground(MainFrame.clrTheme);
        pnlMucKM.add(pnlTien);
        
        JLabel lblTien = new JLabel("Mức tiền giảm:");
        lblTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTien.setFont(new Font("Tahoma", Font.BOLD, 13));
        pnlTien.add(lblTien);

        txtTien = new JTextField();
        pnlTien.add(txtTien);
        

    
//Hang4     
        JPanel pnl4 = new JPanel();
        pnl4.setBackground(MainFrame.clrTheme);
        pnlMainContent.add(pnl4);
//        pnl4.setLayout(null);
        
        
        btnTaoKM = new JButton("Tạo Khuyến Mãi");
        btnTaoKM.setForeground(new Color(255, 255, 255));
        btnTaoKM.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTaoKM.setBackground(new Color(69, 129, 142));
        pnl4.add(btnTaoKM);
        
        btnCapNhat = new JButton("Cập Nhật");
        btnCapNhat.setForeground(new Color(255, 255, 255));
        btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCapNhat.setBackground(new Color(69, 129, 142));
        pnl4.add(btnCapNhat);
        
        btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBackground(new Color(69, 129, 142));
        pnl4.add(btnLamMoi);

        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.setBackground(MainFrame.clrCyan2);
        pnlSouth.setBorder(BorderFactory.createTitledBorder("Danh Sách Chương Trình Khuyến Mãi"));
        pnlContent.add(pnlSouth);

        String[] columnNames = {"STT", "Mã KM", "Tên Chương Trình", "Bắt Đầu", "Kết Thúc", "Mức Giảm", "Loại/Sản Phẩm"};

        model = new DefaultTableModel(null, columnNames); // Khởi tạo model với dữ liệu là null
        table = new JTable(model);
        
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(300);
        JScrollPane scrollPane = new JScrollPane(table);
        pnlSouth.add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String maKM = String.valueOf(table.getValueAt(selectedRow, 1));
                    String tenChuongTrinh = String.valueOf(table.getValueAt(selectedRow, 2));
                    String ngayBatDauString = String.valueOf(table.getValueAt(selectedRow, 3));
                    String ngayKetThucString = String.valueOf(table.getValueAt(selectedRow, 4));
                    double mucGiamGia = Double.parseDouble(String.valueOf(table.getValueAt(selectedRow, 5)));
                    String dsMaSP = String.valueOf(table.getValueAt(selectedRow, 6));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate ngayBatDauValue = LocalDate.parse(ngayBatDauString, formatter);
                    LocalDate ngayKetThucValue = LocalDate.parse(ngayKetThucString, formatter);

                    txtMa.setText(maKM);
                    txtTenChuongTrinh.setText(tenChuongTrinh);
                    dateChooserBatDau.setDate(Date.from(ngayBatDauValue.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    dateChooserKetThuc.setDate(Date.from(ngayKetThucValue.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                    // Lưu loại khuyến mãi hiện tại
                    loaiKhuyenMaiHienTai = radPhanTram.isSelected() ? "Phần trăm" : "Giá tiền";

                    
                    if (dsMaSP.contains(",")) {
                        radTheoLoai.setSelected(false);
                        radTheoSoSP.setSelected(true);

                        lblDsSP.setText(dsMaSP);
                	    cbLoaiSanPham.setSelectedIndex(0);
                	    cbLoaiSanPham.setEditable(false);
                        danhSachMaSP.clear();
                        if (!dsMaSP.isEmpty()) {
                            String[] maSPArray = dsMaSP.split(", ");
                            danhSachMaSP.addAll(Arrays.asList(maSPArray));
                        }
                    } else {
                        radTheoLoai.setSelected(true);
                        radTheoSoSP.setSelected(false);

                        String maLoai = dsMaSP.trim();
                        cbLoaiSanPham.setSelectedItem(SanPhamDAO.getTenLoaiByMaLoai(maLoai));
                        lblDsSP.setText("");
                        txtMaSanPham.setEditable(false);
                    }

                    if (mucGiamGia < 1) {
                        radPhanTram.setSelected(true);
                        radGiaTien.setSelected(false);
                        txtPhanTram.setText(String.valueOf(mucGiamGia));
                    } else {
                        radPhanTram.setSelected(false);
                        radGiaTien.setSelected(true);
                        txtTien.setText(String.valueOf(mucGiamGia));
                    }
                } else {
                    lamMoi();
                }
            }
        });


        // Xử lý sự kiện để disable/enable tương ứng với lựa chọn loại khuyến mãi
        radTheoLoai.addActionListener(e -> {
            cbLoaiSanPham.setEnabled(true);
            txtMaSanPham.setEnabled(false);
        });

        radTheoSoSP.addActionListener(e -> {
            cbLoaiSanPham.setEnabled(false);
            txtMaSanPham.setEnabled(true);
        });
        
        radPhanTram.addActionListener(e -> {
            txtPhanTram.setEnabled(true);
            txtTien.setEnabled(false);
        });

        radGiaTien.addActionListener(e -> {
        	txtPhanTram.setEnabled(false);
        	txtTien.setEnabled(true);
        });

        btnTao.addActionListener(this);
        btnTaoKM.addActionListener(this);
		btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnLamMoi.addActionListener(this);
		
        loadKhuyenMaiToTable();	
	}

	public void loadKhuyenMaiToTable() {
	    // Xóa dữ liệu hiện tại trong bảng
	    model.setRowCount(0);
	    ArrayList<KhuyenMai> dsKhuyenMai = KhuyenMaiDAO.getAllKhuyenMai();

	    // check maKM đã có chưa?
		    Map<String, Integer> maKMMap = new HashMap<>();
	
		    // Đổ dữ liệu vào bảng
		    int stt = 1; 
			    for (KhuyenMai km : dsKhuyenMai) {
			        String maKM = km.getMaChuongTrinh();
			        if (!maKMMap.containsKey(maKM)) {
			            maKMMap.put(maKM, stt);
		
			            if (km.getLoaiKhuyenMai().equals("Giảm Theo Loại")) {
			                // Lấy danh sách sản phẩm từ khuyến mãi
			                ArrayList<SanPham> dsSanPham = KhuyenMaiDAO.getDsSPbyMaKM(km.getMaChuongTrinh());
		
			                // Kiểm tra ds=null?
			                if (!dsSanPham.isEmpty()) {
			                    String maSP = dsSanPham.get(0).getMaSP();
		
			                    //getLoai từ maSP
			                    String maLoai = SanPhamDAO.getMaLoaiByMaSP(maSP);
			                    model.addRow(new Object[]{stt++, maKM, km.getTenChuongTrinh(), formatDate(km.getNgayBatDau()), formatDate(km.getNgayKetThuc()), km.getMucGiamGia(), maLoai});
			                }
			            } else {
			                // Đổ dsmaSP vào table
			                StringBuilder allMaSP = new StringBuilder();
			                ArrayList<SanPham> dsSanPham = KhuyenMaiDAO.getDsSPbyMaKM(km.getMaChuongTrinh());
		
			                for (SanPham sp : dsSanPham) {
			                    allMaSP.append(sp.getMaSP()).append(", ");
			                }
			                if (allMaSP.length() > 0) {
			                    // Xóa dấu phẩy thừa ở cuối chuỗi
			                    allMaSP.setLength(allMaSP.length() - 2);
		
			                    model.addRow(new Object[]{stt++, maKM, km.getTenChuongTrinh(), formatDate(km.getNgayBatDau()), formatDate(km.getNgayKetThuc()), km.getMucGiamGia(), allMaSP.toString()});
			                }
			            }
			        }
			    }
	}


	private String formatDate(LocalDateTime dateTime) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return dateTime.format(formatter);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTaoKM)) {
			try {
				if (validateInput()) {
			        try {
			            tao(); // Gọi hàm tao() nếu validateInput() trả về true
			        } catch (Exception ex) {
			            // Xử lý ngoại lệ nếu có
			            ex.printStackTrace();
			        }
			    }
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(o.equals(btnTao)) {

			taoMaKM();
		}
		else if(o.equals(btnThem)) {
			them();
		}
		else if(o.equals(btnLamMoi)) {
			lamMoi();
		}
		else if(o.equals(btnCapNhat)) {
			capNhat();
		}
		
	}

	private void capNhat() {
	    // Lấy thông tin từ các thành phần phía trên
	    String maKM = txtMa.getText().trim();
	    String tenChuongTrinh = txtTenChuongTrinh.getText().trim();
	    LocalDate ngayBatDauValue = dateChooserBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate ngayKetThucValue = dateChooserKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	    // Kiểm tra thông tin không rỗng
	    if (tenChuongTrinh.isEmpty() || ngayBatDauValue == null || ngayKetThucValue == null) {
	        JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ. Hãy kiểm tra lại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    KhuyenMai khuyenMai = KhuyenMaiDAO.GetKM(maKM);

	    // Kiểm tra sự thay đổi trước khi cập nhật
	    if (!tenChuongTrinh.equals(khuyenMai.getTenChuongTrinh()) ||
	        !ngayBatDauValue.equals(khuyenMai.getNgayBatDau()) ||
	        !ngayKetThucValue.equals(khuyenMai.getNgayKetThuc()) ||
	        !txtPhanTram.getText().equals(String.valueOf(khuyenMai.getMucGiamGia())) ||
	        !txtTien.getText().equals(String.valueOf(khuyenMai.getMucGiamGia()))) {
	    	
            cbLoaiSanPham.setEditable(false);
            txtMaSanPham.setEditable(false);
	    	
	    	radTheoLoai.setEnabled(false);
	    	radTheoSoSP.setEnabled(false);
	    	
	        double mucGiamMoi;
	    	
//	    	if (loaiKhuyenMaiHienTai != null && !loaiKhuyenMaiHienTai.equals("")) {
//	    	    if ((loaiKhuyenMaiHienTai.equals("Phần trăm") && radPhanTram.isSelected()) ||
//	    	        (loaiKhuyenMaiHienTai.equals("Giá tiền") && radGiaTien.isSelected())) {
//
//	    	        // Không cho phép chuyển đổi, đặt lại lựa chọn trước đó
//	    	        if (loaiKhuyenMaiHienTai.equals("Phần trăm")) {
//	    	            radPhanTram.setSelected(true);
////	    	            radGiaTien.setSelected(false);
//	    	            txtPhanTram.setEditable(false);
//	    	            radGiaTien.setEnabled(false); // Đặt radGiaTien không thể chỉnh sửa
//	    	            mucGiamMoi = Double.parseDouble(txtPhanTram.getText());
//	    	            txtTien.setText("");
//	    	            txtTien.setEditable(false);
//	    	            
//	    	        } else if (loaiKhuyenMaiHienTai.equals("Giá tiền")) {
////	    	            radPhanTram.setSelected(false);
//	    	            radGiaTien.setSelected(true);
//	    	            radPhanTram.setEnabled(false); // Đặt radGiaTien có thể chỉnh sửa (nếu cần)
//	    	            mucGiamMoi = Double.parseDouble(txtTien.getText());
//	    	            txtPhanTram.setText("");
//	    	            txtPhanTram.setEditable(false);
//	    	        }
//	    	        return;
//	    	    }
//	    	}


	        // Có sự thay đổi, thực hiện cập nhật
	        LocalDateTime ngayBatDau = ngayBatDauValue.atStartOfDay();
	        LocalDateTime ngayKetThuc = ngayKetThucValue.atStartOfDay();

	        // Lấy thông tin mức giảm mới
//	        double mucGiamMoi;
	        if (radPhanTram.isSelected()) {
	            mucGiamMoi = Double.parseDouble(txtPhanTram.getText());
	            txtTien.setText("");
	            txtTien.setEditable(false);
	        } else {
	            mucGiamMoi = Double.parseDouble(txtTien.getText());
	            txtPhanTram.setText("");
	            txtPhanTram.setEditable(false);
	        }

	        // Cập nhật thông tin trong đối tượng KhuyenMai
	        khuyenMai.setTenChuongTrinh(tenChuongTrinh);
	        khuyenMai.setNgayBatDau(ngayBatDau);
	        khuyenMai.setNgayKetThuc(ngayKetThuc);
	        khuyenMai.setMucGiamGia(mucGiamMoi);

	        // Cập nhật dữ liệu trong table
	        int selectedRow = table.getSelectedRow();
	        table.setValueAt(khuyenMai.getMaChuongTrinh(), selectedRow, 1);
	        table.setValueAt(tenChuongTrinh, selectedRow, 2);
	        table.setValueAt(ngayBatDauValue, selectedRow, 3);
	        table.setValueAt(ngayKetThucValue, selectedRow, 4);
	        table.setValueAt(mucGiamMoi, selectedRow, 5);  // Cập nhật cột mucGiam trong table

	        
	        validateInput();
	        // Lưu vô DB
	        KhuyenMaiDAO.capNhatThongTin(maKM, tenChuongTrinh, ngayBatDauValue, ngayKetThucValue, mucGiamMoi);

	        JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        lamMoi();
	    } else {
	        JOptionPane.showMessageDialog(this, "Không có sự thay đổi để cập nhật.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}



	private void taoMaKM() {
		// TODO Auto-generated method stub
	    String maKM=null;
		try {
			maKM = KhuyenMaiDAO.taoMaKM();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    if (maKM != null) {
	        txtMa.setText(maKM);
	    } else {
	        JOptionPane.showMessageDialog(this, "Không thể tạo Mã Khuyến Mãi. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void lamMoi() {
	    txtMa.setText("");
	    txtTenChuongTrinh.setText("");
	    txtPhanTram.setText("");
	    txtTien.setText("");
	    txtMaSanPham.setText("");

	    // Đặt lại trạng thái của các JComboBox và các RadioButton, JDateChooser
	    cbLoaiSanPham.setSelectedIndex(0); 
	    
	    radTheoLoai.setSelected(false);
	    radTheoSoSP.setSelected(false);
	    radPhanTram.setSelected(false);
	    radGiaTien.setSelected(false);
	    
	    dateChooserBatDau.setDate(null);
	    dateChooserKetThuc.setDate(null);
	    
	    lblDsSP.setText("");
	}

	// Ds các mã sản phẩm đã thêm
	private List<String> danhSachMaSP = new ArrayList<>();

	private void them() {
	    String maSanPham = txtMaSanPham.getText().trim();

	    // Kiểm tra xem mã sản phẩm đã tồn tại?
	    if (!danhSachMaSP.contains(maSanPham)) {
	        String currentContent = lblDsSP.getText();

	        // Ghi nối mã sản phẩm lblDsSP
	        if (currentContent.isEmpty()) {
	            lblDsSP.setText(maSanPham);
	        } else {
	            lblDsSP.setText(currentContent + ", " + maSanPham);
	        }
	        danhSachMaSP.add(maSanPham);

	    } else {
	        // Mã sản phẩm đã tồn tại, thông báo
	        JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    }
	    txtMaSanPham.setText("");
	}
	
	private void tao() throws Exception {
	    // Lấy thông tin 
	    String maChuongTrinh = txtMa.getText().trim();
	    String tenChuongTrinh = txtTenChuongTrinh.getText().trim();
	    LocalDateTime ngayBatDau = dateChooserBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    LocalDateTime ngayKetThuc = dateChooserKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    String loaiKhuyenMai = radTheoLoai.isSelected() ? "Giảm Theo Loại" : "Chỉ Một Số Sản Phẩm";
//	    double mucGiamGia = radPhanTram.isSelected() ? Double.parseDouble(txtPhanTram.getText()) : Double.parseDouble(txtTien.getText());
	    double mucGiamGia;

	    if (radPhanTram.isSelected()) {
	        // Nếu giảm theo phần trăm, chia cho 100
	        mucGiamGia = Double.parseDouble(txtPhanTram.getText()) / 100.0;
	    } else {
	        // Nếu giảm theo mức tiền, lấy giá trị trực tiếp
	        mucGiamGia = Double.parseDouble(txtTien.getText());
	    }

	    ArrayList<SanPham> dsSP = new ArrayList<>();

	    if (loaiKhuyenMai.equals("Giảm Theo Loại")) {
	        // Lấy toàn bộ sản phẩm có mã loại là mã được chọn
	        String selectedLoaiSP = cbLoaiSanPham.getSelectedItem().toString();
	        dsSP = (ArrayList<SanPham>) SanPhamDAO.getDSSanPhamByTenLoai(selectedLoaiSP);
	    } else if (loaiKhuyenMai.equals("Chỉ Một Số Sản Phẩm")) {
	        // Lấy danh sách sản phẩm từ danhSachMaSP
	        for (String maSP : danhSachMaSP) {
	            SanPham sp = SanPhamDAO.GetSanPham(maSP);
	            if (sp != null) {
	                dsSP.add(sp);
	            } else {
	                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm có mã " + maSP, "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        }
	    }

	    KhuyenMai khuyenMai = new KhuyenMai(maChuongTrinh, tenChuongTrinh, ngayBatDau, ngayKetThuc, loaiKhuyenMai, mucGiamGia, dsSP);

	    boolean luuThanhCong = KhuyenMaiDAO.luuKM(khuyenMai, dsSP);
	    if (luuThanhCong) {
	        JOptionPane.showMessageDialog(this, "Tạo chương trình khuyến mãi thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        loadKhuyenMaiToTable();
	        System.out.println("loadKhuyenMaiToTable() is called.");
	    } else {
	        JOptionPane.showMessageDialog(this, "Lỗi khi lưu vào cơ sở dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}


	// Hàm kiểm tra các trường nhập liệu
	
	
	private boolean validateInput() {
	    String maKM = txtMa.getText();
	    if (maKM.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chương trình khuyến mãi.");
	        return false;
	    }

	    String tenChuongTrinh = txtTenChuongTrinh.getText();
	    if (tenChuongTrinh.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập tên chương trình khuyến mãi.");
	        return false;
	    }

//	    Date ngayBatDau = (Date) dateChooserBatDau.getDate();
//	    Date ngayKetThuc = (Date) dateChooserKetThuc.getDate();
	    
	 // Chuyển đổi java.util.Date sang java.sql.Date
	    java.util.Date ngayBatDauUtil = dateChooserBatDau.getDate();
	    java.sql.Date ngayBatDauSql = new java.sql.Date(ngayBatDauUtil.getTime());

	    java.util.Date ngayKetThucUtil = dateChooserKetThuc.getDate();
	    java.sql.Date ngayKetThucSql = new java.sql.Date(ngayKetThucUtil.getTime());

	    if (ngayBatDauSql == null || ngayKetThucSql == null || ngayBatDauSql.after(ngayKetThucSql)) {
//	        JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và kết thúc hợp lệ.");
//	        return false;
	    	JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Kiểm tra ngày kết thúc sau ngày bắt đầu
	    if (!ngayKetThucSql.after(ngayBatDauSql)) {
	        JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu.");
	        return false;
	    }

	    // Kiểm tra loại khuyến mãi và giá trị mức giảm
	    if (radTheoLoai.isSelected()) {
	        String maLoaiSP = cbLoaiSanPham.getSelectedItem().toString();
	        if (maLoaiSP.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm.");
	            return false;
	        }
	    } else if (radTheoSoSP.isSelected()) {
	        String maSanPham = lblDsSP.getText();
	        if (maSanPham.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm.");
	            txtMaSanPham.requestFocus();
	            return false;
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn loại khuyến mãi (Theo loại hoặc Chỉ một số sản phẩm).");
	        return false;
	    }

	    // Kiểm tra hình thức khuyến mãi và giá trị mức giảm
	    if (radPhanTram.isSelected()) {
	        // Kiểm tra giảm theo phần trăm
	        String phanTram = txtPhanTram.getText().replace(",", ".");
	        if (phanTram.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị phần trăm giảm.");
	            return false;
	        }
	        try {
	            double percent = Double.parseDouble(phanTram);
	            if (percent < 0 || percent > 100) {
	                JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm giảm từ 0 đến 100.");
	                txtPhanTram.setText("");
	                txtPhanTram.requestFocus();
	                return false;
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị phần trăm giảm hợp lệ.");
	            return false;
	        }
	    } else if (radGiaTien.isSelected()) {
	        // Kiểm tra giảm theo mức tiền
	        String tienGiam = txtTien.getText();
	        if (tienGiam.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị mức tiền giảm.");
	            return false;
	        }
	        try {
	            double amount = Double.parseDouble(tienGiam);
	            if (amount < 0) {
	                JOptionPane.showMessageDialog(this, "Vui lòng nhập mức tiền giảm không âm.");
	                return false;
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị mức tiền giảm hợp lệ.");
	            return false;
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn hình thức khuyến mãi (Theo chiết khẩu % hoặc Theo mức tiền).");
	        return false;
	    }

	    return true;
	}
}


