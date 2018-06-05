package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class BuyerTaskDetailDO extends BaseModel {
    private Long id;

    private String buyerTaskNo;

    private String itemCode;

    private String upc;

    private Float price;

    private Float maxPrice;

    private Integer count;

    private String ownerName;

    private String ownerNo;

    private Byte currency;

    private String buyerName;

    private Long buyerOpenId;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private Byte mode;

    private String skuCode;

    private String desc;

    private Integer maxCount;

    private String skuPicUrl;

    private Integer entryCount;

    private String upcPassReason;

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

    public String getBuyerTaskNo() {
        return buyerTaskNo;
    }

    public void setBuyerTaskNo(String buyerTaskNo) {
        this.buyerTaskNo = buyerTaskNo == null ? null : buyerTaskNo.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc == null ? null : upc.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerNo() {
        return ownerNo;
    }

    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo == null ? null : ownerNo.trim();
    }

    public Byte getCurrency() {
        return currency;
    }

    public void setCurrency(Byte currency) {
        this.currency = currency;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Byte getMode() {
        return mode;
    }

    public void setMode(Byte mode) {
        this.mode = mode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public String getSkuPicUrl() {
        return skuPicUrl;
    }

    public void setSkuPicUrl(String skuPicUrl) {
        this.skuPicUrl = skuPicUrl == null ? null : skuPicUrl.trim();
    }

    public Integer getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Integer entryCount) {
        this.entryCount = entryCount;
    }

    public String getUpcPassReason() {
        return upcPassReason;
    }

    public void setUpcPassReason(String upcPassReason) {
        this.upcPassReason = upcPassReason == null ? null : upcPassReason.trim();
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