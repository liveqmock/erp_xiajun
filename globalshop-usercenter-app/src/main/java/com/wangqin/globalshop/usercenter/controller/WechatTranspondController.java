package com.wangqin.globalshop.usercenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author biscuit
 * @data 2018/07/25
 */
@Controller
@ResponseBody
@RequestMapping("/forward")
public class WechatTranspondController {

    private static final String baseUrl = "http://test.buyer007.cn";

    @RequestMapping("/login")
    public void login(String code, HttpServletResponse response) {
        String trueUrl = baseUrl + "/#/LoginTest?code=" + code;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/authorized")
    public void authorized(String code,HttpServletResponse response) {
        String trueUrl = baseUrl + "/#/permission/test?code=" + code;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
