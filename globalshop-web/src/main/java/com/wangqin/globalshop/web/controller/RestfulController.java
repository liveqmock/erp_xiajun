package com.wangqin.globalshop.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangqin.globalshop.common.utils.LogWorker;



/**
 * Created by patrick on 2015/6/17.
 */
@RestController
public class RestfulController {
    protected static Logger log = LoggerFactory.getLogger("System");
    @RequestMapping(value = "/restful", method = RequestMethod.GET)
    public String index()  {
        LogWorker.log(log,"健康检查","");
        return "I'm OK!";
    }
}
