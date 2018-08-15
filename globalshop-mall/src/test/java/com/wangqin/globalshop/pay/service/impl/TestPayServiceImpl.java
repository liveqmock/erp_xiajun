package com.wangqin.globalshop.pay.service.impl;

import com.wangqin.globalshop.pay.service.PayService;
import org.junit.Test;

/**
 * @author angus
 * @date 2018/8/14
 */
public class TestPayServiceImpl {

    private PayService payService = new PayServiceImpl();

    @Test
    public void testOrderPay() {
        payService.orderPay(
                "12",
                "2",
                "测试商品名字",
                "1.1.1.12313",
                "wp",
                null);
    }

    @Test
    public void testQueryPay() {
        payService.queryPay("f4511ec61ac04dc5b4bc766a39b7f5aa",
                null,
                null);
    }

    @Test
    public void testRefundPay() {
        payService.refundPay("2226efd8a932442c868f70da1d391b87",
                "2226efd8a932442c868f70da1d391b87",
                "3.00",
                null);
    }

    @Test
    public void testQueryRefund() {
        payService.queryRefund("f2e03275ee25488f99e58630dfb02552",
                "4c3f4341ee094044bcf77825b5ab4d59",
                null,
                null,
                null);
    }
}
