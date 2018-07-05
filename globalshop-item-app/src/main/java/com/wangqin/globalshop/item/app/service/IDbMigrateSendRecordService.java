package com.wangqin.globalshop.item.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DbMigrateSendRecordDO;

public interface IDbMigrateSendRecordService {

    List<DbMigrateSendRecordDO> queryDbMigrateSendRecordByStatusRetryTimes();
	
	void updateDbMigrateSendRecordById(String status,Long id);
}
