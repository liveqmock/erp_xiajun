package com.wangqin.globalshop.logistic.app;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.wangqin.globalshop.logistic.app.bean.common.BusinessType;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import com.wangqin.globalshop.logistic.app.bean.order.JkfGoodsPurchaser;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderDetail;
import com.wangqin.globalshop.logistic.app.bean.order.JkfOrderImportHead;
import com.wangqin.globalshop.logistic.app.bean.order.OrderInfo;
import com.wangqin.globalshop.logistic.app.bean.xml.Body;
import com.wangqin.globalshop.logistic.app.bean.xml.Head;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import com.wangqin.globalshop.logistic.app.util.AESUtil;
import com.wangqin.globalshop.logistic.app.util.RSAUtil;
import org.apache.axis.client.Service;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import java.util.ArrayList;
import java.util.List;

/**
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
        byte[] inputContent = getOriginalContent();

        // 报文密文
        String content = getEncContent(inputContent);

        // 报文类型
        String msgType = "IMPORTORDER";

        // 验签字串
        String dataDigest = getSign(inputContent);

        // 发送方代码
        String sendCode = "3301961Q43";

        Service service = new Service();
        Call call = (Call)service.createCall();
        String targetNamespace = "http://ws.newyork.zjport.gov.cn/";
        call.setTargetEndpointAddress("http://122.224.230.4:18003/newyorkWS/ws/ReceiveEncryptDeclare?wsdl");
        call.setOperationName(new QName(targetNamespace, "receive"));
        // 报文密文参数
        call.addParameter("content", org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
        // 报文类型参数
        call.addParameter("msgType", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        // 签名参数
        call.addParameter("dataDigest", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        // 发送方代码参数
        call.addParameter("sendCode", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);

        // 接收回执报文
        System.out.println("\n======================================\n");
        String result = (String)call.invoke(new Object[]{content,msgType,dataDigest,sendCode});
        System.out.println("接收回执报文：");
        System.out.println(result);

        System.out.println("\n======================================\n");
        System.out.println("进行转换：");
        XStream xstream = new XStream();
        xstream.processAnnotations(Mo.class);
        Mo mo = (Mo) xstream.fromXML(result);
        System.out.println(mo);
        System.out.println(xstream.toXML(mo));
    }

    /**
     * 测试申报总署版报文
     */
    @Test
    public void testReceiveCebDeclare() {
        QNameMap qmaps = new QNameMap();
        qmaps.setDefaultNamespace("http://www.chinaport.gov.cn/ceb");
        qmaps.setDefaultPrefix("ceb");
        XStream xstream = new XStream(new StaxDriver(qmaps));
    }

    /**
     * 报文明文
     *
     * @return
     * @throws Exception
     */
    public byte[] getOriginalContent() throws Exception {
        JkfSign jkfSign = JkfSign.builder()
                .companyCode("3301961Q43")
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
                .totalCount(5D)
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

        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
        String xmlBody = xstream.toXML(mo);
        String originalContent = xmlHeader + xmlBody;

        System.out.println("发送报文：");
        System.out.println(originalContent);

        return originalContent.getBytes("utf-8");
    }

    /**
     * 报文密文
     *
     * @param inputContent
     * @return
     * @throws Exception
     */
    public String getEncContent(byte[] inputContent) throws Exception {
        String aesKey = "WAQqTRVwIEkpiqQBsCJR9Q==";
        byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
        // 报文加密加密
        String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)), "utf-8");

        return encData;
    }

    /**
     * 验签字串
     *
     * @param inputContent
     * @return
     * @throws Exception
     */
    public String getSign(byte[] inputContent) throws Exception {
        String privateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA9WM3iCCXqI7Yb0hVASUBo9ufDWPVpIy2D1UrAR5At2d+S5jFHuxk47xx719rJ7+Its4HzRy8m2RE6vbyehdnaQIDAQABAkEA6+ktFCQxaY2bKrFhd646O2wDJ35ZgM7+oRMCOxr4oNhuuD9ubR3vre0hHTsJyaoBzlL6OBdy8k1UkNKwCoY/2QIhAPszfcjqf/gSTxMu1TkUPVjidrTy2nA6ctpok7gXYOInAiEA+hNLSN1++VZJmCVkwxwlxQRnipX99flEiRiDpexFs+8CIQD3vHNrx2EHTT8xAvoD/eL2mvlJQUyObAZDQemVH3FL9wIgOlvST9jQzuMiHY1sbFPfRJD4kNDcCVD4e33rCweOZKUCIQDq/ZCmpKoQuHb8Kzjinj14pgfxAZsORA080hNPFjMLJA==";
        byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
        //生成数字签名
        String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)), "utf-8");
        return sign;
    }
}
