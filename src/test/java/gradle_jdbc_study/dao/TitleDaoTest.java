package gradle_jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_jdbc_study.dao.impl.TitleDaoImpl;
import gradle_jdbc_study.dto.Title;
import gradle_jdbc_study.util.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		//메소드의 이름이 나오게 하는거
		dao = TitleDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	}

	@Test
	public void test01SelectTitleByNo() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = dao.selectTitleByNo(new Title(3));
		Title res = new Title(3, "과장");
		Assert.assertNotEquals(res, 3);
		LogUtil.prnLog(title);
	}

	@Test
	public void test02SelectTitleByAll() throws SQLException {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Title> lists = dao.selectTitleByAll();
		Assert.assertNotNull(lists);
		
		for(Title t :lists) LogUtil.prnLog(t);
	}

	@Test
	public void test03InsertTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = new Title(6,"팀장");
		int res = dao.insertTitle(title);
		Assert.assertNotEquals(0, res);
		LogUtil.prnLog(title);
		
	}

	@Test
	public void test05DeleteTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Title title = new Title(6);
		int res = dao.deleteTitle(title);
		Assert.assertNotEquals(-1, res);
		LogUtil.prnLog(title);
	}

	@Test
	public void test04UpdateTitle() {
		LogUtil.prnLog(Thread.currentThread().getStackTrace()[1].getMethodName() + "()"); 
		Title title = new Title(6,"낙하산");
		int res = dao.updateTitle(title);
		Assert.assertNotEquals(-1, res);
		LogUtil.prnLog(title);
	}

}
