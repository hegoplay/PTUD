package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;

import component.Nav;
import controller.ChuyenManHinhController;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

public class MainFrame {
	
	
	public static final Color clrCyan4 = new Color(69, 129, 142);
	public static final Color clrCyan3 = new Color(118,165,175);
	public static final Color clrTheme = new Color(254,250,224);
	public static final Color clrBtnHover = new Color(221,161,94);
	public static final Color clrTblBg = new Color(255,217,102);
	public static final Color clrGrey1 = new Color(51,51,51);
	public static final Color clrYellow2 = new Color(255,229,153);
	public static final Color clrCyan2 = new Color(162,196,201);
	public static final Color clrBlue4 = new Color(89,126,170);
	public static final Color clrBlue6 = new Color(7,55,99);
	
	public static final DateTimeFormatter timeFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyyy");
	
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNavBar = new JPanel();
		pnlNavBar.setBackground(new Color(58,90,64));
		pnlNavBar.setBorder(new EmptyBorder(12, 12, 12, 12));
		frame.getContentPane().add(pnlNavBar, BorderLayout.WEST);
		pnlNavBar.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pnlLogo = new JPanel();
		pnlLogo.setBorder(null);
		pnlLogo.setPreferredSize(new Dimension(173, 64));
		pnlNavBar.add(pnlLogo,BorderLayout.NORTH);
		
		ImageIcon logo = new ImageIcon(MainFrame.class.getResource("/view/icon/logo.png"));
		pnlLogo.setLayout(new BorderLayout(0, 0));
		JLabel lblLogo = new JLabel(logo,JLabel.CENTER);
		lblLogo.setLabelFor(pnlLogo);
		pnlLogo.add(lblLogo);
		
		
		JPanel pnlNavLists = new JPanel();
		pnlNavLists.setBackground(new Color(58,90,64));
		pnlNavLists.setBorder(new EmptyBorder(16, 0, 0, 0));
		pnlNavBar.add(pnlNavLists, BorderLayout.CENTER);
		pnlNavLists.setLayout(new GridLayout(9, 1, 0, 15));
		
		JPanel pnlBanHang = new JPanel();
		pnlBanHang.setBackground(clrTheme);
		pnlNavLists.add(pnlBanHang);
		pnlBanHang.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBanHang = new JLabel("Bán Hàng");
		lblBanHang.setLabelFor(pnlBanHang);
		lblBanHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanHang.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/cart_icon.png")));
		lblBanHang.setForeground(Color.DARK_GRAY);
		lblBanHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlBanHang.add(lblBanHang);
		
		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setBackground(clrTheme);
		pnlNavLists.add(pnlKhachHang);
		pnlKhachHang.setLayout(new BorderLayout(0, 0));
		
		JLabel lblKhachHang = new JLabel("Khách Hàng");
		lblKhachHang.setLabelFor(pnlKhachHang);
		lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/khachHang_icon.png")));
		lblKhachHang.setForeground(Color.DARK_GRAY);
		lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlKhachHang.add(lblKhachHang);
		
		JPanel pnlSanPham = new JPanel();
		pnlSanPham.setBackground(clrTheme);
		pnlNavLists.add(pnlSanPham);
		pnlSanPham.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSanPham = new JLabel("Sản Phẩm");
		lblSanPham.setLabelFor(pnlSanPham);
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPham.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/clothes_icon.png")));
		lblSanPham.setForeground(Color.DARK_GRAY);
		lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlSanPham.add(lblSanPham);
		
		JPanel pnlNhaCungCap = new JPanel();
		pnlNhaCungCap.setBackground(clrTheme);
		pnlNavLists.add(pnlNhaCungCap);
		pnlNhaCungCap.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setLabelFor(pnlNhaCungCap);
		lblNhaCungCap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhaCungCap.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/boxes_icon.png")));
		lblNhaCungCap.setForeground(Color.DARK_GRAY);
		lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlNhaCungCap.add(lblNhaCungCap);
		
		JPanel pnlNhanVien = new JPanel();
		pnlNhanVien.setBackground(clrTheme);
		pnlNavLists.add(pnlNhanVien);
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNhanVien = new JLabel("Nhân Viên");
		lblNhanVien.setLabelFor(pnlNhanVien);
		lblNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanVien.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/nhanVien_icon.png")));
		lblNhanVien.setForeground(Color.DARK_GRAY);
		lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlNhanVien.add(lblNhanVien);
		
		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setBackground(clrTheme);
		pnlNavLists.add(pnlThongKe);
		pnlThongKe.setLayout(new BorderLayout(0, 0));
		
		JLabel lblThngK = new JLabel("Thống Kê");
		lblThngK.setLabelFor(pnlThongKe);
		lblThngK.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngK.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/thongKe_icon.png")));
		lblThngK.setForeground(Color.DARK_GRAY);
		lblThngK.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlThongKe.add(lblThngK);
		
		JPanel pnlTraHang = new JPanel();
		pnlTraHang.setBackground(clrTheme);
		pnlNavLists.add(pnlTraHang);
		pnlTraHang.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTraHang = new JLabel("Trả Hàng");
		lblTraHang.setLabelFor(pnlTraHang);
		lblTraHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTraHang.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/traHang_icon.png")));
		lblTraHang.setForeground(Color.DARK_GRAY);
		lblTraHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlTraHang.add(lblTraHang);
		
		JPanel pnlHoTro = new JPanel();
		pnlHoTro.setBackground(clrTheme);
		pnlNavLists.add(pnlHoTro);
		pnlHoTro.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHoTro = new JLabel("Hỗ Trợ");
		lblHoTro.setLabelFor(pnlHoTro);
		lblHoTro.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTro.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/hoTro_icon.png")));
		lblHoTro.setForeground(Color.DARK_GRAY);
		lblHoTro.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlHoTro.add(lblHoTro);

		Image imgBG = Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/view/icon/background_img.png"));
		
		JPanel pnlCenter = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(imgBG, 0, 0, this);
			}
		};
		pnlCenter.setBackground(Color.black);
		pnlCenter.setBorder(new EmptyBorder(15, 15, 15, 15));
		pnlCenter.setPreferredSize(new Dimension(1088,694));
		frame.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlLogout = new JPanel();
		pnlNavBar.add(pnlLogout, BorderLayout.SOUTH);
		
		JPanel pnlContent = new JPanel();
		pnlCenter.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new CardLayout(0, 0));
		
		PnlThongKe pnlThongKeContent = new PnlThongKe();
		pnlContent.add(pnlThongKeContent, "Thong Ke");
		
		PnlTraHang pnlTraHangContent = new PnlTraHang();
		pnlContent.add(pnlTraHangContent, "Tra Hang");
		
		PnlNhanVien pnlNhanVienContent = new PnlNhanVien();
		pnlContent.add(pnlNhanVienContent, "Nhan Vien");
		
		PnlNhaCC pnlNhaCungCapContent = new PnlNhaCC();
		pnlContent.add(pnlNhaCungCapContent,"Nha Cung Cap");
		
		JLabel lblTitle = new JLabel("New label");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 60));
		pnlCenter.add(lblTitle, BorderLayout.NORTH);
		
		ChuyenManHinhController controller = new ChuyenManHinhController(pnlContent,clrTheme,clrBtnHover);
		controller.setLbl(lblTitle);
		
		controller.setView("Thong Ke",pnlThongKe, lblThngK);
		
		List<Nav> listItem = new ArrayList<>();
		
		listItem.add(new Nav("Thong Ke",pnlThongKe,lblThngK));
		listItem.add(new Nav("Tra Hang",pnlTraHang,lblTraHang));
		listItem.add(new Nav("Nhan Vien", pnlNhanVien, lblNhanVien));
		listItem.add(new Nav("Nha Cung Cap",pnlNhaCungCap,lblNhaCungCap));
		
		controller.setEvent(listItem);
		
	}
	
	
	
}
