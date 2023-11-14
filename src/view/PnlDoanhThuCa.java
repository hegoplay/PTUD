package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;

public class PnlDoanhThuCa extends JPanel implements ActionListener {
	private DefaultTableModel model;
	private JTable table;

	private static final long serialVersionUID = 1L;

	private String maNVLogin = MainFrame.nv.getMaNV();
	private DecimalFormat decimalFormat = new DecimalFormat("###,###,### VNĐ");
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private NhanVien nhanVienLogin = MainFrame.nv;
	private String maNV = "NV00000003";

	/**
	 * Create the panel.
	 */
	public PnlDoanhThuCa() {
		setBackground(MainFrame.clrTheme);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pnlHeader = new JPanel();
		add(pnlHeader);
		pnlHeader.setBackground(MainFrame.clrTheme);
		pnlHeader.setLayout(new GridLayout(2, 1, 10, 0));

		JPanel pnlMainContent = new JPanel();
		add(pnlMainContent);
		pnlMainContent.setBackground(MainFrame.clrTheme);
		pnlMainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20));
		pnlMainContent.setLayout(new BorderLayout());

		JPanel pnlTopContent = new JPanel();
		pnlTopContent.setBackground(MainFrame.clrTheme);
		pnlTopContent.setBorder(BorderFactory.createEmptyBorder(0, 0, -80, 0));
		pnlTopContent.setLayout(new BorderLayout());
		pnlHeader.add(pnlTopContent);

		JPanel pnlTopInfo = new JPanel();
		pnlTopInfo.setLayout(new GridLayout(1, 2, 5, 0));
		pnlTopInfo.setBackground(MainFrame.clrTheme);
		pnlTopInfo.setBorder(BorderFactory.createEmptyBorder(-80, 40, 0, 0));
		pnlTopContent.add(pnlTopInfo, BorderLayout.CENTER);

		JPanel pnlNV = new JPanel();
		pnlNV.setLayout(new GridLayout(1, 2, 5, 0));
		pnlNV.setBackground(MainFrame.clrTheme);
		pnlTopInfo.add(pnlNV);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNV.setBackground(MainFrame.clrTheme);
		pnlNV.add(lblTenNV);

		JLabel lblNV = new JLabel("vvvv");
		lblNV.setEnabled(false);
		lblNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNV.setBackground(MainFrame.clrTheme);
		pnlNV.add(lblNV);

		JPanel pnlNgay = new JPanel();
		pnlNgay.setLayout(new GridLayout(1, 2, 5, 0));
		pnlNgay.setBackground(MainFrame.clrTheme);
		pnlTopInfo.add(pnlNgay, BorderLayout.CENTER);

		JLabel lblNgay = new JLabel("Ngày thống kê:");
		lblNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgay.setBackground(MainFrame.clrTheme);
		pnlNgay.add(lblNgay);

		JLabel lbldate = new JLabel("");
		lbldate.setEnabled(false);
		lbldate.setBackground(MainFrame.clrTheme);
		lbldate.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlNgay.add(lbldate);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày tháng
		Date ngayHienHanh = new Date(System.currentTimeMillis()); // Lấy ngày hiện hành
		String ngayHienHanhFormatted = dateFormat.format(ngayHienHanh); // Định dạng ngày hiện hành

		lbldate.setText(ngayHienHanhFormatted);

		JPanel pnlBnt = new JPanel();
		pnlBnt.setBackground(MainFrame.clrTheme);
		pnlBnt.setBorder(BorderFactory.createEmptyBorder(30, 0, -20, 50));
		pnlTopContent.add(pnlBnt, BorderLayout.EAST);
		pnlBnt.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnXuat = new JButton("Xuất file");
		btnXuat.setForeground(new Color(255, 255, 255));
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXuat.setBackground(new Color(69, 129, 142));
		pnlBnt.add(btnXuat);

//Số liệu thống kê		
		JPanel pnlStatistic = new JPanel();
		pnlStatistic.setLayout(new GridLayout(1, 3, 100, 0));
		pnlStatistic.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
//		pnlStatistic.setBackground(MainFrame.clrBlue4);
		pnlStatistic.setBackground(MainFrame.clrTheme);
		pnlHeader.add(pnlStatistic);

		JPanel pnlDT = new JPanel();
//		pnlDT.setBounds(94, 64, 215, 107);
		pnlDT.setBackground(new Color(252, 223, 135));
		pnlDT.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pnlDT.setLayout(new BorderLayout());
		pnlStatistic.add(pnlDT);

		JLabel lblDoanhThu = new JLabel("Doanh thu");
//		lblDoanhThu.setBounds(54, 14, 106, 25);
		lblDoanhThu.setBorder(BorderFactory.createEmptyBorder(0, 65, 10, 0));
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlDT.add(lblDoanhThu, BorderLayout.NORTH);

		JLabel lblDTVND = new JLabel("0 VNĐ");
		lblDTVND.setHorizontalAlignment(SwingConstants.CENTER);
		lblDTVND.setBackground(new Color(240, 240, 240));
//		lblDTVND.setBounds(10, 55, 195, 27);
		lblDTVND.setForeground(new Color(215, 0, 0));
		lblDTVND.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlDT.add(lblDTVND, BorderLayout.CENTER);

//		
		JPanel pnlHD = new JPanel();
//		pnlHD.setBounds(417, 64, 215, 107);
		pnlHD.setBackground(new Color(252, 223, 135));
		pnlHD.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pnlHD.setLayout(new BorderLayout());
		pnlStatistic.add(pnlHD);

		JLabel lblHD = new JLabel("Hóa đơn đã lập");
//		lblHD.setBounds(35, 14, 157, 25);
		lblHD.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 0));
		lblHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlHD.add(lblHD, BorderLayout.NORTH);

		JLabel lblSoHD = new JLabel("0");
		lblSoHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoHD.setForeground(new Color(215, 0, 0));
		lblSoHD.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSoHD.setBackground(UIManager.getColor("Button.background"));
//		lblSoHD.setBounds(10, 55, 195, 27);
		pnlHD.add(lblSoHD, BorderLayout.CENTER);

		JPanel pnlSP = new JPanel();
//		pnlSP.setBounds(744, 64, 215, 107);
		pnlSP.setBackground(new Color(252, 223, 135));
		pnlSP.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		pnlSP.setLayout(new BorderLayout());
		pnlStatistic.add(pnlSP);

		JLabel lblSP = new JLabel("Sản phẩm đã bán");
		lblSP.setHorizontalAlignment(SwingConstants.CENTER);
//		lblSP.setBounds(10, 14, 195, 25);
		lblSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSP.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
		pnlSP.add(lblSP, BorderLayout.NORTH);

		JLabel lblSoSP = new JLabel("0");
		lblSoSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoSP.setForeground(new Color(215, 0, 0));
		lblSoSP.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSoSP.setBackground(UIManager.getColor("Button.background"));
		pnlSP.add(lblSoSP, BorderLayout.CENTER);

		JPanel pnlCTHD = new JPanel();
		pnlCTHD.setBackground(new Color(201, 228, 228));
		pnlCTHD.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách sản phẩm đã bán", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
//		pnlCTHD.setBounds(10, 200, 1034, 343);
		pnlCTHD.setLayout(new BorderLayout());
		pnlMainContent.add(pnlCTHD, BorderLayout.CENTER);

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

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlCTHD.add(scrollPane, BorderLayout.CENTER);

		btnXuat.addActionListener(this);
		String currentUsername = System.getProperty("user.name");
//        lblTenNV.setText(currentUsername);
		lblNV.setText("Hoàng Thị Mỹ Linh");
//		loadTable();
//        double tongDT = hdDAO.getDoanhThuNgayTheoMaNV(maNVLogin);
//		double tongDT = hdDAO.getDoanhThuNgayTheoMaNV(maNV);
//		String tongDTstr = decimalFormat.format(tongDT);
//		lblDTVND.setText(tongDTstr);
		int soLuongHD = 1;
		String soLuongHDStr = String.valueOf(soLuongHD);
		lblSoHD.setText(soLuongHDStr);
		int soLuongSP = 1;
		String soLuongSPstr = String.valueOf(soLuongSP);
		lblSoSP.setText(soLuongSPstr);
	}

	public void loadTable() throws Exception {
//      ArrayList<HoaDon> dsHD = hdDAO.getHoaDonByMaNVinToDay(maNVLogin);
//		ArrayList<HoaDon> dsHD = hdDAO.getHoaDonByMaNVinToDay(maNV); // ???
//		ArrayList<ChiTietHoaDon> cthd = hdDAO.getDSCTHDFromList(dsHD);
//		ArrayList<ChiTietHoaDon> ctSPdaban = hdDAO.processDSCTHD(cthd);
// Sắp xếp danh sách chi tiết hóa đơn theo số lượng giảm dần
//		Collections.sort(ctSPdaban, Comparator.comparingInt(ChiTietHoaDon::getSoLuong).reversed());
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		model.setRowCount(0);
//		int stt = 1;
//		for (ChiTietHoaDon ct : ctSPdaban) {
//			model.addRow(new Object[] { stt++, ct.getSanPham().getMaSP(), ct.getSanPham().getTenSP(),
//					decimalFormat.format(ct.getSanPham().TinhGiaBan()), ct.getSoLuong(),
//					decimalFormat.format(ct.getSoLuong() * ct.getSanPham().TinhGiaBan()) });
//		}
	}

//	public int laySoLuongHDDaLap() {
//		ArrayList<HoaDon> dsHD = hdDAO.getHoaDonByMaNVinToDay(maNV);
//		int soLuong = dsHD.size();
//		return soLuong;
//	}
//
//	public int getTongSoLuongDaBan() {
//		ArrayList<HoaDon> dsHD = hdDAO.getHoaDonByMaNVinToDay(maNV);
//		ArrayList<ChiTietHoaDon> cthd = hdDAO.getDSCTHDFromList(dsHD);
//		ArrayList<ChiTietHoaDon> ctSPdaban = hdDAO.processDSCTHD(cthd);
//		int tongSoLuong = 0;
//		for (ChiTietHoaDon chiTietHoaDon : ctSPdaban) {
//			tongSoLuong += chiTietHoaDon.getSoLuong();
//		}
//		return tongSoLuong;
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}