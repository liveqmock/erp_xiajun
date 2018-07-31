package com.wangqin.globalshop.schedule.enums;

public enum  CommisionEnum {

    AGENCY( "1" , "二级代理分佣模式")

    ;

    private String code;
    private String value;

    CommisionEnum(String code, String value){
       this.code = code;
       this.value = value;
    }

    public static CommisionEnum parse(String code){
        for (CommisionEnum ce : CommisionEnum.values()){

        }
        return null;
    }

}
