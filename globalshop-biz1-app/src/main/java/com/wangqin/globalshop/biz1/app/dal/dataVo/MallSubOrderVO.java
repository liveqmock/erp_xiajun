package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

import java.util.Date;

/**
 * MallSubOrder查询
 * 
 * @author liuhui
 *
 */
public class MallSubOrderVO extends MallSubOrderDO {
	/**
	 * 状态
	 */
	private Byte status;// 订单状态
	/**
	 * 条形码
	 */
	private String upc;
	/**
	 * sku_code
	 */
	private String skuCode;
	/**
	 * 订单开始时间
	 */
	private Date startGmtCreate;
	
	/**
	 * 订单结束时间
	 */
	private Date endGmtCreate;
	
	private String closeReason;

	@Override
	public Byte getStatus() {
		return status;
	}

	@Override
	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String getUpc() {
		return upc;
	}

	@Override
	public void setUpc(String upc) {
		this.upc = upc;
	}

	@Override
	public String getSkuCode() {
		return skuCode;
	}

	@Override
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Date getStartGmtCreate() {
		return startGmtCreate;
	}

	public void setStartGmtCreate(Date startGmtCreate) {
		this.startGmtCreate = startGmtCreate;
	}

	public Date getEndGmtCreate() {
		return endGmtCreate;
	}

	public void setEndGmtCreate(Date endGmtCreate) {
		this.endGmtCreate = endGmtCreate;
	}

	@Override
	public String getCloseReason() {
		return closeReason;
	}

	@Override
	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}
}
