package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.EmptyBorder;

import component.TblSPTraHang;
import controller.ToPDFController;
import dao.HoaDonDAO;
import dao.SanPhamDAO;
import dao.TraHangDAO;
import entity.ChiTietHoaDon;
import entity.ChiTietTraHang;
import entity.HoaDon;
import entity.NguoiQuanLy;
import entity.PhieuTraHang;
import entity.SanPham;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PnlTraHang extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaHD;
	private JTextField txtMaPhieu;
	private JLabel lblTenNV;
	private JLabel lblTenKH;
	private JLabel lblNgayHD;
	private TblSPTraHang tblCTHDTraHang;
	private TblSPTraHang tblCTTTTraHang;
	private JPanel pnlBtnXoa;
	private JButton btnTraHang;
	private JButton btnXoa;
	private JLabel lblValueTongCong;
	private JLabel lblValueKM;
	private JLabel lblValueTongTien;
	private JLabel lblValueTienTL;
	private JButton btnXuatPhieu;
	private JButton btnLamMoi;
	private JLabel lblValueNgTH;
	private JLabel lblValueNgayTra;
	private JButton btnTimHD;
	private HoaDon hd;
	private PhieuTraHang pth;
	private JButton btnMaPhieu;

	/**
	 * Create the panel.
	 */
	public PnlTraHang() {
		setBackground(MainFrame.clrTheme);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(MainFrame.clrTheme);
		pnlTitle.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(pnlTitle, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("PHIẾU TRẢ HÀNG");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		pnlTitle.add(lblTitle);

		JPanel pnlTongKet = new JPanel();
		pnlTongKet.setBackground(MainFrame.clrTheme);
		pnlTongKet.setBorder(new EmptyBorder(20, 50, 20, 50));
		add(pnlTongKet, BorderLayout.SOUTH);
		pnlTongKet.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pnlTongKetLine1 = new JPanel();
		pnlTongKetLine1.setBackground(MainFrame.clrTheme);
		pnlTongKet.add(pnlTongKetLine1);
		pnlTongKetLine1.setLayout(new BoxLayout(pnlTongKetLine1, BoxLayout.X_AXIS));

		JPanel pnlLabelTongCong = new JPanel();
		pnlLabelTongCong.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlLabelTongCong);
		pnlLabelTongCong.setLayout(new BorderLayout(0, 0));

		JLabel lblLabelTongCong = new JLabel("Tổng cộng");
		lblLabelTongCong.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabelTongCong.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlLabelTongCong.add(lblLabelTongCong, BorderLayout.CENTER);

		JPanel pnlValueTongCong = new JPanel();
		pnlValueTongCong.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlValueTongCong);
		pnlValueTongCong.setLayout(new BorderLayout(0, 0));

		lblValueTongCong = new JLabel("679.000 VNĐ");
		lblValueTongCong.setEnabled(false);
		lblValueTongCong.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlValueTongCong.add(lblValueTongCong);

		JPanel pnlLabelKM = new JPanel();
		pnlLabelKM.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlLabelKM);
		pnlLabelKM.setLayout(new BorderLayout(0, 0));

		JLabel lblLabelKM = new JLabel("Khuyến Mãi");
		lblLabelKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlLabelKM.add(lblLabelKM);

		JPanel pnlValueKM = new JPanel();
		pnlValueKM.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlValueKM);
		pnlValueKM.setLayout(new BorderLayout(0, 0));

		lblValueKM = new JLabel("-33.500VNĐ");
		lblValueKM.setEnabled(false);
		lblValueKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlValueKM.add(lblValueKM, BorderLayout.CENTER);

		JPanel pnlLabelTongTien = new JPanel();
		pnlLabelTongTien.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlLabelTongTien);
		pnlLabelTongTien.setLayout(new BorderLayout(0, 0));

		JLabel lblLabelTongTien = new JLabel("Tổng tiền:");
		lblLabelTongTien.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlLabelTongTien.add(lblLabelTongTien, BorderLayout.CENTER);

		JPanel pnlValueTongTien = new JPanel();
		pnlValueTongTien.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlValueTongTien);
		pnlValueTongTien.setLayout(new BorderLayout(0, 0));

		lblValueTongTien = new JLabel("636.500VNĐ\r\n");
		lblValueTongTien.setEnabled(false);
		lblValueTongTien.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlValueTongTien.add(lblValueTongTien, BorderLayout.CENTER);

		JPanel pnlLabelTienTL = new JPanel();
		pnlLabelTienTL.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlLabelTienTL);
		pnlLabelTienTL.setLayout(new BorderLayout(0, 0));

		JLabel lblLabelTienTL = new JLabel("Tiền trả lại:");
		lblLabelTienTL.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlLabelTienTL.add(lblLabelTienTL, BorderLayout.CENTER);

		JPanel pnlValueTienTL = new JPanel();
		pnlValueTienTL.setBackground(MainFrame.clrTheme);
		pnlTongKetLine1.add(pnlValueTienTL);
		pnlValueTienTL.setLayout(new BorderLayout(0, 0));

		lblValueTienTL = new JLabel("326.500 VNĐ");
		lblValueTienTL.setForeground(Color.RED);
		lblValueTienTL.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		pnlValueTienTL.add(lblValueTienTL, BorderLayout.CENTER);

		JPanel pnlTongKetLine2 = new JPanel();
		pnlTongKetLine2.setBackground(MainFrame.clrTheme);
		pnlTongKet.add(pnlTongKetLine2);
		pnlTongKetLine2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlTongKetLine2_1 = new JPanel();
		pnlTongKetLine2_1.setBackground(MainFrame.clrTheme);
		pnlTongKetLine2.add(pnlTongKetLine2_1);
		pnlTongKetLine2_1.setLayout(new BoxLayout(pnlTongKetLine2_1, BoxLayout.X_AXIS));

		JPanel pnlLabelNgTH = new JPanel();
		pnlLabelNgTH.setBackground(MainFrame.clrTheme);
		pnlTongKetLine2_1.add(pnlLabelNgTH);
		pnlLabelNgTH.setLayout(new BorderLayout(0, 0));

		JLabel lblLabelNgTH = new JLabel("Người thực hiện:");
		lblLabelNgTH.setBackground(MainFrame.clrTheme);
		lblLabelNgTH.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlLabelNgTH.add(lblLabelNgTH, BorderLayout.CENTER);

		JPanel pnlValueNgTH = new JPanel();
		pnlValueNgTH.setBackground(MainFrame.clrTheme);
		pnlTongKetLine2_1.add(pnlValueNgTH);
		pnlValueNgTH.setLayout(new BorderLayout(0, 0));

		lblValueNgTH = new JLabel("Nguyễn Huy Hoàng");
		lblValueNgTH.setBackground(MainFrame.clrTheme);
		lblValueNgTH.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		pnlValueNgTH.add(lblValueNgTH, BorderLayout.CENTER);

		JPanel pnlLabelNgayTra = new JPanel();
		pnlLabelNgayTra.setBackground(MainFrame.clrTheme);
		pnlTongKetLine2_1.add(pnlLabelNgayTra);
		pnlLabelNgayTra.setLayout(new BorderLayout(0, 0));

		JLabel lblLabelNgayTra = new JLabel("Ngày Trả");
		lblLabelNgayTra.setBackground(MainFrame.clrTheme);
		lblLabelNgayTra.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlLabelNgayTra.add(lblLabelNgayTra, BorderLayout.CENTER);

		JPanel pnlValueNgayTra = new JPanel();
		pnlValueNgayTra.setBackground(MainFrame.clrTheme);
		pnlTongKetLine2_1.add(pnlValueNgayTra);
		pnlValueNgayTra.setLayout(new BorderLayout(0, 0));

		lblValueNgayTra = new JLabel("20/10/2023");
		lblValueNgayTra.setBackground(MainFrame.clrTheme);
		lblValueNgayTra.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlValueNgayTra.add(lblValueNgayTra, BorderLayout.CENTER);

		JPanel pnlTongKetLine2_2 = new JPanel();
		pnlTongKetLine2_2.setBackground(MainFrame.clrTheme);
		pnlTongKetLine2.add(pnlTongKetLine2_2);
		pnlTongKetLine2_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnMaPhieu = new JButton("Tạo");
		pnlTongKetLine2_2.add(btnMaPhieu);
		btnMaPhieu.setForeground(Color.WHITE);
		btnMaPhieu.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/boxPlusWhite_icon.png")));
		btnMaPhieu.setBackground(MainFrame.clrCyan4);
		btnMaPhieu.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnXuatPhieu = new JButton("Tạo và xuất phiếu");
		btnXuatPhieu.setBackground(MainFrame.clrCyan4);
		btnXuatPhieu.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/print_icon.png")));
		btnXuatPhieu.setForeground(Color.WHITE);
		btnXuatPhieu.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlTongKetLine2_2.add(btnXuatPhieu);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(MainFrame.clrCyan4);
		btnLamMoi.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/refresh_icon.png")));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlTongKetLine2_2.add(btnLamMoi);

		JPanel pnlCenter = new JPanel();
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setBackground(MainFrame.clrTheme);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		JPanel pnlInfo = new JPanel();
		pnlInfo.setBackground(MainFrame.clrTheme);
		pnlInfo.setBorder(new EmptyBorder(0, 100, 0, 100));
		pnlCenter.add(pnlInfo, BorderLayout.NORTH);
		pnlInfo.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pnlInput = new JPanel();
		pnlInput.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlInput);
		pnlInput.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel pnlMaHD = new JPanel();
		pnlMaHD.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnlMaHD.setBackground(MainFrame.clrTheme);
		pnlInput.add(pnlMaHD);
		pnlMaHD.setLayout(new BoxLayout(pnlMaHD, BoxLayout.X_AXIS));

		JLabel lblMaHD = new JLabel("Mã hóa đơn");
		lblMaHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlMaHD.add(lblMaHD);

		Component rigidArea = Box.createRigidArea(new Dimension(35, 20));
		pnlMaHD.add(rigidArea);

		txtMaHD = new JTextField();
		lblMaHD.setLabelFor(txtMaHD);
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMaHD.setColumns(10);
		pnlMaHD.add(txtMaHD);
		txtMaHD.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(16);
		pnlMaHD.add(horizontalStrut);

		btnTimHD = new JButton("Tìm");
		btnTimHD.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/magnifying_glass_icon.png")));
		btnTimHD.setBackground(MainFrame.clrCyan4);
		btnTimHD.setForeground(Color.WHITE);
		btnTimHD.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlMaHD.add(btnTimHD);

		JPanel pnlMaPhieu = new JPanel();
		pnlMaPhieu.setBackground(MainFrame.clrTheme);
		pnlInput.add(pnlMaPhieu);
		pnlMaPhieu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblMaPhieuTra = new JLabel("Mã phiếu trả");
		lblMaPhieuTra.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMaPhieuTra.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlMaPhieu.add(lblMaPhieuTra);

		txtMaPhieu = new JTextField();
		txtMaPhieu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlMaPhieu.add(txtMaPhieu);
		txtMaPhieu.setColumns(10);

		JPanel pnlList = new JPanel();
		pnlList.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlList);
		pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.X_AXIS));

		JPanel pnlTitleKH = new JPanel();
		pnlTitleKH.setBackground(MainFrame.clrTheme);
		pnlList.add(pnlTitleKH);
		pnlTitleKH.setLayout(new BorderLayout(0, 0));

		JLabel lblTitleKH = new JLabel("Khách hàng:");
		lblTitleKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitleKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlTitleKH.add(lblTitleKH, BorderLayout.CENTER);

		JPanel pnlTenKH = new JPanel();
		pnlTenKH.setBackground(MainFrame.clrTheme);
		pnlList.add(pnlTenKH);
		pnlTenKH.setLayout(new BorderLayout(0, 0));

		lblTenKH = new JLabel("Lê Mậu Toàn");
		lblTenKH.setEnabled(false);
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		pnlTenKH.add(lblTenKH, BorderLayout.CENTER);

		JPanel pnlTitleNV = new JPanel();
		pnlTitleNV.setBackground(MainFrame.clrTheme);
		pnlList.add(pnlTitleNV);
		pnlTitleNV.setLayout(new BorderLayout(0, 0));

		JLabel lblTitleNV = new JLabel("NV lập hóa đơn:");
		lblTitleNV.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlTitleNV.add(lblTitleNV, BorderLayout.CENTER);

		JPanel pnlTenNV = new JPanel();
		pnlTenNV.setBackground(MainFrame.clrTheme);
		pnlList.add(pnlTenNV);
		pnlTenNV.setLayout(new BorderLayout(0, 0));

		lblTenNV = new JLabel("Hoàng Thị Mỹ Linh");
		lblTenNV.setEnabled(false);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		pnlTenNV.add(lblTenNV, BorderLayout.CENTER);

		JPanel pnlNgayLapHD = new JPanel();
		pnlNgayLapHD.setBackground(MainFrame.clrTheme);
		pnlList.add(pnlNgayLapHD);
		pnlNgayLapHD.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Ngày lập hóa đơn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlNgayLapHD.add(lblNewLabel, BorderLayout.CENTER);

		JPanel pnlNgayHD = new JPanel();
		pnlNgayHD.setBackground(MainFrame.clrTheme);
		pnlList.add(pnlNgayHD);
		pnlNgayHD.setLayout(new BorderLayout(0, 0));

		lblNgayHD = new JLabel("17/10/2023");
		lblNgayHD.setEnabled(false);
		lblNgayHD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		pnlNgayHD.add(lblNgayHD, BorderLayout.CENTER);

		JPanel pnlTables = new JPanel();
		pnlTables.setBackground(MainFrame.clrTheme);
		pnlTables.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnlCenter.add(pnlTables, BorderLayout.CENTER);
		pnlTables.setLayout(new GridLayout(1, 2, 10, 0));

		JPanel pnlCTHD = new JPanel();
		pnlCTHD.setBackground(MainFrame.clrTblBg);
		pnlCTHD.setBorder(new TitledBorder(null, "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlTables.add(pnlCTHD);
		pnlCTHD.setLayout(new BorderLayout(0, 0));

		JScrollPane scrHoaDon = new JScrollPane();
		scrHoaDon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlCTHD.add(scrHoaDon, BorderLayout.CENTER);

		tblCTHDTraHang = new TblSPTraHang();
		scrHoaDon.setViewportView(tblCTHDTraHang);

		JPanel pnlBtnTraHang = new JPanel();
		pnlBtnTraHang.setBackground(MainFrame.clrTblBg);
		pnlCTHD.add(pnlBtnTraHang, BorderLayout.SOUTH);
		pnlBtnTraHang.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnTraHang = new JButton("Trả Hàng");
		btnTraHang.setBackground(MainFrame.clrCyan4);
		btnTraHang.setForeground(Color.WHITE);
		btnTraHang.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/reply_icon.png")));
		btnTraHang.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnTraHang.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBtnTraHang.add(btnTraHang);

		JPanel pnlCTTH = new JPanel();
		pnlCTTH.setBackground(MainFrame.clrTblBg);
		pnlCTTH.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chi ti\u1EBFt tr\u1EA3 h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTables.add(pnlCTTH);
		pnlCTTH.setLayout(new BorderLayout(0, 0));

		JScrollPane scrCTTH = new JScrollPane();
		pnlCTTH.add(scrCTTH, BorderLayout.CENTER);

		tblCTTTTraHang = new TblSPTraHang();
		scrCTTH.setViewportView(tblCTTTTraHang);

		pnlBtnXoa = new JPanel();
		pnlBtnXoa.setBackground(MainFrame.clrTblBg);
		FlowLayout flowLayout = (FlowLayout) pnlBtnXoa.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnlCTTH.add(pnlBtnXoa, BorderLayout.SOUTH);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/gabage_icon.png")));
		btnXoa.setBackground(MainFrame.clrCyan4);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlBtnXoa.add(btnXoa);

//		thiet lap cac rang buoc cua cac component	

		txtMaPhieu.setEditable(false);

//		thiet lap ngay tra = ngay hom nay

		lblValueNgayTra.setText(LocalDateTime.now().format(MainFrame.timeFormatter));
		lblValueNgTH.setText(MainFrame.nv.getTen());
//		thiet lap su kien cho cac btn
		btnLamMoi.addActionListener(this);
		btnTimHD.addActionListener(this);
		txtMaHD.addKeyListener(this);
		btnTraHang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnMaPhieu.addActionListener(this);
		btnXuatPhieu.addActionListener(this);
		clearFileds();

	}

	public void clearFileds() {
		tblCTHDTraHang.removeAllRow();
		tblCTTTTraHang.removeAllRow();
		txtMaHD.setText("");
		txtMaPhieu.setText("");
		lblValueTongTien.setText("");
		lblValueKM.setText("");
		lblValueTienTL.setText("");
		lblValueTongCong.setText("");
		lblTenKH.setText("");
		lblNgayHD.setText("");
		lblTenNV.setText("");
		hd = null;
		pth = null;
//		lblValueNgTH.setText("");
//		lblValueNgayTra.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		try {
			if (o == btnLamMoi) {
				clearFileds();
			}
			if (o == btnTimHD) {

				LoadList();

			}
			if (o == btnTraHang) {

				reduceItem();
			}
			if (o == btnXoa) {
				deleteItem();
			}
			if (o == btnMaPhieu) {
				TraHangDAO.ThemPhieuTraHang(pth);
				JOptionPane.showMessageDialog(this, "Thêm phiếu thành công", "Thông báo thành công",
						JOptionPane.OK_OPTION);
				clearFileds();
			}
			if (o == btnXuatPhieu) {
				if (pth == null) {
					throw new Exception("Chưa có phiếu trả hàng");
				}
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(MainFrame.PdfPath));
				fileChooser.setDialogTitle("Chọn vị trí muốn lưu");
				
				
				int userSelection = fileChooser.showSaveDialog(this);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					TraHangDAO.ThemPhieuTraHang(pth);
					JOptionPane.showMessageDialog(this, "Thêm phiếu thành công", "Thông báo thành công",
							JOptionPane.OK_OPTION);
					pth.XuatPhieuTraHang(fileToSave.getAbsolutePath());
					clearFileds();
				}
			}
			LoadData();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
//			e1.printStackTrace();
		}

	}

	private void deleteItem() throws Exception {
		// TODO Auto-generated method stub
		int[] row = tblCTTTTraHang.getSelectedRows();
		if (row.length <= 0) {
			throw new Exception("Chưa có sản phẩm nào được chọn");
		}
		int slMuonTra = 0;
		try {
			slMuonTra = Integer.parseInt(JOptionPane.showInputDialog("Xin mời nhập số lượng hàng muốn xóa"));

		} catch (NumberFormatException ne) {
			throw new Exception("Số không đúng định dạng");
		}

		for (int i : row) {
			if (pth.getDsChiTiet().get(i).getSoLuongSP() < slMuonTra) {
				throw new Exception("Số lượng muốn trả lớn hơn số lượng sản phẩm hiện có");
			}
			SanPham sp = new SanPham(pth.getDsChiTiet().get(i).getSanPham().getMaSP());
			// TODO: tang so luong san pham ben hd và giảm bên trả hàng
			// nếu slSp trả về không xóa khỏi trả hàng

			hd.getDsCTHD().get(hd.getDsCTHD().indexOf(new ChiTietHoaDon(sp))).tangSoLuong(slMuonTra);

			pth.getDsChiTiet().get(pth.getDsChiTiet().indexOf(new ChiTietTraHang(sp))).tangSoLuong(-slMuonTra);

			if (pth.getDsChiTiet().get(pth.getDsChiTiet().indexOf(new ChiTietTraHang(sp))).getSoLuongSP() == 0) {
				pth.getDsChiTiet().remove(pth.getDsChiTiet().indexOf(new ChiTietTraHang(sp)));
			}
		}

	}

	private void LoadList() throws Exception {
		// TODO Auto-generated method stub
		hd = HoaDonDAO.GetHoaDon(txtMaHD.getText());
		if (hd == null) {
			throw new Exception("Hóa đơn không tồn tại");
		}
		pth = new PhieuTraHang("TH" + txtMaHD.getText().substring(2), hd, LocalDate.now(), (NguoiQuanLy)MainFrame.nv,
				hd.getKhachHang(), new ArrayList<>());
		if (TraHangDAO.KiemTraTTPhieuTra("TH" + txtMaHD.getText().substring(2))) {
			pth = null;
			throw new Exception("Phiếu trả tồn tại");
		}
		// bo khach hang di

	}

	private void reduceItem() throws Exception {
		// TODO đẩy 1 dữ liệu từ cthd sang ctth và sau đó load lại
		int[] row = tblCTHDTraHang.getSelectedRows();
		if (row.length <= 0) {
			throw new Exception("Chưa có sản phẩm nào được chọn");
		}
		int slMuonTra = 0;
		try {
			slMuonTra = Integer.parseInt(JOptionPane.showInputDialog("Xin mời nhập số lượng hàng muốn trả"));

		} catch (NumberFormatException ne) {
			throw new Exception("Số không đúng định dạng");
		}

		for (int i : row) {
			if (hd.getDsCTHD().get(i).getSoLuong() < slMuonTra) {
				throw new Exception("Số lượng hóa đơn hiện có lớn hơn hoặc bằng số lượng sản phẩm muốn trả về");
			}
			if(slMuonTra + hd.getDsCTHD().get(i).getSanPham().getSlTonKho() > 100) {
				throw new Exception("Số lượng sản phẩm muốn trả về kho bị quá tải");
			}
			SanPham sp = new SanPham(hd.getDsCTHD().get(i).getSanPham().getMaSP());
			// kiem tra xem ctth co chua
			// neu chua them vao
			if (!pth.getDsChiTiet().contains(new ChiTietTraHang(sp))) {
				ChiTietHoaDon cthd = hd.getDsCTHD().get(i);
				ChiTietTraHang chiTietTraHang = new ChiTietTraHang(cthd.getSanPham(), slMuonTra);
				pth.ThemCTTH(chiTietTraHang);

			} else {
				// tang so luong san pham muon tra
				pth.getDsChiTiet().get(pth.getDsChiTiet().indexOf(new ChiTietTraHang(sp))).setSoLuongSP(
						pth.getDsChiTiet().get(pth.getDsChiTiet().indexOf(new ChiTietTraHang(sp))).getSoLuongSP()
								+ slMuonTra);
			}
			hd.getDsCTHD().get(i).tangSoLuong(-slMuonTra);
			;

		}

	}

	private void LoadData() {
		DecimalFormat formatter = new DecimalFormat("###,##0.00");
		tblCTHDTraHang.removeAllRow();
		tblCTTTTraHang.removeAllRow();
		try {

			if (hd != null) {
				ArrayList<ChiTietHoaDon> cthd = hd.getDsCTHD();
				for (int i = 0; i < cthd.size(); i++) {
					ChiTietHoaDon ct = cthd.get(i);
					tblCTHDTraHang.addRow(String.format("%03d", i), ct.getSanPham().getMaSP(),
							ct.getSanPham().getTenSP(), ct.getSanPham().TinhGiaBan(), ct.getSoLuong(),
							ct.getSoLuong() * ct.getSanPham().TinhGiaBan());
				}
				lblTenKH.setText(hd.getKhachHang().getTenKH());
				lblTenNV.setText(hd.getNhanVien().getTen());
				lblNgayHD.setText(hd.getNgayLapHD().format(DateTimeFormatter.ofPattern("dd/MM/yyyyy")));
				txtMaPhieu.setText("TH" + txtMaHD.getText().substring(2));
				lblValueTongCong.setText(formatter.format(hd.TinhThanhTien()) + "VNĐ");
				lblValueKM.setText("-" + formatter.format(hd.TinhGTKhuyenMai()) + "VNĐ");
				lblValueTongTien.setText(formatter.format(hd.TinhTongTien()) + "VNĐ");
			}

			if (pth != null) {
				ArrayList<ChiTietTraHang> ctth = pth.getDsChiTiet();
				for (int i = 0; i < ctth.size(); i++) {
					ChiTietTraHang ct = ctth.get(i);
					tblCTTTTraHang.addRow(String.format("%03d", i), ct.getSanPham().getMaSP(),
							ct.getSanPham().getTenSP(), ct.getSanPham().TinhGiaBan(), ct.getSoLuongSP(),
							ct.getSoLuongSP() * ct.getSanPham().TinhGiaBan());
				}
				lblValueTienTL.setText(new DecimalFormat("###,##0.00").format(pth.TinhTienTra()) + "VNĐ");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				LoadList();
				LoadData();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
