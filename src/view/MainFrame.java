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
import dao.NhanVienDAO;
import entity.NguoiQuanLy;
import entity.NhanVien;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MainFrame {
	
	
	public static final Color clrCyan4 = new Color(69, 129, 142);
	public static final Color clrCyan3 = new Color(118,165,175);
	public static final Color clrTheme = new Color(254,250,224);
	public static final Color clrBtnHover = new Color(221,161,94);
	public static final Color clrTblBg = new Color(255,217,102);
	public static final Color clrGrey1 = new Color(51,51,51);
	public static final Color clrGrey = new Color(160,160,160);
	public static final Color clrYellow2 = new Color(255,229,153);
	public static final Color clrCyan2 = new Color(162,196,201);
	public static final Color clrBlue4 = new Color(89,126,170);
	public static final Color clrBlue6 = new Color(7,55,99);
	public static final Color clrRed = new Color(207,42,39);
	public static final Color clrPnlColor = new Color(7, 55, 99);
	public static final Color clrBtn = new Color(69, 129, 142);
	public static final Color clrPnlDTCa = new Color(252, 223, 135);
	public static final Color clrTableCT = new Color(201, 228, 228);
	public static final Color clrLblColor = Color.white;
	public static final Color clrBlack = Color.BLACK; 

	public static final DateTimeFormatter timeFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DecimalFormat moneyFormatter = new DecimalFormat("###,##0.00");
	
	public static String PdfPath = "";
	
	public JFrame frame;
	private JPanel pnlNhanVien;
	private JPanel pnlThongKe;
	private JPanel pnlNhaCungCap;
	private JPanel pnlTraHang;
	private JLabel lblHoTro;
	private JLabel lblTraHang;
	private JLabel lblThngK;
	private JLabel lblNhanVien;
	private JLabel lblNhaCungCap;
	private JPanel pnlContent;
	private JLabel lblTitle;
	private JLabel lblGDChinh;
	public static NguoiQuanLy nql;
    public static NhanVien nv;
    PnlGDChinh pnlGDChinhContent;
	private JPanel pnlKhachHang;
	private JLabel lblKhachHang;
	private JPanel pnlSanPham;
	private JLabel lblSanPham;
	private JPanel pnlHoTro;
	private JButton btnDangXuat;
	private JPanel pnlBanHang;
	private JLabel lblBanHang;
	private JPanel pnlDX;
//	private final JLabel lblNewLabel;
	private JLabel lblUser;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
//                    PnlThongKe frame = new PnlThongKe();
                    PnlDangNhap frame = new PnlDangNhap();
                    frame.setVisible(true);
//                    checkAccessPermission();
//                	MainFrame window = new MainFrame(NhanVienDAO.getNhanVien("NV00000001"));
//                	window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame window = new MainFrame(null);
//					
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @param nguoiQuanLy 
	 * @throws Exception 
	 */
	public MainFrame(NhanVien nv) throws Exception {
//		nql = (NguoiQuanLy) NhanVienDAO.getNguoiQuanLy("NV00000000");
		MainFrame.nv = nv;
		
		initialize();
		checkAccessPermission();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/view/icon/shop_logo.png")));
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
		
//		ImageIcon logo = new ImageIcon(MainFrame.class.getResource("/view/icon/icon_logoAM.png"));
//		Image img = logo.getImage();
//		Image newImg = img.getScaledInstance(145,64, java.awt.Image.SCALE_SMOOTH);
//		logo = new ImageIcon(newImg);
//
//		JLabel lblLogo = new JLabel(logo, JLabel.CENTER);
//		
		pnlLogo.add(lblLogo);
		
		
		JPanel pnlNavLists = new JPanel();
		pnlNavLists.setBackground(new Color(58,90,64));
		pnlNavLists.setBorder(new EmptyBorder(16, 0, 0, 0));
		pnlNavBar.add(pnlNavLists, BorderLayout.CENTER);
		pnlNavLists.setLayout(null);
		
		pnlBanHang = new JPanel();
		pnlBanHang.setBounds(0, 17, 203, 46);
		pnlBanHang.setBackground(clrTheme);
		pnlNavLists.add(pnlBanHang);
		pnlBanHang.setLayout(new BorderLayout(0, 0));
		
		lblBanHang = new JLabel("Bán Hàng");
		lblBanHang.setLabelFor(pnlBanHang);
		lblBanHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanHang.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/cart_icon.png")));
		lblBanHang.setForeground(Color.DARK_GRAY);
		lblBanHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlBanHang.add(lblBanHang);
		
		pnlKhachHang = new JPanel();
		pnlKhachHang.setBounds(0, 78, 203, 46);
		pnlKhachHang.setBackground(clrTheme);
		pnlNavLists.add(pnlKhachHang);
		pnlKhachHang.setLayout(new BorderLayout(0, 0));
		
		lblKhachHang = new JLabel("Khách Hàng");
		lblKhachHang.setLabelFor(pnlKhachHang);
		lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/khachHang_icon.png")));
		lblKhachHang.setForeground(Color.DARK_GRAY);
		lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlKhachHang.add(lblKhachHang);
		
		pnlSanPham = new JPanel();
		pnlSanPham.setBounds(0, 139, 203, 46);
		pnlSanPham.setBackground(clrTheme);
		pnlNavLists.add(pnlSanPham);
		pnlSanPham.setLayout(new BorderLayout(0, 0));
		
		lblSanPham = new JLabel("Sản Phẩm");
		lblSanPham.setLabelFor(pnlSanPham);
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPham.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/clothes_icon.png")));
		lblSanPham.setForeground(Color.DARK_GRAY);
		lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlSanPham.add(lblSanPham);
		
		pnlNhaCungCap = new JPanel();
		pnlNhaCungCap.setBounds(0, 200, 203, 46);
		pnlNhaCungCap.setBackground(clrTheme);
		pnlNavLists.add(pnlNhaCungCap);
		pnlNhaCungCap.setLayout(new BorderLayout(0, 0));
		
		lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setLabelFor(pnlNhaCungCap);
		lblNhaCungCap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhaCungCap.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/boxes_icon.png")));
		lblNhaCungCap.setForeground(Color.DARK_GRAY);
		lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlNhaCungCap.add(lblNhaCungCap);
		
		pnlNhanVien = new JPanel();
		pnlNhanVien.setBounds(0, 261, 203, 46);
		pnlNhanVien.setBackground(clrTheme);
		pnlNavLists.add(pnlNhanVien);
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		
		lblNhanVien = new JLabel("Nhân Viên");
		lblNhanVien.setLabelFor(pnlNhanVien);
		lblNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanVien.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/nhanVien_icon.png")));
		lblNhanVien.setForeground(Color.DARK_GRAY);
		lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlNhanVien.add(lblNhanVien);
		
		pnlThongKe = new JPanel();
		pnlThongKe.setBounds(0, 322, 203, 46);
		pnlThongKe.setBackground(clrTheme);
		pnlNavLists.add(pnlThongKe);
		pnlThongKe.setLayout(new BorderLayout(0, 0));
		
		lblThngK = new JLabel("Thống Kê");
		lblThngK.setLabelFor(pnlThongKe);
		lblThngK.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngK.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/thongKe_icon.png")));
		lblThngK.setForeground(Color.DARK_GRAY);
		lblThngK.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlThongKe.add(lblThngK);
		
		pnlTraHang = new JPanel();
		pnlTraHang.setBounds(0, 383, 203, 46);
		pnlTraHang.setBackground(clrTheme);
		pnlNavLists.add(pnlTraHang);
		pnlTraHang.setLayout(new BorderLayout(0, 0));
		
		lblTraHang = new JLabel("Trả Hàng");
		lblTraHang.setLabelFor(pnlTraHang);
		lblTraHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTraHang.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/traHang_icon.png")));
		lblTraHang.setForeground(Color.DARK_GRAY);
		lblTraHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlTraHang.add(lblTraHang);
		
		pnlHoTro = new JPanel();
		pnlHoTro.setBounds(0, 444, 203, 46);
		pnlHoTro.setBackground(clrTheme);
		pnlNavLists.add(pnlHoTro);
		pnlHoTro.setLayout(new BorderLayout(0, 0));
		
		lblHoTro = new JLabel("Hỗ Trợ");
		lblHoTro.setLabelFor(pnlHoTro);
		lblHoTro.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTro.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/hoTro_icon.png")));
		lblHoTro.setForeground(Color.DARK_GRAY);
		lblHoTro.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlHoTro.add(lblHoTro);
		
//		lblUser.setForeground(new Color(255, 255, 255));
//		lblUser.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/profile-user.png")));
//		lblUser.setFont(new Font("Tahoma", Font.BOLD, 17));
//		lblUser.setBounds(21, 546, 159, 41);
//		pnlNavLists.add(lblUser);
		

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
		pnlLogout.setBackground(new Color(58,90,64));
		pnlNavBar.add(pnlLogout, BorderLayout.SOUTH);
		pnlLogout.setLayout(new GridLayout(0, 1, 0, 5));
		
		lblUser = new JLabel(nv.getTen());
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/user_circle_M.png")));
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 17));
		pnlLogout.add(lblUser);
		
		pnlContent = new JPanel();
		pnlCenter.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new CardLayout(0, 0));
		
		PnlThongKe pnlThongKeContent = new PnlThongKe();
		pnlContent.add(pnlThongKeContent, "Thong Ke");
		
		System.out.println("Thong Ke");
		
		PnlTraHang pnlTraHangContent = new PnlTraHang();
		pnlContent.add(pnlTraHangContent, "Tra Hang");
		
		System.out.println("Tra Hang");
		
		PnlNhanVien pnlNhanVienContent = new PnlNhanVien();
		pnlContent.add(pnlNhanVienContent, "Nhan Vien");
		PnlNhaCC pnlNhaCungCapContent = new PnlNhaCC();
		pnlContent.add(pnlNhaCungCapContent,"Nha Cung Cap");
		
		System.out.println("Nha Cung Cap");
		
		PnlQLBanHang pnlBanHangContent = new PnlQLBanHang();
		pnlContent.add(pnlBanHangContent,"Ban Hang");

		System.out.println("Ban Hang");
		
		PnlKhachHang pnlKhachHangContent = new PnlKhachHang();
		pnlContent.add(pnlKhachHangContent, "Khach Hang");
		
		System.out.println("Khach Hang");
		
		PnLSanPham pnlSanPhamContent = new PnLSanPham();
		pnlContent.add(pnlSanPhamContent, "San Pham");
		
		System.out.println("San Pham");
		
		pnlGDChinhContent = new PnlGDChinh();
		pnlContent.add(pnlGDChinhContent,"Giao Dien Chinh");
		
		lblTitle = new JLabel("New label");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 60));
		pnlCenter.add(lblTitle, BorderLayout.NORTH);
		
		pnlDX = new JPanel();
		pnlDX.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnlLogout.add(pnlDX);
		pnlDX.setLayout(new BorderLayout(0, 0));
		pnlDX.setBackground(new Color(58,90,64));
		
		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				PnlDangNhap frame = new PnlDangNhap();
                frame.setVisible(true);
			}
		});
		btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangXuat.setForeground(new Color(255, 255, 255));
		btnDangXuat.setBackground(clrRed);
		btnDangXuat.setIcon(new ImageIcon(MainFrame.class.getResource("/view/icon/log_out_icon.png")));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDX.add(btnDangXuat);
		
		lblGDChinh = new JLabel("Giao Diện Chính");
		
		SetPDFPath();
		
	}
	private void SetPDFPath() {
		// TODO Auto-generated method stub
		String path = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	String []path1 = path.split("/");
    	String finPath = "";
    	for (int i = 0 ; i < path1.length - 1; i++) {
    		finPath += path1[i] + "/";
    	}
    	PdfPath = finPath + "/pdf";
	}
	// Thêm phương thức kiểm tra quyền truy cập
	private void checkAccessPermission() {
		ChuyenManHinhController controller = new ChuyenManHinhController(pnlContent,clrTheme,clrBtnHover);
		controller.setLbl(lblTitle);
		controller.setView("Giao Dien Chinh", pnlGDChinhContent, lblGDChinh);
		
		List<Nav> listItem = new ArrayList<>();
//		listItem.add(new Nav("Thong Ke",pnlThongKe,lblThngK));
//		listItem.add(new Nav("Tra Hang",pnlTraHang,lblTraHang));
//		listItem.add(new Nav("Nhan Vien", pnlNhanVien, lblNhanVien));
//		listItem.add(new Nav("Nha Cung Cap",pnlNhaCungCap,lblNhaCungCap));
		listItem.add(new Nav("Ho Tro",pnlHoTro,lblHoTro));
		listItem.add(new Nav("Khach Hang",pnlKhachHang,lblKhachHang));
		listItem.add(new Nav("San Pham",pnlSanPham,lblSanPham));
		listItem.add(new Nav("Ban Hang", pnlBanHang, lblBanHang));
		controller.setEvent(listItem);
		lblUser.setText(nv.getTen());
		
	    if (nv != null && nv instanceof NguoiQuanLy) {
	        // Hiển thị tất cả chức năng đối với Người Quản Lý
	    	listItem.add(new Nav("Thong Ke",pnlThongKe,lblThngK));
			listItem.add(new Nav("Tra Hang",pnlTraHang,lblTraHang));
			listItem.add(new Nav("Nhan Vien", pnlNhanVien, lblNhanVien));
			listItem.add(new Nav("Nha Cung Cap",pnlNhaCungCap,lblNhaCungCap));
			listItem.add(new Nav("Khach Hang",pnlKhachHang,lblKhachHang));
			controller.setEvent(listItem);

	        // ...
	    }
	}
//	if (nv instanceof NhanVien) {
//        lblNewLabel.setText("  " + nv.getMaNhanVien());
//    } else if (nv instanceof NguoiQuanLy) {
//        lblNewLabel.setText("  " + ((NguoiQuanLy) nv).getMaQuanLy());
//    }

	
}