package com.wangqin.globalshop.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dto.MultiDeliveryFormDTO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;

/**
 * @author biscuits
 * @date 2018/5/24
 */
@Controller
@RequestMapping("/shippingOrder")
public class ShippingOrderController {

    @Autowired
    private IShippingOrderService shippingOrderService;

    @RequestMapping("/query")
    @ResponseBody
    /**
     * @param shippingOrderDo 前台界面传输过来的
     */
    public Object query(ShippingOrderVO shippingOrderVO) {
        JsonResult<List<ShippingOrderDO>> result;
        if (shippingOrderVO.getStartOrderTime() != null) {
            String startOrderTimeStr = DateUtil.ymdFormat(shippingOrderVO.getStartOrderTime());
            Date startOrderTime = DateUtil.parseDate(startOrderTimeStr + " 00:00:00");
            shippingOrderVO.setStartOrderTime(startOrderTime);
        }
        if (shippingOrderVO.getEndOrderTime() != null) {
            String endOrderTimeStr = DateUtil.ymdFormat(shippingOrderVO.getEndOrderTime());
            Date endOrderTime = DateUtil.parseDate(endOrderTimeStr + " 23:59:59");
            shippingOrderVO.setEndOrderTime(endOrderTime);
        }
        // 如果是代理
        // ShiroUser shiroUser = this.getShiroUser();
        // Set<String> roles = shiroUser.getRoles();
        // if(roles.contains("irhdaili")) {
        // String[] logingNameArr = shiroUser.getLoginName().split("_");
        // if(logingNameArr.length<2 || StringUtils.isBlank(logingNameArr[1])) {
        // throw new ErpCommonException("用户权限异常");
        // }
        // shippingOrderQueryVO.setSalesId(Integer.parseInt(logingNameArr[1]));
        // Seller seller = sellerService.selectById(shippingOrderQueryVO.getSalesId());
        // if(seller.getOpenId()!=null) {
        // shippingOrderQueryVO.setOpenId(seller.getOpenId());
        // }
        // }
        result = shippingOrderService.queryShippingOrders(shippingOrderVO);
        // if(roles.contains("irhdaili")) {
        // result.setAgentRoler(true);
        // }
        result.setSuccess(true);
        return result;
    }

    // 合单发货表单
    @RequestMapping("/multiDeliveryForm")
    @ResponseBody
    public Object multiDeliveryForm(String erpOrderId) throws InventoryException {
        JsonResult<MultiDeliveryFormDTO> result = new JsonResult<>();

        try {
            result.setData(shippingOrderService.queryByOrderId(erpOrderId));
            result.buildIsSuccess(true);
        } catch (ErpCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        }
        return result;
    }

    // 合单发货(将多个子订单合并成一个包裹)
    @RequestMapping("/multiDelivery")
    @ResponseBody
    public Object multiDelivery(ShippingOrderDO shippingOrder) throws InventoryException {
        JsonResult<String> result = new JsonResult<>();
        if (StringUtils.isNotBlank(shippingOrder.getMallOrders())) {
            // ShiroUser shiroUser = this.getShiroUser();
            // shippingOrder.setUserCreate(shiroUser.getLoginName());

            if (shippingOrder.getLogisticCompany() != null && shippingOrder.getLogisticCompany().equals("海狐")) {
                List<MallSubOrderDO> ErpOrderList = shippingOrderService.queryShippingOrderDetail(shippingOrder.getMallOrders());
                if (ErpOrderList.size() > 1) {
                    throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
                }
                if (ErpOrderList.get(0).getQuantity() > 1) {
                    throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
                }
                if (StringUtils.isEmpty(ErpOrderList.get(0).getIdCard())) {
                    throw new ErpCommonException("海狐物流发货单号缺少身份证信息");
                }
            }
            try {
                Set<Long> mainIds = shippingOrderService.multiDelivery(shippingOrder);
                // 更新主订单发货状态
                if (CollectionUtils.isNotEmpty(mainIds)) {
                    shippingOrderService.updateOuterstatus(mainIds);
                }
                // //对接邮客
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("邮客")) {
                // youkeService.createOrder(shippingOrder.getId());
                // }
                // //对接运通快递
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("运通快递")) {
                // yunTongService.createOrder(shippingOrder.getId());
                // }
                // //对接海狐
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("海狐")) {
                // haihuService.createOrder(shippingOrder.getId());
                // }
                // //对接海狐联邦转运
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("海狐联邦转运")) {
                // haihuService.returnPackageNo(shippingOrder);
                //
                // }
                // //对接美国转运四方
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("4PX")) {
                // siFangService.createOrder(shippingOrder.getId());
                // }
                // //对接联邦
                // if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("联邦转运")) {
                // fadRoadService.createOrder(shippingOrder.getId());
                // }
                // //直接选择顺丰或者韵达快递补全status = 4特殊国内物流轨迹
                // if(shippingOrder.getLogisticCompany()!=null && (shippingOrder.getLogisticCompany().equals("顺丰")||
                // shippingOrder.getLogisticCompany().equals("韵达"))) {
                // ShippingTrack shippingTrack = new ShippingTrack();
                // shippingTrack.setGmtCreate(new Date());
                // shippingTrack.setGmtModify(new Date());
                // shippingTrack.setStatus(4);
                // shippingTrack.setLogisticNo(shippingOrder.getLogisticNo());
                // shippingTrack.setInlandExpressId(shippingOrder.getLogisticCompany());
                // shippingTrack.setInlandExpressNo(shippingOrder.getLogisticNo());
                // shippingTrack.setShippingNo(shippingOrder.getShippingNo());
                // shippingTrackMapper.insert(shippingTrack);
                // }
                result.buildIsSuccess(true);
            } catch (ErpCommonException e) {
                result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
            }
        } else {
            result.buildMsg("错误数据").buildIsSuccess(false);
        }
        return result;
    }

}
