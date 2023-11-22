package component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import entity.NhanVien;

public class TblNhanVien extends JTable{
	private static final int 
			stt = 1 << 0,
			maNV = 1 << 1,
			tenNV = 1 << 2,
			gioiTinh = 1 << 3,
			slHoaDon = 1 << 4,
			sanPhamDaBan =1 << 5,
			doanhThu = 1 << 6;
	private static final String[] title = new String[] {
		"STT","Mã NV", "Tên NV","Giới tính","Hóa đơn đã lập","Sản phẩm đã bán","Doanh thu"
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
				else if((mask & gioiTinh) !=0) {
					return Boolean.class;
				}
				return String.class;
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		this.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		this.getColumnModel().getColumn(0).setPreferredWidth(20);
	}
	
	public TableModel getTableModel(){
		return this.model;
	}
	public void removeAllRow() {
		model.setRowCount(0);
	}
	public void addRow(String stt, NhanVien nv, int slHoaDonNhap,int soSPBan,double doanhThu) {
		model.addRow(new Object[] {stt,nv.getMaNV(),nv.getTen(),
				nv.isNam(),slHoaDonNhap, soSPBan,doanhThu});
	}
	public Object getValue(int row, int column) {
		return model.getValueAt(row, column);
	}
}
