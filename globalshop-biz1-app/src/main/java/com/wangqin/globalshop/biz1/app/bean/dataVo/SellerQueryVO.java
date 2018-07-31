package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

/**
 * 
 * Title: SellerQueryVO.java
 * Description: 
 *
 * @author jc
 * Mar 18, 2017
 *
 */

public class SellerQueryVO extends PageQueryVO {
	private Integer id;
	private String name;
	private Long userId;
	private String userName;
	private String code;
	private Long typeId;
	private String typeName;
	private String typeCode;
	private Date startGmt;
	private Date endGmt;
	private Long companyId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}	
}
