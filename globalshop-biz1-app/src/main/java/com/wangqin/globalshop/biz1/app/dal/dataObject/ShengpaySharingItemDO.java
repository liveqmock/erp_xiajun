package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ShengpaySharingItemDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String sharingReqNo;

    private String sharingNo;

    private String sdSharingNo;

    private String status;

    private Double sharingAmount;

    private Double sharingRate;

    private String payeeId;

    private String payeeIdType;

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

    public String getSharingReqNo() {
        return sharingReqNo;
    }

    public void setSharingReqNo(String sharingReqNo) {
        this.sharingReqNo = sharingReqNo == null ? null : sharingReqNo.trim();
    }

    public String getSharingNo() {
        return sharingNo;
    }

    public void setSharingNo(String sharingNo) {
        this.sharingNo = sharingNo == null ? null : sharingNo.trim();
    }

    public String getSdSharingNo() {
        return sdSharingNo;
    }

    public void setSdSharingNo(String sdSharingNo) {
        this.sdSharingNo = sdSharingNo == null ? null : sdSharingNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Double getSharingAmount() {
        return sharingAmount;
    }

    public void setSharingAmount(Double sharingAmount) {
        this.sharingAmount = sharingAmount;
    }

    public Double getSharingRate() {
        return sharingRate;
    }

    public void setSharingRate(Double sharingRate) {
        this.sharingRate = sharingRate;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId == null ? null : payeeId.trim();
    }

    public String getPayeeIdType() {
        return payeeIdType;
    }

    public void setPayeeIdType(String payeeIdType) {
        this.payeeIdType = payeeIdType == null ? null : payeeIdType.trim();
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