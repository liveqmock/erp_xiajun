package com.wangqin.globalshop.pay.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 盛付通返回结果和通知参数
 *
 * @author angus
 * @date 2018/8/15
 */
@Data
@Builder
public class PayNotifyParam {
    /**
     * 版本名称 Name <br>
     * 版本名称,通知属性值为:REP_B2CPAYMENT
     */
    private String name;

    /**
     * 版本号 Version <br>
     * 版本号,默认属性值为: V4.1.2.1.1
     */
    private String version;

    /**
     * 字符集 Charset <br>
     * 商户提交请求对应的字符集编码
     */
    private String charset;

    /**
     * 请求序列号 TraceNo <br>
     * 商户生成的报文唯一消息标识
     */
    private String traceNo;

    /**
     * 发送方标识 MsgSender <br>
     * 由盛付通指定,默认为:SFT,用于请求方判别消息通知方的身份
     */
    private String msgSender;

    /**
     * 发送支付请求时间 SendTime <br>
     * 商户提交订单申请支付的时间,必须为14位正整数数字，且在商户系统保证唯一
     */
    private String sendTime;

    /**
     * 银行编码 InstCode <br>
     * 综合网银编码列表,机构代码列表以逗号分隔,如：InstCode=ICBC,CMB
     */
    private String instCode;

    /**
     * 商户订单号 OrderNo <br>
     * 商户订单号,50个字符内、只允许使用数字、字母,确保在商户系统唯一
     */
    private String orderNo;

    /**
     * 支付金额 OrderAmount <br>
     * 商户提交支付的订单金额,必须大于1,可支持2位小数
     */
    private String orderAmount;

    /**
     * 盛付通交易号 TransNo <br>
     * 商户调用收单接口成功后盛付通返回的交易订单号
     */
    private String transNo;

    /**
     * 盛付通实际支付金额 TransAmount <br>
     * 实际销卡金额，当订单transStatus值为成功时，成功销卡金额以此字段值为准
     */
    private String transAmount;

    /**
     * 支付状态 TransStatus <br>
     * 商户提交订单到盛付通后的处理状态，订单最终结果以此字段值为准
     */
    private String transStatus;

    /**
     * 盛付通交易类型 TransType <br>
     * 支付类型
     */
    private String transType;

    /**
     * 盛付通交易时间 TransTime <br>
     * 商户提交订单申请支付的时间，必须为14位正整数数字，且在商户系统保证唯一
     */
    private String transTime;

    /**
     * 商户号 MerchantNo <br>
     * 盛付通指定的6位数字的商户号
     */
    private String merchantNo;

    /**
     * 错误代码 ErrorCode <br>
     * 处理失败或异常时异常返回编码
     */
    private String errorCode;

    /**
     * 错误消息 ErrorMsg <br>
     * 处理失败或异常时异常返回信息
     */
    private String errorMsg;

    /**
     * 扩展1 Ext1 <br>
     * 英文或中文字符串，支付完成后，按照原样返回给商户
     */
    private String ext1;

    /**
     * 扩展2 Ext2 <br>
     * 英文或中文字符串，支付完成后，按照原样返回给商户
     */
    private String ext2;

    /**
     * 签名类型 SignType <br>
     * 商户提交请求参数加密的方式：MD5
     */
    private String signType;

    /**
     * 签名串 SignMsg <br>
     * 进行加签后的16进制字符串
     */
    private String signMsg;
}
