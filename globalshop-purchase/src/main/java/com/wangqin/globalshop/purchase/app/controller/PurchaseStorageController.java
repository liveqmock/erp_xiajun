package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.purchase.app.service.IBuyerStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
//@Authenticated
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


    @RequestMapping("/searchByopenid")
    public Object searchByOpenId(Long buyerOpenId){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();

        List<BuyerStorageDetailVo> list = service.searchByOpenId(buyerOpenId);

        result.buildData(list);
        return result.buildIsSuccess(true);

    }

    @RequestMapping("/searchByopenidAndUpc")
    public Object searchByopenidAndUpc(Long buyerOpenId, String upc){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        List<BuyerStorageDetailVo> list = service.searchByopenidAndUpc(buyerOpenId, upc);
        result.buildData(list);
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/searchAll")
    public Object searchAll(){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        List<BuyerStorageDetailVo> list = service.searchAll();
        result.buildData(list);
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/comfirm")
    public Object comfirm(@RequestBody BuyerStorageDetailVo detailVo){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        service.comfirm(detailVo);
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/queryHasComfirm")
    public Object queryHasComfirm(){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        List<BuyerStorageDetailVo> list = service.queryHasComfirm();
        result.buildData(list);
        return result.buildIsSuccess(true);
    }








}
