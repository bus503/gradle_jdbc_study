package gradle_jdbc_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_jdbc_study.dao.TitleDao;
import gradle_jdbc_study.ds.MysqlDataSource;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.util.LogUtil;

public class TitleDaoImpl implements TitleDao {
	private static final TitleDaoImpl instance = new TitleDaoImpl();
	private TitleDaoImpl() {
	};
	public static TitleDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Title selectTitleByNo(Title title) {
		String sql = "select title_no, title_name from title where title_no=?";

		try (Connection con = MysqlDataSource.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, title.getTitleNo());
			try (ResultSet rs = pstmt.executeQuery()) {
			//	LogUtil.prnLog(pstmt);
				if (rs.next()) {
					return getTitle(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select title_no, title_name from title";

		try (Connection con = MysqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			List<Title> list = new ArrayList<>();

			while (rs.next()) {
				list.add(getTitle(rs));
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int titleNo = rs.getInt("title_no");
		String titleName = rs.getString("title_name");
		return new Title(titleNo, titleName);
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values(?,?)";
		int res =0;
		try(Connection con = MysqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setInt(1, title.getTitleNo());
				pstmt.setString(2, title.getTitleName());
				res = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return res;
		}

	@Override
	public int deleteTitle(Title title) {
		String sql = "delete from title where title_no =?";
		int res=-1;
		try(Connection con = MysqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, title.getTitleNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set title_name = ? where title_no= ?";
		int res = -1;
		try(Connection con = MysqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, title.getTitleName());
			pstmt.setInt(2, title.getTitleNo());
		
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
