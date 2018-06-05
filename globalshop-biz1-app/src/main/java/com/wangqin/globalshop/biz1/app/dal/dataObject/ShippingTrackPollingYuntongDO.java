package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ShippingTrackPollingYuntongDO extends BaseModel {
    private Long id;

    private String waybillNo;

    private String inlandTransCode;

    private String inlandTransCompanyName;

    private String currentStatus;

    private Date statusTime;

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

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }

    public String getInlandTransCode() {
        return inlandTransCode;
    }

    public void setInlandTransCode(String inlandTransCode) {
        this.inlandTransCode = inlandTransCode == null ? null : inlandTransCode.trim();
    }

    public String getInlandTransCompanyName() {
        return inlandTransCompanyName;
    }

    public void setInlandTransCompanyName(String inlandTransCompanyName) {
        this.inlandTransCompanyName = inlandTransCompanyName == null ? null : inlandTransCompanyName.trim();
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus == null ? null : currentStatus.trim();
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
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