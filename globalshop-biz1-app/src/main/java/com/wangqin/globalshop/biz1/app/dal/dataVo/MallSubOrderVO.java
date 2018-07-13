package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

import org.apache.bcel.generic.StackInstruction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * MallSubOrder查询
 * 
 * @author liuhui
 *
 */
public class MallSubOrderVO extends MallSubOrderDO {
	private String checkedSubOrderIdString;
	private String companyNo;
	/**
	 * 状态
	 */
	private Integer status;// 订单状态
	/**
	 * 条形码
	 */
	private String upc;
	/**
	 * sku_code
	 */
	private String skuCode;

	private String skuImg;
	/**
	 * 订单开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startGmtCreate;
	
	/**
	 * 订单结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endGmtCreate;
	
	private String closeReason;
	
	private boolean isDel;
	
	
	public String getCheckedSubOrderIdString() {
		return checkedSubOrderIdString;
	}

	public void setCheckedSubOrderIdString(String checkedSubOrderIdString) {
		this.checkedSubOrderIdString = checkedSubOrderIdString;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	@Override
	public Integer getStatus() {
		return status;
	}

	@Override
	public void setStatus(Integer status) {
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

	public String getSkuImg() {
		return skuImg;
	}

	public void setSkuImg(String skuImg) {
		this.skuImg = skuImg;
	}

	public boolean isDel() {
		return isDel;
	}

	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}
	
	
}
