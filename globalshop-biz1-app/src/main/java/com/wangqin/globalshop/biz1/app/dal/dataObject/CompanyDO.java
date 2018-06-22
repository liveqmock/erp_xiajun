package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class CompanyDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String companyName;

    private Integer status;

    private String shopName;

    private String logoUrl;

    private Integer forceIdcard;

    private String tel;

    private String im;

    private String serviceTime;

    private Integer forceIdcardUpload;

    private String creator;

    private String modifier;

    private String intro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public Integer getForceIdcard() {
        return forceIdcard;
    }

    public void setForceIdcard(Integer forceIdcard) {
        this.forceIdcard = forceIdcard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im == null ? null : im.trim();
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime == null ? null : serviceTime.trim();
    }

    public Integer getForceIdcardUpload() {
        return forceIdcardUpload;
    }

    public void setForceIdcardUpload(Integer forceIdcardUpload) {
        this.forceIdcardUpload = forceIdcardUpload;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}