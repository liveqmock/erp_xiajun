package com.wangqin.globalshop.biz1.app.constants.enums;

/**
 * 小程序发布状态
 * @author 朱路
 *
 */
public enum PublishStatus {
    /**
     *已授权
     */
    AUTHORIZED(1,"已授权"),
    /**
     *已提交体验版
     */
    SUBMITTED(2,"已提交体验版"),
    /**
     *待审核
     */
    PENDING_REVIEW(3,"待审核"),
    /**
     *审核通过待发布
     */
    REVIEW_PENDING_RELEASE(4,"审核通过待发布"),
    /**
     *已发布
     */
    PUBLISHED(5,"已发布");

    private int code;
    private String description;
    PublishStatus(int code, String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static PublishStatus of(int code) {
        for (PublishStatus status : PublishStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
