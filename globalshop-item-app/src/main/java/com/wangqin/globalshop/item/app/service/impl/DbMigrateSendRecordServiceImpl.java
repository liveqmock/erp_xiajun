package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DbMigrateSendRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.DbMigrateSendRecordDOMapperExt;
import com.wangqin.globalshop.item.app.service.IDbMigrateSendRecordService;

@Service
public class DbMigrateSendRecordServiceImpl implements IDbMigrateSendRecordService{

	@Autowired
	private DbMigrateSendRecordDOMapperExt dbMigrateSendRecordDOMapperExt;
	
	@Override
    public List<DbMigrateSendRecordDO> queryDbMigrateSendRecordByStatusRetryTimes() {
		return dbMigrateSendRecordDOMapperExt.queryDbMigrateSendRecordByStatusRetryTimes();
	}
	
	@Override
    public void updateDbMigrateSendRecordById(String status,Long id) {
		dbMigrateSendRecordDOMapperExt.updateDbMigrateSendRecordById(status, id);
	}
}
