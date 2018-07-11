package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Getter@Setter
public class MallOrderVO extends MallOrderDO {
    private List<MallSubOrderDO> outerOrderDetails;
    private String dealerCode;
    private String idCard;
    private String platformType;
    private String receiverState;
    
    private Integer payType;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endGmtCreate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startGmtCreate;
    private String outerOrderDetailList;
    //-----------做显示----------------
    private Long id;
    private String orderNo;
    private String dealerName;
    private String shopCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;
    private String channelOrderNo;
    private String receiver;
    private Integer status;
    private String receiverCity;
    private String receiverCountry;
    private String receiverDistrict;
    private String telephone;
    private String creator;
    private String remark;
    private String addressDetail;
    private String skuCode;
    private boolean isDel;

}