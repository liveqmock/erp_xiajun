package com.wangqin.globalshop.biz1.app.enums;

/**
 * Create by 777 on 2018/8/12
 */
public enum ListingStatus {

	LISTING_STATUS(1,"上架"),
	DELISTING_STATUS(2,"下架"),
	NEW(0,"新建"),
	DELETE(-1,"京东");



	private Integer status;
	private String statusName;

	public static String getStatusName(Integer status) {
		for (ListingStatus item : ListingStatus.values()) {
			if (item.getStatus() == status) {
				return item.getStatusName();
			}
		}
		return "";
	}


	private ListingStatus(Integer status, String statusName) {
		this.setStatus(status);
		this.setStatusName(statusName);
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
