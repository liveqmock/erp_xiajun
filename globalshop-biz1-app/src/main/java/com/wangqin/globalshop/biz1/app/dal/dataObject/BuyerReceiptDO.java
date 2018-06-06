package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class BuyerReceiptDO extends BaseModel {
    private Long id;

    private String buyerTaskNo;

    private String receiptNo;

    private Double totalPrice;

    private String currency;

    private Date gmtCreate;

    private Date gmtModify;

    private String purchaseStorageNo;

    private Byte status;

    private Long buyerId;

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

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo == null ? null : receiptNo.trim();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
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

    public String getPurchaseStorageNo() {
        return purchaseStorageNo;
    }

    public void setPurchaseStorageNo(String purchaseStorageNo) {
        this.purchaseStorageNo = purchaseStorageNo == null ? null : purchaseStorageNo.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
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