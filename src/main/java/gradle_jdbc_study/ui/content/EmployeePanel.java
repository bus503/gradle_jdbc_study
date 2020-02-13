package gradle_jdbc_study.ui.content;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.ui.exception.InvalidCheckException;
import gradle_jdbc_study.ui.listener.MyDocumentListener;
import gradle_jdbc_study.ui.service.EmployeeUIService;

@SuppressWarnings("serial")
public class EmployeePanel extends AbsItemPanel<Employee> implements ItemListener, ActionListener  {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Department> cmbDept;
	private JComboBox<Employee> cmbManager;
	private JComboBox<Title> cmbTitle;
	private JPasswordField pfPasswd;
	private JPasswordField pfPasswd2;
	private JLabel lblPasswdEqual;
	private Dimension picDimension=new Dimension(100, 150);
	private JLabel lblPic;
	private JButton btnPic;
	private JSpinner spSalary;
	private JDateChooser tfHireDate;
	private String picPath;
	private EmployeeUIService service;

	public EmployeePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
		pWest.setLayout(new BoxLayout(pWest, BoxLayout.Y_AXIS));
		
		lblPic = new JLabel();
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPic.setPreferredSize(picDimension);
		lblPic.setSize(picDimension);
		setPic(getClass().getClassLoader().getResource("no-image.png").getPath());
		pWest.add(lblPic);
		
		btnPic = new JButton("증명사진");
		btnPic.addActionListener(this);
		pWest.add(btnPic);
		
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblNo);
		
		tfNo = new JTextField();
		pCenter.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pCenter.add(tfName);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblDept);
		
		cmbDept = new JComboBox<>();

		pCenter.add(cmbDept);
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblManager);
		
		cmbManager = new JComboBox<>();
		cmbManager.addItemListener(this);
		pCenter.add(cmbManager);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblTitle);
		
		cmbTitle = new JComboBox<>();
		pCenter.add(cmbTitle);
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblHireDate);
		
		tfHireDate = new JDateChooser(new Date(), "yyyy-MM-dd hh:mm");
		pCenter.add(tfHireDate);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblSalary);
		
		spSalary = new JSpinner();
		spSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5500000, 100000));
		pCenter.add(spSalary);
		
		JLabel lblPasswd = new JLabel("비밀번호");
		lblPasswd.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblPasswd);
		
		pfPasswd = new JPasswordField();
		pfPasswd.getDocument().addDocumentListener(new MyDocumentListener() {
			
			@Override
			public void msg() {
				String pw1 = new String(pfPasswd.getPassword());
				String pw2 = new String(pfPasswd2.getPassword());
				if(pw1.length() ==0 || pw2.length() == 0) {
					lblPasswdEqual.setText("");
				}else if(pw1.contentEquals(pw2)) {
					lblPasswdEqual.setText("비밀번호일치");
				}else {
					lblPasswdEqual.setText("불일치");
				}
			}
		});
		pfPasswd.setText("");
		pCenter.add(pfPasswd);
		
		JLabel lblPasswd2 = new JLabel("비밀번호확인");
		lblPasswd2.setHorizontalAlignment(SwingConstants.RIGHT);
		pCenter.add(lblPasswd2);
		
		pfPasswd2 = new JPasswordField();
		pfPasswd2.getDocument().addDocumentListener(new MyDocumentListener() {
			
			@Override
			public void msg() {
				String pw1 = new String(pfPasswd.getPassword());
				String pw2 = new String(pfPasswd2.getPassword());
				if(pw1.length() ==0 || pw2.length() == 0) {
					lblPasswdEqual.setText("");
				}else if(pw1.contentEquals(pw2)) {
					lblPasswdEqual.setText("비밀번호일치");
				}else {
					lblPasswdEqual.setText("불일치");
				}
			}
		});
		
		DocumentListener docListener = new MyDocumentListener() {
			
			@Override
			public void msg() {
				// TODO Auto-generated method stub
				
			}
		};
		pfPasswd2.setText("");
		pCenter.add(pfPasswd2);
		
		JPanel panel = new JPanel();
		pCenter.add(panel);
		
		lblPasswdEqual = new JLabel("불일치");
		lblPasswdEqual.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblPasswdEqual.setHorizontalAlignment(SwingConstants.CENTER);
		pCenter.add(lblPasswdEqual);

	}

	public void setCmbDeptList(List<Department> deptList) {
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<>(new Vector<>(deptList));
		cmbDept.setModel(model);
		cmbDept.setSelectedIndex(-1);
	}
	
	public void setCmbManagerList(List<Employee> mgnList) {
		DefaultComboBoxModel<Employee> model = new DefaultComboBoxModel<>(new Vector<>(mgnList));
		cmbManager.setModel(model);
		cmbManager.setSelectedIndex(-1);
		
	}
	
	public void setCmbTitleList(List<Title> titleList) {
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<>(new Vector<>(titleList));
		cmbTitle.setModel(model);
		cmbTitle.setSelectedIndex(-1);
	}
	
	@Override
	public Employee getItem() {
		validCheck();
		int empNo = Integer.parseInt(tfNo.getText().trim());
		String empName = tfName.getText().trim();
		Title title =  (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbManager.getSelectedItem();
		int salary = (int)spSalary.getValue();		
		Department dept = (Department)cmbDept.getSelectedItem();
		String passwd = new String(pfPasswd.getPassword());
		Date hireDate = tfHireDate.getDate();
		byte[] pic = getImage();
		return new Employee(empNo, empName, title, manager, salary, dept, passwd, hireDate, pic);
	}

	private byte[] getImage() {
		byte[] pic = null;
		File file = new File(picPath);
		try (InputStream is = new FileInputStream(file)){
			pic = new byte[is.available()];
			is.read(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}

	@Override
	public void setItem(Employee item) {
		tfNo.setText(item.getEmpNo()+"");
		tfName.setText(item.getEmpName());
		cmbDept.setSelectedItem(item.getDept());
		cmbTitle.setSelectedItem(item.getTitle());//안되면 title equals 오버라이딩이 안되어있는거임
		cmbManager.setSelectedItem(item.getManager());
		spSalary.setValue(item.getSalary());		
		pfPasswd.setText("");
		pfPasswd2.setText("");
		tfHireDate.setDate(item.getHireDate());
		if(item.getPic()== null){
			setPic(getClass().getClassLoader().getResource("no-image.png").getPath());
		}else {
			setPic(item.getPic());
		}
		
		lblPasswdEqual.setText("");
		byte[] pic = getImage();
	}
	
	
	private void setPic(byte[] byteImg) {
			
		lblPic.setIcon(new ImageIcon(new ImageIcon(byteImg).getImage().getScaledInstance((int)picDimension.getWidth(),(int)picDimension.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	private void setPic(String imgPath) {
		picPath = imgPath;
		lblPic.setIcon(new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance((int)picDimension.getWidth(),(int)picDimension.getHeight(), Image.SCALE_DEFAULT)));
	}

	@Override
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		cmbTitle.setSelectedIndex(-1);
		cmbManager.setSelectedIndex(-1);
		spSalary.setValue(-1);		
		cmbDept.setSelectedIndex(-1);
		pfPasswd.setText("");
		pfPasswd2.setText("");
		tfHireDate.setDate(new Date());
		setPic(getClass().getClassLoader().getResource("no-image.png").getPath());
		lblPasswdEqual.setText("");
		byte[] pic = getImage();
	}

	@Override
	public void validCheck() {
		//입력한 값들이 있어야 하고 콤보박스 선택안되어있으면 -1 입사일은 체크할 필요없고 급여도, 비밀번호는 일치하는지만 체크 증명사진 없으면 없는 사진 들어가면 되고 있으면 있는사진들어가면됨
		if(tfNo.getText().equals("")||tfName.getText().contentEquals("")||
				cmbDept.getSelectedIndex() ==-1 || cmbManager.getSelectedIndex()== -1 ||
				cmbTitle.getSelectedIndex() == -1 ||
				!lblPasswdEqual.getText().equals("비밀번호일치")) {
			throw new InvalidCheckException();
		}
		
	}
	

	public JComboBox<Department> getCmbDept() {
		return cmbDept;
	}

	

	public JComboBox<Employee> getCmbManager() {
		return cmbManager;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}


	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPic) {
			btnPicActionPerformed(e);
		}
	}
	protected void btnPicActionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG or PNG or GIF", "jpg","png","gif");
		chooser.setFileFilter(filter);
		
		int res = chooser.showOpenDialog(null);
		
	    if(res != JFileChooser.APPROVE_OPTION) {
	    	JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다","경고",JOptionPane.WARNING_MESSAGE);
	    	return;
	    }
	    picPath = chooser.getSelectedFile().getPath();
	    setPic(picPath); //만든메소드
	}

	

	public void setService(EmployeeUIService service2) {
		this.service = service2;
		setCmbDeptList(service2.showDeptList());
		setCmbTitleList(service2.showTitleList());
		cmbDept.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() ==ItemEvent.SELECTED) {
					setCmbManagerList(service2.showManagerList((Department)cmbDept.getSelectedItem()));
				}
				
			}
		});
	}
}
