package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class AppletConfigDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String secret;

    private String appid;

    private String appletType;

    private String authorizerRefreshToken;

    private String authorizerAccessToken;

    private String creator;

    private String modifier;

    private Integer publishStatus;

    private Integer templetId;

    private String imgUrl;

    private String auditId;

    private String extJson;

    private String mchId;

    private String status;

    private String payKey;

    private String requestdomain;

    private String wsrequestdomain;

    private String uploaddomain;

    private String downloaddomain;

    private String webviewdomain;

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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppletType() {
        return appletType;
    }

    public void setAppletType(String appletType) {
        this.appletType = appletType == null ? null : appletType.trim();
    }

    public String getAuthorizerRefreshToken() {
        return authorizerRefreshToken;
    }

    public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
        this.authorizerRefreshToken = authorizerRefreshToken == null ? null : authorizerRefreshToken.trim();
    }

    public String getAuthorizerAccessToken() {
        return authorizerAccessToken;
    }

    public void setAuthorizerAccessToken(String authorizerAccessToken) {
        this.authorizerAccessToken = authorizerAccessToken == null ? null : authorizerAccessToken.trim();
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

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getTempletId() {
        return templetId;
    }

    public void setTempletId(Integer templetId) {
        this.templetId = templetId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId == null ? null : auditId.trim();
    }

    public String getExtJson() {
        return extJson;
    }

    public void setExtJson(String extJson) {
        this.extJson = extJson == null ? null : extJson.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey == null ? null : payKey.trim();
    }

    public String getRequestdomain() {
        return requestdomain;
    }

    public void setRequestdomain(String requestdomain) {
        this.requestdomain = requestdomain == null ? null : requestdomain.trim();
    }

    public String getWsrequestdomain() {
        return wsrequestdomain;
    }

    public void setWsrequestdomain(String wsrequestdomain) {
        this.wsrequestdomain = wsrequestdomain == null ? null : wsrequestdomain.trim();
    }

    public String getUploaddomain() {
        return uploaddomain;
    }

    public void setUploaddomain(String uploaddomain) {
        this.uploaddomain = uploaddomain == null ? null : uploaddomain.trim();
    }

    public String getDownloaddomain() {
        return downloaddomain;
    }

    public void setDownloaddomain(String downloaddomain) {
        this.downloaddomain = downloaddomain == null ? null : downloaddomain.trim();
    }

    public String getWebviewdomain() {
        return webviewdomain;
    }

    public void setWebviewdomain(String webviewdomain) {
        this.webviewdomain = webviewdomain == null ? null : webviewdomain.trim();
    }
}