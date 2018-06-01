package com.wangqin.globalshop.channel.service.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.constants.enums.PlatformType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemSkuVo;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemVo;
import com.wangqin.globalshop.channel.dal.haihu.OuterOrder;
import com.wangqin.globalshop.channel.dal.haihu.OuterOrderDetail;
import com.wangqin.globalshop.channel.dal.youzan.PicModel;
import com.wangqin.globalshop.common.utils.*;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Channel(type=ChannelType.HaiHu)
public class HaihuChannelServiceImpl extends AbstractChannelService {
	
	protected Logger logger = LogManager.getLogger(getClass());

	public HaihuChannelServiceImpl(ChannelAccountDO channelAccount) {
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
	public AdapterData adapterCreateItem(ItemVo item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterUpdateItem(ItemVo item, ChannelListingItemDO outerItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterListingItem(ItemVo item, ChannelListingItemDO outerItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterDelistingItem(ItemVo item, ChannelListingItemDO outerItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterData adapterUpdateSkuInventory(ChannelListingItemSkuDO sku, InventoryDO inventory) {
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
				List<ItemDO> items = itemService.queryHaihuByUptime(vo);
				if (CollectionUtils.isEmpty(items)) {
					return result.buildMsg("未找到符合条件的海狐商品").buildIsSuccess(false);
				}
				String itemRequestType = "0";	//代理
				for (ItemDO item : items) {
					Map<String, Object> param = new HashMap<String, Object>();
					Map<String, Object> paramDetail = new HashMap<String, Object>();
					paramDetail.put("id", item.getId());
					paramDetail.put("name", item.getItemName());
					paramDetail.put("categoryName", item.getCategoryName());
					paramDetail.put("itemCode", item.getItemCode());
					paramDetail.put("brand", item.getBrandName());
					
					if (item.getCategoryCode() != null) {
						paramDetail.put("categoryId", item.getCategoryCode());
					}
					
					try {
						PicModel pm = HaiJsonUtils.toBean(item.getMainPic(), PicModel.class);
						List<String> imgList = new ArrayList<String>();
						for(PicModel.PicList pic : pm.getPicList()){
							imgList.add(pic.getUrl());
						}

						paramDetail.put("imgList", imgList);
					} catch (Exception e) {
						logger.error("haihu 同步商品图片异常! ", e);
					}
					
					List<Map<String, Object>> itemSkusList = new ArrayList<>();
					ItemSkuDO itemSkuso = new ItemSkuDO();
					itemSkuso.setItemCode(item.getItemCode());
					List<ItemSkuVo> itemSkusAll = itemSkuService.queryVoList(itemSkuso);

					Map<String, ItemSkuVo> itemSkuMap = Maps.newHashMap();
					for(ItemSkuVo itemSku : itemSkusAll){
						itemSkuMap.put(itemSku.getItemCode(), itemSku);
					}

					List<ItemSkuVo> itemSkus = itemSkuService.queryItemSkusForItemThirdSale(item.getId());
					if (!itemSkus.isEmpty()) {
						for (ItemSkuVo itemSku : itemSkus) {
							itemSkuMap.get(itemSku.getItemCode()).setItemSkuQuantity(itemSku.getItemSkuQuantity());
							if(itemSku.getItemSkuQuantity()!=null && itemSku.getItemSkuQuantity()>0) {
								itemRequestType = "1";
							}
						}
					}
					
					for(int i=0; i<itemSkusAll.size(); i++) {
						ItemSkuVo itemSku = itemSkusAll.get(i);
						Map<String, Object> itemSkusDetail = new HashMap<String, Object>();
						itemSkusDetail.put("skuCode", itemSku.getSkuCode());
						itemSkusDetail.put("upc", itemSku.getUpc());
						itemSkusDetail.put("color", itemSku.getColor());
						itemSkusDetail.put("scale", itemSku.getScale());
						itemSkusDetail.put("weight", itemSku.getWeight());
						if(itemRequestType.contentEquals("0")) {	//代理
							InventoryDO inventory = inventoryService.queryInventoryByCode(item.getItemCode(), itemSku.getSkuCode());
							Long totalAvailableInv= inventory.getInv()-inventory.getLockedInv()+inventory.getTransInv()-inventory.getLockedTransInv();
							itemSkusDetail.put("itemskuQuantity", totalAvailableInv);

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
						mallSubOrderService.splithaihuErpOrder(outerOrderDetailList, targetNo);
					} catch (ErpCommonException e) {
						this.logger.error("",e);
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

				//海狐保持1.0的数据结构

				OuterOrder outerOrderHh = HaiJsonUtils.toBean(s, new TypeReference<OuterOrder>(){});
				System.out.println("1"+s);
				List<String> outOrderIdList = new ArrayList<String>();
				if(StringUtils.isBlank(outerOrderHh.getTargetNo())) {
					return result.buildIsSuccess(false).buildMsg("订单编号不能为空");
				}
				if(StringUtils.isBlank(outerOrderHh.getIdCard())) {
					return result.buildIsSuccess(false).buildMsg("身份证ID为空");
				}
				if(StringUtils.isBlank(outerOrderHh.getTelephone())) {
					return result.buildIsSuccess(false).buildMsg("手机号为必填");
				}
				//如果海狐订单已存在，略过.然后2.0转换成mallOrder
				MallOrderDO p = new MallOrderDO();
				p.setChannelOrderNo(outerOrderHh.getTargetNo());
				if(outerOrderMapper.queryPoCount(p) > 0) {
					return result.buildIsSuccess(false).buildMsg("推单重复");
				}
				MallOrderDO outerOrder = new MallOrderDO();
				outerOrder.setOrderNo("P"+String.format("%0"+2+"d", 1)+String.format("%0"+4+"d", 4)+"D"+DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDDHHMMSS)+sequenceUtilService.gainORDSequence());	//系统自动生成
				outerOrder.setOrderTime(new Date());
				outerOrder.setStatus(OrderStatus.INIT.getCode());

				outerOrder.setCompanyNo(channelAccount.getCompanyNo());
				outerOrder.setChannelNo(channelAccount.getChannelNo());
				outerOrder.setChannelName(channelAccount.getChannelName());
				outerOrder.setChannelType(channelAccount.getType().toString());
				outerOrder.setShopCode(channelAccount.getShopCode());


				outerOrder.setIdCard(outerOrderHh.getIdCard());					
				outerOrder.setMemo(outerOrderHh.getRemark());
				outerOrder.setChannelOrderNo(outerOrderHh.getTargetNo());
				outerOrder.setChannelType(PlatformType.HAIHU.getCode()+"");
				outerOrder.setPayType(2);	//支付方式
				outerOrder.setCreator("海狐推送订单");

				outerOrder.setCompanyNo(this.channelAccount.getCompanyNo());
				outerOrder.setGmtCreate(new Date());
				outerOrder.setGmtModify(new Date());
				outerOrderMapper.insert(outerOrder);  //添加主订单
				outOrderIdList.add(outerOrder.getOrderNo());

				List<OuterOrderDetail> outerOrderDetails = outerOrderHh.getOuterOrderDetails();
				List<MallSubOrderDO> outerOrderDetailList = new ArrayList<MallSubOrderDO>();
				for (OuterOrderDetail outerOrderDetail : outerOrderDetails) {
					MallSubOrderDO outerOrderDetailTemp = new MallSubOrderDO();
					outerOrderDetailTemp.setChannelOrderNo(outerOrder.getChannelOrderNo());
					outerOrderDetailTemp.setSkuCode(outerOrderDetail.getSkuCode());
					outerOrderDetailTemp.setSalePrice(outerOrderDetail.getSalePrice());
					outerOrderDetailTemp.setQuantity(outerOrderDetail.getQuantity());
					outerOrderDetailTemp.setGmtCreate(new Date());
					outerOrderDetailTemp.setGmtModify(new Date());
					outerOrderDetailTemp.setCompanyNo(this.channelAccount.getCompanyNo());
					outerOrderDetailTemp.setReceiver(outerOrderHh.getReceiver());
					outerOrderDetailTemp.setReceiverState(outerOrderHh.getReceiverState());
					outerOrderDetailTemp.setReceiverCity(outerOrderHh.getReceiverCity());
					outerOrderDetailTemp.setReceiverDistrict(outerOrderHh.getReceiverDistrict());
					outerOrderDetailTemp.setReceiverAddress(outerOrderHh.getAddressDetail());
					outerOrderDetailTemp.setTelephone(outerOrderHh.getTelephone());
					outerOrderDetailTemp.setPostcode(outerOrderHh.getPostcode());
					outerOrderDetailTemp.setChannelName(ChannelType.HaiHu.getName());


					outerOrderDetailTemp.setCustomerNo("无");
					outerOrderDetailTemp.setIsDel(false);
					outerOrderDetailTemp.setCreator("系统");
					outerOrderDetailTemp.setModifier("系统");
					outerOrderDetailTemp.setChannelOrderNo(outerOrder.getChannelOrderNo());



					outerOrderDetailList.add(outerOrderDetailTemp);
					//如果有虚拟库存就扣减虚拟库存
					ItemSkuDO tjItemSku = new ItemSkuDO();
					tjItemSku.setSkuCode(outerOrderDetail.getSkuCode());
					ItemSkuDO itemSku = itemSkuService.queryPo(tjItemSku);
					if(itemSku != null) {
						InventoryDO inventory = inventoryService.queryInventoryByCode(itemSku.getItemCode(), itemSku.getSkuCode());
						if(inventory.getVirtualInv()>0) {
							Long totalAvailableInv= inventory.getInv()-inventory.getLockedInv()+inventory.getTransInv()-inventory.getLockedTransInv();
							Long virtualInv = inventory.getVirtualInv() - outerOrderDetail.getQuantity();
							virtualInv = virtualInv>0 ? virtualInv : 0;
							//如果虚拟库存小于等于可售库存，虚拟库存清零
							virtualInv = virtualInv>totalAvailableInv ? virtualInv : 0;
							inventory.setVirtualInv(virtualInv);
							inventory.setGmtModify(new Date());
							inventoryService.updateByPrimaryKey(inventory);
						}
					}
				}
				mallSubOrderService.insertBatch(outerOrderDetailList);				//添加子订单
				if(outOrderIdList.size() > 0) {
	    			//把商品详情更新到主订单明细里面
	    			outerOrderDetailMapper.updateOuterOrderDetailByItemSku(outOrderIdList);
	    			//生成子订单并配货
					outerOrderService.reviewByIdList(outOrderIdList);
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
    public void syncLogisticsOnlineConfirm(List<MallSubOrderDO> erpOrderList, ShippingOrderDO shippingOrder) {
        // TODO Auto-generated method stub
        
    }

//	@Override
//	public void syncLogisticsOnlineConfirm(List<MallSubOrderDO> erpOrderList, ShippingOrderDO shippingOrder) {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("packageNo", shippingOrder.getShippingNo());
//		param.put("logisticsCompany", shippingOrder.getLogisticCompany());
//		param.put("enteCode", "irhua");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String timeStamp = dateFormat.format(new Date());
//		String sign = Md5Util.getMD5("enteCode=irhua&timeStamp="+timeStamp);
//		param.put("timeStamp", timeStamp);
//		param.put("sign", sign);
//		String targetNo = "";
////		List<Long> erpOrderIdList = HaiJsonUtils.toBean(shippingOrder.getErpOrderId(), new TypeReference<List<Long>>() {
////		});
////		List<ErpOrder> erpOrderList = erpOrderService.selectBatchIds(erpOrderIdList);
//		List<Map<String, Object>> itemSkusList = new ArrayList<>();
//		for (int j = 0; j < erpOrderList.size(); j++) {
//			MallSubOrderDO erpOrder = erpOrderList.get(j);
//			Map<String, Object> itemSkusDetail = new HashMap<String, Object>();
//			itemSkusDetail.put("skuCode", erpOrder.getSkuCode());
//			itemSkusList.add(itemSkusDetail);
//			param.put("itemSkusList", itemSkusList);
//			targetNo = erpOrderList.get(0).getChannelOrderNo();
//		}
//		param.put("erpOrderNo", targetNo);
//		JSONObject json = JSONObject.fromObject(param);
//		System.out.println(json);
//		this.logger.error("同步发货给海狐 req: " + json);
//		JSONObject description = HttpClientUtil.post("http://expressjob.haihu.com/erp/notify", null, param,"1");
//		this.logger.error("同步发货给海狐 resp: " + description.toString());
//		System.err.println(description);
//
//		try {
//			Integer respCode = (Integer) description.get("ResponseCode");
//			if (respCode == 100) {
//				shippingOrder.setSyncSendStatus(1);
//				shippingOrderService.updateByPrimaryKey(shippingOrder);
//			}
//		} catch (Exception e) {
//			this.logger.error("同步发货给海狐 返回结果异常: " + description.toString());
//		}
//	}
}
