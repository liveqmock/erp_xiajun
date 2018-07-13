package com.wangqin.globalshop.order.app.controller.mall;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import com.wangqin.globalshop.biz1.app.vo.MallSubOrderExcelVO;
import com.wangqin.globalshop.common.enums.StockUpStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.item.OrderItemSkuScaleService;
import com.wangqin.globalshop.order.app.service.item.OrderItemSkuService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.*;


/**
 * @author liuhui
 *
 */
@Controller
@RequestMapping("/erpOrder")
@Authenticated
public class MallSubOrderController {

	//APEX使用,@author:xiajun
	private static final Double GROSS_GAIN = 300.0;//毛重比净重大300克
	private static final String SENDER = "爱派客官方微店";//发货人
	private static final String SENDER_ADDRESS = "香港新界元朗流浮山廈村屏廈路丈量約份第129約第3141號地段";//发货人地址
	private static final String SENDER_PHONE = "21561899";//发货人电话

	@Autowired
	private IMallSubOrderService erpOrderService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private IShippingOrderService shippingOrderService;
	@Autowired
	private OrderItemSkuService orderItemSkuService;
	@Autowired
	private OrderItemSkuScaleService orderItemSkuScaleService;
	@Autowired
    private IMallOrderService orderService;


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@RequestParam("orderNo") String orderNo) {
        JsonResult<List<MallSubOrderVO>> result = new JsonResult<>();
        MallSubOrderVO erpOrderQueryVO = new MallSubOrderVO();
        erpOrderQueryVO.setOrderNo(orderNo);
        List<MallSubOrderDO> subOrderDOS = erpOrderService.queryErpOrders(erpOrderQueryVO);
        if (CollectionUtils.isNotEmpty(subOrderDOS)) {
            List<MallSubOrderVO> voList = new ArrayList<>();
            for (MallSubOrderDO orderDO : subOrderDOS) {
                MallSubOrderVO tmpVO = new MallSubOrderVO();
                BeanUtils.copy(orderDO, tmpVO);
                //修改原来是将json格式转换成url格式 现在直接使用json格式
//				if (StringUtils.isNotBlank(tmpVO.getSkuPic())){
//					JSONObject jsonObject = JSONObject.fromObject(tmpVO.getSkuPic());
//					JSONArray array = jsonObject.getJSONArray("picList");
//					JSONObject imgObject = array.getJSONObject(0);
//					String picUrl = imgObject.getString("url");
//					tmpVO.setSkuImg(picUrl);
//				}
                voList.add(tmpVO);
            }
            result.buildData(voList);
        } else {
            result.buildData(Collections.emptyList());
        }
        result.setSuccess(true);
        return result.buildIsSuccess(true);
    }

    @PostMapping("/return")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object returnOrder(String subOrderNo) {
        JsonResult<MallSubOrderDO> result = new JsonResult<>();
        MallSubOrderDO mallSubOrder;
        try {
            mallSubOrder = erpOrderService.selectBySubOrderNo(subOrderNo);
            MallOrderDO mallOrder = orderService.selectByOrderNo(mallSubOrder.getOrderNo());
            if (mallOrder == null){
                throw new ErpCommonException("找不到对应的主订单");
            }
            mallSubOrder.setStatus(OrderStatus.CLOSE.getCode());
            List<MallSubOrderDO> list = erpOrderService.selectByOrderNo(mallOrder.getOrderNo());
            int mallOrderStatus = getMallOrderStatus(list);
            mallOrder.setStatus(mallOrderStatus);
            erpOrderService.update(mallSubOrder);
            orderService.updateById(mallOrder);
            inventoryService.returns(mallSubOrder);
        } catch (ErpCommonException e) {
            return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true).buildData(mallSubOrder);
    }

    private int getMallOrderStatus(List<MallSubOrderDO> list) {
        //todo  算出应该的状态
        if (list.size()==1){
            return OrderStatus.CLOSE.getCode();
        }
        return OrderStatus.SENT.getCode();
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(MallSubOrderVO mallSubOrderVO) {
        JsonResult<List<MallSubOrderDO>> result = new JsonResult<>();
        if (mallSubOrderVO.getEndGmtCreate() != null) {
            String endGmtCreateStr = DateUtil.ymdFormat(mallSubOrderVO.getEndGmtCreate());
            Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
            mallSubOrderVO.setEndGmtCreate(endGmtCreate);
        }

//		mallSubOrderVO.setCompanyNo("MallSUbOrderController?JLJLJJLJ");

        result.buildData(erpOrderService.queryErpOrders(mallSubOrderVO));
        result.setSuccess(true);
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public Object queryById(Long id) {
        JsonResult<MallSubOrderDO> result = new JsonResult<>();
        MallSubOrderDO erpOrder = erpOrderService.selectById(id);
        result.buildIsSuccess(true).setData(erpOrder);
        return result.buildIsSuccess(true);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(MallSubOrderDO orderDO) {
        JsonResult<MallSubOrderDO> result = new JsonResult<>();
//		ShiroUser shiroUser = this.getShiroUser();
//		erpOrder.setUserModify(shiroUser.getLoginName());
        orderDO.setModifier("mallSubOrderController((((");
        orderDO.setGmtModify(new Date());
        orderDO.setQuantity(null);//不能修改销售数量，需要在主订单哪里修改数量
        erpOrderService.update(orderDO);
        result.setSuccess(true);
        return result.buildIsSuccess(true);
    }


    /**
     * 子订单批量关闭
     *
     * @param orderIds
     * @return
     */
    @RequestMapping("/close")
    @ResponseBody
    public Object close(String orderIds, String closeReason) {
        List<String> errorMsg = Lists.newArrayList();
        if (StringUtils.isNotBlank(orderIds)) {
            String s = orderIds.replace("&quot;", "\"");
            List<Long> orderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
            });
            Set<String> mainIds = Sets.newHashSet();
//            int i = 0;
            for (Long orderId : orderIdList) {
//                i++;
                MallSubOrderDO erpOrder = erpOrderService.selectById(orderId);
                if (erpOrder == null) {
                    errorMsg.add("找不到对应的订单id:" + orderId);
                } else {
                    if (Objects.equals(OrderStatus.INIT.getCode(), erpOrder.getStatus())) {
                        try {
                            if (StringUtil.isNotBlank(closeReason)) {
                                erpOrder.setCloseReason(closeReason);
                            }
                            erpOrderService.closeErpOrder(erpOrder);
                            mainIds.add(erpOrder.getOrderNo());
                            //更新子订单相应的占用库存
                            inventoryService.tryRelease(erpOrder);
                        } catch (Exception e) {
                            errorMsg.add("只允许修改待付款单的订单");
                        }
                    } else {
                        errorMsg.add("id" + orderId + "状态不为代付款状态,不允许关闭");
                    }
                }

            }
            //更新主订单发货状态
            if (CollectionUtils.isNotEmpty(mainIds)) {
                shippingOrderService.updateOuterstatus(mainIds);
            }
            //更新子订单相应的占用库存
//            for (Long orderId : orderIdList) {
////                i++;
//                MallSubOrderDO erpOrder = erpOrderService.selectById(orderId);
//                if(erpOrder!=null){
////					inventoryService.release(erpOrder);
//                    //换成另一个方法试试
//                    inventoryService.tryRelease(erpOrder);
//                }
//            }
            String rmsg = "";
            if (CollectionUtils.isNotEmpty(errorMsg)) {
                for (String a : errorMsg) {
                    rmsg += a;
                }
                return JsonResult.buildFailed(rmsg);
            } else {
                return JsonResult.buildSuccess(null);
            }
        } else {
            return JsonResult.buildFailed("参数错误");
        }
    }


    @RequestMapping("/splitErpOrder")
    @ResponseBody
    public Object splitErpOrder(Long orderId, Integer splitCount) {
        if (orderId == null) {
            return JsonResult.buildFailed("订单ID错误");
        }
        if (splitCount == null || splitCount <= 0) {
            return JsonResult.buildFailed("数量错误");
        }
        MallSubOrderDO erpOrder = erpOrderService.selectById(orderId);
        if (erpOrder == null) {
            return JsonResult.buildFailed("未找到订单");
        } else {
            if (erpOrder.getQuantity() == 1) {
                return JsonResult.buildFailed("一个商品不能拆分");
            }
            if (Objects.equals(erpOrder.getStatus(), OrderStatus.INIT.getCode())) {
                try {
                    if (splitCount >= erpOrder.getQuantity()) {
                        return JsonResult.buildFailed("拆单数量不能超过订单数量");
                    }
                    erpOrderService.splitErpOrder(erpOrder, splitCount);
                } catch (InventoryException ie) {
                    return JsonResult.buildFailed(ie.getMessage());
                } catch (Exception ie) {
                    return JsonResult.buildFailed("未知异常");
                }
            } else {
                return JsonResult.buildFailed("子订单状态错误");
            }
        }
        return JsonResult.buildSuccess(null);
    }

    /**
     * 释放库存
     *
     * @return
     */
    @RequestMapping("/releaseInventory")
    @ResponseBody
    public Object releaseInventory(Long id) {
        if (id == null) {
            return JsonResult.buildFailed("订单ID错误");
        }
        MallSubOrderDO erpOrder = erpOrderService.selectById(id);
        if (erpOrder == null) {
            return JsonResult.buildFailed("未找到订单");
        } else {
            //订单状态校验
            if (OrderStatus.INIT.getCode() == erpOrder.getStatus() && erpOrder.getStockStatus() != StockUpStatus.RELEASED.getCode() && erpOrder.getStockStatus() != StockUpStatus.INIT.getCode()) {
                inventoryService.release(erpOrder);
            } else {
                return JsonResult.buildFailed("订单状态错误");
            }
        }
        return JsonResult.buildSuccess(null);
    }

    /**
     * 指定订单备货
     *
     * @return
     */
    @RequestMapping("/lockErpOrder")
    @ResponseBody
    public Object lockErpOrder(Long id) {
        if (id == null) {
            return JsonResult.buildFailed("订单ID错误");
        }
        MallSubOrderDO erpOrder = erpOrderService.selectById(id);
        if (erpOrder == null) {
            return JsonResult.buildFailed("未找到订单");
        } else {
            JsonResult bizResult = null;
//			try {
//				bizResult = erpOrderService.lockErpOrder(erpOrder);
//			} catch (InventoryException e) {
//				e.printStackTrace();
//			}
            if (!bizResult.isSuccess()) {
                return JsonResult.buildFailed(bizResult.getMsg());
            }
        }
        return JsonResult.buildSuccess(null);
    }

    /**
     * 子订单批量重新分配库存
     *
     * @param orderIds
     * @return
     */
    @RequestMapping("/replayAssign")
    @ResponseBody
    public Object replayAssign(String orderIds) {
        List<String> errorMsg = Lists.newArrayList();
        if (StringUtils.isNotBlank(orderIds)) {
            String s = orderIds.replace("&quot;", "\"");
            List<Long> orderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
            });
            List<MallSubOrderDO> erpOrders = Lists.newArrayList();
            int i = 0;
            String skuCode = null;
            for (Long orderId : orderIdList) {
                i++;
                MallSubOrderDO erpOrder = erpOrderService.selectById(orderId);
                if (erpOrder == null) {
                    errorMsg.add("第" + i + "条订单数据有误,");
                } else {
                    erpOrders.add(erpOrder);
                    if (OrderStatus.INIT.getCode() == erpOrder.getStatus()) {
                        if (skuCode == null) {
                            skuCode = erpOrder.getSkuCode();
                        } else {
                            if (!skuCode.equals(erpOrder.getSkuCode())) {
                                errorMsg.add("第" + i + "条订单有不同的SKU,");
                            }
                        }
                    } else {
                        errorMsg.add("第" + i + "条订单状态有误,");
                    }
                }
            }
            StringBuilder rmsg = new StringBuilder();
            if (CollectionUtils.isNotEmpty(errorMsg)) {
                for (String a : errorMsg) {
                    rmsg.append(a);
                }
                return JsonResult.buildFailed(rmsg.toString());
            } else {
                if (erpOrders.size() > 1) {
                    erpOrders.sort((a, b) -> {
                        if (a.getId() > b.getId()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    });
                }
                //批量释放库存
                erpOrders.forEach(order -> {
                    if (order.getStockStatus() != StockUpStatus.INIT.getCode() && order.getStockStatus() != StockUpStatus.RELEASED.getCode()) {
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
     * @author:xiajun
	 */
	@RequestMapping("/erpOrderExport")
    public ResponseEntity<byte[]> erpOrderExport(MallSubOrderVO erpOrderQueryVO) throws Exception {
    	String companyNo = AppUtil.getLoginUserCompanyNo();
    	if(IsEmptyUtil.isStringEmpty(companyNo)) {
    		throw new ErpCommonException("请先登录");
    	}
    	erpOrderQueryVO.setCompanyNo(companyNo);

    	List<MallSubOrderExcelVO> erpOrderList = new ArrayList<MallSubOrderExcelVO>();
    	List<List<Object>> rowDatas = new ArrayList<>();
    	//如果勾选了，不考虑时间范围，只导出勾选的几项
    	if(IsEmptyUtil.isStringNotEmpty(erpOrderQueryVO.getCheckedSubOrderIdString())) {
    		String ids[] = erpOrderQueryVO.getCheckedSubOrderIdString().split(",");
    		List<Long> idList = new ArrayList<Long>();
    		for(String id:ids) {
    			idList.add(Long.parseLong(id));
    		}
    		erpOrderList = erpOrderService.queryErpOrderForExcelByIdList(idList);
    	} else {//如果没勾选，考虑时间范围
        	if(null != erpOrderQueryVO.getStartGmtCreate() && null != erpOrderQueryVO.getEndGmtCreate()) {
        		String startGmtCreateStr = DateUtil.ymdFormat(erpOrderQueryVO.getStartGmtCreate());
        		Date startGmtCreate = DateUtil.parseDate(startGmtCreateStr + " 00:00:00");
        		erpOrderQueryVO.setStartGmtCreate(startGmtCreate);
        		String endGmtCreateStr = DateUtil.ymdFormat(erpOrderQueryVO.getEndGmtCreate());
        		Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
        		erpOrderQueryVO.setEndGmtCreate(endGmtCreate);
        	}

        	erpOrderList = erpOrderService.queryErpOrderForExcel(erpOrderQueryVO);
    	}

    	if(erpOrderList != null) {
    		for (MallSubOrderExcelVO erpOrder : erpOrderList) {
    			List<Object> list = new ArrayList<>();
    			list.add(null);//清关批次
    			list.add(erpOrder.getOrderNo());
    			list.add(null);//运单号
    			String skuCode = erpOrder.getSkuCode();
    			//从item_sku表读取重量
    			if(IsEmptyUtil.isStringNotEmpty(skuCode)) {
    				ItemSkuDO itemSkuDoWeight = orderItemSkuService.queryItemSkuDOBySkuCodeAndCompanyNo(skuCode, companyNo);
    				if(null != itemSkuDoWeight) {
    					list.add(itemSkuDoWeight.getWeight());
    					list.add(itemSkuDoWeight.getWeight()+GROSS_GAIN);
    				} else {
    					list.add(0.0);
        				list.add(0.0+GROSS_GAIN);
    				}
    			} else {
    				list.add(0.0);
    				list.add(0.0+GROSS_GAIN);
    			}
    	        list.add(erpOrder.getQuantity());
    	        list.add(erpOrder.getSalePrice());
    	        //从item_sku表读取upc
    			if(IsEmptyUtil.isStringNotEmpty(skuCode)) {
    				ItemSkuDO itemSkuDoWeight = orderItemSkuService.queryItemSkuDOBySkuCodeAndCompanyNo(skuCode, companyNo);
    				if(null != itemSkuDoWeight) {
    					list.add(itemSkuDoWeight.getUpc());
    				} else {
    					list.add("");
    				}
    			} else {
    				list.add("");
    			}
    	        list.add(erpOrder.getItemName());
    	        //求出品牌
    	        if(IsEmptyUtil.isStringNotEmpty(skuCode)) {
    	        	ItemSkuDO itemSkuDO = orderItemSkuService.queryItemSkuDOBySkuCodeAndCompanyNo(skuCode, companyNo);
    	        	if(null != itemSkuDO) {
    	        		list.add(itemSkuDO.getBrandName());
    	        	}
    	        	else {
    	        		list.add("");//没有品牌的商品暂时以""代替
    	        	}
    	        } else {
    	        	list.add("");
    	        }
    	        list.add(erpOrder.getReceiver());
    	        list.add(erpOrder.getReceiverState());
    	        list.add(erpOrder.getReceiverCity());
    	        list.add(erpOrder.getReceiverDistrict());
    	        list.add(erpOrder.getReceiverAddress());
    	        list.add(erpOrder.getTelephone());
    	        list.add(erpOrder.getIdCard());
    	        list.add(SENDER);
    	        list.add(SENDER_ADDRESS);
    	        list.add(SENDER_PHONE);
    	        list.add(null);
    	        list.add(null);
    	        list.add(null);
    	        list.add(null);
    	        //读取规格信息
    	        if(IsEmptyUtil.isStringNotEmpty(skuCode)) {
    	        	List<ItemSkuScaleDO> scaleList = orderItemSkuScaleService.selectScaleNameValueBySkuCode(skuCode);
    	        	if(IsEmptyUtil.isCollectionNotEmpty(scaleList) && 2 == scaleList.size()) {
    	        		String color = "";
    	        		String scale = "";
    	        		for(ItemSkuScaleDO scaleDO:scaleList) {
    	        			if("颜色".equals(scaleDO.getScaleName())) {
    	        				color = scaleDO.getScaleValue();
    	        			}
    	        			if("尺寸".equals(scaleDO.getScaleName())) {
    	        				scale = scaleDO.getScaleValue();
    	        			}
    	        		}
    	        		list.add(color+"，"+scale);
    	        	} else {
    	        		list.add("");//没有规格信息用""代替
    	        	}
    	        } else {
    	        	list.add("");//没有规格信息用""代替
    	        }
    	        list.add(null);
    	        list.add(null);
    			rowDatas.add(list);
    		}
    	}
    	ExcelHelper excelHelper = new ExcelHelper();
    	String[] columnTitles = new String[]{"清关批次","订单号","运单号","净重", "毛重", "数量", "市场价", "商品条码",
    			"备注商品名称","商品品牌", "收件人姓名","省", "市", "区", "地址", "收件人电话", "收件人证件","发货人名称",
    			"发货人地址", "发货人电话", "商品货号","商品原产国","计量单位","商品备案号","规格型号","国检备案号","购买时间"};
    	Integer[] columnWidth = new Integer[]{10, 15, 10, 10, 10, 10, 10, 15, 20, 10, 12, 10, 10, 10,
    			10, 10, 12, 20,20 ,15, 10, 10, 10, 10, 25, 10, 10};
    	excelHelper.setErpOrderToSheetForAPEX("Erp Order", columnTitles, rowDatas, columnWidth);
    	//excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

    	ResponseEntity<byte[]> filebyte = null;
    	ByteArrayOutputStream  out = excelHelper.writeToByteArrayOutputStream();
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	String fileName = "子订单(" + DateUtil.formatDate(new Date(), "yyyyMMdd") + ").xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }
    @RequestMapping("/listChoice")
    private Object listChoice(Long id) {
        JsonResult<Map<String, String>> result = new JsonResult();
        MallSubOrderDO subOrder = erpOrderService.selectById(id);
        InventoryDO aDo = inventoryService.selectByItemCodeAndSkuCode(subOrder.getItemCode(), subOrder.getSkuCode());
        Map<String, String> map = new HashMap<>();
        map.put("orderNo", subOrder.getOrderNo());
        map.put("skuCode", subOrder.getSkuCode());
        result.setData(map);
        result.setSuccess(true);
        return result.buildIsSuccess(true);

    }

}
