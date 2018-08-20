package com.wangqin.globalshop.channel.service.item;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.channelapi.dal.ItemVo;

import java.util.List;

/**
 * Item 表数据服务层接口
 */
public interface IItemService {

	//渠道商品专用：根据item_code更新商品
	void updateItemByItemCode(ItemDO itemDO);
	
	//渠道商品添加的公共接口	
	void addChannelItem(ItemVo item, Boolean needUpdate);
	
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


    public ItemVo queryItem(Long id);

    List<ItemDO> queryHaihuByUptime(ItemQueryVO ItemDO);

    ItemDO getByItemCode(String itemCode);


	public ItemVo getVoByItemCode(String itemCode);
}
