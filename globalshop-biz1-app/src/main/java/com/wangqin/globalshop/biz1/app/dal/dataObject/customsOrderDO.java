package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class CustomsOrderDO extends BaseModel {
    private Long id;

    private String businessNo;

    private String companyName;

    private String companyCode;

    private String ieFlag;

    private String payType;

    private String payCompanyCode;

    private String payCompanyName;

    private String payNumber;

    private Double orderTotalAmount;

    private Double orderGoodsAmount;

    private Double discount;

    private String orderNo;

    private Double orderTaxAmount;

    private Double feeAmount;

    private Double insureAmount;

    private String eCommerceCode;

    private String eCommerceName;

    private String tradeTime;

    private String currCode;

    private Double totalAmount;

    private String consigneeEmail;

    private String consigneeTel;

    private String consignee;

    private String consigneeAddress;

    private Integer totalCount;

    private String batchNumbers;

    private String consigneeDitrict;

    private String postMode;

    private String senderCountry;

    private String senderName;

    private String logisCompanyName;

    private String logisCompanyCode;

    private String zipCode;

    private String note;

    private String wayBills;

    private String rate;

    private String userProcotol;

    private String purchaserId;

    private String name;

    private String email;

    private String telNumber;

    private String paperType;

    private String paperNumber;

    private String address;

    private String chkMark;

    private String noticeDate;

    private String resultInfo;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag == null ? null : ieFlag.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayCompanyCode() {
        return payCompanyCode;
    }

    public void setPayCompanyCode(String payCompanyCode) {
        this.payCompanyCode = payCompanyCode == null ? null : payCompanyCode.trim();
    }

    public String getPayCompanyName() {
        return payCompanyName;
    }

    public void setPayCompanyName(String payCompanyName) {
        this.payCompanyName = payCompanyName == null ? null : payCompanyName.trim();
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber == null ? null : payNumber.trim();
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public Double getOrderGoodsAmount() {
        return orderGoodsAmount;
    }

    public void setOrderGoodsAmount(Double orderGoodsAmount) {
        this.orderGoodsAmount = orderGoodsAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Double getOrderTaxAmount() {
        return orderTaxAmount;
    }

    public void setOrderTaxAmount(Double orderTaxAmount) {
        this.orderTaxAmount = orderTaxAmount;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Double getInsureAmount() {
        return insureAmount;
    }

    public void setInsureAmount(Double insureAmount) {
        this.insureAmount = insureAmount;
    }

    public String geteCommerceCode() {
        return eCommerceCode;
    }

    public void seteCommerceCode(String eCommerceCode) {
        this.eCommerceCode = eCommerceCode == null ? null : eCommerceCode.trim();
    }

    public String geteCommerceName() {
        return eCommerceName;
    }

    public void seteCommerceName(String eCommerceName) {
        this.eCommerceName = eCommerceName == null ? null : eCommerceName.trim();
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode == null ? null : currCode.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getConsigneeEmail() {
        return consigneeEmail;
    }

    public void setConsigneeEmail(String consigneeEmail) {
        this.consigneeEmail = consigneeEmail == null ? null : consigneeEmail.trim();
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel == null ? null : consigneeTel.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getBatchNumbers() {
        return batchNumbers;
    }

    public void setBatchNumbers(String batchNumbers) {
        this.batchNumbers = batchNumbers == null ? null : batchNumbers.trim();
    }

    public String getConsigneeDitrict() {
        return consigneeDitrict;
    }

    public void setConsigneeDitrict(String consigneeDitrict) {
        this.consigneeDitrict = consigneeDitrict == null ? null : consigneeDitrict.trim();
    }

    public String getPostMode() {
        return postMode;
    }

    public void setPostMode(String postMode) {
        this.postMode = postMode == null ? null : postMode.trim();
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry == null ? null : senderCountry.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getLogisCompanyName() {
        return logisCompanyName;
    }

    public void setLogisCompanyName(String logisCompanyName) {
        this.logisCompanyName = logisCompanyName == null ? null : logisCompanyName.trim();
    }

    public String getLogisCompanyCode() {
        return logisCompanyCode;
    }

    public void setLogisCompanyCode(String logisCompanyCode) {
        this.logisCompanyCode = logisCompanyCode == null ? null : logisCompanyCode.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getWayBills() {
        return wayBills;
    }

    public void setWayBills(String wayBills) {
        this.wayBills = wayBills == null ? null : wayBills.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getUserProcotol() {
        return userProcotol;
    }

    public void setUserProcotol(String userProcotol) {
        this.userProcotol = userProcotol == null ? null : userProcotol.trim();
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId == null ? null : purchaserId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber == null ? null : telNumber.trim();
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType == null ? null : paperType.trim();
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber == null ? null : paperNumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getChkMark() {
        return chkMark;
    }

    public void setChkMark(String chkMark) {
        this.chkMark = chkMark == null ? null : chkMark.trim();
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate == null ? null : noticeDate.trim();
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo == null ? null : resultInfo.trim();
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