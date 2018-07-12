package com.wangqin.globalshop.order.app.controller.mall;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallOrderVO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.service.util_service.OrderISequenceUtilService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.wangqin.globalshop.order.app.comm.Constant.*;

/**
 * @author zhulu
 */
@Controller
@RequestMapping("/outerOrder")
@Authenticated
public class MallOrderController {

    @Autowired
    private IMallOrderService mallOrderService;

    @Autowired
    private OrderISequenceUtilService sequenceUtilService;

    @Autowired
    private IMallSubOrderService mallSubOrderService;
    @Autowired
    private InventoryService inventoryService;

    /**
     * 主页
     */
    @RequestMapping("/index")
    @ResponseBody
    public Object index(MallOrderVO vo) {
        JsonResult<List<MallOrderVO>> result = new JsonResult<>();
        List<MallOrderVO> outerOrder = mallOrderService.list(vo);
        return result.buildData(outerOrder).buildIsSuccess(true);
    }

    /**
     * 增加订单
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(MallOrderVO mallOrderVO) {
        JsonResult<List<MallOrderVO>> result = new JsonResult<>();
        if (mallOrderVO.getId() == null) {
            String setOrderNo = CodeGenUtil.getOrderNo();
            mallOrderVO.setOrderNo(setOrderNo);
            String outerOrderDetailList = mallOrderVO.getOuterOrderDetailList();
            String s = outerOrderDetailList.replace("&quot;", "\"");
            List<MallSubOrderDO> outerOrderDetails = HaiJsonUtils.toBean(s, new TypeReference<List<MallSubOrderDO>>() {
            });
            mallOrderVO.setOuterOrderDetails(outerOrderDetails);
            if (CollectionUtils.isEmpty(outerOrderDetails)) {
                return JsonResult.buildFailed("没有商品信息");
            }
            //创建外部订单
            mallOrderService.addOuterOrder(mallOrderVO);
        } else {
            result.buildMsg("错误数据").buildIsSuccess(false);
        }
        return result.buildIsSuccess(true).buildMsg("添加成功");
    }


    /**
     * 更新订单
     */
    /**
     * @param mallOrderVO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(MallOrderVO mallOrderVO) {

        JsonResult<String> result = new JsonResult<>();
        if (mallOrderVO.getId() != null) {
            MallOrderVO selouterOrderVO = mallOrderService.selectByOrderNoVO(mallOrderVO.getOrderNo());
            if (selouterOrderVO == null || !Objects.equals(selouterOrderVO.getStatus(), ORDER_SATUTS_INIT)) {
                return JsonResult.buildFailed("订单不存在或者状态错误");
            }
            //查询是否有发货的订单，有的话订单不能修改
            int sendOrder = mallSubOrderService.selectCountWithStateAndOrderNo(mallOrderVO.getOrderNo(),ORDER_SATUTS_SENT);
            if (sendOrder > 0) {
                return JsonResult.buildFailed("有子订单发货不能修改");
            }

            selouterOrderVO.setGmtModify(new Date());
            List<MallSubOrderDO> erpOrders = mallSubOrderService.selectByOrderNo(mallOrderVO.getOrderNo());
            for (MallSubOrderDO mallSubOrderDO : erpOrders) {
            	//1,释放子订单库存       
                inventoryService.release(mallSubOrderDO);            
			}
            mallOrderService.deleteByHard(selouterOrderVO);
            //订单详情
            List<MallSubOrderDO> outerOrderDetails = null;
            String outerOrderDetailList = mallOrderVO.getOuterOrderDetailList();
            if (StringUtils.isNotBlank(outerOrderDetailList)) {
                String s = outerOrderDetailList.replace("&quot;", "\"");
                outerOrderDetails = HaiJsonUtils.toBean(s, new TypeReference<List<MallSubOrderDO>>() {
                });
                selouterOrderVO.setOuterOrderDetails(outerOrderDetails);
                if (CollectionUtils.isEmpty(outerOrderDetails)) {
                    return JsonResult.buildFailed("没有商品信息");
                }
                    //创建外部订单
                    selouterOrderVO.setOrderTime(mallOrderVO.getOrderTime());
	                selouterOrderVO.setOrderTime(mallOrderVO.getOrderTime());
	                selouterOrderVO.setReceiver(mallOrderVO.getReceiver());
	                selouterOrderVO.setTelephone(mallOrderVO.getTelephone());
	                selouterOrderVO.setPayType(mallOrderVO.getPayType());
	                selouterOrderVO.setAddressDetail(mallOrderVO.getAddressDetail());
	                selouterOrderVO.setReceiverState(mallOrderVO.getReceiverState());
	                selouterOrderVO.setReceiverCity(mallOrderVO.getReceiverCity());
	                selouterOrderVO.setReceiverDistrict(mallOrderVO.getReceiverDistrict());
	                selouterOrderVO.setIdCard(mallOrderVO.getIdCard());
	                selouterOrderVO.setMemo(mallOrderVO.getMemo());
	                mallOrderService.addOuterOrder(selouterOrderVO);

            }
            result.buildIsSuccess(true);
            } else {
            	result.buildData("错误数据").buildIsSuccess(false);
        	}
        	return result;
        }
    /**
     * 查询单个订单
     */
    @RequestMapping("/query")
    @ResponseBody
    public Object query(String orderNo) {
        JsonResult<MallOrderDO> result = new JsonResult<>();
        if (orderNo == null) {
            return result.buildMsg("参数丢失").buildIsSuccess(true);
        } else {
            MallOrderDO outerOrder = mallOrderService.selectByOrderNo(orderNo);
            return result.buildData(outerOrder).buildIsSuccess(true);
        }

    }


    /**
     * 批量调价查询订单
     *
     * @return
     */
    @RequestMapping(value = "/queryOuterOrderList", method = RequestMethod.POST)
    @ResponseBody
    public Object queryOuterOrderList(MallOrderVO mallOrderVO) {
//        if (mallOrderVO.getStatus() != null && mallOrderVO.getStatus() == 10) {//10代表查询全部订单
//            mallOrderVO.setStatus(null);
//        }
        if (mallOrderVO.getEndGmtCreate() != null) {
            String endGmtCreateStr = DateUtil.ymdFormat(mallOrderVO.getEndGmtCreate());
            Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
            mallOrderVO.setEndGmtCreate(endGmtCreate);
        }
        //如果是代理
//        ShiroUser shiroUser = this.getShiroUser();
//        Set<String> roles = shiroUser.getRoles();
//        if (roles.contains("irhdaili")) {
//            String[] logingNameArr = shiroUser.getLoginName().split("_");
//            if (logingNameArr.length < 2 || StringUtils.isBlank(logingNameArr[1])) {
//                throw new ErpCommonException("用户权限异常");
//            }
//            outerOrderQueryVO.setSalesId(Integer.parseInt(logingNameArr[1]));
//            Seller seller = sellerService.selectById(outerOrderQueryVO.getSalesId());
//            if (seller.getOpenId() != null) {
//                outerOrderQueryVO.setOpenId(seller.getOpenId());
//            }
//        }
        JsonResult<List<MallOrderDO>> result = new JsonResult<>();
        List<MallOrderDO> list = mallOrderService.queryOuterOrderList(mallOrderVO);
        result.setData(list);
//        if (roles.contains("irhdaili")) {
//            result.setAgentRoler(true);
//        }
        return result.buildIsSuccess(true);
    }

    /**
     * 删除单个订单
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String orderNo) {
        JsonResult<String> result = new JsonResult<>();
        try {
            MallOrderVO outerOrder = mallOrderService.selectByOrderNoVO(orderNo);
            outerOrder.setOrderNo(orderNo);
            if (outerOrder == null || !Objects.equals(outerOrder.getStatus(), ORDER_SATUTS_CLOSE)) {
                return JsonResult.buildFailed("先关闭才能删除订单");
            }
            mallOrderService.deleteByIsDel(outerOrder);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 批量关闭订单
     */
    @RequestMapping("/close")
    @ResponseBody
    public Object close(String orderIds) {
        JsonResult result = new JsonResult();
        List<String> errorMsg = Lists.newArrayList();
        int i = 0;
        if (StringUtils.isNotBlank(orderIds)) {
            String s = orderIds.replace("&quot;", "\"");
            List<Long> orderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
            });
            for (Long id : orderIdList) {
                i++;
                MallOrderDO outerOrder = mallOrderService.selectById(id);
                if (outerOrder == null || !Objects.equals(outerOrder.getStatus(), ORDER_SATUTS_INIT)) {
                    errorMsg.add("第" + i + "条不存在或者状态错误,");
                }
                //查询是否有发货的订单，有的话订单不能修改
                int sendOrder = mallSubOrderService.selectCountWithStateAndOrderNo(outerOrder.getOrderNo(),ORDER_SATUTS_SENT);;
                if (sendOrder > 0) {
                    return result.buildMsg("第" + i + "条有子订单发货不能关闭").buildIsSuccess(false);
                }
                //1、释放子订单库存
//                EntityWrapper<ErpOrder> entityWrapper = new EntityWrapper<>();
//                entityWrapper.where("outer_order_id = {0} ", outerOrder.getId());
//                List<MallSubOrderDO> erpOrders = mallSubOrderService.selectByOrderNo(outerOrder.getOrderNo());
//                if (CollectionUtils.isNotEmpty(erpOrders)) {
//                    for (MallSubOrderDO erpOrder : erpOrders) {
//                        //1,释放子订单库存
//                        inventoryService.release(erpOrder);
//                        //2,更改子订单状态
//                        erpOrder.setStatus(ORDER_SATUTS_CLOSE.getCode());
//                        erpOrder.setGmtModify(new Date());
//                        mallSubOrderService.update(erpOrder);
//                    }
//                }
                //2、更改主子订单状态
                outerOrder.setStatus(ORDER_SATUTS_CLOSE);
                outerOrder.setGmtModify(new Date());
                mallOrderService.updateById(outerOrder);
            }
            StringBuilder rmsg = new StringBuilder();
            if (CollectionUtils.isNotEmpty(errorMsg)) {
                for (String a : errorMsg) {
                    rmsg.append(a);
                }
                return result.buildIsSuccess(false).buildMsg(rmsg.toString());
            } else {
                return result.buildIsSuccess(true);
            }

        } else {
            return result.buildMsg("没有订单ID").buildIsSuccess(false);
        }
    }

    /**
     * 查询子订单备货信息
     */
    @RequestMapping("/erpStockup")
    @ResponseBody
    public Object erpStockup(String orderNo) {
        JsonResult<List<MallSubOrderDO>> result = new JsonResult<>();
        try {
            if (orderNo != null) {
                //查询未关闭子订单备货情况
                List<MallSubOrderDO> erpOrders = mallSubOrderService.selectUnClosedByOrderNo(orderNo);
                result.setData(erpOrders);
            } else {
                return JsonResult.buildFailed("没有传ID");
            }
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }

    //手动确认主订单
    @RequestMapping("/reviewOuterOrder")
    @ResponseBody
    public void reviewOuterOrder(String orderNo) {
        mallOrderService.review(orderNo);
    }

    //主订单导出
    @RequestMapping(value = "/OuterOrderExportExcel")
    @ResponseBody
    public ResponseEntity<byte[]> OuterOrderExportExcel(MallOrderVO mallOrderVO) throws Exception {//
        if (mallOrderVO.getStartGmtCreate() == null || mallOrderVO.getEndGmtCreate() == null) {
            throw new ErpCommonException("必须选择创建时间段");
        }
        String startGmtCreateStr = DateUtil.ymdFormat(mallOrderVO.getStartGmtCreate());
        Date startGmtCreate = DateUtil.parseDate(startGmtCreateStr + " 00:00:00");
        mallOrderVO.setStartGmtCreate(startGmtCreate);
        String endGmtCreateStr = DateUtil.ymdFormat(mallOrderVO.getEndGmtCreate());
        Date endGmtCreate = DateUtil.parseDate(endGmtCreateStr + " 23:59:59");
        mallOrderVO.setEndGmtCreate(endGmtCreate);
        mallOrderVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());

        List<List<Object>> rowDatas = new ArrayList<>();
        List<MallOrderDO> outerOrderlist = mallOrderService.queryOuterOrderForExcel(mallOrderVO);
        if (outerOrderlist != null) {
            for (MallOrderDO outerOrder : outerOrderlist) {
                List<Object> list = new ArrayList<>();

                list.add(outerOrder.getOrderNo());            //主订单号
//                list.add(outerOrder.getS());        //销售员
                list.add(outerOrder.getTotalAmount());    //订单金额
                list.add(outerOrder.getGmtCreate());        //下单时间
                list.add(outerOrder.getStatus());  //订单状态
                list.add(outerOrder.getIdCard());            //收件人
//                list.add(outerOrder.getTelephone());        //手机
//                list.add(outerOrder.getSt());    //省
//                list.add(outerOrder.getReceiverCity());        //市
//                list.add(outerOrder.getReceiverDistrict());    //区
//                list.add(outerOrder.getAddressDetail());    //详细地址

                rowDatas.add(list);
            }
        }
        ExcelHelper excelHelper = new ExcelHelper();
        String[] columnTitles = new String[]{"主订单号", "销售员", "订单金额", "下单时间", "订单状态", "收件人", "手机", "省", "市", "区", "详细地址"};
        Integer[] columnWidth = new Integer[]{30, 15, 15, 20, 15, 15, 15, 15, 15, 15, 45};

        excelHelper.setOuterOrderToSheet("Father Order", columnTitles, rowDatas, columnWidth);
        //excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

        ResponseEntity<byte[]> filebyte = null;
        ByteArrayOutputStream out = excelHelper.writeToByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = "主订单(" + DateUtil.formatDate(startGmtCreate, "yyyyMMdd") + ").xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }

    //微信录单确认
    @RequestMapping("/outerOrderReview")
    @ResponseBody
    public Object outerOrderReview() {
        List<MallOrderDO> outerOrderList = mallOrderService.selectByStatus((byte) -2);
        if (outerOrderList.size() > 0) {
            outerOrderList.forEach((outerOrder) -> {
                mallOrderService.review(outerOrder.getOrderNo());

                outerOrder.setStatus(0);
                mallOrderService.updateById(outerOrder);
            });
        }
        return JsonResult.buildSuccess(null);
    }
}
