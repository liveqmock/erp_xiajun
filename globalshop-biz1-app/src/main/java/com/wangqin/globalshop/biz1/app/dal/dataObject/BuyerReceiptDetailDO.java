package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class BuyerReceiptDetailDO extends BaseModel {
    private Long id;

    private String skuCode;

    private String itemCode;

    private Double price;

    private Integer quantity;

    private String upc;

    private String receiptNo;

    private String companyNo;

    private String buyerTaskDetailNo;

    private Integer transQuantity;

    private String skuBuysite;

    private String purchaseStorageNo;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc == null ? null : upc.trim();
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo == null ? null : receiptNo.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getBuyerTaskDetailNo() {
        return buyerTaskDetailNo;
    }

    public void setBuyerTaskDetailNo(String buyerTaskDetailNo) {
        this.buyerTaskDetailNo = buyerTaskDetailNo == null ? null : buyerTaskDetailNo.trim();
    }

    public Integer getTransQuantity() {
        return transQuantity;
    }

    public void setTransQuantity(Integer transQuantity) {
        this.transQuantity = transQuantity;
    }

    public String getSkuBuysite() {
        return skuBuysite;
    }

    public void setSkuBuysite(String skuBuysite) {
        this.skuBuysite = skuBuysite == null ? null : skuBuysite.trim();
    }

    public String getPurchaseStorageNo() {
        return purchaseStorageNo;
    }

    public void setPurchaseStorageNo(String purchaseStorageNo) {
        this.purchaseStorageNo = purchaseStorageNo == null ? null : purchaseStorageNo.trim();
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