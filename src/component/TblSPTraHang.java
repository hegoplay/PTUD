package component;

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
		};
		this.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		this.getColumnModel().getColumn(0).setPreferredWidth(20);
		this.getColumnModel().getColumn(1).setPreferredWidth(50);
		this.getColumnModel().getColumn(4).setPreferredWidth(25);
	}
	
	public TableModel getTableModel(){
		return this.model;
	}
}
