package com.wangqin.globalshop.item.app.service.impl;

import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AppletConfigDOMapperExt;
import com.wangqin.globalshop.item.app.service.IAppletConfigService;

@Service
public class AppletConfigServiceImpl implements IAppletConfigService {

    @Autowired
    private AppletConfigDOMapperExt appletConfigDOMapper;

    @Override
    public AppletConfigDO queryWxMallConfigInfoByCompanyNo(String companyNo, String appletType) {
        return appletConfigDOMapper.queryWxMallConfigInfoByCompanyNo(companyNo, appletType);
    }

    @Override
    public void addAppletConfig(AppletConfigDO appletConfigDO) {
        try {
            appletConfigDOMapper.insertSelective(appletConfigDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("新增 Applet Config 失败！");
        }
    }

    @Override
    public void updateAppletConfig(AppletConfigDO appletConfigDO) {
        if (appletConfigDO.getCompanyNo() == null) {
            throw new BizCommonException("数据不完整！");
        }

        int effectedNum = appletConfigDOMapper.updateByCompanyNo(appletConfigDO);

        if (effectedNum <= 0) {
            throw new BizCommonException("数据库中无此记录！");
        }
    }
}
