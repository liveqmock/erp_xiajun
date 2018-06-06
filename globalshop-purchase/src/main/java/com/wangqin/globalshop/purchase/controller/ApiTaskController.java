package com.wangqin.globalshop.purchase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.common.result.RetCode;
import com.wangqin.globalshop.common.utils.ErrorResult;
import com.wangqin.globalshop.common.utils.SuccessResult;
import com.wangqin.globalshop.purchase.service.TaskService;


@Controller
@ResponseBody
@RequestMapping("/api/task")
public class ApiTaskController {

    @Autowired
    TaskService taskService;


    @RequestMapping("/calc")
    public String calc(Long[] taskDetailId, Integer[] transQuantity, Integer[] quantity, String[] skuBuysite,
                       Long buyerId, Double[] purchasePrice, long[] skuId) {
        if (taskDetailId == null || taskDetailId.length == 0) {
            return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
        }
        int result = 0;
        for (int i = 0; i < taskDetailId.length; i++) {
            String skuBuysiteStr = "";
            if (null != skuBuysite && skuBuysite.length > i) {
                skuBuysiteStr = skuBuysite[i];
            }
            Boolean flag = dealCalc(taskDetailId[i], transQuantity[i], quantity[i], skuBuysiteStr, skuId[i],
                                    purchasePrice[i], buyerId);
            if (flag == null && taskDetailId.length == 1) {
                return new ErrorResult(RetCode.SYS_ERROR, "采购数量已上限").toJSONString();
            }
            if (flag) {
                result++;
            }
        }
        if (result == 0) {
            return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
        }
        return new SuccessResult().toJSONString();
    }

    @Transactional
    public Boolean dealCalc(Long taskDetailId, Integer transQuantity, Integer quantity, String skuBuysite, Long skuId,
                            Double purchasePrice, Long buyerId) {
        return true;
    }

}
