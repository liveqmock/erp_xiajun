package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ShippingTrackDO {
    private Long id;

    private String orderNo;

    private String waybillNo;

    private String logisticNo;

    private Integer logisticsStatus;

    private Double weight;

    private String volume;

    private Double totalfee;

    private Date receiveTime;

    private Date overseaInTime;

    private Date overseaOutTime;

    private Date overseaOnTransferTime;

    private Date inlandInTime;

    private Date inlandOutTime;

    private String inlandExpressCompanyCode;

    private String inlandExpressNum;

    private Date buyerSignTime;

    private String airTakeOff;

    private String airlines;

    private String flight;

    private String trackInfo;

    private Date gmtCreate;

    private Date gmtModify;

    private Boolean isDel;

    private String creator;

    private String modifier;

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
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo == null ? null : logisticNo.trim();
    }

    public Integer getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Integer logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    public Double getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(Double totalfee) {
        this.totalfee = totalfee;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getOverseaInTime() {
        return overseaInTime;
    }

    public void setOverseaInTime(Date overseaInTime) {
        this.overseaInTime = overseaInTime;
    }

    public Date getOverseaOutTime() {
        return overseaOutTime;
    }

    public void setOverseaOutTime(Date overseaOutTime) {
        this.overseaOutTime = overseaOutTime;
    }

    public Date getOverseaOnTransferTime() {
        return overseaOnTransferTime;
    }

    public void setOverseaOnTransferTime(Date overseaOnTransferTime) {
        this.overseaOnTransferTime = overseaOnTransferTime;
    }

    public Date getInlandInTime() {
        return inlandInTime;
    }

    public void setInlandInTime(Date inlandInTime) {
        this.inlandInTime = inlandInTime;
    }

    public Date getInlandOutTime() {
        return inlandOutTime;
    }

    public void setInlandOutTime(Date inlandOutTime) {
        this.inlandOutTime = inlandOutTime;
    }

    public String getInlandExpressCompanyCode() {
        return inlandExpressCompanyCode;
    }

    public void setInlandExpressCompanyCode(String inlandExpressCompanyCode) {
        this.inlandExpressCompanyCode = inlandExpressCompanyCode == null ? null : inlandExpressCompanyCode.trim();
    }

    public String getInlandExpressNum() {
        return inlandExpressNum;
    }

    public void setInlandExpressNum(String inlandExpressNum) {
        this.inlandExpressNum = inlandExpressNum == null ? null : inlandExpressNum.trim();
    }

    public Date getBuyerSignTime() {
        return buyerSignTime;
    }

    public void setBuyerSignTime(Date buyerSignTime) {
        this.buyerSignTime = buyerSignTime;
    }

    public String getAirTakeOff() {
        return airTakeOff;
    }

    public void setAirTakeOff(String airTakeOff) {
        this.airTakeOff = airTakeOff == null ? null : airTakeOff.trim();
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines == null ? null : airlines.trim();
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight == null ? null : flight.trim();
    }

    public String getTrackInfo() {
        return trackInfo;
    }

    public void setTrackInfo(String trackInfo) {
        this.trackInfo = trackInfo == null ? null : trackInfo.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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