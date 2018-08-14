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

    List<AppletConfigDO> selectByPublishStatus(int code);

	/**
	 * 不含token等敏感信息
	 * @param type
	 * @return
	 */
	List<AppletConfigDO> selectByType(String type);

	List<String> publish(String appids, Integer templateId, String userDesc, String userVersion);
}