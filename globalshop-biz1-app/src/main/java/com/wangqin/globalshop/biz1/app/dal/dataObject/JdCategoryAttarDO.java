package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class JdCategoryAttarDO extends BaseModel {
    private Long id;

    private String creator;

    private String modifier;

    private String channelNo;

    private String companyNo;

    private String shopCode;

    private String attrfeatures;

    private String msg;

    private String attname;

    private String attrIndexId;

    private String attributeType;

    private String categoryAttrId;

    private String categoryId;

    private String inputType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getAttrfeatures() {
        return attrfeatures;
    }

    public void setAttrfeatures(String attrfeatures) {
        this.attrfeatures = attrfeatures == null ? null : attrfeatures.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getAttname() {
        return attname;
    }

    public void setAttname(String attname) {
        this.attname = attname == null ? null : attname.trim();
    }

    public String getAttrIndexId() {
        return attrIndexId;
    }

    public void setAttrIndexId(String attrIndexId) {
        this.attrIndexId = attrIndexId == null ? null : attrIndexId.trim();
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType == null ? null : attributeType.trim();
    }

    public String getCategoryAttrId() {
        return categoryAttrId;
    }

    public void setCategoryAttrId(String categoryAttrId) {
        this.categoryAttrId = categoryAttrId == null ? null : categoryAttrId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}