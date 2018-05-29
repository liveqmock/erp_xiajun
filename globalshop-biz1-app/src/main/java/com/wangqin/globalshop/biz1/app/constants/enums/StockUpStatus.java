package com.wangqin.globalshop.biz1.app.constants.enums;

/**
 * 订单备货枚举
 * @author 朱路
 *
 */
public enum StockUpStatus {

	INIT(0, "未备货"),
	PART(1, "部分备货"),
	TRANS_PART(2, "部分在途备货"),
	TRANS_STOCKUP(3, "全部在途备货"),
	MIX_STOCKUP(4,"混合备货完成"),
	
	
	RELEASED(9,"已释放"),
	STOCKUP(10, "已备货"),
	PREPARE(11, "预出库");
	
    private int code;
    private String description;
    StockUpStatus(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static StockUpStatus of(int code) {
        for (StockUpStatus status : StockUpStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
