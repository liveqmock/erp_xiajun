package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.vo.BuyerTaskVO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.excel.ReadExcel;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/19
 */
@Controller
@ResponseBody
@RequestMapping("/purchaseTask")
@Authenticated
public class BuyerTaskController {
    @Autowired
    private IBuyerTaskService buyerTaskService;

    @RequestMapping("/improtTask")
    public Object importTask(MultipartFile file) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            if (!file.isEmpty()) {
                // 文件保存路径
                List<List<Object>> list = ReadExcel.readExcel(file.getInputStream(),file.getOriginalFilename(),1,0,5);
                buyerTaskService.importTask(list);
            }
        } catch (IOException e) {
            return result.buildIsSuccess(false).buildMsg("文件上传错误，请重试");
        } catch (ErpCommonException e) {
            String str = e.getErrorMsg().replace(",", "</br>");
            return result.buildIsSuccess(false).buildMsg(str);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true).buildMsg("上传成功");
    }



    /**
     * 查询采购任务的明细
     * @return
     */
    @PostMapping("/queryTaskDailyList")
    public Object queryTaskDailyList(BuyerTaskVO buyerTaskVO) {
        JsonResult<List<BuyerTaskVO>> result = new JsonResult<>();
        List<BuyerTaskVO> list = null;
        try {
            list = buyerTaskService.list(buyerTaskVO);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildData(list).buildIsSuccess(true);
    }

    /**
     * 新增采购任务
     * @param buyerTaskDO
     * @return
     */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public Object addTask(BuyerTaskVO buyerTaskDO) {
        JsonResult<List<BuyerTaskDO>> result = new JsonResult<>();
        try {
            buyerTaskService.add(buyerTaskDO);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.toString());
        }
        return result.buildIsSuccess(true);
    }

    /**
     * 查询该买手的采购任务
     * @param buyerTaskDO
     * @return
     */
    @PostMapping("/queryBuyerTaskList")
    public Object queryBuyerTaskList(BuyerTaskVO buyerTaskVO) {
        JsonResult<List<BuyerTaskVO>> result = new JsonResult<>();
        try {
            List<BuyerTaskVO> list = buyerTaskService.list(buyerTaskVO);
            result.buildData(list);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true);
    }
}
