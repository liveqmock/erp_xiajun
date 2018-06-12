package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.purchase.app.service.IBuyerService;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 * 买手相关Controller
 */
@Controller
@Authenticated
@ResponseBody
@RequestMapping(value = {
//        "/wx/purchaseUser",
        "/purchase"})
public class BuyerController {
    @Autowired
    private IBuyerService service;
    @Autowired
    private IBuyerTaskService buyerTaskService;

    @PostMapping(value = {"/queryBuyers"})
    public Object queryWxPurchaseUser() {
        JsonResult<List<BuyerDO>> result = new JsonResult<>();
        List<BuyerDO> list = service.list();
        result.buildData(list);
        return result.buildIsSuccess(true);

    }


    @PostMapping("/queryTaskDailyList")
    public Object queryTaskDailyList(BuyerTaskDO buyerTaskDO) {
        JsonResult<List<BuyerTaskDO>> result = new JsonResult<>();
        List<BuyerTaskDO> list = buyerTaskService.list(buyerTaskDO);
        result.buildData(list);
        return result.buildIsSuccess(true);
    }

}
