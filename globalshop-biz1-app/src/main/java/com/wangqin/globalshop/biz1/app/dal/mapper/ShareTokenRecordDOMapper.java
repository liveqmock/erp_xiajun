package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShareTokenRecordDO;

public interface ShareTokenRecordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShareTokenRecordDO record);

    int insertSelective(ShareTokenRecordDO record);

    ShareTokenRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShareTokenRecordDO record);

    int updateByPrimaryKey(ShareTokenRecordDO record);
}