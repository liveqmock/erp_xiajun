package com.wangqin.globalshop.channel.controller.taobao;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.channelAccountConfig.IChannelAccountConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Controller
@RequestMapping("/jd")
public class JDAccountInfoController {
			
	@Autowired
	IChannelAccountService channelAccountService;

	@Autowired
	IChannelAccountConfigService channelAccountConfigService;
						


	@RequestMapping("/accountInfo")
	public void accountInfo(String code, String state) throws IOException, ServletException {

		// 用来获取token的CODE
		String tokenCode = code;
//		if (tokenCode == null) {
//			request.setAttribute("errorNote", "京东提供的<font color='red'>授权码</font>不正确!<br/>请重新返回京东，重新授权，谢谢!");
//			request.getRequestDispatcher("error.jsp").forward(request, response);
//			return;
//		}

		// 浏览器交互时，可能会把'+'换成' ',所以要把' '换成'+'
		if (state != null) {
			state = state.replaceAll(" ", "+");
		}
		System.out.println(state);


		// 下面要获取token
		String url = "https://oauth.jd.com/oauth/token?grant_type=authorization_code&client_id=" + "96C38E0AAAA47520B6211D32A5A14EDE" + "&client_secret=" + "758ae3185aec4822aa5593fda0aa9b98"
				+ "&scope=read&redirect_uri=http://" + "https://erp.buyer007.cn" + "&code=" + tokenCode + "&state="
				+ state;
		URL uri = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestMethod("POST");
		int refreshCode = conn.getResponseCode();
		InputStream is = conn.getInputStream();
		String jsonStr = inputStream2String(is, conn.getContentType());
		System.out.println("jsonStr");


		ChannelAccountDO channelAccount = new ChannelAccountDO();

		//渠道信息
		channelAccount.setChannelId(Long.valueOf(ChannelType.TaoBao.getValue()));
		channelAccount.setChannelNo(ChannelType.TaoBao.getValue()+"");
		channelAccount.setType(ChannelType.TaoBao.getValue());
		channelAccount.setChannelName(ChannelType.TaoBao.getName());

		//company信息，所属域
		channelAccount.setCompanyNo(state);

		//授权信息
		channelAccount.setShopCode(code);
		channelAccount.setShopName(code);
		channelAccount.setCookie(jsonStr);

		//其他信息配置
		channelAccount.setStatus(0);//0正常，1关闭
		channelAccount.setIsDel(false);
		channelAccount.setCreator("-1");
		channelAccount.setGmtCreate(new Date());

		channelAccountService.insert(channelAccount);


	}

	public static String inputStream2String(InputStream is, String charset) throws IOException {
		charset = "GBK";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString(charset);
	}


	

	
	
	


}
