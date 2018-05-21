package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.Date;

public class LogisticCategoryMappingDO {
    private Long id;

    private String categoryCode;

    private String categoryName;

    private Long logisticsCompanyCode;

    private String logisticsCompanyName;

    private String logisticsCompanyCategoryCode;

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

    public Long getLogisticsCompanyCode() {
        return logisticsCompanyCode;
    }

    public void setLogisticsCompanyCode(Long logisticsCompanyCode) {
        this.logisticsCompanyCode = logisticsCompanyCode;
    }

    public String getLogisticsCompanyName() {
        return logisticsCompanyName;
    }

    public void setLogisticsCompanyName(String logisticsCompanyName) {
        this.logisticsCompanyName = logisticsCompanyName == null ? null : logisticsCompanyName.trim();
    }

    public String getLogisticsCompanyCategoryCode() {
        return logisticsCompanyCategoryCode;
    }

    public void setLogisticsCompanyCategoryCode(String logisticsCompanyCategoryCode) {
        this.logisticsCompanyCategoryCode = logisticsCompanyCategoryCode == null ? null : logisticsCompanyCategoryCode.trim();
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