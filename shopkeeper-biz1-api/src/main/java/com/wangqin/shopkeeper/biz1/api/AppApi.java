package com.wangqin.shopkeeper.biz1.api;


import com.wangqin.shopkeeper.biz1.api.dto.request.AppRequest;
import com.wangqin.shopkeeper.biz1.api.dto.ConfigDTO;
import com.wangqin.shopkeeper.biz1.api.dto.response.BaseResp;

/**
 * Created by Patrick on 2018/5/15.
 */
public interface AppApi {
    public BaseResp<ConfigDTO> getConfig(AppRequest request);
}
