package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ChannelListingItemSkuDO extends BaseModel {
    private Long id;

    private String itemCode;

    private String skuCode;

    private Integer platformType;

    private String channelItemSkuCode;

    private String channelItemCode;

    private String modifier;

    private String creator;

    private Integer shopProductSkuId;

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

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
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

    public Integer getShopProductSkuId() {
        return shopProductSkuId;
    }

    public void setShopProductSkuId(Integer shopProductSkuId) {
        this.shopProductSkuId = shopProductSkuId;
    }
}