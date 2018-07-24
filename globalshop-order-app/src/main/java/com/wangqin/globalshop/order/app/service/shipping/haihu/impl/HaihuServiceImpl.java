package com.wangqin.globalshop.order.app.service.shipping.haihu.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.MapUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.order.app.service.shipping.haihu.IHaihuService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("haiHuService")
public class HaihuServiceImpl implements IHaihuService {
	@Autowired
	private IShippingTrackService shippingTrackService;
	@Autowired
	private IShippingOrderService shippingOrderService;
	@Autowired
	private IMallSubOrderService erpOrderService;
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public void createOrder(Long id) {
		ShippingOrderDO shippingOrder = shippingOrderService.selectById(id);

		if (shippingOrder == null) {
			throw new ErpCommonException("发货单号异常");
		}
		List<MallSubOrderDO> list = shippingOrderService.queryShippingOrderDetail(shippingOrder.getMallOrders());
		Map<String, Object> paramDetail = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = dateFormat.format(new Date());
		String sign = Md5Util.getMD5("enteCode=irhua&timestamp=" + timestamp);
		paramDetail.put("EnteCode", "irhua");
		paramDetail.put("Timestamp", timestamp);
		paramDetail.put("Sign", sign);

		List<Map<String, Object>> goodsList = new ArrayList<>();
		Map<String, Object> Detail = new HashMap<String, Object>();

		list.forEach(erpOrder -> {
			Map<String, Object> goods = new HashMap<String, Object>();
			Map<String, Object> categorymap = MapUtil.getCategoryMap();
//			String category = erpOrder.getCategoryName();

			String ItemDeclareTypeCode = "";
			for (Map.Entry<String, Object> entry : categorymap.entrySet()) {
//				if (category.equals(entry.getKey())) {
//					ItemDeclareTypeCode = (String) entry.getValue();
//				}
			}
			if ("09010100002".equals(ItemDeclareTypeCode)) {
				goods.put("SpecUnit", "毫升");
				goods.put("SpecValue", 10);
			}
			goods.put("ItemDeclareType", ItemDeclareTypeCode);// （商品品目暂时固定）
			goods.put("ItemNameLocalLang", erpOrder.getItemName());// 商品名称
			goods.put("ItemNumber", erpOrder.getQuantity());// 商品数量
			goods.put("ItemUnitPrice", getDeclaredPrice(erpOrder.getSalePrice()));// 商品单价
			goods.put("ItemTotalAmount", getDeclaredPrice(erpOrder.getSalePrice() * erpOrder.getQuantity()));// 商品总价
			StringBuffer spec = new StringBuffer();
			if (StringUtil.isNotBlank(erpOrder.getScale())) {
				spec.append(",Size:" + erpOrder.getScale());
			}
			if (!"".equals(spec.toString())) {
				goods.put("Spec", spec.toString().substring(1));
			}
			goodsList.add(goods);
		});
		Detail.put("ConsigneeIDNumber", shippingOrder.getIdCard());
		Detail.put("ConsigneeMobile", shippingOrder.getTelephone());
		Detail.put("ConsigneeName", shippingOrder.getReceiver());
		Detail.put("ConsigneePostCode", shippingOrder.getPostcode());
		Detail.put("Province", shippingOrder.getReceiverState());
		Detail.put("City", shippingOrder.getReceiverCity());
		Detail.put("District", shippingOrder.getReceiverDistrict());
		Detail.put("ConsigneeStreetDoorNo", shippingOrder.getAddress());
		Detail.put("ITEMS", goodsList);
		Detail.put("ItemDeclareCurrency", "USD");// 币种
		Detail.put("CarrierDeliveryNo", shippingOrder.getShippingNo());
		Detail.put("ShipperOrderNo", shippingOrder.getShippingNo());
		Detail.put("WarehouseCode", "USPDX");
		paramDetail.put("Data", Detail);
		JSONObject jsonObject = JSONObject.fromObject(paramDetail);
		System.err.println(jsonObject);
		JSONObject description = HttpClientUtil.post("http://expressjob.haihu.com/haitao/predit", null, paramDetail,
				"1");
		System.err.println(description);
		shippingOrder.setLogisticNo(shippingOrder.getShippingNo());
		shippingOrder.setLogisticCompany("海狐");
		shippingOrderService.update(shippingOrder);
		
	}
	private String getDeclaredPrice(Double salePrice) {
		salePrice = salePrice/7;
		return String.format("%.2f", salePrice);
	}
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public void shippingTrack(String shipperOrderNo) throws ParseException {
		if(shipperOrderNo == null) {
			throw new ErpCommonException("物流单号异常");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> paramData = new HashMap<String, Object>();
		paramData.put("ShipperOrderNo",shipperOrderNo);
		param.put("EnteCode", "irhua");
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = dateFormat.format(new Date());
		String sign = Md5Util.getMD5("enteCode=irhua&timestamp="+timestamp);
		param.put("Timestamp", timestamp);
		param.put("Sign",sign);
		param.put("Data", paramData);
		JSONObject json = JSONObject.fromObject(param);
		// FIXME: 下面这句有问题
		JSONObject jsonObject = HttpClientUtil.post("http://expressjob.haihu.com/haitao/queryTrack", null, param, "1");
		JSONObject jsonData = jsonObject.getJSONObject("Data");
		String shippingNo = jsonData.getString("ShipperOrderNo");
		String logisticNo = jsonData.getString("CarrierDeliveryNo");
		String inlandExpressNo = jsonData.getString("DomesticCarrierDeliveryNo");
		String inlandExpressId = "haihu";
		ShippingTrackDO shippingTrack = new ShippingTrackDO();
		shippingTrack.setShippingOrderNo(shippingNo);
		shippingTrack.setLogisticNo(logisticNo);
		shippingTrack.setInlandExpressCompanyCode(inlandExpressId);
		shippingTrack.setInlandExpressNum(inlandExpressNo);
		JSONArray jsonArray = jsonData.getJSONArray("TrackDetail");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonArrayDetail = jsonArray.getJSONObject(i);
			Integer trackCode = Integer.parseInt(jsonArrayDetail.getString("TrackCode"));
			ShippingTrackDO selShippingTrack = new ShippingTrackDO();
			selShippingTrack.setShippingOrderNo(shippingNo);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (trackCode == 4) {
//				shippingTrack.setStatus(0);
//				selShippingTrack.setStatus(0);
				shippingTrack.setOverseaInTime(date.parse(jsonArrayDetail.getString("OccurDatetime")));
				shippingTrack.setTrackInfo("未出库");
			} else if(trackCode == 7) {
//				shippingTrack.setStatus(1);
//				selShippingTrack.setStatus(1);
				shippingTrack.setOverseaOutTime(date.parse(jsonArrayDetail.getString("OccurDatetime")));
				shippingTrack.setTrackInfo("发往国内");
			} else if(trackCode == 10) {
//				shippingTrack.setStatus(3);
//				selShippingTrack.setStatus(3);
				shippingTrack.setInlandInTime(date.parse(jsonArrayDetail.getString("OccurDatetime")));
				shippingTrack.setTrackInfo("抵达国内");
			} else if(trackCode == 13) {
//				shippingTrack.setStatus(4);
//				selShippingTrack.setStatus(4);
				ShippingTrackDO selectOne = shippingTrackService.selectByShippingOrderNo(selShippingTrack);
				shippingTrack.setTrackInfo(jsonData.toString());
				if (selectOne != null && selectOne.getInlandOutTime()==date.parse(jsonArrayDetail.getString("OccurDatetime"))) {
					continue;
				} else if (selectOne != null) {
					selectOne.setTrackInfo(jsonData.toString());
					shippingTrackService.update(selectOne);
					continue;
				}
				shippingTrack.setInlandOutTime(date.parse(jsonArrayDetail.getString("OccurDatetime")));
			}else if(trackCode == 14) {
//				shippingTrack.setStatus(5);
//				selShippingTrack.setStatus(5);
				shippingOrderService.updateStatusByShippingNo(shippingNo);
				shippingTrack.setBuyerSignTime(date.parse(jsonArrayDetail.getString("OccurDatetime")));
				shippingTrack.setTrackInfo("用户已签收");
			}
			//ShippingTrack selectByLogisticNo = shippingTrackService.selectByLogisticNo(selShippingTrack);
			int count = shippingTrackService.selectCount(selShippingTrack);
			if (count>0) {
				continue;
			}
			shippingTrack.setGmtCreate(new Date());
			shippingTrack.setGmtModify(new Date());
			shippingTrackService.insert(shippingTrack);
		}
	}

	public static void main(String[] args) throws ParseException{
		HaihuServiceImpl one = new HaihuServiceImpl();
		one.shippingTrack("1807200732200511359998762");
//		one.shippingTrack("1708061624477508435176460");
	}

	@Override
	public void returnPackageNo(ShippingOrderDO shippingOrder) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("packageNo", shippingOrder.getShippingNo());
		param.put("enteCode", "irhua");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStamp = dateFormat.format(new Date());
		String sign = Md5Util.getMD5("enteCode=irhua&timeStamp="+timeStamp);
		param.put("timeStamp", timeStamp);
		param.put("sign", sign);
		String targetNo = "";
		List<Long> erpOrderIdList = HaiJsonUtils.toBean(shippingOrder.getMallOrders(), new TypeReference<List<Long>>() {
		});
		List<MallSubOrderDO> erpOrderList = erpOrderService.selectBatchIds(erpOrderIdList);
		List<Map<String, Object>> itemSkusList = new ArrayList<>();
		for (int j = 0; j < erpOrderList.size(); j++) {
			MallSubOrderDO erpOrder = erpOrderList.get(j);
			Map<String, Object> itemSkusDetail = new HashMap<String, Object>();
			itemSkusDetail.put("skuCode", erpOrder.getSkuCode());
			itemSkusList.add(itemSkusDetail);
			param.put("itemSkusList", itemSkusList);
			targetNo = erpOrderList.get(0).getChannelOrderNo();
		}
		param.put("erpOrderNo", targetNo);
		JSONObject json = JSONObject.fromObject(param);
		JSONObject description = HttpClientUtil.post("http://expressjob.haihu.com/erp/notify", null, param,"1");
	}
}
