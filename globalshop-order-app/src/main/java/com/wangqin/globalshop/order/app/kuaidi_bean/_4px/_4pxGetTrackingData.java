package com.wangqin.globalshop.order.app.kuaidi_bean._4px;

import java.util.List;

public class _4pxGetTrackingData {
	private String DeliveryCodeNo;

	private List<_4pxGetTrackingItem> TrackingList ;

	public void setDeliveryCodeNo(String DeliveryCodeNo){
		this.DeliveryCodeNo = DeliveryCodeNo;
	}
	public String getDeliveryCodeNo(){
		return this.DeliveryCodeNo;
	}
	public void setTrackingList(List<_4pxGetTrackingItem> TrackingList){
		this.TrackingList = TrackingList;
	}
	public List<_4pxGetTrackingItem> getTrackingList(){
		return this.TrackingList;
	}
}
