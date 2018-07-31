package com.wangqin.globalshop.order.app.util;

import com.wangqin.globalshop.common.utils.StringUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author chendong
 * @date 
 *
 */
public class HttpClient
{
	public static class HttpResponse
	{
		private byte[] data = null;
		private Map<String, List<String>> headers;
		
		public void setData(byte[] data)
		{
			this.data = data;
		}
		
		public byte[] getData()
		{
			return data;
		}
		
		public Map<String, List<String>> getHeaders()
		{
			return headers;
		}
		
		public void setHeaders(Map<String, List<String>> headers)
		{
			this.headers = headers;
		}
		
		public String getStringResult()
		{
			return getStringResult("utf-8");
		}
		
		public String getStringResult(String charset)
		{
			try
			{
				return new String(this.data, charset);
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		
		public InputStream getStreamResult()
		{
			return new ByteArrayInputStream(this.data);
		}
	}
	
	protected static Logger logger = Logger.getLogger("SimpleHttpClient");
	
	public static HttpResponse httpGet(String urlStr, Map<String, String> headers)
	{
		HttpResponse response = new HttpResponse();
		
		logger.debug("--->开始get请求:" + urlStr);
		
		URL url = null;
		try
		{
			url = new URL(urlStr);
		}
		catch (MalformedURLException e)
		{
			logger.error("--->urlStr不合法", e);
		}
		
		URLConnection conn = null;
		try
		{
			conn = url.openConnection();
		}
		catch (IOException e)
		{
			logger.error("--->url打开连接出错", e);
		}
		
		HttpURLConnection httpConn = (HttpURLConnection) conn;
		
		try
		{
			if (null != headers)
			{
				applyHeaders(httpConn, headers);
			}
			httpConn.setUseCaches(false);
			httpConn.setConnectTimeout(10000);
			httpConn.connect();
			
			response.setHeaders(httpConn.getHeaderFields());
		}
		catch (IOException e)
		{
			logger.error("--->httpConn建立连接出错", e);
		}
		
		byte[] data = readResponseData(httpConn);
		logger.debug("--->完成get请求:" + urlStr);
		
		response.setData(data);
		
		return response;
	}
	
	public static HttpResponse httpGet(String urlStr)
	{
		return httpGet(urlStr, null);
	}
	
	public static HttpResponse httpPost(String urlStr, Map<String, String> params, Map<String, String> headers)
	{
		HttpResponse response = new HttpResponse();
		
		logger.debug("--->开始post请求:" + urlStr);
		URL url = null;
		try
		{
			url = new URL(urlStr);
		}
		catch (MalformedURLException e)
		{
			logger.error("--->urlStr不合法", e);
		}
		
		URLConnection conn = null;
		try
		{
			conn = url.openConnection();
		}
		catch (IOException e)
		{
			logger.error("--->url打开连接出错", e);
		}
		
		HttpURLConnection httpConn = (HttpURLConnection) conn;
		try
		{
			if (null != headers)
			{
				applyHeaders(httpConn, headers);
			}
			httpConn.setConnectTimeout(10000);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setChunkedStreamingMode(5);
			httpConn.connect();
			
			DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
			StringBuffer content = new StringBuffer();
			Set<Entry<String, String>> set = params.entrySet();
			for (Entry<String, String> me : set)
			{
				content.append(me.getKey() + "=" + URLEncoder.encode(me.getValue(), "utf-8")).append("&");
			}
			
			out.writeBytes(content.toString());
			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			logger.error("--->httpConn建立连接出错", e);
		}
		
		response.setHeaders(httpConn.getHeaderFields());
		
		byte[] data = readResponseData(httpConn);
		logger.debug("--->完成post请求:" + urlStr);
		response.setData(data);
		
		return response;
	}
	
	public static String postJson(String url,String jsonData, Map<String,String>headerMap){
		String result = null ;
		if(StringUtil.isBlank(url)||StringUtil.isBlank(jsonData)){
			logger.error("post数据不能为空！") ;
		}
		byte[] data = jsonData.getBytes() ;
		try{
			URL postUrl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) postUrl.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestProperty("Content-Type", "text/json; charset=utf-8");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("Charset", "UTF-8");
			httpConn.setRequestProperty("Content-Length",String.valueOf(data.length)) ;
			if (headerMap != null) {
				for (Entry<String,String> entry : headerMap.entrySet()) {
					httpConn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			
			//httpConn.setChunkedStreamingMode(5);
			httpConn.setConnectTimeout(5*1000);
			httpConn.connect();
			PrintWriter out = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream())) ;
			out.print(jsonData) ;
			out.flush();
			out.close();
			int resultCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb = new StringBuffer();
				String line = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((line = responseReader.readLine()) != null) {
					sb.append(line);
				}
				responseReader.close();
				String responseString = sb.toString() ;
				//return responseString.replaceAll("null", "\"null\"") ;
				return responseString ;
			} else {
				logger.error("请求失败[errorCode:" + resultCode  + "','data':'" + jsonData+ "'");
			}
		}catch(Exception e){
			logger.error("postJson异常",e);
		}
		return result ;
	}
	
	
	public static HttpResponse httpPost(String urlStr, Map<String, String> params)
	{
		return httpPost(urlStr, params, null);
	}
	
	private static void applyHeaders(HttpURLConnection httpConn, Map<String, String> headers)
	{
		Set<Entry<String, String>> set = headers.entrySet();
		for (Entry<String, String> me : set)
		{
			httpConn.setRequestProperty(me.getKey(), me.getValue());
		}
	}
	
	private static byte[] readResponseData(HttpURLConnection httpConn)
	{
		logger.debug("--->开始处理数据");
		
		ByteArrayOutputStream baos = null;
		InputStream in = null;
		byte[] data = null;
		try
		{
			byte[] buffer = new byte[1024];
//			HttpURLConnection.HTTP_OK， HttpURLConnection.HTTP_CREATED， HttpURLConnection.HTTP_ACCEPTED
			int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK || 
					responseCode == HttpURLConnection.HTTP_CREATED || 
					responseCode == HttpURLConnection.HTTP_ACCEPTED  ) {
				in = httpConn.getInputStream();
			} else {
				in = httpConn.getErrorStream();
			}
			
			baos = new ByteArrayOutputStream();
			int len = 0;
			while ((len = in.read(buffer)) != -1)
			{
				baos.write(buffer, 0, len);
			}
			data = baos.toByteArray();
			
		}
		catch (IOException e)
		{
			logger.error("--->处理数据出错", e);
		}
		finally
		{
			try
			{
				if (null != in) {
                    in.close();
                }
				if (null != baos) {
                    baos.close();
                }
				httpConn.disconnect();
			}
			catch (IOException e)
			{
				logger.error("--->关闭流出错", e);
			}
		}
		logger.debug("--->完成处理数据");
		return data;
	}
}
