package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.excel.ReadExcel;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/19
 */
@Controller
@ResponseBody
@RequestMapping("/purchaseTask")
public class BuyerTaskController {
    @Autowired
    private IBuyerTaskService buyerTaskService;

    @RequestMapping("improtTask")
    public Object importTask(HttpServletRequest request, String savePath) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/");
            String fileName = realPath + savePath;
            // 读取上传的文件
            File baseFile = new File(fileName);
            // 从第四行的第A列到X列开始读
            List<List<Object>> list = ReadExcel.readExcel(baseFile, 1, 0, 12);
            String[] errMsg =  buyerTaskService.importTask(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.buildIsSuccess(true);
    }
}
