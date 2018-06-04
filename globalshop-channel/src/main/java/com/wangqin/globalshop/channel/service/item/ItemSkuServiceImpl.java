package com.wangqin.globalshop.channel.service.item;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemSkuVo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;
import com.wangqin.globalshop.common.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * ItemSku 表数据服务层接口实现类
 *
 */
@Service("itemSkuService")
public class ItemSkuServiceImpl  implements IItemSkuService {


	@Autowired
	ItemSkuMapperExt itemSkuDOMapperExt;

	public ItemSkuDOMapper getMapper(){
		return itemSkuDOMapperExt;
	}


	public int deleteByPrimaryKey(Long id){
		return itemSkuDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(ItemSkuDO record){
		return itemSkuDOMapperExt.insert(record);
	}

	public int insertSelective(ItemSkuDO record){
		return itemSkuDOMapperExt.insertSelective(record);
	}

	public ItemSkuDO selectByPrimaryKey(Long id){
		return itemSkuDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ItemSkuDO record){
		return itemSkuDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(ItemSkuDO record){
		return itemSkuDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(ItemSkuDO record){
		return itemSkuDOMapperExt.updateByPrimaryKey(record);
	}


	public ItemSkuDO queryPo(ItemSkuDO itemSkuDO){
		return itemSkuDOMapperExt.queryItemSku(itemSkuDO);
	}

	public Integer queryPoCount(ItemSkuDO itemSkuDO){
		return itemSkuDOMapperExt.queryItemSkuCount(itemSkuDO);
	}
	public List<ItemSkuDO> queryPoList(ItemSkuDO itemSkuDO){
		return itemSkuDOMapperExt.queryItemSkuList(itemSkuDO);
	}


	public List<ItemSkuVo> queryItemSkusForItemThirdSale(String itemCode, String shopCode){

		List<ItemSkuDO> itemSkuDOS =  itemSkuDOMapperExt.queryItemSkusForItemThirdSale(itemCode,shopCode);
		List<ItemSkuVo> itemSkuVos = new ArrayList<>();
		for(ItemSkuDO itemSkuDO : itemSkuDOS){
			ItemSkuVo vo = new ItemSkuVo();
			BeanUtils.copies(itemSkuDO,vo);
			itemSkuVos.add(vo);
		}
		return itemSkuVos;
	}

	public List<ItemSkuVo> queryVoList(ItemSkuDO itemSkuDO){

		List<ItemSkuDO> itemSkuDOS = itemSkuDOMapperExt.queryItemSkuList(itemSkuDO);
		List<ItemSkuVo> itemSkuVos = new ArrayList<>();
		for(ItemSkuDO itemSku : itemSkuDOS){
			ItemSkuVo vo = new ItemSkuVo();
			BeanUtils.copies(itemSku,vo);
			itemSkuVos.add(vo);
		}
		return itemSkuVos;
	}


}
