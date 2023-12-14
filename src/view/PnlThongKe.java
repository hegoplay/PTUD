package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.Nav;
import controller.ChuyenManHinhController;

import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.CardLayout;

public class PnlThongKe extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final Color clrPnlColor = new Color(7, 55, 99);
	public static final Color clrLblColor = Color.white;
	
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
		pnlDoanhThuCH.setBorder(null);
		pnlDoanhThuCH.setBackground(clrPnlColor);
		pnlDoanhThuCH.setLayout(new FlowLayout(FlowLayout.CENTER, 13, 13));
		
		JLabel lblDoanhThuCuaHang = new JLabel("Doanh thu cửa hàng");
		lblDoanhThuCuaHang.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/money_sign_icon.png")));
		lblDoanhThuCuaHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoanhThuCuaHang.setForeground(clrLblColor);
		pnlDoanhThuCH.add(lblDoanhThuCuaHang);
		
		JPanel pnlDoanhThuNV = new JPanel();
		pnlDoanhThuNV.setBorder(null);
		pnlDoanhThuNV.setBackground(clrPnlColor);
		pnlNavTK.add(pnlDoanhThuNV);
		pnlDoanhThuNV.setLayout(new FlowLayout(FlowLayout.CENTER, 13, 13));
		
		JLabel lblDoanhThuNV = new JLabel("Doanh thu nhân viên");
		lblDoanhThuNV.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/id_card_icon.png")));
		lblDoanhThuNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoanhThuNV.setForeground(clrLblColor);
		pnlDoanhThuNV.add(lblDoanhThuNV);
		
		JPanel pnlTonKho = new JPanel();
		pnlTonKho.setBorder(null);
		pnlTonKho.setBackground(clrPnlColor);
		pnlNavTK.add(pnlTonKho);
		pnlTonKho.setLayout(new FlowLayout(FlowLayout.CENTER, 13, 13));
		
		JLabel lblTonKho = new JLabel("Tồn Kho");
		lblTonKho.setForeground(clrLblColor);
		lblTonKho.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/unbox_icon.png")));
		lblTonKho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlTonKho.add(lblTonKho);
		
		JPanel pnlTKLayouts = new JPanel();
		add(pnlTKLayouts, BorderLayout.CENTER);
		pnlTKLayouts.setLayout(new CardLayout(0, 0));
		
		PnlTKDoanhThu pnlTKDoanhThu = new PnlTKDoanhThu();
		pnlTKLayouts.add(pnlTKDoanhThu, "Doanh thu nhan vien");
		
		PnlTKTK pnlTKTK = new PnlTKTK();
		pnlTKLayouts.add(pnlTKTK,"Ton kho");
		
		PnlTKCH pnlTKCH = new PnlTKCH();
		pnlTKLayouts.add(pnlTKCH,"Doanh thu cua hang");
		
		ChuyenManHinhController CHMcontroller = new ChuyenManHinhController(pnlTKLayouts,MainFrame.clrBlue6,MainFrame.clrBlue4);
		
		CHMcontroller.setView("Ton kho", pnlTonKho, lblTonKho);
		
		List<Nav> listItems = new ArrayList<Nav>();
		
		listItems.add(new Nav("Ton kho", pnlTonKho, lblTonKho));
		listItems.add(new Nav("Doanh thu nhan vien",pnlDoanhThuNV,lblDoanhThuNV));
		listItems.add(new Nav("Doanh thu cua hang",pnlDoanhThuCH,lblDoanhThuCuaHang));
		
		CHMcontroller.setEvent(listItems);
		
	}

}
