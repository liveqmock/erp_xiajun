package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerTaskVO;
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
@RequestMapping(value = {"/purchase"})
public class BuyerController {
    @Autowired
    private IBuyerService service;
    @Autowired
    private IBuyerTaskService buyerTaskService;

    /**
     * 查询买手
     * @return
     */
    @PostMapping(value = {"/queryBuyers"})
    public Object queryWxPurchaseUser() {
        JsonResult<List<BuyerDO>> result = new JsonResult<>();
        List<BuyerDO> list = service.list();
        result.buildData(list);
        return result.buildIsSuccess(true);

    }

    /**
     * 查询采购任务的明细
     * @return
     */
    @PostMapping("/queryTaskDailyList")
    public Object queryTaskDailyList(BuyerTaskDO buyerTaskDO) {
        JsonResult<List<BuyerTaskDO>> result = new JsonResult<>();
        List<BuyerTaskDO> list = buyerTaskService.list(buyerTaskDO);
        return result.buildData(list).buildIsSuccess(true);
    }

    /**
     * 新增采购任务
     * @param buyerTaskDO
     * @return
     */
    @PostMapping("/add")
    public Object addTask(BuyerTaskVO buyerTaskDO) {
        JsonResult<List<BuyerTaskDO>> result = new JsonResult<>();
        buyerTaskService.add(buyerTaskDO);
        return result.buildIsSuccess(true);
    }

    /**
     * 查询该买手的采购任务
     * @param buyerTaskDO
     * @return
     */
    @PostMapping("/queryBuyerTaskList")
    public Object queryBuyerTaskList(BuyerTaskDO buyerTaskDO) {
        JsonResult<List<BuyerTaskDO>> result = new JsonResult<>();
        List<BuyerTaskDO> list = buyerTaskService.list(buyerTaskDO);
        result.buildData(list);
        return result.buildIsSuccess(true);
    }

}
