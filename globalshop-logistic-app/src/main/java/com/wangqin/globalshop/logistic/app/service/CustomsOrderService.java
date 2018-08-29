package com.wangqin.globalshop.logistic.app.service;

import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import com.wangqin.globalshop.logistic.app.bean.order.JkfGoodsPurchaser;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderDetail;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderImportHead;
import com.wangqin.globalshop.logistic.app.bean.order.OrderInfo;
import com.wangqin.globalshop.logistic.app.bean.xml.Body;
import com.wangqin.globalshop.logistic.app.bean.xml.Head;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import com.wangqin.globalshop.logistic.app.constant.BusinessType;
import com.wangqin.globalshop.logistic.app.constant.CustomsConst;

import java.util.ArrayList;
import java.util.List;


/**
 * 电商平台发送商品订单数据到通关服务平台
 *
 * @author angus
 * @date 2018/8/24
 */
public class CustomsOrderService {

//    private

    /**
     * 电商平台发送商品订单数据到通关服务平台
     */
    public void sendOrderMessage(String orderNo) {

        // TODO: 根据 orderNo 获取订单信息

        String businessNo = "";
        JkfSign jkfSign = buildJkfSign(businessNo, null);

    }

    /**
     * 构建签名信息
     *
     * @param businessNo 业务编码
     * @param note       备注
     * @return
     */
    private JkfSign buildJkfSign(String businessNo, String note) {

        return JkfSign.builder()
                .companyCode(CustomsConst.COMPANY_CODE)
                .businessNo(businessNo)
                .businessType(BusinessType.IMPORTORDER)
                .declareType('1')
                .cebFlag("03")
                .note(note)
                .build();
    }

    /**
     * 构建订单表头信息
     *
     * @return
     */
    private JkfOrderImportHead buildJkfOrderImportHead(String payNumber, Double orderGoodsAmount, String orderNo,
                                                       Double orderTaxAmount, String tradeTime, String currCode,
                                                       String consigneeTel, String consignee, String consigneeAddress,
                                                       Integer totalCount, String senderCountry, String senderName,
                                                       String purchaserId) {

        double orderTotalAmount = orderGoodsAmount + orderTaxAmount;

        JkfOrderImportHead jkfOrderImportHead = JkfOrderImportHead.builder()
                .companyName(CustomsConst.COMPANY_NAME)
                .companyCode(CustomsConst.COMPANY_CODE)
                .ieFlag('I')
                .payType("03")
                .payCompanyCode(CustomsConst.PAY_COMPANY_CODE)
                .payCompanyName(CustomsConst.PAY_COMPANY_NAME)
                .payNumber(payNumber)
                .orderTotalAmount(orderTotalAmount)
                .orderGoodsAmount(orderGoodsAmount)
                .discount(0D)
                .orderNo(orderNo)
                .orderTaxAmount(orderTaxAmount)
                .feeAmount(0D)
                .insureAmount(0D)
                .eCommerceCode(CustomsConst.E_COMMERCE_CODE)
                .eCommerceName(CustomsConst.E_COMMERCE_NAME)
                .tradeTime(tradeTime)
                .currCode(currCode)
                .totalAmount(orderGoodsAmount)
                .consigneeEmail(null)
                .consigneeTel(consigneeTel)
                .consignee(consignee)
                .consigneeAddress(consigneeAddress)
                .totalCount(totalCount)
                .batchNumbers(null)
                .consigneeDitrict(null)
                .postMode(null)
                .senderCountry(senderCountry)
                .senderName(senderName)
                .purchaserId(purchaserId)
                .logisCompanyName(CustomsConst.LOGIS_COMPANY_NAME)
                .logisCompanyCode(CustomsConst.LOGIS_COMPANY_CODE)
                .zipCode(null)
                .note(null)
                .wayBills(null)
                .rate(null)
                .userProcotol("本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，" +
                        "本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，" +
                        "无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他" +
                        "监管部门的监管，并承担相应法律责任。")
                .build();

        return jkfOrderImportHead;
    }

    /**
     * 构建订单表体明细
     *
     * @return
     */
    private List<JkfOrderDetail> buildJkfOrderDetailList() {

        int goodsOrder = 1;

        JkfOrderDetail jkfOrderDetail1 = JkfOrderDetail.builder()
                .goodsOrder(1)
                .goodsName("物品名称1")
                .codeTs("0100000001")
                .goodsModel(null)
                .originCountry("00342")
                .unitPrice(3.3D)
                .currency("142")
                .goodsCount(343.0D)
                .goodsUnit("035")
                .grossWeight(null)
                .barCode(null)
                .note(null)
                .build();

        JkfOrderDetail jkfOrderDetail2 = JkfOrderDetail.builder()
                .goodsOrder(2)
                .goodsName("物品名称2")
                .goodsModel("规格型号2")
                .codeTs("0100000002")
                .grossWeight(54.94D)
                .unitPrice(3.3D)
                .goodsUnit("035")
                .goodsCount(343.0D)
                .originCountry("00342")
                .barCode("66655554433")
                .currency("142")
                .note(null)
                .build();
        // 订单表体
        List<JkfOrderDetail> jkfOrderDetailList = new ArrayList<>();
        jkfOrderDetailList.add(jkfOrderDetail1);
        jkfOrderDetailList.add(jkfOrderDetail2);

        return jkfOrderDetailList;
    }

    /**
     * 构建订单购买人信息
     *
     * @return
     */
    private JkfGoodsPurchaser buildJkfGoodsPurchaser(String id, String name, String telNumber, String paperNumber) {

        JkfGoodsPurchaser jkfGoodsPurchaser = JkfGoodsPurchaser.builder()
                .id(id)
                .name(name)
                .email(null)
                .telNumber(telNumber)
                .paperType("01")
                .paperNumber(paperNumber)
                .address(null)
                .build();

        return jkfGoodsPurchaser;
    }


    /**
     * 构建订单报文对应的 Mo
     *
     * @param jkfSign
     * @param jkfOrderImportHead
     * @param jkfOrderDetailList
     * @param jkfGoodsPurchaser
     * @return
     */
    private Mo buildOrderMo(JkfSign jkfSign,
                            JkfOrderImportHead jkfOrderImportHead,
                            List<JkfOrderDetail> jkfOrderDetailList,
                            JkfGoodsPurchaser jkfGoodsPurchaser) {

        double totalAmount = jkfOrderDetailList.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getGoodsCount())
                .sum();

        jkfOrderImportHead.setOrderTotalAmount(totalAmount);

        OrderInfo orderInfo = OrderInfo.builder()
                .jkfSign(jkfSign)
                .jkfOrderImportHead(jkfOrderImportHead)
                .jkfOrderDetailList(jkfOrderDetailList)
                .jkfGoodsPurchaser(jkfGoodsPurchaser)
                .build();
        List<OrderInfo> orderInfoList = new ArrayList<>();
        orderInfoList.add(orderInfo);

        Head head = Head.builder()
                .businessType(BusinessType.IMPORTORDER)
                .build();

        Body body = Body.builder()
                .orderInfoList(orderInfoList)
                .build();

        return Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();
    }
}
