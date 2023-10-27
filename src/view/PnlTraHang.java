package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

public class PnlTraHang extends JPanel {

	private static final long serialVersionUID = 1L;

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
		add(pnlTongKet, BorderLayout.SOUTH);

	}

}
