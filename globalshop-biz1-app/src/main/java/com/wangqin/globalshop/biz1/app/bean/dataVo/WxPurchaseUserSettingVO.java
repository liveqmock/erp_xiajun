package com.wangqin.globalshop.biz1.app.bean.dataVo;

public class WxPurchaseUserSettingVO {

    private Integer id;
    private Integer purchaseCommissionMode;
    private String purchaseCommissionStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchaseCommissionMode() {
        return purchaseCommissionMode;
    }

    public void setPurchaseCommissionMode(Integer purchaseCommissionMode) {
        this.purchaseCommissionMode = purchaseCommissionMode;
    }

    public String getPurchaseCommissionStr() {
        return purchaseCommissionStr;
    }

    public void setPurchaseCommissionStr(String purchaseCommissionStr) {
        this.purchaseCommissionStr = purchaseCommissionStr;
    }
}
