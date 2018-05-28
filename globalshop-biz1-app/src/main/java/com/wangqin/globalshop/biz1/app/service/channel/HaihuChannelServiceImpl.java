package com.wangqin.globalshop.biz1.app.service.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.wangqin.enums.ChannelType;
import com.wangqin.enums.OrderStatus;
import com.wangqin.enums.PlatformType;
import com.wangqin.exception.InventoryException;
import com.wangqin.model.ChannelAccount;
import com.wangqin.model.ErpOrder;
import com.wangqin.model.Inventory;
import com.wangqin.model.Item;
import com.wangqin.model.ItemSku;
import com.wangqin.model.ShippingOrder;
import com.wangqin.model.item.OuterItem;
import com.wangqin.model.item.OuterItemSku;
import com.wangqin.model.sale.OuterOrder;
import com.wangqin.model.sale.OuterOrderDetail;
import com.wangqin.service.channels.AbstractChannelService;
import com.wangqin.service.channels.Channel;
import com.wangqin.util.DateUtil;
import com.wangqin.util.HttpClientUtil;
import com.wangqin.util.JsonResult;
import com.wangqin.util.Md5Util;
import com.wangqin.vo.ItemQueryVO;
import com.wangqin.vo.json.PicModel;
import com.wangqinauth.commons.utils.HaiJsonUtils;
import com.wangqinauth.commons.utils.StringUtils;

import net.sf.json.JSONObject;

@Channel(type=ChannelType.HaiHu)
public class HaihuChannelServiceImpl extends AbstractChannelService {
	
	protected Logger logger = LogManager.getLogger(getClass());

	public HaihuChannelServiceImpl(ChannelAccount channelAccount) {
		super(channelAccount);
	}

	@Override
	public void syncItem(HttpServletRequest request, HttpServletResponse respose) throws Exception {
		Object result = queryItem(request, respose);
		respose.getWriter().write(new Gson().toJson(result));
	}
	
	@Override
	public void syncOrder(HttpServletRequest request, HttpServletResponse respose) throws Exception {
		String url = request.getRequestURL().toString();
		if (url.contains("haihupullOrderRequestTwo")) {
			Object result = pullOrderTwo(request);
			respose.getWriter().write(new Gson().toJson(result));
		} else if (url.contains("haihupullOrder")) {
			Object result = pullOrder(request);
			respose.getWriter().write(new Gson().toJson(result));
		}
	}

	@Override
	public AdapterData adapterAuth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterCreateItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterUpdateItem(Item item, OuterItem outerItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterListingItem(Item item, OuterItem outerItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterDelistingItem(Item item, OuterItem outerItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterUpdateSkuInventory(OuterItemSku sku, Inventory inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//
	public Object queryItem(HttpServletRequest request, HttpServletResponse respose) {
		JsonResult<List<Map<String, Object>>> result = new JsonResult<>();
		List<Map<String, Object>> paramList = new ArrayList<>();
		try {
			String name = "";
			String gmtmodify = "";
			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String jsonStr = br.readLine();
			System.out.println(jsonStr);
			JSONObject jsonparam = JSONObject.fromObject(jsonStr);
			System.err.println(jsonparam);
			String timeStamp = jsonparam.getString("timeStamp");
			String enetr = jsonparam.getString("enteCode");
			String sign = jsonparam.getString("sign");
			if (jsonStr.contains("name")) {
				name = jsonparam.getString("name");
			}
			if (jsonStr.contains("gmtmodify")) {
				gmtmodify = jsonparam.getString("gmtmodify");
			}
			this.logger.error("海狐签名日期" + timeStamp);
			this.logger.error("海狐签名标志" + enetr);
			this.logger.error("海狐签名" + sign);
			String mysign = Md5Util.getMD5("enteCode=" + channelAccount.getAppValue1() + "&timeStamp=" + timeStamp);
			this.logger.error("我方签名" + mysign);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (mysign.equalsIgnoreCase(sign)) {
				ItemQueryVO vo = new ItemQueryVO();
				vo.setName(name);
				if (StringUtils.isNotBlank(gmtmodify)) {
					Date date;
					try {
						date = sdf.parse(gmtmodify);
						vo.setGmtModify(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				List<Item> items = itemService.queryHaihuByUptime(vo);
				if (CollectionUtils.isEmpty(items)) {
					return result.buildMsg("未找到符合条件的海狐商品").buildIsSuccess(false);
				}
				String itemRequestType = "0";	//代理
				for (Item item : items) {
					Map<String, Object> param = new HashMap<String, Object>();
					Map<String, Object> paramDetail = new HashMap<String, Object>();
					paramDetail.put("id", item.getId());
					paramDetail.put("name", item.getName());
					paramDetail.put("categoryName", item.getCategoryName());
					paramDetail.put("itemCode", item.getItemCode());
					paramDetail.put("brand", item.getBrand());
					
					if (item.getCategoryId() != null) {
						paramDetail.put("categoryId", item.getCategoryId());
					}
					
					try {
						PicModel pm = HaiJsonUtils.toBean(item.getMainPic(), PicModel.class);
						List<String> imgList = new ArrayList<String>();
						pm.getPicList().forEach((pic) -> {
							if (StringUtils.isNotBlank(pic.getUrl())) {
								imgList.add(pic.getUrl());
							}
						});
						paramDetail.put("imgList", imgList);
					} catch (Exception e) {
						logger.error("haihu 同步商品图片异常! ", e);
					}
					
					List<Map<String, Object>> itemSkusList = new ArrayList<>();
					Map<String, Object> tjMap = Maps.newHashMap();
					tjMap.put("item_id", item.getId());
					List<ItemSku> itemSkusAll = itemSkuService.selectByMap(tjMap);
					Map<Long, ItemSku> itemSkuMap = Maps.newHashMap();
					itemSkusAll.forEach((itemSku) -> {
						itemSkuMap.put(itemSku.getId(), itemSku);
					});

					List<ItemSku> itemSkus = itemSkuService.queryItemSkusForItemThirdSale(item.getId());
					if (!itemSkus.isEmpty()) {
						for (ItemSku itemSku : itemSkus) {
							itemSkuMap.get(itemSku.getId()).setItemSkuQuantity(itemSku.getItemSkuQuantity());
							if(itemSku.getItemSkuQuantity()!=null && itemSku.getItemSkuQuantity()>0) {
								itemRequestType = "1";
							}
						}
					}
					
					for(int i=0; i<itemSkusAll.size(); i++) {
						ItemSku itemSku = itemSkusAll.get(i);
						Map<String, Object> itemSkusDetail = new HashMap<String, Object>();
						itemSkusDetail.put("skuCode", itemSku.getSkuCode());
						itemSkusDetail.put("upc", itemSku.getUpc());
						itemSkusDetail.put("color", itemSku.getColor());
						itemSkusDetail.put("scale", itemSku.getScale());
						itemSkusDetail.put("weight", itemSku.getWeight());
						if(itemRequestType.contentEquals("0")) {	//代理
							Inventory inventory = inventoryService.queryInventoryBySkuId(item.getId(), itemSku.getId());
							itemSkusDetail.put("itemskuQuantity", inventory.getTotalAvailableInv());
							if(itemSku.getSalePrice()< 1000) {
								itemSkusDetail.put("salePrice", itemSku.getSalePrice()-8);
							} else {
								itemSkusDetail.put("salePrice", itemSku.getSalePrice()-18);
							}
						} else {
							itemSkusDetail.put("itemskuQuantity", itemSkuMap.get(itemSku.getId()).getItemSkuQuantity());
							itemSkusDetail.put("salePrice", itemSku.getSalePrice());
						}
						
						if (StringUtil.isNotBlank(itemSku.getSkuPic())) {
							PicModel pm = HaiJsonUtils.toBean(itemSku.getSkuPic(), PicModel.class);
							String imgSrc = pm.getPicList().get(0).getUrl();
							itemSku.setSkuPic(imgSrc);
						}
						itemSkusDetail.put("skuPic", itemSku.getSkuPic());
						itemSkusList.add(itemSkusDetail);
					}
					paramDetail.put("itemSkuList", itemSkusList);
					param.put("item", paramDetail);
					param.put("itemRequestType", itemRequestType);
					paramList.add(param);
				}
				this.logger.error("海狐商品List："+paramList);
				result.buildIsSuccess(true).buildData(paramList).buildMsg("请求成功");
			} else {
				result.buildMsg("拒绝访问").buildIsSuccess(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}	

	/**
     * 海狐推送订单拆单
     * @param request
     * haihupullOrder
     * @return
     */
	public Object pullOrder(HttpServletRequest request) {
		JsonResult<List<Map<String, Object>>> result = new JsonResult<>();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String jsonStr = br.readLine();
			System.out.println(jsonStr);
			JSONObject param = JSONObject.fromObject(jsonStr);
			String timeStamp = param.getString("timeStamp");
			String targetNo = param.getString("targetNo");
			String sign = param.getString("sign");
			String outerOrderDetailListString = param.getString("outerOrderDetailList");
			String mysign = Md5Util.getMD5("enteCode=haihuhaitao&timeStamp=" + timeStamp);
			this.logger.error("我方签名: " + mysign);
			this.logger.error("海狐签名: " + sign);
			this.logger.error("海狐推单参数: " + param);
			OuterOrder outerOrder = new OuterOrder();
			outerOrder.setOrderDetailList(outerOrderDetailListString);
			if (mysign.equalsIgnoreCase(sign)) {
				String outerOrderDetailList = outerOrder.getOrderDetailList();
				if (StringUtils.isNotBlank(outerOrderDetailList)) {
					try {
						erpOrderService.splithaihuErpOrder(outerOrderDetailList, targetNo);
					} catch (InventoryException e) {
						e.printStackTrace();
						this.logger.error(e);
					}
				} else {
					result.buildMsg("参数信息不对").buildIsSuccess(false);
				}
				result.buildMsg("推单成功").buildIsSuccess(true);
			} else {
				result.buildMsg("拒绝访问").buildIsSuccess(false);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			this.logger.error("读取流异常" + e1);
		}

		return result;
	}
    /**
     * 海狐推送订单
     * @param request
     * @return
     */
	@RequestMapping("/haihupullOrderRequestTwo")
	@ResponseBody
	public Object pullOrderTwo(HttpServletRequest request) {
		JsonResult<List<Map<String, Object>>> result = new JsonResult<>();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String jsonStr = br.readLine();
			System.out.println(jsonStr);
			//jsonStr = "{ \"enteCode\": \"haihuhaitao\", \"timeStamp\": \"2018-02-05 18:08:34\", \"sign\": \"956818e38722f3e921999e14d3860688\", \"outerOrder\": { \"targetNo\": \"1710301636296238394182414_0\", \"receiver\": \"王顺\", \"receiverState\": \"浙江省\", \"receiverCity\": \"杭州市\", \"receiverDistrict\": \"西湖区\", \"addressDetail\": \"西斗门路9号福地创业园2期1栋2楼\", \"telephone\": \"18958069075\", \"idCard\": \"330522199007214515\", \"outerOrderDetails\": [{ \"skuCode\": \"B00QRDFX1K\", \"salePrice\": \"149.00\", \"quantity\": 1 }] } }";
			JSONObject param = JSONObject.fromObject(jsonStr);
			String timeStamp = param.getString("timeStamp");
			//String targetNo = param.getString("targetNo");
			String sign = param.getString("sign");
			String outerOrderHhStr = param.getString("outerOrder");
			String mysign = Md5Util.getMD5("enteCode=haihuhaitao&timeStamp=" + timeStamp);
			this.logger.error("我方签名: " + mysign);
			this.logger.error("海狐签名: " + sign);
			this.logger.error("海狐推单参数: " + param);
			if (mysign.equalsIgnoreCase(sign)) {
				String s = outerOrderHhStr.replace("&quot;", "\"");
				OuterOrder outerOrderHh = HaiJsonUtils.toBean(s, new TypeReference<OuterOrder>(){});
				System.out.println("1"+s);
				List<Long> outOrderIdList = new ArrayList<Long>();
				if(StringUtils.isBlank(outerOrderHh.getTargetNo())) {
					return result.buildIsSuccess(false).buildMsg("订单编号不能为空");
				}
				if(StringUtils.isBlank(outerOrderHh.getIdCard())) {
					return result.buildIsSuccess(false).buildMsg("身份证ID为空");
				}
				if(StringUtils.isBlank(outerOrderHh.getTelephone())) {
					return result.buildIsSuccess(false).buildMsg("手机号为必填");
				}
				//如果海狐订单已存在，略过
				OuterOrder p = new OuterOrder();
				p.setTargetNo(outerOrderHh.getTargetNo());
				if(outerOrderMapper.selectCount(p) > 0) {
					return result.buildIsSuccess(false).buildMsg("推单重复");
				}
				OuterOrder outerOrder = new OuterOrder();
				outerOrder.setOrderNo("P"+String.format("%0"+2+"d", 1)+String.format("%0"+4+"d", 4)+"D"+DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDDHHMMSS)+sequenceUtilService.gainORDSequence());	//系统自动生成
				outerOrder.setOrderTime(new Date());
				outerOrder.setStatus(OrderStatus.INIT.getCode());
				outerOrder.setReceiver(outerOrderHh.getReceiver());
				outerOrder.setReceiverState(outerOrderHh.getReceiverState());
				outerOrder.setReceiverCity(outerOrderHh.getReceiverCity());
				outerOrder.setReceiverDistrict(outerOrderHh.getReceiverDistrict());
				outerOrder.setAddressDetail(outerOrderHh.getAddressDetail());
				outerOrder.setTelephone(outerOrderHh.getTelephone());
				outerOrder.setPostcode(outerOrderHh.getPostcode());
				outerOrder.setIdCard(outerOrderHh.getIdCard());					
				outerOrder.setRemark(outerOrderHh.getRemark());
				outerOrder.setTargetNo(outerOrderHh.getTargetNo());
				outerOrder.setPlatformType(PlatformType.HAIHU.getCode());
				outerOrder.setPayType(2);	//支付方式
				outerOrder.setUserCreate("海狐推送订单");
				outerOrder.setSalesName("海狐");
				outerOrder.setSalesId(12L);
				outerOrder.setCompanyId(this.channelAccount.getCompanyId());
				outerOrder.setGmtCreate(new Date());
				outerOrder.setGmtModify(new Date());
				outerOrderMapper.insert(outerOrder);  //添加主订单
				outOrderIdList.add(outerOrder.getId());
				List<OuterOrderDetail> outerOrderDetails = outerOrderHh.getOuterOrderDetails();
				List<OuterOrderDetail> outerOrderDetailList = new ArrayList<OuterOrderDetail>();
				for (OuterOrderDetail outerOrderDetail : outerOrderDetails) {
					OuterOrderDetail outerOrderDetailTemp = new OuterOrderDetail();
					outerOrderDetailTemp.setOuterOrderId(outerOrder.getId());
					outerOrderDetailTemp.setSkuCode(outerOrderDetail.getSkuCode());
					outerOrderDetailTemp.setSalePrice(outerOrderDetail.getSalePrice());
					outerOrderDetailTemp.setQuantity(outerOrderDetail.getQuantity());
					outerOrderDetailTemp.setGmtCreate(new Date());
					outerOrderDetailTemp.setGmtModify(new Date());
					outerOrderDetailTemp.setCompanyId(this.channelAccount.getCompanyId());
					outerOrderDetailList.add(outerOrderDetailTemp);
					//如果有虚拟库存就扣减虚拟库存
					ItemSku tjItemSku = new ItemSku();
					tjItemSku.setSkuCode(outerOrderDetail.getSkuCode());
					ItemSku itemSku = itemSkuService.selectOne(tjItemSku);
					if(itemSku != null) {
						Inventory inventory = inventoryService.queryInventoryBySkuId(itemSku.getItemId(), itemSku.getId());
						if(inventory.getVirtualInv()>0) {
							int virtualInv = inventory.getVirtualInv() - outerOrderDetail.getQuantity();
							virtualInv = virtualInv>0 ? virtualInv : 0;
							//如果虚拟库存小于等于可售库存，虚拟库存清零
							virtualInv = virtualInv>inventory.getTotalAvailableInv() ? virtualInv : 0;
							inventory.setVirtualInv(virtualInv);
							inventory.setGmtModify(new Date());
							inventoryService.updateSelectiveById(inventory);
						}
					}
				}
				outerOrderDetailMapper.insertBatch(outerOrderDetailList);				//添加子订单
				if(outOrderIdList.size() > 0) {
	    			//把商品详情更新到主订单明细里面
	    			outerOrderDetailMapper.updateOuterOrderDetailByItemSku(outOrderIdList);
	    			//生成子订单并配货
	    			outOrderIdList.forEach(erpOrderId -> {
//	    				outerOrderService.review(erpOrderId,"HH");
	    				outerOrderService.review(erpOrderId);
	    			});
	    		}
				result.buildMsg("推单成功").buildIsSuccess(true);
			} else {
				result.buildMsg("拒绝访问").buildIsSuccess(false);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			this.logger.error("读取流异常" + e1);
		}

		return result;
	}

	@Override
	public void syncOrder() {
	}
	
	@Override
	public Object syncOrder(Object data) {
		return null;
	}

	@Override
	public void syncLogisticsOnlineConfirm(List<ErpOrder> erpOrderList, ShippingOrder shippingOrder) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("packageNo", shippingOrder.getShippingNo());
		param.put("logisticsCompany", shippingOrder.getLogisticCompany());
		param.put("enteCode", "irhua");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStamp = dateFormat.format(new Date());
		String sign = Md5Util.getMD5("enteCode=irhua&timeStamp="+timeStamp);
		param.put("timeStamp", timeStamp);
		param.put("sign", sign);
		String targetNo = "";
//		List<Long> erpOrderIdList = HaiJsonUtils.toBean(shippingOrder.getErpOrderId(), new TypeReference<List<Long>>() {
//		});
//		List<ErpOrder> erpOrderList = erpOrderService.selectBatchIds(erpOrderIdList);
		List<Map<String, Object>> itemSkusList = new ArrayList<>();
		for (int j = 0; j < erpOrderList.size(); j++) {
			ErpOrder erpOrder = erpOrderList.get(j);
			Map<String, Object> itemSkusDetail = new HashMap<String, Object>();
			itemSkusDetail.put("skuCode", erpOrder.getSkuCode());
			itemSkusList.add(itemSkusDetail);
			param.put("itemSkusList", itemSkusList);
			targetNo = erpOrderList.get(0).getTargetNo();
		}
		param.put("erpOrderNo", targetNo);
		JSONObject json = JSONObject.fromObject(param);
		System.out.println(json);
		this.logger.error("同步发货给海狐 req: " + json);
		JSONObject description = HttpClientUtil.post("http://expressjob.haihu.com/erp/notify", null, param,"1");
		this.logger.error("同步发货给海狐 resp: " + description.toString());
		System.err.println(description);

		try {
			Integer respCode = (Integer) description.get("ResponseCode");
			if (respCode == 100) {
				shippingOrder.setSyncSendStatus(1);
				shippingOrderService.update(shippingOrder);
			}
		} catch (Exception e) {
			this.logger.error("同步发货给海狐 返回结果异常: " + description.toString());
		}
	}
}
