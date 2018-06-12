package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.purchase.app.service.IBuyerStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@RequestMapping("/purchaseStorage")
@ResponseBody
@Controller
@Authenticated
public class PurchaseStorageController {
    @Autowired
    private IBuyerStorageService service;
    @RequestMapping("/queryPurStorages")
    public Object queryPurStorages(BuyerStorageDO buyerStorage){
        JsonResult<List<BuyerStorageDO>> result = new JsonResult<>();
        List<BuyerStorageDO> list = service.list(buyerStorage);
        result.buildData(list);
        return result.buildIsSuccess(true);

    }
}
