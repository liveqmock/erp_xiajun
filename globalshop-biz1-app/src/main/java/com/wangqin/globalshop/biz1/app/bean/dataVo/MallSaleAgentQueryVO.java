package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 代理管理查询参数对应的 VO
 * <p>
 * 前端查询参数示例：
 * <pre>
 *
 * </pre>
 *
 * @author angus
 * @date 2018/7/31
 */
@Setter
@Getter
@ToString
public class MallSaleAgentQueryVO {

    /**
     * 唯一 userNo，需要跟 Auth_User 一致
     * <p>
     * 与 mall_sale_agent 表中的 user_no 字段对应
     */
    private String userNo;

    /**
     * 代理人的微信号/昵称
     * <p>
     * 与 mall_sale_agent 表中的 agent_name 字段对应
     */
    private String agentName;

    /**
     * 上级代理的 userNo，如已是一级代理该值为 null
     * <p>
     * 与 mall_sale_agent 表中的 parent_agent 字段对应
     */
    private String parentAgent;

    /**
     * 所属公司id
     * <p>
     * 与 mall_sale_agent 表中的 company_no 字段对应
     */
    private String companyNo;

    public MallSaleAgentQueryVO() {
        this.companyNo = AppUtil.getLoginUserCompanyNo();
    }
}
