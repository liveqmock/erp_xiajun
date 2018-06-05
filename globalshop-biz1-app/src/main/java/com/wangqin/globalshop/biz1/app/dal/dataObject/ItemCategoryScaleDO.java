package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ItemCategoryScaleDO {
    private Long id;

    private String categoryCode;

    private String categoryName;

    private String scaleCode;

    private String scaleName;

    private String scaleValues;

    private Date gmtCreate;

    private Date gmtModify;

    private String creator;

    private String modifier;

    private Boolean isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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

    public String getScaleValues() {
        return scaleValues;
    }

    public void setScaleValues(String scaleValues) {
        this.scaleValues = scaleValues == null ? null : scaleValues.trim();
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

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}