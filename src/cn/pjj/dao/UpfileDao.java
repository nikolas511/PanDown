package cn.pjj.dao;

import java.util.List;

import cn.pjj.bean.QueryResult;
import cn.pjj.bean.Upfile;

public interface UpfileDao {

	void add(Upfile upfile);

	Upfile find(String id);

	List<Upfile> getAll();

	void delete(String id);

	void update(Upfile upfile);

	//此处实现分页
	QueryResult pageQuery(String username,int startIndex, int pageSize);

}