package com.wangqin.globalshop.biz1.app.vo;

import java.util.Date;


import com.wangqin.globalshop.common.utils.PageQueryVO;

/**
 * 
 * Title: ResourceQueryVO.java
 * Description: 
 *
 * @author jc
 * Apr 17, 2017
 *
 */
public class ResourceQueryVO extends PageQueryVO {

	/** 资源名称 */
	private String name;

	/** 父级资源id */
	private Long pid;

	/** 资源类别 */
	private Integer resourceType;

	private Date startGmt;
	private Date endGmt;
	
	/**
	 * 资源编码
	 */
	private String resCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
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

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}


}
