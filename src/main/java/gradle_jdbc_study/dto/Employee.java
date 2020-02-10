package gradle_jdbc_study.dto;

import javax.print.DocFlavor.BYTE_ARRAY;

public class Employee {
	private int empNo;
	private String empName;
	private int title;
	private int manager;
	private int salary;
	private Department dept;
	private BYTE_ARRAY pic = null;
	private String pass;
	
	public Employee() {
	}
	

	public Employee(int empNo) {
		this.empNo = empNo;
	}


	public Employee(Department dept) {
		this.dept = dept;
	}


	public Employee(int empNo, String empName, int title, int manager, int salary, Department dept, BYTE_ARRAY pic,
			String pass) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.pic = pic;
		this.pass = pass;
	}


	public int getEmpNo() {
		return empNo;
	}


	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public int getTitle() {
		return title;
	}


	public void setTitle(int title) {
		this.title = title;
	}


	public int getManager() {
		return manager;
	}


	public void setManager(int manager) {
		this.manager = manager;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	public BYTE_ARRAY getPic() {
		return pic;
	}


	public void setPic(BYTE_ARRAY pic) {
		this.pic = pic;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", title=" + title + ", manager=" + manager
				+ ", salary=" + salary + ", dept=" + dept + ", pic=" + pic + ", pass=" + pass + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
		result = prime * result + empNo;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dept == null) {
			if (other.dept != null)
				return false;
		} else if (!dept.equals(other.dept))
			return false;
		if (empNo != other.empNo)
			return false;
		return true;
	}
	
	
	
	
	
	
}
