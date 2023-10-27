package component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Nav {
	private String kind;
	private JPanel jpn;
	private JLabel jlb;
	public Nav() {
		
	}
	public Nav(String kind, JPanel jpn, JLabel jlb) {
		this.setKind(kind);
		this.setJpn(jpn);
		this.setJlb(jlb);
	}
	public JPanel getJpn() {
		return jpn;
	}
	public void setJpn(JPanel jpn) {
		this.jpn = jpn;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public JLabel getJlb() {
		return jlb;
	}
	public void setJlb(JLabel jlb) {
		this.jlb = jlb;
	}
}
