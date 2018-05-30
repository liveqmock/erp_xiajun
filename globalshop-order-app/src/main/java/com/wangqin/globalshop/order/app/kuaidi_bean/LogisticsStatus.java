package com.wangqin.globalshop.order.app.kuaidi_bean;

import org.springframework.util.StringUtils;

public enum LogisticsStatus {

	//物流节点状态
    REFUND("77","已退款", 77),
	PAYED("0","已付款", 0),
    ORDERD("1","已下单", 1),
    MALL_RECIEVED("2","商城已接单", 2),
    MALL_DISPATCHED("3","商城已发货", 3),
    //删除4和5这个节点
    //OVERSEA_REPOSITORY_ARRIVED("4","到达海外仓库"),
    //OVERSEA_REPOSITORY_SIGNED("5","海外仓库签收"),
    //把原来的6改成4,描述信息不改
    OVERSEA_REPOSITORY_STOCK_IN("40","海外仓库入库", 40),
    //把原来的7改成5,描述信息不改
    OVERSEA_REPOSITORY_WATING_PACKAGE("50","海外仓库待装运", 50),
    //把原来的8改成6,描述信息不改
    OVERSEA_REPOSITORY_PACKAGED("60","海外仓库已装运", 60),
    //把原来的9改成7,描述信息国内物流已发货修改成海外仓库已出库
    SENDING_TO_CHINA("70","海外仓库已出库", 70),
    //增加发往海外机场的节点
    SENDING_TO_AIRPORT("80","发往海外机场", 80),
    //增加航班飞往国内节点
    AIRLINE_TO_CHINA("90","航班飞往国内", 90),
    ARRIVE_CHINA_AIRPORT("100","到达国内机场", 100),
    //删除10开始清关节点
    //CLEARANCE_WATING("10","开始报关"),
    //把清关中修改为海关清关
    CLEARANCE_DOING("110","海关清关", 110),
    CLEARANCE_DONE("120","海关放行", 120),
    CHINA_DISPATCHED("130","国内物流派送中", 130),
    SIGNED("140","已签收", 140),
    
    ORDER_UNNORMAL_OVER("150","订单非正常终止", 150),

    
    //物流异常状态
    OVERSEA_REPOSITORY_UNSIGNED("30","海外仓库拒收", 30),
    OUT_STOCK_REJECT("31","海外仓库拒收", 31),
    CLEARANCE_UNSIGNED("32","清关失败", 32),
    CHINA_DISPATCHED_REJECT("33","国内物流拒绝收货", 33),
    UNPASS_ORDER("34","审核订单失败", 34),
    
	;
	
    private String code;
    private String display;
    private int value;
    
    LogisticsStatus(String code, String display) {
        this.code = code;
        this.display = display;
    }
    
    LogisticsStatus(String code, String display, int value) {
        this.code = code;
        this.display = display;
        this.value = value;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static LogisticsStatus getLogisticsStatusByCode(String code){
		if(StringUtils.isEmpty(code)){
			return null;
		}
		for(LogisticsStatus ls: LogisticsStatus.values()) {
			if(code.equalsIgnoreCase(ls.getCode())) {
				return ls;
			}
		}
		return null;
	}
	
	public static LogisticsStatus getLogisticsStatusByValue(int value){
		for(LogisticsStatus ls: LogisticsStatus.values()) {
			if(value == ls.getValue()) {
				return ls;
			}
		}
		return null;
	}
	
	public static LogisticsStatus geLogisticsStatusByDisplay(String display){
		if(display==null){
			return null;
		}
		for(LogisticsStatus ls: LogisticsStatus.values()) {
			if(display.equalsIgnoreCase(ls.getDisplay())) {
				return ls;
			}
		}
		return null;
	}

}
