package com.wangqin.globalshop.biz1.app.vo;

/**
 * 
 * Title: PackageLevelQueryVO.java
 * Description: 
 *
 * @author jc
 * Apr 7, 2017
 *
 */
public class ShippingPackingPatternQueryVO extends PageQueryVO {

	private String name;
	private String packageEn;
	private Integer packageLevel;
	private Long packageId;
	
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
