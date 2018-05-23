package com.wangqin.globalshop.item.app.dal.mapper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.wangqin.globalshop.item.app.dal.dataObject.ItemBrandDO;


public interface ItemBrandDOMapper{
    int deleteByPrimaryKey(Long id);

    int insert(ItemBrandDO record);

    int insertSelective(ItemBrandDO record);

    ItemBrandDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemBrandDO record);

    int updateByPrimaryKey(ItemBrandDO record);
}