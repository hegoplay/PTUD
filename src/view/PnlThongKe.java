package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class PnlThongKe extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlThongKe() {
		setBackground(new Color(111, 168, 220));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNavTK = new JPanel();
		pnlNavTK.setBackground(new Color(111, 168, 220));
		pnlNavTK.setBorder(new EmptyBorder(10, 0, 0, 23));
		add(pnlNavTK, BorderLayout.NORTH);
		pnlNavTK.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pnlDoanhThuCH = new JPanel();
		pnlNavTK.add(pnlDoanhThuCH);
		pnlDoanhThuCH.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDoanhThuCuaHang = new JLabel("Doanh thu cửa hàng");
		lblDoanhThuCuaHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlDoanhThuCH.add(lblDoanhThuCuaHang);

	}

}
