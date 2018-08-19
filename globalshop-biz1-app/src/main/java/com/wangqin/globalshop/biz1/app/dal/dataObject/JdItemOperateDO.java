package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class JdItemOperateDO extends BaseModel {
    private Long id;

    private String channelNo;

    private String shopCode;

    private String itemCode;

    private String syncStatus;

    private String sendStatus;

    private String operateType;

    private String errorMassge;

    private String modifier;

    private String creator;

    private String itemJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public String getErrorMassge() {
        return errorMassge;
    }

    public void setErrorMassge(String errorMassge) {
        this.errorMassge = errorMassge == null ? null : errorMassge.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getItemJson() {
        return itemJson;
    }

    public void setItemJson(String itemJson) {
        this.itemJson = itemJson == null ? null : itemJson.trim();
    }
}