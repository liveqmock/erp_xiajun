package com.wangqin.globalshop.common.enums;

import java.util.Objects;

/**
 * 佣金的状态
 * @author  xiajun
 */
public enum CommissionStatus {
    BALANCE_ABLE(2,"可结算"),
    WAIT_BALANCE(1,"待结算"),
    BALANCED(3,"已结算");

    private Integer value;
    private String desc;

    private CommissionStatus(Integer value, String desc) {
        this.setValue(value);
        this.setDesc(desc);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }




}
