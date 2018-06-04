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
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemSkuVo;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemVo;
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

	public int deleteByPrimaryKey(Long id){
		return itemDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(ItemDO record){
		return itemDOMapperExt.insert(record);
	}

	public int insertSelective(ItemDO record){
		return itemDOMapperExt.insertSelective(record);
	}

	public ItemDO selectByPrimaryKey(Long id){
		return itemDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ItemDO record){
		return itemDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(ItemDO record){
		return itemDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(ItemDO record){
		return itemDOMapperExt.updateByPrimaryKey(record);
	}


	public List<ItemDO> selectBatchIds(List<Long> idList){
		return itemDOMapperExt.selectBatchIds(idList);
	}

	public void updateBatchById(List<ItemDO> itemDOList){
		itemDOMapperExt.updateBatchById(itemDOList);
	}


	/**
	 *
	 * @param id
	 * @return
	 */
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
			InventoryDO inventoryDO = inventoryDOMapperExt.queryInventoryByCode(sku.getItemCode(),sku.getSkuCode());
			itemSkuVo.setInventoryDO(inventoryDO);

			//规格尺寸
			List<ItemSkuScaleDO> itemSkuScaleDOS = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
			Map<String,String> scaleMap = new HashMap<>();
			for(ItemSkuScaleDO scale : itemSkuScaleDOS){
				scaleMap.put(scale.getScaleCode(),scale.getScaleValue());
			}
			itemSkuVo.setScaleMap(scaleMap);

			itemSkuVos.add(itemSkuVo);

		}
		itemVo.setItemSkus(itemSkuVos);
		return itemVo;
	}





	public List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO){
		return itemDOMapperExt.queryHaihuByUptime(itemQueryVO);
	}

}
