package com.wangqin.globalshop.logistic.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CustomsOrderDOMapperExt;
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
import com.wangqin.globalshop.logistic.app.util.EncryptionUtil;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import java.util.ArrayList;
import java.util.List;


/**
 * 电商平台发送商品订单数据到通关服务平台
 *
 * @author angus
 * @date 2018/8/24
 */
public class CustomsOrderService {

    private Logger logger = LoggerFactory.getLogger(CustomsOrderService.class);

    @Autowired
    private IMallOrderService mallOrderService;

    @Autowired
    private IMallSubOrderService mallSubOrderService;

    @Autowired
    private CustomsOrderDOMapperExt customsOrderDOMapper;


    /**
     * 电商平台发送商品订单数据到通关服务平台
     */
    public void sendOrderMessage(String orderNo) {
        // 获取主订单信息
        MallOrderDO mallOrderDO = mallOrderService.selectByOrderNo(orderNo);

        // 获取子订单信息
        List<MallSubOrderDO> mallSubOrderDOList = mallSubOrderService.selectByOrderNo(orderNo);

        // 获取支付单信息

        // 构建报文
        JkfSign jkfSign = buildJkfSign();
        JkfOrderImportHead jkfOrderImportHead = buildJkfOrderImportHead(orderNo);
        List<JkfOrderDetail> jkfOrderDetailList = buildJkfOrderDetailList(mallSubOrderDOList);
        JkfGoodsPurchaser jkfGoodsPurchaser = buildJkfGoodsPurchaser();
        Mo mo = buildOrderMo(jkfSign, jkfOrderImportHead, jkfOrderDetailList, jkfGoodsPurchaser);

        // 发送报文，接收回执

        // 存储信息
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
                .businessType(BusinessType.IMPORTORDER)
                .declareType('1')
                .cebFlag("03")
                .note(null)
                .build();
    }

    /**
     * 构建订单表头信息
     *
     * @param orderNo 订单编号（待定）
     * @return
     */
    private JkfOrderImportHead buildJkfOrderImportHead(String orderNo) {
        // TODO: 需要从 mal_order 表获得的字段
        // 对应 actual_amount 字段
        double orderGoodsAmount = 0D;
        // 对应 order_time 字段
        String tradeTime = "";
        // 对应 company_no 字段
        String eCommerceCode = "";
        String eCommerceName = "";

        // TODO: 需要从 mal_sub_order 表获得的字段
        // 对应 telephone 字段
        String consigneeTel = "";
        // 对应 receiver 字段
        String consignee = "";
        // 对应 receiver_state + receiver_city + receiver_district + receiver_address
        String consigneeAddress = "";
        // 对应 quantity 字段
        int totalCount = 0;

        // TODO: 需要从 mall_wx_pay_bill 表获取的字段
        String purchaserId = "";
        String payNumber = "";

        // TODO: 需要通过计算获得
        double orderTaxAmount = 0D;
        double orderTotalAmount = orderGoodsAmount + orderTaxAmount;

        // TODO: 待添加字段
        String senderCountry = "";
        String senderName = "";

        // 构建海关清关订单表头信息
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
                .eCommerceCode(eCommerceCode)
                .eCommerceName(eCommerceName)
                .tradeTime(tradeTime)
                .currCode("142")
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
    private List<JkfOrderDetail> buildJkfOrderDetailList(List<MallSubOrderDO> mallSubOrderDOList) {

        // 订单表体
        List<JkfOrderDetail> jkfOrderDetailList = new ArrayList<>();

        for (int i = 0; i < mallSubOrderDOList.size(); i++) {
            MallSubOrderDO mallSubOrderDO = mallSubOrderDOList.get(i);

            JkfOrderDetail jkfOrderDetail = JkfOrderDetail.builder()
                    .goodsOrder(i)
                    .goodsName(mallSubOrderDO.getItemName())
                    // FIXME: codeTs 无法从 mall_sub_order 获取
                    .codeTs("0100000001")
                    .goodsModel(null)
                    // FIXME: originCountry 无法从 mall_sub_order 获取
                    .originCountry("00342")
                    .unitPrice(mallSubOrderDO.getSalePrice())
                    .currency("142")
                    .goodsCount(mallSubOrderDO.getQuantity())
                    // FIXME: goodsUnit 无法从 mall_sub_order 获取
                    .goodsUnit("035")
                    .grossWeight(null)
                    .barCode(null)
                    .note(null)
                    .build();

            jkfOrderDetailList.add(jkfOrderDetail);
        }

        return jkfOrderDetailList;
    }

    /**
     * 构建订单购买人信息
     *
     * @return
     */
    private JkfGoodsPurchaser buildJkfGoodsPurchaser() {

        // TODO: 需要从 mal_sub_order 表获得的字段
        String id = "";
        String name = "";
        String telNumber = "";
        String paperNumber = "";

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
     * @param jkfSign            签名信息
     * @param jkfOrderImportHead 订单表头信息
     * @param jkfOrderDetailList 订单表体信息
     * @param jkfGoodsPurchaser  购买人信息
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

    /**
     * 调用海关申报数据接口
     *
     * @param originalContent 报文原文
     * @param msgType         报文业务类型
     * @return
     * @throws Exception
     */
    private String callReceiveEncryptDeclareService(String originalContent, String msgType) throws Exception {
        logger.info("发送报文：", originalContent);

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

        logger.info("接收回执报文：", result);

        return result;
    }
}
