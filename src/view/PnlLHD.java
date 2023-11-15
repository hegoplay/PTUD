package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;

import component.TableEditor;
import dao.CthdDAO;
import dao.HoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class PnlLHD extends JPanel implements ActionListener, DocumentListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtTim;
	private JTextField txtMaHD;
	private JTextField txtKH, txtNV;
	private JTextField txtDate;
	private JTextField txtMaSP;
	private JTable table;
	private JTextField textField;
	private JDateChooser dateChooser;
	private DefaultTableModel model;
	private JTextField txtTienDua;
	private JLabel lblTraLaiVND, lblTongTienVND,lblTongCongVND, lblKMVND, lblNgay;
	private JButton btnLamMoi, btnXuat, btnThem, btnXoa, btnTao, btnTim;
	private JPanel pnlTimHD;
	private JPanel pnlTongHopDT;
	
	private JLabel lblGhiChu;
	private JTextArea txtGhiChu;
	
	private KhachHangDAO kh;
	private HoaDonDAO hd;
	private SanPhamDAO sp;
	private NhanVienDAO nv;
	private ChiTietHoaDon cthd;
	private HoaDon hoaDonv;
	private CthdDAO ctHD;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    private double tienTraLai =0;
    String userLogin = "admin";
	/**
	 * Create the panel.
	 */
	public PnlLHD() {
		setBackground(MainFrame.clrTheme);
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel pnlTopContent = new JPanel();
		pnlTopContent.setLayout(new BorderLayout());
		pnlTopContent.setBackground(MainFrame.clrTheme);
		add(pnlTopContent, BorderLayout.NORTH);
		
		JLabel lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setBounds(487, 11, 134, 40);
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setVerticalAlignment(SwingConstants.TOP);
		lblHoaDon.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHoaDon.setBorder(new EmptyBorder(10,10,0,10));
		pnlTopContent.add(lblHoaDon, BorderLayout.NORTH);
//Thông tin hóa đơn		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setLayout(new GridLayout(2,1,15,0));
		pnlInfo.setBackground(MainFrame.clrTheme);
		pnlInfo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		pnlTopContent.add(pnlInfo, BorderLayout.CENTER);
		
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setLayout(new GridLayout(1,3,10,5));
		pnlInfo1.setBackground(MainFrame.clrTheme);
		pnlInfo1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pnlInfo.add(pnlInfo1);
		
		JPanel pnlSDT = new JPanel();
		pnlSDT.setBackground(MainFrame.clrTheme);
		pnlInfo1.add(pnlSDT);
		pnlSDT.setLayout(null);
		
		JLabel lblSDT = new JLabel("Nhập SĐT Khách hàng:");
		lblSDT.setBackground(MainFrame.clrTheme);
		lblSDT.setBounds(0, 0, 173, 19);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlSDT.add(lblSDT);
		
		txtTim = new JTextField("");
		txtTim.setBounds(180, 0, 180, 27);
		txtTim.setColumns(10);
		pnlSDT.add(txtTim);
		
		btnTim = new JButton("Tìm");
		btnTim.setBounds(370, 0, 80, 27);
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(69, 129, 142));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlSDT.add(btnTim);
		
		JPanel pnlMaHD = new JPanel();
		pnlMaHD.setLayout(null);
		pnlMaHD.setBackground(MainFrame.clrTheme);
		pnlInfo1.add(pnlMaHD);
		
		JLabel lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setBounds(70, 0, 94, 19);
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlMaHD.add(lblMaHD);
		
		txtMaHD = new JTextField("HDxxxxxxxx");
		txtMaHD.setEditable(false);
		txtMaHD.setBounds(172,0, 230, 25);
		txtMaHD.setColumns(10);
		pnlMaHD.add(txtMaHD);
		
		btnTao = new JButton("Tạo");
		btnTao.setBounds(410, 0, 80, 27);
		btnTao.setForeground(new Color(255, 255, 255));
		btnTao.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTao.setBackground(new Color(69, 129, 142));
		pnlMaHD.add(btnTao);
		
		JPanel pnlInfo2 = new JPanel();
		pnlInfo2.setLayout(new GridLayout(1,3,10,5));
		pnlInfo2.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
		pnlInfo2.setBackground(new Color(254, 250, 224));
		pnlInfo.add(pnlInfo2);
		
		JPanel pnlKH = new JPanel();
		pnlKH.setLayout(new GridLayout(1,2,0,0));
		pnlKH.setBackground(MainFrame.clrTheme);
		pnlInfo2.add(pnlKH);
		
		JLabel lblKH = new JLabel("Tên khách hàng:");
//		lblKH.setBounds(30, 110, 134, 19);
		lblKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlKH.add(lblKH);
		
		txtKH = new JTextField();
		txtKH.setEditable(false);
		txtKH.setColumns(10);
		pnlKH.add(txtKH);
		
		JPanel pnlNV = new JPanel();
		pnlNV.setLayout(null);
		pnlNV.setBackground(MainFrame.clrTheme);
		pnlInfo2.add(pnlNV);
		
		JLabel lblNV = new JLabel("NV lập hóa đơn:");
		lblNV.setBounds(30, 0, 120, 19);
		lblNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNV.setBackground(MainFrame.clrTheme);
		pnlNV.add(lblNV);
		
		txtNV = new JTextField();
		txtNV.setBounds(160, 0, 184, 25);
		txtNV.setEditable(false);
		txtNV.setColumns(10);
		pnlNV.add(txtNV);
		
//Ngày lập hóa đơn		
		JPanel pnlNgayL = new JPanel();
		pnlNgayL.setLayout(null);
		pnlNgayL.setBackground(MainFrame.clrTheme);
		pnlInfo2.add(pnlNgayL);
		
		JLabel lblDate = new JLabel("Ngày lập hóa đơn:");
		lblDate.setBounds(30,0, 137, 19);
		lblDate.setBackground(MainFrame.clrTheme);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlNgayL.add(lblDate);
		
		lblNgay = new JLabel("");
		lblNgay.setBounds(185, 0, 150, 19);
		lblNgay.setBackground(MainFrame.clrTheme);
		lblNgay.setEnabled(false);
		lblNgay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		pnlNgayL.add(lblNgay);
		
		
		
//Chi tiết hóa đơn		
		JPanel pnlMainContent = new JPanel();
		pnlMainContent.setLayout(new BorderLayout());
		pnlMainContent.setBackground(MainFrame.clrTheme);
		add(pnlMainContent, BorderLayout.CENTER);	
				
		JPanel pnlCTHD = new JPanel();
		pnlCTHD.setBounds(10, 151, 1034, 282);
		pnlCTHD.setBackground(new Color(241, 231, 190));
		pnlCTHD.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMainContent.add(pnlCTHD, BorderLayout.CENTER);
		pnlCTHD.setLayout(new BorderLayout());
		
		JPanel pnlTopCTHD = new JPanel();
//		pnlTopCTHD.setLayout(new GridLayout(1, 2, 10 ,10));
		pnlTopCTHD.setLayout(new BorderLayout());
		pnlTopCTHD.setBackground(new Color(241, 231, 190));
		pnlTopCTHD.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		pnlCTHD.add(pnlTopCTHD, BorderLayout.NORTH);	
		
		JPanel pnlSP= new JPanel();
		pnlSP.setLayout(null);
		pnlSP.setBackground(new Color(241, 231, 190));
		pnlSP.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		pnlTopCTHD.add(pnlSP, BorderLayout.CENTER);
		
		JLabel lblmaSP = new JLabel("Nhập mã sản phẩm:");
		lblmaSP.setBounds(0, 0, 151, 19);
		pnlSP.add(lblmaSP);
		lblmaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtMaSP = new JTextField();
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(161, 0, 184, 25);
		pnlSP.add(txtMaSP);
		
		btnThem = new JButton("Thêm");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBackground(new Color(69, 129, 142));
		btnThem.setBounds(365, 0, 90, 27);
		pnlSP.add(btnThem);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoa.setBackground(new Color(69, 129, 142));
		btnXoa.setBounds(872, 11, 90, 27);
		pnlTopCTHD.add(btnXoa, BorderLayout.EAST);
		
		

//table Sản phẩm
		JPanel pnlMainCTHD = new JPanel();
		pnlMainCTHD.setLayout(new BorderLayout());
		pnlMainCTHD.setBackground(new Color(241, 231, 190));
		pnlMainCTHD.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 20));
		pnlCTHD.add(pnlMainCTHD, BorderLayout.CENTER);	
		model = new DefaultTableModel();
		table = new JTable(model);
		
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Tên sản phẩm");
		model.addColumn("Đơn giá (VNĐ)");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền (VNĐ)");
		
		
		JTextField textField = new JTextField();
		TableCellEditor editor = new TableEditor(textField);
		table.getColumnModel().getColumn(4).setCellEditor(editor);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 50, 1014, 221);
		pnlMainCTHD.add(scrollPane, BorderLayout.CENTER);
		
//Buttom
		JPanel pnlButtom = new JPanel();
		pnlButtom.setLayout(new BorderLayout());
		pnlButtom.setBackground(MainFrame.clrTheme);
		pnlButtom.setBorder(BorderFactory.createEmptyBorder(10, 0,10, 20));
		add(pnlButtom, BorderLayout.SOUTH);	
		
//Tính toán tiền	
		JPanel pnlTinhToan = new JPanel();
		pnlTinhToan.setLayout(new GridLayout(2,3, 20,15));
		pnlTinhToan.setBackground(MainFrame.clrTheme);
		pnlTinhToan.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
		pnlButtom.add(pnlTinhToan, BorderLayout.CENTER);	
		
		JPanel pnlTC = new JPanel();
		pnlTC.setLayout(new BorderLayout());
		pnlTC.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlTC);
		
		JLabel lblTongCong = new JLabel("Tổng cộng:");
		lblTongCong.setBackground(MainFrame.clrTheme);
		lblTongCong.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlTC.add(lblTongCong, BorderLayout.CENTER);
		
		lblTongCongVND = new JLabel("0 VNĐ");
		lblTongCongVND.setBackground(MainFrame.clrTheme);
		lblTongCongVND.setForeground(Color.GRAY);
		lblTongCongVND.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlTC.add(lblTongCongVND, BorderLayout.EAST);
		
		JPanel pnlKM = new JPanel();
		pnlKM.setLayout(new BorderLayout());
		pnlKM.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlKM);
		
		JLabel lblKhuyenMai = new JLabel("Khuyến mãi:");
		lblKhuyenMai.setBackground(MainFrame.clrTheme);
		lblKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlKM.add(lblKhuyenMai, BorderLayout.CENTER);
		
		lblKMVND = new JLabel("- 0 VNĐ");
		lblKMVND.setBackground(MainFrame.clrTheme);
		lblKMVND.setForeground(new Color(128, 128, 128));
		lblKMVND.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlKM.add(lblKMVND, BorderLayout.EAST);
		
		JPanel pnlTT = new JPanel();
		pnlTT.setLayout(new BorderLayout());
		pnlTT.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlTT);
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setBackground(MainFrame.clrTheme);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlTT.add(lblTongTien, BorderLayout.CENTER);
		
		lblTongTienVND = new JLabel("0 VNĐ");
		lblTongTienVND.setBackground(MainFrame.clrTheme);
		lblTongTienVND.setForeground(new Color(215, 0, 0));
		lblTongTienVND.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlTT.add(lblTongTienVND, BorderLayout.EAST);
		
		JPanel pnlTKD = new JPanel();
		pnlTKD.setLayout(new BorderLayout());
		pnlTKD.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlTKD);
		
		
		JLabel lblTienDua = new JLabel("Tiền khách đưa:");
//		lblTienDua.setBounds(22, 485, 134, 19);
		lblTienDua.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTienDua.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlTKD.add(lblTienDua, BorderLayout.WEST);
		
		txtTienDua = new JTextField();
//		txtTienDua.setBounds(160, 483, 173, 25);
		txtTienDua.setColumns(10);
		pnlTKD.add(txtTienDua, BorderLayout.CENTER);
		
		JPanel pnlTTL= new JPanel();
		pnlTTL.setLayout(new BorderLayout());
		pnlTTL.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlTTL);
		
		
		JLabel lblTienTra = new JLabel("Số tiền trả lại:");
//		lblTienTra.setBounds(22, 523, 120, 19);
		lblTienTra.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTienTra.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlTTL.add(lblTienTra, BorderLayout.WEST);
		
		lblTraLaiVND = new JLabel("0 VNĐ");
		lblTraLaiVND.setForeground(new Color(215, 0, 0));
		lblTraLaiVND.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlTTL.add(lblTraLaiVND, BorderLayout.CENTER);
		
		
		JPanel pnlGC= new JPanel();
		pnlGC.setLayout(new BorderLayout());
		pnlGC.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlGC);
		
		lblGhiChu = new JLabel("Ghi chú:");
//		lblGhiChu.setBounds(360, 485, 62, 19);
		lblGhiChu.setFont(new Font("Tahoma",Font.BOLD | Font.ITALIC, 15));
		lblGhiChu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlGC.add(lblGhiChu,BorderLayout.WEST);
		
		 
		
		txtGhiChu =new JTextArea();
//		txtGhiChu.setBounds(438, 483, 210, 49);
		txtGhiChu.setColumns(10);
		pnlGC.add(txtGhiChu,BorderLayout.CENTER);
		
		JPanel pnlAc = new JPanel();
		pnlAc.setBackground(MainFrame.clrTheme);
		pnlButtom.add(pnlAc, BorderLayout.SOUTH);
		pnlAc.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlAc1 = new JPanel();
		pnlAc1.setBackground(MainFrame.clrTheme);
		pnlAc.add(pnlAc1, BorderLayout.EAST);
		pnlAc1.setLayout(new GridLayout(1, 2, 20, 0));
		
		btnXuat = new JButton("Xuất hóa đơn");
		btnXuat.setForeground(new Color(255, 255, 255));
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuat.setBackground(new Color(69, 129, 142));
		pnlAc1.add(btnXuat);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(new Color(69, 129, 142));
		pnlAc1.add(btnLamMoi);
		
		
		
		String currentUsername = System.getProperty("user.name");
		txtNV.setText("Hoàng Thị Mỹ Linh");
//		txtNV.setText("Phạm Thế Mạnh");

	
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày tháng
		Date ngayHienHanh = new Date(System.currentTimeMillis()); // Lấy ngày hiện hành
		String ngayHienHanhFormatted = dateFormat.format(ngayHienHanh); // Định dạng ngày hiện hành

		lblNgay.setText(ngayHienHanhFormatted); 
		
		
		
		btnLamMoi.addActionListener(this);
		btnTao.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXuat.addActionListener(this);
		
		txtTienDua.getDocument().addDocumentListener(this);
		lblTienTra.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("text")){
					String tienTraLaitxt = lblTienTra.getText();
					if(!tienTraLaitxt.isEmpty()) {
						double tienThua = Double.parseDouble(tienTraLaitxt);
						
						if(tienThua<0) {
							btnXuat.setEnabled(false);
						}else {
							btnXuat.setEnabled(true);
						}
					}
				}
				
			}
		});

	}
	private void tinhTienTraLai() {
	    String tienKDStr = txtTienDua.getText().trim();
	    
	    if (tienKDStr.isEmpty()) {
	        // Xử lý trường hợp người dùng không nhập số tiền khách đưa
	        JOptionPane.showMessageDialog(txtTienDua, "Vui lòng nhập số tiền khách đưa!");
	        return;
	    }

	    double tienKhachDua;
	    try {
	        tienKhachDua = Double.parseDouble(tienKDStr);
	    } catch (NumberFormatException e) {
	        // Xử lý trường hợp người dùng nhập không phải số
	        JOptionPane.showMessageDialog(txtTienDua, "Số tiền khách đưa không hợp lệ!");
	        return;
	    }

	    String tongTienStr = lblTongTienVND.getText().replace(" VNĐ", "").replace(",", "");

	    if (!tongTienStr.matches("\\d+(\\.\\d+)?")) {
	        // Xử lý trường hợp định dạng của tổng tiền không đúng
	        JOptionPane.showMessageDialog(lblTongTienVND, "Định dạng tổng tiền không hợp lệ!");
	        return;
	    }

	    double tongTong = Double.parseDouble(tongTienStr);
	    if(tienKhachDua>=tongTong) {
		    tienTraLai = tienKhachDua - tongTong;
		    String formattedTienTra = decimalFormat.format(tienTraLai) + " VNĐ";
		    lblTraLaiVND.setText(formattedTienTra);
	    }

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTim)) {
			timSDT();
		}
		else if(o.equals(btnTao)) {
			tao();
		}
		else if(o.equals(btnThem)) {
			themSP();
		}
		else if(o.equals(btnXoa)) {
			xoa();
		}
		else if(o.equals(btnXuat)) {
			try {
				HoaDon hoad = getThongTinCreateHD();
				hd.ThemHD(hoad);
				JOptionPane.showMessageDialog(this, "Lập hóa đơn thành công", "Thông báo thành công",
						JOptionPane.OK_OPTION);
				lamMoi();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Lỗi khi thêm và xuất hóa đơn!");
			}
		}
		else if(o.equals(btnLamMoi)) {
			lamMoi();
		}
	}
	// Phần thêm hóa đơn
	private boolean themHD() throws Exception {
	    try {
	        HoaDon k = getThongTinCreateHD();

	        // Thêm hóa đơn
	        if (HoaDonDAO.ThemHD(k)) {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setDialogTitle("Chọn vị trí muốn lưu");

	            int userSelection = fileChooser.showSaveDialog(this);
	            if (userSelection == JFileChooser.APPROVE_OPTION) {
	                File fileToSave = fileChooser.getSelectedFile();
	                k.XuatHoaDon(fileToSave.getAbsolutePath());
	            }
	        	
	        	
	            return true;
	        } else {
	            JOptionPane.showMessageDialog(null, "Thêm thất bại. Kiểm tra lại dữ liệu và thử lại.");
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm hóa đơn. Vui lòng kiểm tra và thử lại.");
	        return false;
	    }
	}

	private ArrayList<ChiTietHoaDon> taoDSChiTietHD() throws NumberFormatException, Exception {
		ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		String maHD = txtMaHD.getText();
		for (int i = 0; i < table.getRowCount(); i++) {
			String maSP = table.getValueAt(i, 1).toString();
			String tenSP = table.getValueAt(i, 2).toString();
			String donGia = table.getValueAt(i, 3).toString();
			String soLuong = table.getValueAt(i, 4).toString();
			String thanhTien = table.getValueAt(i, 5).toString();
			SanPham newSP = sp.GetSanPham(maSP);
			ChiTietHoaDon cthd = new ChiTietHoaDon(newSP, Integer.parseInt(soLuong));
			ds.add(cthd);

		}
		return ds;
	}



	private HoaDon getThongTinCreateHD() throws Exception {
		HoaDon newHoaDon = null;
	    String maHD = txtMaHD.getText().trim();
	    
        LocalDateTime ngayLapHD =LocalDateTime.now();

	    String tenNV = txtNV.getText();    
	    String maNV = MainFrame.nv.getMaNV();
	    NhanVien newNv =nv.getNhanVien(maNV); 

	    String sdt = txtTim.getText().trim();
	    String maKH = kh.getKHBySDT(sdt).getMaKH(); 
	    KhachHang newKh = kh.getKhachHang(maKH);

        String tongCongStr = lblTongCongVND.getText();
        String tongCong = tongCongStr.replaceAll("[^0-9]", "");
        double gtTongCong = Double.parseDouble(tongCong);
	    
	    String khuyenMaiStr = lblKMVND.getText();

        // Loại bỏ các ký tự không phải số từ chuỗi
        String khuyenMai = khuyenMaiStr.replaceAll("[^0-9]", "");
        double gtKhuyenMai = Double.parseDouble(khuyenMai);
        float km = (float) (gtKhuyenMai/gtTongCong);
        

        
	    String tienDuaStr = txtTienDua.getText();

        String td = tienDuaStr.replaceAll("[^0-9]", "");
	    double tienDua = Double.parseDouble( td );

	    String ghiChu = txtGhiChu.getText();
	    ArrayList<ChiTietHoaDon> dsCTHD =taoDSChiTietHD();

	    newHoaDon = new HoaDon(maHD, ngayLapHD, newNv, newKh, km, tienDua, dsCTHD);
	    return newHoaDon;
	}

	private void tao() {
		// TODO Auto-generated method stub
	    String maHD=null;
		try {
			maHD = HoaDonDAO.taoMaHD();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    if (maHD != null) {
	        txtMaHD.setText(maHD);
	    } else {
	        // Xử lý trường hợp không thể tạo mã hóa đơn
	        JOptionPane.showMessageDialog(this, "Không thể tạo mã hóa đơn. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
		
	}
	private void lamMoi() {
		// TODO Auto-generated method stub
		txtTim.setText("");
		txtMaHD.setText("");
		txtKH.setText("");
		txtTienDua.setText("");
		lblTongCongVND.setText("");
		lblKMVND.setText("");
		lblTongTienVND.setText("");
		lblTraLaiVND.setText("");
		txtMaSP.setText("");
		
	    // Làm mới dữ liệu trong table
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	}
	private void xoa() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    int selectedRow = table.getSelectedRow();

	    if (selectedRow != -1) {
	        // Xóa hàng được chọn
	        model.removeRow(selectedRow);
	        capNhatSTT();
	        tinhTongCong();
	    } else {
	        JOptionPane.showMessageDialog(this, "Chọn một hàng để xóa.");
	    }
	}

	private void capNhatSTT() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    // Cập nhật lại STT sau khi xóa
	    for (int i = 0; i < model.getRowCount(); i++) {
	        model.setValueAt(i + 1, i, 0); // Cột STT ở index 0
	    }
	}
	private void themSP() {
	    String maSP = txtMaSP.getText().trim();
	    if (maSP.isEmpty()) {
	        JOptionPane.showMessageDialog(txtMaSP, "Mã sản phẩm không được để rỗng!");
	        return;
	    }

	    SanPham sanPham = sp.GetSanPham(maSP);

	    if (sanPham != null) {
	        // Lấy thông tin sản phẩm
	        String maSanPham = sanPham.getMaSP();
	        String tenSanPham = sanPham.getTenSP();
	        double donGia = sanPham.TinhGiaBan();
	        int soLuongTon = sanPham.getSlTonKho(); 

	        // Kiểm tra số lượng tồn
	        if (soLuongTon <= 0) {
	            JOptionPane.showMessageDialog(txtMaSP, "Sản phẩm đã hết hàng!");
	            return;
	        }

	        // Nếu sản phẩm đã đưa vào
	        boolean daTonTai = false;
	        for (int row = 0; row < model.getRowCount(); row++) {
	            String maSPTrongBang = (String) model.getValueAt(row, 1);
	            if (maSPTrongBang.equals(maSanPham)) {
	                // Nếu đã tồn tại, kiểm tra số lượng tồn
	                int soLuongHienTai = (int) model.getValueAt(row, 4);
	                if (soLuongHienTai >= soLuongTon) {
	                    JOptionPane.showMessageDialog(txtMaSP, "Sản phẩm đã hết hàng!");
	                    return;
	                }

	                // Tăng số lượng và cập nhật thành tiền
	                model.setValueAt(soLuongHienTai + 1, row, 4);

	                double thanhTienMoi = donGia * (soLuongHienTai + 1);
	                String formattedThanhTienMoi = decimalFormat.format(thanhTienMoi);
	                model.setValueAt(formattedThanhTienMoi, row, 5);

	                daTonTai = true;
	                break;
	            }
	        }

	        // Nếu sản phẩm chưa được thêm
	        if (!daTonTai) {
	            int soLuong = 1;
	            if (soLuong > soLuongTon) {
	                JOptionPane.showMessageDialog(txtMaSP, "Không đủ số lượng hàng!");
	                return;
	            }

	            double thanhTien = donGia * soLuong;

	            String formattedDonGia = decimalFormat.format(donGia);
	            String formattedThanhTien = decimalFormat.format(thanhTien);

	            model.addRow(new Object[]{
	                model.getRowCount() + 1, // STT
	                maSanPham,             // Mã sản phẩm
	                tenSanPham,            // Tên sản phẩm
	                formattedDonGia,                // Đơn giá (VNĐ)
	                soLuong,               // Số lượng
	                formattedThanhTien              // Thành tiền (VNĐ)
	            });
	        }

	        // Tính tổng cộng sau khi thêm sản phẩm
	        tinhTongCong();
	    } else {
	        JOptionPane.showMessageDialog(txtMaSP, "Mã sản phẩm không tồn tại!");
	    }
	}

	

	private void timSDT() {
		// TODO Auto-generated method stub
		String sdt = txtTim.getText().trim();
		String tenKHTimDuoc = KhachHangDAO.getKHBySDT(sdt).getTenKH();
	    if (tenKHTimDuoc.isEmpty()) {
	        // Số điện thoại không hợp lệ hoặc không tồn tại 
	        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ hoặc không tồn tại", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    } else {
	        txtKH.setText(tenKHTimDuoc);
	    }
	}
	
	private void tinhTongCong() {
	    double tongCong = 0.0;

	    for (int row = 0; row < model.getRowCount(); row++) {
	        // Lấy giá trị dạng String từ cột donGia và thanhTien
	        String strDonGia = (String) model.getValueAt(row, 3);
	        String strThanhTien = (String) model.getValueAt(row, 5);

	        // Chuyển đổi thành kiểu double
	        double donGia = Double.parseDouble(strDonGia.replace(" VNĐ", "").replace(",", ""));
	        double thanhTien = Double.parseDouble(strThanhTien.replace(" VNĐ", "").replace(",", ""));

	        tongCong += thanhTien;
	    }

	    float km = 0;
	    double tongTien = tongCong;

	    if (tongCong >= 1500000) {
	        km = (float) 0.05;
	        tongTien = tongCong - km*tongCong;
	    } 
	    if (tongCong > 4000000) {
	        km =  (float) 0.1;
	        tongTien =tongCong - tongCong*km;
	    }
	    
	    // Định dạng số
	    String formattedTongCong = decimalFormat.format(tongCong);
	    String formattedKM = decimalFormat.format(km*tongCong);
	    String formattedTongTien = decimalFormat.format(tongTien);

	    // Hiển thị chuỗi định dạng trên các nhãn
	    lblTongCongVND.setText(formattedTongCong + " VNĐ");
	    lblKMVND.setText(formattedKM + " VNĐ");
	    lblTongTienVND.setText(formattedTongTien + " VNĐ");
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		tinhTienTraLai();
		
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		tinhTienTraLai();
		
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		tinhTienTraLai();
		
	}

}
