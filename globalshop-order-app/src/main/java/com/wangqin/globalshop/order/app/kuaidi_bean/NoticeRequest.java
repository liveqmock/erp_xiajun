package com.wangqin.globalshop.order.app.kuaidi_bean;


public class NoticeRequest
{
	private String status = "";
	private String billstatus = "";
	private String message = "";
	private Kuaidi100ShippingTrackResult lastResult = new Kuaidi100ShippingTrackResult();
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getBillstatus()
	{
		return billstatus;
	}
	
	public void setBillstatus(String billstatus)
	{
		this.billstatus = billstatus;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public Kuaidi100ShippingTrackResult getLastResult()
	{
		return lastResult;
	}
	
	public void setLastResult(Kuaidi100ShippingTrackResult lastResult)
	{
		this.lastResult = lastResult;
	}
}
