package cn.pjj.bean;

import java.util.List;

public class PageBean {
	private List<Upfile> list;
	private int totalRecord;
	private int pageSize;
	private int totalPage;
	private int currentPage;
	private int previousPage;
	private int nextPage;
	private int pageBar[];
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		//100 5 20
		//99 5 20
		//101 5 21
		if(this.totalRecord%this.pageSize==0){
			this.totalPage=this.totalRecord/this.pageSize;
		}else{
			this.totalPage=this.totalRecord/this.pageSize+1;
		}
		return totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPreviousPage() {
		this.previousPage = this.currentPage-1;
		if(this.previousPage<=0){
			this.previousPage=1;
		}
		return previousPage;
	}
	public int getNextPage() {
		this.nextPage = this.currentPage+1;
		if(this.nextPage>=this.totalPage){
			this.nextPage=this.totalPage;
		}
		return nextPage;
	}
	public int[] getPageBar() {
		//1 2 3 
		//1 2 3 4 5
		//3 4 5 6 7
		int startpage;
		int endpage;
		int pageBar[]=null;
		if(this.totalPage<=10){
			pageBar = new int[this.totalPage];
			startpage = 1;
			endpage = this.totalPage;
			/*for(int i = 1;i<=this.totalPage;i++){
				this.pageBar[i-1]=i;
			}
			return pageBar;*/
		}else{
			pageBar = new int[10];//每次显示5页
			startpage=this.currentPage-4;//每次显示5页
			endpage=this.currentPage+5;//显示页都为中间 1 2 3 4 5 /3为当前页面
			if(startpage<1){
				startpage=1;
				endpage=10;
			}
			if(endpage>this.totalPage){
				endpage=this.totalPage;
				startpage = this.totalPage-9;
			}
		}
		int index = 0;
		for(int i=startpage;i<=endpage;i++){
			pageBar[index++] = i;
		}
		this.pageBar = pageBar;
		return this.pageBar;
		/*
		this.pageBar = new int[this.totalPage];
		for(int i = 1;i<=this.totalPage;i++){
			this.pageBar[i-1]=i;
		}*/
	}
}
