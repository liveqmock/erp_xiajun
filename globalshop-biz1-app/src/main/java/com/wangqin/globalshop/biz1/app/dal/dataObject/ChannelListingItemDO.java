package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ChannelListingItemDO extends BaseModel {
    private Long id;

    private String itemCode;

    private String channelNo;

    private String companyNo;

    private String channelItemCode;

    private String shopCode;

    private String channelItemAlias;

    private Integer status;

    private String modifier;

    private String creator;

    private String categoryJson;

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

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getChannelItemCode() {
        return channelItemCode;
    }

    public void setChannelItemCode(String channelItemCode) {
        this.channelItemCode = channelItemCode == null ? null : channelItemCode.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getChannelItemAlias() {
        return channelItemAlias;
    }

    public void setChannelItemAlias(String channelItemAlias) {
        this.channelItemAlias = channelItemAlias == null ? null : channelItemAlias.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getCategoryJson() {
        return categoryJson;
    }

    public void setCategoryJson(String categoryJson) {
        this.categoryJson = categoryJson == null ? null : categoryJson.trim();
    }
}