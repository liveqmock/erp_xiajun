package com.wangqin.globalshop.order.app.controller.mall;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallReturnOrderVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.order.app.service.mall.IMallReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liuyang
 */
@Controller
@RequestMapping("/erpReturnOrder")
@Authenticated
public class MallReturnController {
    @Autowired
    private IMallReturnOrderService mallReturnOrderService;


    @PostMapping("/index")
    @ResponseBody
    public Object index(String orderNo, String startGmtCreate, String endGmtCreate) {
        JsonResult<List<MallReturnOrderDO>> result = new JsonResult<>();

        try {
            List<MallReturnOrderDO> list = mallReturnOrderService.selectByCondition(orderNo, startGmtCreate, endGmtCreate);
            result.setData(list);
            result.setSuccess(true);
        } catch (ErpCommonException e) {
            result.setMsg(e.getErrorMsg());
            result.setSuccess(false);
        } catch (Exception ex) {
            result.setMsg(ex.getMessage());
            result.setSuccess(false);
        }

        return result;
    }


    @PostMapping("/add")
    @ResponseBody
    public Object add(MallReturnOrderVO erpReturnOrder) {
        JsonResult<MallReturnOrderVO> result = new JsonResult<>();
        try {
            mallReturnOrderService.add(erpReturnOrder);
        } catch (ErpCommonException exception) {
            return result.buildIsSuccess(false).buildMsg(exception.getErrorMsg());
        }
        result.setSuccess(true);
        return result;
    }

    @PostMapping("/update")
    @ResponseBody
    public Object update(MallReturnOrderVO erpReturnOrder) {
        JsonResult<MallReturnOrderVO> result = new JsonResult<>();
        try {
            mallReturnOrderService.update(erpReturnOrder);
        } catch (ErpCommonException exception) {
            return result.buildIsSuccess(false).buildMsg(exception.getErrorMsg());
        }
        result.setSuccess(true);
        return result;
    }

    @PostMapping("queryById")
    @ResponseBody
    public Object queryById(String mallReturnOrderNo){
        JsonResult<MallReturnOrderDO> result = new JsonResult<>();
        try {
            MallReturnOrderDO mallReturnOrderDO = mallReturnOrderService.getByMallReturnOrderNo(mallReturnOrderNo);
            result.setData(mallReturnOrderDO);
            result.setSuccess(true);
        } catch (ErpCommonException exception) {
            return result.buildIsSuccess(false).buildMsg(exception.getErrorMsg());
        }
        return result;
    }
}
