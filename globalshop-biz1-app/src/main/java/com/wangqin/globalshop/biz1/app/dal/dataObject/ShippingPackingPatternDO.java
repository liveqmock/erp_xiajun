package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ShippingPackingPatternDO {
    private Long id;

    private String patternNo;

    private String packagingScaleNo;

    private String name;

    private String nameEn;

    private Double weight;

    private Integer packageLevel;

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

    public String getPatternNo() {
        return patternNo;
    }

    public void setPatternNo(String patternNo) {
        this.patternNo = patternNo == null ? null : patternNo.trim();
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

    public Integer getPackageLevel() {
        return packageLevel;
    }

    public void setPackageLevel(Integer packageLevel) {
        this.packageLevel = packageLevel;
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