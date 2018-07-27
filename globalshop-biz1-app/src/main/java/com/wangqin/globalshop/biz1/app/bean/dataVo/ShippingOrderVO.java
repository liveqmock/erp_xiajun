package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public class ShippingOrderVO {

    /**
     * 发货单号
     */
    private String shippingNo;

    /**
     * 物流运单号
     */
    private String logisticNo;

    /**
     * 物流公司名称
     */
    private String logisticCompany;

    /**
     * 快递渠道
     */
    private Integer type;
    /**
     * 物流状态/运单状态
     */
    private Integer status;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 发货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startOrderTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOrderTime;

    private Long id;

    private Integer transferStatus;

    private Integer syncSendStatus;

    private String tplPkgStatus;

    private Double freight;

    private Double skuWeight;

    private String mallOrders;

    private String shippingPrinter;

    private String companyNo;

    private String glsReturnBack;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String address;

    private String postcode;

    private String memo;

    private String idCard;

    private String idCardBack;

    private String idCardFront;

    private String creator;

    private String modifier;

    private String logisticType;

    private String salesId;

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    public String getLogisticNo() {
        return logisticNo;
    }

    public void setLogisticNo(String logisticNo) {
        this.logisticNo = logisticNo;
    }

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
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

    public Date getStartOrderTime() {
        return startOrderTime;
    }

    public void setStartOrderTime(Date startOrderTime) {
        this.startOrderTime = startOrderTime;
    }

    public Date getEndOrderTime() {
        return endOrderTime;
    }

    public void setEndOrderTime(Date endOrderTime) {
        this.endOrderTime = endOrderTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.tplPkgStatus = tplPkgStatus;
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
        this.mallOrders = mallOrders;
    }

    public String getShippingPrinter() {
        return shippingPrinter;
    }

    public void setShippingPrinter(String shippingPrinter) {
        this.shippingPrinter = shippingPrinter;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getGlsReturnBack() {
        return glsReturnBack;
    }

    public void setGlsReturnBack(String glsReturnBack) {
        this.glsReturnBack = glsReturnBack;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }
}
