package com.wangqin.globalshop.biz1.app.dal.dataVo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author biscuit
 * @data 2018/06/15
 */
@Getter@Setter
public class ItemTask {
    private String skucode;
    private Long buyerId;
    private BigDecimal taskPrice;
    private BigDecimal taskMaxPrice;
    private Byte mode;
    private Integer count;
    private Integer taskMaxCount;
    private String color;
    private String scaleInt;
    private String remark;
    private Long skuId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskEndTime;
    private String imageUrl;
    private String picList;
}
