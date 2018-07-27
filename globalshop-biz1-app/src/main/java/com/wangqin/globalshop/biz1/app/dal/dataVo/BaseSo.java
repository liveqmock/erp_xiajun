package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.io.Serializable;

/**
 * Create by 777 on 2018/5/24
 */
public class BaseSo implements Serializable {

	private int currentPage = 1;

	private int pageSize = 100;

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

}
