package com.wangqin.globalshop.order.app.controller.kuaidi100;

import com.wangqin.globalshop.order.app.notice_bean.NoticeResponse;
import com.wangqin.globalshop.order.app.service.kuaidi100.IKuaidi100Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("kuaidi100")
public class Kuaidi100ExpressController
{
	@Autowired
	private IKuaidi100Service kuaidi100Service;

	@RequestMapping(value = "callback", method = RequestMethod.POST)
	public void expressCallback(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		NoticeResponse ret = kuaidi100Service.handleCallback(request.getParameter("param"));
		try
		{
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(ret.toJson());
		}
		catch (IOException e)
		{
			ret.setResult(false);
			ret.setMessage("回写失败");
			response.getWriter().print(ret.toJson());
		}
	}
}
