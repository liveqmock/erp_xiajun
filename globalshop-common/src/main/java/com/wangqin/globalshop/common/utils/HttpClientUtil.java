package com.wangqin.globalshop.common.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * 跟外部公司http调用工具类，使用的少，只考虑SHA-1签名及post的调用
 */
public class HttpClientUtil {
	//public static Logger logger = LogManager.getLogger(HttpClientUtil.class);
	
	/**
	 * 远程http post调用处理方法
	 * @return JSONObject 返回结果json串
	 * @throws Exception
	 */
	public static JSONObject post(String url, Map<String, String> params) throws Exception {
		JSONObject jsonResult = null;
		HttpEntity httpEntity = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			Set<Map.Entry<String, String>> entries = params.entrySet();
			for (Map.Entry<String, String> param : entries) {
				nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			response = httpClient.execute(httpPost);
			
			if (200 != response.getStatusLine().getStatusCode()) {
				//logger.error("接口调用失败：" + response.getStatusLine());
				throw new RuntimeException("远程调用返回异常：" + response.getStatusLine());
			}
			httpEntity = response.getEntity();
			jsonResult = httpEntity == null ? null : JSONObject.fromObject(EntityUtils.toString(httpEntity));
			
		} catch (Exception e) {
			//logger.error("调用发生错误：", e);
			throw new Exception("调用发生错误", e);
		} finally {
			try {
				EntityUtils.consume(httpEntity);
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				//logger.error("关闭错误：", e);
			}
		}
		return jsonResult;
	}
	
	/**
     * url 请求 paramUrl
     * 
     * @time 2015年11月10日下午4:40:22
     * @packageName com.rom.utils
     * @param url         url请求地址
     * @param header    请求头信息
     * @param params     url请求参数
     * @param paramstype 参数类型 1:json格式 ; 其他:正常格式;
     * @param resulttype 返回值类型 1: 压缩流字符串 2：正常字符串
     * @return
     */
    public static String post(String url, Map<String, String> header, Map<String, Object> params, String paramstype, String resulttype) {
        String result = "";
        //创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //HttpClient
        CloseableHttpClient httpClient = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        if (header != null) {
            Set<String> headerkey = header.keySet();
            Iterator<String> headerkeyit = headerkey.iterator();
            while (headerkeyit.hasNext()) {
                String key = headerkeyit.next();
                httpPost.setHeader(key, header.get(key).toString());
            }
        }

        try {
            // 如果参数类型为1 证明参数传递方式为 json格式
            if (paramstype != null && paramstype.equals("1")) {
                if (params != null) {
                    StringEntity entity = new StringEntity(JSONObject.fromObject(params).toString(), "utf-8");// 解决中文乱码问题
                    entity.setContentEncoding("UTF-8");
                    entity.setContentType("application/json");
                    httpPost.setEntity(entity);
                }
            } else {
                // 参数格式为 键值对形式
                if (params != null) {
                    List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                    Set<String> keySet = params.keySet();
                    Iterator<String> keyit = keySet.iterator();
                    while (keyit.hasNext()) {
                        String key = keyit.next();
                        formparams.add(new BasicNameValuePair(key, params.get(key).toString()));
                    }
                    UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
                    httpPost.setEntity(uefEntity);
                }
            }

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 返回类型如果不为空 并且 等于1 证明该返回结果经过zip压缩
                if (resulttype != null && resulttype.equals("1")) {
                    result = EntityUtils.toString(new GzipDecompressingEntity(entity), "utf-8");
                } else {
                    result = EntityUtils.toString(entity, "utf-8");
                }
            }
            response.close();
        } catch (Exception e) {
            //logger.error(e.getMessage());
        } finally {
            try {
				httpClient.close();
			} catch (IOException e) {
				//logger.error(e.getMessage());
			}
        }
        return result;
    }
    
    /**
     * url 请求 paramUrl
     * 
     * @time 2015年11月10日下午4:40:22
     * @packageName com.rom.utils
     * @param url         url请求地址
     * @param header    请求头信息
     * @param params     url请求参数
     * @param paramstype 参数类型 1:json格式 ; 其他:正常格式;
     * @return
     */
    public  static JSONObject post(String url ,Map<String, String> header, Map<String, Object> params, String paramstype){
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                .setSocketTimeout(10000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        
        if (header != null) {
            Set<String> headerkey = header.keySet();
            Iterator<String> headerkeyit = headerkey.iterator();
            while (headerkeyit.hasNext()) {
                String key = headerkeyit.next();
                httpPost.setHeader(key, header.get(key).toString());
            }
        }
        
        if (params != null) {
            // 如果参数类型为1 证明参数传递方式为 json格式
            if (paramstype != null && paramstype.equals("1")) {
                StringEntity entity = new StringEntity(JSONObject.fromObject(params).toString(), "utf-8");// 解决中文乱码问题
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            } else {
                // 参数格式为 键值对形式
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                Set<String> keySet = params.keySet();
                Iterator<String> keyit = keySet.iterator();
                while (keyit.hasNext()) {
                    String key = keyit.next();
                    formparams.add(new BasicNameValuePair(key, params.get(key).toString()));
                }
                UrlEncodedFormEntity uefEntity = null;
                try {
                    uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                httpPost.setEntity(uefEntity);
            }
        }
        
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(null).build();//设置进去
        CloseableHttpResponse response = null;
        StringBuffer out;
        try {
            response = httpClient.execute(httpPost); 
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            out = new StringBuffer();  
            byte[] b = new byte[4096];  
            for (int n; (n = in.read(b)) != -1;) {  
                out.append(new String(b, 0, n));  
            }
            JSONObject json = JSONObject.fromObject(out.toString());
            in = null;
            return json;
        } catch (Exception e) {
        	//logger.error(e.getMessage());
        } finally{
            try {
            	httpClient.close();
            } catch (IOException e) {
            	//logger.error(e);
            }
        }
        return null;
    }
    
    public static void main(String args[]) throws Exception {
    	/*Map<String, Object> param = new HashMap<String, Object>();
    	Map<String, Object> paramDetail = new HashMap<String, Object>();
    	paramDetail.put("transfer_id", "1739255020");
    	paramDetail.put("customer_ref_no", "PKG17062502002910007");
    	paramDetail.put("status", 3);
    	paramDetail.put("oversea_in_time", "2014-01-01 23:59:59");
    	paramDetail.put("oversea_out_time", "2015-01-01 23:59:59");
    	paramDetail.put("oversea_on_transfer_time", "2016-01-01 00:00:00");
    	paramDetail.put("inland_express_id", "ems");
    	paramDetail.put("inland_express_no", "123456789");
    	paramDetail.put("weight", "1.52");
    	paramDetail.put("volume", "1.5*1.6*1.8");
    	paramDetail.put("inland_express_id", "1.5*1.6*1.8");
    	paramDetail.put("totalfee", "25.21");
    	param.put("param", paramDetail);
    	//JSONObject ttt = post("http://api.yi-ex.com/createInventory", null, param, "1");
    	//String ttt = post("http://api.yi-ex.com/createInventory", null, param, "1", "2");
    	String ttt = post("http://localhost:8080/haierp1/tpl/youkeP4CallBack", null, param, "1", "2");
    	System.out.println(ttt);*/
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	Map<String, Object> paramDetail = new HashMap<String, Object>();
    	paramDetail.put("Key", "YTKEY001");
    	paramDetail.put("Action", "Tracking");
    	paramDetail.put("Tracking_no", "YT00000001");
    	param.put("json", JSONObject.fromObject(paramDetail).toString());
    	String ttt = post("http://api.yuntongexpress.cn/api.ashx", null, param, "2", "2");
    	System.out.println(ttt);
	}
}
