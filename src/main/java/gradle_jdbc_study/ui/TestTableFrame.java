package gradle_jdbc_study.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.list.DepartmentTblPanel;
import gradle_jdbc_study.ui.list.TitleTblPanel;

public class TestTableFrame {
	private static DepartmentTblPanel tp;
	private static TitleTblPanel titleP;
	
	public static void main(String[] args) {
//		departmentTableTest();
		
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		titleP = new TitleTblPanel();
		frame.add(titleP);
		List<Title> list = TitleDaoImpl.getInstance().selectTitleByAll();
		titleP.loadData(list);
		
		titleP.setPopupMenu(createTitlePopupMenu());
		frame.setVisible(true);
	}

	private static JPopupMenu createTitlePopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopTitleMenuListener);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopTitleMenuListener);
		popMenu.add(deleteItem);

		JMenuItem addItem = new JMenuItem("추가");
		addItem.addActionListener(myPopTitleMenuListener);
		popMenu.add(addItem);

		return popMenu;
	}

	private static void departmentTableTest() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		tp = new DepartmentTblPanel();
		frame.add(tp);
		List<Department> list = DepartmentDaoImpl.getInstance().selectDepartmentByAll();
		tp.loadData(list);
		
		tp.setPopupMenu(createDeptPopupMenu());
		frame.setVisible(true);
	}
	
	static ActionListener myPopTitleMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().contentEquals("수정")) {
				Title updateTitle = new Title(1, "이사장");
				titleP.updateRow(updateTitle, titleP.getSelectedRowIdx());
			}
			
			if (e.getActionCommand().contentEquals("삭제")) {
				Title selectedTitle = titleP.getSelectedItem();
				JOptionPane.showMessageDialog(null, selectedTitle);
				
				titleP.removeRow();
			}
			if (e.getActionCommand().contentEquals("추가")) {
				Title insTitle = new Title(5, "인턴");
				titleP.addItem(insTitle);
			}			
		}
	};
	
	
	static ActionListener myPopDeptMenuListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().contentEquals("수정")) {
				Department updateDept = new Department(5, "마케팅", 10);
				tp.updateRow(updateDept, tp.getSelectedRowIdx());
			}
			if (e.getActionCommand().contentEquals("삭제")) {
				Department selectedDept = tp.getSelectedItem();
				JOptionPane.showMessageDialog(null, selectedDept);
				
				tp.removeRow();
				
				Department insDept = new Department(5, "마케팅", 10);
				tp.addItem(insDept);
				
			}
			if (e.getActionCommand().contentEquals("소속 사원")) {
				JOptionPane.showMessageDialog(null, e.getActionCommand());
			}
		}
	};

	private static JPopupMenu createDeptPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(myPopDeptMenuListener);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(myPopDeptMenuListener);
		popMenu.add(deleteItem);

		JMenuItem showEmployee = new JMenuItem("소속 사원");
		showEmployee.addActionListener(myPopDeptMenuListener);
		popMenu.add(showEmployee);

		return popMenu;
	}
}
