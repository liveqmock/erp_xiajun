package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCommisionApplyDO;

public interface MallCommisionApplyDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallCommisionApplyDO record);

    int insertSelective(MallCommisionApplyDO record);

    MallCommisionApplyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallCommisionApplyDO record);

    int updateByPrimaryKey(MallCommisionApplyDO record);
}