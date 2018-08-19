package com.wangqin.globalshop.channel.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.enums.PlatformType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.PicModel;
import com.wangqin.globalshop.channel.dal.youzan.YouzanOauthResponse;
import com.wangqin.globalshop.channel.dal.youzan.YzProperties;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.dal.*;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.*;
import com.youzan.open.sdk.gen.v3_0_0.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Create by 777 on 2018/8/9
 */

@Service
public class YouzanService {

	private static Logger logger = LoggerFactory.getLogger("YouzanService");

	@Autowired
	private JdItemDOMapperExt jdItemDOMapperExt;

	public volatile  YZClient client = new DefaultYZClient();

	public YouzanShopGetResult getShopInfo(YouzanOauthResponse oauthResponse){
		client = new DefaultYZClient(new Token(oauthResponse.getAccess_token()));
		YouzanShopGetParams youzanShopGetParams = new YouzanShopGetParams();
		YouzanShopGet youzanShopGet = new YouzanShopGet();
		youzanShopGet.setAPIParams(youzanShopGetParams);
		YouzanShopGetResult result = client.invoke(youzanShopGet);
		return result;
	}


	public void getItems(JdShopOauthDO shopOauth, Date startTime, Date endTime) {
		client = new DefaultYZClient(new Token(shopOauth.getAccessToken()));

		Boolean hasNext = true;
		Long pageNo = 1L;
		Long pageSize = 10L;

		YouzanItemsOnsaleGetParams youzanItemsOnsaleGetParams = new YouzanItemsOnsaleGetParams();
		youzanItemsOnsaleGetParams.setPageSize(pageSize);
		//youzanItemsOnsaleGetParams.setUpdateTimeStart(startTime.getTime());
		//youzanItemsOnsaleGetParams.setUpdateTimeEnd(endTime.getTime());
		YouzanItemsOnsaleGet youzanItemsOnsaleGet = new YouzanItemsOnsaleGet();


		while(hasNext){
			youzanItemsOnsaleGetParams.setPageNo(pageNo);
			youzanItemsOnsaleGet.setAPIParams(youzanItemsOnsaleGetParams);
			YouzanItemsOnsaleGetResult result = client.invoke(youzanItemsOnsaleGet);

			if(result.getCount() == null || result.getCount() <= pageNo*pageSize){
				hasNext = false;
			}else{
				hasNext = true;
				pageNo++;
			}
			logger.info("抓到的商品信息: "+BaseDto.toString(result));
			// 处理商品转换问题
			YouzanItemsOnsaleGetResult.ItemListOpenModel[] items = result.getItems();
			for (int i = 0; i < items.length; i++) {
				saveYouzanItemJson(shopOauth,items[i]);
			}

		}
	}


	public void getAllItems(JdShopOauthDO shopOauth) {
		client = new DefaultYZClient(new Token(shopOauth.getAccessToken()));

		Boolean hasNext = true;
		Long pageNo = 1L;
		Long pageSize = 10L;

		YouzanItemsOnsaleGetParams youzanItemsOnsaleGetParams = new YouzanItemsOnsaleGetParams();
		youzanItemsOnsaleGetParams.setPageSize(pageSize);
		YouzanItemsOnsaleGet youzanItemsOnsaleGet = new YouzanItemsOnsaleGet();


		while(hasNext){
			youzanItemsOnsaleGetParams.setPageNo(pageNo);
			youzanItemsOnsaleGet.setAPIParams(youzanItemsOnsaleGetParams);
			YouzanItemsOnsaleGetResult result = client.invoke(youzanItemsOnsaleGet);

			if(result.getCount() == null || result.getCount() <= pageNo*pageSize){
				hasNext = false;
			}else{
				hasNext = true;
				pageNo++;
			}
			logger.info("抓到的商品信息: "+BaseDto.toString(result));
			// 处理商品转换问题
			YouzanItemsOnsaleGetResult.ItemListOpenModel[] items = result.getItems();
			for (int i = 0; i < items.length; i++) {
				saveYouzanItemJson(shopOauth,items[i]);
			}

		}
	}

	private void saveYouzanItemJson(JdShopOauthDO shopOauth, YouzanItemsOnsaleGetResult.ItemListOpenModel youzanItem){

		JdItemDO jdItemSo = new JdItemDO();
		jdItemSo.setChannelItemCode(youzanItem.getItemId()+"");
		jdItemSo.setShopCode(shopOauth.getShopCode());
		JdItemDO oldJdItem = jdItemDOMapperExt.searchJdItem(jdItemSo);

		if(oldJdItem == null){
			JdItemDO newJdItemDO = new JdItemDO();
			newJdItemDO.setCreator("-1");
			newJdItemDO.setModifier("-1");
			newJdItemDO.setChannelNo(shopOauth.getChannelNo());
			newJdItemDO.setCompanyNo(shopOauth.getCompanyNo());
			newJdItemDO.setShopCode(shopOauth.getShopCode());
			newJdItemDO.setSendStatus(SendStatus.REQUEST);
			newJdItemDO.setChannelItemCode(youzanItem.getItemId()+"");
			newJdItemDO.setItemJson(BaseDto.toString(youzanItem));
			newJdItemDO.setItemModifyTime(new Date());
            newJdItemDO.setIsDel(false);
			jdItemDOMapperExt.insert(newJdItemDO);

		}else {
			oldJdItem.setItemModifyTime(new Date());
			oldJdItem.setItemJson(BaseDto.toString(youzanItem));
			oldJdItem.setSendStatus(SendStatus.REQUEST);
			jdItemDOMapperExt.updateByPrimaryKeySelective(oldJdItem);
		}
	}



	public GlobalShopItemVo convertYZItem(JdShopOauthDO shopOauth, JdItemDO jdItem) {

		YouzanItemGetResult.ItemDetailOpenModel youzanItemSkus = getItemSkus(shopOauth,Long.valueOf(jdItem.getChannelItemCode()));

		YouzanItemsOnsaleGetResult.ItemListOpenModel youzanItem = BaseDto.fromJson(jdItem.getItemJson(),YouzanItemsOnsaleGetResult.ItemListOpenModel.class);

		if(youzanItemSkus == null || youzanItem == null ){
			throw new ErpCommonException("sendItem youzanItemSku or youzanItem empty","tbspuid: "+jdItem.getChannelItemCode());
		}

		//第一步：channelItem处理
		ChannelListingItemVo outerItemVo = new ChannelListingItemVo();
		outerItemVo.setChannelNo(String.valueOf(ChannelType.YouZan.getValue()));
		outerItemVo.setCompanyNo(shopOauth.getCompanyNo());
		outerItemVo.setShopCode(shopOauth.getShopCode());
		String alias = youzanItem.getAlias();// 有赞别名
		outerItemVo.setChannelItemAlias(alias);
		outerItemVo.setChannelItemCode(String.valueOf(youzanItem.getItemId()));
		outerItemVo.setItemCode(youzanItem.getItemNo());
		outerItemVo.setStatus(ItemStatus.LISTING.getCode());

		//补充必填信息
		outerItemVo.setIsDel(false);
		outerItemVo.setGmtCreate(new Date());
		outerItemVo.setGmtModify(new Date());
		outerItemVo.setCreator("-1");
		outerItemVo.setModifier("-1");

		//第二步：item处理
		ItemVo itemVo = new ItemVo();
		itemVo.setItemCode(youzanItem.getItemNo());
		itemVo.setItemName(youzanItem.getTitle());
		itemVo.setCompanyNo(shopOauth.getCompanyNo());
		String mainPic = getMainPicStr(youzanItem.getItemImgs());
		itemVo.setMainPic(mainPic);
		itemVo.setDetail(youzanItemSkus.getDesc());
		itemVo.setSaleOnYouzan(1);
		itemVo.setStatus(youzanItemSkus.getIsListing() ? ItemStatus.LISTING.getCode() : ItemStatus.DELISTING.getCode());

		List<ChannelListingItemSkuVo> outSkuList = new ArrayList<>();
		List<ItemSkuVo> itemSkuVoList = new ArrayList<>();

		YouzanItemGetResult.ItemSkuOpenModel[] skus = youzanItemSkus.getSkus();
		for(YouzanItemGetResult.ItemSkuOpenModel sku : skus){

			//第三步：外部SKU
			ChannelListingItemSkuVo outerItemSku = new ChannelListingItemSkuVo();
			outerItemSku.setPlatformType(PlatformType.YOUZAN.getCode());
			//外部信息
			outerItemSku.setChannelItemCode(outerItemVo.getChannelItemCode());
			outerItemSku.setChannelItemSkuCode(String.valueOf(sku.getSkuId()));
			//内部信息
			outerItemSku.setItemCode(outerItemVo.getItemCode());
			outerItemSku.setSkuCode(sku.getItemNo());

			//补充必填信息
			outerItemSku.setIsDel(false);
			outerItemSku.setGmtCreate(new Date());
			outerItemSku.setGmtModify(new Date());
			outerItemSku.setCreator("-1");
			outerItemSku.setModifier("-1");
			outSkuList.add(outerItemSku);

			//第四步：内部SKU
			ItemSkuVo itemSkuVo = new ItemSkuVo();
			itemSkuVo.setItemCode(youzanItem.getItemNo());
			itemSkuVo.setSkuCode(sku.getItemNo());
			itemSkuVo.setSalePrice(BigDecimal.valueOf(sku.getPrice() == null ? 0 : sku.getPrice()/100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

			if(EasyUtil.isStringEmpty(sku.getItemNo())){
				itemSkuVo.setUpc(sku.getSkuUniqueCode());//唯一编码，店铺Id和商品Id组合
			}else{
				itemSkuVo.setUpc(sku.getItemNo());//item_no,这里最好是上传的时候用UPC代替
			}

			Map<String,ItemSkuScaleDO> scaleMap = new HashMap<>();

			List<YzProperties> youzanSkuProperties = BaseDto.fromJson(sku.getPropertiesNameJson(), new TypeReference<List<YzProperties>>() {});
			for(YzProperties propertie  : youzanSkuProperties){
				ItemSkuScaleDO itemSkuScale = new ItemSkuScaleDO();
				//itemSkuScale.setItemCode();
				//itemSkuScale.setSkuCode();
				itemSkuScale.setScaleCode(propertie.getKid());
				itemSkuScale.setScaleName(propertie.getK());
				itemSkuScale.setScaleValue(propertie.getV());
				scaleMap.put(propertie.getK(),itemSkuScale);
			}

			//todo
			//商品UPC

			//商品库存

			//商品图片
            //itemSkuVo.(sku.getQuantity());//库存

			itemSkuVo.setSkuPic(getSkuPicStr(youzanItemSkus.getSkuImages()));

			itemSkuVoList.add(itemSkuVo);


		}
		outerItemVo.setChannelListingItemSkuVos(outSkuList);
		itemVo.setItemSkus(itemSkuVoList);

		GlobalShopItemVo globalShopItemVo = new GlobalShopItemVo();
		globalShopItemVo.setItemVo(itemVo);
		globalShopItemVo.setChannelListingItemVo(outerItemVo);

		return globalShopItemVo;
	}


	private String getMainPicStr(YouzanItemsOnsaleGetResult.ItemImageOpenModel[] youzanImageList){
		String mainPicStr = "";
		if(youzanImageList == null || youzanImageList.length < 1){
            return mainPicStr;
		}
		List<PicModel.PicList> picLists = new ArrayList<>();
		PicModel mainPicModel = new PicModel();
		mainPicModel.setMainPicNum(1+"");//默认全部存第一个为主图
		for(YouzanItemsOnsaleGetResult.ItemImageOpenModel itemImageOpenModel : youzanImageList){
			PicModel.PicList picModel = new PicModel.PicList();
			picModel.setType("image/jpeg");
			//picModel.setUid("i_" + i);
			picModel.setUrl(itemImageOpenModel.getUrl());
			picLists.add(picModel);
		}
		mainPicModel.setPicList(picLists);
		return BaseDto.toString(mainPicModel);
	}

	//参数带着所有的sku的图片，先暂时把所有的sku放第一个sku
	private String getSkuPicStr(YouzanItemGetResult.SkuImageOpenModel[] youzanSkusImageList){
//		String skuPicStr = "";
//		if(youzanSkusImageList == null || youzanSkusImageList.length < 1){
//			return skuPicStr;
//		}
//
//		PicModel picModel = new PicModel();
//
//			PicModel.PicList picModelList = new PicModel.PicList();
//		picModelList.setType("image/jpeg");
//			//picModel.setUid("i_" + i);
//		picModelList.setUrl();
//		picModel.setPicList(picModelList);
		return BaseDto.toString(youzanSkusImageList[0].getImgUrl());
	}


	/**
	 * 根据tbspuid获取单个sku的信息
	 * @param channnelItemCode
	 * @return
	 */
	private YouzanItemGetResult.ItemDetailOpenModel getItemSkus(JdShopOauthDO shopOauth, Long channnelItemCode){

		client = new DefaultYZClient(new Token(shopOauth.getAccessToken()));

		YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();

		youzanItemGetParams.setItemId(channnelItemCode);

		YouzanItemGet youzanItemGet = new YouzanItemGet();
		youzanItemGet.setAPIParams(youzanItemGetParams);
		YouzanItemGetResult result = client.invoke(youzanItemGet);
		logger.info("商品详情："+channnelItemCode+" "+BaseDto.toString(result));
		return result.getItem();
	}




}
