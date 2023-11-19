package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controller.ToPDFController;
import dao.HoaDonDAO;
import dao.SanPhamDAO;
import dao.TraHangDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NguoiQuanLy;
import entity.PhieuTraHang;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.SwingConstants;

public class PnlTKCH extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox cmbDay;
	private JLabel lblValueDoanhThu;
	private LocalDateTime startDay;
	private LocalDateTime endDay;
	private JLabel lblValueLoiNhuan;
	private JLabel lblValueDoanhSo;
	private Map<Integer,String> sortedMap;
	
	private JPanel pnlTables;
	private ArrayList<HoaDon> hdList;
	private ArrayList<PhieuTraHang> pthList;
	private int type;
	private JFreeChart lineChart;
	private ArrayList<Entry<String, Integer>> lists;
	private JButton btnXBC;
	private JFreeChart barChart;

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

		cmbDay = new JComboBox<String>(new String[] { "Ngày", "Tháng", "Kỳ", "Năm" });
		cmbDay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlTitle.add(cmbDay);

		btnXBC = new JButton("Xuất báo cáo");
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
		pnlDoanhThu.setBorder(new EmptyBorder(10, 10, 10, 10));
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
		pnlDoanhSo.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlDoanhSo.setBackground(MainFrame.clrYellow2);
		pnlInfos.add(pnlDoanhSo);
		pnlDoanhSo.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblDoanhSo = new JLabel("Doanh số");
		lblDoanhSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoanhSo.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlDoanhSo.add(lblDoanhSo);

		lblValueDoanhSo = new JLabel("1145");
		lblValueDoanhSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueDoanhSo.setFont(new Font("Tahoma", Font.BOLD, 32));
		pnlDoanhSo.add(lblValueDoanhSo);

		JPanel pnlLoiNhuan = new JPanel();
		pnlLoiNhuan.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlLoiNhuan.setBackground(MainFrame.clrYellow2);
		pnlInfos.add(pnlLoiNhuan);
		pnlLoiNhuan.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblLoiNhuan = new JLabel("Lợi nhuận (VNĐ)");
		lblLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlLoiNhuan.add(lblLoiNhuan);

		lblValueLoiNhuan = new JLabel("642.057.000");
		lblValueLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 32));
		pnlLoiNhuan.add(lblValueLoiNhuan);

		pnlTables = new JPanel();
		pnlTables.setBorder(new EmptyBorder(20, 10, 20, 10));
		pnlTables.setBackground(MainFrame.clrTheme);
		pnlCenter.add(pnlTables, BorderLayout.CENTER);
		pnlTables.setLayout(new GridLayout(0, 2, 10, 0));

		LoadData();
		cmbDay.addActionListener(this);
		btnXBC.addActionListener(this);
	}

	private void LoadData() {
		String timeTitle = "Giờ";
		pnlTables.removeAll();
		startDay = LocalDateTime.now().with(LocalTime.MIDNIGHT);
		endDay = LocalDateTime.now().plusDays(1).with(LocalTime.MIDNIGHT);
		type =0;
		switch ((String) cmbDay.getSelectedItem()) {
			case "Tháng": {
				startDay = startDay.withDayOfMonth(1);
				endDay = startDay.plusMonths(1);
				type = 1;
				timeTitle = "Ngày";
				break;
			}
			case "Kỳ": {
				startDay = startDay.withDayOfYear(1);
				endDay = startDay;
				while (endDay.isBefore(LocalDateTime.now())) {
					endDay = endDay.plusMonths(3);
				}
				startDay = endDay.minusMonths(3);
				timeTitle = "Tháng";
				break;
			}
			case "Năm": {
				startDay = startDay.withDayOfYear(1);
				endDay = startDay.plusYears(1);
				timeTitle = "Kỳ";
				break;
			}
		}
		System.out.println("1");
		hdList = HoaDonDAO.GetHoaDonInDate(startDay.toLocalDate(), endDay.toLocalDate());
		System.out.println("2");
		pthList = TraHangDAO.GetPTHInDate(startDay.toLocalDate(), endDay.toLocalDate());
		System.out.println("3");
		double tongTongTien = 0;
		double tongTienHoan = 0;
		double tongTienGoc = 0;
		double tongTienSPPTH = 0; //Tổng tiền sản phẩm phải trả
		for (HoaDon hd : hdList) {
			tongTongTien += hd.TinhTongTien();
			tongTienGoc += hd.TinhTongTienGoc();
		}

		for (PhieuTraHang pth : pthList) {
			tongTienHoan += pth.TinhTienTra();
			tongTienSPPTH += pth.TinhTongTienGoc();
		}

		lblValueDoanhThu.setText(new DecimalFormat("###,###").format(tongTongTien));
		lblValueLoiNhuan.setText(new DecimalFormat("###,###").format(tongTongTien - tongTienGoc));
		lblValueDoanhSo.setText(HoaDonDAO.GetHoaDonInDate(startDay.toLocalDate(), endDay.toLocalDate()).size() + "");

		Map<String,Integer> SPMap = new TreeMap<>();
		
		System.out.println("YES");
		
		for (HoaDon hd : hdList) {
			for (ChiTietHoaDon cthd : hd.getDsCTHD()) {
				Integer cnt = SPMap.get(cthd.getSanPham().getMaSP());
				if (cnt!=null) {
					SPMap.put(cthd.getSanPham().getMaSP(),cnt + cthd.getSoLuong());
				} else {
					SPMap.put(cthd.getSanPham().getMaSP(), cthd.getSoLuong());
				}
				
			}
		}
		
		
		
		lists = new ArrayList<Map.Entry<String, Integer>>();
		
		
		for (Map.Entry<String, Integer> entry : SPMap.entrySet()) {
			lists.add(entry);
		}
		lists.sort(new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
			}
			
		});
		System.out.println("YES");

// Xu ly du lieu tren dataset		

		lineChart = ChartFactory.createXYLineChart("So sánh doanh thu",
				"Thời gian (" + timeTitle + ")", "doanh thu", createLineDataset(), PlotOrientation.VERTICAL, true, true, false);
		ChartPanel linePanel = new ChartPanel(lineChart);
		linePanel.setMouseZoomable(false);
		linePanel.setDisplayToolTips(true);
		pnlTables.add(linePanel,BorderLayout.CENTER);
		linePanel.setLayout(new BorderLayout(0, 0));
		
		barChart = ChartFactory.createBarChart("Top 5 sản phẩm bán dạy", "Sản phẩm",
				"Doanh thu", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setMouseZoomable(false);
		chartPanel.setDisplayToolTips(true);
		pnlTables.add(chartPanel,BorderLayout.CENTER);
		chartPanel.setLayout(new BorderLayout(0, 0));
		pnlTables.revalidate();
		
		
		
	}

	private CategoryDataset createDataset() {
		final String soLuongtxt = "Số lượng";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int i = 0;
		for (Map.Entry<String, Integer> entry : lists) {
			if (i++ >= 5) {
				break;
			}
			dataset.addValue(entry.getValue(), soLuongtxt, SanPhamDAO.GetSanPham(entry.getKey()).getTenSP());
		}
		return dataset;
	}
	
	private XYDataset createLineDataset() {
		final String soLuongtxt = "Số lượng";
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("");
		XYSeries series2 = new XYSeries("");
		if ((String)cmbDay.getSelectedItem()=="Ngày") {
			series1 = new XYSeries("Doanh thu hôm nay");
			series2 = new XYSeries("Doanh thu hôm qua");
			ArrayList<HoaDon> lists = HoaDonDAO.GetHoaDonInDate(startDay.minusDays(1).toLocalDate(), endDay.minusDays(1).toLocalDate());
			final int step = 3;
			for(int i = 0; i <24;i+=step) {
				double res = 0;
				for (HoaDon hd : hdList) {
					if (hd.getNgayLapHD().isAfter(startDay.plusHours(i)) && 
							hd.getNgayLapHD().isBefore(startDay.plusHours(i+step))) {
						res += hd.TinhTongTien();
					}
				}
				series1.add(i+step, res);
				res = 0;
				for (HoaDon hd : lists) {
					if (hd.getNgayLapHD().isAfter(startDay.minusDays(1).plusHours(i)) && 
							hd.getNgayLapHD().isBefore(startDay.minusDays(1).plusHours(i+step))) {
						
						res += hd.TinhTongTien();
					}
				}
				series2.add(i+step, res);
			}
		}
		if ((String)cmbDay.getSelectedItem()=="Tháng") {
			series1 = new XYSeries("Doanh thu tháng này");
			series2 = new XYSeries("Doanh thu tháng trước");
			ArrayList<HoaDon> lists = HoaDonDAO.GetHoaDonInDate(startDay.minusMonths(1).toLocalDate(), endDay.minusMonths(1).toLocalDate());
			final int step = 7;
			for(int i = 0; i <=21;i+=step) {
				int plus_end = i +step;
				double res = 0;
				for (HoaDon hd : hdList) {
					boolean check = true;
					if((i + step == 28)) {
						if (hd.getNgayLapHD().isAfter(startDay.plusDays(i)) && 
								hd.getNgayLapHD().isBefore(endDay)) {
							res += hd.TinhTongTien();
							check = false;
						}
					}
					if (hd.getNgayLapHD().isAfter(startDay.plusDays(i)) && 
							hd.getNgayLapHD().isBefore(startDay.plusDays(plus_end))&&check) {
						res += hd.TinhTongTien();
					}
				}
				series1.add(plus_end, res);
				res = 0;
				for (HoaDon hd : lists) {
					boolean check = true;
					if((i + step == 28)) {
						if (hd.getNgayLapHD().isAfter(startDay.minusMonths(1).plusDays(i)) && 
								hd.getNgayLapHD().isBefore(endDay.minusMonths(1))) {
							res += hd.TinhTongTien();
							check = false;
						}
					}
					if (hd.getNgayLapHD().isAfter(startDay.minusMonths(1).plusDays(i)) && 
							hd.getNgayLapHD().isBefore(startDay.minusMonths(1).plusDays(plus_end)) && check) {
						res+=hd.TinhTongTien();
					}
				}
				series2.add(plus_end, res);
			}
			
		}
		if ((String)cmbDay.getSelectedItem()=="Kỳ") {
			series1 = new XYSeries("Doanh thu kỳ nay");
			series2 = new XYSeries("Doanh thu kỳ trước");
			ArrayList<HoaDon> lists = HoaDonDAO.GetHoaDonInDate(startDay.minusMonths(3).toLocalDate(),
					endDay.minusMonths(3).toLocalDate());
			final int step = 1;
			double res = 0;
			for(int i = 0; i <3;i+=step) {
				res = 0;
				// xử lý hóa đơn kỳ này
				for (HoaDon hd : hdList) {
					if (hd.getNgayLapHD().isAfter(startDay.plusMonths(i)) && 
							hd.getNgayLapHD().isBefore(startDay.plusMonths(i+step))) {
						res += hd.TinhTongTien();
					}
				}
				series1.add(i+step,res);
				res = 0;
				for (HoaDon hd : lists) {
					if (hd.getNgayLapHD().isAfter(startDay.minusMonths(3).plusMonths(i)) && 
							hd.getNgayLapHD().isBefore(startDay.minusMonths(3).plusMonths(i+step))) {
						res += hd.TinhTongTien();
					}
				}
				series2.add(i+step,res);
			}
		}
		if ((String)cmbDay.getSelectedItem()=="Năm") {
			series1 = new XYSeries("Doanh thu năm nay");
			series2 = new XYSeries("Doanh thu năm trước");
			ArrayList<HoaDon> lists = HoaDonDAO.GetHoaDonInDate(startDay.minusYears(1).toLocalDate(),
					endDay.minusYears(1).toLocalDate());
			final int step = 3;
			double res = 0;
			for(int i = 0; i <12;i+=step) {
				res = 0;
				// xử lý hóa đơn kỳ này
				for (HoaDon hd : hdList) {
					if (hd.getNgayLapHD().isAfter(startDay.plusMonths(i)) && 
							hd.getNgayLapHD().isBefore(startDay.plusMonths(i+step))) {
						res += hd.TinhTongTien();
					}
				}
				series1.add((i+step)/step,res);
				res = 0;
				for (HoaDon hd : lists) {
					if (hd.getNgayLapHD().isAfter(startDay.minusYears(1).plusMonths(i)) && 
							hd.getNgayLapHD().isBefore(startDay.minusYears(1).plusMonths(i+step))) {
						res += hd.TinhTongTien();
					}
				}
				series2.add((i+step)/step,res);
			}
		}
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		
		return dataset;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == cmbDay) {
			LoadData();
		}
		if (e.getSource() == btnXBC) {
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(MainFrame.PdfPath));
				fileChooser.setDialogTitle("Chọn vị trí muốn lưu");   
				
				int userSelection = fileChooser.showSaveDialog(this);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				ToPDFController.xuatTKCH(
						fileToSave.getAbsolutePath(), 
						startDay.toLocalDate(), 
						endDay.toLocalDate(), 
						(NguoiQuanLy)MainFrame.nv, 
						new double[] {
								Double.parseDouble(lblValueDoanhThu.getText().replace(",", "")),
								Double.parseDouble(lblValueDoanhSo.getText().replace(",", "")),
								Double.parseDouble(lblValueLoiNhuan.getText().replace(",", ""))}, 
						lineChart, 
						barChart);
				}
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
