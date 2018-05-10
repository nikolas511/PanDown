package cn.pjj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.pjj.bean.Upfile;
import cn.pjj.bean.QueryResult;
import cn.pjj.dao.UpfileDao;
import cn.pjj.utils.JdbcUtils;

public class UpfileDaoImpl implements UpfileDao {
	public void add(Upfile upfile){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into upfile(id,uuidname,filename,savepath,uptime,description,username) values(?,?,?,?,?,?,?)";
		Object params[]={upfile.getId(),upfile.getUuidname(),upfile.getFilename(),upfile.getSavepath(),upfile.getUptime(),upfile.getDescription(),upfile.getUsername()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Upfile find(String id){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from upfile where id=?";
		try {
			return runner.query(sql,id,new BeanHandler<Upfile>(Upfile.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Upfile> getAll(){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from upfile";
		try {
			return runner.query(sql,new BeanListHandler<Upfile>(Upfile.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(String id){
		QueryRunner runner = new QueryRunner();
		String sql ="delete from upfile where id=?";
		try {
			runner.update(JdbcUtils.getThreadLocal().get(), sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(Upfile upfile){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "update upfile set uuidname=?,filename=?,savepath=?,uptime=?,description=?,username=? where id=?";
		Object params[]={upfile.getUuidname(),upfile.getFilename(),upfile.getSavepath(),upfile.getUptime(),upfile.getDescription(),upfile.getUsername(),upfile.getId()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//此处实现分页
	public QueryResult pageQuery(String username,int startIndex,int pageSize){
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql1 = "select * from upfile where username=? limit ?,?";
		String sql2 = "SELECT count(*) FROM upfile where username=?";
		Object params[]={username,startIndex,pageSize};
		Object params2[]={username};
		QueryResult result = new QueryResult();
		try {
			result.setList(runner.query(sql1, params, new BeanListHandler<Upfile>(Upfile.class)));
			Long i = (Long) runner.query(sql2,params2, new ScalarHandler(1));
			result.setTotalRecord(i.intValue());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
}
