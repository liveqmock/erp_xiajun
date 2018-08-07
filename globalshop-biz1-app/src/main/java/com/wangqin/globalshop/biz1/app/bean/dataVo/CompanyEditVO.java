package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;

/**
 * 商户编辑页面对应的 VO
 *
 * @author angus
 * @date 2018/8/6
 */
@Data
public class CompanyEditVO {
    // ===== 以下信息需要由前端获取 =====

    /**
     * 公司 logo
     * <p>
     * 对应 company 表的 logo_url 字段
     */
    private String logoUrl;

    /**
     * 公司名
     * <p>
     * 对应 company 表的 company_name 字段
     */
    private String companyName;

    /**
     * 公司简介
     * <p>
     * 对应 company 表的 intro 字段
     */
    private String intro;

    /**
     * 登录名
     * <p>
     * 对应 auth_user 表的 login_name 字段
     */
    private String loginName;

    /**
     * 密码
     * <p>
     * 对应 auth_user 表的 password 字段
     */
    private String password;

    /**
     * 联系人
     * <p>
     * 对应 auth_user 表的 name 字段
     */
    private String name;

    /**
     * 手机
     * <p>
     * 对应 auth_user 表的 phone 字段
     */
    private String phone;

    /**
     * 邮箱
     * <p>
     * 对应 auth_user 表的 email 字段
     */
    private String email;

    /**
     * 接入模式
     * <p>
     * 对应 applet_config 表的 status 字段
     */
    private String status;

    /**
     * 商户 mch_id
     * <p>
     * 对应 applet_config 表的 mch_id 字段
     */
    private String mchId;

    /**
     * 商户 pay_key
     * <p>
     * 对应 applet_config 表的 pay_key 字段
     */
    private String payKey;

    /**
     * 中国大陆公司地址（省）
     * <p>
     * 对应 company 表的 state 字段
     */
    private String state;

    /**
     * 中国大陆公司地址（市）
     * <p>
     * 对应 company 表的 city 字段
     */
    private String city;

    /**
     * 中国大陆公司地址（地区）
     * <p>
     * 对应 company 表的 city 字段
     */
    private String district;

    /**
     * 中国大陆公司地址（详细地址）
     * <p>
     * 对应 company 表的 full_address 字段
     */
    private String fullAddress;

    /**
     * 海外公司地址（国家）
     * <p>
     * 对应 company 表的 country 字段
     */
    private String country;

    /**
     * 海外公司地址（详细地址）
     * <p>
     * 对应 company 表的 oversea_address 字段
     */
    private String overseaAddress;

    /**
     * 主要品类
     * <p>
     * 对应 company 表的 main_category 字段
     */
    private String mainCategory;

    /**
     * 线下年销售额
     * <p>
     * 对应 company 表的 offline_annual_sale 字段
     */
    private Double offlineAnnualSale;

    /**
     * 线上年销售额
     * <p>
     * 对应 company 表的 online_annual_sale 字段
     */
    private Double onlineAnnualSale;
}
