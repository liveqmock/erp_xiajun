package com.wangqin.globalshop.order.app.kuaidi_bean;

import java.util.List;

/**
 * 通用物流轨迹信息
 *
 * @author angus
 * @date 2018/7/25
 */
public class CommonShippingTrack {
    /**
     * 查询平台
     */
    private String platform;
    /**
     * 发货单号/包裹号
     */
    private String shippingNo;
    /**
     * 物流单号
     */
    private String logisticNo;
    /**
     * 物流公司代码
     */
    private String logisticCompanyCode;
    /**
     * 物流公司名字
     */
    private String logisticCompany;
    /**
     * 查询结果状态码
     */
    private String status;
    /**
     * 查询结果状态信息
     */
    private String statusInfo;
    /**
     * 运单状态码
     */
    private String state;
    /**
     * 运单状态信息
     */
    private String stateInfo;
    /**
     * 物流轨迹节点信息
     */
    private List<CommonShippingTrackNode> shippingTrackInfo;

    /**
     * 物流轨迹信息构建器
     */
    public static class Builder {
        private String platform;
        private String shippingNo;
        private String logisticNo;
        private String logisticCompanyCode;
        private String logisticCompany;
        private String status;
        private String statusInfo;
        private String state;
        private String stateInfo;
        private List<CommonShippingTrackNode> shippingTrackInfo;

        public Builder(List<CommonShippingTrackNode> shippingTrackInfo) {
            this.shippingTrackInfo = shippingTrackInfo;
        }

        public Builder platform(String platform) {
            this.platform = platform;
            return this;
        }

        public Builder shippingNo(String shippingNo) {
            this.shippingNo = shippingNo;
            return this;
        }

        public Builder logisticNo(String logisticNo) {
            this.logisticNo = logisticNo;
            return this;
        }

        public Builder logisticCompanyCode(String logisticCompanyCode) {
            this.logisticCompanyCode = logisticCompanyCode;
            return this;
        }

        public Builder logisticCompany(String logisticCompany) {
            this.logisticCompany = logisticCompany;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder statusInfo(String statusInfo) {
            this.statusInfo = statusInfo;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder stateInfo(String stateInfo) {
            this.stateInfo = stateInfo;
            return this;
        }

        public CommonShippingTrack build() {
            return new CommonShippingTrack(this);
        }

    }

    private CommonShippingTrack(Builder builder) {
        this.shippingTrackInfo = builder.shippingTrackInfo;
        this.platform = builder.platform;
        this.shippingNo = builder.shippingNo;
        this.logisticNo = builder.logisticNo;
        this.logisticCompanyCode = builder.logisticCompanyCode;
        this.logisticCompany = builder.logisticCompany;
        this.status = builder.status;
        this.statusInfo = builder.statusInfo;
        this.state = builder.state;
        this.stateInfo = builder.stateInfo;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

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

    public String getLogisticCompanyCode() {
        return logisticCompanyCode;
    }

    public void setLogisticCompanyCode(String logisticCompanyCode) {
        this.logisticCompanyCode = logisticCompanyCode;
    }

    public String getLogisticCompany() {
        return logisticCompany;
    }

    public void setLogisticCompany(String logisticCompany) {
        this.logisticCompany = logisticCompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<CommonShippingTrackNode> getShippingTrackInfo() {
        return shippingTrackInfo;
    }

    public void setShippingTrackInfo(List<CommonShippingTrackNode> shippingTrackInfo) {
        this.shippingTrackInfo = shippingTrackInfo;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
