package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public class SettlementDetailVo {




	private String shareUserId;

	private String nick_name;

	private String headProtraitUrl;

	private BigDecimal sumSettlement;

	private List<CommissionSumaryVo> detailVoList;


	public String getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getHeadProtraitUrl() {
		return headProtraitUrl;
	}
	public void setHeadProtraitUrl(String headProtraitUrl) {
		this.headProtraitUrl = headProtraitUrl;
	}
	public BigDecimal getSumSettlement() {
		return sumSettlement;
	}
	public void setSumSettlement(BigDecimal sumSettlement) {
		this.sumSettlement = sumSettlement;
	}
	public List<CommissionSumaryVo> getDetailVoList() {
		return detailVoList;
	}
	public void setDetailVoList(List<CommissionSumaryVo> detailVoList) {
		this.detailVoList = detailVoList;
	}
}
