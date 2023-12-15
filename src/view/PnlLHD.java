package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.ImageIcon;
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
import dao.KhuyenMaiDAO;
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
	private JLabel lblTraLaiVND, lblTongTienVND, lblTongCongVND, lblKMVND, lblNgay;
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
	private double tienTraLai = 0;
	String userLogin = "admin";
	private float km;

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
		lblHoaDon.setBorder(new EmptyBorder(10, 10, 0, 10));
		pnlTopContent.add(lblHoaDon, BorderLayout.NORTH);
//Thông tin hóa đơn		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setLayout(new GridLayout(2, 1, 15, 0));
		pnlInfo.setBackground(MainFrame.clrTheme);
		pnlInfo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		pnlTopContent.add(pnlInfo, BorderLayout.CENTER);

		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setLayout(new GridLayout(1, 3, 10, 5));
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

		btnTim = new JButton("");
		btnTim.setBounds(370, 0, 40, 27);
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new Color(69, 129, 142));
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setIcon(new ImageIcon(PnlLHD.class.getResource("/view/icon/magnifying_glass_icon.png")));		
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
		txtMaHD.setBounds(172, 0, 230, 25);
		txtMaHD.setColumns(10);
		pnlMaHD.add(txtMaHD);

		btnTao = new JButton("Tạo");
		btnTao.setBounds(410, 0, 80, 27);
		btnTao.setForeground(new Color(255, 255, 255));
		btnTao.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTao.setBackground(new Color(69, 129, 142));
		pnlMaHD.add(btnTao);

		JPanel pnlInfo2 = new JPanel();
		pnlInfo2.setLayout(new GridLayout(1, 3, 10, 5));
		pnlInfo2.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
		pnlInfo2.setBackground(new Color(254, 250, 224));
		pnlInfo.add(pnlInfo2);

		JPanel pnlKH = new JPanel();
		pnlKH.setLayout(new GridLayout(1, 2, 0, 0));
		pnlKH.setBackground(MainFrame.clrTheme);
		pnlInfo2.add(pnlKH);

		JLabel lblKH = new JLabel("Tên khách hàng:");
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
		lblDate.setBounds(30, 0, 137, 19);
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
		pnlCTHD.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chi tiết hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMainContent.add(pnlCTHD, BorderLayout.CENTER);
		pnlCTHD.setLayout(new BorderLayout());

		JPanel pnlTopCTHD = new JPanel();
//		pnlTopCTHD.setLayout(new GridLayout(1, 2, 10 ,10));
		pnlTopCTHD.setLayout(new BorderLayout());
		pnlTopCTHD.setBackground(new Color(241, 231, 190));
		pnlTopCTHD.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		pnlCTHD.add(pnlTopCTHD, BorderLayout.NORTH);

		JPanel pnlSP = new JPanel();
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
		btnThem.setBounds(365, 0, 80, 27);
		pnlSP.add(btnThem);

		btnXoa = new JButton("");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoa.setBackground(new Color(69, 129, 142));
//		btnXoa.setBounds(872, 11, 90, 27);
		btnXoa.setBounds(872, 11, 35, 27);
		btnXoa.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/gabage_icon.png")));
		
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

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 50, 1014, 221);
		pnlMainCTHD.add(scrollPane, BorderLayout.CENTER);

//Buttom
		JPanel pnlButtom = new JPanel();
		pnlButtom.setLayout(new BorderLayout());
		pnlButtom.setBackground(MainFrame.clrTheme);
		pnlButtom.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
		add(pnlButtom, BorderLayout.SOUTH);

//Tính toán tiền	
		JPanel pnlTinhToan = new JPanel();
		pnlTinhToan.setLayout(new GridLayout(2, 3, 20, 15));
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
		lblTienDua.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTienDua.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlTKD.add(lblTienDua, BorderLayout.WEST);

		txtTienDua = new JTextField();
		txtTienDua.setColumns(10);
		pnlTKD.add(txtTienDua, BorderLayout.CENTER);

		JPanel pnlTTL = new JPanel();
		pnlTTL.setLayout(new BorderLayout());
		pnlTTL.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlTTL);

		JLabel lblTienTra = new JLabel("Số tiền trả lại:");
		lblTienTra.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTienTra.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlTTL.add(lblTienTra, BorderLayout.WEST);

		lblTraLaiVND = new JLabel("0 VNĐ");
		lblTraLaiVND.setForeground(new Color(215, 0, 0));
		lblTraLaiVND.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlTTL.add(lblTraLaiVND, BorderLayout.CENTER);

		JPanel pnlGC = new JPanel();
		pnlGC.setLayout(new BorderLayout());
		pnlGC.setBackground(MainFrame.clrTheme);
		pnlTinhToan.add(pnlGC);

		lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblGhiChu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pnlGC.add(lblGhiChu, BorderLayout.WEST);

		txtGhiChu = new JTextArea();
		txtGhiChu.setColumns(10);
		pnlGC.add(txtGhiChu, BorderLayout.CENTER);

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
		btnXuat.setIcon(new ImageIcon(PnlLHD.class.getResource("/view/icon/print_icon.png")));
		pnlAc1.add(btnXuat);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBackground(new Color(69, 129, 142));
		btnLamMoi.setIcon(new ImageIcon(PnlLHD.class.getResource("/view/icon/refresh_icon.png")));
		pnlAc1.add(btnLamMoi);

		txtNV.setText(MainFrame.nv.getTen());

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
				if (evt.getPropertyName().equals("text")) {
					String tienTraLaitxt = lblTienTra.getText();
					if (!tienTraLaitxt.isEmpty()) {
						double tienThua = Double.parseDouble(tienTraLaitxt);

						if (tienThua < 0) {
							btnXuat.setEnabled(false);
						} else {
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
			// không nhập số tiền khách đưa
			JOptionPane.showMessageDialog(txtTienDua, "Vui lòng nhập số tiền khách đưa!");
			return;
		}

		double tienKhachDua;
		try {
			tienKhachDua = Double.parseDouble(tienKDStr);
		} catch (NumberFormatException e) {
			// không phải số
			JOptionPane.showMessageDialog(txtTienDua, "Số tiền khách đưa không hợp lệ!");
			return;
		}

		String tongTienStr = lblTongTienVND.getText().replace(" VNĐ", "").replace(",", "");

		if (!tongTienStr.matches("\\d+(\\.\\d+)?")) {
			// định dạng của tổng tiền không đúng
			JOptionPane.showMessageDialog(lblTongTienVND, "Định dạng tổng tiền không hợp lệ!");
			return;
		}

		double tongTong = Double.parseDouble(tongTienStr);
		if (tienKhachDua >= tongTong) {
			tienTraLai = tienKhachDua - tongTong;
			String formattedTienTra = decimalFormat.format(tienTraLai) + " VNĐ";
			lblTraLaiVND.setText(formattedTienTra);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			timSDT();
		} else if (o.equals(btnTao)) {
			tao();
		} else if (o.equals(btnThem)) {
			themSP();
		} else if (o.equals(btnXoa)) {
			xoa();
		} else if (o.equals(btnXuat)) {
			try {
				if (HoaDonDAO.ThemHD(getThongTinCreateHD())) {
					JFileChooser fileChooser = new JFileChooser();

					// Path mặc định
					fileChooser.setCurrentDirectory(new File("C:\\Users\\Linh\\Desktop\\dsPDF\\hoaDon"));

					// Tên file mặc định
					File defaultFile = new File(fileChooser.getCurrentDirectory(), "hoaDon.pdf");
					fileChooser.setSelectedFile(defaultFile);

					// Chon vị trí lưu
					int userSelection = fileChooser.showSaveDialog(this);

					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();

						// Kiểm tra xem file đã tồn tại hay chưa
						if (fileToSave.exists()) {
							int result = JOptionPane.showConfirmDialog(this,
									"File đã tồn tại. Bạn có muốn ghi đè lên không?", "Xác nhận ghi đè",
									JOptionPane.YES_NO_OPTION);
							if (result != JOptionPane.YES_OPTION) {
								return;
							}
						}

						getThongTinCreateHD().XuatHoaDon(fileToSave.getAbsolutePath(), getThongTinCreateHD());
						JOptionPane.showMessageDialog(this, "Hóa đơn đã được xuất thành công!", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thất bại. Kiểm tra lại dữ liệu và thử lại.");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		}
	}

//Tạo chi tiết hóa đơn trong table CTHD
	private ArrayList<ChiTietHoaDon> taoDSChiTietHD() throws NumberFormatException, Exception {
		ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		for (int i = 0; i < table.getRowCount(); i++) {
			String maSP = table.getValueAt(i, 1).toString();
			String soLuong = table.getValueAt(i, 4).toString();
			SanPham newSP = SanPhamDAO.GetSanPham(maSP);
			ChiTietHoaDon cthd = new ChiTietHoaDon(newSP, Integer.parseInt(soLuong));
			ds.add(cthd);

		}
		return ds;
	}

//Tạo hóa đơn từ thông tin lấy trên màn hình
	private HoaDon getThongTinCreateHD() throws Exception {

		String maHD = txtMaHD.getText().trim();

		ArrayList<ChiTietHoaDon> dsCTHD = taoDSChiTietHD();

		if (dsCTHD.size() == 0) {
			throw new Exception("Hóa đơn không được rỗng");
		}

		LocalDateTime ngayLapHD = LocalDateTime.now();

		String sdt = txtTim.getText().trim();
//	    cái này phải tìm thấy số điện thoại của khách hàng
		if (sdt.equalsIgnoreCase("")) {
			throw new Exception("Nhân viên phải nhập số điện thoại của khách hàng");
		}
		String maKH = KhachHangDAO.getKHBySDT(sdt).getMaKH();
		KhachHang newKh = KhachHangDAO.getKhachHang(maKH);

		String tongCongStr = lblTongCongVND.getText();
		String tongCong = tongCongStr.replaceAll("[^0-9]", "");
		double gtTongCong = Double.parseDouble(tongCong);

		String khuyenMaiStr = lblKMVND.getText();

		// Loại bỏ các ký tự không phải số từ chuỗi

		String tienDuaStr = txtTienDua.getText();

		String td = tienDuaStr.replaceAll("[^0-9]", "");
		if (td.compareToIgnoreCase("") == 0) {
			throw new Exception("Tiền đưa không được rỗng");
		}
		double tienDua = Double.parseDouble(td);

		HoaDon newHoaDon = new HoaDon(maHD, ngayLapHD, MainFrame.nv, newKh, km, tienDua, dsCTHD);
				
		return newHoaDon;
	}

//Tạo mã HĐ
	private void tao() {
		// TODO Auto-generated method stub
		String maHD = null;
		try {
			maHD = HoaDonDAO.taoMaHD();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (maHD != null) {
			txtMaHD.setText(maHD);
		} else {
			JOptionPane.showMessageDialog(this, "Không thể tạo mã hóa đơn. Vui lòng thử lại.", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
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

		// Làm mới table
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
			model.setValueAt(i + 1, i, 0);
		}
	}

	// Tổng khuyến mãi khi ap dụng chương trình sale của Hóa đơn
	public static double gomKM = 0.0;
	public static double  tongCongTruSAlE =0.0;

	private void themSP() {
	    String maSP = txtMaSP.getText().trim();
	    if (maSP.isEmpty()) {
	        JOptionPane.showMessageDialog(txtMaSP, "Mã sản phẩm không được để rỗng!");
	        return;
	    }

	    SanPham sanPham = SanPhamDAO.GetSanPham(maSP);

	    if (SanPhamDAO.GetSanPham(maSP)!= null) {
	        // Lấy thông tin sản phẩm
	        String maSanPham = SanPhamDAO.GetSanPham(maSP).getMaSP();
	        String tenSanPham = SanPhamDAO.GetSanPham(maSP).getTenSP();
	        double donGia = SanPhamDAO.GetSanPham(maSP).TinhGiaBan();
	        int soLuongTon = SanPhamDAO.GetSanPham(maSP).getSlTonKho(); 

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
	                String formattedThanhTienMoi = decimalFormat.format(thanhTienMoi) +" VNĐ";
	                model.setValueAt(formattedThanhTienMoi, row, 5);

	                daTonTai = true;
	                
	                
		            if (KhuyenMaiDAO.coChuongTrinhSale(maSP)) {
		                String maKM = SanPhamDAO.layMaKhuyenMaiChoSanPham(maSP);

		                if (maKM != null) {
		                    // Lấy mức giảm từ chương trình KM
		                    double mucGiam = KhuyenMaiDAO.layMucGiamChoKhuyenMai(maKM);

		                    // cộng dồn vào biến gomKM
		                    double giamGia = 0.0;
		                    if (mucGiam < 1) {
		                        giamGia = donGia * mucGiam;
		                    } else {
		                        giamGia = mucGiam;
		                    }
		                    gomKM += giamGia;
		                    }
		                }
	                
	                
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
	                model.getRowCount() + 1, 
	                maSanPham,            
	                tenSanPham,          
	                formattedDonGia,                
	                soLuong,              
	                formattedThanhTien            
	            });
	            
	         // Kiểm tra chương trình sale trực tiếp từ KhuyenMaiDAO
	            if (KhuyenMaiDAO.coChuongTrinhSale(maSP)) {
	                // Lấy mã khuyến mãi cho sản phẩm
	                String maKM = SanPhamDAO.layMaKhuyenMaiChoSanPham(maSP);

	                if (maKM != null) {
	                    // Lấy mức giảm từ chương trình khuyến mãi
	                    double mucGiam = KhuyenMaiDAO.layMucGiamChoKhuyenMai(maKM);

	                    // cộng dồn vào biến gomKM
	                    double giamGia = 0.0;
	                    if (mucGiam < 1) {
	                        giamGia = donGia * mucGiam * soLuong;
	                    } else {
	                        giamGia = mucGiam * soLuong;
	                    }
	                    gomKM += giamGia;
	                    }
	                }
	            
	            
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

	    // Kiểm tra định dạng SĐT
	    if (!isValidPhoneNumber(sdt)) {
	        JOptionPane.showMessageDialog(this, "Định dạng số điện thoại không đúng! Hãy nhập lại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    String tenKHTimDuoc = KhachHangDAO.getKHBySDT(sdt).getTenKH();
	    if (tenKHTimDuoc.isEmpty()) {
	        // Số điện thoại không tồn tại 
	        JOptionPane.showMessageDialog(this, "Số điện thoại không tồn tại", "Thông báo", JOptionPane.WARNING_MESSAGE);

	    } else {
	        txtKH.setText(tenKHTimDuoc);
	    }
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches("094\\d{7}");
	}
	public static double tongCong = 0.0;
	public static double tongTien = 0.0;
	public static float kM = 0;
	public void tinhTongCong() {

		 double tongCong1 = 0.0;

	    for (int row = 0; row < model.getRowCount(); row++) {
	        // Lấy giá trị dạng String từ cột donGia và thanhTien
	        String strDonGia = (String) model.getValueAt(row, 3);
	        String strThanhTien = (String) model.getValueAt(row, 5);

	        // Chuyển đổi thành kiểu double
	        double donGia = Double.parseDouble(strDonGia.replace(" VNĐ", "").replace(",", ""));
	        double thanhTien = Double.parseDouble(strThanhTien.replace(" VNĐ", "").replace(",", ""));

	        tongCong1 += thanhTien;
	    }
	    tongCong= tongCong1;
	    tongCongTruSAlE = tongCong - gomKM;
	    
	    if (tongCongTruSAlE >= 1500000) {
	    	kM = (float) 0.05;
	        tongTien = tongCong - kM*tongCongTruSAlE;
	    }  else if (tongCongTruSAlE> 4000000) {
	    	kM =  (float) 0.1;
	        tongTien =tongCong - tongCongTruSAlE*kM;
	    } else {
		    tongTien = tongCongTruSAlE;
	    }
	    
	    // Định dạng số
	    String formattedTongCong = MainFrame.moneyFormatter.format(tongCong);
	    String formattedKM = MainFrame.moneyFormatter.format(kM*tongCongTruSAlE + gomKM);
	    String formattedTongTien = MainFrame.moneyFormatter.format(tongTien);
	    
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
	
	public static double getTongKM() {
        return kM*tongCongTruSAlE + gomKM;
    }
	public static double getTongCong() {
        return tongCongTruSAlE;
    }
	public static double getTongTien() {
        return tongTien;
    }
}
