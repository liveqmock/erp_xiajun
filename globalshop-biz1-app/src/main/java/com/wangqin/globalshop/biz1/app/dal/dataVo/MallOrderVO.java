package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

import java.util.Date;
import java.util.List;

public class MallOrderVO extends MallOrderDO {
    private String orderDetailList;
    private List<MallSubOrderDO> outerOrderDetails;
    private String outerOrderDetailList;
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
    /**
     * 订单开始时间
     */
    private Date startGmtCreate;

    /**
     * 订单结束时间
     */
    private Date endGmtCreate;

    public String getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(String orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public List<MallSubOrderDO> getOuterOrderDetails() {
        return outerOrderDetails;
    }

    public void setOuterOrderDetails(List<MallSubOrderDO> outerOrderDetails) {
        this.outerOrderDetails = outerOrderDetails;
    }

    public String getOuterOrderDetailList() {
        return outerOrderDetailList;
    }

    public void setOuterOrderDetailList(String outerOrderDetailList) {
        this.outerOrderDetailList = outerOrderDetailList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCustomerNo() {
        return customerNo;
    }

    @Override
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    @Override
    public String getOrderNo() {
        return orderNo;
    }

    @Override
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    @Override
    public String getChannelNo() {
        return channelNo;
    }

    @Override
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    @Override
    public String getChannelName() {
        return channelName;
    }

    @Override
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    @Override
    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    @Override
    public String getChannelCustomerNo() {
        return channelCustomerNo;
    }

    @Override
    public void setChannelCustomerNo(String channelCustomerNo) {
        this.channelCustomerNo = channelCustomerNo;
    }

    @Override
    public String getChannelType() {
        return channelType;
    }

    @Override
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    @Override
    public String getShopCode() {
        return shopCode;
    }

    @Override
    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getWxPayTradeNo() {
        return wxPayTradeNo;
    }

    @Override
    public void setWxPayTradeNo(String wxPayTradeNo) {
        this.wxPayTradeNo = wxPayTradeNo;
    }

    @Override
    public Double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public Double getActualAmount() {
        return actualAmount;
    }

    @Override
    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    @Override
    public Integer getPayType() {
        return payType;
    }

    @Override
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    @Override
    public Date getOrderTime() {
        return orderTime;
    }

    @Override
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


    @Override
    public String getMemo() {
        return memo;
    }

    @Override
    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String getIdCard() {
        return idCard;
    }

    @Override
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String getIdcardPicFront() {
        return idcardPicFront;
    }

    @Override
    public void setIdcardPicFront(String idcardPicFront) {
        this.idcardPicFront = idcardPicFront;
    }

    @Override
    public String getIdcardPicReverse() {
        return idcardPicReverse;
    }

    @Override
    public void setIdcardPicReverse(String idcardPicReverse) {
        this.idcardPicReverse = idcardPicReverse;
    }

    @Override
    public Long getSource() {
        return source;
    }

    @Override
    public void setSource(Long source) {
        this.source = source;
    }

    @Override
    public Double getFreight() {
        return freight;
    }

    @Override
    public void setFreight(Double freight) {
        this.freight = freight;
    }

    @Override
    public Double getFreightAgent() {
        return freightAgent;
    }

    @Override
    public void setFreightAgent(Double freightAgent) {
        this.freightAgent = freightAgent;
    }

    @Override
    public Date getGmtModify() {
        return gmtModify;
    }

    @Override
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public Date getGmtCreate() {
        return gmtCreate;
    }

    @Override
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    @Override
    public String getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getStartGmtCreate() {
        return startGmtCreate;
    }

    public void setStartGmtCreate(Date startGmtCreate) {
        this.startGmtCreate = startGmtCreate;
    }

    public Date getEndGmtCreate() {
        return endGmtCreate;
    }

    public void setEndGmtCreate(Date endGmtCreate) {
        this.endGmtCreate = endGmtCreate;
    }
}