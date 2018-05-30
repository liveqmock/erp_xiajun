package com.wangqin.globalshop.item.app.dal.dto;



/**
 * 包裝的附表
 * @author xiajun
 *
 */
public class ItemPackagePatternDTO {

		private Long id;//类型id
		
		private String name;//名称
		
		private String packagingScaleNo;
	
		
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
