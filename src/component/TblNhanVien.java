package component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TblNhanVien extends JTable{
	private static final int 
			stt = 1 << 0,
			maNV = 1 << 1,
			tenNV = 1 << 2,
			ngaySinh = 1 << 3,
			gioiTinh = 1 << 4,
			slHoaDon = 1 << 5,
			sanPhamDaBan =1 << 6,
			doanhThu = 1 << 7;
	private static final String[] title = new String[] {
		"STT","Mã NV", "Tên NV", "Ngày sinh","Giới tính","Hóa đơn đã lập","Sản phẩm đã bán","Doanh thu"
	};
	private TableRowSorter<TableModel> sorter;
	private DefaultTableModel model;
	public TblNhanVien() {
		model = new DefaultTableModel(title,0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				int mask = 1 << columnIndex ;
				if ((mask & (slHoaDon | sanPhamDaBan)) != 0) {
					return Integer.class;
				}
				else if ((mask & doanhThu) != 0)
					return Double.class;
				return String.class;
			}
		};
		this.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		
	}
	
	public TableModel getTableModel(){
		return this.model;
	}
	public void ResetAllRow() {
		model.setRowCount(0);
	}
}
