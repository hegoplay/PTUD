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
	
	private List<Nav> listItem = null;
	
	public ChuyenManHinhController(JPanel root) {
		this.root = root;
	}
	
	public void setView(JPanel jpnItem,JLabel jlbItem) {
		kindSelected = "name_27209059966600";
		jpnItem.setBackground(new Color(96,100,191));
		jlbItem.setBackground(new Color(96,100,191));
		System.out.println(kindSelected);
//		chuyen sang man hinh trang chu
		((CardLayout)root.getLayout()).show(root, kindSelected);
	}
	
	public void setEvent(List<Nav> listItem) {
		this.listItem = listItem;
		for(Nav item :listItem) {
			item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
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
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			kindSelected = kind;
			jpnItem.setBackground(MainFrame.clrBtnHover);
			jlbItem.setBackground(MainFrame.clrBtnHover);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			jpnItem.setBackground(MainFrame.clrBtnHover);
			jlbItem.setBackground(MainFrame.clrBtnHover);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!kindSelected.equalsIgnoreCase(kind)) {
				jpnItem.setBackground(MainFrame.clrTheme);
				jlbItem.setBackground(MainFrame.clrTheme);
			}
		}
		
	}
	private void setChangeBackground(String kind) {
		for(Nav item: listItem) {
			if(item.getKind().equalsIgnoreCase(kind)) {
				item.getJpn().setBackground(MainFrame.clrBtnHover);
				item.getJlb().setBackground(MainFrame.clrBtnHover);
			}
			else {
				item.getJpn().setBackground(MainFrame.clrTheme);
				item.getJlb().setBackground(MainFrame.clrTheme);
			}
		}
	}
}
