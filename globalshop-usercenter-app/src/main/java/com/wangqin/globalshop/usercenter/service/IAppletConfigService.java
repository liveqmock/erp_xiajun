package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;

import java.util.List;

/**
 *
 * @author biscuits
 *
 */
public interface IAppletConfigService {


	void insert(AppletConfigDO applet);

	AppletConfigDO selectByCompanyNoAndType(String loginUserCompanyNo, String appletType);

    List<AppletConfigDO> list();

	void update(AppletConfigDO applet);

	AppletConfigDO selectByAppid(String appid);
}