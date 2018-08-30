package com.wangqin.globalshop.logistic.app;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclare;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclareDetail;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclareModule;
import com.wangqin.globalshop.logistic.app.bean.order.JkfGoodsPurchaser;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderDetail;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderImportHead;
import com.wangqin.globalshop.logistic.app.bean.order.OrderInfo;
import com.wangqin.globalshop.logistic.app.bean.xml.Body;
import com.wangqin.globalshop.logistic.app.bean.xml.Head;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import com.wangqin.globalshop.logistic.app.constant.BusinessType;
import com.wangqin.globalshop.logistic.app.constant.CustomsConst;
import com.wangqin.globalshop.logistic.app.util.EncryptionUtil;
import org.apache.axis.client.Service;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过 webservice 调用海关接口测试类
 *
 * @author angus
 * @date 2018/8/22
 */
public class TestWsClient {

    /*
     * 1. 流程:
     * 1) 点击【生成key】按钮 生成aes密钥、企业rsa私钥、 rsa公钥 ；
     * 2) 把发送方代码 aes密钥 rsa公钥提供给浙江电子口岸技术，供电子口岸解密验签；
     * 3) 点击【生成密文签名】生成密文和签名 比对代码生成的密文和签名是否一致来判断是否正确生成
     * <p>
     * <p>
     * 2. 加密验签:
     * RSA说明：
     * KEY_ALGORITHM = "RSA"
     * SIGNATURE_ALGORITHM = "SHA1withRSA"
     * <p>
     * AES说明：
     * KEY_ALGORITHM = "AES"
     * CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"
     * <p>
     * 加密例子:
     * byte[] inputContent = originalContent.getBytes("utf-8");
     * byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
     * byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
     * // 报文加密加密
     * String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)),"utf-8");
     * <p>
     * //生成数字签名
     * String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)),"utf-8");
     * <p>
     * 工具类下载:
     * 加解密工具类下载
     * <p>
     * 3.数据申报例子:
     * Service service = new Service();
     * Call call = (Call)service.createCall();
     * String targetNamespace = "http://ws.newyork.zjport.gov.cn/";
     * call.setTargetEndpointAddress("ws申报地址");
     * call.setOperationName(new QName(targetNamespace, "receive"));
     * call.addParameter("content", org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//报文密文
     * call.addParameter("msgType", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//报文类型
     * call.addParameter("dataDigest", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//签名
     * call.addParameter("sendCode", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//发送方代码
     * call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
     * return (String)call.invoke(new Object[]{content,msgType,dataDigest,sendCode});
     */

    /**
     * 测试申报数据加密
     *
     * @throws Exception
     */
    @Test
    public void testReceiveEncryptDeclareService() throws Exception {
//        // 待发送报文
//        String originalContent = getOrderOriginalContent();
//        // 回执报文
//        String result = callReceiveEncryptDeclareService(originalContent, BusinessType.IMPORTORDER);

        String originalContent = getDeclareOriginalContent();
        // 回执报文
        String result = callReceiveEncryptDeclareService(originalContent, BusinessType.PERSONAL_GOODS_DECLAR);

        System.out.println("\n转换为 bean：");
        XStream xstream = new XStream();
        xstream.processAnnotations(Mo.class);
        Mo mo = (Mo) xstream.fromXML(result);
        System.out.println(mo);
        System.out.println("\n再将 bean 装换为 xml，用以校验：");
        System.out.println(xstream.toXML(mo));
    }

    /**
     * 订单报文明文测试样例
     *
     * @return
     */
    private String getOrderOriginalContent() {
        // 签名信息
        JkfSign jkfSign = JkfSign.builder()
                .companyCode(CustomsConst.COMPANY_CODE)
                .businessNo("order001")
                .businessType(BusinessType.IMPORTORDER)
                .declareType('1')
                .cebFlag("03")
                .note(null)
                .build();
        // 订单信息
        JkfOrderImportHead jkfOrderImportHead = JkfOrderImportHead.builder()
                .companyName(CustomsConst.COMPANY_NAME)
                .companyCode(CustomsConst.COMPANY_CODE)
                .ieFlag('I')
                .payType("03")
                .payCompanyCode(CustomsConst.PAY_COMPANY_CODE)
                .payCompanyName(CustomsConst.PAY_COMPANY_NAME)
                .payNumber("zhifu001")
                .orderTotalAmount(100.0D)
                .orderGoodsAmount(100.0D)
                .discount(0D)
                .orderNo("order00001")
                .orderTaxAmount(10.0D)
                .feeAmount(0D)
                .insureAmount(0D)
                .eCommerceCode("123456789")
                .eCommerceName("某某公司旗下商店")
                .tradeTime("2014-02-17 15:23:13")
                .currCode("142")
                .totalAmount(100.0D)
                .consigneeEmail(null)
                .consigneeTel("13242345433")
                .consignee("loujianhua")
                .consigneeAddress("浙江杭州聚龙大厦")
                .totalCount(5)
                .batchNumbers(null)
                .consigneeDitrict(null)
                .postMode(null)
                .senderCountry("34233")
                .senderName("yangtest")
                .purchaserId("2")
                .logisCompanyName(CustomsConst.LOGIS_COMPANY_NAME)
                .logisCompanyCode(CustomsConst.LOGIS_COMPANY_CODE)
                .zipCode(null)
                .note(null)
                .wayBills(null)
                .rate(null)
                .userProcotol("本人承诺所购买商品系个人合理自用，现委托商家代理申报、代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任。")
                .build();
        // 订单表体明细
        JkfOrderDetail jkfOrderDetail1 = JkfOrderDetail.builder()
                .goodsOrder(1)
                .goodsName("物品名称1")
                .codeTs("0100000001")
                .goodsModel(null)
                .originCountry("00342")
                .unitPrice(2D)
                .currency("142")
                .goodsCount(40)
                .goodsUnit("035")
                .grossWeight(null)
                .barCode(null)
                .note(null)
                .build();
        JkfOrderDetail jkfOrderDetail2 = JkfOrderDetail.builder()
                .goodsOrder(2)
                .goodsName("物品名称1")
                .codeTs("0100000001")
                .goodsModel(null)
                .originCountry("00342")
                .unitPrice(3.3D)
                .currency("142")
                .goodsCount(343)
                .goodsUnit("035")
                .grossWeight(null)
                .barCode(null)
                .note(null)
                .build();
        // 订单表体
        List<JkfOrderDetail> jkfOrderDetailList = new ArrayList<>();
        jkfOrderDetailList.add(jkfOrderDetail1);
        jkfOrderDetailList.add(jkfOrderDetail2);
        // 购买人信息
        JkfGoodsPurchaser jkfGoodsPurchaser = JkfGoodsPurchaser.builder()
                .id("2")
                .name("刘安光")
                .email(null)
                .telNumber("24233322323")
                .paperType("01")
                .paperNumber("130626199605271679")
                .address(null)
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

        return toXML(mo);
    }

    /**
     * 清单报文明文测试样例
     *
     * @return
     */
    private String getDeclareOriginalContent() {
        Head head = Head.builder()
                .businessType(BusinessType.PERSONAL_GOODS_DECLAR)
                .build();

        JkfSign jkfSign = JkfSign.builder()
                .companyCode(CustomsConst.COMPANY_CODE)
                .businessNo("业务编码")
                .businessType(BusinessType.PERSONAL_GOODS_DECLAR)
                .declareType('1')
                .cebFlag("03")
                .note("备注")
                .build();

        GoodsDeclare goodsDeclare = GoodsDeclare.builder()
                .accountBookNo("123456")
                .ieFlag('I')
                .preEntryNumber("111111111111111111")
                .importType('1')
                .inOutDateStr("2014-02-16 10:08:01")
                .iePort("11111")
                .destinationPort("1155")
                .trafName(null)
                .voyageNo(null)
                .trafNo(null)
                .trafMode("运输方式代码")
                .declareCompanyType("个人委托电商企业申报 ")
                .declareCompanyCode("11111111")
                .declareCompanyName("电子口岸")
                .companyName(CustomsConst.COMPANY_NAME)
                .companyCode(CustomsConst.COMPANY_CODE)
                .eCommerceCode("11111111")
                .eCommerceName("电子口岸")
                .logisCompanyName("邮政速递")
                .logisCompanyCode("3300232323")
                .orderNo("111111")
                .wayBill("111111")
                .billNo(null)
                .tradeCountry("001")
                .packNo(100)
                .grossWeight(99D)
                .netWeight(88D)
                .warpType(null)
                .remark(null)
                .declPort("6400")
                .enteringPerson("9999")
                .enteringCompanyName("9999")
                .declarantNo(null)
                .customsField("码头/货场代码")
                .senderName("发件人")
                .consignee("收件人")
                .senderCountry("142")
                .senderCity(null)
                .paperType(null)
                .paperNumber(null)
                .consigneeAddress("浙江杭州聚龙大厦")
                .purchaserTelNumber("13443224322")
                .buyerIdType("1")
                .buyerIdNumber("130626199605271679")
                .buyerName("刘安光")
                .worth(77D)
                .feeAmount(5345.0D)
                .insureAmount(4.3343D)
                .currCode("142")
                .mainGName("主要货物名称")
                .internalAreaCompanyNo("111")
                .internalAreaCompanyName("区内企业名称")
                .assureCode(CustomsConst.COMPANY_CODE)
                .applicationFormNo("123456")
                .isAuthorize('1')
                .licenseNo(null)
                .build();

        GoodsDeclareDetail goodsDeclareDetail = GoodsDeclareDetail.builder()
                .goodsOrder(0)
                .codeTs("1100111001")
                .goodsItemNo(null)
                .itemRecordNo("123456")
                .itemName(null)
                .goodsName("物品名称")
                .goodsModel("商品规格、型号")
                .originCountry("产销国")
                .tradeCurr("142")
                .tradeTotal(null)
                .declPrice(80D)
                .declTotalPrice(800D)
                .useTo(null)
                .declareCount(10)
                .goodsUnit("申报计量单位")
                .goodsGrossWeight(null)
                .firstUnit("个")
                .firstCount(4444)
                .secondUnit(null)
                .secondCount(null)
                .productRecordNo("产品国检备案编号")
                .webSite(null)
                .barCode(null)
                .note(null)
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

        return toXML(mo);
    }

    /**
     * 调用海关申报数据接口
     *
     * @param originalContent 报文原文
     * @param msgType         报文业务类型
     * @return
     * @throws Exception
     */
    @SuppressWarnings("Duplicates")
    private String callReceiveEncryptDeclareService(String originalContent, String msgType) throws Exception {
        System.out.println("发送报文：");
        System.out.println(originalContent);

        byte[] inputContent = originalContent.getBytes("utf-8");
        // 报文密文
        String content = EncryptionUtil.getEncContent(inputContent);
        // 验签字串
        String dataDigest = EncryptionUtil.getSign(inputContent);
        // 发送方代码
        String sendCode = CustomsConst.COMPANY_CODE;

        Call call = new Service().createCall();
        String targetNamespace = "http://ws.newyork.zjport.gov.cn/";
        call.setTargetEndpointAddress("http://122.224.230.4:18003/newyorkWS/ws/ReceiveEncryptDeclare?wsdl");
        call.setOperationName(new QName(targetNamespace, "receive"));
        // 报文密文参数
        call.addParameter("content", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        // 报文类型参数
        call.addParameter("msgType", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        // 签名参数
        call.addParameter("dataDigest", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        // 发送方代码参数
        call.addParameter("sendCode", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        // 接收回执报文
        String result = (String) call.invoke(new Object[]{content, msgType, dataDigest, sendCode});
        System.out.println("接收回执报文：");
        System.out.println(result);

        return result;
    }

    private String toXML(Mo mo) {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        return xstream.toXML(mo);
    }
}
