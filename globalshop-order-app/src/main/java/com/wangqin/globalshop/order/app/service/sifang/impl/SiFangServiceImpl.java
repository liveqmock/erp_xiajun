package com.wangqin.globalshop.order.app.service.sifang.impl;

import com.google.gson.Gson;
import com.wangqin.globalshop.biz1.app.constants.enums.TransferStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.MapUtil;
import com.wangqin.globalshop.common.utils.MathUtil;
import com.wangqin.globalshop.order.app.kuaidi_bean._4px.*;
import com.wangqin.globalshop.order.app.service.category.OrderICategoryService;
import com.wangqin.globalshop.order.app.service.item.OrderItemService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import com.wangqin.globalshop.order.app.service.sifang.ISiFangService;
import com.wangqin.globalshop.order.app.service.sifang.OrderShippingTrackPolling4pxService;
import com.wangqin.globalshop.order.app.service.warehouse.OrderIWarehouseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("siFangService")
public class SiFangServiceImpl implements ISiFangService {

	static protected Logger logger = LogManager.getLogger(SiFangServiceImpl.class);

	@Autowired
	private IShippingOrderService shippingOrderService;
	@Autowired
	private IShippingTrackService shippingTrackService;
	@Autowired
	private OrderShippingTrackPolling4pxService trackPolling4pxService;
	@Autowired
	private OrderItemService itemService;
	@Autowired
	private OrderICategoryService categoryService;
	@Autowired
	private OrderIWarehouseService warehouseService;

	// public static final String Token = "CPRX01A-4528-1106-17PM-1808DEEB48A4";

	// 海狐token
	public static final String Token = "7CDF6213-7DE8-4C0A-AAA9-4BABF3C85434";
	public static final String Key = "TFBAPI434";
	public static final String UserCode = "FBSFBE";// 会员编号，由会员注册后由转运四方提供

	public static final String Sub_Token = "V91KD150-N371-189L-R10O-UB4L79P3697G";
	public static final String Sub_Key = "UB4L79P3697G";
	public static final String Sub_UserCode = "CPIKHA";// 会员编号，由会员注册后由转运四方提供

	public static final String GetTrackingOrdersJson = "http://api.tr.4px.com/API/GetTrackingOrder.asmx/GetTrackingOrdersJson";
	// 创建预报 发货人预报单操作
	public static final String CreateNew1Url = "http://open.tr.4px.com/TRSAPI/PreAlert/CreateNew1";
	// 分箱预报
	public static final String CreateSplit = "http://open.tr.4px.com/TRSAPI/Order/CreateSplit";

	// public static final String Token = "104FC78C-7923-404C-82CF-CD88153912AL";
	// public static final String Key = "CD88153912AL";
	// public static final String UserCode = "NMTXME";// 会员编号，由会员注册后由转运四方提供
	//
	// public static final String Sub_Token =
	// "104FC78C-7923-404C-82CF-CD88153912AL";
	// public static final String Sub_Key = "CD88153912AL";
	// public static final String Sub_UserCode = "NMTXME";// 会员编号，由会员注册后由转运四方提供
	//
	// // 创建预报 发货人预报单操作
	// public static final String CreateNew1Url =
	// "http://sandbox.tr.4px.com/TRSAPI/PreAlert/CreateNew1";
	// public static final String GetTrackingOrdersJson =
	// "http://sandbox.tr.4px.com/API/GetTrackingOrder.asmx/GetTrackingOrdersJson";

	public static void main(String[] args) {
		SiFangServiceImpl sf = new SiFangServiceImpl();
		try {
			sf.shippingTrack("1Z1Y84F50355689584");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String createOrder(String shippingOrderNo) {
		ShippingOrderDO shippingOrder = shippingOrderService.selectByShippingNO(shippingOrderNo);
		if (shippingOrder == null) {
			throw new ErpCommonException("发货单号异常");
		}

		if (shippingOrder.getTransferStatus() == TransferStatus.CREATED_ORDER.getValue()) {
			throw new ErpCommonException("四方订单已经创建成功，不必重复预报 shippingNo: " + shippingOrderNo);
		}

		_4pxOrder pxorder = new _4pxOrder();
		pxorder.setToken(Token);
		_4pxOrderData pxOrderData = new _4pxOrderData();

		List<MallSubOrderDO> erpOrderList = shippingOrderService.queryShippingOrderDetail(shippingOrder.getMallOrders());
		if (CollectionUtils.isEmpty(erpOrderList)) {
			logger.error("createOrder失败， erpOrderList为空！shippingOrder shippingNo=" + shippingOrderNo);
			return null;
		}

		pxOrderData.setShipperOrderNo(shippingOrder.getShippingNo());
		
		// 查仓库
		String warehouseNo = erpOrderList.get(0).getWarehouseNo();
		WarehouseDO wareHouse =  warehouseService.selectByWarehouseNo(warehouseNo);
		if (wareHouse == null) {
			throw new ErpCommonException("仓库查不到 wareHouseId：" + warehouseNo);
		}
//		if (StringUtil.isBlank(wareHouse.getCode4px())) {
//			throw new ErpCommonException("仓库code 为空 wareHouseId：" + warehouseNo);
//		}
//
//		// TODO: 转运仓code
//		pxOrderData.setWarehouseCode(wareHouse.getCode4px());
		// 单票(快转)
		pxOrderData.setWarehouseOperateMode("NON");
		// 境外快递单号
//		pxOrderData.setCarrierDeliveryNo(shippingOrder.getLogisticNo());
		pxOrderData.setCarrierDeliveryNo(shippingOrder.getShippingNo());
		if (shippingOrder.getType() == 8) { // 经济A线
			pxOrderData.setServiceTypeCode("DPS");
		} else {
			pxOrderData.setServiceTypeCode("IPS");
		}
		// 货币代码
		pxOrderData.setItemDeclareCurrency("CNY");

		pxOrderData.setConsigneeName(shippingOrder.getReceiver());
		// 目的地国家
		pxOrderData.setCountryOfDestination("142");
		pxOrderData.setReceiptCountry("142");

		String state = shippingOrder.getReceiverState();
		pxOrderData.setProvince(state);
		String city = shippingOrder.getReceiverCity();
		pxOrderData.setCity(city);
		String district = shippingOrder.getReceiverDistrict();
		pxOrderData.setDistrict(district);
		pxOrderData.setConsigneeStreetDoorNo(shippingOrder.getAddress());
		// TODO: 收件邮编
		pxOrderData.setConsigneePostCode("310000");
		pxOrderData.setConsigneeMobile(shippingOrder.getTelephone());
		pxOrderData.setConsigneeIDNumber(shippingOrder.getIdCard());
		// 身份证正面URL
		if (!StringUtil.isBlank(shippingOrder.getIdCardFront())) {
			pxOrderData.setConsigneeIDFrontCopy(shippingOrder.getIdCardFront());
		} else {
			// 如果没有就传默认值
			pxOrderData.setConsigneeIDFrontCopy("http://www.baidu.com/img/bd_logo1.png");
		}
		// 身份证反面URL
		if (!StringUtil.isBlank(shippingOrder.getIdCardBack())) {
			pxOrderData.setConsigneeIDBackCopy(shippingOrder.getIdCardBack());
		} else {
			// 如果没有就传默认值
			pxOrderData.setConsigneeIDBackCopy("http://www.baidu.com/img/bd_logo1.png");
		}
		pxOrderData.setUserCode(UserCode);

		List<_4pxItem> items = new ArrayList<_4pxItem>();

		erpOrderList.forEach(erpOrder -> {
			// 查出商品
			ItemDO item = itemService.queryItemByItemCode(erpOrder.getItemCode());
			if (item == null) {
				throw new ErpCommonException("四方预报异常！商品不存在 : " + erpOrder.getItemName());
			}
			// 查出类目信息
			ItemCategoryDO categoryObj = categoryService.selectByCategoryCode(item.getCategoryCode());
			if (categoryObj == null) {
				throw new ErpCommonException("四方预报异常！类目不存在 : " + erpOrder.getItemName());
			}
			
			_4pxItem pxItem = new _4pxItem();
			// 规格
			StringBuffer spec = new StringBuffer();
			if (StringUtil.isNotBlank(erpOrder.getScale())) {
				spec.append(",Size:" + erpOrder.getScale());
			}
			pxItem.setSpecifications(spec.toString().substring(1));
			// pxItem.setSpec(format);

//			pxItem.setBrand(erpOrder.getBrand());
			pxItem.setItemNameLocalLang(erpOrder.getItemName());
			// TODO: 单位
			// pxItem.setSpecUnit(unit);

			// TODO: 规格值
			// pxItem.setSpecValue(specValue.intValue());
			
			String itemDeclareType = categoryObj.getCategoryCode();
			if (itemDeclareType == null) {
				Map<String, Object> categorymap = MapUtil.getCategoryMap();
//				String category = erpOrder.getCategoryName();
//				itemDeclareType = (String) categorymap.get(category);
			}
			pxItem.setItemDeclareType(itemDeclareType);
			pxItem.setItemNumber(erpOrder.getQuantity());
			// 预报的金额
			pxItem.setItemUnitPrice(erpOrder.getSalePrice());
			pxItem.setItemTotalAmount(MathUtil.mul(erpOrder.getSalePrice(), (double) erpOrder.getQuantity()));

			items.add(pxItem);
		});
		pxOrderData.setITEMS(items);
		pxorder.setData(pxOrderData);

		logger.error("准备发送四方预报信息 shippingOrder shippingNo: " + shippingOrderNo);
		// 3、发送请求
		Gson gson = new Gson();
		String jsonData = null;
		try {
			jsonData = gson.toJson(pxorder);
		} catch (Exception e) {
			logger.error("推送4px数据转换车json异常！", e);
		}
		logger.error("发送四方预报信息 shippingOrder shippingNo:" + shippingOrderNo + " json : " + jsonData);
		String response = post(CreateNew1Url, jsonData);
		logger.error("接收四方预报信息 shippingOrder shippingNo:" + shippingOrderNo + " json : " + response);

		if (response == null) {
			return null;
		}

		_4pxResponse pxResponse = new Gson().fromJson(response, _4pxResponse.class);
		if (pxResponse == null) {
			return null;
		}

		String responseCode = pxResponse.getResponseCode();
		String message = pxResponse.getMessage();
		if (_4pxResponse.SUCCESS.equals(responseCode) || "11201".equals(responseCode)) {
			// 预报成功
			logger.error("shippingOrder shippingNo:" + shippingOrderNo + " 预报成功！推送给四方数据：" + jsonData);
			shippingOrder.setTransferStatus((byte) TransferStatus.CREATED_ORDER.getValue());
			shippingOrderService.update(shippingOrder);

			return message;

		} else {
			// 预报失败
			logger.error("shippingOrder shippingNo:" + shippingOrderNo + " 预报失败！推送给四方数据：" + jsonData);
			shippingOrder.setTransferStatus((byte) TransferStatus.PREDICT_FAILED.getValue());
			shippingOrderService.update(shippingOrder);
			try {
			} catch (Exception e) {
				logger.error("更新UserTradeExpress失败", e);
				logger.error("URL:" + CreateNew1Url);
				logger.error("JSON数据:" + jsonData);
			}
		}
		return null;
	}

	@Override
	public void shippingTrack(String shipperOrderNo) throws ParseException {
		if (shipperOrderNo == null) {
			throw new ErpCommonException("物流单号异常");
		}

		String respondStr = getRequest(GetTrackingOrdersJson + "?token=" + Token + "&orderno=" + shipperOrderNo);
		_4pxGetTrackingRespond respond = (_4pxGetTrackingRespond) new Gson().fromJson(respondStr,
				_4pxGetTrackingRespond.class);
		_4pxGetTrackingData data = respond.getData();

		String shippingNo = data.getDeliveryCodeNo();

		List<_4pxGetTrackingItem> trackingList = data.getTrackingList();

		LogisticsStatus lastStatus = null;

		// 查出所有的节点，避免重复
//		EntityWrapper<ShippingTrackDO> selEntityWrapper = new EntityWrapper<ShippingTrack>();
		List<ShippingTrackDO> shippingReadyTracks = shippingTrackService.selectByShippingOrderNoList(shipperOrderNo);
		ArrayList<String> contentList = null;
		if (shippingReadyTracks != null && shippingReadyTracks.size() > 0) {
			contentList = new ArrayList<String>(shippingReadyTracks.size());
			for (ShippingTrackDO item : shippingReadyTracks) {
				contentList.add(item.getTrackInfo());
			}
		}
		
		boolean signed = false;

		// 从后往前遍历
		int count = trackingList.size();
		for (int i = count - 1; i >= 0; i--) {
			_4pxGetTrackingItem item = trackingList.get(i);

			String trackCode = item.getBusinessLinkCode();
			String content = item.getTrackingContent();
			String time = item.getOccurDatetime();
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date timeDate = date.parse(time);

			// 将轨迹映射成数据库的节点
			_4pxExpressActCode pxExpressActCode = _4pxExpressActCode.get4pxExpressActCodeByCode(trackCode);
			if (pxExpressActCode == null) {
				continue;
			}
			LogisticsStatus logisticsStatus = pxExpressActCode.getLogisticsStatus();
			if (logisticsStatus == null) {
				continue;
			}
			
			if (!signed && logisticsStatus == LogisticsStatus.SIGNED) {
				signed = true;
			}
			
			int status = Integer.valueOf(logisticsStatus.getCode());

			// 日本线路，有些包裹在日本海关也会清关，删除这部分的数据 (未到达国内，清关的)
			if ((logisticsStatus == LogisticsStatus.CLEARANCE_DOING
					|| logisticsStatus == LogisticsStatus.CLEARANCE_DONE)
					&& (lastStatus == null || Integer.valueOf(lastStatus.getCode()) < Integer
							.valueOf((LogisticsStatus.ARRIVE_CHINA_AIRPORT.getCode())))) {
				continue;
			}
			lastStatus = logisticsStatus;

			// 已经存在
			if (contentList != null && contentList.contains(content)) {
				continue;
			}

			ShippingTrackDO shippingTrack = new ShippingTrackDO();
			shippingTrack.setShippingOrderNo(shipperOrderNo);
			shippingTrack.setLogisticNo(shipperOrderNo);
//			shippingTrack.stet(status);
			shippingTrack.setOverseaInTime(timeDate);
			shippingTrack.setTrackInfo(content);
			shippingTrack.setGmtCreate(timeDate);
			shippingTrack.setGmtModify(new Date());
			shippingTrackService.insert(shippingTrack);
		}
		
		if (signed) {
			ShippingOrderDO shippingOrder = new ShippingOrderDO();
			shippingOrder.setShippingNo(shippingNo);
			shippingOrderService.updateStatusByShippingNo(shippingNo);
		}

		String trackStr = new Gson().toJson(trackingList);
		ShippingTrackPolling4pxDO pxon = new ShippingTrackPolling4pxDO();
		pxon.setShippingNo(shipperOrderNo);
		// pxon.setDeliveryNo(DeliveryNo);
		pxon.setTrackInfo(trackStr);
//		if (trackStr.contains("AO")) {
//			pxon.setIsGet(1);
//		}
//		selEntityWrapperPx.where("shipping_no={0}", shipperOrderNo);
		ShippingTrackPolling4pxDO pxMiddle = trackPolling4pxService.selectByShippingOrderNo(shipperOrderNo);
		if (pxMiddle == null) {
			pxon.setGmtCreate(new Date());
			pxon.setGmtModify(new Date());
			trackPolling4pxService.insert(pxon);
		} else {
			pxon.setId(pxMiddle.getId());
			pxon.setGmtModify(new Date());
			trackPolling4pxService.update(pxon);
		}

	}

	public static String post(String url, String jsonData) {
		String result = null;
		if (StringUtil.isBlank(url) || StringUtil.isBlank(jsonData)) {
			logger.error("post数据不能为空！");
		}
		byte[] data = jsonData.getBytes();
		try {
			URL postUrl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) postUrl.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestProperty("Content-Type", "text/json; charset=utf-8");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("Charset", "UTF-8");
			httpConn.setRequestProperty("Content-Length", String.valueOf(data.length));
			// httpConn.setChunkedStreamingMode(5);
			httpConn.setConnectTimeout(5 * 1000);
			httpConn.connect();
			PrintWriter out = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream()));
			out.println(jsonData);
			out.flush();
			out.close();
			int resultCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb = new StringBuffer();
				String line = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((line = responseReader.readLine()) != null) {
					sb.append(line);
				}
				responseReader.close();
				String responseString = sb.toString();
				// return responseString.replaceAll("null", "\"null\"") ;
				return responseString;
			} else {
				logger.error("请求失败[errorCode:" + resultCode + "','data':'" + jsonData + "'");
			}
		} catch (Exception e) {
			logger.error("推送4px数据异常", e);
		}
		return result;
	}

	public static String getRequest(String urlStr) {
		try {
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("GET");
			httpConn.setDoInput(true);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setConnectTimeout(5 * 1000);
			httpConn.connect();

			int resultCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb = new StringBuffer();
				String line = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((line = responseReader.readLine()) != null) {
					sb.append(line);
				}
				responseReader.close();
				String responseString = sb.toString();
				return responseString;
			} else {
				logger.error("GET请求失败[errorCode:" + resultCode + "','data':'" + urlStr + "'");
			}
		} catch (Exception e) {
			logger.error("GET请求查询四方数据异常", e);
		}

		return null;
	}

}
