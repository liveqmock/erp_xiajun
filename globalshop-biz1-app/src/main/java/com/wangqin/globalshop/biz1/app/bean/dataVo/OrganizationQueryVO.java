package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	private Long id;
	@Size(max = 64, min = 1, message = "所属公司不能为空也不能大于64位")
    private String companyNo;

    private String orgId;
    @Size(max = 100, min = 1, message = "地址不能为空也不能大于100位")
    private String address;
    @Size(max = 64, min = 1, message = "图标不能为空也不能大于32位")
    private String icon;
    @NotNull(message = "排序好不能为空")
    private Integer seq;

    private String creator;

    private String modifier;
    @Size(max = 64, min = 1, message = "部门名称不能为空也不能大于64位")
	/** 组织名 */
	private String name;
    @Size(max = 64, min = 1, message = "编号不能为空也不能大于64位")
	/** 编号 */
	private String code;
//    @NotNull(message = "父级主键不能为空")
	//可以为空，表示一级
	/** 父级主键 */
	private Long pid;

	private Date startGmt;
	private Date endGmt;	
	
	private Boolean IsDel;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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
