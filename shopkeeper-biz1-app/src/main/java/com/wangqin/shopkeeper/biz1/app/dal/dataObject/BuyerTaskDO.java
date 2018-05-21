package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.Date;

public class BuyerTaskDO {
    private Long id;

    private String buyerTaskNo;

    private String title;

    private String ownerNo;

    private String desc;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModify;

    private Long buyerOpenId;

    private String buyerName;

    private Long purchaseCommissionMode;

    private String purchaseCommissionStr;

    private Boolean isDel;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
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

    public Long getBuyerOpenId() {
        return buyerOpenId;
    }

    public void setBuyerOpenId(Long buyerOpenId) {
        this.buyerOpenId = buyerOpenId;
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

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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