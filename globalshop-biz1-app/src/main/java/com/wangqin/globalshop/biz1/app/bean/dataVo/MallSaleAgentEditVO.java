package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Data;

/**
 * 代理管理编辑页面对应的 VO
 *
 * @author angus
 * @date 2018/8/1
 */
@Data
public class MallSaleAgentEditVO {
    /**
     * 唯一userNo，需要跟Auth_User一致
     */
    private String userNo;

    /**
     * 代理人的微信号/昵称
     */
    private String agentName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 状态
     * <li> 0 正常
     * <li> 1 已解除
     */
    private Boolean status;

    /**
     * 手机号
     */
    private String phone;

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
     * 所属公司id
     * <p>
     * 与 mall_sale_agent 表中的 company_no 字段对应
     */
    private String companyNo;

    public MallSaleAgentEditVO() {
        this.companyNo = AppUtil.getLoginUserCompanyNo();
    }

}
