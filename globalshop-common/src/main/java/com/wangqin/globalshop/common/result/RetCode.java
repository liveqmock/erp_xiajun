package com.wangqin.globalshop.common.result;


public class RetCode {

    /** 成功 */
    public static final String SUCCESS                     = "0";
    /** 系统错误 */
    public static final String SYS_ERROR                   = "10001";
    /** 没有权限 */
    public static final String NO_PERMITTION               = "10002";
    /** 系统繁忙 */
    public static final String SYSTEM_BUSY                 = "10003";
    /** Illegal request */
    public static final String ILLEGAL_REQUEST             = "10004";
    /** 请求方法不支持 */
    public static final String ILLEGAL_REQUEST_METHOD      = "10005";
    /** 参数错误 */
    public static final String ILLEGAL_ARGS                = "20001";
    /** 数据异常 */
    public static final String WRONG_DATA                  = "20002";
    /** 没有数据 */
    public static final String NO_DATA                     = "20003";
    /** 用户信息更新失败 */
    public static final String ACCOUNT_UPDATE_FAIL         = "40001";
    /** 用户配置信息更新失败 */
    public static final String ACCOUNT_SETTING_UPDATE_FALI = "40002";
    /** 账号未激活 */
    public static final String ACCOUNT_INACTIVE            = "40003";
    /** 用户不存在 */
    public static final String ACCOUNT_NOT_EXISTS          = "40004";
    /** 账号已经存在 */
    public static final String ACCOUNT_EXIST               = "40005";
    /** 用户名或者密码错误 */
    public static final String ACCOUNT_WRONG_PASSWORD      = "40006";
    /** 账号被冻结 */
    public static final String ACCOUNT_FREZZE              = "40007";

}