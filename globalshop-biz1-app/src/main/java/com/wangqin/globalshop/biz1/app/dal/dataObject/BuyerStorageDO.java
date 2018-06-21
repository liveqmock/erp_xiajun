package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class BuyerStorageDO extends BaseModel {
    private Long id;

    private String storageNo;

    private String warehouseNo;

    private String companyNo;

    private String warehouseName;

    private String buyerName;

    private Long buyerOpenId;

    private String purchaseStorageNo;

    private String buyerTaskNo;

    private Byte storageType;

    private Date entryDate;

    private String memo;

    private String creator;

    private String modifier;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageNo() {
        return storageNo;
    }

    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo == null ? null : storageNo.trim();
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo == null ? null : warehouseNo.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public Long getBuyerOpenId() {
        return buyerOpenId;
    }

    public void setBuyerOpenId(Long buyerOpenId) {
        this.buyerOpenId = buyerOpenId;
    }

    public String getPurchaseStorageNo() {
        return purchaseStorageNo;
    }

    public void setPurchaseStorageNo(String purchaseStorageNo) {
        this.purchaseStorageNo = purchaseStorageNo == null ? null : purchaseStorageNo.trim();
    }

    public String getBuyerTaskNo() {
        return buyerTaskNo;
    }

    public void setBuyerTaskNo(String buyerTaskNo) {
        this.buyerTaskNo = buyerTaskNo == null ? null : buyerTaskNo.trim();
    }

    public Byte getStorageType() {
        return storageType;
    }

    public void setStorageType(Byte storageType) {
        this.storageType = storageType;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}