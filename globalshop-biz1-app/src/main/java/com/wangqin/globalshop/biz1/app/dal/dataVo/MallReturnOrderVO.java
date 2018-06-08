package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter@Setter
public class MallReturnOrderVO extends MallReturnOrderDO {
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
    /**是否入库*/
    private Byte isCheckin;
    private String customerOpenId;
    private String telephone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receiveTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnPayTime;
    private String remark;
    private Integer returnRefer;
    private String proofImg;
    private Integer returnType;
    private Date gmtCreate;
    private Date gmtModify;
    private Boolean isDel;
    private String creator;
    private String modifier;
    /**是否国内退货*/
    private Integer isGn;
    private Long erpOrderId;

}