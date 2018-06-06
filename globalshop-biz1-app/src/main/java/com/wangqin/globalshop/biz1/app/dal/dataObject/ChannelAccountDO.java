package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ChannelAccountDO extends BaseModel {
    private Long id;

    private Long channelId;

    private String channelNo;

    private Integer type;

    private String channelName;

    private String companyNo;

    private String shopCode;

    private String shopName;

    private Integer status;

    private String appKey;

    private String appSecret;

    private String accessToken;

    private String refreshToken;

    private String serverUrl;

    private String tokenUrl;

    private String accessKey;

    private String secreteKey;

    private String cookie;

    private String appValue1;

    private String value1Desc;

    private String appValue2;

    private String value2Desc;

    private String appValue3;

    private String value3Desc;

    private String creator;

    private String modifier;

    private Date gmtCreate;

    private Date gmtModify;

    private Boolean isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl == null ? null : serverUrl.trim();
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl == null ? null : tokenUrl.trim();
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey == null ? null : accessKey.trim();
    }

    public String getSecreteKey() {
        return secreteKey;
    }

    public void setSecreteKey(String secreteKey) {
        this.secreteKey = secreteKey == null ? null : secreteKey.trim();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
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
}