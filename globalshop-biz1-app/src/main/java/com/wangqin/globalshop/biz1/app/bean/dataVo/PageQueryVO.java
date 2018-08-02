package com.wangqin.globalshop.biz1.app.bean.dataVo;

public class PageQueryVO {

	
	/**
	 * 分页中的，第几页
	 */
	private Integer pageIndex = 1;
	
	private Integer pageSize = 20;
	
	/**
	 * 第一条记录
	 */
	private Integer firstStart = 0;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getFirstStart() {
		return firstStart;
	}

	public void setFirstStart(Integer firstStart) {
		this.firstStart = firstStart;
	}

	public void initFirstStart(){
		if(pageSize == null || pageSize < 0){
			this.pageSize = 20;
		}

		if(pageIndex == null || pageIndex < 1){
			this.pageIndex = 1;
		}
		this.firstStart = (pageIndex-1)*pageSize;
	}
	
	
}
