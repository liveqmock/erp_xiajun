package com.wangqin.shopkeeper.web.controller;



import com.wangqin.shopkeeper.biz1.api.AppApi;
import com.wangqin.shopkeeper.biz1.api.dto.ConfigDTO;
import com.wangqin.shopkeeper.biz1.api.dto.request.AppRequest;
import com.wangqin.shopkeeper.biz1.api.dto.response.BaseResp;
import com.wangqin.shopkeeper.common.utils.LogWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
}
