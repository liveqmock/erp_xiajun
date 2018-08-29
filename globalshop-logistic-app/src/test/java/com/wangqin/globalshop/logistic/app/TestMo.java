package com.wangqin.globalshop.logistic.app;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.wangqin.globalshop.logistic.app.bean.bill.BillResult;
import com.wangqin.globalshop.logistic.app.bean.cancel.ModifyCancel;
import com.wangqin.globalshop.logistic.app.bean.cancel.ModifyCancelResult;
import com.wangqin.globalshop.logistic.app.bean.ceb.*;
import com.wangqin.globalshop.logistic.app.constant.BusinessType;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclare;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclareDetail;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclareModule;
import com.wangqin.globalshop.logistic.app.bean.order.JkfGoodsPurchaser;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderDetail;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderImportHead;
import com.wangqin.globalshop.logistic.app.bean.order.OrderInfo;
import com.wangqin.globalshop.logistic.app.bean.result.JkfResult;
import com.wangqin.globalshop.logistic.app.bean.result.JkfResultDetail;
import com.wangqin.globalshop.logistic.app.bean.returns.CustomCheckResult;
import com.wangqin.globalshop.logistic.app.bean.returns.GoodsReturn;
import com.wangqin.globalshop.logistic.app.bean.returns.GoodsReturnDetail;
import com.wangqin.globalshop.logistic.app.bean.returns.GoodsReturnModule;
import com.wangqin.globalshop.logistic.app.bean.xml.Body;
import com.wangqin.globalshop.logistic.app.bean.xml.Head;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import com.wangqin.globalshop.logistic.app.util.XStreamUtil;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 拼接报文测试类
 *
 * @author angus
 * @date 2018/8/21
 */
public class TestMo {

    /**
     * 订单样例
     */
    @Test
    public void testOrderInfo() {
        JkfSign jkfSign = JkfSign.builder()
                .companyCode("3301968800")
                .businessNo("order001")
                .businessType(BusinessType.IMPORTORDER)
                .declareType('1')
                .cebFlag("01")
                .note("进口订单备注")
                .build();

        JkfOrderImportHead jkfOrderImportHead = JkfOrderImportHead.builder()
                .eCommerceCode("33019688oo")
                .eCommerceName("亚马逊")
                .ieFlag('I')
                .payType("支付类型")
                .payCompanyCode("3301968pay")
                .payNumber("zhifu001")
                .orderTotalAmount(100.0D)
                .orderNo("order00001")
                .orderTaxAmount(10.0D)
                .orderGoodsAmount(90.0D)
                .feeAmount(5345.0D)
                .insureAmount(1.5D)
                .companyName("天猫国际")
                .companyCode("3301968833")
                .tradeTime("2014-02-17 15:23:13")
                .currCode("502")
                .totalAmount(100.0D)
                .consigneeEmail("loujh@sina.com")
                .consigneeTel("13242345433")
                .consignee("loujianhua")
                .consigneeAddress("浙江杭州聚龙大厦")
                .totalCount(5)
                .postMode("1")
                .senderCountry("34233")
                .senderName("yangtest")
                .purchaserId("中国买家a")
                .logisCompanyName("邮政速递")
                .logisCompanyCode("3301980101")
                .zipCode("322001")
                .note("备注信息")
                .wayBills("001;002;003")
                .rate("6.34")
                .discount(0D)
                .batchNumbers("3434-343434")
                .consigneeDitrict("632043")
                .userProcotol("本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任。")
                .build();

        JkfOrderDetail jkfOrderDetail1 = JkfOrderDetail.builder()
                .goodsOrder(1)
                .goodsName("物品名称1")
                .goodsModel("规格型号1")
                .codeTs("0100000001")
                .grossWeight(34.94D)
                .unitPrice(3.3D)
                .goodsUnit("035")
                .goodsCount(343.0D)
                .originCountry("00342")
                .barCode("66655554433")
                .currency("142")
                .note("备注")
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
                .note("备注")
                .build();


        List<JkfOrderDetail> jkfOrderDetailList = new ArrayList<>();
        jkfOrderDetailList.add(jkfOrderDetail1);
        jkfOrderDetailList.add(jkfOrderDetail2);

        JkfGoodsPurchaser jkfGoodsPurchaser = JkfGoodsPurchaser.builder()
                .id("中国买家a")
                .name("tetsname")
                .email("tetsname@sina.com")
                .telNumber("24233322323")
                .address("浙江杭州杭大路9999号")
                .paperType("01")
                .paperNumber("23458-9503285382434")
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
                .businessType(BusinessType.IMPORTORDER)
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

        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
        System.out.println(xmlHeader + xml);
        System.out.println("mo: " + mo);
        System.out.println("mx: " + xstream.fromXML(xmlHeader + xml));
    }

    /**
     * 清单样例
     */
    @Test
    public void testGoodsDeclare() {
        Head head = Head.builder()
                .businessType(BusinessType.PERSONAL_GOODS_DECLAR)
                .build();

        JkfSign jkfSign = JkfSign.builder()
                .companyCode("发送方备案编号")
                .businessNo("业务编码")
                .businessType("业务类型")
                .declareType('1')
                .cebFlag("不填或者填写或01表示杭州版报文， 02 表示企业自行生成总署报文， 03委托电子口岸生成总署报文")
                .note("备注")
                .build();

        GoodsDeclare goodsDeclare = GoodsDeclare.builder()
                .accountBookNo("账册编号")
                .ieFlag('I')
                .preEntryNumber("预录入号码")
                .importType('1')
                .inOutDateStr("进出口日期")
                .iePort("进出口岸代码")
                .destinationPort("指运港(抵运港)")
                .trafName("运输工具名称")
                .voyageNo("运输工具航次(班)号")
                .trafMode("运输方式代码")
                .declareCompanyType("申报单位类别")
                .declareCompanyCode("申报单位代码")
                .declareCompanyName("申报单位名称")
                .eCommerceCode("电商企业代码")
                .eCommerceName("电商企业名称")
                .orderNo("订单编号")
                .wayBill("分运单号")
                .tradeCountry("贸易国别（起运地）")
                .packNo(100)
                .grossWeight(99D)
                .netWeight(88D)
                .warpType("包装种类")
                .remark("备注")
                .declPort("申报口岸代码")
                .enteringPerson("录入人")
                .enteringCompanyName("录入单位名称")
                .declarantNo("报关员代码")
                .customsField("码头/货场代码")
                .senderName("发件人")
                .consignee("收件人")
                .senderCountry("发件人国别")
                .senderCity("发件人城市")
                .paperType('1')
                .paperNumber("购买人证件号")
                .buyerName("购买人姓名")
                .worth(77D)
                .currCode("币制")
                .mainGName("主要货物名称")
                .internalAreaCompanyNo("区内企业编码")
                .internalAreaCompanyName("区内企业名称")
                .applicationFormNo("申请单编号")
                .isAuthorize('1')
                .companyName("天猫国际")
                .companyCode("9343343343")
                .logisCompanyName("邮政速递")
                .logisCompanyCode("3300232323")
                .feeAmount(5345.0D)
                .insureAmount(4.3343D)
                .consigneeAddress("浙江杭州聚龙大厦")
                .purchaserTelNumber("13443224322")
                .assureCode("")
                .licenseNo("")
                .trafNo("333343")
                .billNo("33343")
                .build();

        GoodsDeclareDetail goodsDeclareDetail = GoodsDeclareDetail.builder()
                .goodsOrder(1)
                .codeTs("行邮税号")
                .goodsItemNo("商品货号")
                .goodsName("物品名称")
                .itemName("商品品名")
                .goodsModel("商品规格、型号")
                .originCountry("产销国")
                .tradeCurr("成交币制")
                .tradeTotal(9999.99D)
                .declPrice(8888.99D)
                .declTotalPrice(7777.77D)
                .useTo("用途")
                .declareCount(6666)
                .goodsUnit("申报计量单位")
                .goodsGrossWeight(5555.55D)
                .firstUnit("第一单位")
                .firstCount(4444)
                .secondUnit("第二单位")
                .secondCount(3333)
                .productRecordNo("产品国检备案编号")
                .webSite("商品网址")
                .barCode("条形码")
                .note("备注")
                .build();


        List<GoodsDeclareDetail> goodsDeclareDetails = new ArrayList<>();
        goodsDeclareDetails.add(goodsDeclareDetail);

        GoodsDeclareModule goodsDeclareModule = GoodsDeclareModule.builder()
                .jkfSign(jkfSign)
                .goodsDeclare(goodsDeclare)
                .goodsDeclareDetails(goodsDeclareDetails)
                .build();

        List<GoodsDeclareModule> goodsDeclareModuleList = new ArrayList<>();
        goodsDeclareModuleList.add(goodsDeclareModule);

        Body body = Body.builder()
                .goodsDeclareModuleList(goodsDeclareModuleList)
                .build();

        Mo mo = Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();

        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        String xml = xstream.toXML(mo);
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
        System.out.println(xmlHeader + xml);
        System.out.println("mo: " + mo);
        System.out.println("mx: " + xstream.fromXML(xmlHeader + xml));
    }

    /**
     * 清单审批回执样例
     */
    @Test
    public void testJkfGoodsFromXml() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<mo version=\"1.0.0\">\n" +
                "  <head> \n" +
                "    <businessType>PERSONAL_GOODS_DECLAR</businessType>\n" +
                "  </head> \n" +
                "  <body> \n" +
                "    <jkfSign> \n" +
                "      <companyCode>PT14021901</companyCode>  \n" +
                "      <businessNo>TMGJ00022910007000</businessNo> \n" +
                "    </jkfSign>  \n" +
                "    <jkfGoodsDeclar> \n" +
                "      <personalGoodsFormNo>299120149000031187</personalGoodsFormNo>  \n" +
                "      <approveResult>99</approveResult>  \n" +
                "      <approveComment></approveComment>  \n" +
                "      <processTime>20140611195846</processTime> \n" +
                "    </jkfGoodsDeclar> \n" +
                "  </body> \n" +
                "</mo>";

        XStream xstream = new XStream();
        xstream.processAnnotations(Mo.class);

        Mo mo = (Mo) xstream.fromXML(xml);
        System.out.println("mo: " + mo);

        String moXml = xstream.toXML(mo);
        System.out.println(moXml);
    }

    /**
     * 回执样例
     */
    @Test
    public void testJkfResult() {
        Head head = Head.builder()
                .businessType(BusinessType.RESULT)
                .build();

        JkfResultDetail jkfResultDetail1 = JkfResultDetail.builder()
                .resultInfo("22001:测试1")
                .build();
        JkfResultDetail jkfResultDetail2 = JkfResultDetail.builder()
                .resultInfo("22002:测试2")
                .build();
        List<JkfResultDetail> resultList = new ArrayList<>();
        resultList.add(jkfResultDetail1);
        resultList.add(jkfResultDetail2);

        JkfResult jkfResult = JkfResult.builder()
                .companyCode("3301961Q43")
                .businessNo("1")
                .businessType("1")
                .declareType('1')
                .chkMark('1')
                .noticeDate("2012-09-03")
                .noticeTime("09:22")
                .note("123")
                .resultList(resultList)
                .build();
        List<JkfResult> jkfResultList = new ArrayList<>();
        jkfResultList.add(jkfResult);

        Body body = Body.builder()
                .list(jkfResultList)
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
    }

    /**
     * 运单回执样例
     */
    @Test
    public void testBillResult() {
        Head head = Head.builder()
                .businessType("IMPORTBILLRESULT")
                .build();

        JkfSign jkfSign = JkfSign.builder()
                .companyCode("企业编码")
                .businessNo("业务编码")
                .build();

        BillResult billResult = BillResult.builder()
                .wayBillNos("分运单号")
                .outState("1")
                .outTime("2014-05-20 22:00:00")
                .build();
        List<BillResult> billResultList = new ArrayList<>();
        billResultList.add(billResult);

        Body body = Body.builder()
                .jkfSign(jkfSign)
                .billResultList(billResultList)
                .build();

        Mo mo = Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();

        toXmlByXStream(mo);
    }

    /**
     * 退单样例
     */
    @Test
    public void testGoodsReturn() {
        Head head = Head.builder()
                .businessType(BusinessType.IMPORT_ORDER_RETURN)
                .build();

        JkfSign jkfSign = JkfSign.builder()
                .companyCode("发送方企业备案编号")
                .businessNo("业务编码")
                .businessType("业务类型")
                .declareType('I')
                .note("备注")
                .build();

        GoodsReturn goodsReturn = GoodsReturn.builder()
                .appCode("退货申报编号")
                .orderNo("原订单编号")
                .wayBillNo("原运单号")
                .eCommerceCode("电商企业编码")
                .eCompanyCode("电商平台编码")
                .internalAreaCompanyNo("仓储企业代码")
                .declareCompanyCode("申报企业编码")
                .returnWayBillNo("退货运单号")
                .declareTimeStr("申报时间")
                .customsField("场地代码")
                .declPort("申报关区")
                .packNo(99D)
                .reason("退货原因")
                .build();

        GoodsReturnDetail goodsReturnDetail = GoodsReturnDetail.builder()
                .goodsOrder(88)
                .codeTs("行邮税号")
                .goodsItemNo("商品货号")
                .goodsName("物品名称")
                .declareCount(77D)
                .build();
        List<GoodsReturnDetail> goodsReturnDetails = new ArrayList<>();
        goodsReturnDetails.add(goodsReturnDetail);

        GoodsReturnModule goodsReturnModule = GoodsReturnModule.builder()
                .jkfSign(jkfSign)
                .goodsReturn(goodsReturn)
                .goodsReturnDetails(goodsReturnDetails)
                .build();
        List<GoodsReturnModule> goodsReturnModuleList = new ArrayList<>();
        goodsReturnModuleList.add(goodsReturnModule);

        Body body = Body.builder()
                .goodsReturnModuleList(goodsReturnModuleList)
                .build();

        Mo mo = Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();

        toXmlByXStream(mo);
    }

    /**
     * 审核回执样例
     */
    @Test
    public void testCustomCheck() {
        Head head = Head.builder()
                .businessType(BusinessType.CHECK_RESULT)
                .build();

        CustomCheckResult customCheckResult = CustomCheckResult.builder()
                .businessNo("业务编号")
                .businessType(BusinessType.IMPORT_ORDER_RETURN)
                .companyCode("发送方企业备案编号")
                .approveResult("审批结果")
                .approveComment("审批意见")
                .processTime("审核时间")
                .build();

        Body body = Body.builder()
                .customCheckResult(customCheckResult)
                .build();

        Mo mo = Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();

        toXmlByXStream(mo);
    }

    /**
     * 删单样例
     */
    @Test
    public void testModifyCance() {
        Head head = Head.builder()
                .businessType(BusinessType.MODIFY_CANCEL)
                .build();

        JkfSign jkfSign = JkfSign.builder()
                .companyCode("接收方企业 备案编号")
                .businessType(BusinessType.MODIFY_CANCEL)
                .declareType('1')
                .businessNo("业务编号")
                .build();

        ModifyCancel modifyCancel = ModifyCancel.builder()
                .eCommerceCode("电商企业备案编号")
                .eCompanyCode("电商平台备案编号")
                .subCarriageNo("分运单号")
                .reason("删单原因")
                .build();
        List<ModifyCancel> modifyCancelList = new ArrayList<>();
        modifyCancelList.add(modifyCancel);

        Body body = Body.builder()
                .jkfSign(jkfSign)
                .modifyCancelList(modifyCancelList)
                .build();

        Mo mo = Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();

        toXmlByXStream(mo);
    }

    /**
     * 删单回执信息样例
     */
    @Test
    public void testModifyCancelResult() {
        Head head = Head.builder()
                .businessType(BusinessType.MODIFY_CANCEL)
                .build();

        ModifyCancelResult modifyCancelResult = ModifyCancelResult.builder()
                .businessNo("业务编号")
                .companyCode("发送方企业备案编号")
                .approveResult("审批结果")
                .approveComment("审批意见")
                .processTime("处理时间")
                .build();

        Body body = Body.builder()
                .modifyCancelResult(modifyCancelResult)
                .build();

        Mo mo = Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();

        toXmlByXStream(mo);
    }

    @Test
    public void testXmlToBean() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<mo version=\"1.0.0\">" +
                "  <head>" +
                "    <businessType>IMPORTORDER</businessType>" +
                "  </head>" +
                "  <body>" +
                "    <orderInfoList>" +
                "      <orderInfo>" +
                "        <jkfSign>" +
                "          <companyCode>3301961Q43</companyCode>" +
                "          <businessNo>order001</businessNo>" +
                "          <businessType>IMPORTORDER</businessType>" +
                "          <declareType>1</declareType>" +
                "          <cebFlag>01</cebFlag>" +
                "          <note>进口订单备注</note>" +
                "        </jkfSign>" +
                "        <jkfOrderImportHead>" +
                "          <companyName>天猫国际</companyName>" +
                "          <companyCode>3301968833</companyCode>" +
                "         <ieFlag>I</ieFlag>" +
                "          <payType>支付类型</payType>" +
                "          <payCompanyCode>3301968pay</payCompanyCode>" +
                "          <payNumber>zhifu001</payNumber>" +
                "          <orderTotalAmount>100.0</orderTotalAmount>" +
                "          <orderGoodsAmount>90.0</orderGoodsAmount>" +
                "          <discount>0.0</discount>" +
                "          <orderNo>order00001</orderNo>" +
                "          <orderTaxAmount>10.0</orderTaxAmount>" +
                "          <feeAmount>5345.0</feeAmount>" +
                "          <insureAmount>1.5</insureAmount>" +
                "          <eCommerceCode>33019688oo</eCommerceCode>" +
                "          <eCommerceName>亚马逊</eCommerceName>" +
                "          <tradeTime>2014-02-17 15:23:13</tradeTime>" +
                "          <currCode>502</currCode>" +
                "          <totalAmount>100.0</totalAmount>" +
                "          <consigneeEmail>loujh@sina.com</consigneeEmail>" +
                "          <consigneeTel>13242345433</consigneeTel>" +
                "          <consignee>loujianhua</consignee>" +
                "          <consigneeAddress>浙江杭州聚龙大厦</consigneeAddress>" +
                "          <totalCount>5</totalCount>" +
                "          <batchNumbers>3434-343434</batchNumbers>" +
                "          <consigneeDitrict>632043</consigneeDitrict>" +
                "          <postMode>1</postMode>" +
                "          <senderCountry>34233</senderCountry>" +
                "          <senderName>yangtest</senderName>" +
                "          <purchaserId>中国买家a</purchaserId>" +
                "          <logisCompanyName>邮政速递</logisCompanyName>" +
                "          <logisCompanyCode>3301980101</logisCompanyCode>" +
                "          <zipCode>322001</zipCode>" +
                "          <note>备注信息</note>" +
                "          <wayBills>001;002;003</wayBills>" +
                "          <rate>6.34</rate>" +
                "          <userProcotol>本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任。</userProcotol>" +
                "        </jkfOrderImportHead>" +
                "        <jkfOrderDetailList>" +
                "          <jkfOrderDetail>" +
                "            <goodsOrder>1</goodsOrder>" +
                "            <goodsName>物品名称1</goodsName>" +
                "            <codeTs>0100000001</codeTs>" +
                "            <goodsModel>规格型号1</goodsModel>" +
                "            <originCountry>00342</originCountry>" +
                "            <unitPrice>3.3</unitPrice>" +
                "            <currency>142</currency>" +
                "            <goodsCount>343.0</goodsCount>" +
                "            <goodsUnit>035</goodsUnit>" +
                "            <grossWeight>34.94</grossWeight>" +
                "            <barCode>66655554433</barCode>" +
                "            <note>备注</note>" +
                "          </jkfOrderDetail>" +
                "          <jkfOrderDetail>" +
                "            <goodsOrder>2</goodsOrder>" +
                "            <goodsName>物品名称2</goodsName>" +
                "            <codeTs>0100000002</codeTs>" +
                "            <goodsModel>规格型号2</goodsModel>" +
                "            <originCountry>00342</originCountry>" +
                "            <unitPrice>3.3</unitPrice>" +
                "            <currency>142</currency>" +
                "            <goodsCount>343.0</goodsCount>" +
                "            <goodsUnit>035</goodsUnit>" +
                "            <grossWeight>54.94</grossWeight>" +
                "            <barCode>66655554433</barCode>" +
                "            <note>备注</note>" +
                "          </jkfOrderDetail>" +
                "        </jkfOrderDetailList>" +
                "        <jkfGoodsPurchaser>" +
                "          <id>中国买家a</id>" +
                "          <name>tetsname</name>" +
                "          <email>tetsname@sina.com</email>" +
                "          <telNumber>24233322323</telNumber>" +
                "          <paperType>01</paperType>" +
                "          <paperNumber>23458-9503285382434</paperNumber>" +
                "          <address>浙江杭州杭大路9999号</address>" +
                "        </jkfGoodsPurchaser>" +
                "      </orderInfo>" +
                "    </orderInfoList>" +
                "  </body>" +
                "</mo>";
        Mo mo = XStreamUtil.toBean(xml, Mo.class);
        System.out.println(mo);
    }

    /**
     * 总署版报文样例
     */
    @Test
    public void testCeb() {
        DeliveryHead deliveryHead = DeliveryHead.builder()
                .guid("5923DGGB-9940-415B-B666-C09BOO7F40B2")
                .appType("1")
                .appTime("20160310163311")
                .appStatus("2")
                .customsCode("3700")
                .copNo("NBBH20160308005")
                .preNo("B20160308000000005")
                .rkdNo("010220160308000005")
                .operatorCode("1101110326")
                .operatorName("监管人")
                .ieFlag("I")
                .trafMode("1")
                .trafNo("0")
                .voyageNo("BJ20160308")
                .billNo("B00024204001")
                .logisticsCode("1105910159")
                .logisticsName("测试物流企业")
                .unloadLocation("")
                .note("")
                .build();

        DeliveryList deliveryList = DeliveryList.builder()
                .gnum("1")
                .logisticsNo("L201603081138001")
                .note("")
                .build();

        Delivery delivery = Delivery.builder()
                .deliveryHead(deliveryHead)
                .deliveryList(deliveryList)
                .build();

        BaseTransfer baseTransfer = BaseTransfer.builder()
                .copCode("1101180326")
                .copName("物流企业")
                .dxpMode("DXP")
                .dxpId("DXPLGS0000000001")
                .note("")
                .build();

        CEB711Message ceb711Message = CEB711Message.builder()
                .version("1.0")
                .guid("TRF4DGGB-9940-415B-B666-C09BOO7F40B2")
                .delivery(delivery)
                .baseTransfer(baseTransfer)
                .build();

        QNameMap qmaps = new QNameMap();
        qmaps.setDefaultNamespace("http://www.chinaport.gov.cn/ceb");
        qmaps.setDefaultPrefix("ceb");

        XStream xstream = new XStream(new StaxDriver(qmaps));
        xstream.autodetectAnnotations(true);

        String xml = xstream.toXML(ceb711Message);
        System.out.println(xml);
    }

    /**
     * 将 MO 对象转换为为 XML 并打印（XStream 方式）
     *
     * @param mo
     */
    private void toXmlByXStream(Mo mo) {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);

        String xml = xstream.toXML(mo);
        System.out.println(xml);
    }

    /**
     * 将 MO 对象转换为为 XML 并打印（JAXB 方式）
     *
     * @param mo
     */
    public void toXmlByJAXB(Mo mo) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Mo.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(mo, writer);
        System.out.println(writer.toString());
    }
}
