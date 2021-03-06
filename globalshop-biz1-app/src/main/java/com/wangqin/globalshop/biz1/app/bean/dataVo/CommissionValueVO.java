package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 指定代理（或含父代理）的分佣比率信息 VO
 * <li> 如果该用户是一级代理，只写一级代理的部分
 * <li> 如果该用户是二级代理，字段都需要填写
 *
 * @author angus
 * @date 2018/7/31
 */
@Setter
@Getter
@ToString
public class CommissionValueVO {
    /**
     * 一级分佣 ID
     */
    // private String levelOneUserNo;
    private String levelOneUserId;

    /**
     * 一级分佣 分佣模式，0 为按百分比，1 为按金额
     */
    private String levelOneCommissionMode;

    /**
     * 一级分佣 分佣数字值，百分比模式如 5% 填 0.05，金额模式则为金额
     */
    // private Double levelOneCommissionValue;
    private String levelOneCommissionRate;

    /**
     * 二级分佣 ID
     */
    // private String levelTwoUserNo;
    private String levelTwoUserId;

    /**
     * 二级分佣 分佣模式，0 为按百分比，1 为按金额
     */
    private String levelTwoCommissionMode;

    /**
     * 二级分佣 分佣数字值，百分比模式如 5% 填 0.05，金额模式则为金额
     */
    // private Double levelTwoCommissionValue;
    private String levelTwoCommissionRate;
}
