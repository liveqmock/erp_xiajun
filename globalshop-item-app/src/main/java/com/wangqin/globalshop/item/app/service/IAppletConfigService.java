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

    /**
     * 更新 Applet Config
     *
     * @param appletConfigDO
     */
    void updateAppletConfig(AppletConfigDO appletConfigDO);
}
