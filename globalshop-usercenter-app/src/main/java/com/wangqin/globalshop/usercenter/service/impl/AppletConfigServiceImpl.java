package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AppletConfigDOMapperExt;
import com.wangqin.globalshop.usercenter.service.IAppletConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *	@author biscuits
 * 小程序配置类
 *
 */
@Service
public class AppletConfigServiceImpl implements IAppletConfigService {
	@Autowired
	private AppletConfigDOMapperExt mapper;

	@Override
	public void insert(AppletConfigDO applet) {
		mapper.insertSelective(applet);

	}

	@Override
	public AppletConfigDO selectByCompanyNoAndType(String companyNo,String type) {
		return mapper.selectByCompanyNoAndType(companyNo,type);
	}
}