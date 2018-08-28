package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class CustomsDeclareDO extends BaseModel {
    private Long id;

    private String businessNo;

    private String accountBookNo;

    private String ieFlag;

    private String preEntryNumber;

    private String importType;

    private String inOutDateStr;

    private String iePort;

    private String destinationPort;

    private String trafName;

    private String voyageNo;

    private String trafNo;

    private String trafMode;

    private String declareCompanyType;

    private String declareCompanyCode;

    private String declareCompanyName;

    private String companyName;

    private String companyCode;

    private String eCommerceCode;

    private String eCommerceName;

    private String logisCompanyName;

    private String logisCompanyCode;

    private String orderNo;

    private String wayBill;

    private String billNo;

    private String tradeCountry;

    private Double packNo;

    private Double grossWeight;

    private Double netWeight;

    private String warpType;

    private String remark;

    private String declPort;

    private String enteringPerson;

    private String enteringCompanyName;

    private String declarantNo;

    private String customsField;

    private String senderName;

    private String consignee;

    private String senderCountry;

    private String senderCity;

    private String paperType;

    private String paperNumber;

    private String consigneeAddress;

    private String purchaserTelNumber;

    private String buyerIdType;

    private String buyerIdNumber;

    private String buyerName;

    private Double worth;

    private Double feeAmount;

    private Double insureAmount;

    private String currCode;

    private String mainGName;

    private String internalAreaCompanyNo;

    private String internalAreaCompanyName;

    private String assureCode;

    private String applicationFormNo;

    private String isAuthorize;

    private String licenseNo;

    private String personalGoodsFormNo;

    private String approveResult;

    private String approveComment;

    private String processTime;

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

    public String getAccountBookNo() {
        return accountBookNo;
    }

    public void setAccountBookNo(String accountBookNo) {
        this.accountBookNo = accountBookNo == null ? null : accountBookNo.trim();
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag == null ? null : ieFlag.trim();
    }

    public String getPreEntryNumber() {
        return preEntryNumber;
    }

    public void setPreEntryNumber(String preEntryNumber) {
        this.preEntryNumber = preEntryNumber == null ? null : preEntryNumber.trim();
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType == null ? null : importType.trim();
    }

    public String getInOutDateStr() {
        return inOutDateStr;
    }

    public void setInOutDateStr(String inOutDateStr) {
        this.inOutDateStr = inOutDateStr == null ? null : inOutDateStr.trim();
    }

    public String getIePort() {
        return iePort;
    }

    public void setIePort(String iePort) {
        this.iePort = iePort == null ? null : iePort.trim();
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort == null ? null : destinationPort.trim();
    }

    public String getTrafName() {
        return trafName;
    }

    public void setTrafName(String trafName) {
        this.trafName = trafName == null ? null : trafName.trim();
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo == null ? null : voyageNo.trim();
    }

    public String getTrafNo() {
        return trafNo;
    }

    public void setTrafNo(String trafNo) {
        this.trafNo = trafNo == null ? null : trafNo.trim();
    }

    public String getTrafMode() {
        return trafMode;
    }

    public void setTrafMode(String trafMode) {
        this.trafMode = trafMode == null ? null : trafMode.trim();
    }

    public String getDeclareCompanyType() {
        return declareCompanyType;
    }

    public void setDeclareCompanyType(String declareCompanyType) {
        this.declareCompanyType = declareCompanyType == null ? null : declareCompanyType.trim();
    }

    public String getDeclareCompanyCode() {
        return declareCompanyCode;
    }

    public void setDeclareCompanyCode(String declareCompanyCode) {
        this.declareCompanyCode = declareCompanyCode == null ? null : declareCompanyCode.trim();
    }

    public String getDeclareCompanyName() {
        return declareCompanyName;
    }

    public void setDeclareCompanyName(String declareCompanyName) {
        this.declareCompanyName = declareCompanyName == null ? null : declareCompanyName.trim();
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getWayBill() {
        return wayBill;
    }

    public void setWayBill(String wayBill) {
        this.wayBill = wayBill == null ? null : wayBill.trim();
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public String getTradeCountry() {
        return tradeCountry;
    }

    public void setTradeCountry(String tradeCountry) {
        this.tradeCountry = tradeCountry == null ? null : tradeCountry.trim();
    }

    public Double getPackNo() {
        return packNo;
    }

    public void setPackNo(Double packNo) {
        this.packNo = packNo;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public String getWarpType() {
        return warpType;
    }

    public void setWarpType(String warpType) {
        this.warpType = warpType == null ? null : warpType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDeclPort() {
        return declPort;
    }

    public void setDeclPort(String declPort) {
        this.declPort = declPort == null ? null : declPort.trim();
    }

    public String getEnteringPerson() {
        return enteringPerson;
    }

    public void setEnteringPerson(String enteringPerson) {
        this.enteringPerson = enteringPerson == null ? null : enteringPerson.trim();
    }

    public String getEnteringCompanyName() {
        return enteringCompanyName;
    }

    public void setEnteringCompanyName(String enteringCompanyName) {
        this.enteringCompanyName = enteringCompanyName == null ? null : enteringCompanyName.trim();
    }

    public String getDeclarantNo() {
        return declarantNo;
    }

    public void setDeclarantNo(String declarantNo) {
        this.declarantNo = declarantNo == null ? null : declarantNo.trim();
    }

    public String getCustomsField() {
        return customsField;
    }

    public void setCustomsField(String customsField) {
        this.customsField = customsField == null ? null : customsField.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry == null ? null : senderCountry.trim();
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity == null ? null : senderCity.trim();
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

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getPurchaserTelNumber() {
        return purchaserTelNumber;
    }

    public void setPurchaserTelNumber(String purchaserTelNumber) {
        this.purchaserTelNumber = purchaserTelNumber == null ? null : purchaserTelNumber.trim();
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType == null ? null : buyerIdType.trim();
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber == null ? null : buyerIdNumber.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public Double getWorth() {
        return worth;
    }

    public void setWorth(Double worth) {
        this.worth = worth;
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

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode == null ? null : currCode.trim();
    }

    public String getMainGName() {
        return mainGName;
    }

    public void setMainGName(String mainGName) {
        this.mainGName = mainGName == null ? null : mainGName.trim();
    }

    public String getInternalAreaCompanyNo() {
        return internalAreaCompanyNo;
    }

    public void setInternalAreaCompanyNo(String internalAreaCompanyNo) {
        this.internalAreaCompanyNo = internalAreaCompanyNo == null ? null : internalAreaCompanyNo.trim();
    }

    public String getInternalAreaCompanyName() {
        return internalAreaCompanyName;
    }

    public void setInternalAreaCompanyName(String internalAreaCompanyName) {
        this.internalAreaCompanyName = internalAreaCompanyName == null ? null : internalAreaCompanyName.trim();
    }

    public String getAssureCode() {
        return assureCode;
    }

    public void setAssureCode(String assureCode) {
        this.assureCode = assureCode == null ? null : assureCode.trim();
    }

    public String getApplicationFormNo() {
        return applicationFormNo;
    }

    public void setApplicationFormNo(String applicationFormNo) {
        this.applicationFormNo = applicationFormNo == null ? null : applicationFormNo.trim();
    }

    public String getIsAuthorize() {
        return isAuthorize;
    }

    public void setIsAuthorize(String isAuthorize) {
        this.isAuthorize = isAuthorize == null ? null : isAuthorize.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public String getPersonalGoodsFormNo() {
        return personalGoodsFormNo;
    }

    public void setPersonalGoodsFormNo(String personalGoodsFormNo) {
        this.personalGoodsFormNo = personalGoodsFormNo == null ? null : personalGoodsFormNo.trim();
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult == null ? null : approveResult.trim();
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment == null ? null : approveComment.trim();
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime == null ? null : processTime.trim();
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