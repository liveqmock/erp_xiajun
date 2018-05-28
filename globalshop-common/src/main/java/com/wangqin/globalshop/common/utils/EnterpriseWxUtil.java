package com.wangqin.globalshop.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class EnterpriseWxUtil {
	
	public static void sendMessage(ByteArrayOutputStream byteArrayOutputStream ,String fileName) {
		JSONObject jsonObjectAccessToken = HttpClientUtil.post(
				"https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww2f625e375ae82faa&corpsecret=klQsIqsjZq8iyW7cxO0wcSlkL2bZR5C5Wuj3bZFV8RU",
				null, null, "1");
		String accessToken = jsonObjectAccessToken.getString("access_token");
		//System.out.println(accessToken);
		//String filePath = "C:/Users/Administrator/Desktop/app需求管理文档.xlsx";
		String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=" + accessToken + "&type=file";
		String result = null;
		try {
			result = send(sendUrl, byteArrayOutputStream,fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(result);
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> param1 = new HashMap<String, Object>();
		//param.put("totag", "2");
		//param.put("touser", "BaoYinLei");
		param.put("toparty", "4");
		param.put("msgtype", "file");
		param.put("agentid", 1000002);
		param.put("file", param1);
		param1.put("media_id", jsonObj.get("media_id"));
		JSONObject jsonObject = HttpClientUtil
				.post("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken, null, param, "1");
		System.out.println(jsonObject);
	}

	public static String send(String url, ByteArrayOutputStream byteArrayOutputStream,String fileName) throws Exception {

		String result = null;
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fileName+".xlsx" + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		//DataInputStream in = new DataInputStream(new FileInputStream(file));
		ByteArrayInputStream input = new ByteArrayInputStream(byteArrayOutputStream.toByteArray()); 
		DataInputStream in = new DataInputStream(input);
		
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return result;
	}
}
