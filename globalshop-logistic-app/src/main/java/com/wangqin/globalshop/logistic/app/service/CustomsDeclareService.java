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

import java.util.ArrayList;
import java.util.List;

/**
 * 清单写入跨境电商通关服务平台
 *
 * @author angus
 * @date 2018/8/28
 */
public class CustomsDeclareService {

    /**
     * 清单写入跨境电商通关服务平台
     */
    public void sendDeclareMessage() {
        JkfSign jkfSign = buildJkfSign();
    }

    /**
     * 构建签名信息
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
}
