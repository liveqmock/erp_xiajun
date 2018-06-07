package com.wangqin.globalshop.channel.service.item;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;

import java.util.List;

/**
 *
 * ItemSku 表数据服务层接口
 *
 */
public interface IItemSkuService  {

	public int deleteByPrimaryKey(Long id);

	public int insert(ItemSkuDO record);

	public int insertSelective(ItemSkuDO record);

	public ItemSkuDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ItemSkuDO record);

	public int updateByPrimaryKeyWithBLOBs(ItemSkuDO record);

	public int updateByPrimaryKey(ItemSkuDO record);



	public ItemSkuDO queryPo(ItemSkuDO itemSkuDO);
	public Integer queryPoCount(ItemSkuDO itemSkuDO);
	public List<ItemSkuDO> queryPoList(ItemSkuDO itemSkuDO);

	public List<ItemSkuVo> queryVoList(ItemSkuDO itemSkuDO);

	public List<ItemSkuVo> queryItemSkusForItemThirdSale(String itemCode, String shopCode);
}
