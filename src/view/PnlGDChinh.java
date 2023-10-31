package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class PnlGDChinh extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlGDChinh() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Phần Mềm Quản Lý Cửa Hàng Bán Quần Áo AM");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitle.setBounds(259, 11, 673, 61);
		add(lblTitle);
		
		JLabel lblFacebookHttpswwwfacebookcom = new JLabel("Facebook: https://www.facebook.com/");
		lblFacebookHttpswwwfacebookcom.setForeground(Color.WHITE);
		lblFacebookHttpswwwfacebookcom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFacebookHttpswwwfacebookcom.setBounds(36, 58, 695, 39);
		add(lblFacebookHttpswwwfacebookcom);
		
		JLabel lblHotline = new JLabel("Hotline: 0782505434");
		lblHotline.setForeground(Color.WHITE);
		lblHotline.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHotline.setBounds(36, 98, 695, 39);
		add(lblHotline);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlGDChinh.class.getResource("/view/icon/background_img.png")));
		lblNewLabel.setBounds(0, 0, 1200, 780);
		add(lblNewLabel);

	}
}
