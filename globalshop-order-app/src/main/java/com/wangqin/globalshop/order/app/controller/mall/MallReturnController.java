package com.wangqin.globalshop.order.app.controller.mall;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallReturnOrderVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.order.app.service.mall.IMallReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liuyang
 *
 */
@Controller
@RequestMapping("/erpReturnOrder")
@Authenticated
public class MallReturnController {
	@Autowired
	private IMallReturnOrderService erpReturnOrderService;
//	@Autowired
//	private IMallSubOrderService erpOrderService;
//	@Autowired
//	private IShippingOrderService shippingOrderService;
	
	@PostMapping("/index")
	@ResponseBody
	public Object index() {
		JsonResult<List<MallReturnOrderDO>> result= new JsonResult<>();
//		if(mallReturnOrderDO.getEndGmtCreate()!=null) {
//			String endGmtCreateStr = DateUtil.ymdFormat(erpReturnOrderQueryVO.getEndGmtCreate());
//			Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
//			erpReturnOrderQueryVO.setEndGmtCreate(endGmtCreate);
//		}
		List<MallReturnOrderDO> list = erpReturnOrderService.list();
		result.setData(list);
		result.setSuccess(true);
		return result;
	}
	
//	@RequestMapping("/queryById")
//	@ResponseBody
//	public Object queryById(Long id) {
//		JsonResult<ErpReturnOrder> result = new JsonResult<>();
//		ErpReturnOrder erpReturnOrder = erpReturnOrderService.selectById(id);
//		if(erpReturnOrder == null) {
//			return result.buildIsSuccess(false).buildMsg("查询异常");
//		}
//		ErpOrder erpOrder = erpOrderService.selectById(erpReturnOrder.getErpOrderId());
//		erpReturnOrder.setItemName(erpOrder.getItemName());
//		erpReturnOrder.setSkuCode(erpOrder.getSkuCode());
//		erpReturnOrder.setUpc(erpOrder.getUpc());
//		erpReturnOrder.setSkuPic(erpOrder.getSkuPic());
//		result.buildIsSuccess(true).setData(erpReturnOrder);
//		return result;
//	}
//
	@PostMapping("/add")
	@ResponseBody
	public Object add(MallReturnOrderVO erpReturnOrder) {
		JsonResult<MallReturnOrderVO> result = new JsonResult<>();
		if(erpReturnOrder.getErpOrderId()==null) {
			return result.buildIsSuccess(false).buildMsg("参数异常");
		}
		erpReturnOrder.setReturnRefer(0);
		try {
			erpReturnOrderService.add(erpReturnOrder);
		}catch (ErpCommonException exception){
			return result.buildIsSuccess(false).buildMsg(exception.getErrorMsg());
		}
		result.setSuccess(true);
		return result;
	}

//	@RequestMapping("/update")
//	@ResponseBody
//	public Object update(ErpReturnOrder erpReturnOrder) {
//		JsonResult<ErpReturnOrder> result = new JsonResult<>();
//		ShiroUser shiroUser = this.getShiroUser();
//		erpReturnOrder.setUserModify(shiroUser.getLoginName());
//		erpReturnOrder.setGmtModify(new Date());
//		erpReturnOrderService.updateSelectiveById(erpReturnOrder);
//		if(erpReturnOrder.getStatus() == -1) {	//关闭退单
//			ErpReturnOrder selErpReturnOrder = erpReturnOrderService.selectById(erpReturnOrder.getId());
//			//恢复子订单状态
//			ErpOrder selErpOrder = erpOrderService.selectById(selErpReturnOrder.getErpOrderId());
//			if(selErpOrder.getStatus() == -1) {
//				selErpOrder.setStatus(0);
//				selErpOrder.setCloseReason("");
//				erpOrderService.updateSelectiveById(selErpOrder);
//				//对子订单分配库存
//				try {
//					erpOrderService.lockErpOrder(selErpOrder);
//				} catch (InventoryException e) {
//					logger.error("关闭退单", e);
//				}
//
//				Set<Long> mainIds = Sets.newHashSet();
//				mainIds.add(selErpOrder.getOuterOrderId());
//				shippingOrderService.updateOuterstatus(mainIds);
//			}
//		}
//		result.setSuccess(true);
//		return result;
//	}
//	/**
//	 * 根据退单导出excel表
//	 */
//	@RequestMapping("/erpReturnOrderExport")
//    public ResponseEntity<byte[]> erpOrderExport(ErpReturnOrderQueryVO erpReturnOrderQueryVO) throws Exception {
//    	List<List<Object>> rowDatas = new ArrayList<>();
//    	if(erpReturnOrderQueryVO.getStartGmtCreate() != null) {
//    		String startGmtCreateStr = DateUtil.ymdFormat(erpReturnOrderQueryVO.getStartGmtCreate());
//    		Date startGmtCreate= DateUtil.parseDate(startGmtCreateStr + " 00:00:00");
//    		erpReturnOrderQueryVO.setStartGmtCreate(startGmtCreate);
//    	}
//    	if(erpReturnOrderQueryVO.getEndGmtCreate() != null) {
//    		String endGmtCreateStr = DateUtil.ymdFormat(erpReturnOrderQueryVO.getEndGmtCreate());
//    		Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
//    		erpReturnOrderQueryVO.setEndGmtCreate(endGmtCreate);
//    	}
//
//    	 List<ErpReturnOrder> erpReturnOrderList = erpReturnOrderService.queryErpReturnOrderForExcel(erpReturnOrderQueryVO);
//    	if(erpReturnOrderList != null) {
//    		for (ErpReturnOrder erpReturnOrder : erpReturnOrderList) {
//    			List<Object> list = new ArrayList<>();
//    			//sku图片
//    			String skuPic = erpReturnOrder.getSkuPic();
//    			if(StringUtil.isNotBlank(skuPic)) {
//    				PicModel pm = HaiJsonUtils.toBean(skuPic, PicModel.class);
//        			String imgSrc = pm.getPicList().get(0).getUrl();
//        			String imgType = imgSrc.substring(imgSrc.lastIndexOf(".") + 1).toUpperCase();
//        			if(imgSrc.contains("aliyuncs.com")) {
//        				imgSrc += "?x-oss-process=image/resize,m_lfit,h_100,w_100";
//        			} else {
//        				imgSrc = imgSrc.replaceAll("Resource", "Thumbnail");
//        			}
//        			//System.out.println(imgSrc);
//        			URL url = new URL(imgSrc);
//        			BufferedImage image = ImageIO.read(url);
//        			ByteArrayOutputStream output = new ByteArrayOutputStream();
//        	        ImageIO.write(image, imgType, output);
//        	        output.flush();
//        	        list.add(output.toByteArray());
//        	        output.close();
//    			} else {
//    				list.add(null);
//    			}
//
//    	        list.add(erpReturnOrder.getItemName());	     //商品名称
//    	        list.add(erpReturnOrder.getOrderNo());	     //主订单号
//    	        list.add(erpReturnOrder.getErpNo());	     //子订单号
//    	        list.add(erpReturnOrder.getSkuCode());	     //SKU代码
//    	        list.add(erpReturnOrder.getUpc());           //upc
//    	        list.add(erpReturnOrder.getReturnReason());	 //退单原因
//    	        list.add(erpReturnOrder.getReturnReasonDetail());	 //退单原因详情
//    	        list.add(OrderStatus.of(erpReturnOrder.getStatus()).getDescription());	//状态
//    	        list.add(erpReturnOrder.getReturnQuantity());	     //退货数量
//    	        list.add(erpReturnOrder.getReturnPrice());           //退款金额
//    	        list.add(erpReturnOrder.getIsGn());                  //是否国内退货
//    	        list.add(erpReturnOrder.getIsCheckin());             //是否入库
//    	        list.add(DateUtil.ymdFormat(erpReturnOrder.getReceiveTime()));          //收货时间
//    	        list.add(DateUtil.ymdFormat(erpReturnOrder.getReturnPayTime()));        //退款时间
//    	        list.add(erpReturnOrder.getSalesName());             //售货员
//    	        list.add(erpReturnOrder.getRemark());		         //备注
//
//    			rowDatas.add(list);
//    		}
//    	}
//    	ExcelHelper excelHelper = new ExcelHelper();
//    	String[] columnTitles = new String[]{"sku图片", "商品名称", "主订单号", "子订单号", "SKU代码", "upc", "退单原因",  "退单原因详情", "状态", "退货数量", "退款金额", "是否国内退货", "是否入库", "收货时间", "退款时间", "销售员", "备注"};
//    	Integer[] columnWidth = new Integer[]{10, 30, 20, 20, 20, 20, 20, 20, 15, 15, 15, 15, 15, 20, 15, 10, 20};
//    	excelHelper.setErpReturnOrderToSheet("ErpReturnOrder", columnTitles, rowDatas, columnWidth);
//
//    	ResponseEntity<byte[]> filebyte = null;
//    	ByteArrayOutputStream  out = excelHelper.writeToByteArrayOutputStream();
//    	HttpHeaders headers = new HttpHeaders();
//    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//    	String fileName = "退货单.xlsx";
//        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//
//        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
//        out.close();
//        excelHelper.close();
//        return filebyte;
//    }
}
