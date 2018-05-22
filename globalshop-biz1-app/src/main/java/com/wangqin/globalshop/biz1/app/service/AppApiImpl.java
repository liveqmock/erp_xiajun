package com.wangqin.globalshop.biz1.app.service;


import com.wangqin.globalshop.biz1.api.AppApi;
import com.wangqin.globalshop.biz1.api.dto.ConfigDTO;
import com.wangqin.globalshop.biz1.api.dto.request.AppRequest;
import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ConfigDOMapperExt;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.LogWorker;
import com.wangqin.globalshop.common.utils.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Patrick on 2018/5/15.
 */
@Service
@Slf4j
public class AppApiImpl implements AppApi {
    @Resource
    ConfigDOMapperExt configDOMapperExt;
    @Override
    public BaseResp<ConfigDTO> getConfig(AppRequest request) {
        BaseResp<ConfigDTO> baseResp = null;
        LogWorker.logEnd(log,"配置查询","request:{}",request);

        try {
            ValidateUtils.fail4Null(request, "request");
            ValidateUtils.fail4Null(request.getId(), "request ID");
            ConfigDO configDO = configDOMapperExt.selectByPrimaryKey(Long.valueOf(request.getId()));
            ConfigDTO configDTO = BeanUtils.copies(configDO, new ConfigDTO());
           
            baseResp = BaseResp.createSuccess(configDTO);

        }catch (Exception e){
            baseResp = BaseResp.createFailure(e.getMessage());
        }
        LogWorker.logEnd(log,"配置查询","baseResp:{}",baseResp);
        return baseResp;
    }
}
