package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.sql.Date;

/**
 * 
 * Title: SellerTypeQueryVO.java
 * Description: 
 *
 * @author jc
 * Mar 17, 2017
 *
 */

public class SellerTypeQueryVO extends PageQueryVO {

	/**
	 * Seller Type Name
	 */
	private String name;
	
	/**
	 * Seller Type Code
	 */
	private String code;
	
	/**
	 * Start Time
	 */
	private Date startGmt;
	
	/**
	 * End Time
	 */
	private Date endGmt;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getStartGmt() {
		return startGmt;
	}
	public void setStartGmt(Date startGmt) {
		this.startGmt = startGmt;
	}
	public Date getEndGmt() {
		return endGmt;
	}
	public void setEndGmt(Date endGmt) {
		this.endGmt = endGmt;
	}
	
}
