package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class MallOrderVO extends MallOrderDO {
    private List<MallSubOrderDO> outerOrderDetails;
    private Long salesId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;
    private String receiver;
    private String telephone;
    private String targetNo;
    private String idCard;
    private String platformType;
    private Integer payType;
    private String addressDetail;
    private String remark;
    private String salesName;
    private String receiverState;
    private String receiverCity;
    private String receiverDistrict;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endGmtCreate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startGmtCreate;
    private String outerOrderDetailList;
    //-----------做显示----------------

    public String getTargetNo() {
        return targetNo;
    }

    public void setTargetNo(String targetNo) {
        this.targetNo = targetNo;
    }

    @Override
    public String getIdCard() {
        return idCard;
    }

    @Override
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }


    public List<MallSubOrderDO> getOuterOrderDetails() {
        return outerOrderDetails;
    }

    public void setOuterOrderDetails(List<MallSubOrderDO> outerOrderDetails) {
        this.outerOrderDetails = outerOrderDetails;
    }

    public Date getStartGmtCreate() {
        return startGmtCreate;
    }

    public void setStartGmtCreate(Date startGmtCreate) {
        this.startGmtCreate = startGmtCreate;
    }
    public Long getSalesId() {
        return salesId;
    }

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    @Override
    public Date getOrderTime() {
        return orderTime;
    }

    public Date getEndGmtCreate() {
        return endGmtCreate;
    }

    public void setEndGmtCreate(Date endGmtCreate) {
        this.endGmtCreate = endGmtCreate;
    }

    @Override
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    @Override
    public Integer getPayType() {
        return payType;
    }
    @Override
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getOuterOrderDetailList() {
        return outerOrderDetailList;
    }

    public void setOuterOrderDetailList(String outerOrderDetailList) {
        this.outerOrderDetailList = outerOrderDetailList;
    }
}