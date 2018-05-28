package com.wangqin.globalshop.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class DimensionCodeUtil {
	public static String youZanToken;
	public static String clientId;
	public static String clientSecret;
	public static String kdtId;
	
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public static void setClientKey(String clientIdP, String clientSecretP, String kdtIdP) {
		clientId = clientIdP;
		clientSecret = clientSecretP;
		kdtId = kdtIdP;
	}
	
	// 获取有赞token 
	public static void getYouZanToken(){
		String  urlresponse = DimensionCodeUtil.sendGet("https://open.youzan.com/oauth/token", "client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=silent&kdt_id=" + kdtId);
		JSONObject jsonObject = JSONObject.fromObject(urlresponse);
		youZanToken = jsonObject.getString("access_token");
		System.out.println("如花的账号，这里已经废弃。 第一次获取youZanToken:" + youZanToken);
	}
}