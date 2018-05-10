package cn.pjj.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.pjj.bean.User;
import cn.pjj.utils.JdbcUtils;

public class UserDao {
	//实现查找用户是否存在
	public boolean find(String username){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "SELECT username FROM users WHERE username='"+username+"'";
		try {
			User user = runner.query(sql,new BeanHandler<User>(User.class));
			if(user==null)
			return false;//false不存在
			else return true;//true存在
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//实现注册添加用户
	public void addUser(User user){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "INSERT INTO users VALUES(?,?,?,?,?)";
		try {
			runner.update(sql, user.getUsername(),user.getPassword(),user.getBirthday(),user.getEmail(),user.getNickname());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//实现用户的登录查找
	public User findUser(String username,String password){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "SELECT * FROM users WHERE username='"+username+"'and password='"+password+"'";
		try {
			//将查找出来的数据封装到javabean中
			User user = runner.query(sql,new BeanHandler<User>(User.class));
			if(user!=null)
			return user;
			else return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//确认用户信息,返回boolean值确认是否是用户本人
	public boolean checkUser(String username,int index,String answer){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String[] str = {"birthday","email","nickname"};//随机选择一个问题问用户index记录问题
		String s = str[index];//记录问题去数据库中匹配答案
		String sql = "SELECT * FROM users WHERE username='"+username+"'and "+s+"='"+answer+"'";
		try {
			//将查找出来的数据封装到javabean中
			User user = runner.query(sql,new BeanHandler<User>(User.class));
			if(user!=null)
			return true;//信息匹配true
			else return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//实现修改密码的功能
	public void changePassword(String username,String password){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "UPDATE users SET password='"+password+"' WHERE username='"+username+"'";
		try {
			runner.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
