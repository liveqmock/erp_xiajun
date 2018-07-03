package com.wangqin.globalshop.biz1.app.vo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	private Long id;

    private Long roleId;
    @NotNull(message = "公司编号不能为空")
    private String companyNo;


    private String description;


    private String creator;

    private String modifier;
	/** 角色名 */
    @Size(max = 64 , min = 1, message = "角色名不能为空长度也不能超过64位")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
}
