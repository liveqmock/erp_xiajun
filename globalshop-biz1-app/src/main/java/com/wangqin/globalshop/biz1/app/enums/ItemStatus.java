package com.wangqin.globalshop.biz1.app.enums;

/**
 * 外部平台类型
 * @author 朱路
 *
 */
public enum ItemStatus {

	INIT(0, "新建"),
	LISTING(1,"上架"),//listing
	DELISTING(2,"下架"),//delisting
	//SYNONE(3, "同步"),//1号仓商品同步到系统的初始状态
	DELETE(-1, "删除");
    private int code;
    private String description;
    ItemStatus(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static ItemStatus of(int code) {
        for (ItemStatus status : ItemStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
