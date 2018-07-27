package com.wangqin.globalshop.biz1.app.enums;

/**
 * 订单支付类型
 * @author zhulu
 *
 */
public enum PayType {
	WEIXIN(0, "微信自有支付"),
	WEIXIN_DAIXIAO(1,"微信代销支付"),
	ALIPAY(2,"支付宝支付"),
	BANKCARDPAY(3, "银行卡支付"),
	PEERPAY(4, "代付"),
	CODPAY(5,"货到付款"),
	BAIDUPAY(6,"百度钱包支付"),
	PRESENTTAKE(7, "直接领取赠品"),
	COUPONPAY(8, "优惠券/码全额抵扣"),
	BULKPURCHASE(9,"来自分销商的采购"),
	MERGEDPAY(10,"合并付货款"),
	ECARD(11, "有赞E卡支付"),
	CREDITECARD(12, "信用卡");

	
    private int code;
    private String description;
    PayType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static PayType of(int code) {
        for (PayType status : PayType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
