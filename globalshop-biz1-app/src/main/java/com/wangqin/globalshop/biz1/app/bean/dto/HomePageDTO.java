package com.wangqin.globalshop.biz1.app.bean.dto;

/**
 * 首页数据
 * @author XiaJun
 *
 */
public class HomePageDTO {

	//今日订单数
	private Integer todayOrderNum;
	
	//待分配订单数
	private Integer waitForAlloOrderNum;
	
	//采购商品数
	private Integer purItemNum;
	
	//今日入库商品数
	private Integer todayInInvItemNum;
	
	//采购异常订单数
	private Integer purExcOrderNum;
	
	//库存异常的商品数
	private Integer invExcItemNum;
	
	//今日采购订单数
	private Integer todayPurOrderNum;
	
	//今日已采购商品数
	private Integer todayPurchasedItemNum;
	
	//预入报商品（即入库）
	private Integer prepareInInvItemNum;
	
	//已入库商品(即上面的今日入库商品数)
	
	//采购中订单数
	private Integer purchasingItemNum;
	
	//待分配订单数(上面有)

	//已结算商品数
	private Integer balancedItemNum;
	
	//入库商品数
	private Integer inInvItemNum;
	
	//采购缺货商品数
	private Integer purProblemItemNum;
	
	//已发货订单数
	private Integer sentOrderNum;

	public Integer getTodayOrderNum() {
		return todayOrderNum;
	}

	public void setTodayOrderNum(Integer todayOrderNum) {
		this.todayOrderNum = todayOrderNum;
	}

	public Integer getWaitForAlloOrderNum() {
		return waitForAlloOrderNum;
	}

	public void setWaitForAlloOrderNum(Integer waitForAlloOrderNum) {
		this.waitForAlloOrderNum = waitForAlloOrderNum;
	}

	public Integer getPurItemNum() {
		return purItemNum;
	}

	public void setPurItemNum(Integer purItemNum) {
		this.purItemNum = purItemNum;
	}

	public Integer getTodayInInvItemNum() {
		return todayInInvItemNum;
	}

	public void setTodayInInvItemNum(Integer todayInInvItemNum) {
		this.todayInInvItemNum = todayInInvItemNum;
	}

	public Integer getPurExcOrderNum() {
		return purExcOrderNum;
	}

	public void setPurExcOrderNum(Integer purExcOrderNum) {
		this.purExcOrderNum = purExcOrderNum;
	}

	public Integer getInvExcItemNum() {
		return invExcItemNum;
	}

	public void setInvExcItemNum(Integer invExcItemNum) {
		this.invExcItemNum = invExcItemNum;
	}

	public Integer getTodayPurOrderNum() {
		return todayPurOrderNum;
	}

	public void setTodayPurOrderNum(Integer todayPurOrderNum) {
		this.todayPurOrderNum = todayPurOrderNum;
	}

	public Integer getTodayPurchasedItemNum() {
		return todayPurchasedItemNum;
	}

	public void setTodayPurchasedItemNum(Integer todayPurchasedItemNum) {
		this.todayPurchasedItemNum = todayPurchasedItemNum;
	}

	public Integer getPrepareInInvItemNum() {
		return prepareInInvItemNum;
	}

	public void setPrepareInInvItemNum(Integer prepareInInvItemNum) {
		this.prepareInInvItemNum = prepareInInvItemNum;
	}


	public Integer getPurchasingItemNum() {
		return purchasingItemNum;
	}

	public void setPurchasingItemNum(Integer purchasingItemNum) {
		this.purchasingItemNum = purchasingItemNum;
	}

	public Integer getBalancedItemNum() {
		return balancedItemNum;
	}

	public void setBalancedItemNum(Integer balancedItemNum) {
		this.balancedItemNum = balancedItemNum;
	}

	public Integer getInInvItemNum() {
		return inInvItemNum;
	}

	public void setInInvItemNum(Integer inInvItemNum) {
		this.inInvItemNum = inInvItemNum;
	}

	public Integer getPurProblemItemNum() {
		return purProblemItemNum;
	}

	public void setPurProblemItemNum(Integer purProblemItemNum) {
		this.purProblemItemNum = purProblemItemNum;
	}

	public Integer getSentOrderNum() {
		return sentOrderNum;
	}

	public void setSentOrderNum(Integer sentOrderNum) {
		this.sentOrderNum = sentOrderNum;
	}
	
	
	
}
