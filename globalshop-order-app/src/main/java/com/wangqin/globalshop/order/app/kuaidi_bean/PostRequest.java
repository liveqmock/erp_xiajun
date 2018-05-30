package com.wangqin.globalshop.order.app.kuaidi_bean;

import java.util.HashMap;

public class PostRequest
{
	private String company; //公司代码,小写
	private String number; // 物流单号
	private String from; // 可不填写
	private String to;// 可不填写
	private String key = "zUNguBRY2899"; // 身份标志
	private String src;
	
	private HashMap<String, String> parameters = new HashMap<String, String>();
	
	public PostRequest()
	{
	}
	
	public PostRequest(String company, String number)
	{
		this.company = company;
		this.number = number;
	}
	
	public String getCompany()
	{
		return company;
	}
	
	public void setCompany(String company)
	{
		this.company = company;
	}
	
	public String getNumber()
	{
		return number;
	}
	
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public String getFrom()
	{
		return from;
	}
	
	public void setFrom(String from)
	{
		this.from = from;
	}
	
	public String getTo()
	{
		return to;
	}
	
	public void setTo(String to)
	{
		this.to = to;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public HashMap<String, String> getParameters()
	{
		return parameters;
	}
	
	public void setParameters(HashMap<String, String> parameters)
	{
		this.parameters = parameters;
	}
	
	/**
	 * 
	 * @param key : callbackurl/salt
	 * @param val
	 */
	public void addParam(String key, String val)
	{
		this.parameters.put(key, val);
	}
	
	public void addCallbackParam(String val)
	{
		this.parameters.put("callbackurl", val);
	}
	
	public void addSaltParam(String val)
	{
		this.parameters.put("salt", val);
	}
	
	@Override
	public String toString()
	{
		return JacksonHelper.toJSON(this);
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}
	
}
