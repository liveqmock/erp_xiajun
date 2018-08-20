package com.wangqin.globalshop.channel.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.*;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.*;
import com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGet;
import com.wangqin.globalshop.channel.service.item.IItemSkuService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.dal.*;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.*;
import com.youzan.open.sdk.gen.v3_0_0.model.*;
import org.eclipse.jetty.util.StringUtil;
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

	@Autowired
	private IItemSkuService itemSkuService;

	public volatile  YZClient client = new DefaultYZClient();

	public YouzanShopGetResult getShopInfo(YouzanOauthResponse oauthResponse){
		client = new DefaultYZClient(new Token(oauthResponse.getAccess_token()));
		YouzanShopGetParams youzanShopGetParams = new YouzanShopGetParams();
		YouzanShopGet youzanShopGet = new YouzanShopGet();
		youzanShopGet.setAPIParams(youzanShopGetParams);
		YouzanShopGetResult result = client.invoke(youzanShopGet);
		return result;
	}


	public void getItemsByTime(JdShopOauthDO shopOauth, Date startTime, Date endTime) {
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

//			String result = client.execute(youzanItemsOnsaleGet);
//            logger.info("商品数据："+result);
//			hasNext = false;



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

		YouzanItemGetResult.ItemDetailOpenModel youzanItemDetail = getItemSkus(shopOauth,Long.valueOf(jdItem.getChannelItemCode()));

		YouzanItemsOnsaleGetResult.ItemListOpenModel youzanItem = BaseDto.fromJson(jdItem.getItemJson(),YouzanItemsOnsaleGetResult.ItemListOpenModel.class);

		if(youzanItemDetail == null || youzanItem == null ){
			throw new ErpCommonException("sendItem youzanItemSku or youzanItem empty","tbspuid: "+jdItem.getChannelItemCode());
		}

		//第一步：channelItem处理
		ChannelListingItemVo outerItemVo = new ChannelListingItemVo();
		outerItemVo.setChannelNo(String.valueOf(ChannelType.YouZan.getValue()));
		outerItemVo.setCompanyNo(shopOauth.getCompanyNo());
		outerItemVo.setShopCode(shopOauth.getShopCode());
		String alias = youzanItem.getAlias();// 有赞别名,商品就是这些
		outerItemVo.setChannelItemAlias(alias);
		outerItemVo.setChannelItemCode(String.valueOf(youzanItem.getItemId()));
		outerItemVo.setItemCode(youzanItem.getItemNo());//youzanItemDetail这个数据无item——no
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
		String mainPic = getMainPicStr(youzanItemDetail.getPicUrl());
		itemVo.setMainPic(mainPic);
		itemVo.setDetail(youzanItemDetail.getDesc());
		itemVo.setSaleOnYouzan(1);
		itemVo.setStatus(youzanItemDetail.getIsListing() ? ItemStatus.LISTING.getCode() : ItemStatus.DELISTING.getCode());

		List<ChannelListingItemSkuVo> outSkuList = new ArrayList<>();
		List<ItemSkuVo> itemSkuVoList = new ArrayList<>();

		YouzanItemGetResult.ItemSkuOpenModel[] skus = youzanItemDetail.getSkus();

		if(skus == null || skus.length < 1){
			//单品规格，item无规格时，skus.length==0
			//第三步：外部SKU
			ChannelListingItemSkuVo outerItemSku = new ChannelListingItemSkuVo();
			outerItemSku.setPlatformType(PlatformType.YOUZAN.getCode());
			//外部信息
			outerItemSku.setChannelItemCode(outerItemVo.getChannelItemCode());
			outerItemSku.setChannelItemSkuCode(String.valueOf(youzanItem.getItemId()));
			//内部信息
			outerItemSku.setItemCode(outerItemVo.getItemCode());
			outerItemSku.setSkuCode(youzanItem.getItemNo());
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
			itemSkuVo.setSkuCode(youzanItem.getItemNo());
			itemSkuVo.setSalePrice(BigDecimal.valueOf(youzanItem.getPrice() == null ? 0 : youzanItem.getPrice() / 100)
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			if (EasyUtil.isStringEmpty(youzanItem.getItemNo())) {
				itemSkuVo.setUpc(youzanItem.getItemId()+"");//唯一编码，店铺Id和商品Id组合
			} else {
				itemSkuVo.setUpc(youzanItem.getItemNo());//item_no,这里最好是上传的时候用UPC代替
			}

			itemSkuVo.setVirtualInv(youzanItem.getQuantity());//库存
			itemSkuVo.setSkuPic(getSkuPicStr(youzanItemDetail.getSkuImages()));
			itemSkuVoList.add(itemSkuVo);
		}else {
			//多规格：含一个及一个以上sku
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

				itemSkuVo.setVirtualInv(sku.getQuantity());//库存

				itemSkuVo.setSkuPic(getSkuPicStr(youzanItemDetail.getSkuImages()));

				itemSkuVoList.add(itemSkuVo);
			}
		}
		outerItemVo.setChannelListingItemSkuVos(outSkuList);
		itemVo.setItemSkus(itemSkuVoList);

		GlobalShopItemVo globalShopItemVo = new GlobalShopItemVo();
		globalShopItemVo.setItemVo(itemVo);
		globalShopItemVo.setChannelListingItemVo(outerItemVo);

		return globalShopItemVo;
	}


	private String getMainPicStr(String mainPicStrUrl){
		List<PicModel.PicList> picLists = new ArrayList<>();
		PicModel mainPicModel = new PicModel();
		mainPicModel.setMainPicNum(1 + "");//默认全部存第一个为主图
		PicModel.PicList picModel = new PicModel.PicList();
		picModel.setType("image/jpeg");
		//picModel.setUid("i_" + i);
		picModel.setUrl(mainPicStrUrl);
		picLists.add(picModel);
		mainPicModel.setPicList(picLists);
		return BaseDto.toString(mainPicModel);
	}

//	private String getMainPicStr(YouzanItemsOnsaleGetResult.ItemImageOpenModel[] youzanImageList){
//		String mainPicStr = "";
//		if(youzanImageList == null || youzanImageList.length < 1){
//            return mainPicStr;
//		}
//		List<PicModel.PicList> picLists = new ArrayList<>();
//		PicModel mainPicModel = new PicModel();
//		mainPicModel.setMainPicNum(1+"");//默认全部存第一个为主图
//		for(YouzanItemsOnsaleGetResult.ItemImageOpenModel itemImageOpenModel : youzanImageList){
//			PicModel.PicList picModel = new PicModel.PicList();
//			picModel.setType("image/jpeg");
//			//picModel.setUid("i_" + i);
//			picModel.setUrl(itemImageOpenModel.getUrl());
//			picLists.add(picModel);
//		}
//		mainPicModel.setPicList(picLists);
//		return BaseDto.toString(mainPicModel);
//	}

	//参数带着所有的sku的图片，先暂时把所有的sku放第一个sku
	private String getSkuPicStr(YouzanItemGetResult.SkuImageOpenModel[] youzanSkusImageList){
		String skuPicStr = "";
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
		if(youzanSkusImageList == null || youzanSkusImageList.length < 1){
			return skuPicStr;
		}
		skuPicStr = youzanSkusImageList[0].getImgUrl();
		return BaseDto.toString(skuPicStr);
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
		logger.info("商品详情 channnelItemCode："+channnelItemCode+" "+BaseDto.toString(result));
		return result.getItem();
	}


	/**
	 * 根据时间抓取订单信息
	 * @param shopOauth
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<JdOrderDO> getOrders(JdShopOauthDO shopOauth, Date startTime, Date endTime) {

		List<JdOrderDO> jdOrderDOList = new ArrayList<>();

		client = new DefaultYZClient(new Token(shopOauth.getAccessToken()));

		// 方法
		com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
		// 参数
		YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
		youzanTradesSoldGetParams.setType("ALL");
		// 买家已付款，等待发货
		youzanTradesSoldGetParams.setStatus("WAIT_SELLER_SEND_GOODS");
		youzanTradesSoldGetParams.setStartUpdate(startTime);
		youzanTradesSoldGetParams.setEndUpdate(endTime);
		youzanTradesSoldGetParams.setPageSize(100L);
		youzanTradesSoldGetParams.setUseHasNext(true);
		boolean hasNext = true;
		long pageNo = 1L;
		while (hasNext) {
			youzanTradesSoldGetParams.setPageNo(pageNo);
			youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);
			com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGetResult result = client.invoke(youzanTradesSoldGet);
			// 设置循环
			pageNo++;
			hasNext = result.getHasNext();
			com.wangqin.globalshop.channel.dal.youzan.TradeDetailV2[] tradeList = result.getTrades();
			for (int i = tradeList.length - 1; i >= 0; i--) {
				JdOrderDO jdOrder = new JdOrderDO();
				jdOrder.setCreator("-1");
				jdOrder.setModifier("-1");
				jdOrder.setChannelNo(shopOauth.getChannelNo());
				jdOrder.setCompanyNo(shopOauth.getCompanyNo());
				jdOrder.setShopCode(shopOauth.getShopCode());
				jdOrder.setSendStatus(SendStatus.REQUEST);
				jdOrder.setChannelOrderNo(tradeList[i].getTid()+"");
				jdOrder.setOrderJson(BaseDto.toString(tradeList[i]));
				jdOrder.setOrderModifyTime(new Date());
				jdOrder.setIsDel(false);
				jdOrderDOList.add(jdOrder);
			}
		}
		return jdOrderDOList;
	}


	public GlobalshopOrderVo convertYZOrder(JdShopOauthDO shopOauth, JdOrderDO requestJdOrder) {

		GlobalshopOrderVo globalshopOrderVo = new GlobalshopOrderVo();


		TradeDetailV2 TradeDetail = BaseDto.fromJson(requestJdOrder.getOrderJson(),TradeDetailV2.class);
		// 如果有赞订单还不存在，继续
		MallOrderDO outerOrder = new MallOrderDO();
		outerOrder.setCompanyNo(shopOauth.getCompanyNo());
		outerOrder.setChannelNo(shopOauth.getChannelNo());
		outerOrder.setChannelName(ChannelType.getChannelName(Integer.valueOf(shopOauth.getChannelNo())));
		outerOrder.setChannelType(shopOauth.getChannelNo());
		outerOrder.setShopCode(shopOauth.getShopCode());

		outerOrder.setOrderNo(CodeGenUtil.getOrderNo()); // 系统自动生成
		outerOrder.setOrderTime(TradeDetail.getPayTime()); // 付款时间
		outerOrder.setStatus(OrderStatus.PAID.getCode()); // 状态：代发货

		outerOrder.setChannelOrderNo(TradeDetail.getTid());

		outerOrder.setIdCard(TradeDetail.getIdCardNumber()); // 身份证

		if (StringUtil.isNotBlank(TradeDetail.getPayType())) {
			outerOrder.setPayType(PayType.valueOf(TradeDetail.getPayType()).getCode()); // 支付方式
		}

		//邮费
		outerOrder.setFreight(Double.valueOf(TradeDetail.getPostFee()));

		BigDecimal totalPrice = BigDecimal.valueOf(TradeDetail.getTotalFee().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP);

		outerOrder.setTotalAmount(TradeDetail.getTotalFee() == null ? 0d : totalPrice.doubleValue());
		outerOrder.setActualAmount(TradeDetail.getPayment() == null ? 0d : totalPrice.doubleValue());
		outerOrder.setMemo(TradeDetail.getBuyerMessage() + TradeDetail.getTradeMemo());

		outerOrder.setCreator("有赞推送订单");
		outerOrder.setGmtCreate(TradeDetail.getCreated()); // 创建时间
		outerOrder.setGmtModify(TradeDetail.getUpdateTime()); // 修改时间


		//补充必填信息
		outerOrder.setChannelCustomerNo("自定义类型，无买家昵称");
		outerOrder.setIsDel(false);
		outerOrder.setModifier("-1");


		globalshopOrderVo.setMallOrderDO(outerOrder);

		com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGetResult.TradeOrderV2[] tradeOrderArr = TradeDetail.getOrders();
		List<MallSubOrderDO> outerOrderDetails = new ArrayList<MallSubOrderDO>();

		for (int j = 0; j < tradeOrderArr.length; j++) {
			com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGetResult.TradeOrderV2 tradeOrder = tradeOrderArr[j];
			MallSubOrderDO outerOrderDetail = new MallSubOrderDO();

			//关键是查找到对应的商品信息
			String skuCode = null;
			if(Long.valueOf(0).equals(tradeOrder.getSkuId()) || EasyUtil.isStringEmpty(tradeOrder.getOuterSkuId())){
				//代表这个商品是个单品，只有itemId,itemCode
				String itemCode = tradeOrder.getOuterItemId();//这个就是上传的Item
				ItemSkuDO skuSo = new ItemSkuDO();
				skuSo.setCompanyNo(shopOauth.getCompanyNo());
				skuSo.setItemCode(itemCode);
				List<ItemSkuDO> skuDOS = itemSkuService.queryPoList(skuSo);
				if(EasyUtil.isListEmpty(skuDOS)){

					//商品未下来，先把商品抓下来
					getItems4Order(shopOauth,tradeOrder.getTitle());

					throw new ErpCommonException("item_code error","商品未能找到，itemCode: "+itemCode);
				}
				skuCode = skuDOS.get(0).getSkuCode();
			}else {
				skuCode = tradeOrder.getOuterSkuId();
				//即使有，也应该判断下商品是否存在
				ItemSkuDO skuSo = new ItemSkuDO();
				skuSo.setIsDel(false);
				skuSo.setCompanyNo(shopOauth.getCompanyNo());
				skuSo.setSkuCode(skuCode);
				Integer count =  itemSkuService.queryPoCount(skuSo);
				if(count < 1){
					//商品未下来，先把商品抓下来
					getItems4Order(shopOauth,tradeOrder.getTitle());
					throw new ErpCommonException("item_code error","商品未能找到，itemCode: "+tradeOrder.getOuterItemId());
				}
			}

			outerOrderDetail.setSkuCode(skuCode); // sku编码

			outerOrderDetail.setCompanyNo(outerOrder.getCompanyNo());
			outerOrderDetail.setOrderNo(outerOrder.getOrderNo()); // 主订单ID
			outerOrderDetail.setShopCode(outerOrder.getShopCode());

			outerOrderDetail.setSalePrice(Double.parseDouble(String.valueOf(tradeOrder.getPrice()))); // 商品单价
			outerOrderDetail.setQuantity(Integer.parseInt(String.valueOf(tradeOrder.getNum()))); // 购买数量
			outerOrderDetail.setGmtCreate(TradeDetail.getCreated()); // 创建时间
			outerOrderDetail.setGmtModify(TradeDetail.getUpdateTime()); // 修改时间

			outerOrderDetail.setOrderTime(outerOrder.getOrderTime());

			outerOrderDetail.setReceiver(TradeDetail.getReceiverName()); // 收货人
			outerOrderDetail.setReceiverState(TradeDetail.getReceiverState()); // 省
			outerOrderDetail.setReceiverCity(TradeDetail.getReceiverCity()); // 市
			outerOrderDetail.setReceiverDistrict(TradeDetail.getReceiverDistrict()); // 区
			outerOrderDetail.setReceiverAddress(TradeDetail.getReceiverAddress()); // 详细地址
			outerOrderDetail.setTelephone(TradeDetail.getReceiverMobile()); // 联系电话
			outerOrderDetail.setPostcode(TradeDetail.getReceiverZip()); // 邮编

			outerOrderDetail.setShopCode(shopOauth.getShopCode());
			outerOrderDetail.setOrderNo(outerOrder.getOrderNo());
			outerOrderDetail.setSubOrderNo(CodeGenUtil.getSubOrderNo());

			outerOrderDetail.setCustomerNo("无");
			outerOrderDetail.setIsDel(false);
			outerOrderDetail.setCreator("系统");
			outerOrderDetail.setModifier("系统");
			outerOrderDetail.setChannelOrderNo(outerOrder.getChannelOrderNo());

			outerOrderDetail.setStatus(OrderStatus.PAID.getCode());

			//有赞有子订单号
			outerOrderDetail.setChannelSubOrderNo(String.valueOf(tradeOrder.getOid()));

			outerOrderDetails.add(outerOrderDetail);
		}
		globalshopOrderVo.setMallSubOrderDOS(outerOrderDetails);

		return globalshopOrderVo;
	}

	/**
	 * 当有订单找不到商品时，用商品名称进行搜索订单，放进待下发商品表，等待下发至erp
	 * @param shopOauth
	 * @param title
	 */
	public void getItems4Order(JdShopOauthDO shopOauth, String title) {
		client = new DefaultYZClient(new Token(shopOauth.getAccessToken()));

		Boolean hasNext = true;
		Long pageNo = 1L;
		Long pageSize = 10L;

		YouzanItemsOnsaleGetParams youzanItemsOnsaleGetParams = new YouzanItemsOnsaleGetParams();
		youzanItemsOnsaleGetParams.setPageSize(pageSize);
		YouzanItemsOnsaleGet youzanItemsOnsaleGet = new YouzanItemsOnsaleGet();
		youzanItemsOnsaleGetParams.setQ(title);

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
			logger.info("为订单抓单的商品详情: "+BaseDto.toString(result));
			// 处理商品转换问题
			YouzanItemsOnsaleGetResult.ItemListOpenModel[] items = result.getItems();
			for (int i = 0; i < items.length; i++) {
				saveYouzanItemJson(shopOauth,items[i]);
			}

		}
	}





}
