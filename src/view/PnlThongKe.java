package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class PnlThongKe extends JPanel {

	private static final long serialVersionUID = 1L;
	
	static final Color idle_pnlclr = new Color(7, 55, 99);
	static final Color idle_lblclr = Color.white;
	/**
	 * Create the panel.
	 */
	public PnlThongKe() {
		setBackground(new Color(111, 168, 220));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNavTK = new JPanel();
		pnlNavTK.setBackground(new Color(111, 168, 220));
		pnlNavTK.setBorder(new EmptyBorder(10, 23, 0, 0));
		add(pnlNavTK, BorderLayout.NORTH);
		pnlNavTK.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
		
		JPanel pnlDoanhThuCH = new JPanel();
		pnlNavTK.add(pnlDoanhThuCH);
		pnlDoanhThuCH.setBorder(new EmptyBorder(13,13,13,13));
		pnlDoanhThuCH.setBackground(idle_pnlclr);
		pnlDoanhThuCH.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDoanhThuCuaHang = new JLabel("Doanh thu cửa hàng");
		lblDoanhThuCuaHang.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/money_sign.png")));
		lblDoanhThuCuaHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoanhThuCuaHang.setForeground(idle_lblclr);
		pnlDoanhThuCH.add(lblDoanhThuCuaHang);
		
		JPanel pnlDoanhThuNV = new JPanel();
		pnlDoanhThuNV.setBorder(new EmptyBorder(13, 13, 13, 13));
		pnlDoanhThuNV.setBackground(idle_pnlclr);
		pnlNavTK.add(pnlDoanhThuNV);
		pnlDoanhThuNV.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDoanhThuNV = new JLabel("Doanh thu nhân viên");
		lblDoanhThuNV.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/id_card_ico.png")));
		lblDoanhThuNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoanhThuNV.setForeground(idle_lblclr);
		pnlDoanhThuNV.add(lblDoanhThuNV, BorderLayout.NORTH);
		
		JPanel pnlTonKho = new JPanel();
		pnlTonKho.setBorder(new EmptyBorder(13, 13, 13, 13));
		pnlTonKho.setBackground(idle_pnlclr);
		pnlNavTK.add(pnlTonKho);
		pnlTonKho.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTonKho = new JLabel("Tồn Kho");
		lblTonKho.setForeground(idle_lblclr);
		lblTonKho.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/unbox_ico.png")));
		lblTonKho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlTonKho.add(lblTonKho, BorderLayout.NORTH);

	}

}
