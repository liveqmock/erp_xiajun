package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.purchase.app.service.IBuyerService;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        try {
            List<BuyerDO> list = service.list();
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.toString());
        }
        return result.buildIsSuccess(true);

    }



    @PostMapping("/setCommission")
    public Object setCommission(Long id, String purchaseCommissionStr, Long purchaseCommissionMode) {
        try {
            BuyerDO buyer = service.selectByPrimaryKey(id);
            buyer.setPurchaseCommissionMode(purchaseCommissionMode);
            buyer.setPurchaseCommissionStr(purchaseCommissionStr);
            service.updateByPrimaryKey(buyer);
        } catch (Exception e) {
            return JsonResult.buildSuccess(false).buildMsg(e.toString());
        }
        return JsonResult.buildSuccess(true);
    }

    @GetMapping("/queryBuyerById")
    public Object setCommission(Long id) {
        BuyerDO buyer = service.selectByPrimaryKey(id);
        return JsonResult.buildSuccess(buyer);
    }

    @PostMapping("/add")
    public Object add(BuyerDO buyer) {
        try {
            buyer.initCompany();
            buyer.init();
            service.insert(buyer);
        } catch (Exception e) {
            return JsonResult.buildSuccess(false).buildMsg(e.toString());
        }
        return JsonResult.buildSuccess(buyer);
    }


    @PostMapping("/update")
    public Object update(BuyerDO buyer) {
        try {
            BuyerDO buyerOld = service.selectByPrimaryKey(buyer.getId());
            buyerOld.setCompanyNo(AppUtil.getLoginUserCompanyNo());
            buyerOld.setNickName(buyer.getNickName());
            //仓库暂时不添加，后续再维护
            buyerOld.setPurchaseCommissionStr(buyer.getPurchaseCommissionStr());
            service.updateByPrimaryKey(buyerOld);
        } catch (Exception e) {
            return JsonResult.buildSuccess(false).buildMsg(e.toString());
        }
        return JsonResult.buildSuccess(buyer);
    }

    @PostMapping("/delete")
    public Object delete(Long id) {
        try {
            service.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.buildSuccess(true);
    }


}
