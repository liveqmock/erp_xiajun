package com.wangqin.globalshop.biz1.app.vo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public class ShippingOrderVO extends ShippingOrderDO {
    /**包裹号*/
    private String shippingNo;
//    /**物流单号*/
//    private String logisticNo;
    /**物流公司*/
    private String logisticCompany;
//    private String erpNo;
    /**收货人*/
    private String receiver;
    /**电话*/
    private String telephone;
    //状态
    private Integer status;
    private Integer type;
    //发货时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startOrderTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOrderTime;
    private String companyNo;
    /**
     * 销售ID
     */
    private Integer salesId;

    private String customer_no;

    private Integer syncSendStatus;

    @Override
    public String getShippingNo() {
        return shippingNo;
    }

    @Override
    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    @Override
    public String getLogisticCompany() {
        return logisticCompany;
    }

    @Override
    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    @Override
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    public Integer getType() {
        return type;
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
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

    @Override
    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getCustomer_no() {
        return customer_no;
    }

    public void setCustomer_no(String customer_no) {
        this.customer_no = customer_no;
    }

    @Override
    public Integer getSyncSendStatus() {
        return syncSendStatus;
    }

    @Override
    public void setSyncSendStatus(Integer syncSendStatus) {
        this.syncSendStatus = syncSendStatus;
    }
}
