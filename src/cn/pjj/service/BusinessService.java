package cn.pjj.service;

import java.util.List;

import cn.pjj.bean.PageBean;
import cn.pjj.bean.QueryInfo;
import cn.pjj.bean.Upfile;

public interface BusinessService {

	void add(Upfile upfile);

	Upfile find(String id);

	void update(Upfile upfile);

	List<Upfile> getAll();

	void delete(String id);

	PageBean pageQuery(QueryInfo info,String username);

}