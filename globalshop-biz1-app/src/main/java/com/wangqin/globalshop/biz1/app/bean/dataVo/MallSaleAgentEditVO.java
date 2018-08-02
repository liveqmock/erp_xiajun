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
     * <li> 1 正常
     * <li> 0 已解除
     */
    private Integer status;

    /**
     * 手机号
     */
    private String phone;

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
