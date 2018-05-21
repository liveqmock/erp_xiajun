package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ChannelAccountDO {
    private Long id;

    private String channelNo;

    private Byte type;

    private String channelName;

    private Long channelId;

    private Byte status;

    private String shopName;

    private String shopCode;

    private Long companyNo;

    private String appKey;

    private String appSecret;

    private String appValue1;

    private String value1Desc;

    private String appValue2;

    private String value2Desc;

    private String appValue3;

    private String value3Desc;

    private String accessToken;

    private String refreshToken;

    private String tokenUrl;

    private String serverUrl;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDel;

    private Date gmtModify;

    private String creator;

    private String modifier;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public Long getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(Long companyNo) {
        this.companyNo = companyNo;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getAppValue1() {
        return appValue1;
    }

    public void setAppValue1(String appValue1) {
        this.appValue1 = appValue1 == null ? null : appValue1.trim();
    }

    public String getValue1Desc() {
        return value1Desc;
    }

    public void setValue1Desc(String value1Desc) {
        this.value1Desc = value1Desc == null ? null : value1Desc.trim();
    }

    public String getAppValue2() {
        return appValue2;
    }

    public void setAppValue2(String appValue2) {
        this.appValue2 = appValue2 == null ? null : appValue2.trim();
    }

    public String getValue2Desc() {
        return value2Desc;
    }

    public void setValue2Desc(String value2Desc) {
        this.value2Desc = value2Desc == null ? null : value2Desc.trim();
    }

    public String getAppValue3() {
        return appValue3;
    }

    public void setAppValue3(String appValue3) {
        this.appValue3 = appValue3 == null ? null : appValue3.trim();
    }

    public String getValue3Desc() {
        return value3Desc;
    }

    public void setValue3Desc(String value3Desc) {
        this.value3Desc = value3Desc == null ? null : value3Desc.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl == null ? null : tokenUrl.trim();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl == null ? null : serverUrl.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
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