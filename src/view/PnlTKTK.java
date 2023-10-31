package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import component.TblCTTK;

import javax.swing.JButton;
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

public class PnlTKTK extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTimTheoTen;
	private JTable tblCTTK;

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
		
		JDateChooser dateDauKy = new JDateChooser();
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
		
		JDateChooser dateCuoiKy = new JDateChooser();
		lblCuoiKy.setLabelFor(dateCuoiKy);
		pnlDateCuoiKy.add(dateCuoiKy, BorderLayout.CENTER);
		
		
		JPanel pnlXuatFile = new JPanel();
		pnlXuatFile.setBackground(MainFrame.clrTheme);
		pnlFirstLine.add(pnlXuatFile);
		FlowLayout fl_pnlXuatFile = new FlowLayout(FlowLayout.TRAILING, 5, 20);
		pnlXuatFile.setLayout(fl_pnlXuatFile);
		
		JButton btnXuatFile = new JButton("Xuất file");
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
		
		JLabel lblValTongSP = new JLabel("13134");
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
		
		JLabel lblValDaBan = new JLabel("1145");
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
		
		JLabel lblValNhapMoi = new JLabel("207");
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
		
		JLabel lblValConLai = new JLabel("12196");
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
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(MainFrame.clrCyan4);
		btnTim.setIcon(new ImageIcon(PnlTKTK.class.getResource("/view/icon/magnifying_glass_icon.png")));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnlHeader.add(btnTim);
		
		JScrollPane scrCTTK = new JScrollPane();
		pnlTable.add(scrCTTK, BorderLayout.CENTER);
		
		TblCTTK tblCTTK = new TblCTTK();
		tblCTTK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrCTTK.setViewportView(tblCTTK);

	}

}
