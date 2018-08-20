package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.AdWords;
import com.jd.open.api.sdk.domain.Image;
import com.jd.open.api.sdk.domain.Sku;
import com.jd.open.api.sdk.domain.Ware;
import com.jd.open.api.sdk.domain.category.Category;
import com.jd.open.api.sdk.domain.list.CategoryAttrReadService.CategoryAttrJos;
import com.jd.open.api.sdk.domain.list.CategoryAttrValueReadService.CategoryAttrValue;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.UserInfo;
import com.jd.open.api.sdk.request.category.CategorySearchRequest;
import com.jd.open.api.sdk.request.list.CategoryReadFindAttrsByCategoryIdJosRequest;
import com.jd.open.api.sdk.request.list.CategoryReadFindValuesByAttrIdRequest;
import com.jd.open.api.sdk.request.order.PopOrderGetRequest;
import com.jd.open.api.sdk.request.order.PopOrderSearchRequest;
import com.jd.open.api.sdk.request.order.PopOrderShipmentRequest;
import com.jd.open.api.sdk.request.ware.*;
import com.jd.open.api.sdk.response.category.CategorySearchResponse;
import com.jd.open.api.sdk.response.list.CategoryReadFindAttrsByCategoryIdJosResponse;
import com.jd.open.api.sdk.response.list.CategoryReadFindValuesByAttrIdResponse;
import com.jd.open.api.sdk.response.order.PopOrderGetResponse;
import com.jd.open.api.sdk.response.order.PopOrderSearchResponse;
import com.jd.open.api.sdk.response.order.PopOrderShipmentResponse;
import com.jd.open.api.sdk.response.ware.*;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channel.Channel;
import com.wangqin.globalshop.channelapi.dal.*;
import com.wangqin.globalshop.common.utils.DateUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */
@Channel(type = ChannelType.JingDong)
public class JdShopServiceImpl extends JdAbstractShopService implements JdShopService{

	public JdClient client = null;

	private final int connectionTimeOut = 15 * 1000;

	private final int readTimeOut = 30 * 1000;


    private final String WAIT_SELLER_STOCK_OUT = "WAIT_SELLER_STOCK_OUT";//等待出库

	private final String WAIT_GOODS_RECEIVE_CONFIRM = "WAIT_GOODS_RECEIVE_CONFIRM";//等待确认收货


	private final String WAIT_SELLER_DELIVERY = "WAIT_SELLER_DELIVERY等待发货";//等待发货（只适用于海外购商家，含义为'等待境内发货'标签下的订单,非海外购商家无需使用）

	private final String PAUSE = "PAUSE";//暂停（loc订单可通过此状态获取）

	private final String FINISHED_L = "FINISHED_L"; // 完成

	private final String TRADE_CANCELED = "TRADE_CANCELED"; //取消

	private final String LOCKED = "LOCKED"; //已锁定

	private final String WAIT_SEND_CODE = "WAIT_SEND_CODE";// 等待发码（LOC订单特有状态）

	private final Integer ITEM_OP_TYPE_LISTING = 1; //上架

	private final Integer ITEM_OP_TYPE_DELISTING = 2; //下架



	private final String orderinfo_fileds = "orderId,venderId,orderType,payType,orderTotalPrice,orderSellerPrice,"
			+ "orderPayment,freightPrice,sellerDiscount,orderState,orderStateRemark,deliveryType,"
			+ "invoiceEasyInfo,invoiceInfo,invoiceCode,orderRemark,orderStartTime,orderEndTime,"
			+ "consigneeInfo,itemInfoList,couponDetailList,venderRemark,balanceUsed,pin,returnOrder,"
			+ "paymentConfirmTime,waybill,logisticsId,vatInfo,modified,directParentOrderId,parentOrderId,"
			+ "customs,customsModel,orderSource,storeOrder,idSopShipmenttype,scDT,serviceFee,pauseBizInfo,"
			+ "taxFee,tuiHuoWuYou,orderSign,storeId";



	/**
	 * 构造函数：1、配置shopOauth;
	 * 2、创建Client，可能不同平台，client需要不一样的创建方式
	 * 3、如果过期，考虑直接去尝试刷新token一次
	 * @param shopOauth
	 */
	public JdShopServiceImpl(JdShopOauthDO shopOauth) {
		super(shopOauth);

		client = new DefaultJdClient(shopOauth.getServerUrl(),shopOauth.getAccessToken(),shopOauth.getAppKey(),shopOauth.getAppsecretKey(),connectionTimeOut,readTimeOut);

//		Date now = new Date();
//		if(now.after(shopOauth.getExpiresTime())){
//			//现在大于过期时间，说明过期了
//			//refreshToken(shopOauth);
//			//super(shopOauth);
//		}
	}



	/***********************   商品模块   **************************/

	//上新
	@Override
    public Object createItem(ItemVo itemVo){

		WareWriteAddRequest request = new WareWriteAddRequest();


		Ware ware = new Ware();
		ware.setTitle(itemVo.getItemName());
		ware.setCategoryId(1L);//必填
		ware.setOuterId(itemVo.getItemCode());
		ware.setWrap(itemVo.getUnit());

		List<Image> images = new ArrayList<>();
//		Image image = new Image();
//		image.setColorId(00000000000+"");
//		image.setImgUrl("jfs/t2116/102/1731643157/81969/c3df941a/5670f868Nc441d4c3.jpg");
//		image.setImgId(1L);
//		image.setImgIndex(1);

		Image image = new Image("jfs/t2116/102/1731643157/81969/c3df941a/5670f868Nc441d4c3.jpg",00000000000+"",1);
		images.add(image);
		ware.setImages(images);


		AdWords adWords = new AdWords();
		adWords.setUrl("adWords url");
		adWords.setUrlWords("链接广告词");
		adWords.setWords("广告词");
		ware.setAdWords(adWords);


		List<Sku> skus = new ArrayList<>();
		for(ItemSkuVo skuVo : itemVo.getItemSkus()){
			Sku sku = new Sku();
			sku.setBarCode(skuVo.getUpc());
			sku.setOuterId(skuVo.getSkuCode());
			sku.setJdPrice(BigDecimal.valueOf(skuVo.getSalePrice()));
			//sku.setStockNum(skuVo.getTotalAvailableInv());
			sku.setStockNum(100L);//必填大于0
			skus.add(sku);
		}
		ware.setSkus(skus);
		request.setWare(ware);
		request.setSkus(skus);

		WareWriteAddResponse response = null;
		try {
			 response=client.execute(request);
		} catch (JdException e) {
			logger.error("createItem_error",e);
			throw new ErpCommonException("createItem,商品发布时，京东内部出错");
		}

        if(!"0".equals(response.getCode())){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("商品发布时，京东内部出错:"+errorMsg);
		}
		return JSONObject.toJSON(response.getWare());
	}


	@Override
    public void updateItem(GlobalShopItemVo globalShopItemVo){
		ItemVo itemVo = globalShopItemVo.getItemVo();
		ChannelListingItemVo channelListingItemVo = globalShopItemVo.getChannelListingItemVo();

		WareWriteUpdateWareRequest request=new WareWriteUpdateWareRequest();

		Ware ware = new Ware();

		ware.setWareId(Long.valueOf(channelListingItemVo.getChannelItemCode())); //必填

		ware.setTitle(channelListingItemVo.getChannelItemAlias()); //名称，必填

		ware.setTemplateId(1L); //非必填，模板ID，只能店铺后台查看

		ware.setTransportId(1L); //必填，运输模板,后台查看，但是后台根本查看不到，需要测试不填会出现什么情况

		ware.setIntroduction(itemVo.getDetail());//菲必填

		ware.setWeight(itemVo.getWeight().floatValue());//非必填

		List<Sku> skus = new ArrayList<>();
		for(ItemSkuVo skuVo : itemVo.getItemSkus()){
			Sku sku = new Sku();
			sku.setBarCode(skuVo.getUpc());
			sku.setOuterId(skuVo.getSkuCode());
			sku.setJdPrice(BigDecimal.valueOf(skuVo.getSalePrice()));
			sku.setStockNum(skuVo.getTotalAvailableInv());
			skus.add(sku);
		}
		ware.setSkus(skus);
		request.setWare(ware);


		WareWriteUpdateWareResponse response = null;

		try {
			response=client.execute(request);
		} catch (JdException e) {
			logger.error("updateItem_error",e);
			throw new ErpCommonException("updateItem,商品更新时，京东内部出错");
		}

		if(!"0".equals(response.getCode())){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("商品更新时，京东内部出错:"+errorMsg);
		}


	}


	//已完成，成功
	@Override
    public void listingItem(ChannelListingItemVo channelListingItemVo){
		WareWriteUpOrDownRequest request=new WareWriteUpOrDownRequest();
		request.setNote(channelListingItemVo.getNode());
		request.setWareId(Long.valueOf(channelListingItemVo.getChannelItemCode()));
		request.setOpType(ITEM_OP_TYPE_LISTING);
		WareWriteUpOrDownResponse response = null;
		try {
			response = client.execute(request);
		} catch (JdException e) {
			logger.error("listingItem_error",e);
			throw new ErpCommonException("listingItem,上架商品时，京东内部出错");
		}
		if(response != null && response.getSuccess()){
			return;
		}else {
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("上架商品时，京东内部出错:"+errorMsg);
		}

	}


	//已完成，成功
	@Override
    public void delistingItem(ChannelListingItemVo channelListingItemVo){

		WareWriteUpOrDownRequest request =  new WareWriteUpOrDownRequest();
		request.setNote(channelListingItemVo.getNode());
		request.setWareId(Long.valueOf(channelListingItemVo.getChannelItemCode()));
		request.setOpType(ITEM_OP_TYPE_DELISTING);
		WareWriteUpOrDownResponse response = null;
		try {
			response=client.execute(request);
		} catch (JdException e) {
			logger.error("delistingItem_error",e);
			throw new ErpCommonException("delistingItem,下架商品时，京东内部出错");
		}
		if(response != null && response.getSuccess()){
			return;
		}else {
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("上架商品时，京东内部出错:"+errorMsg);
		}
	}


	//已完成
	@Override
    public void modifySalePrice(ChannelSalePriceVo channelSalePriceVo){

		PriceWriteUpdateSkuJdPriceRequest request=new PriceWriteUpdateSkuJdPriceRequest();

		request.setJdPrice(BigDecimal.valueOf(channelSalePriceVo.getSalePrice()));
		request.setSkuId(Long.valueOf(channelSalePriceVo.getChannelItemSkuCode()));

		PriceWriteUpdateSkuJdPriceResponse response = null;

		try {
			response = client.execute(request);
		} catch (JdException e) {
			logger.error("modifySalePrice",e);
			throw new ErpCommonException("modifySalePrice,修改商品京东价时，京东内部出错");
		}

		if(response != null && response.getSuccess()){
			return;
		}
		if(response.getSuccess()){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("上架商品时，京东内部出错:"+errorMsg);
		}
	}


	@Override
    public void resetSkuStockNum(ChannelListingItemVo channelListingItemVo){

		StockWriteUpdateSkuStockRequest request = new StockWriteUpdateSkuStockRequest();

		StockWriteUpdateSkuStockResponse response = null;

        for(ChannelListingItemSkuVo skuVo : channelListingItemVo.getChannelListingItemSkuVos()){

			request.setSkuId(Long.valueOf(skuVo.getChannelItemSkuCode()));
			request.setStockNum(skuVo.getStockNum());
			try {
				response = client.execute(request);
			} catch (JdException e) {
				logger.error("modifySalePrice",e);
				throw new ErpCommonException("modifySalePrice,修改商品库存时，京东内部出错");
			}

			if(response != null && response.getSuccess()){
				return;
			}
			if(response.getSuccess()){
				String errorMsg = "";
				errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
				throw new ErpCommonException("修改商品库存时，京东内部出错:"+errorMsg);
			}
		}
	}


	//已完成，调用者要负责sendStatus的改写
	public List<JdItemDO> getItemsError(Date startTime, Date endTime){

		List<JdItemDO> resultList = new ArrayList<>();

		WareReadSearchWare4ValidRequest request=new WareReadSearchWare4ValidRequest();

		request.setStartModifiedTime(startTime);
		request.setEndModifiedTime(endTime);

        int page = 1;
        int pageSize = 50;

		request.setPageNo(page);
		request.setPageSize(pageSize);

		WareReadSearchWare4ValidResponse response = null;


		for(;;page++){
			try {
				response = client.execute(request);
			} catch (JdException e) {
				logger.error("getItems",e);
				throw new ErpCommonException("getItems,批量获取京东商品时，京东内部出错");
			}

			if(response != null && (response.getPage() == null || !"0".equals(response.getCode())) ){
				throw new ErpCommonException("getItemByItemCode", "从京东获取商品时,调用API时异常.卖家:[" + shopOauth.getShopCode() + "].京东描述:" + response.getZhDesc());
			}

			if(response.getPage().getData() == null || response.getPage().getData().size() < 1){
				break;
			}

			for(com.jd.open.api.sdk.domain.ware.WareReadService.Ware ware : response.getPage().getData()){
				JdItemDO jdItemDO = new JdItemDO();
				jdItemDO.setIsDel(false);
				jdItemDO.setCreator("-1");
				jdItemDO.setGmtCreate(new Date());
				jdItemDO.setItemModifyTime(new Date());

				jdItemDO.setShopCode(shopOauth.getShopCode());
				jdItemDO.setChannelItemCode(ware.getWareId()+"");
				jdItemDO.setItemJson(JSON.toJSONString(ware));

				resultList.add(jdItemDO);
			}

			if(response.getPage().getData().size() < pageSize){
				break;
			}

		}
		return resultList;
	}
	/**
	 * 老接口调用
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Override
    public List<JdItemDO> getItems(Date startTime, Date endTime){

		List<JdItemDO> resultList = new ArrayList<>();

		WareInfoByInfoRequest request=new WareInfoByInfoRequest();

		request.setEndModified(DateUtil.convertDate2Str(endTime,DateUtil.formateStr19));
		request.setStartModified(DateUtil.convertDate2Str(startTime,DateUtil.formateStr19));

		int page = 1;
		int pageSize = 50;

		request.setPage(page+"");
		request.setPageSize(pageSize+"");
		request.setFields("ware_id");

		WareInfoByInfoSearchResponse response = null;


		for(;;page++){
			try {
				response = client.execute(request);
			} catch (JdException e) {
				logger.error("getItems",e);
				throw new ErpCommonException("getItems,批量获取京东商品时，京东内部出错");
			}

			if(response != null && (response.getWareInfos() == null || !"0".equals(response.getCode())) ){
				throw new ErpCommonException("getItemByItemCode", "从京东获取商品时,调用API时异常.卖家:[" + shopOauth.getShopCode() + "].京东描述:" + response.getZhDesc());
			}

			if(response.getWareInfos() == null || response.getWareInfos().size() < 1){
				break;
			}

			for(com.jd.open.api.sdk.domain.ware.Ware ware : response.getWareInfos()){
				WareGetRequest skuRequest=new WareGetRequest();

				skuRequest.setWareId(ware.getWareId()+"");

				WareGetResponse skuResponse = null;
				try {
					skuResponse = client.execute(skuRequest);
				} catch (JdException e) {
					logger.error("getItems",e);
					throw new ErpCommonException("getItems,批量获取京东商品时，京东内部出错");
				}

				JdItemDO jdItemDO = new JdItemDO();
				jdItemDO.setIsDel(false);
				jdItemDO.setCreator("-1");
				jdItemDO.setGmtCreate(new Date());
				jdItemDO.setItemModifyTime(new Date());

				jdItemDO.setShopCode(shopOauth.getShopCode());
				jdItemDO.setChannelItemCode(ware.getWareId()+"");
				jdItemDO.setItemJson(JSON.toJSONString(skuResponse.getWare()));

				resultList.add(jdItemDO);
			}

			if(response.getWareInfos().size() < pageSize){
				break;
			}

		}
		return resultList;

	}


	public void createItemSku(){
//		SkuWriteUpdateSkusRequest request=new SkuWriteUpdateSkusRequest();
//
//		request.setAppId( "jingdong" );
//		request.setName( "jingdong" );
//		request.setWareId( 123 );
//		request.setSkuId( "123,234,345" );
//		request.setSaleAttrs( "jingdong,yanfa,pop" );
//		request.setSkuFeatures( "jingdong,yanfa,pop" );
//		request.setJdPrice( "jingdong,yanfa,pop" );
//		request.setOuterId( "jingdong,yanfa,pop" );
//		request.setStockNum( "jingdong,yanfa,pop" );
//		request.setBarCode( "jingdong,yanfa,pop" );
//
//		SkuWriteUpdateSkusResponse response=client.execute(request);
	}


	//已完成
	@Override
    public JdItemDO getItemByItemCode(String itemCode){
		WareReadFindWareByIdRequest request=new WareReadFindWareByIdRequest();

		request.setWareId(Long.valueOf(itemCode));

		WareReadFindWareByIdResponse response = null;
		try {
			response = client.execute(request);
		} catch (JdException e) {
			logger.error("getOrders_error",e);
			throw new ErpCommonException("getOrders,根据时间抓取订单时，京东内部出错");
		}

		if(response != null && (response.getWare() == null || !"0".equals(response.getCode())) ){
			throw new ErpCommonException("getItemByItemCode", "从京东获取商品时,调用API时异常.卖家:[" + shopOauth.getShopCode() + "].京东描述:" + response.getZhDesc());
		}

		JdItemDO jdItemDO = new JdItemDO();
		jdItemDO.setIsDel(false);
		jdItemDO.setCreator("-1");
		jdItemDO.setGmtCreate(new Date());
		jdItemDO.setItemModifyTime(new Date());

		jdItemDO.setShopCode(shopOauth.getShopCode());
		jdItemDO.setChannelItemCode(response.getWare().getWareId()+"");
		jdItemDO.setItemJson(JSON.toJSONString(response.getWare()));

		return jdItemDO;
	}




	//已完成
	@Override
    public List<JdOrderDO> getOrders(String startTime, String endTime){

		List<JdOrderDO> resultOrders = new ArrayList<>();

		PopOrderSearchRequest request=new PopOrderSearchRequest();

		int page = 1;
		int pageSize = 50;

		request.setStartDate(startTime);
		request.setEndDate(endTime);
		request.setOptionalFields(orderinfo_fileds);

		request.setPage(page+"");
		request.setPageSize(pageSize+"");

		PopOrderSearchResponse response = null;

		for(;;page++){
			try {
				response=client.execute(request);
			} catch (JdException e) {
				logger.error("getOrders_error",e);
				throw new ErpCommonException("getOrders,根据时间抓取订单时，京东内部出错");
			}
			if (!"0".equals(response.getCode())) {
				throw new ErpCommonException("getOrders_error", "从京东获取订单ID时,调用API时异常.卖家:[" + shopOauth.getShopCode() + "].京东描述:" + response.getZhDesc());
			}

			List<OrderSearchInfo> orderList = response.getSearchorderinfoResult().getOrderInfoList();

			if(orderList == null || orderList.size() < 1){
				break;
			}
			for (OrderSearchInfo order : orderList) {

				JdOrderDO jdOrderDO = new JdOrderDO();
				jdOrderDO.setChannelOrderNo(order.getOrderId());
                jdOrderDO.setCreator("-1");
                jdOrderDO.setGmtCreate(new Date());
                jdOrderDO.setModifier("-1");


                jdOrderDO.setShopCode(shopOauth.getShopCode());
                jdOrderDO.setOrderModifyTime(new Date());
                jdOrderDO.setOrderJson(JSON.toJSONString(order));

                jdOrderDO.setChannelNo(shopOauth.getChannelNo());

				resultOrders.add(jdOrderDO);
			}

			if (orderList.size() < pageSize) {
				break;
			}

		}
        return resultOrders;
	}

	/**
	 * 已完成：按照订单抓取订单详情
	 * @param tid
	 * @return
	 */
	@Override
    public JdOrderDO getOrderByTid(String tid){
		PopOrderGetRequest request=new PopOrderGetRequest();

		request.setOptionalFields(orderinfo_fileds);
		request.setOrderId( Long.valueOf(tid) );
		PopOrderGetResponse response = null;
		try {
			response= client.execute(request);
		} catch (JdException e) {
			logger.error("getOrderByTid_error",e);
			throw new ErpCommonException("getOrderByTid,根据订单号抓取订单时，京东内部出错");
		}
		if(response == null || response.getOrderDetailInfo() == null){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("发货到京东时，京东内部出错:"+errorMsg);
		}
		OrderSearchInfo order = response.getOrderDetailInfo().getOrderInfo();
		JdOrderDO jdOrderDO = new JdOrderDO();
		jdOrderDO.setChannelOrderNo(order.getOrderId());
		jdOrderDO.setCreator("-1");
		jdOrderDO.setGmtCreate(new Date());
		jdOrderDO.setModifier("-1");


		jdOrderDO.setShopCode(shopOauth.getShopCode());
		jdOrderDO.setOrderModifyTime(new Date());
		jdOrderDO.setOrderJson(JSON.toJSONString(order));

		jdOrderDO.setChannelNo(shopOauth.getChannelNo());

		return jdOrderDO;
	}


	//已完成
	@Override
    public void logisticComfire(JdLogisticsDO jdLogisticsDO){

		PopOrderShipmentRequest request=new PopOrderShipmentRequest();

		request.setOrderId(Long.valueOf(jdLogisticsDO.getChannelOrderNo()));
		request.setLogiCoprId(jdLogisticsDO.getLogisticCode());
		request.setLogiNo(jdLogisticsDO.getLogisticNo());

		PopOrderShipmentResponse response = null;
		try {
			response=client.execute(request);
		} catch (JdException e) {
			logger.error("logisticComfire_error",e);
			throw new ErpCommonException("logisticComfire,发货到京东时，京东内部出错");
		}
		if(response != null && response.getSopjosshipmentResult() != null && response.getSopjosshipmentResult().getSuccess()){
			return;
		}
		if(!response.getSopjosshipmentResult().getSuccess()){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("发货到京东时，京东内部出错:"+errorMsg);
		}

	}



	//未完成
	@Override
    public GlobalShopItemVo convertItemJd2Global(String jsonData){

		com.jd.open.api.sdk.domain.ware.Ware  ware = JSON.parseObject(jsonData,com.jd.open.api.sdk.domain.ware.Ware.class);

		GlobalShopItemVo resultVo = new GlobalShopItemVo();

		ChannelListingItemVo channelListingItemVo = new ChannelListingItemVo();

		ItemVo itemVo = new ItemVo();

		//先处理头部

		channelListingItemVo.setChannelNo(shopOauth.getChannelNo());
		channelListingItemVo.setShopCode(shopOauth.getShopCode());
		channelListingItemVo.setCompanyNo(shopOauth.getCompanyNo());

		channelListingItemVo.setChannelItemCode(ware.getWareId()+"");
		channelListingItemVo.setChannelItemAlias(ware.getTitle());
		channelListingItemVo.setItemCode(ware.getWareId()+"");

		channelListingItemVo.init4NoLogin();

		//itemVo.



		itemVo.setItemCode(ware.getWareId()+"");
		itemVo.setCompanyNo(shopOauth.getCompanyNo());
        itemVo.setItemName(ware.getTitle());
        itemVo.setWeight(Double.valueOf(ware.getWeight()));
        itemVo.setUnit(ware.getWrap());
		itemVo.setCategoryCode(ware.getCategoryId()+"");

		itemVo.init4NoLogin();

		List<com.jd.open.api.sdk.domain.ware.Sku> skus = ware.getSkus();

		List<ChannelListingItemSkuVo> chanenlSkuVos = new ArrayList<>();

		List<ItemSkuVo> itemSkuVos = new ArrayList<>();

		for(com.jd.open.api.sdk.domain.ware.Sku sku : skus){

			ChannelListingItemSkuVo skuVo = new ChannelListingItemSkuVo();
			skuVo.setItemCode(itemVo.getItemCode());
			skuVo.setChannelItemCode(ware.getWareId()+"");
			skuVo.setChannelItemSkuCode(sku.getSkuId()+"");
			skuVo.setSkuCode(sku.getSkuId()+"");
			skuVo.setStockNum(sku.getStockNum());
			skuVo.setPlatformType(Integer.valueOf(shopOauth.getChannelNo()));

			skuVo.init4NoLogin();
			chanenlSkuVos.add(skuVo);

			ItemSkuVo itemSkuVo = new ItemSkuVo();
			itemSkuVo.setItemCode(ware.getWareId()+"");
			itemSkuVo.setSkuCode(sku.getSkuId()+"");
			itemSkuVo.setSalePrice(Double.valueOf(sku.getJdPrice()));
            itemSkuVo.setUpc(ware.getUpcCode());
            //itemSkuVo.setScale(ware.getAttributes());
			itemSkuVo.init4NoLogin();
			itemSkuVos.add(itemSkuVo);

		}

		channelListingItemVo.setChannelListingItemSkuVos(chanenlSkuVos);
		resultVo.setChannelListingItemVo(channelListingItemVo);
		//itemVo.setItemSkus(itemSkuVos);
		resultVo.setItemVo(itemVo);
        return resultVo;
	}


	@Override
    public GlobalshopOrderVo convertJdOrder2Globalshop(String orderJson)  {

		GlobalshopOrderVo globalshopOrderVo = null;
		try {
			globalshopOrderVo= doConvertJdOrder2Globalshop(orderJson);
		} catch (ParseException e) {
			logger.error("",e);
			throw  new ErpCommonException("解析订单失败");
		}
		return globalshopOrderVo;
	}

	private GlobalshopOrderVo doConvertJdOrder2Globalshop(String orderJson) throws ParseException {
		OrderSearchInfo jdOrder = JSON.parseObject(orderJson, OrderSearchInfo.class);
		GlobalshopOrderVo globalshopOrderVo = new GlobalshopOrderVo();
		MallOrderDO mallOrderDO = new MallOrderDO();
		MallSubOrderDO mallSubOrderDO = new MallSubOrderDO();
		mallOrderDO.setCompanyNo(shopOauth.getCompanyNo());
		mallOrderDO.setChannelNo(shopOauth.getChannelNo());
		mallOrderDO.setChannelName("京东");
		mallOrderDO.setChannelType(shopOauth.getChannelNo());
		mallOrderDO.setShopCode(shopOauth.getShopCode());
		// 有赞平台为01,销售为0000
		mallOrderDO.setOrderTime(DateUtil.convertStr2Date(jdOrder.getOrderStartTime(), DateUtil.formateStr19)); // 付款时间
		mallOrderDO.setStatus(OrderStatus.INIT.getCode()); // 状态：新建
		mallOrderDO.setChannelOrderNo(jdOrder.getOrderId());
		//mallOrderDO.setIdCard(TradeDetail.getIdCardNumber()); // 身份证
		mallOrderDO.setPayType(Integer.valueOf(jdOrder.getPayType())); // 支付方式
		//邮费
		mallOrderDO.setFreight(Double.valueOf(jdOrder.getFreightPrice()));
		mallOrderDO.setTotalAmount(Double.valueOf(jdOrder.getOrderSellerPrice()));
		mallOrderDO.setActualAmount(Double.valueOf(jdOrder.getOrderTotalPrice()));
		mallOrderDO.setMemo(jdOrder.getOrderRemark() + jdOrder.getVenderRemark());
		mallOrderDO.setCreator("京东自动获取订单");
		mallOrderDO.setGmtCreate(DateUtil.convertStr2Date(jdOrder.getOrderStartTime(), DateUtil.formateStr19)); // 创建时间
		//mallOrderDO.setGmtModify(jdOrder.getti); // 修改时间
		//补充必填信息
		mallOrderDO.setOpenId("");
		mallOrderDO.setChannelCustomerNo("自定义类型，无买家昵称");
		mallOrderDO.setIsDel(false);
		mallOrderDO.setModifier("-1");
		List<MallSubOrderDO> outerOrderDetails = new ArrayList<MallSubOrderDO>();
		for (com.jd.open.api.sdk.domain.order.OrderQueryJsfService.ItemInfo itemInfo : jdOrder.getItemInfoList()) {
			MallSubOrderDO outerOrderDetail = new MallSubOrderDO();
			outerOrderDetail.setCompanyNo(shopOauth.getCompanyNo());
			outerOrderDetail.setOrderNo(jdOrder.getOrderId()); // 主订单ID
			outerOrderDetail.setShopCode(shopOauth.getShopCode());
			outerOrderDetail.setSkuCode(itemInfo.getSkuId()); // sku编码
			outerOrderDetail.setSalePrice(Double.parseDouble(String.valueOf(itemInfo.getJdPrice()))); // 商品单价
			outerOrderDetail.setQuantity(Integer.parseInt(String.valueOf(itemInfo.getItemTotal()))); // 购买数量
			outerOrderDetail.setGmtCreate(DateUtil.convertStr2Date(jdOrder.getPaymentConfirmTime(), DateUtil.formateStr19)); // 创建时间
			outerOrderDetail.setGmtModify(DateUtil.convertStr2Date(jdOrder.getModified(), DateUtil.formateStr19)); // 修改时间
			UserInfo receiver = jdOrder.getConsigneeInfo();
			outerOrderDetail.setReceiver(receiver.getFullname()); // 收货人
			outerOrderDetail.setReceiverState(receiver.getProvince()); // 省
			outerOrderDetail.setReceiverCity(receiver.getCity()); // 市
			outerOrderDetail.setReceiverDistrict(receiver.getCounty()); // 区
			outerOrderDetail.setReceiverAddress(receiver.getFullAddress()); // 详细地址
			outerOrderDetail.setTelephone(receiver.getTelephone()); // 联系电话
			//outerOrderDetail.setPostcode(receiver.get); // 邮编
			outerOrderDetail.setShopCode(shopOauth.getShopCode());
			outerOrderDetail.setOrderNo(mallOrderDO.getOrderNo());
			outerOrderDetail.setCustomerNo("无");
			outerOrderDetail.setIsDel(false);
			outerOrderDetail.setCreator("系统");
			outerOrderDetail.setModifier("系统");
			outerOrderDetail.setChannelOrderNo(jdOrder.getOrderId());
			//有赞有子订单号
			//outerOrderDetail.setChannelSubOrderNo(String.valueOf(tradeOrder.getOid()));
			outerOrderDetails.add(outerOrderDetail);

		}
		globalshopOrderVo.setMallOrderDO(mallOrderDO);
		globalshopOrderVo.setMallSubOrderDOS(outerOrderDetails);
		return globalshopOrderVo;
	}

	@Override
    public void getCategory(){
		CategorySearchRequest request=new CategorySearchRequest();
		CategorySearchResponse cateRes = null;
		try{
			cateRes=client.execute(request);
			System.out.println(JSON.toJSONString(cateRes));
		} catch (JdException e) {
			e.printStackTrace();
		}

		for(Category category : cateRes.getCategory()){
			JdCategoryDO jdCategoryDO = new JdCategoryDO();
			jdCategoryDO.setMsg(JSON.toJSONString(cateRes));
			jdCategoryDO.setCid(category.getId()+"");
			jdCategoryDO.setAttributeId(category.getId()+"");
			jdCategoryDO.setLev(category.getLev()+"");
			jdCategoryDO.setName(category.getName());

			jdCategoryDO.setShopCode(shopOauth.getShopCode());
			jdCategoryDO.setChannelNo(shopOauth.getChannelNo());
			jdCategoryDO.setCompanyNo(shopOauth.getCompanyNo());
			//jdCategoryDOMapper.insertSelective(jdCategoryDO);
		}


		//第二步：根据已有类目ID，查看该类目下有哪些属性
		List<Integer> categoryIdList = new ArrayList<>();
		for(Category category : cateRes.getCategory()){
			categoryIdList.add(category.getId());

			CategoryReadFindAttrsByCategoryIdJosRequest attIdListRequest=new CategoryReadFindAttrsByCategoryIdJosRequest();


			attIdListRequest.setCid(Long.valueOf(category.getId()));
			//request.setAttributeType( 123 );
			try {
				CategoryReadFindAttrsByCategoryIdJosResponse attresponse=client.execute(attIdListRequest);
				System.out.println("category.getId(): "+category.getId()+" "+category.getName()+"  "+JSON.toJSONString(attresponse));


				for(CategoryAttrJos attrJos : attresponse.getCategoryAttrs()){

					JdCategoryAttarDO jdCategoryAttarDO = new JdCategoryAttarDO();
					jdCategoryAttarDO.setShopCode(shopOauth.getShopCode());
					jdCategoryAttarDO.setChannelNo(shopOauth.getChannelNo());
					jdCategoryAttarDO.setCompanyNo(shopOauth.getCompanyNo());

					jdCategoryAttarDO.setAttname(attrJos.getAttName());
					jdCategoryAttarDO.setAttributeType(attrJos.getAttributeType()+"");
					jdCategoryAttarDO.setMsg(JSON.toJSONString(attresponse));
					jdCategoryAttarDO.setCategoryAttrId(attrJos.getCategoryAttrId()+"");

					//jdCategoryAttarDOMapper.insert(jdCategoryAttarDO);
				}


				//第三步：针对每个属性值，再去查看所有可能的值

				for(CategoryAttrJos attrJos : attresponse.getCategoryAttrs()){

					CategoryReadFindValuesByAttrIdRequest valueRequest=new CategoryReadFindValuesByAttrIdRequest();


					valueRequest.setCategoryAttrId(Long.valueOf(attrJos.getCategoryAttrId()));
					//valueRequest.setField( "jingdong,yanfa,pop" );
					try {
						CategoryReadFindValuesByAttrIdResponse valuesresponse=client.execute(valueRequest);
						System.out.println(category.getId()+""+ attrJos.getAttName()+" "+attrJos.getCategoryAttrId()+"  "+valuesresponse.getCategoryAttrValues().get(0).getId() +JSON.toJSONString(valuesresponse));



						for(CategoryAttrValue categoryAttrValue : valuesresponse.getCategoryAttrValues()){


							JdCategoryAttarValueDO attarValueDO = new JdCategoryAttarValueDO();
							attarValueDO.setShopCode(shopOauth.getShopCode());
							attarValueDO.setChannelNo(shopOauth.getChannelNo());
							attarValueDO.setCompanyNo(shopOauth.getCompanyNo());


							attarValueDO.setCategoryAttrId(categoryAttrValue.getAttributeId()+"");
							attarValueDO.setCategoryId(categoryAttrValue.getCategoryId()+"");
							attarValueDO.setValueId(categoryAttrValue.getId()+"");
							attarValueDO.setValue(categoryAttrValue.getValue());

							//jdCategoryAttarValueDOMapper.insert(attarValueDO);


						}

					} catch (JdException e) {
						e.printStackTrace();
					}
				}











			} catch (JdException e) {
				e.printStackTrace();

			}
		}


	}







}
