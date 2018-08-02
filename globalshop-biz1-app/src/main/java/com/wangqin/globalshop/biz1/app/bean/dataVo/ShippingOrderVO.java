package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Data
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

//    private String salesId;
}
