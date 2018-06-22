package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class JdItemDO extends BaseModel {
    private Long id;

    private Long version;

    private String creator;

    private String modifier;

    private String channelNo;

    private String shopCode;

    private String sendStatus;

    private String errorMassge;

    private Date itemModifyTime;

    private String channelItemCode;

    private String itemJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
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

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public String getErrorMassge() {
        return errorMassge;
    }

    public void setErrorMassge(String errorMassge) {
        this.errorMassge = errorMassge == null ? null : errorMassge.trim();
    }

    public Date getItemModifyTime() {
        return itemModifyTime;
    }

    public void setItemModifyTime(Date itemModifyTime) {
        this.itemModifyTime = itemModifyTime;
    }

    public String getChannelItemCode() {
        return channelItemCode;
    }

    public void setChannelItemCode(String channelItemCode) {
        this.channelItemCode = channelItemCode == null ? null : channelItemCode.trim();
    }

    public String getItemJson() {
        return itemJson;
    }

    public void setItemJson(String itemJson) {
        this.itemJson = itemJson == null ? null : itemJson.trim();
    }
}