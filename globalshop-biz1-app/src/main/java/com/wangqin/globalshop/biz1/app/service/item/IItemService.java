package com.wangqin.globalshop.biz1.app.service.item;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;

import java.util.List;

/**
 *
 * Item 表数据服务层接口
 *
 */
public interface IItemService  {

	//公共部分
	public int deleteByPrimaryKey(Long id);

	public int insert(ItemDO record);

	public int insertSelective(ItemDO record);

	public ItemDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ItemDO record);

	public int updateByPrimaryKeyWithBLOBs(ItemDO record);

	public int updateByPrimaryKey(ItemDO record);


    //自定义
	public List<ItemDO> selectBatchIds(List<Long> idList);

	public void updateBatchById(List<ItemDO> itemDOList);



}
