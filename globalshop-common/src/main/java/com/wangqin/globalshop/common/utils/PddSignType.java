package com.wangqin.globalshop.common.utils;

public class PddSignType {
	
	public PddSignType(String uCode, String mType, String secret, String timeStamp) {
		super();
		this.uCode = uCode;
		this.mType = mType;
		this.secret = secret;
		this.timeStamp = timeStamp;
	}


	/**
	 * 接入码
	 */
	private String uCode;
	
	/**
	 * 接口名
	 */
	private String mType;
	
	/**
	 * secret
	 */
	private String secret;
	
	/**
	 * 标准时间戳
	 */
	private String timeStamp;

	public String getuCode() {
		return uCode;
	}

	public void setuCode(String uCode) {
		this.uCode = uCode;
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * 按参数第一个字母排序添加，Map会打乱secret的顺序
	 * @return
	 */
	public String makeToSignString() {
		StringBuffer signString = new StringBuffer("");
		signString.append(this.secret);
		signString.append("mType");
		signString.append(this.mType);
		signString.append("timeStamp");
		signString.append(this.timeStamp);
		signString.append("uCode");
		signString.append(this.uCode);
		signString.append(this.secret);		
		return signString.toString();
	}
	
		
	

}
