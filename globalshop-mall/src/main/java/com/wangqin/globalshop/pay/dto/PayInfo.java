package com.wangqin.globalshop.pay.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 盛付通支付要素 <br>
 * 微信小程序方式时，payinfo 为支付要素：
 * <pre>
 * {
 * 	"appId": "wx2421b1c4370ec43b",
 * 	"timeStamp": "1528855300",
 * 	"nonceStr": "427f468ae7654c4ea615c6f5189aa3cd",
 * 	"package": "prepay_id=wx13100140876526f3e8b7b7e81402117356",
 * 	"signType": "RSA",
 * 	"paySign": "SnfBTET9Z5lSKbd6hiffzbW/p/WoJuUoteNLpLe"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/16
 */
@Data
public class PayInfo {
    /**
     * 微信小程序调起支付数据签名字段列表
     */
    private String appId;
    private String timeStamp;
    private String nonceStr;
    @SerializedName("package")
    private String pack;
    private String signType;
    private String paySign;
}
