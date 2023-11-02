package controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import component.Nav;
import view.MainFrame;

public class ChuyenManHinhController {
	private JPanel root;
	private String kindSelected = "";
	private Color clrIdle;
	private Color clrHover;
	private List<Nav> listItem = null;
	private JLabel lblTitle = null;
	
	public ChuyenManHinhController(JPanel root, Color clrIdle, Color clrHover) {
		this.root = root;
		this.clrIdle = clrIdle;
		this.clrHover = clrHover;
	}
	
	public void setView(String kindSelected,JPanel jpnItem,JLabel jlbItem) {
		this.kindSelected = kindSelected;
		jpnItem.setBackground(clrHover);
		jlbItem.setBackground(clrHover);
		changeTitle(this.kindSelected);
//		System.out.println(this.kindSelected);
//		chuyen sang man hinh trang chu
		((CardLayout)root.getLayout()).show(root, this.kindSelected);
	}
	
	public void setEvent(List<Nav> listItem) {
		this.listItem = listItem;
		
		
		for(Nav item :listItem) {
			item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
		}
		
	}
	
	private void changeTitle(String kindSelected) {
		// TODO Auto-generated method stub
		if (lblTitle!=null) {
			lblTitle.setText(kindSelected);
		}
	}
	
	class LabelEvent implements MouseListener{
		
		private JPanel node;
		
		private String kind;
		private JPanel jpnItem;
		private JLabel jlbItem;
		
		
		public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
			this.kind = kind;
			this.jpnItem = jpnItem;
			this.jlbItem = jlbItem;
			
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			((CardLayout)root.getLayout()).show(root, kindSelected);
			setChangeBackground(kind);
			changeTitle(kindSelected);
		}

		


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			kindSelected = kind;
			jpnItem.setBackground(clrHover);
			jlbItem.setBackground(clrHover);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			jpnItem.setBackground(clrHover);
			jlbItem.setBackground(clrHover);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!kindSelected.equalsIgnoreCase(kind)) {
				jpnItem.setBackground(clrIdle);
				jlbItem.setBackground(clrIdle);
			}
		}
		
	}
	private void setChangeBackground(String kind) {
		for(Nav item: listItem) {
			if(item.getKind().equalsIgnoreCase(kind)) {
				item.getJpn().setBackground(clrHover);
				item.getJlb().setBackground(clrHover);
			}
			else {
				item.getJpn().setBackground(clrIdle);
				item.getJlb().setBackground(clrIdle);
			}
		}
	}
	public void setLbl(JLabel lbl) {
		this.lblTitle = lbl;
	}
}
