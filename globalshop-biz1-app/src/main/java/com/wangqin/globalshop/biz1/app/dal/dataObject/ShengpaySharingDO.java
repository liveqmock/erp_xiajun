package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ShengpaySharingDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String merchantOrderNo;

    private String sharingOrderNo;

    private String sharingQueryOrderNo;

    private String sharingReqNo;

    private String status;

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

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
    }

    public String getSharingOrderNo() {
        return sharingOrderNo;
    }

    public void setSharingOrderNo(String sharingOrderNo) {
        this.sharingOrderNo = sharingOrderNo == null ? null : sharingOrderNo.trim();
    }

    public String getSharingQueryOrderNo() {
        return sharingQueryOrderNo;
    }

    public void setSharingQueryOrderNo(String sharingQueryOrderNo) {
        this.sharingQueryOrderNo = sharingQueryOrderNo == null ? null : sharingQueryOrderNo.trim();
    }

    public String getSharingReqNo() {
        return sharingReqNo;
    }

    public void setSharingReqNo(String sharingReqNo) {
        this.sharingReqNo = sharingReqNo == null ? null : sharingReqNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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