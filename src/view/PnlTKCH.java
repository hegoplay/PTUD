package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import dao.HoaDonDAO;
import dao.TraHangDAO;
import entity.HoaDon;
import entity.PhieuTraHang;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.SwingConstants;

public class PnlTKCH extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox cmbDay;
	private JLabel lblValueDoanhThu;
	private LocalDate startDay;
	private LocalDate endDay;

	/**
	 * Create the panel.
	 */
	public PnlTKCH() {
		setBackground(MainFrame.clrTheme);
		setBorder(new EmptyBorder(10, 30, 10, 30));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(MainFrame.clrTheme);
		FlowLayout flowLayout = (FlowLayout) pnlTitle.getLayout();
		flowLayout.setHgap(12);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(pnlTitle, BorderLayout.NORTH);
		
		JLabel lblThongKeTheo = new JLabel("Thống kê theo:");
		lblThongKeTheo.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlTitle.add(lblThongKeTheo);
		
		cmbDay = new JComboBox<String>(new String[] {"Ngày", "Tháng", "Kỳ", "Năm"});
		cmbDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlTitle.add(cmbDay);
		
		JButton btnXBC = new JButton("Xuất báo cáo");
		btnXBC.setBackground(MainFrame.clrCyan4);
		btnXBC.setForeground(Color.WHITE);
		btnXBC.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnXBC.setIcon(new ImageIcon(PnlTKCH.class.getResource("/view/icon/file_icon.png")));
		pnlTitle.add(btnXBC);
		
		JPanel pnlCenter = new JPanel();
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlInfos = new JPanel();
		pnlInfos.setBackground(MainFrame.clrTheme);
		pnlInfos.setBorder(new EmptyBorder(0, 50, 0, 50));
		pnlCenter.add(pnlInfos, BorderLayout.NORTH);
		pnlInfos.setLayout(new GridLayout(1, 3, 90, 0));
		
		JPanel pnlDoanhThu = new JPanel();
		pnlDoanhThu.setBackground(MainFrame.clrYellow2);
		pnlInfos.add(pnlDoanhThu);
		pnlDoanhThu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDoanhThu = new JLabel("Doanh thu (VNĐ)");
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDoanhThu.add(lblDoanhThu);
		
		lblValueDoanhThu = new JLabel("642.057.000");
		lblValueDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblValueDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDoanhThu.add(lblValueDoanhThu);
		
		JPanel pnlDoanhSo = new JPanel();
		pnlDoanhSo.setBackground(MainFrame.clrYellow2);
		pnlInfos.add(pnlDoanhSo);
		pnlDoanhSo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDoanhSo = new JLabel("Doanh số");
		lblDoanhSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoanhSo.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlDoanhSo.add(lblDoanhSo);
		
		JLabel lblValueDoanhSo = new JLabel("1145");
		lblValueDoanhSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueDoanhSo.setFont(new Font("Tahoma", Font.BOLD, 32));
		pnlDoanhSo.add(lblValueDoanhSo);
		
		JPanel pnlLoiNhuan = new JPanel();
		pnlLoiNhuan.setBackground(MainFrame.clrYellow2);
		pnlInfos.add(pnlLoiNhuan);
		pnlLoiNhuan.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblLoiNhuan = new JLabel("Lợi nhuận (VNĐ)");
		lblLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlLoiNhuan.add(lblLoiNhuan);
		
		JLabel lblValueLoiNhuan = new JLabel("642.057.000");
		lblValueLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 32));
		pnlLoiNhuan.add(lblValueLoiNhuan);
		
		JPanel pnlTables = new JPanel();
		pnlTables.setBackground(MainFrame.clrTheme);
		pnlCenter.add(pnlTables, BorderLayout.CENTER);
		pnlTables.setLayout(new GridLayout(0, 2, 0, 0));
		
		LoadData();
		cmbDay.addActionListener(this);
	}

	private void LoadData() {
		startDay = LocalDate.now();
		endDay = LocalDate.now().plusDays(1);

		switch ((String) cmbDay.getSelectedItem()) {
		case "Tháng": {
			startDay = startDay.minusMonths(1);
		}
		case "Kỳ": {
			startDay = startDay.minusMonths(3);
		}
		case "Năm": {
			startDay = startDay.minusYears(1);
		}
		}
		lblValueDoanhThu.setText(new DecimalFormat("###,###").format(HoaDonDAO.GetTongDT(startDay, endDay)));
		ArrayList<HoaDon> hdList = HoaDonDAO.GetHoaDonInDate(startDay, endDay);
		ArrayList<PhieuTraHang> pthList = TraHangDAO.GetPTHInDate(startDay, endDay);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==cmbDay) {
			LoadData();
		}
	}

}
