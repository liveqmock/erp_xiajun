package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;

import java.util.Date;

/**
 * 商户展示列表项对应的 VO
 *
 * @author angus
 * @date 2018/8/7
 */
@Data
public class CompanyItemVO {
    /**
     * 公司编号
     * <br>
     * 对应 company 表的 company_no 字段
     */
    private String companyNo;

    /**
     * 公司名称
     * <br>
     * 对应 company 表的 company_name 字段
     */
    private String companyName;

    /**
     * 联系人
     * <br>
     * 对应 auth_user 表的 name 字段
     */
    private String adminName;

    /**
     * 登录账号
     * <br>
     * 对应 auth_user 表的 login_name 字段
     */
    private String loginName;

    /**
     * 状态
     * <br>
     * 对应 company 表的 status 字段
     */
    private Integer status;

    /**
     * 联系电话
     * <br>
     * 对应 auth_user 表的 phone 字段
     */
    private String phone;

    /**
     * 邮箱
     * <br>
     * 对应 auth_user 表的 email 字段
     */
    private String email;

    /**
     * 开通时间
     * <br>
     * 对应 company 表的 gmt_create 字段
     */
    private Date gmtCreate;
}
