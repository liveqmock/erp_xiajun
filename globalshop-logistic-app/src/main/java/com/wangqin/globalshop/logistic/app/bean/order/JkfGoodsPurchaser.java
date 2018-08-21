package com.wangqin.globalshop.logistic.app.bean.order;

import lombok.Builder;
import lombok.Data;

/**
 * 购买人信息
 *
 * @author angus
 * @date 2018/8/20
 */
@Data
@Builder
public class JkfGoodsPurchaser {

    /**
     * 购买人ID
     * 必填：Y
     */
    private String id;

    /**
     * 姓名
     * 必填：Y
     */
    private String name;

    /**
     * 注册邮箱
     * 必填：N
     */
    private String email;

    /**
     * 联系电话
     * 必填：Y
     */
    private String telNumber;

    /**
     * 证件类型代码
     * 必填：Y <br>
     * 01:身份证（试点期间只能是身份证）
     * 02:护照
     * 03:其他
     */
    private String paperType;

    /**
     * 证件号码
     * 必填：Y
     */
    private String paperNumber;

    /**
     * 地址
     * 必填：N
     */
    private String address;
}
