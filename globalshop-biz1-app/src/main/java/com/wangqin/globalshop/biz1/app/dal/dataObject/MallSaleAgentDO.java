package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class MallSaleAgentDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String userNo;

    private String parentAgent;

    private String openId;

    private String unionId;

    private String agentName;

    private String realName;

    private String idCard;

    private String headProtraitUrl;

    private Integer gender;

    private String city;

    private String province;

    private String country;

    private String phone;

    private Integer commissionMode;

    private Double commissionValue;

    private Integer status;

    private Date joinTime;

    private Date lastLoginTime;

    private String modifier;

    private String creator;

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getParentAgent() {
        return parentAgent;
    }

    public void setParentAgent(String parentAgent) {
        this.parentAgent = parentAgent == null ? null : parentAgent.trim();
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getCommissionMode() {
        return commissionMode;
    }

    public void setCommissionMode(Integer commissionMode) {
        this.commissionMode = commissionMode;
    }

    public Double getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(Double commissionValue) {
        this.commissionValue = commissionValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
}