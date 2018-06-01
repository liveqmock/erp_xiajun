package com.wangqin.globalshop.purchase.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangqin.globalshop.common.utils.TimeUtil;
import com.wangqin.globalshop.purchase.service.TaskService;
import com.wangqin.globalshop.biz1.app.constants.enums.TaskStatusEnum;
import com.wangqin.globalshop.common.GlobalContants;
import com.wangqin.globalshop.common.result.RetCode;
import com.wangqin.globalshop.common.utils.ErrorResult;
import com.wangqin.globalshop.common.utils.JSONUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.common.utils.SingleResult;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.SuccessResult;


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
