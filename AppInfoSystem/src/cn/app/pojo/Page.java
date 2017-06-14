package cn.app.pojo;

import java.util.List;

public class Page {

	private int pageIndex ; //当前页
	private int pageSize;//页面大小，即每页显示的记录数
	private int totalCount; //总记录数
	private int totalPageCount; //总页数
	private  List<Info> pageNews = null; //查询记录
	private int currentPageNo;
		
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	//设置当前页
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
		if(currentPageNo<1){
			currentPageNo=1;
		}else if(currentPageNo!=0&&currentPageNo>totalPageCount){
			currentPageNo=totalPageCount;
		}
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//获得总页数
		this.totalPageCount=this.totalCount%this.pageSize==0?
							this.totalCount/this.pageSize:
							this.totalCount/this.pageSize+1;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public List<Info> getPageNews() {
		return pageNews;
	}
	public void setPageNews(List<Info> pageNews) {
		this.pageNews = pageNews;
	}
	
}
