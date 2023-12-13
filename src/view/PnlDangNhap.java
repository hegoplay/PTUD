package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.EventTarget;

import dao.NhanVienDAO;
import entity.NguoiQuanLy;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JTextField textMatKhau;
	protected Object frame;
	private NguoiQuanLy nguoiQL;
	private NhanVien nv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnlDangNhap frame = new PnlDangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PnlDangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1315, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(PnlDangNhap.class.getResource("/view/icon/logogdchinh-removebg-preview.png")));
		lblNewLabel.setBounds(339, 0, 543, 229);
		contentPane.add(lblNewLabel);

		JLabel lblDangNhapHeThong = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
		lblDangNhapHeThong.setForeground(new Color(255, 255, 255));
		lblDangNhapHeThong.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDangNhapHeThong.setBounds(482, 223, 296, 54);
		contentPane.add(lblDangNhapHeThong);

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String taiKhoan = txtTaiKhoan.getText();
					String matKhau = textMatKhau.getText();

					if (taiKhoan.equals("ql001") && matKhau.equals("12345")) {
						// Đăng nhập thành công là Người Quản Lý
						// Đóng cửa sổ đăng nhập
						dispose();

						// Mở cửa sổ MainFrame
						EventQueue.invokeLater(() -> {
							try {
								MainFrame mainFrame = new MainFrame(NhanVienDAO.getNguoiQuanLy("NV00000000"));
								mainFrame.frame.setVisible(true);
							} catch (Exception a) {
								a.printStackTrace();
							}
						});
//			            try {
//			                NguoiQuanLy nguoiQuanLy = new NguoiQuanLy("NV00000000"); // Thay đổi mã NV mặc định nếu cần
//			                MainFrame.nql = nguoiQuanLy;
//			            } catch (Exception ex) {
//			                ex.printStackTrace();
//			            }

					} else if (taiKhoan.equals("nv001") && matKhau.equals("12345")) {
						// Đăng nhập thành công là Nhân Viên
						// Đóng cửa sổ đăng nhập
						dispose();

						// Mở cửa sổ MainFrame
						EventQueue.invokeLater(() -> {
							try {
								MainFrame mainFrame = new MainFrame(NhanVienDAO.getNhanVien("NV00000001"));
								mainFrame.frame.setVisible(true);
							} catch (Exception a) {
								a.printStackTrace();
							}
						});
//			            try {
//			                NhanVien nhanVien = new NhanVien("NV00000001"); // Thay đổi mã NV mặc định nếu cần
//			                MainFrame.nv = nhanVien;
//			            } catch (Exception ex) {
//			                ex.printStackTrace();
//			            }

					} else {
						JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng", "Lỗi",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					textMatKhau.requestFocus();
				}
			}
		});
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTaiKhoan.setToolTipText("");
		txtTaiKhoan.setBounds(421, 288, 423, 33);
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setText("hegoplay");

		textMatKhau = new JPasswordField();
		textMatKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					login();
				}
			}
		});
		textMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textMatKhau.setColumns(10);
		textMatKhau.setBounds(421, 351, 423, 33);
		textMatKhau.setText("abcABC123");
		contentPane.add(textMatKhau);

		JLabel lblTaiKhoan = new JLabel("Nhập mã người dùng:");
		lblTaiKhoan.setForeground(new Color(255, 255, 255));
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTaiKhoan.setBounds(195, 289, 199, 33);
		contentPane.add(lblTaiKhoan);

		JLabel lblDangNhap = new JLabel("Mật khẩu:");
		lblDangNhap.setForeground(new Color(255, 255, 255));
		lblDangNhap.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDangNhap.setBounds(195, 348, 199, 33);
		contentPane.add(lblDangNhap);

		JLabel lblQuenMK = new JLabel("Quên mật khẩu?");
		lblQuenMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println("1");
				PnlKhoiPhucMK pnlKhoiPhucMK = new PnlKhoiPhucMK();
				pnlKhoiPhucMK.setVisible(true);

			}
		});

		lblQuenMK.setForeground(new Color(255, 255, 255));
		lblQuenMK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuenMK.setBounds(701, 399, 126, 33);
		contentPane.add(lblQuenMK);
//		lblQuenMK.addMouseListener(new java.awt.event.MouseAdapter() {
//		    public void mouseClicked(java.awt.event.MouseEvent evt) {
//		        try {
//		            System.out.println("Mouse Clicked on lblQuenMK");
//
//		            // Hiển thị thông báo khi nhấn vào nút "Quên mật khẩu"
//		            JOptionPane.showMessageDialog(null, "Liên hệ với quản trị viên để khôi phục mật khẩu.", "Quên mật khẩu", JOptionPane.INFORMATION_MESSAGE);
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		        }
//		    }
//		});

		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDangNhap.setBackground(new Color(64, 128, 128));
		btnDangNhap.setBounds(545, 443, 161, 42);
		contentPane.add(btnDangNhap);

		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PnlDangNhap.class.getResource("/view/icon/hinhanhgiaodienchinh.png")));
		lblNewLabel_1.setBounds(0, 0, 1296, 745);
		contentPane.add(lblNewLabel_1);
	}

	private void login() {
		String taiKhoan = txtTaiKhoan.getText();
		String matKhau = textMatKhau.getText();
		
		try {
			TaiKhoan tk = NhanVienDAO.getTKNV(taiKhoan, matKhau);
			if (tk == null) {
				throw new Exception("Tài khoản hoặc mật khẩu không đúng");
			}
			if (tk.getNhanVien().isDangLamViec() == false) {
				throw new Exception("Nhân viên hiện tại không làm việc");
			}
			// Đăng nhập thành công là Người Quản Lý
			// Đóng cửa sổ đăng nhập
			dispose();

			// Mở cửa sổ MainFrame
			EventQueue.invokeLater(() -> {
				try {
					MainFrame mainFrame = new MainFrame(tk.getNhanVien());
					mainFrame.frame.setVisible(true);
				} catch (Exception a) {
					a.printStackTrace();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
