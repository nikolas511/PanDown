package cn.pjj.bean;

import java.util.List;

public class QueryResult {
	private List<Upfile> list;//用来存放信息的List
	private int totalRecord;//总记录条数
	public List<Upfile> getList() {
		return list;
	}
	public void setList(List<Upfile> list) {
		this.list = list;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
}
