package com.wangqin.globalshop.pay.service;

import com.wangqin.globalshop.pay.dto.Exts;
import com.wangqin.globalshop.pay.dto.SharingReqItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 个人支付相关 service
 *
 * @author angus
 * @date 2018/8/13
 */
public interface PayService {

    /**
     * 创建支付订单
     *
     * @param merchantOrderNo 商户系统内的唯一订单号，商户订单号不能重复
     * @param amount          该笔订单的交易金额，单位默认为RMB-元，精确到小数点后两位，如：23.42
     * @param productName     商品名称(不超过32个字符)
     * @param userIp          用户IP（用户下单时的IP-公网IP,银联交易必填）
     * @param exts            扩展属性,JSON串（微信H5 、微信小程序、微信APP必传，请参考备注）
     */
    void orderPay(String merchantOrderNo, String amount, String productName,
                  String userIp, Exts exts);

    /**
     * 单笔查询
     *
     * @param merchantOrderNo 商户系统内的唯一订单号，商户订单号不能重复
     * @param sftOrderNo      盛付通订单号
     * @param exts            扩展属性,JSON串
     */
    void queryPay(String merchantOrderNo, String sftOrderNo, String exts);

    /**
     * 退款
     *
     * @param merchantOrderNo 原支付订单号
     * @param refundOrderNo   退款请求流水号(商户系统唯一)
     * @param refundAmount    退款金额(与支付金额一致)
     * @param exts            扩展属性,JSON串
     */
    void refundPay(String merchantOrderNo, String refundOrderNo, String refundAmount, String exts);

    /**
     * 退款查询
     *
     * @param merchantOrderNo 原支付订单号
     * @param refundOrderNo   退款请求流水号(商户系统唯一)
     * @param refundTransNo   盛付通退款订单号
     * @param sftOrderNo      盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474
     * @param exts            扩展属性,JSON串
     */
    void queryRefund(String merchantOrderNo, String refundOrderNo, String refundTransNo,
                     String sftOrderNo, String exts);

    /**
     * 分账请求
     *
     * @param merchantOrderNo    商户订单号
     * @param sharingOrderNo     分账请求订单号
     * @param sharingReqItemList 分账信息子项集合
     * @param exts               扩展属性,JSON串
     */
    void sharingPay(String merchantOrderNo, String sharingOrderNo,
                    List<SharingReqItem> sharingReqItemList, String exts);

    /**
     * 分账查询
     *
     * @param merchantOrderNo     商户订单号
     * @param sharingQueryOrderNo 分账查询请求订单号
     * @param sharingType         分账类型（MERCHANT_SHARING：查询分账信息，MERCHANT_SHARING_REFUND：查询分账退款信息）
     */
    void querySharing(String merchantOrderNo, String sharingQueryOrderNo, String sharingType);

    /**
     * 处理支付通知
     *
     * @param request
     */
    void payNotify(HttpServletRequest request);

    /**
     * 处理退款通知
     *
     * @param request
     */
    void refundNotify(HttpServletRequest request);
}
