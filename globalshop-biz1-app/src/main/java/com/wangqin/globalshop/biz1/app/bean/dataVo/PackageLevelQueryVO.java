package com.wangqin.globalshop.biz1.app.bean.dataVo;

import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;

import com.wangqin.globalshop.common.utils.PageQueryVO;

/**
 * 
 * Title: PackageLevelQueryVO.java
 * Description: 
 *
 * @author jc
 * Apr 7, 2017
 *
 */
public class PackageLevelQueryVO extends PageQueryVO {

	private Long id;
	private String name;
	private String packageEn;
	private Integer packageLevel;
	private Long packageId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageEn() {
		return packageEn;
	}
	public void setPackageEn(String packageEn) {
		this.packageEn = packageEn;
	}
	public Integer getPackageLevel() {
		return packageLevel;
	}
	public void setPackageLevel(Integer packageLevel) {
		this.packageLevel = packageLevel;
	}
	public Long getPackageId() {
		return packageId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	
}
