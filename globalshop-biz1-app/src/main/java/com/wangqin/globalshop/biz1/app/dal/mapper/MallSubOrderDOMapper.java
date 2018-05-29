package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

public interface MallSubOrderDOMapper extends AutoMapper<MallSubOrderDO> {
    int deleteByPrimaryKey(Long id);

    int insert(MallSubOrderDO record);

    int insertSelective(MallSubOrderDO record);

    MallSubOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSubOrderDO record);

    int updateByPrimaryKey(MallSubOrderDO record);
}