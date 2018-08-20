package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class JdShopOauthDO extends BaseModel {
    private Long id;

    private Long version;

    private String channelNo;

    private String companyNo;

    private String shopCode;

    private String accessToken;

    private Date expiresTime;

    private String refreshToken;

    private Date refreshExpriresTime;

    private String appKey;

    private String appsecretKey;

    private String accessKey;

    private String accessValue;

    private String serverUrl;

    private String tokenUrl;

    private String shopType;

    private Boolean open;

    private String modifier;

    private String creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Date getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public Date getRefreshExpriresTime() {
        return refreshExpriresTime;
    }

    public void setRefreshExpriresTime(Date refreshExpriresTime) {
        this.refreshExpriresTime = refreshExpriresTime;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getAppsecretKey() {
        return appsecretKey;
    }

    public void setAppsecretKey(String appsecretKey) {
        this.appsecretKey = appsecretKey == null ? null : appsecretKey.trim();
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey == null ? null : accessKey.trim();
    }

    public String getAccessValue() {
        return accessValue;
    }

    public void setAccessValue(String accessValue) {
        this.accessValue = accessValue == null ? null : accessValue.trim();
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

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
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