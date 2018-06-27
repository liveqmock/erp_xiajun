package com.wangqin.globalshop.biz1.app.constants.enums;

/**
 * @author 朱路
 *
 */
public enum PlatformType {
	YOUZAN(1, "有赞"),
	HAIHU(2, "海狐"),
	TAOBAO(3, "淘宝"),
	JingDong(4,"京东"),
	WEIXIN(101, "微信"),
	WEIXINXCX(102, "微信小程序");
	
//	YOUZAN(1, "有赞"),
//	 WEIXIN(2, "微信"),
//	 WEIXINXCX(3, "微信小程序"),
//	 OTHER(10, "其他");
    private int code;
    private String description;
    PlatformType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static PlatformType of(int code) {
        for (PlatformType status : PlatformType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
