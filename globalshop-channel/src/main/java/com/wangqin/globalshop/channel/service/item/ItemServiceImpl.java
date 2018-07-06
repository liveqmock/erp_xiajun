package com.wangqin.globalshop.channel.service.item;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.common.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Item 表数据服务层接口实现类
 *
 */
@Service
public class ItemServiceImpl  implements IItemService {


	@Autowired
	private ItemDOMapperExt itemDOMapperExt;

	@Autowired
	private ItemSkuMapperExt itemSkuDOMapperExt;

	@Autowired
	private InventoryMapperExt inventoryDOMapperExt;

	@Autowired
	private ItemSkuScaleMapperExt itemSkuScaleDOMapper;

	@Override
    public int deleteByPrimaryKey(Long id){
		return itemDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(ItemDO record){
		return itemDOMapperExt.insert(record);
	}

	@Override
    public int insertSelective(ItemDO record){
		return itemDOMapperExt.insertSelective(record);
	}

	@Override
    public ItemDO selectByPrimaryKey(Long id){
		return itemDOMapperExt.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(ItemDO record){
		return itemDOMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKeyWithBLOBs(ItemDO record){
		return itemDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
    public int updateByPrimaryKey(ItemDO record){
		return itemDOMapperExt.updateByPrimaryKey(record);
	}


	@Override
    public List<ItemDO> selectBatchIds(List<Long> idList){
		return itemDOMapperExt.selectBatchIds(idList);
	}

	@Override
    public void updateBatchById(List<ItemDO> itemDOList){
		itemDOMapperExt.updateBatchById(itemDOList);
	}


	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
    public ItemVo queryItem(Long id){
		ItemVo itemVo = new ItemVo();
		ItemDO itemDo = itemDOMapperExt.selectByPrimaryKey(id);
		BeanUtils.copies(itemDo,itemVo);

        List<ItemSkuVo> itemSkuVos = new ArrayList<>();
		ItemSkuDO skuSo = new ItemSkuDO();
		skuSo.setItemCode(itemDo.getItemCode());
		List<ItemSkuDO> skuList = itemSkuDOMapperExt.queryItemSkuList(skuSo);
		for(ItemSkuDO sku : skuList){
			ItemSkuVo itemSkuVo = new ItemSkuVo();
			BeanUtils.copies(sku,itemSkuVo);

			//库存
			InventoryDO inventoryDO = inventoryDOMapperExt.queryBySkuCodeAndCompanyNo(sku.getSkuCode(),itemDo.getCompanyNo());
			itemSkuVo.setInventoryDO(inventoryDO);

			//规格尺寸
			List<ItemSkuScaleDO> itemSkuScaleDOS = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
			Map<String,ItemSkuScaleDO> scaleMap = new HashMap<>();
			for(ItemSkuScaleDO scale : itemSkuScaleDOS){
				scaleMap.put(scale.getScaleCode(),scale);
			}
			itemSkuVo.setScaleMap(scaleMap);

			itemSkuVos.add(itemSkuVo);

		}
		itemVo.setItemSkus(itemSkuVos);
		return itemVo;
	}





	@Override
    public List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO){
		return itemDOMapperExt.queryHaihuByUptime(itemQueryVO);
	}

}
