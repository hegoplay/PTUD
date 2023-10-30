package component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TblCTTK extends JTable{
	private static final int 
			stt = 1 << 0,
			maSP = 1 << 1,
			tenSP = 1 << 2,
			loaiSP = 1 << 3,
			kichThuoc = 1 << 4,
			mauSac = 1 << 5,
			soLuong =1 << 6,
			daBan = 1 << 7,
			nhapMoi = 1 << 8,
			conLai = 1 << 9;
	private static final String[] title = new String[] {
		"STT","Mã SP", "Tên SP", "Loại","Kích thước","Màu sác","Số lượng","Đã bán","Nhập mới","Còn lại"
	};
	private TableRowSorter<TableModel> sorter;
	private DefaultTableModel model;
	public TblCTTK() {
		model = new DefaultTableModel(title,0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				int mask = 1 << columnIndex;
				if ((mask & (soLuong | daBan | nhapMoi | conLai)) != 0) {
					return Integer.class;
				}
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
