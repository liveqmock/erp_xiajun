package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;

/**
 * Create by 777 on 2018/5/29
 */
public interface ItemSkuDOMapperExt extends ItemSkuDOMapper {

	public ItemSkuDO queryPo(ItemSkuDO itemSkuDO);

	public List<ItemSkuDO> queryPoList(ItemSkuDO itemSkuDO);

	public Integer queryPoCount(ItemSkuDO itemSkuDO);

	public List<ItemSkuDO> queryItemSkusForItemThirdSale(Long id);



}
