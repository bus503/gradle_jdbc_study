package gradle_jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.content.TitlePanel;
import gradle_jdbc_study.ui.exception.InvalidCheckException;
import gradle_jdbc_study.ui.list.TitleTblPanel;
import gradle_jdbc_study.ui.service.TitleUiService;

@SuppressWarnings("serial")
public class TitleUIPanel extends JPanel implements ActionListener {
	private TitleUiService service;
	private TitleTblPanel pTitleList;
	private TitlePanel pTitle;
	private DlgEmployee dialog;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	public TitleUIPanel() {
		service = new TitleUiService();
		dialog = new DlgEmployee();
		initialize();
	}
	private void initialize() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new BorderLayout(0, 0));
		
		pTitle = new TitlePanel();
		pContent.add(pTitle, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		add(pBtns);
		
//		TitlePanel pTitle = new TitlePanel();
//		pContent.add(pTitle, BorderLayout.CENTER);
//		pTitle.setLayout(new GridLayout(0, 2, 10, 0));
		
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		JPanel pList = new JPanel();
		add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
//		TitleTblPanel pTitleList = new TitleTblPanel();
//		pList.add(pTitleList, BorderLayout.CENTER);
//		pTitleList.setLayout(new BorderLayout(0, 0));
		
		pTitleList = new TitleTblPanel();
		pTitleList.loadData(service.showTitleList());
		pTitleList.setPopupMenu(createPopMenu());
		pList.add(pTitleList, BorderLayout.CENTER);
		
	}

	private JPopupMenu createPopMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(MyPopupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(MyPopupMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem showEmployee = new JMenuItem("소속 사원");
		showEmployee.addActionListener(MyPopupMenuListener);
		popMenu.add(showEmployee);
		
		return popMenu;
	}
	
	ActionListener MyPopupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("수정")) {
				Title upTitle = pTitleList.getSelectedItem();
				pTitle.setItem(upTitle);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().equals("삭제")) {
				Title delTitle = pTitleList.getSelectedItem();
				service.removeTitle(delTitle);
				pTitleList.removeRow();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			}
			if(e.getActionCommand().equals("소속 사원")) {
				Title selectedTitle = pTitleList.getSelectedItem();
				List<Employee> list = service.showEmployeeGroupByTno(selectedTitle);
				dialog.setEmpList(list);
				dialog.setTitle(selectedTitle.getTitleName() + "직책");
				
				dialog.setVisible(true);
				
			}
		}
	};
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("추가")) {
				btnAddActionPerformed(e);
			}else{
				btnUpdateActionPerformed(e);
			}
		}
		if(e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}

	private void btnUpdateActionPerformed(ActionEvent e) {
		Title newTitle = pTitle.getItem();
		service.modifyTitle(newTitle);
		pTitleList.updateRow(newTitle, pTitleList.getSelectedRowIdx());
		btnAdd.setText("추가");
		pTitle.clearTf();
		JOptionPane.showMessageDialog(null, "직책이 수정되었습니다.");
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		try {
			Title newTitle = pTitle.getItem();
			service.addTitle(newTitle);
			pTitleList.addItem(newTitle);
			pTitle.clearTf();
			JOptionPane.showMessageDialog(null, "부서가 추가되었습니다.");
		}catch(InvalidCheckException e1){
			JOptionPane.showMessageDialog(null, e1.getMessage());
			
		}catch(Exception e1) {
			SQLException e2 = (SQLException) e1;
			if(e2.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "직책번호가 중복됩니다.");
				System.err.println(e2.getMessage());
				return;
			}
			e1.printStackTrace();
		}
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		pTitle.clearTf();
	}
}
