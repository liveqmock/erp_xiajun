package com.wangqin.globalshop.usercenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author biscuit
 * @data 2018/07/11
 */
@RestController
public class TxTestController {
    @RequestMapping("/4883393465")
    public String getTxt(){
        return "136bbec27d35b86aafb3cefde812a05e";
    }
}
