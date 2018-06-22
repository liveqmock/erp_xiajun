package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class BuyerTaskDO extends BaseModel {
    private Long id;

    private String buyerTaskNo;

    private String title;

    private String ownerNo;

    private String companyNo;

    private String remark;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private String buyerOpenId;

    private String buyerName;

    private Long purchaseCommissionMode;

    private String purchaseCommissionStr;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerTaskNo() {
        return buyerTaskNo;
    }

    public void setBuyerTaskNo(String buyerTaskNo) {
        this.buyerTaskNo = buyerTaskNo == null ? null : buyerTaskNo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOwnerNo() {
        return ownerNo;
    }

    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo == null ? null : ownerNo.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBuyerOpenId() {
        return buyerOpenId;
    }

    public void setBuyerOpenId(String buyerOpenId) {
        this.buyerOpenId = buyerOpenId == null ? null : buyerOpenId.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public Long getPurchaseCommissionMode() {
        return purchaseCommissionMode;
    }

    public void setPurchaseCommissionMode(Long purchaseCommissionMode) {
        this.purchaseCommissionMode = purchaseCommissionMode;
    }

    public String getPurchaseCommissionStr() {
        return purchaseCommissionStr;
    }

    public void setPurchaseCommissionStr(String purchaseCommissionStr) {
        this.purchaseCommissionStr = purchaseCommissionStr == null ? null : purchaseCommissionStr.trim();
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