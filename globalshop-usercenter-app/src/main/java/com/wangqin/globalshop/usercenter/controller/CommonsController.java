package com.wangqin.globalshop.usercenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.wangqin.globalshop.usercenter.app.commons.utils.CaptchaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 通用的控制器
 *
 */
@Controller
public class CommonsController {

    /**
     * 图标页
     */
    @GetMapping("icons.html")
    public String icons() {
        return "icons";
    }
	
    /**
     * 图形验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
//        CaptchaUtils.generate(request, response);
    }
}
