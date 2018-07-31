package com.wangqin.globalshop.common.enums;

/**
 * 分佣申请表状态
 * @author xiajun
 * mall_commision_apply.status
 */
public enum MallCommisionApplyStatus {
    CREATE("0", "新建"),
    RECEIVE("1", "已签收"),
    REMOVE("2", "清分");

    private String code;
    private String description;
    MallCommisionApplyStatus(String code,String description){
        this.code = code;
        this.description = description;
    }
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


}
