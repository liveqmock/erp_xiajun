package com.wangqin.globalshop.item.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelSalePriceVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuPriceVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SkuChannelPriceEditVO;
import com.wangqin.globalshop.biz1.app.bean.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.bean.dto.QueryItemSkuPriceListDTO;
import com.wangqin.globalshop.biz1.app.bean.dto.SkuChannelPriceDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelSalePriceDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.item.app.service.IItemSkuService;




@Service
public class ItemSkuServiceImpl implements IItemSkuService {
	

	
	@Autowired
	private ItemSkuMapperExt itemSkuMapperExt;
	
	@Autowired
	private ItemSkuScaleMapperExt itemSkuScaleMapperExt;


	@Autowired
	private ChannelSalePriceDOMapperExt salePriceDOMapperExt;


	//商品编辑：查找哪些sku被前端删除了
	@Override
	public List<String> queryToDeleteSkuCodeList(List<String> codeList,String itemCode) {
		return itemSkuMapperExt.queryToDeleteSkuCodeList(codeList, itemCode);
	}
	
	//查询和本sku同属一个商品的所有sku的sale_price
	@Override
	public List<Double> querySalePriceListBySkuCode(String skuCode) {
		return itemSkuMapperExt.querySalePriceListBySkuCode(skuCode);
	}
	
	@Override
	public void insertBatch(List<ItemSkuAddVO> skuList) {
		itemSkuMapperExt.insertBatch(skuList);
	}
	/**
	 * 按条件查询sku列表(分页）
	 */
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public JsonPageResult<List<ISkuDTO>> queryItemSkus(ItemSkuQueryVO itemSkuQueryVO) {
		JsonPageResult<List<ISkuDTO>> itemResult = new JsonPageResult<>();
		//1、查询总的记录数量
		Integer totalCount =  itemSkuMapperExt.queryItemSkusCount(itemSkuQueryVO);
		//2、查询分页记录
		if(totalCount!=null&&totalCount!=0L){
			itemResult.buildPage(totalCount, itemSkuQueryVO);
			List<ISkuDTO> itemSkus = itemSkuMapperExt.queryItemSkus(itemSkuQueryVO);
			//查询sku的规格信息
			itemSkus.forEach(itemSku -> {
				List<ItemSkuScaleDO> skuScaleList = itemSkuScaleMapperExt.selectScaleNameValueBySkuCode(itemSku.getSkuCode());
	        	if(!EasyUtil.isListEmpty(skuScaleList)) {
	        		for(ItemSkuScaleDO scale:skuScaleList) {
	        			if("颜色".equals(scale.getScaleName())) {
	        				itemSku.setColor(scale.getScaleValue());
	        			}
	        			if("尺寸".equals(scale.getScaleName())) {
	        				itemSku.setScale(scale.getScaleValue());
	        			}
	        		}
	        	}
			});
			
			itemResult.setData(itemSkus);
		}
		return itemResult;
	}

	/**
	 * 按照条件分页查询商品多渠道价格(分页)
	 */
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public JsonPageResult<List<SkuChannelPriceDTO>> queryItemSkuPrices(ItemSkuQueryVO itemSkuQueryVO) {


		JsonPageResult<List<SkuChannelPriceDTO>> itemResult = new JsonPageResult<>();
		//1、查询总的记录数量
		Integer totalCount =  itemSkuMapperExt.querySalableItemSkuCount(itemSkuQueryVO);
		//2、查询分页记录
		if(totalCount!=null&&totalCount!=0L) {
			itemResult.buildPage(totalCount, itemSkuQueryVO);
			//List<ItemSkuDO> itemSkus = itemSkuMapperExt.queryItemSkuListOnly(itemSkuQueryVO);
//			String companyNo = itemSkuQueryVO.getCompanyNo();
			itemSkuQueryVO.initFirstStart();
			List<ItemSkuPriceVO> itemSkuPrices = itemSkuMapperExt.querySalableItemSkuList(itemSkuQueryVO);


			List<SkuChannelPriceDTO> skuChannelPriceDTOList = new ArrayList<>();
			for (ItemSkuPriceVO itemSku : itemSkuPrices) {
				//渠道价格

				List<ChannelSalePriceDO> salePriceDOList = new ArrayList<>();

				if(Integer.valueOf(1).equals(itemSku.getSaleOnYouzan())){
					List<ChannelSalePriceDO> youzanSalePriceList = salePriceDOMapperExt.queryPriceListBySkuCodeAndChannel(itemSku.getSkuCode(),ChannelType.YouZan.getValue());
					if(EasyUtil.isListEmpty(youzanSalePriceList)){
						ChannelSalePriceDO youzanSalePrice = new ChannelSalePriceDO();
						youzanSalePrice.setCompanyNo(AppUtil.getLoginUserCompanyNo());
						//youzanSalePrice.setCompanyNo("KZQA3rqCwV");
						youzanSalePrice.setChannelNo(ChannelType.YouZan.getValue()+"");
						youzanSalePrice.setSkuCode(itemSku.getSkuCode());
						youzanSalePrice.setItemCode(itemSku.getItemCode());
						youzanSalePrice.setSalePrice(itemSku.getSalePrice().floatValue());
						salePriceDOList.add(youzanSalePrice);
					}else {
						salePriceDOList.addAll(youzanSalePriceList);
					}
				}

				if(Integer.valueOf(1).equals(itemSku.getSaleOnXcx())){
					List<ChannelSalePriceDO> xcxSalePriceList  = salePriceDOMapperExt.queryPriceListBySkuCodeAndChannel(itemSku.getSkuCode(),ChannelType.WEIXINXCX.getValue());
					if(EasyUtil.isListEmpty(xcxSalePriceList)){
						ChannelSalePriceDO xcxSalePrice = new ChannelSalePriceDO();
						xcxSalePrice.setCompanyNo(AppUtil.getLoginUserCompanyNo());
						//xcxSalePrice.setCompanyNo("KZQA3rqCwV");
						xcxSalePrice.setChannelNo(ChannelType.WEIXINXCX.getValue()+"");
						xcxSalePrice.setSkuCode(itemSku.getSkuCode());
						xcxSalePrice.setItemCode(itemSku.getItemCode());
						xcxSalePrice.setSalePrice(itemSku.getSalePrice().floatValue());
						salePriceDOList.add(xcxSalePrice);
					}else {
						salePriceDOList.addAll(xcxSalePriceList);
					}
				}

				if(Integer.valueOf(1).equals(itemSku.getSaleOnHaihu())){
					List<ChannelSalePriceDO> haihuSalePriceList  = salePriceDOMapperExt.queryPriceListBySkuCodeAndChannel(itemSku.getSkuCode(),ChannelType.HaiHu.getValue());
					if(EasyUtil.isListEmpty(haihuSalePriceList)){
						ChannelSalePriceDO haihuSalePrice = new ChannelSalePriceDO();
						haihuSalePrice.setCompanyNo(AppUtil.getLoginUserCompanyNo());
						//haihuSalePrice.setCompanyNo("KZQA3rqCwV");
						haihuSalePrice.setChannelNo(ChannelType.HaiHu.getValue()+"");
						haihuSalePrice.setSkuCode(itemSku.getSkuCode());
						haihuSalePrice.setItemCode(itemSku.getItemCode());
						haihuSalePrice.setSalePrice(itemSku.getSalePrice().floatValue());
						salePriceDOList.add(haihuSalePrice);
					}else {
						salePriceDOList.addAll(haihuSalePriceList);
					}
				}

				SkuChannelPriceDTO skuChannelPriceDTO = new SkuChannelPriceDTO();
				BeanUtils.copy(itemSku, skuChannelPriceDTO);

				List<ChannelSalePriceVO> salePriceVOList = new ArrayList<>();
				for(ChannelSalePriceDO priceDO : salePriceDOList){
					ChannelSalePriceVO priceVO = new ChannelSalePriceVO();
					BeanUtils.copies(priceDO,priceVO);
					priceVO.setChannelSkuKey(itemSku.getId()+priceVO.getChannelNo());
					salePriceVOList.add(priceVO);
				}
				skuChannelPriceDTO.setChannelSalePriceList(salePriceVOList);

				skuChannelPriceDTOList.add(skuChannelPriceDTO);
			}
			itemResult.setData(skuChannelPriceDTOList);
		}
		return itemResult;
	}

	/**
	 * 批量保存SKU的多渠道价格
	 */
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public void saveItemSkuMultiPriceList(List<SkuChannelPriceEditVO> skuChannelPriceEditVOList) {
		for (SkuChannelPriceEditVO skuChannelPriceEditVO :skuChannelPriceEditVOList ) {
			saveOneItemSkuMultiPrice(skuChannelPriceEditVO);
		}
	}

	/**
	 * 保存一个SKU的多渠道价格
	 */
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public void saveOneItemSkuMultiPrice(SkuChannelPriceEditVO skuChannelPriceEditVO) {
		List<ChannelSalePriceDO> channelSalePriceList = skuChannelPriceEditVO.getChannelSalePriceList();
		if (channelSalePriceList != null) {
			for (ChannelSalePriceDO channelSalePrice : channelSalePriceList) {
				if(channelSalePrice.getId() != null){
					channelSalePrice.setCompanyNo(AppUtil.getLoginUserCompanyNo());
					salePriceDOMapperExt.updatePriceBySkuCodeAndChannelNo(skuChannelPriceEditVO.getSkuCode(), Double.valueOf(channelSalePrice.getSalePrice()), channelSalePrice.getChannelNo());
				}else{
					channelSalePrice.setCompanyNo(AppUtil.getLoginUserCompanyNo());
					channelSalePrice.init();
					if(channelSalePrice.getShopCode() == null){
						channelSalePrice.setShopCode(1L);
					}
					salePriceDOMapperExt.insert(channelSalePrice);
				}

			}
		}
	}

	public JsonPageResult<List<SkuChannelPriceDTO>> querySkuSalePrice(String channelNo, ItemSkuQueryVO itemSkuQueryVO){

		JsonPageResult<List<SkuChannelPriceDTO>> itemResult = new JsonPageResult<>();

		if(Integer.valueOf(channelNo).equals(ChannelType.YouZan.getValue())){
			itemSkuQueryVO.setSaleOnYouzan(1);
		}

		if(Integer.valueOf(channelNo).equals(ChannelType.HaiHu.getValue())){
			itemSkuQueryVO.setThirdSale(1);
		}

		if(Integer.valueOf(channelNo).equals(ChannelType.WEIXINXCX.getValue())){
			itemSkuQueryVO.setWxisSale(Integer.valueOf(1).byteValue());
		}


		//1、查询总的记录数量
		Integer totalCount =  itemSkuMapperExt.querySalableItemSkuCount(itemSkuQueryVO);
		//2、查询分页记录
		if(totalCount!=null&&totalCount!=0L) {
			itemResult.buildPage(totalCount, itemSkuQueryVO);
			//List<ItemSkuDO> itemSkus = itemSkuMapperExt.queryItemSkuListOnly(itemSkuQueryVO);
			//			String companyNo = itemSkuQueryVO.getCompanyNo();
			itemSkuQueryVO.initFirstStart();
			List<ItemSkuPriceVO> itemSkuPrices = itemSkuMapperExt.querySalableItemSkuList(itemSkuQueryVO);



			List<SkuChannelPriceDTO> skuChannelPriceDTOList = new ArrayList<>();
			for (ItemSkuPriceVO itemSku : itemSkuPrices) {
				//拷贝
				SkuChannelPriceDTO skuChannelPriceDTO = new SkuChannelPriceDTO();
				BeanUtils.copy(itemSku, skuChannelPriceDTO);

				List<ItemSkuScaleDO> skuScaleList = itemSkuScaleMapperExt.selectScaleNameValueBySkuCode(itemSku.getSkuCode());
				if(!EasyUtil.isListEmpty(skuScaleList)) {
					for(ItemSkuScaleDO scale:skuScaleList) {
						if("颜色".equals(scale.getScaleName())) {
							skuChannelPriceDTO.setColor(scale.getScaleValue());
						}
						if("尺寸".equals(scale.getScaleName())) {
							skuChannelPriceDTO.setScale(scale.getScaleValue());
						}
					}
				}
				skuChannelPriceDTOList.add(skuChannelPriceDTO);
			}
			itemResult.setData(skuChannelPriceDTOList);
		}

		return itemResult;
	}

	/**
	 * 设置所有SKU的某渠道上价格
	 * @param discountPercent 折扣，比如85折，传85，但折扣不能超过100
	 */
	@Override
	public void saveAllItemSkuInOneChannelPrice(String discountPercent, String channelNo){

		BigDecimal discount=new BigDecimal(discountPercent).multiply(new BigDecimal(0.01));

		QueryItemSkuPriceListDTO queryItemSkuPriceListDTO=new QueryItemSkuPriceListDTO();
		queryItemSkuPriceListDTO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		queryItemSkuPriceListDTO.setSalable(1);

		List<ItemSkuPriceVO> itemSkuPrices = itemSkuMapperExt.queryAllSalableItemSkuList(queryItemSkuPriceListDTO);
		for (ItemSkuPriceVO itemSkuPrice : itemSkuPrices) {
			//渠道价格
//			List<SkuChannelPriceDTO> skuChannelPriceDTOList = new ArrayList<>();
			Double salePrice=itemSkuPrice.getSalePrice();
			Double discountPrice=discount.multiply(BigDecimal.valueOf(salePrice)).doubleValue();
			List<ChannelSalePriceDO> channelSalePriceList = salePriceDOMapperExt.queryPriceListBySkuCode(itemSkuPrice.getSkuCode());
			if(channelSalePriceList==null || channelSalePriceList.size()==0){
				//没有多渠道，需加上n条
//				List<ChannelShopDO> channels=channelShopDOMapperExt.searchShopList(null);
				ChannelSalePriceDO channelSalePriceDO=new ChannelSalePriceDO();
				channelSalePriceDO.setItemCode(itemSkuPrice.getItemCode());
				channelSalePriceDO.setChannelNo(channelNo);
				channelSalePriceDO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
				channelSalePriceDO.setSkuCode(itemSkuPrice.getSkuCode());
				channelSalePriceDO.setCreator("SYSTEM");
				channelSalePriceDO.setModifier("SYSTEM");
				//TODO 此处不对，shopCode不定，先填
				channelSalePriceDO.setShopCode(itemSkuPrice.getId());

				//TODO channelNo现在写死是1，2，3，以后优化
				if(itemSkuPrice.getSaleOnYouzan()!=null && itemSkuPrice.getSaleOnYouzan()==1) {
					channelSalePriceDO.setSalePrice(salePrice.floatValue());
					//如果是当前渠道
					if ((channelNo=="1")) {
						channelSalePriceDO.setChannelNo(channelNo);
						channelSalePriceDO.setSalePrice(discountPrice.floatValue());
					}
					salePriceDOMapperExt.insertChannelSalePriceSelective(channelSalePriceDO);
				}
				if(itemSkuPrice.getSaleOnHaihu()!=null && itemSkuPrice.getSaleOnHaihu()==1){
					channelSalePriceDO.setSalePrice(salePrice.floatValue());
					//如果是当前渠道
					if ((channelNo=="2")) {
						channelSalePriceDO.setChannelNo(channelNo);
						channelSalePriceDO.setSalePrice(discountPrice.floatValue());
					}
					salePriceDOMapperExt.insertChannelSalePriceSelective(channelSalePriceDO);
				}
				if(itemSkuPrice.getSaleOnXcx()!=null && itemSkuPrice.getSaleOnXcx()==1){
					channelSalePriceDO.setSalePrice(salePrice.floatValue());
					//如果是当前渠道
					if ((channelNo=="3")) {
						channelSalePriceDO.setChannelNo(channelNo);
						channelSalePriceDO.setSalePrice(discountPrice.floatValue());
					}
					salePriceDOMapperExt.insertChannelSalePriceSelective(channelSalePriceDO);
				}
			}else
			{
				//已有多渠道，只需更新一条
				salePriceDOMapperExt.updatePriceBySkuCodeAndChannelNo(itemSkuPrice.getSkuCode(),discountPrice,channelNo);

			}
		}

	}

	/**
	 * 初始化库存信息，添加商品时用
	 */
	@Override
	public List<InventoryDO> initInventory(List<ItemSkuAddVO> itemSkuList) {
		List<InventoryDO> inventoryList = new ArrayList<InventoryDO>();
		itemSkuList.forEach(itemSku -> {
			// 初始化库存信息
			InventoryDO inventory = new InventoryDO();
			inventory.setItemName(itemSku.getItemName());
		    inventory.setItemCode(itemSku.getItemCode());
			inventory.setSkuCode(itemSku.getSkuCode());
			inventory.setUpc(itemSku.getUpc());
			inventory.setVirtualInv(Long.valueOf(itemSku.getVirtualInv()));
			inventoryList.add(inventory);
		});
		return inventoryList;
	}


	


	

	
	@Override
	public ItemSkuDO selectByPrimaryKey(Long id) {
		return itemSkuMapperExt.selectByPrimaryKey(id);
	}
	
	
	

	
	@Override
	public void deleteById(Long id) {
		 itemSkuMapperExt.deleteById(id);
	}
	

	
	@Override
	public List<ItemSkuDO> queryItemSkusByUpc(String upc) {
		return itemSkuMapperExt.queryItemSkusByUpc(upc);
	}
	
	@Override
	public ISkuDTO queryItemSkuBySkuCode(String skuCode) {
		 return itemSkuMapperExt.queryItemSkuBySkuCode(skuCode); 
	}
	
	@Override
	public void updateById(ItemSkuQueryVO itemSkuUpdateVO) {
	
		itemSkuMapperExt.updateById(itemSkuUpdateVO);
	}
	
	 //查询可售的sku 
	@Override
	public List<ItemSkuDO> querySaleableSkus() {
		return itemSkuMapperExt.querySaleableSkus();
	}
	
	@Override
	public List<ItemSkuDO> querySkuListByItemCode(String itemCode) {
		return itemSkuMapperExt.querySkuListByItemCode(itemCode);
	}
	
	//根据itemCode查询sku列表,包含虚拟库存、颜色、尺寸这3个不在item_sku表里面的字段
    //商品编辑的时候使用
    //TODO颜色尺寸的查询 
	@Override
	public List<ItemSkuQueryVO> querySkuListByItemCodeContainsVirtualInvScale(String itemCode) {
		return itemSkuMapperExt.querySkuListByItemCodeContainsVirtualInvScale(itemCode);
	}
	
	@Override
	public List<ItemSkuDO> queryItemSkuListSelective(ItemSkuQueryVO itemSkuQueryVO) {
		return itemSkuMapperExt.queryItemSkuListSelective(itemSkuQueryVO);
	}
	
	@Override
	public void deleteItemSkuBySkuCode(String skuCode) {
		 itemSkuMapperExt.deleteItemSkuBySkuCode(skuCode);
	}
	
	@Override
	public void insertItemSkuSelective(ItemSkuDO itemSkuDO) {
		itemSkuMapperExt.insertItemSkuSelective(itemSkuDO);
	}
	
	@Override
	public String querySkuCodeById(Long id) {
		return itemSkuMapperExt.querySkuCodeById(id);
	}

	@Override
	public List<ItemSkuDO> queryByItemCodeAndCompanyNo(String itemCode, String loginUserCompanyNo) {
		return itemSkuMapperExt.queryByItemCodeAndCompanyNo(itemCode,loginUserCompanyNo);
	}

	@Override
	public ItemSkuDO queryBySkuCodeOrUpcAndCompanyNo(String code, String companyNo) {
		return itemSkuMapperExt.queryBySkuCodeOrUpcAndCompanyNo(code,companyNo);
	}
	
	@Override
	public Double querySalePriceByItemCode(String itemCode) {
		// TODO Auto-generated method stub
		return itemSkuMapperExt.querySalePriceByItemCode(itemCode);
	}
	
	//根据id查出该sku对应的商品在sku表里面映射了几个sku，如果只有一个，禁止删除这个sku
	@Override
	public Integer querySkuNumberBySkuId(Long skuId) {
		return itemSkuMapperExt.querySkuNumberBySkuId(skuId);
	}
	
	//查询指定的upc对应的item_sku的sku_code,按公司划分,防止重复的upc
	@Override
	public List<String> querySkuCodeListByUpc(String companyNo,String upc) {
		return itemSkuMapperExt.querySkuCodeListByUpc(companyNo, upc);
	}
	
	@Override
	public Integer queryRecordCountByUpcCompanyNotInSameItem(String companyNo,String upc,String itemCode) {
		return itemSkuMapperExt.queryRecordCountByUpcCompanyNotInSameItem(companyNo, upc, itemCode);
	}

	@Override
	public Integer queryCountByUpcAndCompanyNo(String companyNo, String upc) {
		return itemSkuMapperExt.queryCountByUpcAndCompanyNo(companyNo,upc);
	}

}

