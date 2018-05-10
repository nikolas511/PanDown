package cn.pjj.service.impl;

import java.io.File;
import java.util.List;

import cn.pjj.bean.PageBean;
import cn.pjj.bean.QueryInfo;
import cn.pjj.bean.Upfile;
import cn.pjj.dao.UpfileDao;
import cn.pjj.bean.QueryResult;
import cn.pjj.factory.Factory;
import cn.pjj.service.BusinessService;
import cn.pjj.utils.JdbcUtils;

public class BusinessServiceImpl implements BusinessService {
	private UpfileDao dao = Factory.getInstance().createImpl(UpfileDao.class);
	public void add(Upfile upfile){
		dao.add(upfile);
	}
	public Upfile find(String id){
		return dao.find(id);
	}
	public void update(Upfile upfile){
		//在更新之前删除以前的上传的资料
		String savepath=dao.find(upfile.getId()).getSavepath();
		File file = new File(savepath);
		JdbcUtils.startTransaction();
		dao.update(upfile);
		if(file.exists()){
			file.delete();
		}
		JdbcUtils.commitTransaction();
		JdbcUtils.closeConnection();
	}
	public List<Upfile> getAll(){
		return dao.getAll();
	}
	public void delete(String id){
		File file = new File(dao.find(id).getSavepath());
		JdbcUtils.startTransaction();
		dao.delete(id);
		if(file.exists()){
			file.delete();
		}
		JdbcUtils.commitTransaction();
		JdbcUtils.closeConnection();
	}
	public PageBean pageQuery(QueryInfo info,String username){
		QueryResult qr = dao.pageQuery(username,info.getStartIndex(),info.getPageSize());
		PageBean pb = new PageBean();
		pb.setCurrentPage(info.getCurrentPage());
		pb.setList(qr.getList());
		pb.setPageSize(info.getPageSize());
		pb.setTotalRecord(qr.getTotalRecord());
		return pb;
	}
}
