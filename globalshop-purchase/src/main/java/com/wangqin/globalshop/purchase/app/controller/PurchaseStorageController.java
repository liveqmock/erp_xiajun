package com.wangqin.globalshop.purchase.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.purchase.app.service.IBuyerStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
        try {
            List<BuyerStorageDO> list = service.list(buyerStorage);
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);

    }


    @RequestMapping("/searchByopenid")
    public Object searchByOpenId(Long buyerOpenId){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            List<BuyerStorageDetailVo> list = service.searchByOpenId(buyerOpenId);
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);

    }

    @RequestMapping("/searchByopenidAndUpc")
    public Object searchByopenidAndUpc(Long buyerOpenId, String upc){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            List<BuyerStorageDetailVo> list = service.searchByopenidAndUpc(buyerOpenId, upc);
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/searchAll")
    public Object searchAll(){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            List<BuyerStorageDetailVo> list = service.searchAll();
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }

    @PostMapping("/comfirmBody")
    @ResponseBody
    public Object comfirmBody(BuyerStorageDetailVo detailVo){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            service.comfirm(detailVo);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }

    @PostMapping("/comfirm")
    public Object comfirm(String  detailVo){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            BuyerStorageDetailVo detail = HaiJsonUtils.toBean(detailVo, new TypeReference<BuyerStorageDetailVo>(){});
            service.comfirm(detail);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/queryHasComfirm")
    public Object queryHasComfirm(){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            List<BuyerStorageDetailVo> list = service.queryHasComfirm();
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }


    @RequestMapping("/queryComfirmWithParam")
    public Object queryComfirmWithParam(Long buyerOpenId, String upc){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            List<BuyerStorageDetailVo> list = service.queryComfirmWithParam(buyerOpenId,upc);
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 软删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Object delete(Long id){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            service.deleteById(id);

        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/updateMem")
    public Object updateMem(Long id, String mem){
        JsonResult<List<BuyerStorageDetailVo>> result = new JsonResult<>();
        try {
            service.updateMem(id, mem);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }







}
