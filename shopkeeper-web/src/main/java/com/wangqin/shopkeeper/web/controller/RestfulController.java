package com.wangqin.shopkeeper.web.controller;


import com.wangqin.shopkeeper.common.utils.LogWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by patrick on 2015/6/17.
 */
@RestController
@Slf4j
public class RestfulController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index()  {
        LogWorker.log(log,"健康检查","");
        return "I'm OK!";
    }
}
