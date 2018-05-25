package com.wangqin.globalshop.biz1.app.service.item;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Item 表数据服务层接口实现类
 *
 */
@Service
public class ItemServiceImpl  implements IItemService {




	@Resource ItemDOMapperExt itemDOMapperExt;

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

}
