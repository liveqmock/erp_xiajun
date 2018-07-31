package com.wangqin.globalshop.schedule.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/api/schedule/test", method = RequestMethod.GET)
    @ResponseBody
    public String job(){
        return "hello schedule" ;
    }


}
