package com.wangqin.globalshop.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.StringUtil;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class SimpleHtmlUnitUtils {
	public static Logger logger = LogManager.getLogger(SimpleHtmlUnitUtils.class);
	public static WebClient wc = new WebClient();
	
	static {
		wc.getCookieManager().setCookiesEnabled(true);	//开启cookie管理
        wc.getOptions().setJavaScriptEnabled(false);		//开启js解析。对于变态网页，这个是必须的
        wc.getOptions().setCssEnabled(false);			//开启css解析。对于变态网页，这个是必须的。
        wc.getOptions().setRedirectEnabled(true);		//开启302跳转
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setThrowExceptionOnScriptError(false);
        wc.getOptions().setTimeout(30000);
	}
	
	public static String httpRequest(String url, HttpMethod method, Map<String, String> headerMap, Map<String, String> param, BasicCookieStore cookieStore, String encoding)  {
		URL u = null;
		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			logger.error("error in SimpleHtmlUnitUtils ===> httpRequest method", e);
			return null;
		}
		WebRequest request=new WebRequest(u, method);
        //设置head
        if(headerMap!=null && !headerMap.isEmpty()) {
        	for( Entry<String, String> entry : headerMap.entrySet()) {
			  	request.setAdditionalHeader(entry.getKey(), entry.getValue());
        	}
        }
        //设置参数
        if (param != null && !param.isEmpty()) {
        	List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> it = param.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> i = it.next();
				formparams.add(new NameValuePair(i.getKey(), i.getValue()));
			}
			request.setRequestParameters(formparams);
		}
        //设置编码
        if(StringUtil.isNotBlank(encoding)) {
        	request.setCharset(encoding);
        }
		
        //设置cookie，如果你有cookie，可以在这里设置
        Iterator<org.apache.http.cookie.Cookie> i = cookieStore.getCookies().iterator();
        while (i.hasNext()) {
        	org.apache.http.cookie.Cookie hCookie = i.next();
        	Cookie cookie = new Cookie(hCookie.getDomain(), hCookie.getName(), hCookie.getValue());
            wc.getCookieManager().addCookie(cookie);
        }
        
        //准备工作已经做好了
        Page page = null;
        try {
        	page = wc.getPage(request);
        	// 等待JS驱动dom完成获得还原后的网页
        	wc.waitForBackgroundJavaScript(3000);
        } catch(Exception e1) {
        	logger.error("error in SimpleHtmlUnitUtils ===> httpRequest method", e1);
        	return null;
        }
        String content = page.getWebResponse().getContentAsString();	//网页内容保存在content里
        //搞定了
        CookieManager cookieManager = wc.getCookieManager();
        Set<Cookie> cookies_ret = cookieManager.getCookies();
        Iterator<Cookie> j = cookies_ret.iterator();
        while (j.hasNext()) {
        	Cookie hCookie = j.next();
        	BasicClientCookie cookie = new BasicClientCookie(hCookie.getName(), hCookie.getValue());
        	cookie.setDomain(hCookie.getDomain());
        	cookie.setPath(hCookie.getPath());
        	cookie.setExpiryDate(hCookie.getExpires());
        	cookieStore.addCookie(cookie);
        }
		return content;
	}
	
	public static String httpGetSsl(String url) {
		String html = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpGet get = new HttpGet(url);
			HttpResponse response = httpClient.execute(get);
			html = EntityUtils.toString(response.getEntity(), "UTF-8").trim();
		} catch (Exception e) {
			//logger.error("error in SimpleHtmlUnitUtils ===> httpGetSsl method",e);
		}
		return html;
	}
	
	public static void writeObjectToFile(Object obj, String filePath) {
		File file =new File(filePath);
		FileOutputStream out;
	    try {
	    	out = new FileOutputStream(file);
	    	ObjectOutputStream objOut=new ObjectOutputStream(out);
	    	objOut.writeObject(obj);
	    	objOut.flush();
	    	objOut.close();
	    } catch (IOException e) {
	    	logger.error("error in SimpleHtmlUnitUtils ===> writeObjectToFile method", e);
	    }
	}
	
	public static Object readObjectFromFile(String filePath) {
		Object temp=null;
	    File file =new File(filePath);
	    FileInputStream in;
	    try {
	    	in = new FileInputStream(file);
	    	ObjectInputStream objIn=new ObjectInputStream(in);
	    	temp=objIn.readObject();
	    	objIn.close();
	    } catch (Exception e) {
	    	logger.error("error in SimpleHtmlUnitUtils ===> readObjectFromFile method", e);
	    }
	    return temp;
	}
}
