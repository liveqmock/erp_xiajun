package com.wangqin.globalshop.biz1.app.service;


import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.api.AppApi;
import com.wangqin.globalshop.biz1.api.dto.ConfigDTO;
import com.wangqin.globalshop.biz1.api.dto.request.AppRequest;
import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;


/**
 * Created by Patrick on 2018/5/15.
 */
@Service
public class AppApiImpl implements AppApi {
    @Override
    public BaseResp<ConfigDTO> getConfig(AppRequest request) {
        BaseResp<ConfigDTO> baseResp = null;
        return baseResp;
    }
}
