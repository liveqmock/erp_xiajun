package com.wangqin.globalshop.item.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AppletConfigDOMapperExt;
import com.wangqin.globalshop.item.app.service.IAppletConfigService;

@Service
public class AppletConfigServiceImpl implements IAppletConfigService{

	@Autowired
	private AppletConfigDOMapperExt appletConfigDOMapperExt;
	
	@Override
	public AppletConfigDO queryWxMallConfigInfoByCompanyNo(String companyNo,String appletType) {
		return appletConfigDOMapperExt.queryWxMallConfigInfoByCompanyNo(companyNo, appletType);
	}
}
