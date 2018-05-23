package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class InventoryDO {
    private Long id;

    private String companyNo;

    private String itemCode;

    private String itemName;

    private String upc;

    private String skuCode;

    private Long virtualInv;

    private Long lockedVirtualInv;

    private Long inv;

    private Long lockedInv;

    private Long transInv;

    private Long lockedTransInv;

    private Date gmtCreate;

    private Date gmtModify;

    private Boolean isDel;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc == null ? null : upc.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Long getVirtualInv() {
        return virtualInv;
    }

    public void setVirtualInv(Long virtualInv) {
        this.virtualInv = virtualInv;
    }

    public Long getLockedVirtualInv() {
        return lockedVirtualInv;
    }

    public void setLockedVirtualInv(Long lockedVirtualInv) {
        this.lockedVirtualInv = lockedVirtualInv;
    }

    public Long getInv() {
        return inv;
    }

    public void setInv(Long inv) {
        this.inv = inv;
    }

    public Long getLockedInv() {
        return lockedInv;
    }

    public void setLockedInv(Long lockedInv) {
        this.lockedInv = lockedInv;
    }

    public Long getTransInv() {
        return transInv;
    }

    public void setTransInv(Long transInv) {
        this.transInv = transInv;
    }

    public Long getLockedTransInv() {
        return lockedTransInv;
    }

    public void setLockedTransInv(Long lockedTransInv) {
        this.lockedTransInv = lockedTransInv;
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