package com.wangqin.globalshop.biz1.app.bean.dto;

import com.wangqin.globalshop.common.utils.PageQueryVO;

/**
 * 包裝的附表
 * @author xiajun
 *
 */
public class ItemPackagePatternDTO extends PageQueryVO{

		private Long id;//类型id
		
		private String name;//名称
		
		private String packagingScaleNo;
		
		private Long packageId;
	
		private String packageEn;
		
		private String creator;
		
		private String modifier;
		
		private Integer packageLevel;
		
		private Double weight;
		
		
		public Long getPackageId() {
			return packageId;
		}
		public void setPackageId(Long packageId) {
			this.packageId = packageId;
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
		public Double getWeight() {
			return weight;
		}
		public void setWeight(Double weight) {
			this.weight = weight;
		}
		
		
}
