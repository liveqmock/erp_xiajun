package com.wangqin.globalshop.pay.service;

import com.wangqin.globalshop.pay.dto.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 用于对接盛付通接口的 service
 *
 * @author angus
 * @date 2018/8/14
 */
public interface ShengpayService {
    Logger logger = LoggerFactory.getLogger(ShengpayService.class);

    /**
     * 商户号
     */
    String MERCHANT_NO = "540511";

    /**
     * 签名加密类型
     */
    String SIGN_TYPE = "MD5";

    /**
     * MD5 密钥（用于获取消息加密摘要）
     */
    String MD5_KEY = "support4test";

    /**
     * 编码
     */
    String CHARSET = "UTF-8";

    /**
     * 货币类型
     */
    String CURRENCY = "CNY";

    /**
     * 公众号，服务窗支付，银联支付码，微信条码，支付宝条码，微信小程序，微信APP支付必填
     */
    String OPEN_ID = "wxe25c15397f0ec710";

    /**
     * 盛付通请求 Base url
     */
    String SHENGPAY_BASE_URL = "http://mgw.shengpay.com/web-acquire-channel/pay/";

    /**
     * 接收服务端的支付成功通知URL，需要是公网可访问的地址
     */
    String PAY_NOTIFY_URL = "http://45.77.198.100/javademo/notify.jsp";

    /**
     * 同步跳转URL（微信H5，支付宝H5，公众号，服务窗必传,同时这个地址的一级域名必须和报备的授权域名一致）
     */
    String PAY_PAGE_URL = "http://45.77.198.100/javademo/page.jsp";

    /**
     * 接收服务端的退款成功通知URL，需要是公网可访问的地址
     */
    String REFUND_NOTIFY_URL = "http://45.77.198.100/javademo/notify.jsp";

    /**
     * 接收服务端的分账成功通知URL，需要是公网可访问的地址
     */
    String SHARYING_NOTIFY_URL = "http://45.77.198.100/javademo/notify.jsp";


    /**
     * 获得 ShengpayService 实例，用于对接盛付通
     * <br>
     * 关于 Retrofit 的用法请参考 https://square.github.io/retrofit/
     *
     * @return ShengpayService 实例
     */
    static ShengpayService newInstance() {
        // 为 Retrofit 配置 Http Log 拦截器（借助 OkHttp）
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(logger::debug)
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SHENGPAY_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ShengpayService.class);
    }

    /**
     * 创建支付订单
     *
     * @param signType        请求头-加密类型
     * @param signMsg         请求头-消息 MD5 摘要
     * @param orderPayRequest 请求体-创建支付订单请求参数
     * @return Retrofit Http 请求调用对象
     */
    @POST("order.htm")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Call<OrderPayResponse> orderPay(@Header("signType") String signType,
                                    @Header("signMsg") String signMsg,
                                    @Body OrderPayRequest orderPayRequest);

    /**
     * 单笔查询
     *
     * @param signType        请求头-加密类型
     * @param signMsg         请求头-消息 MD5 摘要
     * @param queryPayRequest 请求体-单笔查询请求参数
     * @return Retrofit Http  请求调用对象
     */
    @POST("query.htm")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Call<QueryPayResponse> queryPay(@Header("signType") String signType,
                                    @Header("signMsg") String signMsg,
                                    @Body QueryPayRequest queryPayRequest);

    /**
     * 退款请求
     *
     * @param signType         请求头-加密类型
     * @param signMsg          请求头-消息 MD5 摘要
     * @param refundPayRequest 请求体-退款请求参数
     * @return Retrofit Http 请求调用对象
     */
    @POST("refund.htm")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Call<RefundPayResponse> refundPay(@Header("signType") String signType,
                                      @Header("signMsg") String signMsg,
                                      @Body RefundPayRequest refundPayRequest);

    /**
     * 退款查询
     *
     * @param signType           请求头-加密类型
     * @param signMsg            请求头-消息 MD5 摘要
     * @param queryRefundRequest 请求体-退款查询请求参数
     * @return Retrofit Http 请求调用对象
     */
    @POST("refundQuery.htm")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Call<QueryRefundResponse> queryRefund(@Header("signType") String signType,
                                          @Header("signMsg") String signMsg,
                                          @Body QueryRefundRequest queryRefundRequest);

    /**
     * 分账请求
     *
     * @param signType          请求头-加密类型
     * @param signMsg           请求头-消息 MD5 摘要
     * @param sharingPayRequest 请求体-分账请求参数
     * @return Retrofit Http 请求调用对象
     */
    @POST("sharing.htm")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Call<SharingPayResponse> sharingPay(@Header("signType") String signType,
                                        @Header("signMsg") String signMsg,
                                        @Body SharingPayRequest sharingPayRequest);

    /**
     * 分账查询
     *
     * @param signType            请求头-加密类型
     * @param signMsg             请求头-消息 MD5 摘要
     * @param querySharingRequest 请求体-分账查询请求参数
     * @return Retrofit Http 请求调用对象
     */
    @POST("sharingQuery.htm")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Call<QuerySharingResponse> querySharing(@Header("signType") String signType,
                                            @Header("signMsg") String signMsg,
                                            @Body QuerySharingRequest querySharingRequest);
}
