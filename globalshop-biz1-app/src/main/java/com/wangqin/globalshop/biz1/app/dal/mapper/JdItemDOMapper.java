package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;

public interface JdItemDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdItemDO record);

    int insertSelective(JdItemDO record);

    JdItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdItemDO record);

    int updateByPrimaryKeyWithBLOBs(JdItemDO record);

    int updateByPrimaryKey(JdItemDO record);
}