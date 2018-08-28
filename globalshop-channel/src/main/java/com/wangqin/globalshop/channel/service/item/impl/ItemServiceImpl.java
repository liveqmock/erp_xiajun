package com.wangqin.globalshop.channel.service.item.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SkuChannelPriceEditVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.*;
import com.wangqin.globalshop.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.common.base.BaseDto;

/**
 * Item 表数据服务层接口实现类
 */
@Service
public class ItemServiceImpl implements IItemService {

	//品牌和类目的默认值
	private static final String DEFAULT_CATE_CODE = "0101009";
	private static final String DEFAULT_CATE_NAME = "其他";
	private static final String BRAND_NO = "b11";
	private static final String BRAND_NAME = "channel->其他";
	private static final String USER_NO = "-1";
	private static final String COUNTRY = "408";
	private static final String PRICE_RANGE = "0.00";

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


    @Autowired
	private ItemCategoryMapperExt categoryMapperExt;

    //渠道商品专用：根据item_code更新商品
    @Override
    public void updateItemByItemCode(ItemDO itemDO) {
    	itemDOMapper.updateItemByItemCode(itemDO);
    }
    
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
	public void addChannelItem(ItemVo item, Boolean needUpdate) throws ErpCommonException{
    	String itemCode = item.getItemCode();
    	String companyNo = item.getCompanyNo();
    	String itemName = item.getItemName();
    	List<ItemSkuVo> skuList = item.getItemSkus();

    	//检测sku_code是否存在且是否重复
    	if (!skuCodeCheck(skuList)) {
    		throw new ErpCommonException("sku的skuCode相互之间重复或者空的skuCode");
    	}
    	//检测item_code和sku_code是否和数据库已有的重复，重复则抛出异常
    	Boolean dup = false;
		if (null != itemDOMapper.queryItemDOByItemCode(itemCode)) {
			dup = true;
		}
		for (ItemSkuVo sku:skuList) {
    		if (null != itemSkuDOMapper.queryItemSkuDOBySkuCode(sku.getSkuCode())) {
    			dup = true;
    		}
    	}
    	if (!needUpdate) { //如果不需要更新
    		if (dup) {
    			return;
    		}
    	} else { //需要更新   		
    		if (dup) { //有重复的商品或者sku
        		//更新商品
    			updateItem(item);
    			//更新sku
    			updateItemSku(skuList, item);	
    			return;
        	}
    	}
    	
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
    	itemDO.setMainPic(handlePic(item.getMainPic()));
    	itemDO.setBrandName(BRAND_NAME);
    	itemDO.setBrandNo(BRAND_NO);
    	itemDO.setCountry(COUNTRY);
    	itemDO.setCreator(USER_NO);
    	itemDO.setModifier(USER_NO);
    	itemDO.setStatus(item.getStatus());

    	//类目处理,传过来不为空，专门处理
		if(!EasyUtil.isStringEmpty(item.getCategoryCode())){
			ItemCategoryDO itemCategoryDO = categoryMapperExt.queryByCategoryCode(item.getCategoryCode());
			if(itemCategoryDO != null){
				itemDO.setCategoryCode(itemCategoryDO.getCategoryCode());
				itemDO.setCategoryName(itemCategoryDO.getName());
			}
		}

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
    		skuDO.setSkuPic(handlePic(sku.getSkuPic()));
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
    		if (IsEmptyUtil.isStringNotEmpty(sku.getSize())) {
    			ItemSkuScaleDO scale = new ItemSkuScaleDO();
    			scale.setItemCode(itemCode);
    			scale.setSkuCode(skuCode);
    			scale.setScaleCode(CodeGenUtil.getScaleCode());
    			scale.setScaleName("尺寸");
    			scale.setScaleValue(sku.getSize());
    			scale.setCreator(USER_NO);
    			scale.setModifier(USER_NO);
    			//插入规格表
    			itemSkuScaleDOMapper.insertSelective(scale);
    		}   		
    	}//end of sku loop   	    	
    }
  
    /**
     * 获得商品的主图
     * @param mainPic
     * @return
     */
    private String handlePic(String mainPic) {
    	List<String> picUrlList = BaseDto.fromJson(mainPic, new TypeReference<List<String>>(){});
    	if (IsEmptyUtil.isCollectionEmpty(picUrlList)) {
    		throw new ErpCommonException("商品或者的sku的图片为空");
    	}
    	return ImageUtil.assempleMainPic(picUrlList);
    } 
    
    
    //检测是否有空的skuCode或者重复的skuCode
    public Boolean skuCodeCheck(List<ItemSkuVo> skuList) {
    	Boolean result = true;
    	List<String> codeList = new ArrayList<String>();
    	for (ItemSkuVo sku:skuList) {
    		if (IsEmptyUtil.isStringEmpty(sku.getSkuCode())) { //有为''的skuCode值
    			result = false;
    		} else {
    			codeList.add(sku.getSkuCode());
    		}
    	}
    	//检测是否有重复的skuCode
    	HashSet<String> codeSet = new HashSet<String>(codeList);
    	if (codeSet.size() < codeList.size()) {
    		result = false;
    	}
    	return result;
    }
    
    //更新商品
    @Transactional
    public void updateItem(ItemVo item) {
    	List<ItemSkuVo> skuList = item.getItemSkus();
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
    	
    	//更新其他信息
    	itemDO.setItemCode(item.getItemCode());
    	itemDO.setItemName(item.getItemName());
    	itemDO.setMainPic(handlePic(item.getMainPic()));
    	itemDO.setStatus(item.getStatus());

		//类目处理,传过来不为空，专门处理
		if(!EasyUtil.isStringEmpty(item.getCategoryCode())){
			ItemCategoryDO itemCategoryDO = categoryMapperExt.queryByCategoryCode(item.getCategoryCode());
			if(itemCategoryDO != null){
				itemDO.setCategoryCode(itemCategoryDO.getCategoryCode());
				itemDO.setCategoryName(itemCategoryDO.getName());
			}
		}

    	itemDOMapper.updateItemByItemCode(itemDO);
    }
    
    //更新sku
    @Transactional
    public void updateItemSku(List<ItemSkuVo> skuList, ItemVo item) throws BizCommonException {
    	String itemCode = item.getItemCode();
    	//判断传来的upc是否有重复,重复则抛出错误  
    	List<String> upcList = new ArrayList<String>();
    	for (ItemSkuVo sku:skuList) {
    		if (IsEmptyUtil.isStringNotEmpty(sku.getUpc())) {
    			upcList.add(sku.getUpc());
    		}   		
    	}
    	if (IsEmptyUtil.isCollectionNotEmpty(upcList)) {
    		HashSet<String> upcSet = new HashSet<String>(upcList);
            if (upcList.size() > upcSet.size()) {
                throw new BizCommonException("sku的upc属性有重复，禁止更新");
            }
    	}
    	//计算原有的sku
    	List<ItemSkuDO> oldSkuList = itemSkuDOMapper.querySkuListByItemCode(itemCode);
    	List<String> oldSkuCodeList = new ArrayList<String>();
    	for (ItemSkuDO oldSku:oldSkuList) {
    		oldSkuCodeList.add(oldSku.getSkuCode());
    	}
    	//更新已经存在的sku
    	for (ItemSkuVo sku:skuList) {
    		if (oldSkuCodeList.contains(sku.getSkuCode())) { //更新
    			ItemSkuDO updateSku = new ItemSkuDO();
    			updateSku.setSkuCode(sku.getSkuCode());
    			updateSku.setGoodsNo(sku.getGoodsNo());
    			updateSku.setSalePrice(sku.getSalePrice());
    			updateSku.setUpc(sku.getUpc());
    			updateSku.setWeight(sku.getWeight());
    			updateSku.setSkuPic(handlePic(sku.getSkuPic()));
    			updateSku.setItemName(item.getItemName());
    			itemSkuDOMapper.updateSkuBySkuCode(updateSku);
    		} else { //新增
    			ItemSkuDO addSku = new ItemSkuDO();
    			addSku.setSkuCode(sku.getSkuCode());
    			addSku.setItemCode(itemCode);
    			addSku.setItemName(item.getItemName());
    			addSku.setCompanyNo(item.getCompanyNo());
    			addSku.setGoodsNo(sku.getGoodsNo());
    			addSku.setSalePrice(sku.getSalePrice());
    			addSku.setUpc(sku.getUpc());
    			addSku.setWeight(sku.getWeight());
    			addSku.setSkuPic(sku.getSkuPic());
    			addSku.setCreator(USER_NO);
    			addSku.setModifier(USER_NO);
    			itemSkuDOMapper.insertSelective(addSku);
    		}

    		//规格处理,先删除，再更新
			List<ItemSkuScaleDO> skuScaleDOS = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
    		for(ItemSkuScaleDO skuScaleDO : skuScaleDOS){
				itemSkuScaleDOMapper.deleteByPrimaryKey(skuScaleDO.getId());
			}


			//计算规格
			if (IsEmptyUtil.isStringNotEmpty(sku.getColor())) {
				ItemSkuScaleDO color = new ItemSkuScaleDO();
				color.setItemCode(itemCode);
				color.setSkuCode(sku.getSkuCode());
				color.setScaleCode(CodeGenUtil.getScaleCode());
				color.setScaleName("颜色");
				color.setScaleValue(sku.getColor());
				color.setCreator(USER_NO);
				color.setModifier(USER_NO);
				//插入规格表
				itemSkuScaleDOMapper.insertSelective(color);
			}
			if (IsEmptyUtil.isStringNotEmpty(sku.getSize())) {
				ItemSkuScaleDO scale = new ItemSkuScaleDO();
				scale.setItemCode(itemCode);
				scale.setSkuCode(sku.getSkuCode());
				scale.setScaleCode(CodeGenUtil.getScaleCode());
				scale.setScaleName("尺寸");
				scale.setScaleValue(sku.getSize());
				scale.setCreator(USER_NO);
				scale.setModifier(USER_NO);
				//插入规格表
				itemSkuScaleDOMapper.insertSelective(scale);
			}
		}
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
