package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class BuyerDO {
    private Long id;

    private String companyNo;

    private String openId;

    private String unionId;

    private String nickName;

    private String headProtraitUrl;

    private Integer gender;

    private String city;

    private String province;

    private String country;

    private Long referer;

    private Long purchaseCommissionMode;

    private String purchaseCommissionStr;

    private Date firstLoginTime;

    private Date lastLoginTime;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getHeadProtraitUrl() {
        return headProtraitUrl;
    }

    public void setHeadProtraitUrl(String headProtraitUrl) {
        this.headProtraitUrl = headProtraitUrl == null ? null : headProtraitUrl.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Long getReferer() {
        return referer;
    }

    public void setReferer(Long referer) {
        this.referer = referer;
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

    public Date getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Date firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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