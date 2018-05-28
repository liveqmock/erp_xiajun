package com.wangqin.globalshop.web.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangqin.globalshop.biz1.api.AppApi;
import com.wangqin.globalshop.biz1.api.dto.ConfigDTO;
import com.wangqin.globalshop.biz1.api.dto.request.AppRequest;
import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;
import com.wangqin.globalshop.common.utils.LogWorker;

import lombok.extern.slf4j.Slf4j;


/**
 * Created by patrick on 2015/6/17.
 */
@RestController
@Slf4j
public class AppRestfulController {
    @Autowired
    AppApi appApi;
    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    public BaseResp getConfig(@PathVariable String id)  {
        LogWorker.logStart(log,"配置","id:{}",id);
        AppRequest request = new AppRequest();
        request.setId(id);
        BaseResp<ConfigDTO> resp = appApi.getConfig(request);
        LogWorker.logEnd(log,"配置","response:{}",resp);
        return resp;
    }


    @RequestMapping(value = "/config/validate", method = RequestMethod.POST)
    public BaseResp validate(@RequestBody @Valid AppRequest appRequest, BindingResult result){
        LogWorker.logStart(log,"配置","appRequest:{}",appRequest);

        if(result.hasErrors()){
            StringBuffer sb = new StringBuffer();
            for (ObjectError error : result.getAllErrors()) {
                sb.append(error.getDefaultMessage()).append(",");
            }
            return BaseResp.createFailure(sb.toString());
        }

        BaseResp resp =BaseResp.createSuccess("");
        LogWorker.logEnd(log,"配置","response:{}",resp);

        return resp;
    }
}
