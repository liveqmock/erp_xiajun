package com.wangqin.globalshop.biz1.app.vo;


import com.wangqin.globalshop.common.utils.PageQueryVO;

/**
 * 
 * Title: RoleQueryVO.java
 * Description: 
 *
 * @author jc
 * Apr 17, 2017
 *
 */
public class RoleQueryVO extends PageQueryVO {

	/** 角色名 */
	private String name;

	/** 排序号 */
	private Integer seq;

	/** 状态 */
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
