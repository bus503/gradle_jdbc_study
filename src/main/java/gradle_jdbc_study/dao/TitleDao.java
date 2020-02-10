package gradle_jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_study.dto.Title;

public interface TitleDao {
	Title selectTitleByNo(Title title);
	
	List<Title> selectTitleByAll() throws SQLException;
	
	int insertTitle(Title title);
	int deleteTitle(Title title);
	int updateTitle(Title title);
}
