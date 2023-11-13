package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.Nav;
import controller.ChuyenManHinhController;

public class PnlQLBanHang extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public PnlQLBanHang() throws Exception {
		setBackground(MainFrame.clrTheme);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(170, 219, 238));
		pnlHeader.setBorder(new EmptyBorder(10, 23, 0, 0));
		add(pnlHeader, BorderLayout.NORTH);
		pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));

		JPanel pnlLapHoaDon = new JPanel();
		pnlHeader.add(pnlLapHoaDon);
		pnlLapHoaDon.setBorder(new EmptyBorder(13,13,13,13));
		pnlLapHoaDon.setBackground(MainFrame.clrPnlColor);
		pnlLapHoaDon.setLayout(new BorderLayout(0, 0));

		JLabel lblLapHoaDon = new JLabel("Lập hóa đơn");
		ImageIcon originalIcon = new ImageIcon(PnlThongKe.class.getResource("/view/icon/icons8-machine.png"));
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		// Tạo ImageIcon mới từ Image đã được thay đổi kích thước
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblLapHoaDon.setIcon(scaledIcon);
		lblLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLapHoaDon.setForeground(MainFrame.clrLblColor);
		pnlLapHoaDon.add(lblLapHoaDon);

		JPanel pnlTimHD = new JPanel();
		pnlTimHD.setBorder(new EmptyBorder(13, 13, 13, 13));
		pnlTimHD.setBackground(MainFrame.clrPnlColor);
		pnlHeader.add(pnlTimHD);
		pnlTimHD.setLayout(new BorderLayout(0, 0));

		JLabel lblTimHD = new JLabel("Tìm kiếm hóa đơn");
		ImageIcon originalIcon2 = new ImageIcon(PnlThongKe.class.getResource("/view/icon/icons8-search-64.png"));
		Image originalImage2 = originalIcon2.getImage();
		Image scaledImage2 = originalImage2.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		// Tạo ImageIcon mới từ Image đã được thay đổi kích thước
		ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
		lblTimHD.setIcon(scaledIcon2);
		lblTimHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimHD.setForeground(MainFrame.clrLblColor);
		pnlTimHD.add(lblTimHD, BorderLayout.NORTH);

		JPanel pnlDoanhThuCa= new JPanel();
		pnlDoanhThuCa.setBorder(new EmptyBorder(13, 13, 13, 13));
		pnlDoanhThuCa.setBackground(MainFrame.clrPnlColor);
		pnlHeader.add(pnlDoanhThuCa);
		pnlDoanhThuCa.setLayout(new BorderLayout(0, 0));

		JLabel lblDTCa = new JLabel("Doanh thu theo ca");
		lblDTCa.setForeground(MainFrame.clrLblColor);
		lblDTCa.setIcon(new ImageIcon(PnlThongKe.class.getResource("/view/icon/icons8-coins-24.png")));
		lblDTCa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlDoanhThuCa.add(lblDTCa, BorderLayout.NORTH);


		JPanel pnlLHDLayouts = new JPanel();
		add(pnlLHDLayouts, BorderLayout.CENTER);
		pnlLHDLayouts.setLayout(new CardLayout(0, 0));

		PnlLHD pnlLhd= new PnlLHD();
		pnlLHDLayouts.add(pnlLhd, "Lap Hoa Don");

		PnlTimHD pnlTkHD = new PnlTimHD();
		pnlLHDLayouts.add(pnlTkHD,"Tim Hoa Don");

		PnlDoanhThuCa pnlDTCa = new PnlDoanhThuCa();
		pnlLHDLayouts.add(pnlDTCa,"Doanh thu theo ca");

		ChuyenManHinhController CHMcontroller = new ChuyenManHinhController(pnlLHDLayouts,MainFrame.clrBlue6,MainFrame.clrBlue4);

		CHMcontroller.setView("Lap Hoa Don", pnlLapHoaDon, lblLapHoaDon);

		List<Nav> listItems = new ArrayList<Nav>();

		listItems.add(new Nav("Lap Hoa Don", pnlLapHoaDon, lblLapHoaDon));
		listItems.add(new Nav("Tim Hoa Don",pnlTimHD,lblTimHD));
		listItems.add(new Nav("Doanh thu theo ca",pnlDoanhThuCa,lblDTCa));

		CHMcontroller.setEvent(listItems);
	}

}