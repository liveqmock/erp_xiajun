package com.wangqin.globalshop.pay.service.impl;

import com.google.gson.Gson;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.pay.dto.*;
import com.wangqin.globalshop.pay.service.PayService;
import com.wangqin.globalshop.pay.service.ShengpayService;
import okhttp3.Headers;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wangqin.globalshop.pay.service.ShengpayService.*;

/**
 * @author angus
 * @date 2018/8/13
 */
@Service
public class PayServiceImpl implements PayService {

    private Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    private static ShengpayService shengpayService = newInstance();

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");


    @Override
    public void orderPay(String merchantOrderNo, String amount, String productName,
                         String userIp, String payChannel, String exts) {
        Date currentTime = new Date();
        // 5 分钟后过期
        Date expireTime = new Date(currentTime.getTime() + 5 * 60 * 1000);

        // 构建支付订单请求参数 VO
        OrderPayRequestVO orderPayRequestVO = OrderPayRequestVO.builder()
                .merchantNo(MERCHANT_NO)
                .charset(CHARSET)
                .requestTime(sdf.format(currentTime))
                .merchantOrderNo(merchantOrderNo)
                .amount(amount)
                .expireTime(sdf.format(expireTime))
                .notifyUrl(PAY_NOTIFY_URL)
                .productName(productName)
                .currency(CURRENCY)
                .userIp(userIp)
                .pageUrl(PAY_PAGE_URL)
                .payChannel(payChannel)
                .openid(OPEN_ID)
                .exts(exts)
                .build();

        // 获取 MD5 加密摘要
        String signMsg = getSignMsg(orderPayRequestVO);
        logger.debug("signMsg: {}", signMsg);

        // 封装请求（Http 请求由 Retrofit2 提供支持）
        Call<OrderPayResponseVO> call = shengpayService.orderPay(SIGN_TYPE, signMsg, orderPayRequestVO);
        logger.debug("call: {}", call.request());

        try {
            // 执行请求，接收响应
            OrderPayResponseVO responseBody = call.execute().body();
            logger.debug("responseBody: {}", responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }


    @Override
    public void queryPay(String merchantOrderNo, String sftOrderNo, String exts) {
        // 构建单笔查询参数 VO
        QueryPayRequestVO queryPayRequestVO = QueryPayRequestVO.builder()
                .merchantNo(MERCHANT_NO)
                .charset(CHARSET)
                .requestTime(sdf.format(new Date()))
                .merchantOrderNo(merchantOrderNo)
                .sftOrderNo(sftOrderNo)
                .exts(exts)
                .build();

        // 获取 MD5 加密摘要
        String signMsg = getSignMsg(queryPayRequestVO);
        logger.debug("signMsg: {}", signMsg);

        // 封装请求（Http 请求由 Retrofit2 提供支持）
        Call<QueryPayResponseVO> call = shengpayService.queryPay(SIGN_TYPE, signMsg, queryPayRequestVO);
        logger.debug("call: {}", call.request());

        try {
            // 执行请求，接收响应
            QueryPayResponseVO responseBody = call.execute().body();
            logger.debug("responseBody: {}", responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void refundPay(String refundOrderNo, String merchantOrderNo, String refundAmount, String exts) {
        // 构建退款参数 VO
        RefundPayRequestVO refundPayRequestVO = RefundPayRequestVO.builder()
                .merchantNo(MERCHANT_NO)
                .charset(CHARSET)
                .requestTime(sdf.format(new Date()))
                .refundOrderNo(refundOrderNo)
                .merchantOrderNo(merchantOrderNo)
                .refundAmount(refundAmount)
                .notifyURL(REFUND_NOTIFY_URL)
                .exts(exts)
                .build();

        // 获取 MD5 加密摘要
        String signMsg = getSignMsg(refundPayRequestVO);
        logger.debug("signMsg: {}", signMsg);

        // 封装请求（Http 请求由 Retrofit2 提供支持）
        Call<RefundPayResponseVO> call = shengpayService.refundPay(SIGN_TYPE, signMsg, refundPayRequestVO);
        logger.debug("call: {}", call.request());

        try {
            // 执行请求，接收响应
            RefundPayResponseVO responseBody = call.execute().body();
            logger.debug("responseBody: {}", responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }
    }

    @Override
    public void queryRefund(String refundOrderNo, String merchantOrderNo, String refundTransNo,
                            String sftOrderNo, String exts) {
        // 构建退款查询参数 VO
        QueryRefundRequestVO queryPayRequestVO = QueryRefundRequestVO.builder()
                .merchantNo(MERCHANT_NO)
                .charset(CHARSET)
                .requestTime(sdf.format(new Date()))
                .refundOrderNo(refundOrderNo)
                .merchantOrderNo(merchantOrderNo)
                .refundTransNo(refundTransNo)
                .sftOrderNo(sftOrderNo)
                .exts(exts)
                .build();

        // 获取 MD5 加密摘要
        String signMsg = getSignMsg(queryPayRequestVO);
        logger.debug("signMsg: {}", signMsg);

        // 封装请求（Http 请求由 Retrofit2 提供支持）
        Call<QueryRefundResponseVO> call = shengpayService.queryRefund(SIGN_TYPE, signMsg, queryPayRequestVO);
        logger.debug("call: {}", call.request());

        try {
            // 执行请求，接收响应
            Response<QueryRefundResponseVO> response = call.execute();
            Headers headers = response.headers();
            logger.debug("headers:{}", headers);
            QueryRefundResponseVO body = response.body();
            logger.debug("body:{}", body);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizCommonException("未知异常！");
        }

    }

    /**
     * 生成加密信息串 signMsg <br>
     * 计算公式：signMsg = md5(requestBody + md5key).toUpperCase()
     *
     * @param requestBodyObj 待放入请求体的对象
     * @return signMsg
     */
    private String getSignMsg(Object requestBodyObj) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(requestBodyObj);
        return DigestUtils.md5Hex(requestBody + MD5_KEY).toUpperCase();
    }
}
