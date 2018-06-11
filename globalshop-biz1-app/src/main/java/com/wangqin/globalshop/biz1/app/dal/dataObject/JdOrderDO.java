package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class JdOrderDO {
    private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    private Long version;

    private Boolean isDel;

    private String creator;

    private String modifier;

    private String shopCode;

    private String sendStatus;

    private Date orderModifyTime;

    private String errorMassge;

    private String channelOrderNo;

    private String orderJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public Date getOrderModifyTime() {
        return orderModifyTime;
    }

    public void setOrderModifyTime(Date orderModifyTime) {
        this.orderModifyTime = orderModifyTime;
    }

    public String getErrorMassge() {
        return errorMassge;
    }

    public void setErrorMassge(String errorMassge) {
        this.errorMassge = errorMassge == null ? null : errorMassge.trim();
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo == null ? null : channelOrderNo.trim();
    }

    public String getOrderJson() {
        return orderJson;
    }

    public void setOrderJson(String orderJson) {
        this.orderJson = orderJson == null ? null : orderJson.trim();
    }
}