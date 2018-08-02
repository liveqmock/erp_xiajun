package com.wangqin.globalshop.biz1.app.enums;

/**
 * 分佣模式枚举类
 *
 * @author angus
 * @date 2018/8/2
 */
public enum CommissionModeEnum {
    /**
     * 百分比模式
     */
    PERCENTAGE_MODE(0,"百分比模式"),
    /**
     * 金额模式
     */
    AMOUNT_MODE(1, "金额模式");


    private int value;
    private String name;


    CommissionModeEnum(int value, String name){
        this.value = value;
        this.name = name;
    }

    public static String getName(int value) {
        for (CommissionModeEnum mode : CommissionModeEnum.values()) {
            if (mode.getValue() == value) {
                return mode.name;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
