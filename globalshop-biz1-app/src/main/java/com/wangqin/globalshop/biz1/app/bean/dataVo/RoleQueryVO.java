package com.wangqin.globalshop.biz1.app.bean.dataVo;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Size(max = 64, min = 1, message = "所属公司不能为空也不能大于64位")
    private String companyNo;


    private String description;


    private String creator;

    private String modifier;
	/** 角色名 */
    @Size(max = 64 , min = 1, message = "角色名不能为空长度也不能超过64位")
	private String name;
    @NotNull(message = "排序好不能为空")
	/** 排序号 */
	private Integer seq;
	@Max(value = 1, message = "1：是状态正常")
    @Min(value = 0, message = "0：是状态错误")
	/** 状态 */
	private Integer status;
	
	private Boolean IsDel;
	
	
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

	public Boolean getIsDel() {
		return IsDel;
	}

	public void setIsDel(Boolean isDel) {
		IsDel = isDel;
	}
	
}
