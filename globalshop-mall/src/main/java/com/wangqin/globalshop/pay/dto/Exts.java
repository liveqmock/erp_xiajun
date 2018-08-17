package com.wangqin.globalshop.pay.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 盛付通请求/响应扩展属性（微信H5 、微信小程序、微信APP必传，请参考备注）
 * <br>
 * 微信小程序或微信APP支付，需要在 exts 中传入一个 JSON 格式的对象，对象包括 appid
 * （注意：微信小程序支付时上送的 appid 为商户在微信平台申请的微信小程序 appid；
 * 微信APP支付时上送的 appid 为商户在微信平台申请的微信 APP 支付的 appid），数据格式为：
 * <pre>
 * {
 *     "appid":"wx11111111111111"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/16
 */
@Data
@Builder
public class Exts {
    /**
     * 微信APP支付 appid
     */
    private String appid;
}
