package com.wangqin.globalshop.biz1.app.dal.dataVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author EDZ
 */
@Getter@Setter
@ToString
public class BuyerTaskVO {
    /**买手ID*/
    private Long buyerId;
    /**商品详情*/
    private String detailList;
    private String purOrderNo;
    private String remark;
    private String taskDesc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskEndTime;
    private String taskTitle;

}
//@Getter@Setter
//class ItemTask {
//    private String skucode;
//    private Long buyerId;
//    private Double taskPrice;
//    private Double taskMaxPrice;
//    private Integer mode;
//    private Long count;
//    private Long taskMaxCount;
//    private String color;
//    private String scaleInt;
//    private String remark;
//    private Long skuId;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date taskStartTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date taskEndTime;
//    private String imageUrl;
//    private List<Pic> picList;
//
//}
//@Getter@Setter
//class Pic {
//    private String pic;
//    private String uid;
//    private String url;
//
//}

