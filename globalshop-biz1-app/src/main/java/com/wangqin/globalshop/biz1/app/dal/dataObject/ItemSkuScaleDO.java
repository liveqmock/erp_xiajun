package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ItemSkuScaleDO extends BaseModel {
    private Long id;

    private String itemCode;

    private String skuCode;

    private String scaleCode;

    private String scaleName;

    private String scaleValue;

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

    public String getScaleCode() {
        return scaleCode;
    }

    public void setScaleCode(String scaleCode) {
        this.scaleCode = scaleCode == null ? null : scaleCode.trim();
    }

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName == null ? null : scaleName.trim();
    }

    public String getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(String scaleValue) {
        this.scaleValue = scaleValue == null ? null : scaleValue.trim();
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
}