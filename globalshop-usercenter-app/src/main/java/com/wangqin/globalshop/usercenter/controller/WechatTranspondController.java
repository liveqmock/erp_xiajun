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

    private static final String testUrl = "http://test.buyer007.cn";
    private static final String devUrl = "http://dev.buyer007.cn";

    @RequestMapping("/test/login")
    public void loginTest(String code, HttpServletResponse response) {
        String trueUrl = testUrl + "/#/LoginTest?code=" + code;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/test/authorized")
    public void authorizedTest(String code, String state, HttpServletResponse response) {
        String trueUrl = testUrl + "/#/permission/test?code=" + code + "&state=" + state;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/dev/login")
    public void loginDev(String code, HttpServletResponse response) {
        String trueUrl = devUrl + "/#/LoginTest?code=" + code;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/test/proxy")
    public void testProxy(String code, String state, HttpServletResponse response) {
        String trueUrl = testUrl + "/#/Order/AgentTest?code=" + code + "&state=" + state;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/dev/proxy")
    public void devProxy(String code,String state, HttpServletResponse response) {
        String trueUrl = devUrl + "/#/Order/AgentTest?code=" + code + "&state=" + state;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/dev/authorized")
    public void authorizedDev(String code, String state, HttpServletResponse response) {
        String trueUrl = devUrl + "/#/permission/test?code=" + code + "&state=" + state;
        try {
            response.sendRedirect(trueUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/devGetCode")
    public void dev(String trueUrl, String code, HttpServletResponse response) {
        trueUrl = trueUrl.replace("$", "/");
        String url = devUrl + "#" + trueUrl + "?code=" + code;
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/testGetCode")
    public void test(String trueUrl, String code, HttpServletResponse response) {
        String url = testUrl + "#" + trueUrl + "?code=" + code;
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
