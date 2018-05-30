package com.wangqin.globalshop.order.app.notice_bean;

public class ResultItem
{
	String time;
	String context;
	String ftime;
	
	public String getTime()
	{
		return time;
	}
	
	public void setTime(String time)
	{
		this.time = time;
	}
	
	public String getContext()
	{
		return context;
	}
	
	public void setContext(String context)
	{
		this.context = context;
	}
	
	public String getFtime()
	{
		return ftime;
	}
	
	public void setFtime(String ftime)
	{
		this.ftime = ftime;
	}

	@Override
	public String toString() {
		return "ResultItem [time=" + time + ", context=" + context + ", ftime="
				+ ftime + "]";
	}
}
