package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;

import java.util.Date;

/**
 * 代理管理展示列表项对应的 VO
 *
 * @author angus
 * @date 2018/7/31
 */
@Data
public class MallSaleAgentItemVO {

    /**
     * 唯一userNo，需要跟Auth_User一致
     */
    private String userNo;

    /**
     * 头像
     */
    private String headProtraitUrl;

    /**
     * 代理人的微信号/昵称
     */
    private String agentName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 二级代理人数（一级代理列表下有效）
     */
    private Integer childAgentsNum;

    /**
     * 二级代理所属的一级代理（二级代理列表下有效）
     */
    private String parentAgent;

    /**
     * 一级代理姓名（二级代理列表下有效）
     */
    private String parentAgentName;

    /**
     * 佣金模式
     * <li> 0 为按百分比
     * <li> 1 为按金额
     */
    private Long commissionMode;

    /**
     * 佣金数字值，百分比模式如 5% 填 0.05，金额模式则为金额
     */
    private Double commissionValue;

    /**
     * 待结佣金（由其他接口给出）
     */
    private Double outstandingCommission;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态
     * <li> 0 正常
     * <li> 1 已解除
     */
    private Boolean status;

    /**
     * 加入时间
     */
    private Date joinTime;

}
