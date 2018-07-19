package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class MallReturnOrderDO extends BaseModel {
    private Long id;

    private String orderNo;

    private Long outerOrderNo;

    private String subOrderNo;

    private Byte status;

    private String returnReason;

    private String returnReasonDetail;

    private Integer returnQuantity;

    private Double returnPrice;

    private Byte isCivil;

    private Byte isCheckin;

    private String customerOpenId;

    private String telephone;

    private Date receiveTime;

    private Date returnPayTime;

    private String remark;

    private Integer returnRefer;

    private String proofImg;

    private Integer returnType;

    private String modifier;

    private String creator;

    private String mallReturnOrderNo;

    private String companyNo;

    /**
     * 以下字段为售后订单展示准备
     */
    private String skuCode;

    private String skuPic;

    private String itemName;

    private String upc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getOuterOrderNo() {
        return outerOrderNo;
    }

    public void setOuterOrderNo(Long outerOrderNo) {
        this.outerOrderNo = outerOrderNo;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnReasonDetail() {
        return returnReasonDetail;
    }

    public void setReturnReasonDetail(String returnReasonDetail) {
        this.returnReasonDetail = returnReasonDetail;
    }

    public Integer getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(Integer returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public Double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(Double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public Byte getIsCivil() {
        return isCivil;
    }

    public void setIsCivil(Byte isCivil) {
        this.isCivil = isCivil;
    }

    public Byte getIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(Byte isCheckin) {
        this.isCheckin = isCheckin;
    }

    public String getCustomerOpenId() {
        return customerOpenId;
    }

    public void setCustomerOpenId(String customerOpenId) {
        this.customerOpenId = customerOpenId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReturnPayTime() {
        return returnPayTime;
    }

    public void setReturnPayTime(Date returnPayTime) {
        this.returnPayTime = returnPayTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getReturnRefer() {
        return returnRefer;
    }

    public void setReturnRefer(Integer returnRefer) {
        this.returnRefer = returnRefer;
    }

    public String getProofImg() {
        return proofImg;
    }

    public void setProofImg(String proofImg) {
        this.proofImg = proofImg;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public String getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMallReturnOrderNo() {
        return mallReturnOrderNo;
    }

    public void setMallReturnOrderNo(String mallReturnOrderNo) {
        this.mallReturnOrderNo = mallReturnOrderNo;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }
}