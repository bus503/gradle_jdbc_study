package gradle_jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_study.dto.Department;

public interface DepartmentDao {
	Department selectDepartmentByNo(Department department);
	
	List<Department> selectDepartmentByAll() throws SQLException;
	
	int insertDepartment(Department department);
	int deleteDepartment(Department department);
	int updateDepartment(Department department);
}
