package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DbMigrateReceiveRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.DbMigrateReceiveRecordDOMapper;

public interface DbMigrateReceiveRecordDOMapperExt extends DbMigrateReceiveRecordDOMapper{
   
	Integer queryDbMigrateReceiveRecordCountByToken(String token);
	
}