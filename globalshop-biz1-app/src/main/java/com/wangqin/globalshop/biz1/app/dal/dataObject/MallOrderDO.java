package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class MallOrderDO extends BaseModel {
    private Long id;

    private String customerNo;

    private String orderNo;

    private String companyNo;

    private String channelNo;

    private String channelName;

    private String channelOrderNo;

    private String channelCustomerNo;

    private String channelType;

    private String shopCode;

    private String wxPayTradeNo;

    private Double totalAmount;

    private Double actualAmount;

    private Integer payType;

    private Date orderTime;

    private Integer status;

    private String memo;

    private String idCard;

    private String idcardPicFront;

    private String idcardPicReverse;

    private Long source;

    private Double freight;

    private Double freightAgent;

    private Date gmtModify;

    private Date gmtCreate;

    private Boolean isDel;

    private String modifier;

    private String creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo == null ? null : channelOrderNo.trim();
    }

    public String getChannelCustomerNo() {
        return channelCustomerNo;
    }

    public void setChannelCustomerNo(String channelCustomerNo) {
        this.channelCustomerNo = channelCustomerNo == null ? null : channelCustomerNo.trim();
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getWxPayTradeNo() {
        return wxPayTradeNo;
    }

    public void setWxPayTradeNo(String wxPayTradeNo) {
        this.wxPayTradeNo = wxPayTradeNo == null ? null : wxPayTradeNo.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getIdcardPicFront() {
        return idcardPicFront;
    }

    public void setIdcardPicFront(String idcardPicFront) {
        this.idcardPicFront = idcardPicFront == null ? null : idcardPicFront.trim();
    }

    public String getIdcardPicReverse() {
        return idcardPicReverse;
    }

    public void setIdcardPicReverse(String idcardPicReverse) {
        this.idcardPicReverse = idcardPicReverse == null ? null : idcardPicReverse.trim();
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getFreightAgent() {
        return freightAgent;
    }

    public void setFreightAgent(Double freightAgent) {
        this.freightAgent = freightAgent;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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
}