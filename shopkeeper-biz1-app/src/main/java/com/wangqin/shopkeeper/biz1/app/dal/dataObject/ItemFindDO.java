package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.Date;

public class ItemFindDO {
    private Long id;

    private String itemFindNo;

    private String itemName;

    private String itemCode;

    private String skuCode;

    private Byte isNew;

    private Byte saleType;

    private Byte currency;

    private String buySite;

    private String origin;

    private Byte logisticType;

    private String contactPerson;

    private String contactTel;

    private Byte idCard;

    private Date startDate;

    private Date endDate;

    private Date bookingDate;

    private Byte isSale;

    private Byte wxisSale;

    private Byte isFind;

    private Byte status;

    private String desc;

    private String spec;

    private String model;

    private String buyerOpenId;

    private String buyerName;

    private Byte purchaseStatus;

    private String reason;

    private String findAddress;

    private String refuseReason;

    private String creator;

    private String modifier;

    private Date gmtCreate;

    private Date gmtModify;

    private Boolean isDel;

    private String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemFindNo() {
        return itemFindNo;
    }

    public void setItemFindNo(String itemFindNo) {
        this.itemFindNo = itemFindNo == null ? null : itemFindNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
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

    public Byte getIsNew() {
        return isNew;
    }

    public void setIsNew(Byte isNew) {
        this.isNew = isNew;
    }

    public Byte getSaleType() {
        return saleType;
    }

    public void setSaleType(Byte saleType) {
        this.saleType = saleType;
    }

    public Byte getCurrency() {
        return currency;
    }

    public void setCurrency(Byte currency) {
        this.currency = currency;
    }

    public String getBuySite() {
        return buySite;
    }

    public void setBuySite(String buySite) {
        this.buySite = buySite == null ? null : buySite.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public Byte getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(Byte logisticType) {
        this.logisticType = logisticType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public Byte getIdCard() {
        return idCard;
    }

    public void setIdCard(Byte idCard) {
        this.idCard = idCard;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Byte getIsSale() {
        return isSale;
    }

    public void setIsSale(Byte isSale) {
        this.isSale = isSale;
    }

    public Byte getWxisSale() {
        return wxisSale;
    }

    public void setWxisSale(Byte wxisSale) {
        this.wxisSale = wxisSale;
    }

    public Byte getIsFind() {
        return isFind;
    }

    public void setIsFind(Byte isFind) {
        this.isFind = isFind;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
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

    public Byte getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(Byte purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getFindAddress() {
        return findAddress;
    }

    public void setFindAddress(String findAddress) {
        this.findAddress = findAddress == null ? null : findAddress.trim();
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}