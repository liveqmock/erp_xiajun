package com.wangqin.globalshop.usercenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author biscuit
 * @data 2018/07/25
 */
@Controller
public class WechatTranspondController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
}
