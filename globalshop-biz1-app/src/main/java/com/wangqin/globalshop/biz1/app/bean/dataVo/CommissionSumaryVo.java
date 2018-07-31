package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDO;

import java.math.BigDecimal;

/**
 * Create by 777 on 2018/7/31
 *
 * 就是sumary+detail的扩展
 */
public class CommissionSumaryVo extends CommissionSumaryDO {

	private Long detailId;//传给前台，区别于子订单详情，结算时用

	private Integer status;

	private String settlementNo;

	private String shareUserId;

	private BigDecimal settlement;
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	public String getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}
	public BigDecimal getSettlement() {
		return settlement;
	}
	public void setSettlement(BigDecimal settlement) {
		this.settlement = settlement;
	}
}
