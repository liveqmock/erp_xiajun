package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemOperateDO;

public interface JdItemOperateDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(JdItemOperateDO record);

    int insertSelective(JdItemOperateDO record);

    JdItemOperateDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdItemOperateDO record);

    int updateByPrimaryKeyWithBLOBs(JdItemOperateDO record);

    int updateByPrimaryKey(JdItemOperateDO record);
}
