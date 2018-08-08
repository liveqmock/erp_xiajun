package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemQrcodeShareDO;

public interface ItemQrcodeShareDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemQrcodeShareDO record);

    int insertSelective(ItemQrcodeShareDO record);

    ItemQrcodeShareDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemQrcodeShareDO record);

    int updateByPrimaryKey(ItemQrcodeShareDO record);
}