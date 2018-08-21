package com.wangqin.globalshop.logistic.app;

import com.thoughtworks.xstream.XStream;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import com.wangqin.globalshop.logistic.app.bean.order.*;
import com.wangqin.globalshop.logistic.app.bean.xml.Body;
import com.wangqin.globalshop.logistic.app.bean.xml.Head;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author angus
 * @date 2018/8/21
 */
public class TestXStream {

    @Test
    public void testJkfDemo() {
        JkfSign jkfSign = JkfSign.builder()
                .companyCode("发送方备案编号")
                .businessNo("业务编号")
                .businessType("业务类型")
                .declareType("申报类型")
                .cebFlag("不填或者填写或01表示杭州版报文， 02 表示企业自行生成总署报文， 03委托电子口岸生成总署报文")
                .note("备注")
                .build();

        JkfOrderImportHead jkfOrderImportHead = JkfOrderImportHead.builder()
                .eCommerceCode("电商企业编号")
                .eCommerceName("电商企业名称")
                .ieFlag('I')
                .payType("支付类型")
                .payCompanyCode("支付公司编码")
                .payNumber("支付单号")
                .orderTotalAmount(99.99D)
                .orderNo("订单编号")
                .orderTaxAmount(88.88D)
                .orderGoodsAmount(77.77D)
                .feeAmount(66.66D)
                .insureAmount(55.55D)
                .companyName("企业备案名称")
                .companyCode("企业备案编码")
                .tradeTime("成交时间")
                .currCode("成交币制")
                .totalAmount(44.44D)
                .consigneeEmail("收件人邮箱")
                .consigneeTel("收件人电话")
                .totalCount(33.33D)
                .postMode("发货方式（物流方式）")
                .senderCountry("发件人国别")
                .senderName("发件人名称")
                .purchaserId("购买人ID")
                .logisCompanyName("物流企业备案名称")
                .logisCompanyCode("物流企业备案编号")
                .zipCode("邮编")
                .note("备注信息")
                .wayBills("运单号列表,单号之间分号隔开")
                .rate("汇率，人民币填写1")
                .discount(22.22D)
                .batchNumbers("商品批次号")
                .consigneeDitrict("收货地址行政区划代码")
                .userProcotol("本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任。")
                .build();

        JkfOrderDetail jkfOrderDetail1 = JkfOrderDetail.builder()
                .goodsOrder(1)
                .goodsName("物品名称1")
                .goodsModel("规格型号1")
                .codeTs("hs编码1")
                .grossWeight(100.00D)
                .unitPrice(99.99D)
                .goodsUnit("申报计量单位1")
                .goodsCount(88.88D)
                .originCountry("产销国1")
                .barCode("条形码1")
                .currency("币制1")
                .note("备注1")
                .build();
        JkfOrderDetail jkfOrderDetail2 = JkfOrderDetail.builder()
                .goodsOrder(2)
                .goodsName("物品名称2")
                .goodsModel("规格型号2")
                .codeTs("hs编码2")
                .grossWeight(100.00D)
                .unitPrice(99.99D)
                .goodsUnit("申报计量单位2")
                .goodsCount(88.88D)
                .originCountry("产销国2")
                .barCode("条形码2")
                .currency("币制2")
                .note("备注2")
                .build();
        List<JkfOrderDetail> jkfOrderDetailList = new ArrayList<>();
        jkfOrderDetailList.add(jkfOrderDetail1);
        jkfOrderDetailList.add(jkfOrderDetail2);

        JkfGoodsPurchaser jkfGoodsPurchaser = JkfGoodsPurchaser.builder()
                .id("购买人ID")
                .name("购买人名称")
                .email("购买人邮箱")
                .telNumber("联系电话")
                .address("地址")
                .paperType("证件类型")
                .paperNumber("证件号码")
                .build();

        OrderInfo orderInfo = OrderInfo.builder()
                .jkfSign(jkfSign)
                .jkfOrderImportHead(jkfOrderImportHead)
                .jkfOrderDetailList(jkfOrderDetailList)
                .jkfGoodsPurchaser(jkfGoodsPurchaser)
                .build();

        List<OrderInfo> orderInfoList = new ArrayList<>();
        orderInfoList.add(orderInfo);

        Head head = Head.builder()
                .businessType("IMPORTORDER")
                .build();

        Body body = Body.builder()
                .orderInfoList(orderInfoList)
                .build();

        Mo mo = Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();

        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        String xml = xstream.toXML(mo);
        System.out.println(xml);

        System.out.println("mo: " + mo);
        System.out.println("mx: " + xstream.fromXML(xml));
    }

}
