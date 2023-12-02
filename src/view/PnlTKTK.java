package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import component.TblCTTK;
import controller.ToPDFController;
import dao.HoaDonDAO;
import dao.LSTTonDAO;
import dao.TraHangDAO;
import entity.LishSuTon;
import entity.NguoiQuanLy;
import entity.SanPham;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PnlTKTK extends JPanel implements ActionListener, PropertyChangeListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtTimTheoTen;
	private TblCTTK tblCTTK;
	private JDateChooser dateCuoiKy;
	private JDateChooser dateDauKy;
	private JLabel lblValConLai;
	private JLabel lblValNhapMoi;
	private JLabel lblValDaBan;
	private JLabel lblValTongSP;
	private JButton btnTim;
	private ArrayList<SanPham> dsSP;
	private LocalDate ldDauKy;
	private LocalDate ldCuoiKy;
	private JButton btnXuatFile;

	/**
	 * Create the panel.
	 */
	public PnlTKTK() {
		setBackground(MainFrame.clrTheme);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBorder(new EmptyBorder(0, 0, 20, 0));
		pnlInfo.setBackground(MainFrame.clrTheme);
		add(pnlInfo, BorderLayout.NORTH);
		pnlInfo.setLayout(new GridLayout(2, 1, 0, 15));
		
		JPanel pnlFirstLine = new JPanel();
		pnlFirstLine.setBackground(MainFrame.clrTheme);
		pnlInfo.add(pnlFirstLine);
		pnlFirstLine.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pnlDate = new JPanel();
		pnlDate.setBackground(MainFrame.clrTheme);
		pnlFirstLine.add(pnlDate);
		pnlDate.setLayout(new GridLayout(1, 4, 10, 0));
		
		JLabel lblDauKy = new JLabel("Ngày đầu kỳ:");
		lblDauKy.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDate.add(lblDauKy);

		JPanel pnlDateDauKy = new JPanel();
		pnlDateDauKy.setBackground(MainFrame.clrTheme);
		pnlDateDauKy.setBorder(new EmptyBorder(20, 0, 20, 0));
		pnlDate.add(pnlDateDauKy);
		pnlDateDauKy.setLayout(new BorderLayout(0, 0));
		
		dateDauKy = new JDateChooser();
		dateDauKy.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDauKy.setLabelFor(dateDauKy);
		pnlDateDauKy.add(dateDauKy);
		
		JLabel lblCuoiKy = new JLabel("Ngày cuối kỳ: ");
		lblCuoiKy.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlDate.add(lblCuoiKy);
		
		JPanel pnlDateCuoiKy = new JPanel();
		pnlDateCuoiKy.setBackground(MainFrame.clrTheme);
		pnlDateCuoiKy.setBorder(new EmptyBorder(20, 0, 20, 0));
		pnlDate.add(pnlDateCuoiKy);
		pnlDateCuoiKy.setLayout(new BorderLayout(0, 0));
		
		dateCuoiKy = new JDateChooser();
		dateCuoiKy.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCuoiKy.setLabelFor(dateCuoiKy);
		pnlDateCuoiKy.add(dateCuoiKy, BorderLayout.CENTER);
		
		
		JPanel pnlXuatFile = new JPanel();
		pnlXuatFile.setBackground(MainFrame.clrTheme);
		pnlFirstLine.add(pnlXuatFile);
		FlowLayout fl_pnlXuatFile = new FlowLayout(FlowLayout.TRAILING, 5, 20);
		pnlXuatFile.setLayout(fl_pnlXuatFile);
		
		btnXuatFile = new JButton("Xuất file PDF");
		btnXuatFile.setBackground(MainFrame.clrCyan4);
		btnXuatFile.setForeground(new Color(255, 255, 255));
		btnXuatFile.setIcon(new ImageIcon(PnlTKTK.class.getResource("/view/icon/file_icon.png")));
		btnXuatFile.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlXuatFile.add(btnXuatFile);
		
		JPanel pnlSecondLine = new JPanel();
		pnlSecondLine.setBackground(MainFrame.clrTheme);
		pnlSecondLine.setBorder(new EmptyBorder(0, 15, 0, 15));
		pnlInfo.add(pnlSecondLine);
		pnlSecondLine.setLayout(new GridLayout(1, 4, 90, 0));
		
		JPanel pnlTongSP = new JPanel();
		pnlTongSP.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnlTongSP.setBackground(MainFrame.clrYellow2);
		pnlSecondLine.add(pnlTongSP);
		pnlTongSP.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblTongSP = new JLabel("Tổng sản phẩm");
		lblTongSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongSP.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTongSP.add(lblTongSP);
		
		lblValTongSP = new JLabel("13134");
		lblValTongSP.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblValTongSP.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTongSP.add(lblValTongSP);
		
		JPanel pnlDaBan = new JPanel();
		pnlDaBan.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnlDaBan.setBackground(MainFrame.clrYellow2);
		pnlSecondLine.add(pnlDaBan);
		pnlDaBan.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblDaBan = new JLabel("Đã bán");
		lblDaBan.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaBan.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlDaBan.add(lblDaBan);
		
		lblValDaBan = new JLabel("1145");
		lblValDaBan.setHorizontalAlignment(SwingConstants.CENTER);
		lblValDaBan.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlDaBan.add(lblValDaBan);
		
		JPanel pnlNhapMoi = new JPanel();
		pnlNhapMoi.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnlNhapMoi.setBackground(MainFrame.clrYellow2);
		pnlSecondLine.add(pnlNhapMoi);
		pnlNhapMoi.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNhapMoi = new JLabel("Nhập mới");
		lblNhapMoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapMoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlNhapMoi.add(lblNhapMoi);
		
		lblValNhapMoi = new JLabel("207");
		lblValNhapMoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblValNhapMoi.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlNhapMoi.add(lblValNhapMoi);
		
		JPanel pnlConLai = new JPanel();
		pnlConLai.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnlConLai.setBackground(MainFrame.clrYellow2);
		pnlSecondLine.add(pnlConLai);
		pnlConLai.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblConLai = new JLabel("Còn Lại");
		lblConLai.setHorizontalAlignment(SwingConstants.CENTER);
		lblConLai.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlConLai.add(lblConLai);
		
		lblValConLai = new JLabel("12196");
		lblValConLai.setHorizontalAlignment(SwingConstants.CENTER);
		lblValConLai.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnlConLai.add(lblValConLai);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(MainFrame.clrCyan2);
		pnlTable.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64)), "Chi ti\u1EBFt t\u1ED3n kho", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		add(pnlTable, BorderLayout.CENTER);
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(MainFrame.clrCyan2);
		FlowLayout fl_pnlHeader = (FlowLayout) pnlHeader.getLayout();
		fl_pnlHeader.setHgap(15);
		fl_pnlHeader.setAlignment(FlowLayout.RIGHT);
		pnlTable.add(pnlHeader, BorderLayout.NORTH);
		
		JLabel lblTKTheoMa = new JLabel("Tìm kiếm theo mã: ");
		lblTKTheoMa.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlHeader.add(lblTKTheoMa);
		
		txtTimTheoTen = new JTextField();
		txtTimTheoTen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlHeader.add(txtTimTheoTen);
		txtTimTheoTen.setColumns(15);
		
		btnTim = new JButton("Tìm");
		btnTim.setBackground(MainFrame.clrCyan4);
		btnTim.setIcon(new ImageIcon(PnlTKTK.class.getResource("/view/icon/magnifying_glass_icon.png")));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlHeader.add(btnTim);
		
		JScrollPane scrCTTK = new JScrollPane();
		pnlTable.add(scrCTTK, BorderLayout.CENTER);
		
		tblCTTK = new TblCTTK();
		tblCTTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrCTTK.setViewportView(tblCTTK);
		
//		them su kien cac btn
		
		setJDate();
		dateDauKy.getDateEditor().addPropertyChangeListener(this);
		dateCuoiKy.getDateEditor().addPropertyChangeListener(this);
//		cai dat ngay mac dinh cua lich
		
		
		
		btnTim.addActionListener(this);
		txtTimTheoTen.addKeyListener(this);
		btnXuatFile.addActionListener(this);
		
	}
	
	
	private void setJDate() {

		
		
//		han che ngay cua lich
		dateCuoiKy.setMaxSelectableDate(new Date());
		dateDauKy.setMaxSelectableDate(new Date());
		dateCuoiKy.setMinSelectableDate(dateDauKy.getDate());
		dateCuoiKy.setDate(new Date());
		dateDauKy.setDate(new Date());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if (e.getSource() == btnTim) {
			TimSP();
		}
		if (e.getSource() == btnXuatFile) {
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(MainFrame.PdfPath));
				fileChooser.setDialogTitle("Chọn vị trí muốn lưu");   
				
				int userSelection = fileChooser.showSaveDialog(this);
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					ToPDFController.xuatTKTK(
							fileToSave.getAbsolutePath(), 
							ldDauKy, 
							ldCuoiKy,
							(NguoiQuanLy)MainFrame.nv , 
							tblCTTK, 
							new int[] {Integer.parseInt( lblValTongSP.getText()),
									Integer.parseInt( lblValDaBan.getText()),
									Integer.parseInt( lblValNhapMoi.getText()),
									Integer.parseInt( lblValConLai.getText())});
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void TimSP() {
		try {
			int index = dsSP.indexOf(new SanPham(txtTimTheoTen.getText()));
			if (index == -1) {
				throw new Exception("Khong tim thay san pham");
			}
			tblCTTK.setRowSelectionInterval(index, index);
			tblCTTK.scrollRectToVisible(new Rectangle(tblCTTK.getCellRect(index, 0, true)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		Object obj = evt.getSource();
		if (dateDauKy.getDate() == null || dateCuoiKy.getDate() == null) {
			return;
		}
		if (obj == dateDauKy.getDateEditor() &&  !dateDauKy.getDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().equals(ldDauKy)) {
			SetDate();
			dateCuoiKy.setMinSelectableDate(dateDauKy.getDate());
			LoadTable();
			
		}
		else if(obj==dateCuoiKy.getDateEditor() && !dateCuoiKy.getDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().plusDays(1).equals(ldCuoiKy)) {
			SetDate();
			LoadTable();
		}
	}

	private void SetDate() {
		if (dateDauKy.getDate() == null) {
			ldDauKy = LocalDate.now().plusYears(10);
		}
		else {
			ldDauKy = dateDauKy.getDate().toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
			
		}
		if (dateCuoiKy.getDate() == null) {
			ldCuoiKy = LocalDate.now().minusYears(10);
		}
		else
			ldCuoiKy = dateCuoiKy.getDate().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		ldCuoiKy = ldCuoiKy.plusDays(1);
	}
	
	private void LoadTable() {
		tblCTTK.removeAllRow();
		// TODO Auto-generated method stub
//		dateDauKy.getDate() = dateDauKy.getDate() == null ? LocalDate.now() : dateDauKy.getDate()
//		Lấy các sản phẩm trong hóa đơn có trong ngày cần kiểm tra
		dsSP = HoaDonDAO.GetSanPhamInDate(ldDauKy,ldCuoiKy);
//		Thêm các sản phẩm được nhập mới trong ngày
		for (SanPham sp : LSTTonDAO.GetSanPhamInDate(ldDauKy, ldCuoiKy)) {
			if (!dsSP.contains(sp))
				dsSP.add(sp);
		}
//		Sắp xếp mã sản phẩm tăng dần
		dsSP.sort(new Comparator<SanPham>() {

			@Override
			public int compare(SanPham o1, SanPham o2) {
				// TODO Auto-generated method stub
				return o1.getMaSP().compareToIgnoreCase(o2.getMaSP());
			}
		});
		
		int temp = 0;
		int tongConLai = 0;
		int tongTang = 0;
		int tongBan = 0;
		int tongSanPham = 0;
		
		for (SanPham x :dsSP) {
			int slTang = LSTTonDAO.GetSLTang(x, ldDauKy, ldCuoiKy);
			int slBan = HoaDonDAO.GetSLSanPham(x, ldDauKy, ldCuoiKy);
			tblCTTK.addRow(String.format("%03d", temp),x.getMaSP(),x.getTenSP(),x.getLoaiSP().getTenLoai(),x.getKichThuoc(),
					x.getMauSac(),slBan + x.getSlTonKho(),slBan,slTang,x.getSlTonKho());
			temp++;
			tongConLai += x.getSlTonKho();
			tongTang +=slTang;
			tongBan +=slBan;
			tongSanPham+= x.getSlTonKho() + slBan;
		}
		lblValConLai.setText(tongConLai + "");
		lblValNhapMoi.setText(tongTang + "");
		lblValDaBan.setText(tongBan+"");
		lblValTongSP.setText("" + tongSanPham);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			TimSP();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
