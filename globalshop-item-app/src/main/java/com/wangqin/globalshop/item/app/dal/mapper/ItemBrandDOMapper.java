package com.wangqin.globalshop.item.app.dal.mapper;

<<<<<<< HEAD:globalshop-item-app/src/main/java/com/wangqin/globalshop/item/app/dal/mapper/ItemBrandDOMapper.java
import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.wangqin.globalshop.item.app.dal.dataObject.ItemBrandDO;


public interface ItemBrandDOMapper{
=======
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;

public interface ItemBrandDOMapper {
>>>>>>> 6e241ab7ac64bf8e35b3295ebc7e1a8fde158966:globalshop-biz1-app/src/main/java/com/wangqin/globalshop/biz1/app/dal/mapper/ItemBrandDOMapper.java
    int deleteByPrimaryKey(Long id);

    int insert(ItemBrandDO record);

    int insertSelective(ItemBrandDO record);

    ItemBrandDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemBrandDO record);

    int updateByPrimaryKey(ItemBrandDO record);
}