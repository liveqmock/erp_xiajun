package com.wangqin.globalshop.biz1.app.vo;

import java.util.Date;

import com.wangqin.globalshop.common.utils.PageQueryVO;

/**
 * 
 * Title: OrganizationQueryVO.java
 * Description: 
 *
 * @author jc
 * Apr 17, 2017
 *
 */
public class OrganizationQueryVO extends PageQueryVO {

	/** 组织名 */
	private String name;

	/** 编号 */
	private String code;

	/** 父级主键 */
	private Long pid;

	private Date startGmt;
	private Date endGmt;	
	
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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

}
