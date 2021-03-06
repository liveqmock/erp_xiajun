package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ShippingPackingScaleDO extends BaseModel {
    private Long id;

    private String packagingScaleNo;

    private String name;

    private String nameEn;

    private Double weight;

    private Byte sizeLevel;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackagingScaleNo() {
        return packagingScaleNo;
    }

    public void setPackagingScaleNo(String packagingScaleNo) {
        this.packagingScaleNo = packagingScaleNo == null ? null : packagingScaleNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Byte getSizeLevel() {
        return sizeLevel;
    }

    public void setSizeLevel(Byte sizeLevel) {
        this.sizeLevel = sizeLevel;
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