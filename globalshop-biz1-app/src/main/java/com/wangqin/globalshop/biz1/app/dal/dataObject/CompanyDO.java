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

    private String modifier;

    private String creator;

    private String state;

    private String city;

    private String district;

    private String fullAddress;

    private String overseaAddress;

    private String country;

    private String mainCategory;

    private Double offlineAnnualSale;

    private Double onlineAnnualSale;

    private String adminNo;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress == null ? null : fullAddress.trim();
    }

    public String getOverseaAddress() {
        return overseaAddress;
    }

    public void setOverseaAddress(String overseaAddress) {
        this.overseaAddress = overseaAddress == null ? null : overseaAddress.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory == null ? null : mainCategory.trim();
    }

    public Double getOfflineAnnualSale() {
        return offlineAnnualSale;
    }

    public void setOfflineAnnualSale(Double offlineAnnualSale) {
        this.offlineAnnualSale = offlineAnnualSale;
    }

    public Double getOnlineAnnualSale() {
        return onlineAnnualSale;
    }

    public void setOnlineAnnualSale(Double onlineAnnualSale) {
        this.onlineAnnualSale = onlineAnnualSale;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo == null ? null : adminNo.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}