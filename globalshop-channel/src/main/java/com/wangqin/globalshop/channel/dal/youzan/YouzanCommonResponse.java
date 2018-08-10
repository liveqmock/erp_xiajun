package com.wangqin.globalshop.channel.dal.youzan;

/**
 * Create by 777 on 2018/8/10
 */
public class YouzanCommonResponse {


	//{"code":0,"msg":"success"}


	private String code;

	private String msg;


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void buildSuccess(){
		this.setCode("0");
		this.setMsg("success");
	}
}
