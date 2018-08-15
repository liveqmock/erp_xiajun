package com.wangqin.globalshop.common.idcard;

import com.alibaba.fastjson.JSON;
import com.songshun.sdk.entity.RespEntity;
import com.songshun.sdk.http.HttpRequestClient;
import com.wangqin.globalshop.common.base.BaseDto;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 发起http请求工具类
 * Created by jyl on 2017/5/17.
 */
public class IDCardHttpClientUtil {


    /**
     * 字符编码格式UTF-8
     */
    private final static String ENCODING = "UTF-8";

    /**
     * 发起http-get请求，(post 方式用户自实现)
     * @param map
     * @throws Exception
     */
    public static  void invoke(Map<String, Object> map) throws Exception{
        StringBuilder sb = new StringBuilder();
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        if (map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String value=String.valueOf( entry.getValue()).trim();
                if(value!=null && value!="null") {
                    // post 提交参数
                    parameters.add(new BasicNameValuePair(entry.getKey(),value));
                    // get 提交参数
                    sb.append(entry.getKey()).append("=").append(value).append("&");
                }
            }
        }
        System.out.println( String.format("调用服务参数：%s", sb.toString()) );

        //签名
        String signature=ConfigContant.sign(map);

        //拼接请求地址
        String url= MessageFormat.format(ConfigContant.URL+"/{0}/{1}/{2}/{3}/{4}", ConfigContant.VERSION,
                ConfigContant. ACCCODE, ConfigContant.ACCESSKEYID,signature,map.get("timestamp"));
        System.out.println(String.format("调用服务开始 ，url：%s",url) );
        System.out.println(String.format("调用服务开始 ，parameters：%s",BaseDto.toString(parameters)));
        RespEntity respEntity =null;
        try {
            //respEntity = HttpRequestClient.get_http(url+"?"+sb.toString(),  ENCODING);
            // 发起post 请求,请放开下面一行的注释
            respEntity = HttpRequestClient.post_http(url, parameters, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println( String.format("调用服务完整 ，url：%s",url+"?"+sb.toString()) );
        System.out.println("调用服务结束 ，返回报文："+ JSON.toJSONString(respEntity));


      // String result=" {"ext":{"cardinfo":"{"abbreviation":"CMB","bankname":"招商银行","cardname":"一卡通(银联卡)","cardtype":"银联借记卡","iscreditcard":1}"},"key":"0000","msg":"认证通过","requestId":"2017112817512285049089"}";

        if(respEntity.getKey().equals("0000")){
            System.out.println("返回码：" + respEntity.getKey());
            System.out.println("返回码信息：" + respEntity.getMsg());
            System.out.println("返回码内容：" + respEntity.getExt());
        }else{//错误返回
            System.out.println("返回码：" + respEntity.getKey());
            System.out.println("返回码信息：" + respEntity.getMsg());
            System.out.println("返回码内容：" + respEntity.getExt());
        }
    }


	/**
	 * 发起http-get请求，返回内容进行业务处理
	 * @param map
	 * @throws Exception
	 */
	public static RespEntity invokeWithResp(Map<String, Object> map) throws Exception{
		StringBuilder sb = new StringBuilder();
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if (map.size() > 0) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String value=String.valueOf( entry.getValue()).trim();
				if(value!=null && value!="null") {
					// post 提交参数
					parameters.add(new BasicNameValuePair(entry.getKey(),value));
					// get 提交参数
					sb.append(entry.getKey()).append("=").append(value).append("&");
				}
			}
		}
		System.out.println( String.format("调用服务参数：%s", sb.toString()) );

		//签名
		String signature=ConfigContant.sign(map);

		//拼接请求地址
		String url= MessageFormat.format(ConfigContant.URL+"/{0}/{1}/{2}/{3}/{4}", ConfigContant.VERSION,
				ConfigContant. ACCCODE, ConfigContant.ACCESSKEYID,signature,map.get("timestamp"));
		System.out.println(String.format("调用服务开始 ，url：%s",url) );
		System.out.println(String.format("调用服务开始 ，parameters：%s",BaseDto.toString(parameters)));
		RespEntity respEntity =null;
		try {
			//respEntity = HttpRequestClient.get_http(url+"?"+sb.toString(),  ENCODING);
			// 发起post 请求,请放开下面一行的注释
			respEntity = HttpRequestClient.post_http(url, parameters, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println( String.format("调用服务完整 ，url：%s",url+"?"+sb.toString()) );
		System.out.println("调用服务结束 ，返回报文："+ JSON.toJSONString(respEntity));


		// String result=" {"ext":{"cardinfo":"{"abbreviation":"CMB","bankname":"招商银行","cardname":"一卡通(银联卡)","cardtype":"银联借记卡","iscreditcard":1}"},"key":"0000","msg":"认证通过","requestId":"2017112817512285049089"}";

		if(respEntity.getKey().equals("0000")){
			System.out.println("返回码：" + respEntity.getKey());
			System.out.println("返回码信息：" + respEntity.getMsg());
			System.out.println("返回码内容：" + respEntity.getExt());
		}else{//错误返回
			System.out.println("返回码：" + respEntity.getKey());
			System.out.println("返回码信息：" + respEntity.getMsg());
			System.out.println("返回码内容：" + respEntity.getExt());
		}
		return respEntity;
	}
}
