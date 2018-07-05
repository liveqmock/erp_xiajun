package com.wangqin.globalshop.item.app.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.DbMigrateReceiveRecordDOMapperExt;
import com.wangqin.globalshop.item.app.service.IDbMigrateReceiveRecordService;

@Service
public class DbMigrateReceiveRecordServiceImpl implements IDbMigrateReceiveRecordService{

	@Autowired
	private DbMigrateReceiveRecordDOMapperExt dbMigrateReceiveRecordDOMapperExt;
	
	@Override
	public Integer queryDbMigrateReceiveRecordCountByToken(String token) {
		return dbMigrateReceiveRecordDOMapperExt.queryDbMigrateReceiveRecordCountByToken(token);
	}
	
}
