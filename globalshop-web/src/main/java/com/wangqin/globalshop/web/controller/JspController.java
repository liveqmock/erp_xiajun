package com.wangqin.globalshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Patrick
 * @date 2016-12-14
 */
@Controller
@RequestMapping(value = "/jsp")
public class JspController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String doSomething() {
        return "test";
    }

}
