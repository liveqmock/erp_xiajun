package com.wangqin.globalshop.item.app.service;


import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;

public interface IAppletConfigService {

    AppletConfigDO queryWxMallConfigInfoByCompanyNo(String companyNo, String appletType);

    /**
     * 新增 Applet Config
     *
     * @param appletConfigDO
     */
    void addAppletConfig(AppletConfigDO appletConfigDO);
}
