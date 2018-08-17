package com.wangqin.globalshop.pay.service.impl;

import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.pay.constant.PayChannelEnum;
import com.wangqin.globalshop.pay.dto.*;
import com.wangqin.globalshop.pay.service.PayService;
import com.wangqin.globalshop.pay.service.ShengpayService;
import com.wangqin.globalshop.pay.util.ShengpayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author angus
 * @date 2018/8/13
 */
@Service
public class PayServiceImpl implements PayService {
    private Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    private static ShengpayService shengpayService = ShengpayService.newInstance();

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");


    @Override
    public void orderPay(String merchantOrderNo, String amount, String productName,
                         String userIp, Exts exts) {
        // 创建支付订单时间
        Date currentTime = new Date();
        // 假定 5 分钟后为过期时间
        Date expireTime = new Date(currentTime.getTime() + 5 * 60 * 1000);
        // 构建支付订单请求参数
        OrderPayRequest orderPayRequest = OrderPayRequest.builder()
                .merchantNo(ShengpayService.MERCHANT_NO)
                .charset(ShengpayService.CHARSET)
                .requestTime(sdf.format(currentTime))
                .merchantOrderNo(merchantOrderNo)
                .amount(amount)
                .expireTime(sdf.format(expireTime))
                .notifyUrl(ShengpayService.PAY_NOTIFY_URL)
                .productName(productName)
                .currency(ShengpayService.CURRENCY)
                .userIp(userIp)
                .pageUrl(ShengpayService.PAY_PAGE_URL)
                // 默认微信渠道
                .payChannel(PayChannelEnum.WP.getCode())
                .openid(ShengpayService.OPEN_ID)
                .exts(exts)
                .build();
        // 获取 MD5 加密摘要
        String signMsg = ShengpayUtil.getSignMsgFromReq(orderPayRequest);
        // 封装请求（Http 请求由 Retrofit2 提供支持）
        Call<OrderPayResponse> call =
                shengpayService.orderPay(ShengpayService.SIGN_TYPE, signMsg, orderPayRequest);
        try {
            // 执行请求，接收响应
            OrderPayResponse orderPayResponse = call.execute().body();
            logger.debug("orderPayResponse: {}", orderPayResponse);
            // TODO: 根据响应信息进行下一步的逻辑
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void queryPay(String merchantOrderNo, String sftOrderNo, String exts) {
        // 构建单笔查询参数
        QueryPayRequest queryPayRequest = QueryPayRequest.builder()
                .merchantNo(ShengpayService.MERCHANT_NO)
                .charset(ShengpayService.CHARSET)
                .requestTime(sdf.format(new Date()))
                .merchantOrderNo(merchantOrderNo)
                .sftOrderNo(sftOrderNo)
                .exts(exts)
                .build();

        String signMsg = ShengpayUtil.getSignMsgFromReq(queryPayRequest);
        Call<QueryPayResponse> call =
                shengpayService.queryPay(ShengpayService.SIGN_TYPE, signMsg, queryPayRequest);

        try {
            QueryPayResponse queryPayResponse = call.execute().body();
            logger.debug("queryPayResponse: {}", queryPayResponse);
            // TODO: 根据响应信息进行下一步的逻辑
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void refundPay(String merchantOrderNo, String refundOrderNo, String refundAmount, String exts) {
        // 构建退款参数
        RefundPayRequest refundPayRequest = RefundPayRequest.builder()
                .merchantNo(ShengpayService.MERCHANT_NO)
                .charset(ShengpayService.CHARSET)
                .requestTime(sdf.format(new Date()))
                .refundOrderNo(refundOrderNo)
                .merchantOrderNo(merchantOrderNo)
                .refundAmount(refundAmount)
                .notifyURL(ShengpayService.REFUND_NOTIFY_URL)
                .exts(exts)
                .build();

        String signMsg = ShengpayUtil.getSignMsgFromReq(refundPayRequest);
        Call<RefundPayResponse> call =
                shengpayService.refundPay(ShengpayService.SIGN_TYPE, signMsg, refundPayRequest);

        try {
            RefundPayResponse refundPayResponse = call.execute().body();
            logger.debug("refundPayResponse: {}", refundPayResponse);
            // TODO: 根据响应信息进行下一步的逻辑
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void queryRefund(String merchantOrderNo, String refundOrderNo, String refundTransNo,
                            String sftOrderNo, String exts) {
        // 构建退款查询参数
        QueryRefundRequest queryPayRequest = QueryRefundRequest.builder()
                .merchantNo(ShengpayService.MERCHANT_NO)
                .charset(ShengpayService.CHARSET)
                .requestTime(sdf.format(new Date()))
                .refundOrderNo(refundOrderNo)
                .merchantOrderNo(merchantOrderNo)
                .refundTransNo(refundTransNo)
                .sftOrderNo(sftOrderNo)
                .exts(exts)
                .build();

        String signMsg = ShengpayUtil.getSignMsgFromReq(queryPayRequest);
        Call<QueryRefundResponse> call =
                shengpayService.queryRefund(ShengpayService.SIGN_TYPE, signMsg, queryPayRequest);

        try {
            QueryRefundResponse queryRefundResponse = call.execute().body();
            logger.debug("queryRefundResponse:{}", queryRefundResponse);
            // TODO: 根据响应信息进行下一步的逻辑
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void sharingPay(String merchantOrderNo, String sharingOrderNo,
                           List<SharingReqItem> sharingReqItemList, String exts) {
        // 构建分账请求参数
        SharingPayRequest sharingPayRequest = SharingPayRequest.builder()
                .merchantNo(ShengpayService.MERCHANT_NO)
                .charset(ShengpayService.CHARSET)
                .requestTime(sdf.format(new Date()))
                .sharingOrderNo(sharingOrderNo)
                .merchantOrderNo(merchantOrderNo)
                .notifyURL(ShengpayService.SHARYING_NOTIFY_URL)
                .sharingReqItem(sharingReqItemList)
                .exts(exts)
                .build();

        String signMsg = ShengpayUtil.getSignMsgFromReq(sharingPayRequest);
        Call<SharingPayResponse> call =
                shengpayService.sharingPay(ShengpayService.SIGN_TYPE, signMsg, sharingPayRequest);

        try {
            SharingPayResponse sharingPayResponse = call.execute().body();
            logger.debug("sharingPayResponse: {}", sharingPayResponse);
            // TODO: 根据响应信息进行下一步的逻辑
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void querySharing(String merchantOrderNo, String sharingQueryOrderNo, String sharingType) {
        // 构建分账查询参数
        QuerySharingRequest querySharingRequest = QuerySharingRequest.builder()
                .merchantNo(ShengpayService.MERCHANT_NO)
                .charset(ShengpayService.CHARSET)
                .requestTime(sdf.format(new Date()))
                .sharingQueryOrderNo(sharingQueryOrderNo)
                .paymentOrderNo(merchantOrderNo)
                .sharingType(sharingType)
                .build();

        String signMsg = ShengpayUtil.getSignMsgFromReq(querySharingRequest);
        Call<QuerySharingResponse> call =
                shengpayService.querySharing(ShengpayService.SIGN_TYPE, signMsg, querySharingRequest);

        try {
            QuerySharingResponse querySharingResponse = call.execute().body();
            logger.debug("querySharingResponse: {}", querySharingResponse);
            // TODO: 根据响应信息进行下一步的逻辑
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void payNotify(HttpServletRequest request) {
        // 获取请求参数
        String queryStr = request.getQueryString();
        System.out.println("queryStr: " + queryStr);

        /**
         * TODO: 进行签名校验，盛付通要求签名顺序
         * Name + Version + Charset + TraceNo + MsgSender + SendTime + InstCode
         * OrderNo + OrderAmount + TransNo + TransAmount + TransStatus
         * TransType + TransTime + MerchantNo + ErrorCode + ErrorMsg
         * Ext1 + Ext2 + SignType + merchantKey
         */
        String serverSign = request.getParameter("SignMsg");
        System.out.println("serverSign: " + serverSign);
        String clientSign = ShengpayUtil.getSignMsgFromRes(queryStr);
        System.out.println("clientSign: " + clientSign);
        System.out.println(clientSign.equals(serverSign));

        // 校验通过后将参数封装到对象中，方便处理
        PayNotifyParam payNotifyParam = PayNotifyParam.builder()
                .name(request.getParameter("Name"))
                .version(request.getParameter("Version"))
                .charset(request.getParameter("Charset"))
                .traceNo(request.getParameter("TraceNo"))
                .msgSender(request.getParameter("MsgSender"))
                .sendTime(request.getParameter("SendTime"))
                .instCode(request.getParameter("InstCode"))
                .orderNo(request.getParameter("OrderNo"))
                .orderAmount(request.getParameter("OrderAmount"))
                .transNo(request.getParameter("TransNo"))
                .transAmount(request.getParameter("TransAmount"))
                .transStatus(request.getParameter("TransStatus"))
                .transType(request.getParameter("TransType"))
                .transTime(request.getParameter("TransTime"))
                .merchantNo(request.getParameter("MerchantNo"))
                .errorCode(request.getParameter("ErrorCode"))
                .errorMsg(request.getParameter("ErrorMsg"))
                .ext1(request.getParameter("Ext1"))
                .ext2(request.getParameter("Ext2"))
                .signType(request.getParameter("SignType"))
                .signMsg(request.getParameter("SignMsg"))
                .build();

        // 根据商户订单号 OrderNo，查询出本地订单 ,商户自行实现代码...

        // 校验商户号是否一致  MerchantNo 商户自行实现代码...

        // 校验订单金额是否一致，此项校验是必须的，防止数据被篡改,商户自行实现代码...
        // localAmount=本地订单记录的金额
        // if(!localAmount.equals(ret.get("TransAmount"))) return  ReturnCode.FAIL.toString();

        // 校验支付状态是否为成功

        // 调用订单查询接口，判断订单是否支付成功

        // 修改本地校验状态为成功 ......

        // 返回处理成功
    }

    @Override
    public void refundNotify(HttpServletRequest request) {
        // 获取请求参数
        String queryStr = request.getQueryString();
        System.out.println("queryStr: " + queryStr);

        /**
         * TODO: 进行签名校验，盛付通要求签名顺序
         * Name + Version + Charset + TraceNo + MsgSender + SendTime + InstCode
         * OrderNo + OrderAmount + TransNo + TransAmount + TransStatus
         * TransType + TransTime + MerchantNo + ErrorCode + ErrorMsg
         * Ext1 + Ext2 + SignType + merchantKey
         */
        String serverSign = request.getParameter("SignMsg");
        System.out.println("serverSign: " + serverSign);
        String clientSign = ShengpayUtil.getSignMsgFromRes(queryStr);
        System.out.println("clientSign: " + clientSign);
        System.out.println(clientSign.equals(serverSign));

        // 校验通过后将参数封装到对象中，方便处理

        // 判断服务码是否是退款通知

        // 根据退款请求单号，查询出本地的退款记录...... RefundOrderNo，商户自行实现代码...

        // 计算签名

        // 校验签名是否一致

        // 判断本地退款金额是否和通知的一致，RefundAmount，如果不一致返回FAIL，商户自行实现代码...

        // 判断退款状态

        // 调用退款查询接口，判断订单是否支付成功
    }

}
