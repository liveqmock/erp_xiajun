package com.wangqin.globalshop.logistic.app.bean.common;

import lombok.Builder;
import lombok.Data;

/**
 * 签名信息
 *
 * @author angus
 * @date 2018/8/20
 */
@Data
@Builder
public class JkfSign {

    /**
     * 发送方备案编号
     * 必填：Y <br>
     * 发送方备案编号,不可随意填写
     */
    private String companyCode;

    /**
     * 业务编号
     * 必填：Y <br>
     * 主要作用是回执给到企业的时候通过这个编号企业能认出对应之前发送的哪个单子
     */
    private String businessNo;

    /**
     * 业务类型
     * 必填：Y <br>
     */
    private String businessType;

    /**
     * 申报类型
     * 必填：Y <br>
     * 企业报送类型。1-新增 2-变更 3-删除。默认为1。
     */
    private String declareType;

    /**
     * 必填：Y <br>
     * 填写或01表示在途在库单证， 02 表示企业采用方案二对接总署版，自行生成加签总署报文，
     * 03表示采用方案一对接，委托平台生成总署报文，回调企业加签服务器加签
     */
    private String cebFlag;

    /**
     * 备注
     * 必填：N <br>
     */
    private String note;
}
