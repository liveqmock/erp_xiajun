package com.wangqin.globalshop.common.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

public class HttpPostUtil {

	private static final int TIMEOUT = 15000;

//	public static String postRaw(String url, String jsonStr){
//		String result = "";
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpPost post = new HttpPost(url);
//		StringEntity postingString = new StringEntity(json);// json传递
//		post.setEntity(postingString);
//		post.setHeader("Content-type", "application/json");
//		HttpResponse response = httpClient.execute(post);
//		String content = EntityUtils.toString(response.getEntity());
//		return result;
//	}


	public static String doHttpPost(String url, Map<String, String> params){
		String result = "";
		try {
			result = doPost(url, params, "UTF-8");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	public static String doPost(String url, Map<String, String> params, String encode) throws Exception {
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		byte[] content = spliceParams(params, encode).getBytes(encode);
		String param = content.toString();
		System.out.println(url);
		System.out.println(param);
		try {
			URL endpoint = new URL(url);
//			conn = getConnection(endpoint, "POST", "text/html; charset=" + encode, null);
			conn = (HttpURLConnection) endpoint.openConnection();
			conn.setConnectTimeout(TIMEOUT);
			conn.setReadTimeout(TIMEOUT);
			conn.setDoOutput(true);
			out = conn.getOutputStream();
			out.write(content);
			rsp = getResponseAsString(conn);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}

	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			Reader reader = new InputStreamReader(stream, charset);
			StringBuilder response = new StringBuilder();

			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		} else {
			String msg = getStreamAsString(es, charset);
			if (msg != null && !"".equals(msg.trim())) {
				throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}

	private static String getResponseCharset(String ctype) {
		String charset = "UTF-8";

		if (ctype != null && !"".equals(ctype.trim())) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (pair[1] != null && !"".equals(pair[1].trim())) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

	private static HttpURLConnection getConnection(URL url, String method, String ctype, Map<String, String> headerMap) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript");
		conn.setRequestProperty("Content-Type", ctype);
		if (headerMap != null) {
			for (Entry<String, String> entry : headerMap.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		return conn;
	}

	private static String spliceParams(Map<String, String> params, String encode) throws UnsupportedEncodingException {

		if (params == null || params.isEmpty()) {
			return "";
		}

		StringBuffer paramsString = new StringBuffer("");
		for (Entry<String, String> e:params.entrySet()) {
			paramsString.append(e.getKey()).append("=").append(URLEncoder.encode(e.getValue(), encode)).append("&");
		}
		return paramsString.substring(0, paramsString.length() - 1);
	}
}
