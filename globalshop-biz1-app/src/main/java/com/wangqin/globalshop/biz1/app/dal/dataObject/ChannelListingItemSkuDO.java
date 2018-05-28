package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ChannelListingItemSkuDO {
    private Long id;

    private String itemCode;

    private String skuCode;

    private Byte platformType;

    private String channelItemSkuCode;

    private String channelItemCode;

    private Boolean isDel;

    private Date gmtCreate;

    private Date gmtModify;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Byte getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Byte platformType) {
        this.platformType = platformType;
    }

    public String getChannelItemSkuCode() {
        return channelItemSkuCode;
    }

    public void setChannelItemSkuCode(String channelItemSkuCode) {
        this.channelItemSkuCode = channelItemSkuCode == null ? null : channelItemSkuCode.trim();
    }

    public String getChannelItemCode() {
        return channelItemCode;
    }

    public void setChannelItemCode(String channelItemCode) {
        this.channelItemCode = channelItemCode == null ? null : channelItemCode.trim();
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}