package com.wangqin.globalshop.pay.service.impl;

import com.wangqin.globalshop.pay.dto.SharingReqItem;
import com.wangqin.globalshop.pay.service.PayService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author angus
 * @date 2018/8/14
 */
public class TestPayServiceImpl {

    private PayService payService = new PayServiceImpl();

    @Test
    public void testOrderPay() {
        payService.orderPay(
                "f4511ec61ac04dc5b4bc766a39b7f5aa",
                "2",
                "测试商品名字",
                "1.1.1.123",
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

    @Test
    public void testSharyingPay() {

        List<SharingReqItem> sharingReqItemList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            SharingReqItem amountItem = SharingReqItem.builder()
                    .sharingNo(String.valueOf(i))
                    .sharingAmount("9.9")
                    .payeeId("107537")
                    .payeeIdType("1")
                    .build();
            sharingReqItemList.add(amountItem);

            SharingReqItem rateItem = SharingReqItem.builder()
                    .sharingNo(String.valueOf(i + 5))
                    .sharingRate("0.5")
                    .payeeId("107537")
                    .payeeIdType("1")
                    .build();
            sharingReqItemList.add(rateItem);
        }

        payService.sharingPay(
                "f4511ec61ac04dc5b4bc766a39b7f5aa",
                "f4511ec61ac04dc5b4bc766a39b7f5aa",
                sharingReqItemList,
                null);
    }

    @Test
    public void testQuerySharing() {
        payService.querySharing(
                "09ecfe7e1a074290a50930c5781e28fe",
                "f4511ec61ac04dc5b4bc766a39b7f5aa",
                "MERCHANT_SHARING");
    }
}
