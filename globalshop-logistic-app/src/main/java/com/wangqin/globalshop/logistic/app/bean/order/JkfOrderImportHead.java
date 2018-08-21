package com.wangqin.globalshop.logistic.app.bean.order;

import lombok.Builder;
import lombok.Data;

/**
 * 订单表头信息
 *
 * @author angus
 * @date 2018/8/20
 */
@Data
@Builder
public class JkfOrderImportHead {

    /**
     * 企业备案名称
     * 必填：Y <br>
     * 电商平台在跨境电商通关服务平台的备案名称
     */
    private String companyName;

    /**
     * 企业备案编号
     * 必填：Y <br>
     * 电商平台在跨境电商通关服务的备案编号
     */
    private String companyCode;

    /**
     * 进出口标志
     * 必填：Y <br>
     * I:进口
     * E:出口
     */
    private Character ieFlag;

    /**
     * 支付类型
     * 必填：Y <br>
     * 01:银行卡支付
     * 02:余额支付
     * 03:其他
     */
    private String payType;

    /**
     * 支付公司编码
     * 必填：Y <br>
     * 支付平台在跨境平台备案编号
     */
    private String payCompanyCode;

    /**
     * 支付公司名称
     * 必填：N <br>
     * 对接总署版必填；支付平台在跨境平台备案名称
     */
    private String payCompanyName;

    /**
     * 支付单号
     * 必填：Y <br>
     * 支付成功后，支付平台反馈给电商平台的支付单号
     */
    private String payNumber;

    /**
     * 订单总金额
     * 必填：Y <br>
     * 货款+订单税款+运费+保费
     */
    private Double orderTotalAmount;

    /**
     * 订单货款
     * 必填：Y <br>
     * 与成交总价一致，按以电子订单的实际销售价格作为完税价格
     */
    private Double orderGoodsAmount;

    /**
     * 非现金抵扣金额
     * 必填：Y <br>
     * 使用积分、虚拟货币、代金券等非现金支付金额，无则填写"0"
     */
    private Double discount;

    /**
     * 订单编号
     * 必填：Y <br>
     * 电商平台订单号，
     * 注意：一个订单只能对应一个运单(包裹)
     */
    private String orderNo;

    /**
     * 订单税款
     * 必填：Y <br>
     * 交易过程中商家向用户征收的税款，按缴税新政计算填写
     */
    private Double orderTaxAmount;

    /**
     * 运费
     * 必填：N <br>
     * 交易过程中商家向用户征收的运费，免邮模式填写0
     */
    private Double feeAmount;

    /**
     * 保费
     * 必填：Y <br>
     * 商家向用户征收的保价费用，无保费可填写0
     */
    private Double insureAmount;

    /**
     * 电商企业编码
     * 必填：Y <br>
     * 电商平台下的商家备案编号
     */
    private String eCommerceCode;

    /**
     * 电商企业名称
     * 必填：Y <br>
     * 电商平台下的商家备案名称
     */
    private String eCommerceName;

    /**
     * 成交时间
     * 必填：Y <br>
     * 格式：2014-02-18 15:58:11
     */
    private String tradeTime;

    /**
     * 成交币制
     * 必填：Y <br>
     * 见参数表
     */
    private String currCode;

    /**
     * 成交总价
     * 必填：Y <br>
     * 与订单货款一致
     */
    private Double totalAmount;

    /**
     * 收件人Email
     * 必填：N
     */
    private String consigneeEmail;

    /**
     * 收件人联系方式
     * 必填：Y
     */
    private String consigneeTel;

    /**
     * 收件人姓名
     * 必填：Y
     */
    private String consignee;

    /**
     * 收件人地址
     * 必填：Y
     */
    private String consigneeAddress;

    /**
     * 总件数
     * 必填：Y <br>
     * 包裹中独立包装的物品总数，不考虑物品计量单位
     */
    private Double totalCount;

    /**
     * 商品批次号
     * 必填：N
     */
    private String batchNumbers;

    /**
     * 收货地址行政区划代码
     * 必填：N <br>
     * 参照国家统计局公布的国家行政区划标准填制
     */
    private String consigneeDitrict;

    /**
     * 发货方式（物流方式）
     * 必填：N <br>
     * 见参数表
     */
    private String postMode;

    /**
     * 发件人国别
     * 必填：Y <br>
     * 见参数表
     */
    private String senderCountry;

    /**
     * 发件人姓名
     * 必填：Y
     */
    private String senderName;

    /**
     * 购买人ID
     * 必填：Y <br>
     * 购买人在电商平台的注册ID
     */
    private String purchaserId;

    /**
     * 物流企业名称
     * 必填：Y
     */
    private String logisCompanyName;

    /**
     * 物流企业编号
     * 必填：Y
     */
    private String logisCompanyCode;

    /**
     * 邮编
     * 必填：N
     */
    private String zipCode;

    /**
     * 备注
     * 必填：N
     */
    private String note;

    /**
     * 运单号列表
     * 必填：N <br>
     * 运单之间以分号隔开
     */
    private String wayBills;

    /**
     * 汇率
     * 必填：N <br>
     * 如果是人民币，统一填写1
     */
    private String rate;

    /**
     * 个人委托申报协议
     * 必填：Y <br>
     * 填写个人在电商平台上所同意的委托协议。文本可参考：
     * <p>
     *     本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，
     *     本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息
     *     真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、
     *     检验检疫机构及其他监管部门的监管，并承担相应法律责任.
     * </p>
     */
    private String userProcotol;
}
