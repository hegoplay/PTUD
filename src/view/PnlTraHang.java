package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import component.TblSPTraHang;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
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

public class PnlTraHang extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaHD;
	private JTextField txtMaPhieu;

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
		
		JLabel lblValueTongCong = new JLabel("679.000 VNĐ");
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
		
		JLabel lblValueKM = new JLabel("-33.500VNĐ");
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
		
		JLabel lblNewLabel_3 = new JLabel("636.500VNĐ\r\n");
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlValueTongTien.add(lblNewLabel_3, BorderLayout.CENTER);
		
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
		
		JLabel lblValueTienTL = new JLabel("326.500 VNĐ");
		lblValueTienTL.setForeground(Color.RED);
		lblValueTienTL.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		pnlValueTienTL.add(lblValueTienTL, BorderLayout.CENTER);
		
		JPanel pnlTongKetLine2 = new JPanel();
		pnlTongKetLine2.setBackground(MainFrame.clrTheme);
		pnlTongKet.add(pnlTongKetLine2);
		pnlTongKetLine2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pnlTongKetLine2_1 = new JPanel();
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
		
		JLabel lblValueNgTH = new JLabel("Nguyễn Huy Hoàng");
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
		
		JLabel lblValueNgayTra = new JLabel("20/10/2023");
		lblValueNgayTra.setBackground(MainFrame.clrTheme);
		lblValueNgayTra.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlValueNgayTra.add(lblValueNgayTra, BorderLayout.CENTER);
		
		JPanel pnlTongKetLine2_2 = new JPanel();
		pnlTongKetLine2_2.setBackground(MainFrame.clrTheme);
		pnlTongKetLine2.add(pnlTongKetLine2_2);
		pnlTongKetLine2_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnXuatPhieu = new JButton("Xuất phiếu trả hàng");
		btnXuatPhieu.setBackground(MainFrame.clrCyan4);
		btnXuatPhieu.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/print_icon.png")));
		btnXuatPhieu.setForeground(Color.WHITE);
		btnXuatPhieu.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlTongKetLine2_2.add(btnXuatPhieu);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(MainFrame.clrCyan4);
		btnLamMoi.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/refresh_icon.png")));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 17));
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
		
		JButton btnTimHD = new JButton("Tìm");
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
		
		JButton btnMaPhieu = new JButton("Tạo");
		btnMaPhieu.setForeground(Color.WHITE);
		btnMaPhieu.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/boxPlusWhite_icon.png")));
		btnMaPhieu.setBackground(MainFrame.clrCyan4);
		btnMaPhieu.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlMaPhieu.add(btnMaPhieu);
		
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
		
		JLabel lblTenKH = new JLabel("Lê Mậu Toàn");
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
		
		JLabel lblTenNV = new JLabel("Hoàng Thị Mỹ Linh");
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
		
		JLabel lblNgayHD = new JLabel("17/10/2023");
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
		pnlCTHD.setBorder(new TitledBorder(null, "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTables.add(pnlCTHD);
		pnlCTHD.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrHoaDon = new JScrollPane();
		scrHoaDon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlCTHD.add(scrHoaDon, BorderLayout.CENTER);
		
		TblSPTraHang tblCTHDTraHang = new TblSPTraHang();
		scrHoaDon.setViewportView(tblCTHDTraHang);
		
		JPanel pnlBtnTraHang = new JPanel();
		pnlBtnTraHang.setBackground(MainFrame.clrTblBg);
		pnlCTHD.add(pnlBtnTraHang, BorderLayout.SOUTH);
		pnlBtnTraHang.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnTraHang = new JButton("Trả Hàng");
		btnTraHang.setBackground(MainFrame.clrCyan4);
		btnTraHang.setForeground(Color.WHITE);
		btnTraHang.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/reply_icon.png")));
		btnTraHang.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnTraHang.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlBtnTraHang.add(btnTraHang);
		
		JPanel pnlCTTH = new JPanel();
		pnlCTTH.setBackground(MainFrame.clrTblBg);
		pnlCTTH.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi ti\u1EBFt tr\u1EA3 h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTables.add(pnlCTTH);
		pnlCTTH.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrCTTH = new JScrollPane();
		pnlCTTH.add(scrCTTH, BorderLayout.CENTER);
		
		TblSPTraHang tblCTTTTraHang = new TblSPTraHang();
		scrCTTH.setViewportView(tblCTTTTraHang);
		
		JPanel pnlBtnXoa = new JPanel();
		pnlBtnXoa.setBackground(MainFrame.clrTblBg);
		FlowLayout flowLayout = (FlowLayout) pnlBtnXoa.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnlCTTH.add(pnlBtnXoa, BorderLayout.SOUTH);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(PnlTraHang.class.getResource("/view/icon/gabage_icon.png")));
		btnXoa.setBackground(MainFrame.clrCyan4);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlBtnXoa.add(btnXoa);

	}

}
