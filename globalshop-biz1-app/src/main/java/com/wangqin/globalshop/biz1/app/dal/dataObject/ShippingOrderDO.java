package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ShippingOrderDO extends BaseModel {
    private Long id;

    private String shippingNo;

    private String subOrderNo;

    private String logisticNo;

    private String logisticCompany;

    private Integer type;

    private Integer status;

    private Integer transferStatus;

    private Integer syncSendStatus;

    private String tplPkgStatus;

    private Double freight;

    private Double skuWeight;

    private String mallOrders;

    private String shippingPrinter;

    private String companyNo;

    private String glsReturnBack;

    private String receiver;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String address;

    private String telephone;

    private String postcode;

    private String memo;

    private String idCard;

    private String idCardBack;

    private String idCardFront;

    private String creator;

    private String modifier;

    private String logisticType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo == null ? null : shippingNo.trim();
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo == null ? null : logisticNo.trim();
    }

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany == null ? null : logisticCompany.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Integer transferStatus) {
        this.transferStatus = transferStatus;
    }

    public Integer getSyncSendStatus() {
        return syncSendStatus;
    }

    public void setSyncSendStatus(Integer syncSendStatus) {
        this.syncSendStatus = syncSendStatus;
    }

    public String getTplPkgStatus() {
        return tplPkgStatus;
    }

    public void setTplPkgStatus(String tplPkgStatus) {
        this.tplPkgStatus = tplPkgStatus == null ? null : tplPkgStatus.trim();
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getSkuWeight() {
        return skuWeight;
    }

    public void setSkuWeight(Double skuWeight) {
        this.skuWeight = skuWeight;
    }

    public String getMallOrders() {
        return mallOrders;
    }

    public void setMallOrders(String mallOrders) {
        this.mallOrders = mallOrders == null ? null : mallOrders.trim();
    }

    public String getShippingPrinter() {
        return shippingPrinter;
    }

    public void setShippingPrinter(String shippingPrinter) {
        this.shippingPrinter = shippingPrinter == null ? null : shippingPrinter.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getGlsReturnBack() {
        return glsReturnBack;
    }

    public void setGlsReturnBack(String glsReturnBack) {
        this.glsReturnBack = glsReturnBack == null ? null : glsReturnBack.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState == null ? null : receiverState.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack == null ? null : idCardBack.trim();
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront == null ? null : idCardFront.trim();
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

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType == null ? null : logisticType.trim();
    }
}