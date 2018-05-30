package com.wangqin.globalshop.item.app.dal.dto;

import java.util.List;


/**
 * 包裝主表
 * @author xiajun
 *
 */
public class ItemPackageScaleDTO {
	
	private Long id;//类型id
	
	private String name;//名称
	
	private String packagingScaleNo;
	
	private List<ItemPackagePatternDTO> packageLevels;
	
	public List<ItemPackagePatternDTO> getPackageLevels() {
		return packageLevels;
	}
	public void setPackageLevels(List<ItemPackagePatternDTO> packageLevels) {
		this.packageLevels = packageLevels;
	}
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
	public String getPackagingScaleNo() {
		return packagingScaleNo;
	}
	public void setPackagingScaleNo(String packagingScaleNo) {
		this.packagingScaleNo = packagingScaleNo;
	}
	

}
