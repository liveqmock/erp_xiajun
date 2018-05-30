package com.wangqin.globalshop.channel.dal.dataVo;

public class ChannelAccountConfigVo extends PageQueryVO{
	
	private Long companyId;
	
	private String shopCode;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
		
}
