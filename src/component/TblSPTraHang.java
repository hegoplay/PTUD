package component;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TblSPTraHang extends JTable{
	private static final int 
			stt = 1 << 0,
			maSP = 1 << 1,
			tenSP = 1 << 2,
			DonGia = 1 << 3,
			slSP = 1 << 4,
			ThanhTien = 1 << 5;
	private static final String[] title = new String[] {
		"STT","Mã SP","Tên Sản Phẩm","Đơn giá(VNĐ)","SL","Thành tiền(VNĐ)"
	};
	private ArrayList<String> MaSPList;
	private TableRowSorter<TableModel> sorter;
	private DefaultTableModel model;
	public TblSPTraHang() {
		model = new DefaultTableModel(title,0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				int mask = 1 << columnIndex;
				if ((mask & slSP ) != 0) {
					return Integer.class;
				}
				else if ((mask & (DonGia | ThanhTien)) != 0)
					return Double.class;
				return String.class;
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		this.setModel(model);
		this.setRowSelectionAllowed(true);
		
		sorter = new TableRowSorter<TableModel>(model);
		MaSPList = new ArrayList<>();
		
		this.getColumnModel().getColumn(0).setPreferredWidth(20);
		this.getColumnModel().getColumn(1).setPreferredWidth(50);
		this.getColumnModel().getColumn(4).setPreferredWidth(25);
	}
	
	public TableModel getTableModel(){
		return this.model;
	}
	
	public void removeAllRow() {
		model.setRowCount(0);
	}
	public void addRow(String stt, String maSP, String tenSP, double donGia, int soLuong, double thanhTien) {
		model.addRow(new Object[] {stt,maSP,tenSP,donGia,soLuong,thanhTien});
		MaSPList.add(tenSP);
	}
	public boolean checkMaSP(String maSP) {
		return MaSPList.contains(maSP);
	}
	public void increaseValue(String maSP,int column, int valueIncrease) throws Exception {
		if (!MaSPList.contains(maSP)) {
			throw new Exception("maSP khong ton tai");
		}
		int row = MaSPList.indexOf(maSP);
		model.setValueAt(row, column, (int) model.getValueAt(row, column) + valueIncrease); ;
		
	}
	
}
