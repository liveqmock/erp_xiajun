package com.wangqin.globalshop.inventory.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.constants.enums.GeneralStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOnWarehouseVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOutVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.service.ISequenceUtilService;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.shiro.ShiroUser;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.inventory.app.service.*;
import net.sf.json.JSONArray;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 库存处理器
 *
 * @author liuhui
 */
@Controller
@RequestMapping("/inventory")
//@Authenticated
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private IInventoryOnWarehouseService inventoryAreaService;
    @Autowired
    private InventoryBookingRecordService inventoryRecordService;
    @Autowired
    private IInventoryInoutService inventoryInoutService;
    @Autowired
    private IWarehouseService warehouseService;
    @Autowired
    private IInventoryOutManifestDetailService inventoryOutService;
    @Autowired
    private ISequenceUtilService sequenceUtilService;
    @Autowired
    private InventoryIMallSubOrderService erpOrderService;
    @Autowired
    private MallSubOrderMapperExt mallSubOrderMapper;
    @Autowired
    private ItemSkuScaleMapperExt itemSkuScaleMapperExt;
    @Autowired
    private ItemSkuMapperExt itemSkuMapperExt;

    @RequestMapping("/query")
    @ResponseBody
    public Object query(String skuCode) {
        if (skuCode != null) {
            InventoryDO inventory = inventoryService.selectBySkuCodeAndCompanyNo(skuCode, AppUtil.getLoginUserCompanyNo());
            return JsonResult.buildSuccess(true).buildData(inventory);
        } else {
            return JsonResult.buildFailed("没有SKU id");
        }
    }

    @RequestMapping("/area/queryList")
    @ResponseBody
    public Object queryInventoryAreas(InventoryQueryVO inventoryQueryVO) {
        JsonResult<List<InventoryOnWarehouseVO>> result = new JsonResult<>();
//        try {
//            if (StringUtil.isNotBlank(inventoryQueryVO.getBuySite())) {
//                String orderBy = Underline2Camel.camel2Underline(inventoryQueryVO.getBuySite());
//                inventoryQueryVO.setBuySite(orderBy);
//            }
            List<InventoryOnWarehouseVO> list = inventoryAreaService.queryInventoryAreas(inventoryQueryVO);
            /**查规格**/
            for(InventoryOnWarehouseVO inv:list) {
            	List<ItemSkuScaleDO> scaleList = itemSkuScaleMapperExt.selectScaleNameValueBySkuCode(inv.getSkuCode());
            	if(IsEmptyUtil.isCollectionNotEmpty(scaleList)) {
            		scaleList.forEach(skuScale -> {
            			if("颜色".equals(skuScale.getScaleName())) {
            				inv.setColor(skuScale.getScaleValue());
            			}
            			if("尺寸".equals(skuScale.getScaleName())) {
            				inv.setScale(skuScale.getScaleValue());
            			}
            		});
            	}
            	inv.setSkuPic(ImgUtil.initImg2Json(inv.getSkuPic()));
            }
            result.buildData(list);
            result.buildIsSuccess(true);

//        } catch (Exception e) {
//            result.buildIsSuccess(false);
//        }
        return result;
    }

//	@RequestMapping("/area/transTo")
//	@ResponseBody
//	public Object transToInventory(Long inventoryAreaId, int toTrans, String positionNo) throws InventoryException {
//		if (inventoryAreaId == null) {
//			return JsonResult.buildFailed("没有inventoryArea id");
//		}
//		if(toTrans<=0){
//			return JsonResult.buildFailed("在途到仓必须为正数");
//		}
//
//		inventoryService.transToInventory(inventoryAreaId, toTrans, positionNo);
//
//		//对子订单进行库存分配
//		InventoryOnWareHouseDO inventoryArea = inventoryAreaService.selectById(inventoryAreaId);
//		try {
//			erpOrderService.lockErpOrderBySkuId(inventoryArea.getSkuCode());
//		} catch (InventoryException e) {
//			e.printStackTrace();
//		}
//		return JsonResult.buildSuccess(null);
//	}

    // ------------test------------------------
    // http://localhost:8080/haierp1/inventory/add?itemId=73&skuId=352&warehouseId=16&positionNo=S001&inventory=10&transInv=5

    /***
     * 直接新增的接口
     * @param skuCode
     * @param warehouseNo
     * @param positionNo 货架号
     * @param quantity 入库数目
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add( String skuCode, String warehouseNo, String positionNo, Long quantity) {
        InventoryDO inventoryDO = new InventoryDO();
        inventoryDO.setSkuCode(skuCode);
        inventoryDO.setInv(quantity);
        inventoryService.outbound(inventoryDO, warehouseNo, positionNo);
        return JsonResult.buildSuccess(null);

    }

    @RequestMapping("/lock")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object lockOrder(Long orderId) throws InventoryException {
        MallSubOrderDO order = erpOrderService.selectById(orderId);
        inventoryService.order(order);
        return JsonResult.buildSuccess(true);
    }

    @RequestMapping("/release")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object releaseOrder(Long orderId) throws InventoryException {
        MallSubOrderDO order = erpOrderService.selectById(orderId);
        inventoryService.release(order);
        return JsonResult.buildSuccess(true);
    }

//	@RequestMapping("/send")
//	@ResponseBody
//	public Object sendOrder(Long orderId) throws InventoryException {
//		MallSubOrderDO order = erpOrderService.selectById(orderId);
//		inventoryService.sendInventroyOrder(order);
//		return JsonResult.buildSuccess(null);
//	}

    /**
     * 库存调整，库存盘进、盘出
     *
     * @return
     * @throws InventoryException
     */
    @RequestMapping("/inventoryCheckIn")
    @ResponseBody
    public Object inventoryCheckIn(String inventoryOnWarehouseNo, String skuCode, Long quantity, String shelfNo)
            throws InventoryException {
        try {
            inventoryService.inventoryCheckIn(inventoryOnWarehouseNo, skuCode, quantity, shelfNo);
        } catch (ErpCommonException e) {
            return JsonResult.buildFailed(e.getErrorMsg());
        } catch (Exception ex) {
            return JsonResult.buildFailed("未知异常");
        }



//			//对子订单进行库存分配
//			erpOrderService.queryBySkuCode(skuCode);
//		}
        return JsonResult.buildSuccess(null);
    }

    @RequestMapping("/inventoryCheckOut")
    @ResponseBody
    public Object inventoryCheckOut(String inventoryOnWarehouseNo, String skuCode, Long quantity) throws InventoryException {
        try {
            inventoryService.inventoryCheckOut(inventoryOnWarehouseNo, skuCode, quantity);
        } catch (ErpCommonException e) {
            return JsonResult.buildFailed(e.getErrorMsg());
        } catch (Exception ex) {
            return JsonResult.buildFailed("未知异常");
        }
//
//			//对子订单进行库存分配
//			InventoryOnWareHouseDO inventoryArea = inventoryAreaService.selectByNo(inventoryAreaId);
//			erpOrderService.lockErpOrderBySkuId(inventoryArea.getSkuCode());
//		}
        return JsonResult.buildSuccess(null);
    }

    @RequestMapping("/record/queryList")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object queryInventoryRecords(Long id) {
        JsonResult<Object> result = new JsonResult<>();
        MallSubOrderDO orderDO = mallSubOrderMapper.selectByPrimaryKey(id);
        List<InventoryOnWareHouseDO> inventoryOnWareHouseDO = inventoryRecordService.selectByCompanyNoAndSkuCode(orderDO.getCompanyNo(), orderDO.getSkuCode());
        return result.buildIsSuccess(true).buildData(inventoryOnWareHouseDO);
//        JsonPageResult<InventoryBookingRecordDO> result = new JsonPageResult<>();
//        try {
//            InventoryBookingRecordDO inventoryRecordList = inventoryRecordService.queryById(id);
//            result.setData(inventoryRecordList);
//            result.buildIsSuccess(true);
//        } catch (Exception e) {
//            result.buildIsSuccess(false);
//        }
//        return result;
    }
    @RequestMapping("stockWarehouse")
    @ResponseBody
    public Object stockWarehouse() {
        JsonResult<List<InventoryOnWareHouseDO>> result = new JsonResult<>();
        List<InventoryOnWareHouseDO> inventoryOnWareHouseDO = inventoryRecordService.selectByCompanyNo("3");
        return result.buildIsSuccess(true).buildData(inventoryOnWareHouseDO);
//        JsonPageResult<InventoryBookingRecordDO> result = new JsonPageResult<>();
//        try {
//            InventoryBookingRecordDO inventoryRecordList = inventoryRecordService.queryById(id);
//            result.setData(inventoryRecordList);
//            result.buildIsSuccess(true);
//        } catch (Exception e) {
//            result.buildIsSuccess(false);
//        }
//        return result;
    }

    /**
     * 出入库流水明细
     *
     * @return
     */
    @RequestMapping("/queryInventoryInout")
    @ResponseBody
    public Object queryInventoryInout(InventoryQueryVO inventoryQueryVO) {
    	JsonResult<List<InventoryQueryVO>> result = new JsonResult<>();
    	List<InventoryQueryVO> inventoryList = inventoryInoutService.queryInventoryInoutsVo(inventoryQueryVO);
    	for(InventoryQueryVO inv:inventoryList) {
    		ItemSkuDO itemSku = inventoryInoutService.selectItemBySkuCode(inv.getSkuCode());
    		InventoryOnWareHouseDO warehouseDO = inventoryAreaService.selectByInventoryOnWarehouseNo(inv.getInventoryOnWarehouseNo());
    		if(IsEmptyUtil.isCollectionNotEmpty(inventoryList)) {
    			inv.setItemName(itemSku.getItemName());
    			inv.setUpc(itemSku.getUpc());
    			inv.setSkuPic(itemSku.getSkuPic());
    			inv.setWarehouseName(warehouseDO.getWarehouseName());
    			inv.setCreator(itemSku.getCreator());
    		}
        }
    	result.buildData(inventoryList);
    	return result.buildIsSuccess(true);


    }

    /**
     * 导出excel
     */
    @RequestMapping("/inventoryAreaExport")
    public ResponseEntity<byte[]> inventoryAreaExport(InventoryQueryVO inventoryQueryVO) throws Exception {
        List<List<Object>> rowDatas = new ArrayList<>();
        List<InventoryOnWareHouseDO> inventoryAreaList = inventoryAreaService.queryInventoryAreaForExcel(inventoryQueryVO);
        if (inventoryAreaList != null) {
            Double totalSalePrice = 0.0D;
            for (InventoryOnWareHouseDO inventoryArea : inventoryAreaList) {
                List<Object> list = new ArrayList<>();

                list.add(inventoryArea.getSkuCode());        //SKU CODE
                list.add(inventoryArea.getItemName());        //Item Name
                //sku图片
                String skuPic = inventoryArea.getSkuPic();
                if (StringUtil.isNotBlank(skuPic)) {
                    PicModel pm = HaiJsonUtils.toBean(skuPic, PicModel.class);
                    String imgSrc = pm.getPicList().get(0).getUrl();
                    String imgType = imgSrc.substring(imgSrc.lastIndexOf(".") + 1).toUpperCase();
                    if (imgSrc.contains("aliyuncs.com")) {
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

                list.add(inventoryArea.getUpc());                    //UPC
                list.add(inventoryArea.getScale());                    //尺码
                list.add(inventoryArea.getWarehouseName());            //仓库名称
//    	        list.add(inventoryArea.getPositionNo());            //货架号
//    	        list.add(inventoryDO.getInv() + inventoryDO.getLockedTransInv() - inventoryDO.getLockedInv());		//可售库存
                list.add(inventoryArea.getInventory());                //现货库存
                list.add(inventoryArea.getLockedInv());                //现货占用
                list.add(inventoryArea.getTransInv());                //在途库存
                list.add(inventoryArea.getLockedTransInv());        //在途占用

                rowDatas.add(list);
            }
            List<Object> list = new ArrayList<>();
            list.add("库存总金额：");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add(totalSalePrice);
            rowDatas.add(list);
        }
        ExcelHelper excelHelper = new ExcelHelper();
        String[] columnTitles = new String[]{"SKU CODE", "商品名称", "商品图片", "UPC", "品牌", "颜色", "尺码", "仓库名称", "货架号", "可售库存", "现货库存", "现货占用", "在途库存", "在途占用", "销售价格"};
        Integer[] columnWidth = new Integer[]{25, 30, 10, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15};
        excelHelper.setInventoryAreaToSheet("inventoryArea", columnTitles, rowDatas, columnWidth);
        //excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

        ResponseEntity<byte[]> filebyte = null;
        ByteArrayOutputStream out = excelHelper.writeToByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = "库存统计报表(" + DateUtil.formatDate(new Date(), "yyyyMMdd") + ").xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }

    /**
     * 修改货架号
     *
     * @param inventoryOnWarehouseNo inventoryOnWarehouseNo
     * @param shelfNo                货架号
     * @return
     */
    @RequestMapping("/changePositionNo")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object changePositionNo(String inventoryOnWarehouseNo, String shelfNo) {
        try {
            inventoryService.updateSelfNo(inventoryOnWarehouseNo, shelfNo);
        } catch (ErpCommonException e) {
            return JsonResult.buildFailed(e.getErrorMsg());
        } catch (Exception ex) {
            return JsonResult.buildFailed("未知异常");
        }
        return JsonResult.buildSuccess(null);
    }

//    /**
//     * 修改货架号
//     *
//     * @param inventoryAreaId
//     * @param positionNo
//     * @return
//     */
//    @RequestMapping("/changePositionNo")
//    @ResponseBody
//    @Transactional(rollbackFor = ErpCommonException.class)
//    public Object changePositionNo(Long inventoryAreaId, String positionNo) {
//        if (StringUtil.isBlank(positionNo)) {
//            return JsonResult.buildFailed("货架号不能为空！");
//        }
//        InventoryOnWareHouseDO inventoryArea = inventoryAreaService.selectById(inventoryAreaId);
//        if (inventoryArea.getInventory() > 0 || inventoryArea.getLockedInv() > 0) {
//            return JsonResult.buildFailed("此库存已有现货库存，不能更改货架号！");
//        } else {
////    		inventoryArea.setPositionNo(positionNo);
//            inventoryAreaService.changePositionNo(inventoryArea);
//            return JsonResult.buildSuccess(null);
//        }
//    }

    /**
     * 添加出库单
     */
    @RequestMapping("/inventoryOutAdd")
    @ResponseBody
    public Object inventoryOutAdd(InventoryOutManifestDO inventoryOut) {
        JsonResult<String> result = new JsonResult<>();
        initInventoryOut(inventoryOut);
        inventoryOut.setStatus(GeneralStatus.INIT.getCode());

        inventoryOutService.addInventoryOut(inventoryOut);
        return result.buildIsSuccess(true);
    }

    /**
     * 查询单个出库单
     */
    @RequestMapping("/inventoryOutQuery")
    @ResponseBody
    public Object inventoryOutQuery(Long id) {
        JsonResult<InventoryOutManifestDO> result = new JsonResult<>();
        if (id != null) {
            InventoryOutManifestDO inventoryOut = inventoryOutService.queryInventoryOut(id);
            if (inventoryOut == null) {
                result.buildIsSuccess(false).buildMsg("没有找到InventoryOut");
            }
            result.setData(inventoryOut);
            return result.buildIsSuccess(true);
        } else {
            return result.buildIsSuccess(false).buildMsg("没有Item id");
        }
    }

    /**
     * 修改出库单
     */
    @RequestMapping("/inventoryOutUpdate")
    @ResponseBody
    public Object inventoryOutUpdate(InventoryOutManifestDO inventoryOut) {
        JsonResult<String> result = new JsonResult<>();
        if (inventoryOut.getId() == null) {
            return result.buildIsSuccess(false).buildMsg("没有ID");
        } else {
            InventoryOutManifestDO io = inventoryOutService.selectById(inventoryOut.getId());
            if (io == null || io.getStatus() != GeneralStatus.INIT.getCode()) {
                return result.buildIsSuccess(false).buildMsg("状态不对");
            }
        }
        initInventoryOut(inventoryOut);
        inventoryOut.setStatus(GeneralStatus.INIT.getCode());
        inventoryOutService.updateInventoryOut(inventoryOut);
        return result.buildIsSuccess(true);
    }

    public void initInventoryOut(InventoryOutManifestDO inventoryOut) {
        ShiroUser su = null;
        try {
//			 su = this.getShiroUser();
        } catch (Exception e) {
            throw new ErpCommonException("没有登入");
        }
//		final Long loginId = su.getId();
//		final String loginName = su.getName();
        Date nowDate = new Date();

        if (inventoryOut.getId() == null) {    //新增
            //出库单号
            String invOutNo = "IOUT" + DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDD) + "U" + String.format("%0" + 4 + "d", "inventoryControllerJJJJ") + sequenceUtilService.gainINVOUTSequence();
            inventoryOut.setInventoryOutNo(invOutNo);
//			inventoryOut.setUserCreate(loginName);
        } else {
//			inventoryOut.setUserModify(loginName);
        }

//    	if(!StringUtils.isEmpty(inventoryOut.getInventoryOutDetailListStr())) {
//			String s = inventoryOut.getInventoryOutDetailListStr().replace("&quot;", "\"");
//			List<InventoryOutManifestDetailDO> details = HaiJsonUtils.toBean(s, new TypeReference<List<InventoryOutManifestDetailDO>>(){});
//			details.forEach(
//					detail -> {
//							if(detail.getNo()==null || detail.getQuantity()==null || detail.getQuantity()<=0) {
//								throw new ErpCommonException("参数异常");
//							}
//							InventoryOnWareHouseDO inventoryArea= inventoryAreaService.selectById(detail.getInventoryAreaId());
////							detail.setItemId(inventoryArea.getItemId());
//							detail.setSkuCode(inventoryArea.getSkuCode());
//							detail.setItemName(inventoryArea.getItemName());
//							detail.setScale(inventoryArea.getScale());
//							detail.setUpc(inventoryArea.getUpc());
//							detail.setSkuCode(inventoryArea.getSkuCode());
//							detail.setSkuPic(inventoryArea.getSkuPic());
//							detail.setGmtCreate(nowDate);
//							detail.setGmtModify(nowDate);
//					}
//			);
////			inventoryOut.setInventoryOutDetailList(details);
//		}
    }

    /**
     * 查询出库单列表
     */
    @RequestMapping("/inventoryOutQueryList")
    @ResponseBody
    public Object inventoryOutQueryList(InventoryOutVO inventoryOutVO) {
        JsonPageResult<List<InventoryOutManifestDO>> result = new JsonPageResult<>();
        try {
            result = inventoryOutService.inventoryOutQueryList(inventoryOutVO);
            result.buildIsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.buildIsSuccess(false);
        }
        return result;
    }


    /**
     * 确认出库
     *
     * @param inventoryOutDetailList inventoryOutDetailList
     * @param warehouseNo            仓库编号
     * @param warehouseName          仓库名
     * @param desc                   描述/备注
     * @return
     * @throws InventoryException
     */
    @RequestMapping("/inventoryOutConfirm")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object inventoryOutConfirm(String inventoryOutDetailList,String warehouseNo,
                                      String warehouseName, String desc) throws InventoryException {

        JSONArray inventoryOutDetailArray = JSONArray.fromObject(inventoryOutDetailList);

        try {
            inventoryService.inventoryOutConfirm(inventoryOutDetailArray, warehouseNo, warehouseName, desc);
        } catch (ErpCommonException e) {
            return JsonResult.buildFailed(e.getErrorMsg());
        } catch (Exception ex) {
            return JsonResult.buildFailed("未知异常");
        }

        return JsonResult.buildSuccess(null);
    }

//    /**
//     * 确认出库,区分新增出库和修改出库，逻辑不同
//     *
//     * @param
//     * @return
//     */
//    @RequestMapping("/inventoryOutConfirm")
//    @ResponseBody
//    @Transactional(rollbackFor = ErpCommonException.class)
//    public Object inventoryOutConfirm(InventoryOutManifestDO inventoryOut) throws InventoryException {
//        JsonResult<String> result = new JsonResult<>();
//        Set<String> skuIdSet = null;
//        if (inventoryOut.getId() == null) {    //新增出库
//            initInventoryOut(inventoryOut);
//            inventoryOut.setStatus(GeneralStatus.CONFIRM.getCode());
//            skuIdSet = inventoryOutService.addInventoryOutConfirm(inventoryOut);
//        } else {
//            if (inventoryOut.getId() == null) {
//                return result.buildIsSuccess(false).buildMsg("没有ID");
//            } else {
//                InventoryOutManifestDO io = inventoryOutService.selectById(inventoryOut.getId());
//                if (io == null || io.getStatus() != GeneralStatus.INIT.getCode()) {
//                    return result.buildIsSuccess(false).buildMsg("状态不对");
//                }
//            }
//            initInventoryOut(inventoryOut);
//            inventoryOut.setStatus(GeneralStatus.CONFIRM.getCode());
//            skuIdSet = inventoryOutService.updateInventoryOutConfirm(inventoryOut);
//        }
//        //对子订单进行库存分配
//        if (skuIdSet != null && !skuIdSet.isEmpty()) {
//            for (String skuId : skuIdSet) {
//                erpOrderService.lockErpOrderBySkuId(skuId);
//            }
//        }
//        return result.buildIsSuccess(true);
//    }

    /**
     * 删除出库单
     *
     * @param
     * @return
     */
    @RequestMapping("/inventoryOutDelete")
    @ResponseBody
    public Object inventoryOutDelete(Long id) {
        JsonResult<String> result = new JsonResult<>();

        try {
            inventoryOutService.deleteInventoryOutById(id);
            return result.buildIsSuccess(true);
        } catch (ErpCommonException e) {
            return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        } catch (Exception ex) {
            return result.buildIsSuccess(false).buildMsg("未知异常");
        }
    }
//	/**
//	 * 盘出到备货仓
//	 * @param inventoryAreaId
//	 * @param quantity
//	 * @return
//	 * @throws InventoryException
//	 */
//	@RequestMapping("/inventoryCheckOuttoStock")
//	@ResponseBody
//	public Object inventoryCheckOuttoStock(Long inventoryAreaId, Integer quantity) throws InventoryException {
//		// 非空校验
//		if (inventoryAreaId== null ||quantity == null) {
//			return JsonResult.buildFailed("有空数据");
//		}else{
//			if (quantity <= 0) {
//				return JsonResult.buildFailed("盘出的库存要为正数");
//			}
//			try {
//				inventoryService.inventoryCheckOut(inventoryAreaId, quantity);
//			} catch (Exception ex) {
//				return JsonResult.buildFailed("未知异常");
//			}
//
//			//对子订单进行库存分配
//			InventoryOnWareHouseDO inventoryArea = inventoryAreaService.selectById(inventoryAreaId);
//			erpOrderService.lockErpOrderBySkuId(inventoryArea.getSkuCode());
//		}
//
////		InventoryOnWareHouseDO inventoryArea = inventoryAreaMapper.selectById(inventoryAreaId);
//
////		InventoryStock tjInsetStock = new InventoryStock();
////		tjInsetStock.setSkuId(inventoryArea.getSkuId());
////		InventoryStock selInsetStock = inventoryStockService.selectOne(tjInsetStock);
////		if(selInsetStock != null) {
////			selInsetStock.setInventory(selInsetStock.getInventory() + quantity);
////			selInsetStock.setGmtModify(new Date());
////			inventoryStockService.updateSelectiveById(selInsetStock);
////		} else {
////			InventoryStock insetStock = new InventoryStock();
////			BeanUtils.copyProperties(inventoryArea, insetStock);
////			insetStock.setInventory(quantity);
////			insetStock.setLockedInv(0);
////			insetStock.setTransInv(0);
////			insetStock.setLockedTransInv(0);
////			insetStock.setGmtCreate(new Date());
////			insetStock.setGmtModify(new Date());
////			inventoryStockService.insertSelective(insetStock);
////		}
//		return JsonResult.buildSuccess(null);
//	}
//	/**
//	 * 库存调整，库存盘进、盘出
//	 *
//	 * @param orderId
//	 * @return
//	 * @throws InventoryException
//	 */
//	@RequestMapping("/inventoryStockCheckIn")
//	@ResponseBody
//	public Object inventoryStockCheckIn(Long skuId, Long warehouseId, String positionNo, Integer quantity,Long id)
//			throws InventoryException {
////		InventoryStock insetStock = inventoryStockService.selectById(id);
//		if(quantity > insetStock.getInventory()) {
//			return JsonResult.buildFailed("存入库存数量超过国内仓现有数量");
//		}
//
//		// 非空校验
//		if (skuId == null || warehouseId == null || StringUtils.isBlank(positionNo) || quantity == null) {
//			return JsonResult.buildFailed("有空数据");
//		} else {
//			if (quantity <= 0) {
//				return	JsonResult.buildFailed("增加库存要为正数");
//			}
//			try {
//				inventoryAreaService.inventoryCheckIn(skuId, warehouseId, positionNo, quantity);
//			} catch (ErpCommonException e) {
//				return JsonResult.buildFailed(e.getErrorMsg());
//			} catch (Exception ex) {
//				return JsonResult.buildFailed("未知异常");
//			}
//			try {
//				//对子订单进行库存分配
//				erpOrderService.lockErpOrderBySkuId(skuId);
//			} catch (InventoryException e) {
//				return JsonResult.buildFailed("库存盘进，子订单重新库存分配异常skuid:"+skuId);
//			}
//
//		}
//
////		insetStock.setInventory(insetStock.getInventory()- quantity);
////		inventoryStockService.updateSelectiveById(insetStock);
//		return JsonResult.buildSuccess(null);
//		return null;
//	}
}
