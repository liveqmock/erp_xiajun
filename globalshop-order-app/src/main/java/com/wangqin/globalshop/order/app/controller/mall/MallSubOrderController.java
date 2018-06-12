package com.wangqin.globalshop.order.app.controller.mall;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import com.wangqin.globalshop.common.enums.StockUpStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.PicModel;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.*;

import static com.wangqin.globalshop.order.app.comm.Constant.ORDER_SATUTS_INIT;

/**
 * @author liuhui
 *
 */
@Controller
@RequestMapping("/erpOrder")
@Authenticated
public class MallSubOrderController {

	@Autowired
	private IMallSubOrderService erpOrderService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private IShippingOrderService shippingOrderService;

	@RequestMapping(value = "/query",method = RequestMethod.POST)
	@ResponseBody
	public Object query(MallSubOrderVO mallSubOrderVO) {
		JsonResult<List<MallSubOrderDO>> result = new JsonResult<>();
		if(mallSubOrderVO.getEndGmtCreate()!=null) {
			String endGmtCreateStr = DateUtil.ymdFormat(mallSubOrderVO.getEndGmtCreate());
			Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
			mallSubOrderVO.setEndGmtCreate(endGmtCreate);
		}

//		mallSubOrderVO.setCompanyNo("MallSUbOrderController?JLJLJJLJ");

		result .buildData(erpOrderService.queryErpOrders(mallSubOrderVO));
		result.setSuccess(true);
		return result;
	}

	@RequestMapping("/queryById")
	@ResponseBody
	public Object queryById(Long id) {
		JsonResult<MallSubOrderDO> result = new JsonResult<>();
		MallSubOrderDO erpOrder = erpOrderService.selectById(id);
		result.buildIsSuccess(true).setData(erpOrder);
		return result;
	}
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(String erpOrder) {
		MallSubOrderDO orderDO = JSON.parseObject(erpOrder, MallSubOrderDO.class);
		JsonResult<MallSubOrderDO> result = new JsonResult<>();
//		ShiroUser shiroUser = this.getShiroUser();
//		erpOrder.setUserModify(shiroUser.getLoginName());
		orderDO.setModifier("mallSubOrderController((((");
		orderDO.setGmtModify(new Date());
		orderDO.setQuantity(null);//不能修改销售数量，需要在主订单哪里修改数量
		erpOrderService.update(orderDO);
		result.setSuccess(true);
		return result;
	}
	
	
	/**
	 * 子订单批量关闭
	 * @param orderIds
	 * @return
	 */
	@RequestMapping("/close")
	@ResponseBody
	public Object close(String orderIds, String closeReason) {
		List<String> errorMsg = Lists.newArrayList();
		if(StringUtils.isNotBlank(orderIds)){
			String s = orderIds.replace("&quot;", "\"");
			List<Long> orderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>(){});
			Set<String> mainIds = Sets.newHashSet();
			int i=0;
			for(Long orderId:orderIdList){
				i++;
				MallSubOrderDO erpOrder = erpOrderService.selectById(orderId);
				if(erpOrder==null){
					errorMsg.add("第"+i+"条订单数据有误,");
				}else{
 					if( ORDER_SATUTS_INIT == erpOrder.getStatus()){
						try{
							if(StringUtil.isNotBlank(closeReason)) {
								erpOrder.setCloseReason(closeReason);
							}
							erpOrderService.closeErpOrder(erpOrder);
							mainIds.add(erpOrder.getOrderNo());
						}catch(Exception e){
							errorMsg.add("第"+i+"条订单关闭失败,");
						}
					}else{
						errorMsg.add("第"+i+"条订单状态有误,");
					}
				}
				
			}
			//更新主订单发货状态
			if(CollectionUtils.isNotEmpty(mainIds)) {
				shippingOrderService.updateOuterstatus(mainIds);
			}
			String rmsg = "";
			if(CollectionUtils.isNotEmpty(errorMsg)){
				for(String a:errorMsg){
					rmsg+=a;
				}
				return JsonResult.buildFailed(rmsg);
			}else{
				return JsonResult.buildSuccess(null);
			}
		}else{
			return JsonResult.buildFailed("参数错误");
		}
	}
	
	
	@RequestMapping("/splitErpOrder")
	@ResponseBody
	public Object splitErpOrder(Long orderId,Integer splitCount) {
		if(orderId==null){
			return JsonResult.buildFailed("订单ID错误");
		}
		if(splitCount==null||splitCount<=0){
			return JsonResult.buildFailed("数量错误");
		}
		MallSubOrderDO erpOrder = erpOrderService.selectById(orderId);
		if(erpOrder==null){
			return JsonResult.buildFailed("未找到订单");
		}else{
			if(erpOrder.getQuantity()==1){
				return JsonResult.buildFailed("一个商品不能拆分");
			}
			if(erpOrder.getStatus()==ORDER_SATUTS_INIT){
				try{
					if(splitCount>=erpOrder.getQuantity()){
						return JsonResult.buildFailed("拆单数量不能超过订单数量");
					}
					erpOrderService.splitErpOrder(erpOrder, splitCount);
				}catch(InventoryException ie){
					return JsonResult.buildFailed(ie.getMessage());
				}catch(Exception ie){
					return JsonResult.buildFailed("未知异常");
				}
			}else{
				return JsonResult.buildFailed("子订单状态错误");
			}
		}
		return JsonResult.buildSuccess(null);
	}
	/**
	 * 释放库存
	 * @return
	 */
	@RequestMapping("/releaseInventory")
	@ResponseBody
	public Object releaseInventory(Long id) {
		if(id==null){
			return JsonResult.buildFailed("订单ID错误");
		}
		MallSubOrderDO erpOrder = erpOrderService.selectById(id);
		if(erpOrder==null){
			return JsonResult.buildFailed("未找到订单");
		}else{
			//订单状态校验
			if(ORDER_SATUTS_INIT.equals(erpOrder.getStatus())&&erpOrder.getStockStatus()!= StockUpStatus.RELEASED.getCode()&&erpOrder.getStockStatus()!=StockUpStatus.INIT.getCode()){
				inventoryService.release(erpOrder);
			}else{
				return JsonResult.buildFailed("订单状态错误");
			}
		}
		return JsonResult.buildSuccess(null);
	}
	
	/**
	 * 指定订单备货
	 * @return
	 */
	@RequestMapping("/lockErpOrder")
	@ResponseBody
	public Object lockErpOrder(Long id) {
		if(id==null){
			return JsonResult.buildFailed("订单ID错误");
		}
		MallSubOrderDO erpOrder = erpOrderService.selectById(id);
		if(erpOrder==null){
			return JsonResult.buildFailed("未找到订单");
		}else{
			JsonResult bizResult = null;
//			try {
//				bizResult = erpOrderService.lockErpOrder(erpOrder);
//			} catch (InventoryException e) {
//				e.printStackTrace();
//			}
			if(!bizResult.isSuccess()){
					return JsonResult.buildFailed(bizResult.getMsg());
				}
		}
		return JsonResult.buildSuccess(null);
	}
	
	/**
	 * 子订单批量重新分配库存
	 * @param orderIds
	 * @return
	 */
	@RequestMapping("/replayAssign")
	@ResponseBody
	public Object replayAssign(String orderIds) {
		List<String> errorMsg = Lists.newArrayList();
		if(StringUtils.isNotBlank(orderIds)){
			String s = orderIds.replace("&quot;", "\"");
			List<Long> orderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>(){});
			List<MallSubOrderDO> erpOrders = Lists.newArrayList();
			int i=0;
			String skuCode=null;
			for(Long orderId:orderIdList){
				i++;
				MallSubOrderDO erpOrder = erpOrderService.selectById(orderId);
				if(erpOrder==null){
					errorMsg.add("第"+i+"条订单数据有误,");
				}else{
					erpOrders.add(erpOrder);
					if(ORDER_SATUTS_INIT.equals(erpOrder.getStatus())){
						if(skuCode==null){
							skuCode = erpOrder.getSkuCode();
						}else{
							if(!skuCode.equals(erpOrder.getSkuCode())){
								errorMsg.add("第"+i+"条订单有不同的SKU,");
							}
						}
					}else{
						errorMsg.add("第"+i+"条订单状态有误,");
					}
				}
			}
			StringBuilder rmsg = new StringBuilder();
			if(CollectionUtils.isNotEmpty(errorMsg)){
				for(String a:errorMsg){
					rmsg.append(a);
				}
				return JsonResult.buildFailed(rmsg.toString());
			}else{
				if(erpOrders.size()>1){
					erpOrders.sort((a,b)-> {
						if(a.getId() > b.getId()) {
							return 1;
						} else {
							return -1;
						}
					});
				}
				//批量释放库存
				erpOrders.forEach(order->{
						if(order.getStockStatus()!=StockUpStatus.INIT.getCode()&&order.getStockStatus()!=StockUpStatus.RELEASED.getCode()){
							inventoryService.release(order);
						}
				});
//				//批量重新分配库存
//				erpOrders.forEach(order->{
//					try {
//						erpOrderService.lockErpOrder(order);
//					} catch (Exception e) {
//					}
//				});
				return JsonResult.buildSuccess(null);
			}
			
		}else{
			return JsonResult.buildFailed("参数错误");
		}
	}
	
	/**
	 * 导出excel
	 */
	@RequestMapping("/erpOrderExport")
    public ResponseEntity<byte[]> erpOrderExport(MallSubOrderVO erpOrderQueryVO) throws Exception {
    	List<List<Object>> rowDatas = new ArrayList<>();
    	if(erpOrderQueryVO.getStartGmtCreate()==null || erpOrderQueryVO.getEndGmtCreate()==null) {
			throw new ErpCommonException("必须选择创建时间段");
		}
    	String startGmtCreateStr = DateUtil.ymdFormat(erpOrderQueryVO.getStartGmtCreate());
		Date startGmtCreate = DateUtil.parseDate(startGmtCreateStr + " 00:00:00");
		erpOrderQueryVO.setStartGmtCreate(startGmtCreate);
		String endGmtCreateStr = DateUtil.ymdFormat(erpOrderQueryVO.getEndGmtCreate());
		Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
		erpOrderQueryVO.setEndGmtCreate(endGmtCreate);
		
    	List<MallSubOrderDO> erpOrderList = erpOrderService.queryErpOrderForExcel(erpOrderQueryVO);
    	if(erpOrderList != null) {
    		for (MallSubOrderDO erpOrder : erpOrderList) {
    			List<Object> list = new ArrayList<>();
    			//sku图片
    			String skuPic = erpOrder.getSkuPic();
    			if(StringUtil.isNotBlank(skuPic)) {
    				PicModel pm = HaiJsonUtils.toBean(skuPic, PicModel.class);
        			String imgSrc = pm.getPicList().get(0).getUrl();
        			String imgType = imgSrc.substring(imgSrc.lastIndexOf(".") + 1).toUpperCase();
        			if(imgSrc.contains("aliyuncs.com")) {
        				imgSrc += "?x-oss-process=image/resize,m_lfit,h_100,w_100";
        			} else {
        				imgSrc = imgSrc.replaceAll("Resource", "Thumbnail");
        			}
        			//System.out.println(imgSrc);
        			URL url = new URL(imgSrc);
        			BufferedImage image = ImageIO.read(url);
        			ByteArrayOutputStream output = new ByteArrayOutputStream();
        	        ImageIO.write(image, imgType, output);
        	        output.flush();
        	        list.add(output.toByteArray());
        	        output.close();
    			} else {
    				list.add(null);
    			}
    			
    	        list.add(erpOrder.getItemName());	//商品名称
    	        list.add(erpOrder.getScale());		//尺码
    	        list.add(erpOrder.getOrderNo());	//主订单号
    	        list.add(erpOrder.getSkuCode());	//商品条码
    	        list.add(erpOrder.getQuantity());	//销售数量
    	        list.add(erpOrder.getSalePrice());	//销售价格
    	        list.add(erpOrder.getQuantity()*erpOrder.getSalePrice());               //销售总价
    	        list.add(erpOrder.getStatus());		//订单状态
    	        list.add(StockUpStatus.of(erpOrder.getStockStatus()).getDescription());	//备货状态
    	        list.add(DateUtil.ymdFormat(erpOrder.getOrderTime()));					//创建时间
    	        list.add(erpOrder.getReceiver());	//收件人
    	        list.add(erpOrder.getTelephone());	//收件人电话
    	        list.add(erpOrder.getReceiverState() + erpOrder.getReceiverCity() + erpOrder.getReceiverDistrict() + "\r\n" + erpOrder.getReceiverAddress());		//地址
    	        list.add(erpOrder.getMemo());				//备注
    	        
    	        
    			rowDatas.add(list);
    		}
    	}
    	ExcelHelper excelHelper = new ExcelHelper();
    	String[] columnTitles = new String[]{"商品图片", "商品名称", "颜色", "尺码", "主订单号", "子订单号", "SKU代码", "订单来源", "销售数量",  "商品价格", "销售总价", "订单状态", "备货状态", "创建时间", "外部订单号","小程序客户", "收件人", "手机", "地址", "备注"};
    	Integer[] columnWidth = new Integer[]{10, 30, 10, 10, 20, 20 , 20, 12, 10, 10, 10, 10, 10, 12, 20,20 ,15, 15, 30, 20};
    	excelHelper.setErpOrderToSheet("Erp Order", columnTitles, rowDatas, columnWidth);
    	//excelHelper.writeToFile("/Users/liuyang/Work/test.xls");
    	
    	ResponseEntity<byte[]> filebyte = null;
    	ByteArrayOutputStream  out = excelHelper.writeToByteArrayOutputStream();
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	String fileName = "子订单(" + DateUtil.formatDate(startGmtCreate, "yyyyMMdd") + ").xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        
        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }
    @RequestMapping("/listChoice")
	private Object listChoice(Long id){
		JsonResult<Map<String, String>> result = new JsonResult();
		MallSubOrderDO subOrder = erpOrderService.selectById(id);
		InventoryDO aDo = inventoryService.selectByItemCodeAndSkuCode(subOrder.getItemCode(), subOrder.getSkuCode());
		Map<String, String> map = new HashMap<>();
		map.put("orderNo",subOrder.getOrderNo());
		map.put("skuCode",subOrder.getSkuCode());
		result.setData(map);
		result.setSuccess(true);
		return result;

	}

}
