package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.List;

/**
 * 佣金结算管理
 */
public class AgentCommissionVO {
	
	private String userNo;
private String profile;
	
	private String name;
	
	private Double commission;
	
	private List<AgentOrderVO> orderInfo;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public List<AgentOrderVO> getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(List<AgentOrderVO> orderInfo) {
		this.orderInfo = orderInfo;
	}
	
	

  
}
