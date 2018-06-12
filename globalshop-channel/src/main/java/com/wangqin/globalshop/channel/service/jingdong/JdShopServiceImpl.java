package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.Sku;
import com.jd.open.api.sdk.domain.Ware;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;
import com.jd.open.api.sdk.request.order.PopOrderGetRequest;
import com.jd.open.api.sdk.request.order.PopOrderSearchRequest;
import com.jd.open.api.sdk.request.order.PopOrderShipmentRequest;
import com.jd.open.api.sdk.request.ware.*;
import com.jd.open.api.sdk.response.order.PopOrderGetResponse;
import com.jd.open.api.sdk.response.order.PopOrderSearchResponse;
import com.jd.open.api.sdk.response.order.PopOrderShipmentResponse;
import com.jd.open.api.sdk.response.ware.*;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channel.Channel;
import com.wangqin.globalshop.channelapi.dal.ChannelListingItemVo;
import com.wangqin.globalshop.channelapi.dal.ChannelSalePriceVo;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;

import java.math.BigDecimal;
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
	public Object createItem(ItemVo itemVo){

		WareWriteAddRequest request = new WareWriteAddRequest();

		Ware ware = new Ware();
		ware.setTitle(itemVo.getItemName());
		ware.setCategoryId(1L);//必填
		ware.setOuterId(itemVo.getItemCode());
		ware.setWrap(itemVo.getUnit());


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

		WareWriteAddResponse response = null;
		try {
			 response=client.execute(request);
		} catch (JdException e) {
			logger.error("createItem_error",e);
			throw new ErpCommonException("createItem,商品发布时，京东内部出错");
		}

        if(!response.getCode().equals("0")){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc();
			throw new ErpCommonException("商品发布时，京东内部出错:"+errorMsg);
		}
		return JSONObject.toJSON(response.getWare());
	}


	public void updateItem(ItemVo itemVo, ChannelListingItemVo channelListingItemVo){

	}


	//已完成
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
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc();
			throw new ErpCommonException("上架商品时，京东内部出错:"+errorMsg);
		}

	}


	//已完成
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
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc();
			throw new ErpCommonException("上架商品时，京东内部出错:"+errorMsg);
		}
	}


	//已完成
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
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc();
			throw new ErpCommonException("上架商品时，京东内部出错:"+errorMsg);
		}
	}


	//已完成，调用者要负责sendStatus的改写
	public List<JdItemDO> getItems(Date startTime, Date endTime){

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

			if(response != null && (response.getPage() == null || !response.getCode().equals("0")) ){
				throw new ErpCommonException("getItemByItemCode", "从京东获取商品时,调用API时异常.卖家:[" + shopOauth.getShopCode() + "].京东描述:" + response.getZhDesc());
			}

			if(response.getPage().getData() == null || response.getPage().getData().size() < 1){
				break;
			}

			for(com.jd.open.api.sdk.domain.ware.WareReadService.Ware ware : response.getPage().getData()){
				JdItemDO jdItemDO = new JdItemDO();
				jdItemDO.setIsDel(false);
				jdItemDO.setVersion(0L);
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


	//已完成
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

		if(response != null && (response.getWare() == null || !response.getCode().equals("0")) ){
			throw new ErpCommonException("getItemByItemCode", "从京东获取商品时,调用API时异常.卖家:[" + shopOauth.getShopCode() + "].京东描述:" + response.getZhDesc());
		}

		JdItemDO jdItemDO = new JdItemDO();
		jdItemDO.setIsDel(false);
		jdItemDO.setVersion(0L);
		jdItemDO.setCreator("-1");
		jdItemDO.setGmtCreate(new Date());
		jdItemDO.setItemModifyTime(new Date());

		jdItemDO.setShopCode(shopOauth.getShopCode());
		jdItemDO.setChannelItemCode(response.getWare().getWareId()+"");
		jdItemDO.setItemJson(JSON.toJSONString(response.getWare()));

		return jdItemDO;
	}




	//已完成
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
			if (!response.getCode().equals("0")) {
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
                jdOrderDO.setVersion(0L);


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
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc();
			throw new ErpCommonException("发货到京东时，京东内部出错:"+errorMsg);
		}
		OrderSearchInfo order = response.getOrderDetailInfo().getOrderInfo();
		JdOrderDO jdOrderDO = new JdOrderDO();
		jdOrderDO.setChannelOrderNo(order.getOrderId());
		jdOrderDO.setCreator("-1");
		jdOrderDO.setGmtCreate(new Date());
		jdOrderDO.setModifier("-1");
		jdOrderDO.setVersion(0L);


		jdOrderDO.setShopCode(shopOauth.getShopCode());
		jdOrderDO.setOrderModifyTime(new Date());
		jdOrderDO.setOrderJson(JSON.toJSONString(order));

		jdOrderDO.setChannelNo(shopOauth.getChannelNo());

		return jdOrderDO;
	}


	//已完成
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
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc();
			throw new ErpCommonException("发货到京东时，京东内部出错:"+errorMsg);
		}


	}















}
