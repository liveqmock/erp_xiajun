package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO; /**
 *
 * @author biscuits
 *
 */
public interface IAppletConfigService {


	void insert(AppletConfigDO applet);

	AppletConfigDO selectByCompanyNoAndType(String loginUserCompanyNo, String appletType);
}