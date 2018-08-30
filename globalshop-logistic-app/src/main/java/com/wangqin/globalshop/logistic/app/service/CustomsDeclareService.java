package com.wangqin.globalshop.logistic.app.service;

import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclare;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclareDetail;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclareModule;
import com.wangqin.globalshop.logistic.app.bean.xml.Body;
import com.wangqin.globalshop.logistic.app.bean.xml.Head;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import com.wangqin.globalshop.logistic.app.constant.BusinessType;
import com.wangqin.globalshop.logistic.app.constant.CustomsConst;
import com.wangqin.globalshop.logistic.app.util.EncryptionUtil;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import java.util.ArrayList;
import java.util.List;

/**
 * 清单写入跨境电商通关服务平台
 *
 * @author angus
 * @date 2018/8/28
 */
public class CustomsDeclareService {
    private Logger logger = LoggerFactory.getLogger(CustomsDeclareService.class);

    /**
     * 清单写入跨境电商通关服务平台
     */
    public void sendDeclareMessage() {
        JkfSign jkfSign = buildJkfSign();
    }

    /**
     * 构建签名信息（参数待定）
     *
     * @return
     */
    private JkfSign buildJkfSign() {
        // 生成业务编号
        String businessNo = "";

        return JkfSign.builder()
                .companyCode(CustomsConst.COMPANY_CODE)
                .businessNo(businessNo)
                .businessType(BusinessType.PERSONAL_GOODS_DECLAR)
                .declareType('1')
                .cebFlag("03")
                .note(null)
                .build();
    }


    private GoodsDeclare buildGoodsDeclare(String accountBookNo, String preEntryNumber, String inOutDateStr,
                                           String iePort, String destinationPort, String trafMode, String declareCompanyType,
                                           String declareCompanyCode, String declareCompanyName, String eCommerceCode,
                                           String eCommerceName, String orderNo, String wayBill, String tradeCountry,
                                           Integer packNo, Double grossWeight, Double netWeight, String declPort,
                                           String enteringPerson, String enteringCompanyName, String customsField,
                                           String senderName, String consignee, String senderCountry, String consigneeAddress,
                                           String purchaserTelNumber, String buyerIdNumber, String buyerName, Double worth,
                                           Double feeAmount, Double insureAmount, String currCode, String mainGName,
                                           String internalAreaCompanyNo, String internalAreaCompanyName, String applicationFormNo) {


        GoodsDeclare goodsDeclare = GoodsDeclare.builder()
                .accountBookNo(accountBookNo)
                .ieFlag('I')
                .preEntryNumber(preEntryNumber)
                .importType('1')
                .inOutDateStr(inOutDateStr)
                .iePort(iePort)
                .destinationPort(destinationPort)
                .trafName(null)
                .voyageNo(null)
                .trafNo(null)
                .trafMode(trafMode)
                .declareCompanyType(declareCompanyType)
                .declareCompanyCode(declareCompanyCode)
                .declareCompanyName(declareCompanyName)
                .companyName(CustomsConst.COMPANY_NAME)
                .companyCode(CustomsConst.COMPANY_CODE)
                .eCommerceCode(eCommerceCode)
                .eCommerceName(eCommerceName)
                .logisCompanyName(CustomsConst.LOGIS_COMPANY_NAME)
                .logisCompanyCode(CustomsConst.LOGIS_COMPANY_CODE)
                .orderNo(orderNo)
                .wayBill(wayBill)
                .billNo(null)
                .tradeCountry(tradeCountry)
                .packNo(packNo)
                .grossWeight(grossWeight)
                .netWeight(netWeight)
                .warpType(null)
                .remark(null)
                .declPort(declPort)
                .enteringPerson(enteringPerson)
                .enteringCompanyName(enteringCompanyName)
                .declarantNo(null)
                .customsField(customsField)
                .senderName(senderName)
                .consignee(consignee)
                .senderCountry(senderCountry)
                .senderCity(null)
                .paperType(null)
                .paperNumber(null)
                .consigneeAddress(consigneeAddress)
                .purchaserTelNumber(purchaserTelNumber)
                .buyerIdType("1")
                .buyerIdNumber(buyerIdNumber)
                .buyerName(buyerName)
                .worth(worth)
                .feeAmount(feeAmount)
                .insureAmount(insureAmount)
                .currCode(currCode)
                .mainGName(mainGName)
                .internalAreaCompanyNo(internalAreaCompanyNo)
                .internalAreaCompanyName(internalAreaCompanyName)
                .assureCode(CustomsConst.COMPANY_CODE)
                .applicationFormNo(applicationFormNo)
                .isAuthorize('1')
                .licenseNo(null)
                .build();

        return goodsDeclare;
    }

    private List<GoodsDeclareDetail> buildGoodsDeclareDetails() {

        GoodsDeclareDetail goodsDeclareDetail = GoodsDeclareDetail.builder()
                .goodsOrder(1)
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
        return goodsDeclareDetails;
    }

    /**
     * 构建订单报文对应的 Mo 实体对象
     *
     * @param jkfSign             签名信息
     * @param goodsDeclare        清单表头信息
     * @param goodsDeclareDetails 清单表体信息
     * @return
     */
    private Mo buildDeclareMo(JkfSign jkfSign, GoodsDeclare goodsDeclare, List<GoodsDeclareDetail> goodsDeclareDetails) {
        Head head = Head.builder()
                .businessType(BusinessType.PERSONAL_GOODS_DECLAR)
                .build();

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

        return Mo.builder()
                .version("1.0.0")
                .head(head)
                .body(body)
                .build();
    }

    /**
     * 调用海关申报数据接口
     *
     * @param originalContent 报文原文
     * @param msgType         报文业务类型
     * @return 响应报文
     * @throws Exception
     */
    @SuppressWarnings("Duplicates")
    private String callReceiveEncryptDeclareService(String originalContent, String msgType) throws Exception {
        logger.info("发送报文：{}", originalContent);

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
        String response = (String) call.invoke(new Object[]{content, msgType, dataDigest, sendCode});

        logger.info("接收回执报文：{}", response);

        return response;
    }
}
