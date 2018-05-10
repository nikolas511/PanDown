package cn.pjj.junit;

import org.junit.Test;

import cn.pjj.bean.User;
import cn.pjj.dao.UserDao;

public class TestDao {
	@Test
	public void TestfindUser(){
		UserDao ud = new UserDao();
		User user = new User();
		user = ud.findUser("aaa","123456");
		System.out.println(user.getUsername()+user.getPassword()+
				user.getBirthday()+user.getEmail()+user.getNickname());
	}
	@Test
	public void TestaddUser(){
		UserDao ud = new UserDao();
		User user = new User();
		user.setUsername("ddd");
		user.setPassword("123");
		user.setBirthday("1995-01-05");
		user.setEmail("123@qq.com");
		user.setNickname("刘婷婷");
		ud.addUser(user);
		user = ud.findUser("ccc","123");
		System.out.println(user.getUsername()+user.getPassword()+
				user.getBirthday()+user.getEmail()+user.getNickname());
	}
	@Test
	public void checkUser(){
		UserDao ud = new UserDao();
//		User user = new User();
		boolean flag = ud.checkUser("eee", 1, "2017-01-01");
		System.out.println(flag);
//		System.out.println(user.getUsername()+user.getPassword()+
//				user.getBirthday()+user.getEmail()+user.getNickname());
	}
	@Test
	public void changePassword(){
		UserDao ud = new UserDao();
		ud.changePassword("caa", "123456");
	}
	@Test
	public void testFind(){
		UserDao ud = new UserDao();
		System.out.println(ud.find("aaa"));
	}
}
