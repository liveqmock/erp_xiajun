package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DbMigrateReceiveRecordDO;

public interface DbMigrateReceiveRecordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DbMigrateReceiveRecordDO record);

    int insertSelective(DbMigrateReceiveRecordDO record);

    DbMigrateReceiveRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DbMigrateReceiveRecordDO record);

    int updateByPrimaryKey(DbMigrateReceiveRecordDO record);
}