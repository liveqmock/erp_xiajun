package com.wangqin.globalshop.channel.service.item.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelSalePriceDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.PriceUtil;

/**
 * Item 表数据服务层接口实现类
 */
@Service
public class ItemServiceImpl implements IItemService {


    @Autowired
    private ItemDOMapperExt itemDOMapper;

    @Autowired
    private ItemSkuMapperExt itemSkuDOMapper;

    @Autowired
    private InventoryMapperExt inventoryDOMapper;

    @Autowired
    private ItemSkuScaleMapperExt itemSkuScaleDOMapper;

    @Autowired
	private ChannelSalePriceDOMapperExt priceDOMapperExt;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return itemDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ItemDO record) {
        return itemDOMapper.insert(record);
    }

    @Override
    public int insertSelective(ItemDO record) {
        return itemDOMapper.insertSelective(record);
    }

    @Override
    public ItemDO selectByPrimaryKey(Long id) {
        return itemDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemDO record) {
        return itemDOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ItemDO record) {
        return itemDOMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(ItemDO record) {
        return itemDOMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<ItemDO> selectBatchIds(List<Long> idList) {
        return itemDOMapper.selectBatchIds(idList);
    }

    @Override
    public void updateBatchById(List<ItemDO> itemDOList) {
        itemDOMapper.updateBatchById(itemDOList);
    }


    /**
     * 给渠道模块提供的商品添加接口
     * 商品必填字段：
     * 1:companyNo
     * 4:itemName
     * 6:mainPic
     * sku必填字段：
     * 18:virtualInv
     * 19:weight
     * 20:upc
     * 21:salePrice
     */
    @Override
    @Transactional
	public void addChannelItem(ItemVo item) throws ErpCommonException{
    	String itemCode = item.getItemCode();
    	String companyNo = item.getCompanyNo();
    	String itemName = item.getItemName();
    	
    	//检测item_code和sku_code是否和数据库已有的重复，重复则抛出异常
    	if (null != itemDOMapper.queryItemDOByItemCode(itemCode)) {
    		throw new ErpCommonException("重复的itemCode");
    	}
    	List<ItemSkuVo> skuList = item.getItemSkus();
    	for (ItemSkuVo sku:skuList) {
    		if (null != itemSkuDOMapper.queryItemSkuDOBySkuCode(sku.getSkuCode())) {
    			throw new ErpCommonException("重复的skuCode");
    		}
    	}
    	
    	//品牌和类目的默认值
    	final String DEFAULT_CATE_CODE = "0101009";
    	final String DEFAULT_CATE_NAME = "其他";
    	final String BRAND_NO = "b11";
    	final String BRAND_NAME = "channel->其他";
    	final String USER_NO = "yjfx";
    	final String COUNTRY = "408";
    	final String PRICE_RANGE = "0.00";
    	
    	//计算价格区间
    	ItemDO itemDO = new ItemDO();
    	List<Double> salePriceList = new ArrayList<Double>();
    	for (ItemSkuVo sku:skuList) {
    		Double salePrice = sku.getSalePrice();
    		if (null != salePrice) {
    			salePriceList.add(salePrice);
    		}
    	}
    	if (IsEmptyUtil.isCollectionNotEmpty(salePriceList)) {
    		itemDO.setPriceRange(PriceUtil.calNewPriceRange(salePriceList));
    	} else {
    		itemDO.setPriceRange(PRICE_RANGE);
    	}
    	
    	//插入其他信息
    	itemDO.setItemCode(itemCode);
    	itemDO.setCompanyNo(companyNo);
    	itemDO.setCategoryCode(DEFAULT_CATE_CODE);
    	itemDO.setItemName(itemName);
    	itemDO.setCategoryName(DEFAULT_CATE_NAME);
    	itemDO.setMainPic(item.getMainPic());
    	itemDO.setBrandName(BRAND_NAME);
    	itemDO.setBrandNo(BRAND_NO);
    	itemDO.setCountry(COUNTRY);
    	itemDO.setCreator(USER_NO);
    	itemDO.setModifier(USER_NO);
    	
    	//插入商品表
    	itemDOMapper.insertItemSelective(itemDO);
    	
    	//item_sku字段的计算
    	for (ItemSkuVo sku:skuList) {
    		String skuCode = sku.getSkuCode();
    		ItemSkuDO skuDO = new ItemSkuDO();
    		skuDO.setSkuCode(skuCode);
    		skuDO.setItemCode(itemCode);
    		skuDO.setItemName(itemName);
    		skuDO.setCompanyNo(companyNo);
    		skuDO.setCategoryCode(DEFAULT_CATE_CODE);
    		skuDO.setCategoryName(DEFAULT_CATE_NAME);
    		skuDO.setUpc(sku.getUpc());
    		skuDO.setBrandName(BRAND_NAME);
    		skuDO.setWeight(sku.getWeight());
    		skuDO.setSkuPic(item.getMainPic());
    		skuDO.setSalePrice(sku.getSalePrice());
    		skuDO.setCreator(USER_NO);
    		skuDO.setModifier(USER_NO);
    		//插入sku
    		itemSkuDOMapper.insertSelective(skuDO);
    		
    		//库存的计算
    		InventoryDO invDO = new InventoryDO();
    		invDO.setCompanyNo(companyNo);
    		invDO.setItemName(itemName);
    		invDO.setItemCode(itemCode);
    		invDO.setUpc(sku.getUpc());
    		invDO.setSkuCode(skuCode);
    		invDO.setVirtualInv(sku.getVirtualInv());
    		invDO.setCreator(USER_NO);
    		invDO.setModifier(USER_NO);
    		//插入库存
    		inventoryDOMapper.insertSelective(invDO);
    		
    		//计算规格
    		if (IsEmptyUtil.isStringNotEmpty(sku.getColor())) {
    			ItemSkuScaleDO color = new ItemSkuScaleDO();
    			color.setItemCode(itemCode);
    			color.setSkuCode(skuCode);
    			color.setScaleCode(CodeGenUtil.getScaleCode());
    			color.setScaleName("颜色");
    			color.setScaleValue(sku.getColor());
    			color.setCreator(USER_NO);
    			color.setModifier(USER_NO);
    			//插入规格表
    			itemSkuScaleDOMapper.insertSelective(color);
    		}
    		if (IsEmptyUtil.isStringNotEmpty(sku.getScale())) {
    			ItemSkuScaleDO scale = new ItemSkuScaleDO();
    			scale.setItemCode(itemCode);
    			scale.setSkuCode(skuCode);
    			scale.setScaleCode(CodeGenUtil.getScaleCode());
    			scale.setScaleName("尺寸");
    			scale.setScaleValue(sku.getScale());
    			scale.setCreator(USER_NO);
    			scale.setModifier(USER_NO);
    			//插入规格表
    			itemSkuScaleDOMapper.insertSelective(scale);
    		}   		
    	}//end of sku loop   	    	
    }
    
    @Override
	public ItemVo getVoByItemCode(String itemCode){
		ItemVo itemVo = new ItemVo();
		ItemDO itemDo = itemDOMapper.queryItemByItemCode(itemCode);
		BeanUtils.copies(itemDo, itemVo);

		List<ItemSkuVo> itemSkuVos = new ArrayList<>();
		ItemSkuDO skuSo = new ItemSkuDO();
		skuSo.setItemCode(itemDo.getItemCode());
		List<ItemSkuDO> skuList = itemSkuDOMapper.queryItemSkuList(skuSo);
		for (ItemSkuDO sku : skuList) {
			ItemSkuVo itemSkuVo = new ItemSkuVo();
			BeanUtils.copies(sku, itemSkuVo);

			//库存
			InventoryDO inventoryDO = inventoryDOMapper.queryBySkuCodeAndCompanyNo(sku.getSkuCode(), itemDo.getCompanyNo());
			itemSkuVo.setInventoryDO(inventoryDO);

			//规格尺寸
			List<ItemSkuScaleDO> itemSkuScaleDOS = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
			Map<String, ItemSkuScaleDO> scaleMap = new HashMap<>();
			for (ItemSkuScaleDO scale : itemSkuScaleDOS) {
				scaleMap.put(scale.getScaleCode(), scale);
			}
			itemSkuVo.setScaleMap(scaleMap);

			itemSkuVos.add(itemSkuVo);

		}
		itemVo.setItemSkus(itemSkuVos);
		return itemVo;
	}

    /**
     * @param id
     * @return
     */
    @Override
    public ItemVo queryItem(Long id) {
        ItemVo itemVo = new ItemVo();
        ItemDO itemDo = itemDOMapper.selectByPrimaryKey(id);
        BeanUtils.copies(itemDo, itemVo);

        List<ItemSkuVo> itemSkuVos = new ArrayList<>();
        ItemSkuDO skuSo = new ItemSkuDO();
        skuSo.setItemCode(itemDo.getItemCode());
        List<ItemSkuDO> skuList = itemSkuDOMapper.queryItemSkuList(skuSo);
        for (ItemSkuDO sku : skuList) {
            ItemSkuVo itemSkuVo = new ItemSkuVo();
            BeanUtils.copies(sku, itemSkuVo);

            //库存
            InventoryDO inventoryDO = inventoryDOMapper.queryBySkuCodeAndCompanyNo(sku.getSkuCode(), itemDo.getCompanyNo());
            itemSkuVo.setInventoryDO(inventoryDO);

            //规格尺寸
            List<ItemSkuScaleDO> itemSkuScaleDOS = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
            Map<String, ItemSkuScaleDO> scaleMap = new HashMap<>();
            for (ItemSkuScaleDO scale : itemSkuScaleDOS) {
                scaleMap.put(scale.getScaleName(), scale);
            }
            itemSkuVo.setScaleMap(scaleMap);

            Map<String,Float> channelSalePriceMap = new HashMap<>();
            List<ChannelSalePriceDO> channelSalePriceDOS = priceDOMapperExt.queryPriceListBySkuCode(sku.getSkuCode());
            for(ChannelSalePriceDO channelSalePriceDO : channelSalePriceDOS){
				channelSalePriceMap.put(channelSalePriceDO.getChannelNo()+"",channelSalePriceDO.getSalePrice());
			}
			itemSkuVo.setChannelSalePriceMap(channelSalePriceMap);

            itemSkuVos.add(itemSkuVo);

        }
        itemVo.setItemSkus(itemSkuVos);
        return itemVo;
    }


    @Override
    public List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO) {
        return itemDOMapper.queryHaihuByUptime(itemQueryVO);
    }

    @Override
    public ItemDO getByItemCode(String itemCode) {
        return itemDOMapper.queryItemByItemCode(itemCode);
    }

}
