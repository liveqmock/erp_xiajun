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
                .businessType(BusinessType.PERSONAL_GOODS_DECLAR)
                .declareType('1')
                .cebFlag("03")
                .note(note)
                .build();
    }

    private GoodsDeclare buildGoodsDeclare() {
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
        return goodsDeclare;
    }

    private List<GoodsDeclareDetail> buildGoodsDeclareDetails() {
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
