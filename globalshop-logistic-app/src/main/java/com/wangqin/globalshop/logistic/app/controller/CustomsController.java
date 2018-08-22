package com.wangqin.globalshop.logistic.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 海关相关 controller
 *
 * @author angus
 * @date 2018/8/22
 */
@RestController
@RequestMapping("/customs")
public class CustomsController {

    @PostMapping("/result")
    public void result(String content) {

    }


}
