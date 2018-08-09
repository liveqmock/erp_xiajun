package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;

/**
 * 商家添加/编辑/详情页面对应的 VO
 *
 * @author angus
 * @date 2018/8/6
 */
@Data
public class CompanyDetailVO {
    /**
     * 商家 logo
     * <p>
     * 对应 company 表的 logo_url 字段
     */
    private String logoUrl;

    /**
     * 商家名
     * <p>
     * 对应 company 表的 company_name 字段
     */
    private String companyName;

    /**
     * 商家简介
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
     * 支付类型 1.商家版 2.平台版
     * <p>
     * 对应 applet_config 表的 status 字段
     */
    private String status;

    /**
     * 商家 mch_id
     * <p>
     * 对应 applet_config 表的 mch_id 字段
     */
    private String mchId;

    /**
     * 商家 pay_key
     * <p>
     * 对应 applet_config 表的 pay_key 字段
     */
    private String payKey;

    /**
     * 中国大陆商家地址（省）
     * <p>
     * 对应 company 表的 state 字段
     */
    private String state;

    /**
     * 中国大陆商家地址（市）
     * <p>
     * 对应 company 表的 city 字段
     */
    private String city;

    /**
     * 中国大陆商家地址（地区）
     * <p>
     * 对应 company 表的 city 字段
     */
    private String district;

    /**
     * 中国大陆商家地址（详细地址）
     * <p>
     * 对应 company 表的 full_address 字段
     */
    private String fullAddress;

    /**
     * 海外商家地址（国家）
     * <p>
     * 对应 company 表的 country 字段
     */
    private Integer country;

    /**
     * 海外商家地址（详细地址）
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
     * 对应 company 表的  字段
     */
    private Double offlineAnnualSale;

    /**
     * 线上年销售额
     * <p>
     * 对应 company 表的 online_annual_sale 字段
     */
    private Double onlineAnnualSale;

    // ===== 编辑和查询商家额外附加的字段 =====
    /**
     * 商家编号
     * <p>
     * 对应 company 表的 company_no 字段
     */
    private String companyNo;

    /**
     * 商家管理员 ID
     * <p>
     * 对应 company 表的 admin_no 字段
     */
    private String adminNo;
}
