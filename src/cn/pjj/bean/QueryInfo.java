package cn.pjj.bean;

public class QueryInfo {
	private int currentPage = 1;//当前页面默认为1;
	private int pageSize = 5;//设置的每页显示数量默认为5;
	//数据库查早的位置 select * from books limit 0,5; 意思为查找0后面的5条记录,此处startIndex为0的位置
	private int startIndex;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		this.startIndex = (this.currentPage-1)*this.pageSize;
		return startIndex;
	}
}
