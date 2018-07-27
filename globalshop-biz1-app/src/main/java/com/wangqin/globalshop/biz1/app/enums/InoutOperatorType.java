package com.wangqin.globalshop.biz1.app.enums;
/**
 * 出入库操作类型
 * @author zhulu
 *
 */
public enum InoutOperatorType {
	PURCHASE_IN(0, "采购入库"),
	SALE_OUT(1,"销售出库"),
	TRANS_IN(2,"在途入库"),
	CHECK_IN(3, "库存盘入"),
	CHECK_OUT(4, "库存盘出"),
	PURCHASE_TRANS_IN(5, "采购在途");
    private int code;
    private String description;
    InoutOperatorType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static InoutOperatorType of(int code) {
        for (InoutOperatorType status : InoutOperatorType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
