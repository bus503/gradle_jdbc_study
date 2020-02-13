package gradle_jdbc_study.ui.service;

import java.util.List;

import gradle_jdbc_study.dao.DepartmentDao;
import gradle_jdbc_study.dao.EmployeeDao;
import gradle_jdbc_study.dao.impl.DepartmentDaoImpl;
import gradle_jdbc_study.dao.impl.EmployeeDaoImpl;
import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Department;
import gradle_jdbc_study.dto.Employee;
import gradle_jdbc_study.dto.Title;

public class EmployeeUIService {
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	private TitleDaoImpl titleDao;
	

	public EmployeeUIService() {
		empDao = EmployeeDaoImpl.getInstance();
		deptDao = DepartmentDaoImpl.getInstance();
		titleDao = TitleDaoImpl.getInstance();
	}
	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}

	public List<Employee> showManagerList(Department dept) {
		return empDao.selectEmployeeGroupByDno(dept);
	}

	public List<Title> showTitleList() {
		return titleDao.selectTitleByAll();
	}

	public List<Employee> showEmployeeList() {
		return empDao.selectEmployeeByAll();
	}

	public void removeEmployee(Employee emp) {
		empDao.deleteEmployee(emp);
	}

	public void modifyEmployee(Employee emp) {
		empDao.updateEmployee(emp);
	}

	public void addDepartment(Employee emp) {
		empDao.insertEmployee(emp);
	}
	public List<Employee> showEmployeesJoin() {
		return empDao.selectEmployeejoin();
	}
}
