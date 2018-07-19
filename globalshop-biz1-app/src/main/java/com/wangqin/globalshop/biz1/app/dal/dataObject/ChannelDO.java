package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ChannelDO extends BaseModel {
    private Long id;

    private String channelNo;

    private String name;

    private String type;

    private String contactMobile;

    private String contactName;

    private String contactEmail;

    private String contactUrl;

    private String remark;

    private String modifier;

    private String creator;

    private String saleLevel;

    private Double discount;

    private Double discount1;

    private Double discount2;

    private Double discount3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl == null ? null : contactUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getSaleLevel() {
        return saleLevel;
    }

    public void setSaleLevel(String saleLevel) {
        this.saleLevel = saleLevel == null ? null : saleLevel.trim();
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount1() {
        return discount1;
    }

    public void setDiscount1(Double discount1) {
        this.discount1 = discount1;
    }

    public Double getDiscount2() {
        return discount2;
    }

    public void setDiscount2(Double discount2) {
        this.discount2 = discount2;
    }

    public Double getDiscount3() {
        return discount3;
    }

    public void setDiscount3(Double discount3) {
        this.discount3 = discount3;
    }
}