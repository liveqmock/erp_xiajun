package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DbMigrateSendRecordDO;

public interface DbMigrateSendRecordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DbMigrateSendRecordDO record);

    int insertSelective(DbMigrateSendRecordDO record);

    DbMigrateSendRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DbMigrateSendRecordDO record);

    int updateByPrimaryKey(DbMigrateSendRecordDO record);
}