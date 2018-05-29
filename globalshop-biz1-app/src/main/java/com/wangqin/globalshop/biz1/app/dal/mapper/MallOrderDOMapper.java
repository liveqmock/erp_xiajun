package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;

public interface MallOrderDOMapper extends AutoMapper<MallOrderDO> {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderDO record);

    int insertSelective(MallOrderDO record);

    MallOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallOrderDO record);

    int updateByPrimaryKey(MallOrderDO record);
}