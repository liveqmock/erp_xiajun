package com.wangqin.globalshop.mall.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.thoughtworks.xstream.XStream;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxPayBillDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.MoneyUtil;
import com.wangqin.globalshop.common.utils.WxPay.MessageUtil;
import com.wangqin.globalshop.common.utils.WxPay.PayUtil;
import com.wangqin.globalshop.common.utils.WxPay.PaymentPo;
import com.wangqin.globalshop.common.utils.WxPay.UUIDHexGenerator;
import com.wangqin.globalshop.mall.dal.mapperExt.MallOrderDOMapperExt;
import com.wangqin.globalshop.mall.dal.mapperExt.MallWxPayBillDOMapperExt;
import com.wangqin.globalshop.mall.model.WeixinPayResultModel;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx/pay")
public class WxPayController {

    private String total_fee;   // 总金额
    private String body;        // 商品描述
    private String detail;      // 商品详情
    private String attach;      // 附加数据
    private String time_start;  // 交易起始时间
    private String time_expire; // 交易结束时间
    private String openid;      // 用户标识

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Autowired
    MallWxPayBillDOMapperExt mallWxPayBillDOMapperExt;
    @Autowired
    MallOrderDOMapperExt     mallOrderDOMapperExt;

    @RequestMapping("/notify_url")
    @ResponseBody
    public Object notifyUrl(HttpServletRequest request) {
        int size = request.getContentLength();
        if (size == -1) {
            return null;
        }
        byte[] content = new byte[size];
        try {
            InputStream is = request.getInputStream();
            int pad = 0;
            while (pad < size) {
                pad += is.read(content, pad, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = new String(content);
        System.out.println("微信支付回调参数" + body);
        XStream xstream = new XStream();
        xstream.ignoreUnknownElements();
        xstream.alias("xml", WeixinPayResultModel.class);
        WeixinPayResultModel weixinPayResult = (WeixinPayResultModel) xstream.fromXML(body);
        JsonResult<String> result = new JsonResult<>();

        if (!"SUCCESS".equals(weixinPayResult.getReturn_code())) {
            result.buildIsSuccess(false).buildMsg("订单不存在！");
            return result;
        }
        String outTradeNo = weixinPayResult.getOut_trade_no();
        MallWxPayBillDO outerOrderWxPay = mallWxPayBillDOMapperExt.selectByOutTradeNo(outTradeNo);
        outerOrderWxPay.setStatus(OrderStatus.PAID.getCode());
        mallWxPayBillDOMapperExt.updateByPrimaryKeySelective(outerOrderWxPay);
        MallOrderDO outerOrder = HaiJsonUtils.toBean(outerOrderWxPay.getOrderInfo(), new TypeReference<MallOrderDO>() {
        });
        outerOrder.setStatus(OrderStatus.PAID.getCode());
        String payFee = "0";
        try {
            payFee = MoneyUtil.changeFun2Yuan(weixinPayResult.getTotal_fee());
        } catch (Exception e) {
            e.printStackTrace();
        }
        outerOrder.setActualAmount(Double.valueOf(payFee));
        mallOrderDOMapperExt.updateByPrimaryKeySelective(outerOrder);
        return "SUCCESS";
    }

    @RequestMapping("/unifiedorder")
    @ResponseBody
    public Object pay(HttpServletRequest request, String outOrderId) throws UnsupportedEncodingException,
                                                                     DocumentException {
        String openId = "";
        String orderInfo = "";
        Double totalFee = 0.00;
        MallOrderDO outerOrder = mallOrderDOMapperExt.selectByOutOrderId(outOrderId);
        if (outerOrder == null) {
            JsonResult<Long> result = new JsonResult<>();
            result.buildIsSuccess(false).buildMsg("订单不存在！");
            return result;
        }
        totalFee = outerOrder.getTotalAmount() + outerOrder.getFreight();
        // TODO 需查表
        openId = outerOrder.getCustomerNo();
        orderInfo = HaiJsonUtils.toJson(outerOrder);
        String appid = "wxdef3e972a4a93e91";// 小程序ID
        String mch_id = "1501900631";// 商户号
        String nonce_str = UUIDHexGenerator.generate();// 随机字符串
        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String code = PayUtil.createCode(4);
        String out_trade_no = mch_id + today + code;// 商户订单号
        String spbill_create_ip = request.getRemoteAddr();// 终端IP
        String notify_url = "https://cg.logthin.cn/api/wx/pay/notify_url";// 通知地址
        String trade_type = "JSAPI";// 交易类型
        String openid = openId;// 用户标识

        // 封装支付参数
        PaymentPo paymentPo = new PaymentPo();

        paymentPo.setAppid(appid);
        paymentPo.setMch_id(mch_id);
        paymentPo.setNonce_str(nonce_str);
        String newbody = new String("购买商品");// 以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
        paymentPo.setBody(newbody);
        paymentPo.setOut_trade_no(out_trade_no);
        Integer totalPrice = MoneyUtil.changeYuan2Fen(totalFee.toString());
        paymentPo.setTotal_fee(totalPrice.toString());
        paymentPo.setSpbill_create_ip(spbill_create_ip);
        paymentPo.setNotify_url(notify_url);
        paymentPo.setTrade_type(trade_type);
        paymentPo.setOpenid(openid);

        // 把请求参数打包成Map
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("appid", paymentPo.getAppid());
        sParaTemp.put("mch_id", paymentPo.getMch_id());
        sParaTemp.put("nonce_str", paymentPo.getNonce_str());
        sParaTemp.put("body", paymentPo.getBody());
        sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
        sParaTemp.put("total_fee", paymentPo.getTotal_fee());
        sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
        sParaTemp.put("notify_url", paymentPo.getNotify_url());
        sParaTemp.put("trade_type", paymentPo.getTrade_type());
        sParaTemp.put("openid", paymentPo.getOpenid());

        // 除去Map中的空值和签名参数
        Map<String, String> sPara = PayUtil.paraFilter(sParaTemp);
        String prestr = PayUtil.createLinkString(sPara); // 把Map所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String key = "&key=KZnlxcyHpetdgbtVwXiSMURszSpOfeNx"; // 商户支付密钥
        // MD5运算生成签名
        String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
        paymentPo.setSign(mysign);
        // 打包要发送的xml
        String respXml = MessageUtil.messageToXML(paymentPo);
        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
        respXml = respXml.replace("__", "_");
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 统一下单API接口链接
        String param = respXml;
        String result = PayUtil.httpRequest(url, "POST", param);
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        InputStream in = new ByteArrayInputStream(result.getBytes());
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        for (Element element : elementList) {
            map.put(element.getName(), element.getText());
        }
        // 返回信息
        String return_code = map.get("return_code");// 返回状态码
        JSONObject JsonObject = new JSONObject();
        // 请求成功
        if (return_code == "SUCCESS" || return_code.equals(return_code)) {
            // 业务结果
            String prepay_id = map.get("prepay_id");// 返回的预付单信息
            String nonceStr = UUIDHexGenerator.generate();
            JsonObject.put("nonceStr", nonceStr);
            JsonObject.put("package", "prepay_id=" + prepay_id);
            Long timeStamp = System.currentTimeMillis() / 1000;
            JsonObject.put("timeStamp", timeStamp + "");
            String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id
                                    + "&signType=MD5&timeStamp=" + timeStamp;
            // 再次签名
            String paySign = PayUtil.sign(stringSignTemp, "&key=KZnlxcyHpetdgbtVwXiSMURszSpOfeNx",
                                          "utf-8").toUpperCase();
            JsonObject.put("paySign", paySign);
            // 商户单号
            JsonObject.put("outTradeNo", out_trade_no);
        }

        if (StringUtils.isNotBlank(orderInfo)) {
            MallWxPayBillDO outerOrderWxPay = new MallWxPayBillDO();
            outerOrderWxPay.setWxPayTradeNo(out_trade_no);
            outerOrderWxPay.setOrderInfo(orderInfo);
            outerOrderWxPay.setStatus(0);
            outerOrderWxPay.setCustomerNo(outerOrder.getCustomerNo());
            outerOrderWxPay.setGmtCreate(new Date());
            outerOrderWxPay.setGmtModify(new Date());
            mallWxPayBillDOMapperExt.insertSelective(outerOrderWxPay);
        }
        return JsonObject;
    }
}
