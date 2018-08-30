package com.wangqin.globalshop.item.api.util;



import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;

public interface AppletConfigService {

    @RequestMapping(value = "/config/queryWxMallConfigInfoByCompanyNo", method = RequestMethod.POST)
    AppletConfigDO queryWxMallConfigInfoByCompanyNo(@RequestParam("companyNo") String companyNo, @RequestParam("appletType") String appletType);

    /**
     * 新增 Applet Config
     *
     * @param appletConfigDO
     */
    @RequestMapping(value = "/config/addAppletConfig", method = RequestMethod.POST)
    void addAppletConfig(@RequestBody AppletConfigDO appletConfigDO);

    /**
     * 更新 Applet Config
     *
     * @param appletConfigDO
     */
    @RequestMapping(value = "/config/updateAppletConfig", method = RequestMethod.POST)
    void updateAppletConfig(@RequestBody AppletConfigDO appletConfigDO);
}
