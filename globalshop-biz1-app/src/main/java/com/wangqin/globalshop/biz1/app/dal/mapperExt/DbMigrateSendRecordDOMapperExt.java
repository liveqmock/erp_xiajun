package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DbMigrateSendRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.DbMigrateSendRecordDOMapper;

public interface DbMigrateSendRecordDOMapperExt extends DbMigrateSendRecordDOMapper{
    
	List<DbMigrateSendRecordDO> queryDbMigrateSendRecordByStatusRetryTimes();
	
	void updateDbMigrateSendRecordById(@Param("status")String status,@Param("id")Long id);
}