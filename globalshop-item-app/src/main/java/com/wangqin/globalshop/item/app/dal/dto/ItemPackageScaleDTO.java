package com.wangqin.globalshop.item.app.dal.dto;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;


/**
 * 包裝主表
 * @author xiajun
 *
 */
public class ItemPackageScaleDTO {
	
	private Long id;//类型id
	
	private String name;//名称
	
	private String enName;//英文名
	
	private String packagingScaleNo;
	
	private String creator;
	
	private String modifier;
	
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
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
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
